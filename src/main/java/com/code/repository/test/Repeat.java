package com.code.repository.test;

import java.util.ArrayList;
import java.util.List;

public class Repeat {

    public static void main(String[] args) {
        String s = "1111";
        List<String> result = restoreIpAddresses(s);
        for(String ip : result){
            System.out.println(ip);
        }
    }

    public static List<String> restoreIpAddresses(String s){
        List<String> result = new ArrayList<>();
        // 255 255 255 255 IP地址分为4段，每段最多3位数，每段数字值的范围 0-2555
        // 分段处理，递归处理每一段，最后一段拼接结果
        addNextSegment(s,1,0,"",result);
        return result;
    }

    /**
     * 添加下一段
     * @param s
     * @param seg 段号
     * @param pos 已使用位置
     * @param preStr 前序段ip
     * @param result 最终结果
     */
    public static void addNextSegment(String s,int seg ,int pos,String preStr,List<String> result){
        if(seg>4 || pos>=s.length()){// 超过范围边界
            return;
        }
        String nowStr ="";
        if(seg == 4){// 如果是第4段，判断剩余字符串是否ok
            nowStr = s.substring(pos,s.length());
            if(nowStr.length()>3 || nowStr.length()>1 && nowStr.charAt(0)=='0'){// 不是1位且0开头不合法
                return;
            }
            Integer num = new Integer(nowStr);
            if(num >=0 && num<=255){ // 符合数字大小范围，加上前序字符，加到结果中
                result.add(preStr+nowStr);
            }
            return; // 到第4段结束
        }

        seg++;
        // 本段取1位，不会有0开头问题，不会有值范围问题
        if(pos+1<=s.length()){
            nowStr = s.substring(pos,pos+1);
            addNextSegment(s,seg,pos+1,preStr+nowStr+".",result);
        }
        // 本段取2位，需校验0开头问题
        if(pos+2<=s.length()){
            nowStr = s.substring(pos,pos+2);
            if(nowStr.length() >1 && nowStr.charAt(0)=='0'){// 0开头非法，直接范围，不需要进行取3位验证
                return;
            }
            addNextSegment(s,seg,pos+2,preStr+nowStr+".",result);
        }
        // 本段取3位，需校验值范围问题
        if(pos+3<=s.length()){
            nowStr = s.substring(pos,pos+3);
            Integer num = new Integer(nowStr);
            if(num>=0 && num<=255){// 值范围是否合法
                addNextSegment(s,seg,pos+3,preStr+nowStr+".",result);
            }
        }
    }
}
