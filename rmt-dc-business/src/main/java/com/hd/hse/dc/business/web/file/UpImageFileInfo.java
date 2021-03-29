package com.hd.hse.dc.business.web.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.weblistener.AbsWebListener;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;
import com.hd.hse.padinterface.PadInterfaceResponse;
import com.hd.hse.system.SystemProperty;
import com.hd.hse.utils.UtilTools;

/**
 * ClassName: UpImageFileInfo (上传图片文件信息)<br/>
 * date: 2015年5月28日 <br/>
 * 需要提供删除图片的对象
 * 
 * @author lxf
 * @version
 */
public class UpImageFileInfo extends AbsWebListener {

	private static Logger logger = LogUtils.getLogger(UpImageFileInfo.class);
	private HashMap<String, Object> hashmap = new HashMap<String, Object>();
	private List<SuperEntity> listimage = null;
	List<HashMap<String, HashMap<String, Object>>> listfile = null;

	@SuppressWarnings("unchecked")
	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		if (args != null) {
			if (args[0] instanceof List) {
				listimage = (List<SuperEntity>) args[0];
			} else if (args[0] instanceof SuperEntity) {
				listimage = new ArrayList<SuperEntity>();
				listimage.add((SuperEntity) args[0]);
			} else {
				//
				throw new HDException("没有传入要传的文件对象");
			}
		}
		super.action(action, args);
		listfile = getFilePathInfo();
		List<String> listsuccess = uploadFile(listfile);
		if (null != listsuccess && null != listfile) {
			// 表示上传图片报错
			deleteSDcardFile(listsuccess);
			if(listfile.size() != listsuccess.size()){
			throw new HDException("上传照片成功：" + listsuccess.size() + ";失败："
					+ (listfile.size() - listsuccess.size()));
			}
		}
		return listsuccess;
	}

	@Override
	public Object initParam() throws HDException {
		hashmap.put(PadInterfaceRequest.KEYPERSONID, SystemProperty
				.getSystemProperty().getLoginPerson().getPersonid());
		return hashmap;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_COMMON_UPFILE;
	}

	public List<HashMap<String, HashMap<String, Object>>> getFilePathInfo() {
		// TODO Auto-generated method stub
		List<HashMap<String, HashMap<String, Object>>> listImage = new ArrayList<HashMap<String, HashMap<String, Object>>>();
		HashMap<String, HashMap<String, Object>> imagehash;
		HashMap<String, Object> mapwhere;
		String filename;
		for (SuperEntity imagesuper : listimage) {
			// 表示取出每一行
			String path = null;
			mapwhere = new HashMap<String, Object>();
			imagehash = new HashMap<String, HashMap<String, Object>>();

			path = imagesuper.getAttribute("imagepath") == null ? ""
					: imagesuper.getAttribute("imagepath").toString();
			// 业务id
			mapwhere.put(PadInterfaceRequest.KEYZYPSTRID,
					imagesuper.getAttribute("tableid"));

			// 业务类型

			mapwhere.put(PadInterfaceRequest.KEYFUNCODE,
					imagesuper.getAttribute("funcode"));

			// 创建时间
			mapwhere.put(PadInterfaceRequest.KEYCREATETIME,
					UtilTools.getSysCurrentTime());
			// 登录拍照人
			mapwhere.put(PadInterfaceRequest.KEYPERSONID, SystemProperty
					.getSystemProperty().getLoginPerson().getPersonid());
			// 登录拍照人
			mapwhere.put(PadInterfaceRequest.KEYPERSONDESC, SystemProperty
					.getSystemProperty().getLoginPerson().getPersonid_desc());

			if (null != path && path.length() > 0) {
				filename = getFileName(path);
				// 文件名
				mapwhere.put(PadInterfaceRequest.KEYFILENAME, filename);
				// appName
				//mapwhere.put(PadInterfaceRequest.KEYAPPNAME, "appzyxkonline");
				mapwhere.put(PadInterfaceRequest.KEYAPPNAME, "appzysq");
				// MAC地址
				mapwhere.put(PadInterfaceRequest.KEYMAC, SystemProperty
						.getSystemProperty().getPadmac());
				imagehash.put(path, mapwhere);
				listImage.add(imagehash);
				// imagehash.clear();
			}

		}
		return listImage;
	}

	/**
	 * 根绝路径返回文件名
	 * 
	 * @param path
	 *            路径
	 * @return 文件名
	 */
	private String getFileName(String path) {
		String filename;
		int start = path.lastIndexOf("/");
		int end = path.length();
		if (start != -1 && end != -1) {
			filename = path.substring(start + 1, end);
		} else {
			filename = "hd_zhangjie.jpg";
		}
		return filename;
	}

	/**
	 * 返回上传成功的图片信息
	 * */
	private List<String> uploadFile(
			List<HashMap<String, HashMap<String, Object>>> listImage) {
		// 循环上传图片
		List<String> listsuccess = new ArrayList<String>();
		for (HashMap<String, HashMap<String, Object>> hasmap : listImage) {
			// 此时key表示路径
			Iterator<Entry<String, HashMap<String, Object>>> iter = hasmap
					.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, HashMap<String, Object>> entry = iter.next();
				String image = entry.getKey();
				HashMap<String, Object> map = entry.getValue();
				try {
					File file = new File(image);
					if (!file.exists()) {
						listsuccess.add(image);
						continue;
					}
					Object obj = sClient.upFile(getMethodType(), image, map);
					if (obj instanceof PadInterfaceResponse) {
						PadInterfaceResponse response = (PadInterfaceResponse) obj;
						if (response.getFlag().equalsIgnoreCase("T")) {
							listsuccess.add(image);
						}
					} else {
						listsuccess.add(image);
					}
				} catch (Exception e) {
					setWritelog("上传图片报错:", e);
				}
			}
		}
		return listsuccess;
	}

	/**
	 * deleteSDcardFile:(删除SDCard文件). <br/>
	 * date: 2015年3月25日 <br/>
	 * 
	 * @author lxf
	 * @param listfile
	 * @throws HDException
	 */

	private void deleteSDcardFile(List<String> listfile) throws HDException {
		// 表示上传完文件后执行的动作
		if (null != listfile) {
			// 删除本地文件信息
			for (String path : listfile) {
				File file = new File(path);
				if (file.exists()) {
					file.delete();
				}
				// 删除空文件夹
				String directoryPath = path.substring(0, path.lastIndexOf("/"));
				file = new File(directoryPath);
				if (file.isDirectory()) {
					String[] fileNames = file.list();
					if (fileNames == null || fileNames.length == 0) {
						file.delete();
					}
				}
			}

		}
	}
}
