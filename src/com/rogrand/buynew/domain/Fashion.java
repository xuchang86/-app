package com.rogrand.buynew.domain;
import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import java.util.Date;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-01 <br/>
 * 描述：服装类
 */
public class Fashion extends Base {

    @FieldAnnotation(comment = "fashion_id", exp = false, imp = false, empty = true, len = 10, pk = true)
    private Long fashion_id;

    @FieldAnnotation(comment = "明文ID", exp = true, imp = true, empty = true, len = 50)
    private String num_iid;

    @FieldAnnotation(comment = "混淆商品ID", exp = true, imp = true, empty = true, len = 50)
    private String open_iid;

    @FieldAnnotation(comment = "商品标题", exp = true, imp = true, empty = true, len = 100)
    private String title;

    @FieldAnnotation(comment = "卖家nick", exp = true, imp = true, empty = true, len = 100)
    private String nick;

    @FieldAnnotation(comment = "店铺名称", exp = true, imp = true, empty = true, len = 100)
    private String shop_name;

    @FieldAnnotation(comment = "LAB限抢价", exp = true, imp = true, empty = true, len = 10, scale = 2)
    private Double lab_price;

    @FieldAnnotation(comment = "提示", exp = true, imp = true, empty = true, len = 30)
    private String cue;

    @FieldAnnotation(comment = "分类ID", exp = true, imp = true, empty = true, len = 10)
    private Long classify_id;

    @FieldAnnotation(comment = "库存量", exp = true, imp = true, empty = true, len = 10)
    private Long qty;

    @FieldAnnotation(comment = "上架时间", exp = true, imp = true, empty = true)
    private Date sale_time;

    @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
    private Date create_time;

    @FieldAnnotation(comment = "创建人", exp = true, imp = true, empty = true, len = 20)
    private String create_user;

    @FieldAnnotation(comment = "更新时间", exp = true, imp = true, empty = true)
    private Date update_time;

    @FieldAnnotation(comment = "更新人", exp = true, imp = true, empty = true, len = 20)
    private String update_user;


    /**
     * 服装对象构造函数
     */
    public Fashion() {
        super();
    }

    public Fashion(String fashion_id) {
        this("fashion_id",fashion_id);
    }

    public Fashion(String property, Object value) {
        this();
        init(property, value);
    }

    /**
     * 获得fashion_id
     * @return Long
     */
    public Long getFashion_id(){
        return this.fashion_id;
    }

    /**
     * 设置fashion_id
     * @param fashion_id  fashion_id
     */
    public void setFashion_id(Long fashion_id){
        putField("fashion_id");
        this.fashion_id = fashion_id;
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
     * 获得混淆商品ID
     * @return String
     */
    public String getOpen_iid(){
        return this.open_iid;
    }

    /**
     * 设置混淆商品ID
     * @param open_iid  混淆商品ID
     */
    public void setOpen_iid(String open_iid){
        putField("open_iid");
        this.open_iid = open_iid;
    }
    /**
     * 获得商品标题
     * @return String
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * 设置商品标题
     * @param title  商品标题
     */
    public void setTitle(String title){
        putField("title");
        this.title = title;
    }
    /**
     * 获得卖家nick
     * @return String
     */
    public String getNick(){
        return this.nick;
    }

    /**
     * 设置卖家nick
     * @param nick  卖家nick
     */
    public void setNick(String nick){
        putField("nick");
        this.nick = nick;
    }
    /**
     * 获得店铺名称
     * @return String
     */
    public String getShop_name(){
        return this.shop_name;
    }

    /**
     * 设置店铺名称
     * @param shop_name  店铺名称
     */
    public void setShop_name(String shop_name){
        putField("shop_name");
        this.shop_name = shop_name;
    }
    /**
     * 获得LAB限抢价
     * @return Double
     */
    public Double getLab_price(){
        return this.lab_price;
    }

    /**
     * 设置LAB限抢价
     * @param lab_price  LAB限抢价
     */
    public void setLab_price(Double lab_price){
        putField("lab_price");
        this.lab_price = lab_price;
    }
    /**
     * 获得提示
     * @return String
     */
    public String getCue(){
        return this.cue;
    }

    /**
     * 设置提示
     * @param cue  提示
     */
    public void setCue(String cue){
        putField("cue");
        this.cue = cue;
    }
    /**
     * 获得分类ID
     * @return Long
     */
    public Long getClassify_id(){
        return this.classify_id;
    }

    /**
     * 设置分类ID
     * @param classify_id  分类ID
     */
    public void setClassify_id(Long classify_id){
        putField("classify_id");
        this.classify_id = classify_id;
    }
    /**
     * 获得库存量
     * @return Long
     */
    public Long getQty(){
        return this.qty;
    }

    /**
     * 设置库存量
     * @param qty  库存量
     */
    public void setQty(Long qty){
        putField("qty");
        this.qty = qty;
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