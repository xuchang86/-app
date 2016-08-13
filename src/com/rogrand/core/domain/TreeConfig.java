package com.rogrand.core.domain;

import com.rogrand.core.util.StringUtil;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：在自动生成页面，树形结构展现数据库中的table
 */
public class TreeConfig extends SerializeCloneable {

	private static final long serialVersionUID = 1454417934156069567L;
	
	private String rootID;          //根ID
    private String rootText;        //根Text
    private String state;           //打开关闭状态 值为 open closed

    private String fieldID;             //ID字段
    private String fieldText;           //text字段
    private String fieldParentID;       //父ID字段
    private String fieldChild;          //是否有子节点的字段 值为 1有子节点，0没有子节点
    private String[] fieldAttributes;      //附加属性字段，以,分割
    private String fieldChecked;        //选中状态字段 值为 1选中，0未选中

    public TreeConfig() {

    }

    public TreeConfig(String rootID, String rootText, String state,
                      String fieldID, String fieldText, String fieldParentID,
                      String fieldChild, String[] fieldAttributes, String fieldChecked) {
        this.setRootID(rootID);
        this.setRootText(rootText);
        this.setState(state);
        this.setFieldID(fieldID);
        this.setFieldText(fieldText);
        this.setFieldParentID(fieldParentID);
        this.setFieldChild(fieldChild);
        this.setFieldAttributes(fieldAttributes);
        this.setFieldChild(fieldChecked);
    }

    public TreeConfig(String rootID, String rootText, String state,
                      String fieldID, String fieldText, String fieldParentID,
                      String fieldChild, String fieldAttributes, String fieldChecked) {
        this.setRootID(rootID);
        this.setRootText(rootText);
        this.setState(state);
        this.setFieldID(fieldID);
        this.setFieldText(fieldText);
        this.setFieldParentID(fieldParentID);
        this.setFieldChild(fieldChild);
        this.setFieldAttributes(fieldAttributes);
        this.setFieldChild(fieldChecked);
    }

    public TreeConfig(String rootID, String rootText, String state,
                      String fieldID, String fieldText, String fieldParentID) {
        this.setRootID(rootID);
        this.setRootText(rootText);
        this.setState(state);
        this.setFieldID(fieldID);
        this.setFieldText(fieldText);
        this.setFieldParentID(fieldParentID);
    }

    public TreeConfig(String rootID, String rootText, String state,
                      String fieldID, String fieldText, String fieldParentID,
                      String fieldChild) {
        this.setRootID(rootID);
        this.setRootText(rootText);
        this.setState(state);
        this.setFieldID(fieldID);
        this.setFieldText(fieldText);
        this.setFieldParentID(fieldParentID);
        this.setFieldChild(fieldChild);
    }

    public TreeConfig(String rootID, String rootText, String state,
                      String fieldID, String fieldText, String fieldParentID,
                      String fieldChild, String fieldAttributes) {
        this.setRootID(rootID);
        this.setRootText(rootText);
        this.setState(state);
        this.setFieldID(fieldID);
        this.setFieldText(fieldText);
        this.setFieldParentID(fieldParentID);
        this.setFieldChild(fieldChild);
        this.setFieldAttributes(fieldAttributes);
    }

    public TreeConfig(String rootID, String rootText, String state,
                      String fieldID, String fieldText, String fieldParentID,
                      String fieldChild, String[] fieldAttributes) {
        this.setRootID(rootID);
        this.setRootText(rootText);
        this.setState(state);
        this.setFieldID(fieldID);
        this.setFieldText(fieldText);
        this.setFieldParentID(fieldParentID);
        this.setFieldChild(fieldChild);
        this.setFieldAttributes(fieldAttributes);
    }

    public String getFieldID() {
        return fieldID;
    }

    public String getFieldParentID() {
        return fieldParentID;
    }

    public String getFieldText() {
        return fieldText;
    }

    public String getFieldChecked() {
        return fieldChecked;
    }

    public String[] getFieldAttributes() {
        return fieldAttributes;
    }

    public String getState() {
        return state;
    }

    public String getRootID() {
        return rootID;
    }

    public String getRootText() {
        return rootText;
    }

    public String getFieldChild() {
        return fieldChild;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public void setFieldParentID(String fieldParentID) {
        this.fieldParentID = fieldParentID;
    }

    public void setFieldText(String fieldText) {
        this.fieldText = fieldText;
    }

    public void setFieldChecked(String fieldChecked) {
        this.fieldChecked = fieldChecked;
    }

    public void setFieldAttributes(String[] fieldAttributes) {
        this.fieldAttributes = fieldAttributes;
    }

    public void setFieldAttributes(String fieldAttributes) {
        if (!StringUtil.isEmpty(fieldAttributes))
            this.fieldAttributes = fieldAttributes.split(",");
    }

    public void setFieldChild(String fieldChild) {
        this.fieldChild = fieldChild;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRootID(String rootID) {
        this.rootID = rootID;
    }

    public void setRootText(String rootText) {
        this.rootText = rootText;
    }

    /*
    public TreeConfig putFieldParentID(String fieldParentID) {
        setFieldParentID(fieldParentID);
        return this;
    }

    public TreeConfig putFieldID(String fieldID) {
        setFieldID(fieldID);
        return this;
    }
    public TreeConfig putFieldText(String fieldText) {
        setFieldText(fieldText);
        return this;
    }
    public TreeConfig putFieldChecked(String fieldChecked) {
        setFieldChecked(fieldChecked);
        return this;
    }
    public TreeConfig putFieldAttributes(String[] fieldAttributes) {
        setFieldAttributes(fieldAttributes);
        return this;
    }
    public TreeConfig putFieldAttributes(String fieldAttributes) {
        setFieldAttributes(fieldAttributes);
        return this;
    }
    public TreeConfig putState(String state) {
        setState(state);
        return this;
    }
    public TreeConfig putRootID(String rootID) {
        setRootID(rootID);
        return this;
    }
    public TreeConfig putRootText(String rootText) {
        setRootText(rootText);
        return this;
    }
    public TreeConfig putFieldChild(String fieldChild) {
        setFieldChild(fieldChild);
        return this;
    }
    */
}
