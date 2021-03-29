/**
 * Project Name:hse-cbs-app
 * File Name:LoginActionListener.java
 * Package Name:com.hd.hse.cbs.business.login
 * Date:2014年9月3日
 * Copyright (c) 2014, longgang@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.main.business.login;

import android.os.Message;

import com.hd.hse.business.listener.AbstractActionListener;
import com.hd.hse.business.task.IMessageWhat;
import com.hd.hse.common.exception.AppException;
import com.hd.hse.common.exception.DaoException;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.constant.IActionType;
import com.hd.hse.dao.BaseDao;
import com.hd.hse.dao.connection.IConnection;
import com.hd.hse.dao.factory.ConnectionSourceManager;
import com.hd.hse.dao.result.EntityResult;
import com.hd.hse.dao.result.MapResult;
import com.hd.hse.dao.source.IConnectionSource;
import com.hd.hse.dc.business.listener.basicdata.BasicDataInit;
import com.hd.hse.dc.business.listener.basicdata.LoginDataUpdate;
import com.hd.hse.dc.business.listener.common.PCCheckUser;
import com.hd.hse.entity.common.PersonCard;
import com.hd.hse.entity.common.RecentlyLoginPerson;
import com.hd.hse.system.SystemProperty;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * ClassName:LoginActionListener (登录业务处理).<br/>
 * Date: 2014年9月3日 <br/>
 * 
 * @author lg
 * @version
 * @see
 */
public class LoginActionListener extends AbstractActionListener {

