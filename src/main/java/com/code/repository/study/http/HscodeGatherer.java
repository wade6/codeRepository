package com.code.repository.study.http;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhaoyuan.lizy on 2019/9/5
 **/

public class HscodeGatherer implements  Runnable{

    private int start=0;

    private int end=0;

    HscodeGatherer(int start,int end){
        this.start = start;
        this.end = end;
    }


    public static void main(String[] args){

        ExecutorService executor = Executors.newCachedThreadPool();


        String url = "https://www.customs.gov.vn/SitePages/Tariff-Search.aspx?portlet=DetailsImportTax&language=en-US&code=";
        String hscode = "62043300";
        HscodeGatherer.generateCode(62,63);
        // 爬取数据
//        Map<String,String> result = HscodeGatherer.fetchInfo(url+hscode); // 爬取数据
//        result.put("hscode",hscode); // 填充hscode
//        System.out.println(JSON.toJSONString(result));
//        HscodeGatherer.writeToFile(result);// 写文件
    }


    @Override
    public void run() {
        this.generateCode(start,end);
    }

    private static String generateCode(int start,int end){ // 开头两位 01-97，一共8位
        if(start<=0){
            start=1;
        }
        if(end>99){
            end=99;
        }
        int start1 = start;
        int start2 = 1;
        int start3 = 1;
        int start4 = 0;
        for(;start1<=end;start1++){
            for(;start2 <=99;start2++){
                for(;start3 <=99;start3++){
                    for(;start4 <=99;start4++){
                        String hscode = HscodeGatherer.numToStr(start1)+HscodeGatherer.numToStr(start2)+HscodeGatherer.numToStr(start3)+HscodeGatherer.numToStr(start4);
                        System.out.println("=====hscode:"+hscode);
                        String url = "https://www.customs.gov.vn/SitePages/Tariff-Search.aspx?portlet=DetailsImportTax&language=en-US&code=";
                        Map<String,String> result = HscodeGatherer.fetchInfo(url+hscode); // 爬取数据
                        if(result == null){
                            System.out.println("=====no result:");
                            continue;
                        }
                        result.put("hscode",hscode); // 填充hscode
                        System.out.println(JSON.toJSONString(result));
                        HscodeGatherer.writeToFile(result);// 写文件
                    }
                }
            }
        }
        return null;
    }

    // 写文件
    private static void writeToFile(Map<String,String> result){
        String fileName = "D:\\hscodeTH.txt";
        BufferedWriter out = null;
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(new File(fileName),true),"UTF-8");
            out = new BufferedWriter(ow);
            out.newLine();
            out.write(result.get("hscode")+";"+result.get("Favour")+";"+result.get("ASEAN - China  (ACFTA)"));
            out.flush();
            out.close();
        } catch (Exception e) {
        }finally{
        }
    }

    private static String numToStr(int num){
        if(num<9){
            return "0"+num;
        }else{
            return String.valueOf(num);
        }
    }

    private static Map<String,String> fetchInfo(String url){

        String html = HscodeGatherer.doGet(url);
        if(StringUtils.isBlank(html)){
            return null;
        }
        if(html.contains("Not found")){ // Not found
            System.out.println("===Not found:");
            return null;
        }
        Document doc = Jsoup.parse(html);// 结构化解析
        Elements tables = doc.select("table");
        for(Element eTable : tables){
            if (eTable.text().contains("Tax rate")) {// 锁定税率列表，提取各种税率
                Map<String,String> result = new HashMap<>();
                Elements trs = eTable.select("tr");
                for(Element tr:trs){
                    Elements tds = tr.select("td");
                    if(tds.size()<2){
                        continue;
                    }
                    if(StringUtils.isNotBlank(tds.get(1).text())){ // 税率列不为空，记录
                        result.put(tds.get(0).text(),tds.get(1).text());
                    }
                }
                return result;
            }else{
                continue;
            }
        }
        return null;
    }

    private static String doGet(String url){
        HttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet();
        try {
            // 初始化post请求头
            request.addHeader("Content-Type", "text/html; charset=utf-8");
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            // 解析结果
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) { // 请求成功
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "utf-8");
            }else {
                System.out.println("===http status:"+code);
            }
        }catch(Exception e){
        }
        return null;
    }


}
