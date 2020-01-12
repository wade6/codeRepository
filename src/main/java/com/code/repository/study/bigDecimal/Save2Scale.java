package com.code.repository.study.bigDecimal;

import java.math.BigDecimal;

/**
 * 保留两位小数，
 * 使用BigDecimal实现
 */
public class Save2Scale {
    public static void main(String[] args) {
        Double dd = new Double(12.3456); // double 带小数数字
        BigDecimal bd = new BigDecimal(dd); // 转换成BigDecimal
        BigDecimal bd2 = bd.setScale(2,BigDecimal.ROUND_HALF_UP);// 设置保留2位小数，同时实现四舍五入（还有其他舍入方式）
        String result1 = String.valueOf(bd2.doubleValue()); // 输出最终结果
        String result2 = bd2.toPlainString(); // 输出最终结果
        System.out.println(result1);
        System.out.println(result2);
    }
}
