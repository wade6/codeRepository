package com.code.repository.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.DateUtil;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExcelUtil {
    private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 写数组数据到一个excel行
     *
     * @param sheet
     * @param values
     * @param style
     */
    private static void addHeader(Sheet sheet, Collection<String> values, CellStyle style, int rowNum) {
        Row row = sheet.createRow(rowNum);
        Iterator<String> it = values.iterator();
        int index = 0;
        while (it.hasNext()) {
            String name = it.next();
            sheet.setColumnWidth(index, 550 * name.length());
            Cell cell = row.createCell(index, Cell.CELL_TYPE_STRING);
            cell.setCellValue(name);
            if (style != null) {
                cell.setCellStyle(style);
            }
            index++;
        }
    }

    /**
     * 写数组数据到一个excel行
     *
     * @param sheet
     * @param values
     * @param style
     */
    public static void addHeaders(Sheet sheet, Collection<String> values, CellStyle style, int rowNum) {
        Row row = sheet.createRow(rowNum);
        Iterator<String> it = values.iterator();
        int index = 0;
        while (it.hasNext()) {
            String name = it.next();
            sheet.setColumnWidth(index, 550 * name.length());
            Cell cell = row.createCell(index, Cell.CELL_TYPE_STRING);
            cell.setCellValue(name);
            if (style != null) {
                cell.setCellStyle(style);
            }
            index++;
        }
    }

    /**
     * 写一组对象到excel sheet
     *
     * @param sheet
     * @param dataObj
     * @param getproperties 按属性的get方法获取对象内容
     * @param style
     * @throws Exception
     */
    private static <T> void writeRow(Sheet sheet, List<T> dataList, Collection<String> pros, CellStyle style,
                                     int rowNumStart) throws Exception {
        if (dataList != null && !dataList.isEmpty()) {

            Class<?> cls = dataList.get(0).getClass();
            for (T t : dataList) {
                Row row = sheet.createRow(rowNumStart);
                int index = 0;
                for (String pro : pros) {
                    PropertyDescriptor pd = new PropertyDescriptor(pro, cls);
                    Method method = pd.getReadMethod();
                    Object val = method.invoke(t);
                    Cell cell = row.createCell(index, Cell.CELL_TYPE_STRING);
                    setCellValue(cell, val);
                    if (style != null) {
                        cell.setCellStyle(style);
                    }
                    index++;
                }
                rowNumStart++;
            }
        }
    }

    /**
     * 写一组对象到excel sheet
     *
     * @param sheet
     * @param dataObj
     * @param getproperties 按属性的get方法获取对象内容
     * @param style
     * @throws Exception
     */
    public static <T> void writeRows(Sheet sheet, List<T> dataList, Collection<String> pros, CellStyle style,
                                     int rowNumStart) throws Exception {
        if (dataList != null && !dataList.isEmpty()) {

            Class<?> cls = dataList.get(0).getClass();
            for (T t : dataList) {
                Row row = sheet.createRow(rowNumStart);
                int index = 0;
                for (String pro : pros) {
                    PropertyDescriptor pd = new PropertyDescriptor(pro, cls);
                    Method method = pd.getReadMethod();
                    Object val = method.invoke(t);
                    Cell cell = row.createCell(index, Cell.CELL_TYPE_STRING);
                    setCellValue(cell, val);
                    if (style != null) {
                        cell.setCellStyle(style);
                    }
                    index++;
                }
                rowNumStart++;
            }
        }
    }

    /**
     * @param os            输出流,方法内不会关闭流，请外面自行关闭
     * @param dataList      输出数据列表 ，数据对象要有public的get方法
     * @param defineMapping 数据定 {{"XX商品名","getProductName","100"},{"XX商品ID","getProductID"
     *                      ,"100"},{"表头名称","get对象属性","列宽"}...}
     * @return
     */
    public static <T> void defaultExport(OutputStream os, List<T> dataList, Map<String, String> mapping)
            throws Exception {

        // 创建book
        Workbook wb = new HSSFWorkbook();

        // 表头单元格边STYLE
        CellStyle headStyle = getDefautHeaderStyle(wb);
        // 数据 单元格STYLE
        CellStyle dataStyle = getDefautDataStyle(wb);
        // 创建 包裹信息sheet
        Sheet sheet = wb.createSheet("default");

        Collection<String> headers = mapping.values();
        Set<String> pros = mapping.keySet();
        addHeader(sheet, headers, headStyle, 0);
        writeRow(sheet, dataList, pros, dataStyle, 1);

        // 写入到输出流
        wb.write(os);

    }

    /**
     * 给格子设置值
     *
     * @param cell
     * @param value
     */
    public static void setCellValue(Cell cell, Object value) {
        if (value == null) {
            cell.setCellValue("");
        } else if (value instanceof Float) {
            // 转成string，不然Excle显示的不一样
            cell.setCellValue(value.toString());
        } else if (value instanceof Double) {
            // 转成string，不然Excle显示的会不一样
            cell.setCellValue(value.toString());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue(new SimpleDateFormat(DATE_FORMAT).format(value));
        } else {
            cell.setCellValue(value.toString());
        }
    }

    /**
     * 创建头部的默认样式
     *
     * @param wb
     * @return
     */
    public static CellStyle getDefautHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        // 线条
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        // 背景
        // style.setFillBackgroundColor(new HSSFColor.BLUE_GREY().getIndex());
        return style;
    }

    /**
     * 设置默认数据部分格式
     *
     * @param wb
     * @return
     */
    public static CellStyle getDefautDataStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        // 默认不设置
        return style;
    }

    /**
     * 取一个excel单元格的值
     *
     * @param cell
     * @return
     * @author xiasq
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    cellValue = StringUtils.trim(cell.getRichStringCellValue().getString());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = cell.getBooleanCellValue() ? "TRUE" : "FALSE";
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = new SimpleDateFormat(DATE_FORMAT).format(cell.getDateCellValue());
                    } else {
                        cellValue = NumberFormat.getNumberInstance().format(cell.getNumericCellValue()).replaceAll(",",
                                "");
                    }
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = StringUtils.trim(cell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = StringUtils.trim(String.valueOf(cell.getErrorCellValue()));
                    break;
                default:
                    cellValue = "";
            }
        }
        return cellValue;
    }

    /**
     * 取Excel所有数据，包含header
     *
     * @return List<String   [   ]>
     * @throws IOException
     * @throws InvalidFormatException
     */
    @SuppressWarnings("unchecked")
    public static List<String[]> readAllData(InputStream is, int sheetIndex) throws Exception {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(is);
            List<String[]> dataList = new ArrayList<String[]>();
            int columnNum = 0;
            int startColNum = 0;
            int endColNum = 0;
            Sheet sheet = wb.getSheetAt(sheetIndex);
            if (sheet.getRow(0) != null) {
                startColNum = sheet.getRow(0).getFirstCellNum();
                endColNum = sheet.getRow(0).getLastCellNum();
                columnNum = endColNum - startColNum;
            }
            if (columnNum > 0) {
                for (Row row : sheet) {
                    if (row == null) {
                        continue;
                    }
                    String[] singleRow = new String[columnNum + 1];
                    singleRow[0] = (row.getRowNum() + 1) + "";
                    for (int i = 0; i < columnNum; i++) {
                        Cell cell = row.getCell(i + startColNum);
                        if (cell != null) {
                            singleRow[i + 1] = getCellValue(cell);
                        } else {
                            singleRow[i + 1] = "";
                        }
                    }
                    dataList.add(singleRow);
                }
            }
            return dataList;
        } catch (IOException e) {
            logger.error("io exception", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("io close error", e);
            }
        }
        return Collections.EMPTY_LIST;
    }
}
