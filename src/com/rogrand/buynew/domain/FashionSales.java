package com.rogrand.buynew.domain;
import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import java.util.Date;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-07-13 <br/>
 * 描述：服装促销类
 */
public class FashionSales extends Base {

    @FieldAnnotation(comment = "fashion_sales_id", exp = false, imp = false, empty = true, len = 10, pk = true)
    private Long fashion_sales_id;

    @FieldAnnotation(comment = "服装ID", exp = true, imp = true, empty = true, len = 10)
    private Long fashion_id;

    @FieldAnnotation(comment = "类型", exp = true, imp = true, empty = true, len = 5)
    private String type;

    @FieldAnnotation(comment = "置顶时间", exp = true, imp = true, empty = true)
    private Date stick_time;

    @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
    private Date create_time;

    @FieldAnnotation(comment = "创建人", exp = true, imp = true, empty = true, len = 20)
    private String create_user;

    @FieldAnnotation(comment = "更新时间", exp = true, imp = true, empty = true)
    private Date update_time;

    @FieldAnnotation(comment = "更新人", exp = true, imp = true, empty = true, len = 20)
    private String update_user;


    /**
     * 服装促销对象构造函数
     */
    public FashionSales() {
        super();
    }

    public FashionSales(String fashion_sales_id) {
        this("fashion_sales_id",fashion_sales_id);
    }

    public FashionSales(String property, Object value) {
        this();
        init(property, value);
    }

    /**
     * 获得fashion_sales_id
     * @return Long
     */
    public Long getFashion_sales_id(){
        return this.fashion_sales_id;
    }

    /**
     * 设置fashion_sales_id
     * @param fashion_sales_id  fashion_sales_id
     */
    public void setFashion_sales_id(Long fashion_sales_id){
        putField("fashion_sales_id");
        this.fashion_sales_id = fashion_sales_id;
    }
    /**
     * 获得服装ID
     * @return Long
     */
    public Long getFashion_id(){
        return this.fashion_id;
    }

    /**
     * 设置服装ID
     * @param fashion_id  服装ID
     */
    public void setFashion_id(Long fashion_id){
        putField("fashion_id");
        this.fashion_id = fashion_id;
    }
    /**
     * 获得类型
     * @return String
     */
    public String getType(){
        return this.type;
    }

    /**
     * 设置类型
     * @param type  类型
     */
    public void setType(String type){
        putField("type");
        this.type = type;
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
}