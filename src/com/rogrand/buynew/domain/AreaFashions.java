package com.rogrand.buynew.domain;
import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import java.util.Date;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-05 <br/>
 * 描述：服装区下的服装类
 */
public class AreaFashions extends Base {

    @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 10, pk = true)
    private Long id;

    @FieldAnnotation(comment = "服装区ID", exp = true, imp = true, empty = true, len = 10)
    private Long area_id;

    @FieldAnnotation(comment = "服装区类型", exp = true, imp = true, empty = true, len = 10)
    private String area_type;

    @FieldAnnotation(comment = "明文ID", exp = true, imp = true, empty = true, len = 50)
    private String num_iid;

    @FieldAnnotation(comment = "是否上架", exp = true, imp = true, empty = true, len = 1)
    private String is_sale;

    @FieldAnnotation(comment = "上架时间", exp = true, imp = true, empty = true)
    private Date sale_time;

    @FieldAnnotation(comment = "是否置顶", exp = true, imp = true, empty = true, len = 1)
    private String is_stick;

    @FieldAnnotation(comment = "置顶时间", exp = true, imp = true, empty = true)
    private Date stick_time;


    /**
     * 服装区下的服装对象构造函数
     */
    public AreaFashions() {
        super();
    }

    public AreaFashions(String id) {
        this("id",id);
    }

    public AreaFashions(String property, Object value) {
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
     * 获得服装区ID
     * @return Long
     */
    public Long getArea_id(){
        return this.area_id;
    }

    /**
     * 设置服装区ID
     * @param area_id  服装区ID
     */
    public void setArea_id(Long area_id){
        putField("area_id");
        this.area_id = area_id;
    }
    /**
     * 获得服装区类型
     * @return String
     */
    public String getArea_type(){
        return this.area_type;
    }

    /**
     * 设置服装区类型
     * @param area_type  服装区类型
     */
    public void setArea_type(String area_type){
        putField("area_type");
        this.area_type = area_type;
    }
    /**
     * 获得明文ID
     * @return String
     */
    public String getNum_iid(){
        return this.num_iid;
    }

    /**
     * 设置明文ID
     * @param num_iid  明文ID
     */
    public void setNum_iid(String num_iid){
        putField("num_iid");
        this.num_iid = num_iid;
    }
    /**
     * 获得是否上架
     * @return String
     */
    public String getIs_sale(){
        return this.is_sale;
    }

    /**
     * 设置是否上架
     * @param is_sale  是否上架
     */
    public void setIs_sale(String is_sale){
        putField("is_sale");
        this.is_sale = is_sale;
    }
    /**
     * 获得上架时间
     * @return Date
     */
    public Date getSale_time(){
        return this.sale_time;
    }

    /**
     * 设置上架时间
     * @param sale_time  上架时间
     */
    public void setSale_time(Date sale_time){
        putField("sale_time");
        this.sale_time = sale_time;
    }
    /**
     * 获得是否置顶
     * @return String
     */
    public String getIs_stick(){
        return this.is_stick;
    }

    /**
     * 设置是否置顶
     * @param is_stick  是否置顶
     */
    public void setIs_stick(String is_stick){
        putField("is_stick");
        this.is_stick = is_stick;
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
}