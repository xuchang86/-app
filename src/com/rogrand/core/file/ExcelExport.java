package com.rogrand.core.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import com.rogrand.core.util.StringUtil;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-12-3 <br/>
 * 描述：〈Excel文件导出工具〉
 */
public class ExcelExport {
    
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DecimalFormat DEFAULT_NUMBER_FORMAT = new DecimalFormat("#.##");
    
    private List<Field> cachedExportFields = new ArrayList<Field>();
    
    private List<Field> cachedHeadExportFields = new ArrayList<Field>(); 
    
    private int rowindex = 0;
    
    private Integer[] limitFileds;
    
    public <T> void exportExcel(List<T> data,Integer[] limitFileds, String fileName, HttpServletResponse response, HttpServletRequest request) throws Exception {
    	this.limitFileds=limitFileds;
    	response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + StringUtil.encodeDownloadFileName(fileName, request) + ".xls\"");
        HSSFWorkbook workbook = createWorkbook(data);
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close();
    }
    
    public <T> void exportExcel(List<T> data, String fileName, HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + StringUtil.encodeDownloadFileName(fileName, request) + ".xls\"");
        HSSFWorkbook workbook = createWorkbook(data);
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close();
    }
    
    public <T> void exportExcel(List<T> data, String fileName, File exportFile) throws Exception {
        fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        HSSFWorkbook workbook = createWorkbook(data);
        
        FileOutputStream out = new FileOutputStream(exportFile);
        workbook.write(out);
        out.flush();
        out.close();
    }
    
    private <T> HSSFWorkbook createWorkbook(List<T> data) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        
        if (data != null && data.size() > 0) {
            createHeader(workbook, sheet, data.get(0));
            createBody(workbook, sheet, data);
        }
        return workbook;
    }
    
    /**
     * 〈获取实体中导出数据方法〉 <br/>
     * 
     * @param <T>
     * @param dataRow
     */
    @SuppressWarnings("rawtypes")
    private <T> void cacheExportFields(T dataRow) {
        Class clazz = dataRow.getClass();
        Set<Integer> filedIndexRange=new HashSet<Integer>();
        if(this.limitFileds!=null){
            filedIndexRange.addAll(Arrays.asList(this.limitFileds));
        }
        
        while (true) {
            if (clazz == Base.class)
                break;
            
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(FieldAnnotation.class)) {
                    FieldAnnotation annotation = field.getAnnotation(FieldAnnotation.class);
                    if (annotation.exp()&&!annotation.hasNextHead()){
                        if(this.limitFileds==null||this.limitFileds.length==0){
                        	cachedExportFields.add(field);
                        }else if(filedIndexRange.contains(annotation.expIndex())){
                        	cachedExportFields.add(field);
                        }
                    } else if(annotation.exp() && annotation.hasNextHead()) {
                    		cachedHeadExportFields.add(field);
                    }
                }
            }
            
            clazz = clazz.getSuperclass();
        }
        
        Collections.sort(cachedHeadExportFields, new Comparator<Field>() {
            
            @Override
            public int compare(Field o1, Field o2) {
                FieldAnnotation f1 = o1.getAnnotation(FieldAnnotation.class);
                FieldAnnotation f2 = o2.getAnnotation(FieldAnnotation.class);
                return f1.expIndex() - f2.expIndex();
            }
        });

        Collections.sort(cachedExportFields, new Comparator<Field>() {
            
            @Override
            public int compare(Field o1, Field o2) {
                FieldAnnotation f1 = o1.getAnnotation(FieldAnnotation.class);
                FieldAnnotation f2 = o2.getAnnotation(FieldAnnotation.class);
                return f1.expIndex() - f2.expIndex();
            }
        });

    }
    
    private <T> void createHeader(HSSFWorkbook workbook, Sheet sheet, T dataRow) {
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        // 把字体应用到当前的样式
        style.setFont(font);
        
        //得到行信息
        cacheExportFields(dataRow);
        {
        	if(cachedHeadExportFields.size() > 0) {
        		Row header = sheet.createRow(rowindex++);
        		int start, end;
        		start = end = 0;
        		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 0);
        		for(Field field : cachedHeadExportFields) {
        			FieldAnnotation export = field.getAnnotation(FieldAnnotation.class);
        			Cell headerCell = header.createCell(start);
        			headerCell.setCellStyle(style);
                    headerCell.setCellValue(export.comment());
                    end = start + export.colspan() - 1;
                    cellRangeAddress.setFirstColumn(start);
                    cellRangeAddress.setLastColumn(end);
                    sheet.addMergedRegion(cellRangeAddress);
                    start = end + 1;
        		}
        	}
        }
        {// 数据处理逻辑
            Row header = sheet.createRow(rowindex++);
            int cellindex = 0;
            for (Field field : cachedExportFields) {
                FieldAnnotation export = field.getAnnotation(FieldAnnotation.class);
                Cell headerCell = header.createCell(cellindex);
                headerCell.setCellStyle(style);
                headerCell.setCellValue(export.comment());
                cellindex++;
            }
        }
    }
    
    private <T> void createBody(HSSFWorkbook workbook, Sheet sheet, List<T> data) throws Exception {
        // 生成并设置一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        
        // 把字体应用到当前的样式
        style.setFont(font);
        
        {// 数据处理逻辑
            if (!data.isEmpty()) {
                for (T datarow : data) {
                    Row sheetrow = sheet.createRow(rowindex);
                    
                    int cellindex = 0;
                    for (Field field : cachedExportFields) {
                        Cell sheetcell = sheetrow.createCell(cellindex);
                        sheetcell.setCellStyle(style);
                        
                        field.setAccessible(true);
                        Object cellvalue = PropertyUtils.getProperty(datarow, field.getName());
                        if (cellvalue != null) {
                            if (cellvalue instanceof Date) {
                                FieldAnnotation annotation = field.getAnnotation(FieldAnnotation.class);
                                String format = annotation.expFormat();
                                if (!"".equals(format)) {
                                    sheetcell.setCellValue(new SimpleDateFormat(format).format(cellvalue));
                                } else {
                                    sheetcell.setCellValue(DEFAULT_DATE_FORMAT.format(cellvalue));
                                }
                            } else if (cellvalue instanceof Float || cellvalue instanceof Double || cellvalue instanceof Number) {
                                FieldAnnotation annotation = field.getAnnotation(FieldAnnotation.class);
                                String format = annotation.expFormat();
                                if (!"".equals(format)) {
                                    sheetcell.setCellValue(new DecimalFormat(format).format(cellvalue));
                                } else {
                                    sheetcell.setCellValue(DEFAULT_NUMBER_FORMAT.format(cellvalue));
                                }
                            } else if (cellvalue instanceof Boolean) {
                                sheetcell.setCellValue((Boolean) cellvalue);
                            } else {
                                RichTextString text = new HSSFRichTextString(cellvalue.toString());
                                sheetcell.setCellValue(text);
                            }
                        }
                        cellindex++;
                    }
                    rowindex++;
                }
            }
        }
    }
}
