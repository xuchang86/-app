package com.group.core.commons.uploads;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadItem implements java.io.Serializable {
    
    private static final long serialVersionUID = -6935613987367008409L;

    private String filename;
	
	private List<CommonsMultipartFile> fileData;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<CommonsMultipartFile> getFileData() {
		return fileData;
	}

	public void setFileData(List<CommonsMultipartFile> fileData) {
		this.fileData = fileData;
	}
}