	private static Logger logger = LogUtils
			.getLogger(LoginActionListener.class);

	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		// 登录
		if (IActionType.LOGIN_LOGIN.equals(action)) {
			return login(args[0]);
		}
		return super.action(action);
	}

	/**
	 * login:(登录验证). <br/>
	 * date: 2014年9月3日 <br/>
	 * 
	 * @author lg
	 * @param user
	 * @return登录用户信息
	 * @throws AppException
	 */
	private PersonCard login(Object user) {
		PersonCard psnCard = (PersonCard) user;
		try {
			// 本地验证
			PersonCard loginPsn = localChk(psnCard);
			// 空库时，远程验证
			boolean nullFlag = false;
			if (loginPsn == null) {
				//if (nullFlag) {
					loginPsn = remoteLogin(psnCard);
				//}
			}
			if (loginPsn == null) {
				throw new AppException("用户名或密码错误，请确认！");
			}
			// 登录成功，记录登录人
			else {
				SystemProperty.getSystemProperty().setLoginPerson(loginPsn);
				// 保存登录人信息
				updateRtyLoginInfo(loginPsn);
				// 空库和非空库时，更新基础数据
				nullFlag = isNullDB();
				if (nullFlag) {
					//空库执行初始化
					initDownLoadData();
				}
				else{
					//非空库执行更新
					remoteDownloadData();
				}
				// 成功登录
				sendAsyncLoginSusMsg();
				return loginPsn;
			}
		} catch (AppException e) {
			logger.error("登录验证出错：" + e.getMessage(), e);
			sendAsyncErrMsg(e.getMessage());
		}
		return null;
	}

	/**
	 * localChk:(本地验证). <br/>
	 * date: 2014年9月5日 <br/>
	 * 
	 * @author lg
	 * @param psnCard
	 * @return
	 * @throws AppException
	 */
	private PersonCard localChk(PersonCard psnCard) throws AppException {
		PersonCard loginPsn = null;
		StringBuilder sbSql = new StringBuilder();
		BaseDao dao = new BaseDao();
		try {
			String psnID = psnCard.getPersonid();// 人员编号
			String pwd = psnCard.getPassword();// 密码
			String cardNum = psnCard.getPcardnum();// 人员卡卡号
			sbSql.append("select * from ud_zyxk_ryk where islogin = 1 and iscan = 1");
			// 通过人员卡登录
			if (!StringUtils.isEmpty(cardNum)) {
				sbSql.append(" and pcardnum='").append(cardNum).append("'");
			}
			// 通过用户名、密码登录
			else if (!StringUtils.isEmpty(psnID) && !StringUtils.isEmpty(pwd)) {
				sbSql.append(" and personid='").append(psnID).append("'");
				sbSql.append(" and (password='").append(pwd).append("'");
				sbSql.append(" or password is null)");
			} else {
				throw new AppException("请输入用户名、密码或采用刷卡登录！");
			}
			loginPsn = (PersonCard) dao.executeQuery(sbSql.toString(),
					new EntityResult(PersonCard.class));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			logger.error("登录验证出错：" + e.getMessage(), e);
			throw new AppException("登录验证失败，请联系系统管理员！");
		}
		return loginPsn;
	}

	/**
	 * isNullDB:(判断是否为空库). <br/>
	 * date: 2014年9月5日 <br/>
	 * 
	 * @author lg
	 * @return
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	private boolean isNullDB() throws AppException {
		StringBuilder selSql = new StringBuilder();
		boolean nullFlag = false;
		selSql.append("select count(personid) as psncount from ud_zyxk_ryk where personid!='0001';");
		BaseDao dao = new BaseDao();
		try {
			HashMap<String, Integer> map = (HashMap<String, Integer>) dao
					.executeQuery(selSql.toString(), new MapResult());
			if (map == null || map.get("psncount") == 0) {
				nullFlag = true;
			}
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			throw new AppException("登录验证失败，请联系系统管理员！");
		}
		return nullFlag;
	}

	/**
	 * remoteLogin:(远程登录验证). <br/>
	 * date: 2014年9月4日 <br/>
	 * 
	 * @author lg
	 * @param psnCard
	 * @return
	 * @throws AppException
	 */
	private PersonCard remoteLogin(PersonCard psnCard) throws AppException {
		PersonCard loginPsn = null;
		try {
			sendAsyncProcessingMsg("远程登录验证");
			PCCheckUser pcChkUser = new PCCheckUser();
			pcChkUser.setAsyncTask(getAsyncTask());
			loginPsn = (PersonCard) pcChkUser.action(IActionType.LOGIN_LOGIN,
					psnCard);
		} catch (HDException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e.getMessage());
		}
		return loginPsn;
	}

	/**
	 * updateRtyLoginInfo:(保留最新登录5人记录). <br/>
	 * date: 2014年9月4日 <br/>
	 * 
	 * @author lg
	 * @param psnCard
	 * @throws AppException
	 */
	private void updateRtyLoginInfo(PersonCard psnCard) throws AppException {
		boolean delFlag = true;// 是否需要删除历史记录
		int maxNum = 5;// 最大历史记录条数
		int hisOrder = 5;// 相同历史记录的顺序号
		IConnectionSource conSrc = null;
		IConnection con = null;
		try {
			// 历史登录记录中若存在此人，则先删除
			StringBuilder selSql = new StringBuilder();
			selSql.append("select * from hse_sys_login where usercode = '")
					.append(psnCard.getPersonid()).append("'");
			BaseDao dao = new BaseDao();
			RecentlyLoginPerson hisLoginPsn = (RecentlyLoginPerson) dao
					.executeQuery(selSql.toString(), new EntityResult(
							RecentlyLoginPerson.class));
			conSrc = ConnectionSourceManager.getInstance()
					.getJdbcPoolConSource();
			con = conSrc.getConnection();
			
			if (hisLoginPsn != null) {
				hisOrder = hisLoginPsn.getSysorder();
				StringBuilder delSql = new StringBuilder();
				delSql.append("delete from hse_sys_login where usercode = '")
						.append(psnCard.getPersonid()).append("'");
				dao.executeUpdate(con, delSql.toString());
				delFlag = false;
			}
			// 历史顺序+1
			StringBuilder updateSql = new StringBuilder();
			updateSql
					.append("update hse_sys_login set sysorder = sysorder + 1 where sysorder <= ")
					.append(hisOrder);
			dao.executeUpdate(con, updateSql.toString());
			// 删除顺序大于5
			if (delFlag) {
				StringBuilder delSql = new StringBuilder();
				delSql.append("delete from hse_sys_login where sysorder > ")
						.append(maxNum);
				dao.executeUpdate(con, delSql.toString());
			}
			// 保存最新登录人
			RecentlyLoginPerson newLoginPsn = new RecentlyLoginPerson();
			newLoginPsn.setUsercode(psnCard.getPersonid());
			newLoginPsn.setUsername(psnCard.getPersonid_desc());
			newLoginPsn.setSysorder(1);
			newLoginPsn.setCreatedate(SystemProperty.getSystemProperty()
					.getSysDateTime());
			dao.insertEntity(con, newLoginPsn);
			con.commit();
		} catch (DaoException e) {
			logger.error("保存历史登录信息出错：" + e.getMessage(), e);
		} catch (SQLException e) {
			logger.error("保存历史登录信息出错：" + e.getMessage(), e);
		} finally {
			if (conSrc != null) {
				try {
					conSrc.releaseConnection(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block

				}
			}
		}
	}

	/**
	 * remoteDownloadData:(更新基础数据，不能影响登录，异常不处理). <br/>
	 * date: 2014年9月5日 <br/>
	 * 
	 * @author lg
	 */
	private void remoteDownloadData() {
		
		sendAsyncProcessingMsg("更新基础数据");
		LoginDataUpdate dataUpdate = new LoginDataUpdate();
		dataUpdate.setAsyncTask(getAsyncTask());
		try {
			dataUpdate.action(null);
		} catch (HDException e) {
			// TODO Auto-generated catch block
			logger.error("更新基础数据出错：" + e.getMessage(), e);
		} catch (Exception e) {
			logger.error("更新基础数据出错：" + e.getMessage(), e);
		}
		
	}
	
	/**
	 * initDownLoadData:(数据初始化). <br/>
	 * date: 2014年11月19日 <br/>
	 *
	 * @author lxf
	 */
	public void  initDownLoadData()
	{
		sendAsyncProcessingMsg("初始化基础数据");
		BasicDataInit dataInit = new BasicDataInit();
		dataInit.setAsyncTask(getAsyncTask());
		try {
			dataInit.action(null);
		} catch (HDException e) {
			// TODO Auto-generated catch block
			logger.error("初始化基础数据：" + e.getMessage(), e);
		} catch (Exception e) {
			logger.error("初始化基础数据：" + e.getMessage(), e);
		}
	}
	
	/**
	 * sendAsyncProcessingMsg:(登录等待消息). <br/>
	 * date: 2014年9月5日 <br/>
	 *
	 * @author lg
	 * @param msg
	 */
	private void sendAsyncProcessingMsg(String msg){
		Message message = getAsyncTask().obtainMessage();
		message.what = IMessageWhat.PROCESSING;
		message.getData().putString(IActionType.LOGIN_LOGIN, msg);
		getAsyncTask().sendMessage(message);
	}

	/**
	 * sendAsyncSusMsg:(登录成功后发送消息). <br/>
	 * date: 2014年9月5日 <br/>
	 * 
	 * @author lg
	 */
	private void sendAsyncLoginSusMsg() {
		Message message = getAsyncTask().obtainMessage();
		message.what = IMessageWhat.END;
		message.getData().putString(IActionType.LOGIN_LOGIN, "登录成功");
		getAsyncTask().sendMessage(message);
	}

	/**
	 * sendAsyncErrMsg:(发送异步错误消息). <br/>
	 * date: 2014年9月5日 <br/>
	 * 
	 * @author lg
	 * @param msg
	 */
	private void sendAsyncErrMsg(String msg) {
		Message message = getAsyncTask().obtainMessage();
		message.what = IMessageWhat.ERROR;
		message.getData().putString(IActionType.LOGIN_LOGIN, msg);
		getAsyncTask().sendMessage(message);
	}

}
