package com.group.core.commons.uploads;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-4-2 <br/>
 * 描述：〈描述〉
 */
public enum UploadType {

    TEMP(0, "temp", "临时文件"), 
    USER_PIC(1, "userpic", "用户头像"),
    IMAGE(2, "images", "图片"), 
    AUDIO(3, "audio", "音频");

    private int code;
    private String name;
    private String desc;

    private UploadType(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
