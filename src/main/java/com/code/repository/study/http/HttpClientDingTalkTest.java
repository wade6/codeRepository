package com.code.repository.study.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Author zhaoyuan.lizy on 2019/7/25
 **/

public class HttpClientDingTalkTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("bizType", "MSG_DIRECT_EXPORT_CALLBACK_BIZ_TYPE");
        params.put("formatType", "1");
        params.put("bizKey", "LP00096553139264");

        // content内容
        String msg = "{\n" +
                "    \"msgtype\": \"text\", \n" +
                "    \"text\": {\n" +
                "        \"content\": \"\"\n" +
                "    }, \n" +
                "    \"msgId\": \"123456\", \n" +
                "    \"createAt\": 1487561654123,\n" +
                "    \"conversationType\": \"2\", \n" +
                "    \"conversationId\": \"12345\", \n" +
                "    \"conversationTitle\": \"钉钉群\", \n" +
                "    \"senderId\": \"dingtalk1235\", \n" +
                "    \"senderNick\": \"星星\",\n" +
                "    \"chatbotUserId\":\"XXX\",\n" +
                "    \"atUsers\":[\n" +
                "       {\n" +
                "             \"dingtalkId\":\"testdingid234\"       \n" +
                "       },\n" +
                "       {\n" +
                "              \"dingtalkId\":\"testdingid444\"  \n" +
                "       }\n" +
                "    ]\n" +
                "}";

        JSONObject jsonMsg = JSONObject.parseObject(msg);

        String url = "https://pre-partner.customs.cainiao.com/robot/cbe";
//        String url = "https://partner.customs.cainiao.com/robot/cbe";
//        String url = "https://pre-partner.customs.cainiao.com/checkpreload.htm";


        String token = "sadfwer22ssfgCUSTOMS_13468211___sdf22dg";
        System.out.println("服务器的响应是:"+HttpClientDingTalkTest.doPost(url, token,jsonMsg));
    }

    /**
     * post请求
     */
    private static String doPost(String url, String token , JSONObject params) {
        // 定义HttpClient
        HttpClient client = HttpClients.createDefault();
        // 实例化HTTP方法
        HttpPost request = new HttpPost();
        try {
            // 初始化post请求头
            request.addHeader("Content-Type","application/json; charset=utf-8");
            request.addHeader("token",token);
            request.setURI(new URI(url));
            // 设置参数
            StringEntity stringEntity = new StringEntity(params.toString(), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            request.setEntity(stringEntity);
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
