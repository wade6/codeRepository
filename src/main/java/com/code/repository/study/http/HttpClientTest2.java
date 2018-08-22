package com.code.repository.study.http;

import com.code.repository.util.ExcelUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientTest2 {
	
	public static void main(String[] args) throws  Exception {
		
		List<MyContet> myList = new ArrayList<MyContet>();
		try {
			for(int i=0;i<10;i++){
				Thread.sleep(800);
				String url = "http://www.maigoo.com/company/"+i+".html";
				// 定义HttpClient
				HttpClient client = HttpClients.createDefault();
				HttpGet request = new HttpGet();
				request.addHeader("Accept","text/plain");
				request.addHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
				request.setURI(new URI(url));
				HttpResponse response = client.execute(request);
				int code = response.getStatusLine().getStatusCode();
				if (code == 200) { // 请求成功
					HttpEntity entity=response.getEntity();  
					String content = EntityUtils.toString(entity, "utf-8");
					if(content.contains("很抱歉，您访问的页面无法显示！")){
						System.out.println("很抱歉，您访问的页面无法显示！"+url);
						request.releaseConnection();
						continue;
					}
					MyContet my = HttpClientTest2.parse(content,i);
					myList.add(my);
					System.out.println("ok:"+i);
					request.releaseConnection();
				}else{
					System.out.println("errorCode"+code);
					request.releaseConnection();
					continue;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		if(myList.size()>0){
			OutputStream os = null;
			try {
				
				os = new FileOutputStream(new File("D:\\result.xls"));
				Map<String, String> mapping = new HashMap<String, String>();
				mapping.put("name", "name");
				mapping.put("contact", "contact");
				mapping.put("web", "web");
				mapping.put("address", "address");
				mapping.put("mail", "mail");
				mapping.put("i", "i");
				ExcelUtil.defaultExport(os, myList, mapping);
			} catch (Exception e) {
			}finally{
				os.close();
			}
			
		}
		
		
	}
	
	public  static MyContet parse(String content,int i){
		String titleStartElement = "<span class=\"title font30 b c333 \">";
		String titleEndElement = "</span>";
		String telStartElement = "<i class=\"iconfont icon-lianxidianhua sblue\"></i>";
		String telEndElement = "</li>";
		String webStartElement = "target=\"_blank\" class=\"c3f6799\">";
		String webEndElement = "</a></li>";
		String addrStartElement = "<i class=\"iconfont icon-dizhi sblue\"></i>";
		String addrEndElement = "</li>";
		String mailStartElement = "<i class=\"iconfont icon-email sblue\"></i></a>";
		String mailEndElement = "</li>";
		String name = HttpClientTest2.getString(titleStartElement, titleEndElement, content);
		String contact = HttpClientTest2.getString(telStartElement, telEndElement, content);
		String web = HttpClientTest2.getString(webStartElement, webEndElement, content);
		String address = HttpClientTest2.getString(addrStartElement, addrEndElement, content);
		String mail = HttpClientTest2.getString(mailStartElement, mailEndElement, content);
		MyContet my = new MyContet();
		my.setName(name);
		my.setContact(contact);
		my.setWeb(web);
		my.setAddress(address);
		my.setMail(mail);
		my.setI(i);
		return my;
	}
	
 
	
	public static String getString(String startElement,String endElement,String content){
		int titleIndex = content.indexOf(startElement);
		if(titleIndex<=0){
			return null;
		}
		return content.substring(titleIndex+startElement.length(), titleIndex+content.substring(titleIndex).indexOf(endElement));
	}


	
	 
}
