package com.hd.hse.dc.business.common.webservice;

import java.util.HashMap;

import com.hd.hse.common.exception.HDException;

/**
 * ClassName:WebServlet (支持servlet通信).<br/>
 * Date: 2014年8月21日 <br/>
 * 
 * @author lxf
 * @version
 * @see
 **/
public class HttpClient extends AbstractWebServiceClient {



	public static HttpClient newInstance() {

		return new HttpClient();

	}

	private HttpClient() {
	
	}

	

}
