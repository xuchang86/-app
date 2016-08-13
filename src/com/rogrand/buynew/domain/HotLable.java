package com.rogrand.buynew.domain;
import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-06 <br/>
 * 描述：热门标签类
 */
public class HotLable extends Base {

    @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 10, pk = true)
    private Long id;

    @FieldAnnotation(comment = "类目ID", exp = true, imp = true, empty = true, len = 10)
    private Long classify_id;
    
    @FieldAnnotation(comment = "类目", exp = true, imp = true, empty = true, len = 30)
    private String classify_name;
    
    @FieldAnnotation(comment = "大类", exp = true, imp = true, empty = true, len = 30)
    private String classify_parent;

    @FieldAnnotation(comment = "标签名称", exp = true, imp = true, empty = true, len = 30)
    private String name;

    @FieldAnnotation(comment = "置顶时间", exp = true, imp = true, empty = true)
    private Date stick_time;

    @FieldAnnotation(comment = "创建人", exp = true, imp = true, empty = true, len = 20)
    private String create_user;

    @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
    private Date create_time;

    @FieldAnnotation(comment = "", exp = true, imp = true, empty = true, len = 20)
    private String update_user;

    @FieldAnnotation(comment = "更新时间", exp = true, imp = true, empty = true)
    private Date update_time;


    /**
     * 热门标签对象构造函数
     */
    public HotLable() {
        super();
    }

    public HotLable(String id) {
        this("id",id);
    }

    public HotLable(String property, Object value) {
        this();
        init(property, value);
    }

    /**
     * 获得id
     * @return Long
     */
    public Long getId(){
        return this.id;
    }

    /**
     * 设置id
     * @param id  id
     */
    public void setId(Long id){
        putField("id");
        this.id = id;
    }
    /**
     * 获得类目ID
     * @return Long
     */
    public Long getClassify_id(){
        return this.classify_id;
    }

    /**
     * 设置类目ID
     * @param classify_id  类目ID
     */
    public void setClassify_id(Long classify_id){
        putField("classify_id");
        this.classify_id = classify_id;
    }
    /**
     * 获得标签名称
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * 设置标签名称
     * @param name  标签名称
     */
    public void setName(String name){
        putField("name");
        this.name = name;
    }
    /**
     * 获得置顶时间
     * @return Date
     */
    public Date getStick_time(){
        return this.stick_time;
    }

    /**
     * 设置置顶时间
     * @param stick_time  置顶时间
     */
    public void setStick_time(Date stick_time){
        putField("stick_time");
        this.stick_time = stick_time;
    }
    /**
     * 获得创建人
     * @return String
     */
    public String getCreate_user(){
        return this.create_user;
    }

    /**
     * 设置创建人
     * @param create_user  创建人
     */
    public void setCreate_user(String create_user){
        putField("create_user");
        this.create_user = create_user;
    }
    /**
     * 获得创建时间
     * @return Date
     */
    public Date getCreate_time(){
        return this.create_time;
    }

    /**
     * 设置创建时间
     * @param create_time  创建时间
     */
    public void setCreate_time(Date create_time){
        putField("create_time");
        this.create_time = create_time;
    }
    /**
     * 获得
     * @return String
     */
    public String getUpdate_user(){
        return this.update_user;
    }

    /**
     * 设置
     * @param update_user  
     */
    public void setUpdate_user(String update_user){
        putField("update_user");
        this.update_user = update_user;
    }
    /**
     * 获得更新时间
     * @return Date
     */
    public Date getUpdate_time(){
        return this.update_time;
    }

    /**
     * 设置更新时间
     * @param update_time  更新时间
     */
    public void setUpdate_time(Date update_time){
        putField("update_time");
        this.update_time = update_time;
    }

	public String getClassify_name() {
		return classify_name;
	}

	public void setClassify_name(String classify_name) {
		this.classify_name = classify_name;
	}

	public String getClassify_parent() {
		return classify_parent;
	}

	public void setClassify_parent(String classify_parent) {
		this.classify_parent = classify_parent;
	}
}