package com.code.repository.study.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.*;

// zSearch访问测试
public class HttpClientTest_zsearch {
	
	public static void main(String[] args) throws  NoSuchAlgorithmException, UnsupportedEncodingException {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("bizType", "MSG_DIRECT_EXPORT_CALLBACK_BIZ_TYPE");
		params.put("formatType", "1");
		params.put("bizKey", "LP00096553139264");

		// content内容
		JSONObject json = new JSONObject();
		json.put("orderCode", "LP00096553139264");
		json.put("optTime", "2018-04-02 14:33:12");
		json.put("status", "123");

		params.put("content", json.toJSONString());

		String url = "https://linkdaily.tbsandbox.com/gateway/custom/customsCommonPlgin?msg_type=GLOBAL_CUSTOMS_DECLARE_CALLBACK&from_code=hzdirect_001";
		System.out.println("服务器的响应是:"+HttpClientTest_zsearch.doPost(url, params));
	}
	
	 

	/**
	 * post请求
	 */
	private static String doPost(String url, Map params) {
		// 定义HttpClient
		HttpClient client = HttpClients.createDefault();
		// 实例化HTTP方法
		HttpPost request = new HttpPost();
		try {
			// 初始化post请求头
			request.addHeader("Accept","text/plain");
			request.addHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
			request.setURI(new URI(url));
			// 设置参数  
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();   
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {  
                String name = (String) iter.next();  
                String value = String.valueOf(params.get(name));  
                nvps.add(new BasicNameValuePair(name, value));  
            }  
            request.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));  
            // 发起调用
			HttpResponse response = client.execute(request);
			// 解析结果
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) { // 请求成功
				HttpEntity entity=response.getEntity();  
				return EntityUtils.toString(entity, "utf-8");
			} else { 
				System.out.println("状态码：" + code);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			// 释放连接
			request.releaseConnection();
		}
	}
	
	 
}
