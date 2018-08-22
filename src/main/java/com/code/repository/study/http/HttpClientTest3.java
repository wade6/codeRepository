package com.code.repository.study.http;

import com.code.repository.util.ExcelUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpClientTest3 implements Runnable{
	
	private int start;
	
	private int end;
	
	public HttpClientTest3(int start,int end){
		this.start = start;
		this.end = end;
	}
	
	
	public static void main(String[] args) {
		
		int corePoolSize = 10;  // 主流线程个数
		int maximumPoolSize = 15; // 线程最大个数
		long keepAliveTime = 0L; // 大于主流线程个数的线程的过期时间  wait for new tasks before terminating
		TimeUnit unit = TimeUnit.MILLISECONDS; // 时间单元
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(); // 工作队列，有三种类SynchronousQueue、LinkedBlockingQueue(在所有 corePoolSize 线程都忙时新任务在队列中等待,maximumPoolSiz失效)、ArrayBlockingQueue，分别对应同步队列、无界队列、有界队列。

		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, keepAliveTime, unit, workQueue);
		
//		HttpClientTest3 r0 = new HttpClientTest3(1,2);
//		Thread t0 = new Thread(r0,"t0");
//		t0.start();
//		int step = 20000;
		int step = 30;
		int i =1;
//		for(int start = 63000;start<1000000;start = start + step){
			for(int start = 1;start<100;start = start + step){
			int end = start+step;
//			System.out.println(start+":"+end +"="+i);
//			i++;
			HttpClientTest3 r = new HttpClientTest3(start,end);
			Thread t = new Thread(r);
			executorPool.execute(t);
		}
		executorPool.shutdown();
		
//		HttpClientTest3 r1 = new HttpClientTest3(24000,27000);
//		HttpClientTest3 r2 = new HttpClientTest3(30000,33000);
//		HttpClientTest3 r3 = new HttpClientTest3(33000,36000);
//		HttpClientTest3 r5 = new HttpClientTest3(39000,42000);
//		HttpClientTest3 r6 = new HttpClientTest3(42000,45000);
//		HttpClientTest3 r7 = new HttpClientTest3(48000,51000);
//		HttpClientTest3 r8 = new HttpClientTest3(54000,57000);
//		HttpClientTest3 r4 = new HttpClientTest3(60000,63000);
//		
//		Thread t1 = new Thread(r1,"t1");
//		Thread t2 = new Thread(r2,"t2");
//		Thread t3 = new Thread(r3,"t3");
//		Thread t4 = new Thread(r4,"t4");
//		Thread t5 = new Thread(r5,"t5");
//		Thread t6 = new Thread(r6,"t6");
//		Thread t7 = new Thread(r7,"t7");
//		Thread t8 = new Thread(r8,"t8");
//		
//		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
//		t5.start();
//		t6.start();
//		t7.start();
//		t8.start();
		
	}
	
	
	@Override
	public void run() {
		List<MyContet> myList = new ArrayList<MyContet>();
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File("D:\\result"+start+".xls"));
			// 创建book
			Workbook wb = new HSSFWorkbook();
			
			// 表头单元格边STYLE
			CellStyle headStyle = ExcelUtil.getDefautHeaderStyle(wb);
			// 数据 单元格STYLE
			CellStyle dataStyle = ExcelUtil.getDefautDataStyle(wb);
			// 创建 包裹信息sheet
			Sheet sheet = wb.createSheet("default");
			Map<String, String> mapping = new HashMap<String, String>();
			mapping.put("name", "name");
			mapping.put("contact", "contact");
			mapping.put("web", "web");
			mapping.put("address", "address");
			mapping.put("mail", "mail");
			mapping.put("i", "i");
			Collection<String> headers = mapping.values();
			Set<String> pros = mapping.keySet();
			ExcelUtil.addHeaders(sheet, headers, headStyle, 0);
						
			for(int i=start;i<end;i++){
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
						continue;
					}
					MyContet my = this.parse(content,i);
					if(my == null){
						continue;
					}
					myList.add(my);
					ExcelUtil.writeRows(sheet, myList, pros, dataStyle, 1);
					// 写入到输出流
					wb.write(os);
					myList.clear();
					System.out.println("ok:"+i);
				}else{
					System.out.println("errorCode"+code);
					continue;
				}
			}
		} catch (Exception e) {
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		if(myList.size()>0){
//			OutputStream os = null;
//			try {
//				
//				os = new FileOutputStream(new File("D:\\result"+start+".xls"));
//				Map<String, String> mapping = new HashMap<String, String>();
//				mapping.put("name", "name");
//				mapping.put("contact", "contact");
//				mapping.put("web", "web");
//				mapping.put("address", "address");
//				mapping.put("mail", "mail");
//				mapping.put("i", "i");
//				ExcelUtil.defaultExport(os, myList, mapping);
//			} catch (Exception e) {
//			}finally{
//				try {
//					os.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		}
		
	}
	
	
	public static <T> void defaultExport(OutputStream os, List<T> dataList, Map<String, String> mapping)
            throws Exception {

			// 创建book
			Workbook wb = new HSSFWorkbook();
			
			// 表头单元格边STYLE
			CellStyle headStyle = ExcelUtil.getDefautHeaderStyle(wb);
			// 数据 单元格STYLE
			CellStyle dataStyle = ExcelUtil.getDefautDataStyle(wb);
			// 创建 包裹信息sheet
			Sheet sheet = wb.createSheet("default");
			
			Collection<String> headers = mapping.values();
			Set<String> pros = mapping.keySet();
			ExcelUtil.addHeaders(sheet, headers, headStyle, 0);
			ExcelUtil.writeRows(sheet, dataList, pros, dataStyle, 1);
			
			// 写入到输出流
			wb.write(os);

	}
	
	 
	
	private   MyContet parse(String content,int i){
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
		String name = this.getString(titleStartElement, titleEndElement, content);
		String contact = this.getString(telStartElement, telEndElement, content);
		String web = this.getString(webStartElement, webEndElement, content);
		String address = this.getString(addrStartElement, addrEndElement, content);
		String mail = this.getString(mailStartElement, mailEndElement, content);
//		if(StringUtils.isBlank(address) || !address.contains("南京市")){
//			return null;
//		}
		MyContet my = new MyContet();
		my.setName(name);
		my.setContact(contact);
		my.setWeb(web);
		my.setAddress(address);
		my.setMail(mail);
		my.setI(i);
		return my;
	}
	
 
	
	private  String getString(String startElement,String endElement,String content){
		int titleIndex = content.indexOf(startElement);
		if(titleIndex<=0){
			return null;
		}
		return content.substring(titleIndex+startElement.length(), titleIndex+content.substring(titleIndex).indexOf(endElement));
	}

	


	
	 
}
