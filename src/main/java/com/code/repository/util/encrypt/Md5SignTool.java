package com.code.repository.util.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * MD5签名生成和校验类
 * 
 * @author zhaoyuan.lizy
 *
 */
public class Md5SignTool {
	
	private static final String key="20130903";

	/**
	 * 校验签名
	 * @param arg
	 * @param sign
	 * @return
	 */
	public static boolean validateSign(String arg,String sign){
		
		if(StringUtils.isBlank(sign)){
			return false;
		}
		
		return sign.equals(generateSgin(arg));
	}
	
	/**
	 * 生成签名
	 * @param arg
	 * @return
	 */
	public static String generateSgin(String arg){
		return DigestUtils.md5Hex(arg + key +arg).substring(1, 10);
	}
	
	public static void main(String[] args) {
		String str = "日常中文账号";
		System.out.println(generateSgin(str));
	}
	
	/**
	 * 签名
	 */
	public static String doSign(String content, String charset, String keys) {
        String sign = "";
        content = content + keys;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes(charset));
            sign = new String(Base64.encodeBase64(md.digest()), charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sign;
    }
	
}
