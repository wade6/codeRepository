package com.code.repository.study.http;

import com.alibaba.fastjson.JSON;
import com.code.repository.util.JsonStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.assertj.core.util.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author zhaoyuan.lizy on 2019/9/5
 **/

public class HscodeGatherer implements Callable<List<Map<String,String>>> {

    private String hscode4 = null;

    HscodeGatherer(String hscode4){
        this.hscode4 = hscode4;
    }

    private static int corePoolSize = 20;  // 主流线程个数
    private static int maximumPoolSize = 50; // 线程最大个数
    private static long keepAliveTime = 1000L; // 大于主流线程个数的线程空闲的过期时间  wait for new tasks before terminating
    private static TimeUnit unit = TimeUnit.MILLISECONDS; // 时间单元
    private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(); // 工作队列，有三种类SynchronousQueue、LinkedBlockingQueue(在所有 corePoolSize 线程都忙时新任务在队列中等待,maximumPoolSiz失效)、ArrayBlockingQueue，分别对应同步队列、无界队列、有界队列。

    private static final ThreadPoolExecutor executorPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);


    public static void main(String[] args){

//        // 多线程
        ArrayList<Future<List<Map<String, String>>>> results = new ArrayList<Future<List<Map<String, String>>>>();
        try{
            int start1=81;
            for(;start1<=99;start1++) {
                int start2=9;
                for (; start2 <= 9; start2++) {
                    String hscode4 = HscodeGatherer.numToStr(start1)+HscodeGatherer.numToStr(start2);
                    System.out.println(hscode4);
                    results.add(executorPool.submit(new HscodeGatherer(hscode4)));
//                    System.out.println("====thread num:"+executorPool.getActiveCount());
                }
            }
            int count =1;
            for(Future<List<Map<String, String>>> future : results){
                List<Map<String, String>> result = future.get();
                if(result!=null && result.size()>0){
                    HscodeGatherer.writeToFile(result);// 写文件
                }
                System.out.println("=====finish num:"+count++);
            }
            System.out.println("=====finish all:"+count++);
            return;
        } catch(Exception e){
        }


        // 单个code
//        List<String> hscode8List = HscodeGatherer.fetchHscode8("0101");
//        List<Map<String, String>> result = Lists.newArrayList();
//        for(String hscode8 : hscode8List){
//            result.add(HscodeGatherer.parseHscode8Detail(hscode8));
//        }
//        HscodeGatherer.writeToFile(result);// 写文件
    }


    private static String numToStr(int num){
        if(num<=9){
            return "0"+num;
        }else{
            return String.valueOf(num);
        }
    }

    @Override
    public List<Map<String, String>> call() throws Exception {
        System.out.println("=== start:"+this.hscode4);
        // 获取8位hscode
        List<String> hscode8List = HscodeGatherer.fetchHscode8(this.hscode4);
        // 获取hsocde税率
        List<Map<String, String>> result = Lists.newArrayList();
        for(String hscode8 : hscode8List){
            result.add(HscodeGatherer.parseHscode8Detail(hscode8));
        }
        System.out.println("=== finish:"+this.hscode4+",hscode:"+JSON.toJSONString(hscode8List));
        // 返回结果
        return result;
    }


    /**
     * 根据4位hscode 获取8位hscode
     */
    private static List<String> fetchHscode8(String hscode4){
        String url = "https://www.customs.gov.vn/SitePages/Tariff-Search.aspx?portlet=Structure&language=en-US&tariff="+hscode4;
        String html = HscodeGatherer.doGet(url); // 获取内容
        if(StringUtils.isBlank(html)){
            return null ;
        }
        List<String> hscodeList = Lists.newArrayList();
        Document doc = Jsoup.parse(html);// 结构化解析
        Elements tables = doc.getElementsByClass("tariffList");
        for(Element eTable : tables){
            Elements trs = eTable.select("tr");
            if(trs.size()<5){
                continue;
            }
            for(Element tr : trs){
                if(tr.toString().contains("DetailsImportTax")){// 8位hscode
                    String hscode8 = tr.select("a").get(0).text();
                    hscodeList.add(hscode8);
                }
            }
        }
        return hscodeList;
    }


    /**
     * 获取8位hscode税率，forme
     */
    private static Map<String,String> parseHscode8Detail(String hscode8){
        String url = "https://www.customs.gov.vn/SitePages/Tariff-Search.aspx?portlet=DetailsImportTax&language=en-US&code="+hscode8;
        String html = HscodeGatherer.doGet(url);
        if(StringUtils.isBlank(html)){
            return null;
        }
        if(html.contains("Not found")){ // Not found
            System.out.println("===Not found:");
            return null;
        }
        Document doc = Jsoup.parse(html);// 结构化解析
        Elements tables = doc.getElementsByClass("tariffList");
        Map<String,String> result = new HashMap<>();
        for(Element eTable : tables){
            if (eTable.text().contains("Description")) { // 描述
                Elements trs = eTable.select("tr");
                if(trs.size()>=6){
                    Elements tds = trs.get(5).select("td");
                    result.put("desc",tds.get(1).text().replaceAll("-","#"));
                }
            }
            if (eTable.text().contains("Tax rate")) {// 锁定税率列表，提取各种税率
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
                result.put("hscode",hscode8); // 填充hscode
//                System.out.println("=======hscode8 detail:"+JSON.toJSONString(result));
            }
        }
        return result;
    }

    // 写文件
    private static void writeToFile(Map<String,String> result){
        String fileName = "D:\\hscodeTH.txt";
        BufferedWriter out = null;
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(new File(fileName),true));
            out = new BufferedWriter(ow);
            out.newLine();
            if(StringUtils.isBlank(result.get("ASEAN - China  (ACFTA)"))){
                out.write(result.get("hscode")+";"+result.get("Favour")+";Na"+";"+result.get("desc"));
            }else{
                out.write(result.get("hscode")+";"+result.get("Favour")+";"+result.get("ASEAN - China  (ACFTA)")+";"+result.get("desc"));
            }
            out.flush();
            out.close();
        } catch (Exception e) {
        }finally{
        }
    }

    // 写文件
    private static void writeToFile(List<Map<String,String>> resultList){
        String fileName = "D:\\hscodeTH.txt";
        BufferedWriter out = null;
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(new File(fileName),true),"GBK");
            out = new BufferedWriter(ow);
            for(Map<String,String> result : resultList){
                out.newLine();
                if(StringUtils.isBlank(result.get("ASEAN - China  (ACFTA)"))){
                    out.write(result.get("hscode")+";"+result.get("Favour")+";Na"+";"+result.get("desc"));
                }else{
                    out.write(result.get("hscode")+";"+result.get("Favour")+";"+result.get("ASEAN - China  (ACFTA)")+";"+result.get("desc"));
                }
            }
            out.flush();
            out.close();
        } catch (Exception e) {
        }finally{
        }
    }

    // 发起get请求
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
