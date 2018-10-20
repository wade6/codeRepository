package com.code.repository.util;

/**
 * @Author zhaoyuan.lizy on 2018/9/4
 **/

public class JsonStringUtil {

    /**
     * JSON字符串特殊字符处理，比如：“\A1;1300”
     * @param s
     * @return String
     */
    public static String string2Json(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            switch (c){
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        String ss = "嘎\b\b嘎";
        System.out.println(ss);
        System.out.println(JsonStringUtil.string2Json(ss));
    }
}
