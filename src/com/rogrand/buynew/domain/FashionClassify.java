package com.rogrand.buynew.domain;
import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import java.util.Date;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-06 <br/>
 * 描述：服装分类类
 */
public class FashionClassify extends Base {

    @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 10, pk = true)
    private Long id;

    @FieldAnnotation(comment = "名称", exp = true, imp = true, empty = true, len = 20)
    private String name;

    @FieldAnnotation(comment = "大类名称", exp = true, imp = true, empty = true, len = 20)
    private String parent_name;

    @FieldAnnotation(comment = "排序值", exp = true, imp = true, empty = true, len = 10)
    private Long order_value;

    @FieldAnnotation(comment = "创建人", exp = true, imp = true, empty = true, len = 20)
    private String create_user;

    @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
    private Date create_time;

    @FieldAnnotation(comment = "更新人", exp = true, imp = true, empty = true, len = 20)
    private String update_user;

    @FieldAnnotation(comment = "更新时间", exp = true, imp = true, empty = true)
    private Date update_time;


    /**
     * 服装分类对象构造函数
     */
    public FashionClassify() {
        super();
    }

    public FashionClassify(String id) {
        this("id",id);
    }

    public FashionClassify(String property, Object value) {
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
     * 获得名称
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * 设置名称
     * @param name  名称
     */
    public void setName(String name){
        putField("name");
        this.name = name;
    }
    /**
     * 获得大类名称
     * @return String
     */
    public String getParent_name(){
        return this.parent_name;
    }

    /**
     * 设置大类名称
     * @param parent_name  大类名称
     */
    public void setParent_name(String parent_name){
        putField("parent_name");
        this.parent_name = parent_name;
    }
    /**
     * 获得排序值
     * @return Long
     */
    public Long getOrder_value(){
        return this.order_value;
    }

    /**
     * 设置排序值
     * @param order_value  排序值
     */
    public void setOrder_value(Long order_value){
        putField("order_value");
        this.order_value = order_value;
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
     * 获得更新人
     * @return String
     */
    public String getUpdate_user(){
        return this.update_user;
    }

    /**
     * 设置更新人
     * @param update_user  更新人
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
}