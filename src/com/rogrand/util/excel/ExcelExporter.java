package com.rogrand.util.excel;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

public class ExcelExporter {

	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public enum ExcelStyle {
		SheetTitle, Head, LineHead, Tab, WrapTab, Bold
	}

	/**
	 * 导出Excel
	 * @param response
	 * @param title Excel名称
	 * @param sheets 定义的sheet集合
	 * @throws IOException
	 */
	public static void export(HttpServletResponse response, String title,
			List<Sheet> sheets) throws IOException {
		ServletOutputStream servletOutputStream = response.getOutputStream();
		String filesave = URLEncoder.encode(title, "UTF-8") + ".xls";
		
		response.addHeader("Content-Disposition", "attachment;filename="+ filesave);
		response.setContentType("application/x-download;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HSSFWorkbook book = parseSheets(sheets);
		book.write(servletOutputStream);
		servletOutputStream.flush();
		servletOutputStream.close();
	}

	/**
	 * 导出Excel
	 * @param response
	 * @param title Excel名称
	 * @param sheet 定义的sheet对象
	 * @throws IOException
	 */
	public static void export(HttpServletResponse response, String title,
			Sheet sheet) throws IOException {
		ServletOutputStream servletOutputStream = response.getOutputStream();
		String filesave = URLEncoder.encode(title, "UTF-8") + ".xls";//导出文件名称+后缀
		
		response.addHeader("Content-Disposition", "attachment;filename="+ filesave);
		response.setContentType("application/x-download;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HSSFWorkbook book = parseSheet(sheet);
		book.write(servletOutputStream);
		servletOutputStream.flush();
		servletOutputStream.close();
	}
	
	/**
	 * 导出Excel,带表头融合
	 * @param response
	 * @param title
	 * @param sheet
	 * @throws IOException
	 */
	public static void exportMer(HttpServletResponse response, String title,
			Sheet sheet) throws IOException {
		String filesave = URLEncoder.encode(title, "UTF-8") + ".xls";//导出文件名称+后缀
		response.addHeader("Content-Disposition", "attachment;filename="+ filesave);
		response.setContentType("application/x-download;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		OutputStream os = response.getOutputStream();
		HSSFWorkbook swb = new HSSFWorkbook();
		createSheetMer(swb, sheet);
		swb.write(os);
		os.flush();
		os.close();
	}

	/**
	 * 创建HSSFWorkbook对象
	 * @param sheet 定义的sheet对象
	 * @return
	 */
	public static HSSFWorkbook parseSheet(Sheet sheet) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		createSheet(workbook, sheet);
		return workbook;
	}

	/**
	 * 创建HSSFWorkbook对象
	 * @param sheets 定义的sheet集合
	 * @return
	 */
	public static HSSFWorkbook parseSheets(List<Sheet> sheets) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		if (sheets != null && sheets.size() > 0) {
			for (Sheet sheet : sheets) {
				createSheet(workbook, sheet);
			}
		}
		return workbook;
	}

	/**
	 * 将sheet对象（自定义）数据放到HSSFWorbook对象中
	 * @param book
	 * @param sheet
	 */
	public static void createSheet(HSSFWorkbook book, Sheet sheet) {
		if (sheet == null) {
			return;
		}
		HSSFSheet hsheet = book.createSheet(sheet.getTitle());
		hsheet.setDefaultRowHeightInPoints(sheet.getDefaultRowHeight());
		hsheet.setDefaultColumnWidth(sheet.getDefaultColumnWidth());
		if (sheet.getColumnWidth() != null) {
			for (Map.Entry<Integer, Integer> entry : sheet.getColumnWidth()
					.entrySet()) {
				hsheet.setColumnWidth(entry.getKey(), entry.getValue() * 256);
			}
		}

		if (sheet.getRows() == null) {
			return;
		}
		int i = 0;
		HSSFRow hrow = null;
		for (Object[] row : sheet.getRows()) {
			hrow = hsheet.createRow(i++);
			if (row == null) {
				continue;
			}

			int c = 0;
			for (Object cell : row) {
				HSSFCell hcell = hrow.createCell(c++);
				hcell.setCellValue(getStringValue(cell));
				ExcelStyle style = sheet.getStyle().get(i);
				if (style != null) {
					hcell.setCellStyle(getStyle(style, book));
				}
			}
		}

	}
	
	/**
	 * 将sheet对象（自定义）数据放到HSSFWorbook对象中,带表头融合
	 * 目前这个方法只适用于第一行带表头融合的导出Excel，其他更过类型的表格融合敬请期待中
	 * @param book
	 * @param sheet
	 */
	public static void createSheetMer(HSSFWorkbook book, Sheet sheet) {
		if (sheet == null || sheet.getRows()==null || sheet.getRows().size()==0)
			return;
		
		//设置列宽
		HSSFSheet hsheet = book.createSheet(sheet.getTitle());
		hsheet.setDefaultRowHeightInPoints(sheet.getDefaultRowHeight());
		hsheet.setDefaultColumnWidth(sheet.getDefaultColumnWidth());
		if (sheet.getColumnWidth() != null) {
			for (Map.Entry<Integer, Integer> entry : sheet.getColumnWidth().entrySet()) {
				hsheet.setColumnWidth(entry.getKey(), entry.getValue() * 256);
			}
		}

		//合并单元格
		if(sheet.getMerCells()!=null && sheet.getMerCells().size()>0){
			for(Integer[] merCells:sheet.getMerCells()){
				hsheet.addMergedRegion(new Region(merCells[0],merCells[1].shortValue(),merCells[2],merCells[3].shortValue()));
			}
		}
		
		//创建行并填充数据
		HSSFRow hrow = null;
		HSSFCell hcell = null;
		ExcelStyle style = null;
		Object[] row = null;
		for (int i=0;i<sheet.getRows().size();i++) {
			row = sheet.getRows().get(i);
			sheet.getMerCells();
			hrow = hsheet.createRow(i);
			if(i==0){
				int c = 0;
				for(Integer[] merCells:sheet.getMerCells()){
					hcell = hrow.createCell(merCells[1]);
					hcell.setCellValue(getStringValue(row[c]));
					style = sheet.getStyle().get(i);
					hcell.setCellStyle(getStyle(style, book));
					c++;
				}
			}else{
				int c = 0;
				for (Object cell : row) {
					hcell = hrow.createCell(c++);
					hcell.setCellValue(getStringValue(cell));
					style = sheet.getStyle().get(i);
					if (style != null) {
						hcell.setCellStyle(getStyle(style, book));
					}
				}
			}
		}
	}
	
	public static String getStringValue(Object obj) {
		if (obj != null) {
			if (obj instanceof Date) {
				return format.format((Date) obj);
			}
			if(obj instanceof BigDecimal){
				DecimalFormat df = new DecimalFormat("#0.00");
				return df.format(obj);
			}
			return obj.toString();
		}
		return "";
	}

	public static HSSFCellStyle getStyle(ExcelStyle style, HSSFWorkbook book) {
		if (style == ExcelStyle.SheetTitle) {
			return getSheetTitleStyle(book);
		}
		if (style == ExcelStyle.Head) {
			return getHeadStyle(book);
		}
		if (style == ExcelStyle.LineHead) {
			return getLightHeadStyle(book);
		}
		if (style == ExcelStyle.Tab) {
			return getTabStyle(book);
		}
		if (style == ExcelStyle.Bold) {
			return getBoldStyle(book);
		}
		return getWrapTabStyle(book);
	}

	public static HSSFCellStyle getSheetTitleStyle(HSSFWorkbook book) {
		HSSFCellStyle style = book.createCellStyle();
		HSSFFont font = book.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 20);
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		return style;
	}

	public static HSSFCellStyle getHeadStyle(HSSFWorkbook book) {
		HSSFCellStyle style = book.createCellStyle();
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
		HSSFFont font = book.createFont();
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		return style;
	}

	public static HSSFCellStyle getBoldStyle(HSSFWorkbook book) {
		HSSFCellStyle style = book.createCellStyle();
		HSSFFont font = book.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		return style;
	}

	public static HSSFCellStyle getLightHeadStyle(HSSFWorkbook book) {
		HSSFCellStyle style = book.createCellStyle();
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = book.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		style.setBorderBottom((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderRight((short) 1);
		style.setBorderTop((short) 1);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);;
		return style;
	}

	public static HSSFCellStyle getTabStyle(HSSFWorkbook book) {
		HSSFCellStyle style = book.createCellStyle();
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		style.setBorderBottom((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderRight((short) 1);
		style.setBorderTop((short) 1);
		return style;
	}

	public static HSSFCellStyle getWrapTabStyle(HSSFWorkbook book) {
		HSSFCellStyle style = book.createCellStyle();
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		return style;
	}
}
