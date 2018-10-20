package com.code.repository.study.log;

import java.io.IOException;
import java.util.logging.*;

public class LogDemo {

    public static void main(String[] args) throws IOException {
        //日志记录器
        Logger logger = Logger.getLogger("logdemo");

        //====使用文件输出日志===
        //日志处理器
        FileHandler fileHandler = new FileHandler("d:\\test.txt");
        //为处理器设置日志格式：Formatter
        SimpleFormatter sf = new SimpleFormatter();
        fileHandler.setFormatter(sf);
        //注册处理器
        logger.addHandler(fileHandler);
        //====使用文件输出日志 end===

        //需要记录的日志消息
        LogRecord lr = new LogRecord(Level.INFO, "This is a text log.");
        //记录日志消息
        logger.log(lr);
    }


}
