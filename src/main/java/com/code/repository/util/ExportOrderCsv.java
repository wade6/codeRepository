package com.code.repository.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExportOrderCsv {
	
	@Resource
	private HttpServletRequest request;
	
	@Resource
	private HttpServletResponse response;
	
	private static final String      CONTENT_TYPE = "application/vnd.ms-excel;charset=GBK";
	private static final String      HEAD_NAME    = "Content-Disposition";
    private static final String      HEAD_VALUE   = "attachment;";
	
	public void execute( ) {
        
        String data = "hello,world,\r\nok";
        setHeader();
        ServletOutputStream fos = null;
        try {
            fos = response.getOutputStream();
            fos.write(data.getBytes());
            fos.flush();
        } catch (IOException e) {
        } finally {
            IOUtils.closeQuietly(fos);
        }

    }
	
	
	/**
     * 设置导出excel文件http相应报头
     */
    private void setHeader() {
    	response.reset();
        response.setHeader("Pragma", "No-cache");
        response.setContentType(CONTENT_TYPE);
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String filename = "OrderExport" + "-" + f.format(new Date()) + ".csv";
        response.addHeader(HEAD_NAME, HEAD_VALUE + "filename=" + filename);
    }
    
    

}
