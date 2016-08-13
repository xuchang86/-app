package com.rogrand.util.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rogrand.util.excel.ExcelExporter.ExcelStyle;


/**
 * Excel Sheet 
 */
public class Sheet {
	
	private String title;
	
	private float defaultRowHeight=15;
	
	private int defaultColumnWidth=20;
	
	/**
	 * 单元格列宽
	 */
	private Map<Integer,Integer> columnWidth;
	
	/**
	 * 导出数据
	 */
	private List<Object[]> rows;
	
	/**
	 * 行样式配置 
	 */
	private Map<Integer,ExcelStyle> style;
	
	/**
	 * 合并的单元格
	 */
	private List<Integer[]> merCells;
	
	public Sheet(String title){
		this.title=title;
		rows=new ArrayList<Object[]>();
		style=new HashMap<Integer, ExcelStyle>();
		columnWidth=new HashMap<Integer, Integer>();
	}
	
	public void addRow(Object... cells){
		this.rows.add(cells);
	}
	
	public Map<Integer, Integer> getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(Map<Integer, Integer> columnWidth) {
		this.columnWidth = columnWidth;
	}

	public Map<Integer, ExcelStyle> getStyle() {
		return style;
	}


	public void setStyle(Map<Integer, ExcelStyle> style) {
		this.style = style;
	}

	public List<Object[]> getRows() {
		return rows;
	}

	public void setRows(List<Object[]> rows) {
		this.rows = rows;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getDefaultRowHeight() {
		return defaultRowHeight;
	}

	public void setDefaultRowHeight(float defaultRowHeight) {
		this.defaultRowHeight = defaultRowHeight;
	}

	public int getDefaultColumnWidth() {
		return defaultColumnWidth;
	}

	public void setDefaultColumnWidth(int defaultColumnWidth) {
		this.defaultColumnWidth = defaultColumnWidth;
	}

	public List<Integer[]> getMerCells() {
		return merCells;
	}

	public void setMerCells(List<Integer[]> merCells) {
		this.merCells = merCells;
	}
	
}
