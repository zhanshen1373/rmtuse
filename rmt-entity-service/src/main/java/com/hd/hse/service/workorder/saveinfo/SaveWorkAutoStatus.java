/**
 * Project Name:hse-entity-service
 * File Name:SaveWorkAutoStatus.java
 * Package Name:com.hd.hse.service.workorder.saveinfo
 * Date:2014年10月29日
 * Copyright (c) 2014, zhaofeng@ushayden.com All Rights Reserved.
 *
 */
package com.hd.hse.service.workorder.saveinfo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.hd.hse.common.exception.DaoException;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.constant.IRelativeEncoding;
import com.hd.hse.constant.IWorkOrderStatus;
import com.hd.hse.entity.base.RelationTableName;
import com.hd.hse.entity.workorder.WorkOrder;
import com.hd.hse.service.workorder.checkrules.AbstractCheckListener;

/**
 * ClassName: SaveWorkAutoStatus ()<br/>
 * date: 2014年10月29日 <br/>
 * 
 * @author zhaofeng
 * @version
 */
public class SaveWorkAutoStatus extends AbstractCheckListener {

	@Override
	public Object action(String action, Object... object) throws HDException {
		// TODO Auto-generated method stub
		saveWorkAutoStatus();
		return null;
	}

	private void saveWorkAutoStatus() throws HDException {
		try {
			StringBuilder sbSql = new StringBuilder();
			// 自动作废
			WorkOrder workOrder = new WorkOrder();
			workOrder.setStatus(IWorkOrderStatus.NULLIFY);
			workOrder.setSpstatus(IWorkOrderStatus.SPSTATUS_NULLIFY);
			workOrder.setZfcause("自动作废");
			sbSql.append("update ud_zyxk_zysq set isupload=0, spstatus ='")
					.append(workOrder.getSpstatus()).append("', status='")
					.append(workOrder.getStatus()).append("',zfcause='")
					.append(workOrder.getZfcause()).append("'  ");
			sbSql.append("  where datetime(sjendtime)<datetime('now', 'localtime')");
			sbSql.append(" and status not in ('CLOSE','CAN','CQCLOSE','NULLIFY','WAPPR') and (status='INPRG' or (status='APPR' and ifnull(spstatus,'')='SPAPPR'));");

			StringBuilder cqSbSql = new StringBuilder();
			// 自动关闭
			WorkOrder cqWorkOrder = new WorkOrder();
			cqWorkOrder.setStatus(IWorkOrderStatus.CQCLOSE);
			cqWorkOrder.setSpstatus(IWorkOrderStatus.SPSTATUS_CQCLOSE);
			cqWorkOrder.setZfcause("自动关闭");
			cqSbSql.append("update ud_zyxk_zysq set isupload=0, spstatus ='")
					.append(cqWorkOrder.getSpstatus()).append("', status='")
					.append(cqWorkOrder.getStatus()).append("',zfcause='")
					.append(cqWorkOrder.getZfcause()).append("'  ");
			RelationTableName relation = getRelationTableName(IRelativeEncoding.HCSJPZ);
			int hcsj = 0;
			if (relation != null
					&& !StringUtils.isEmpty(relation.getInput_value())
					&& NumberUtils.isNumber(relation.getInput_value())) {
				hcsj = Integer.valueOf(relation.getInput_value());
			}
			cqSbSql.append("  where  (julianday('now', 'localtime')-julianday(sjendtime))*24*60>"
					+ hcsj);

			cqSbSql.append("   and status not in ('CLOSE','CAN','CQCLOSE','NULLIFY','WAPPR','INPRG') and  ifnull(spstatus,'')!='SPAPPR';");
			dao.executeUpdate(connection, new String[] { sbSql.toString(),
					cqSbSql.toString() });
		} catch (DaoException e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			throw new HDException("更新作业票状态失败！", e);
		}
	}
}
