package com.rogrand.buynew.domain;
import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import java.util.Date;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-01 <br/>
 * 描述：服装款式类
 */
public class Fashions extends Base {

    @FieldAnnotation(comment = "商品明文ID", exp = false, imp = false, empty = true, len = 50, pk = true)
    private String num_iid;

    @FieldAnnotation(comment = "款式名称", exp = true, imp = true, empty = true, len = 50)
    private String name;

    @FieldAnnotation(comment = "款式主图", exp = true, imp = true, empty = true, len = 200)
    private String main_image;

    @FieldAnnotation(comment = "款式详情链接", exp = true, imp = true, empty = true, len = 200)
    private String detail_url;

    @FieldAnnotation(comment = "店铺名称", exp = true, imp = true, empty = true, len = 50)
    private String shop_name;

    @FieldAnnotation(comment = "商品价格", exp = true, imp = true, empty = true, len = 10, scale = 2)
    private Double price;

    @FieldAnnotation(comment = "月销量", exp = true, imp = true, empty = true, len = 10)
    private Long month_count;

    @FieldAnnotation(comment = "收入比率(%)", exp = true, imp = true, empty = true, len = 10, scale = 2)
    private Double rate;

    @FieldAnnotation(comment = "佣金", exp = true, imp = true, empty = true, len = 10, scale = 2)
    private Double commision;

    @FieldAnnotation(comment = "卖家昵称", exp = true, imp = true, empty = true, len = 50)
    private String nick;

    @FieldAnnotation(comment = "淘宝客短链接", exp = true, imp = true, empty = true, len = 100)
    private String tbk_shot_url;

    @FieldAnnotation(comment = "淘宝客链接", exp = true, imp = true, empty = true, len = 200)
    private String tbk_url;

    @FieldAnnotation(comment = "淘口令", exp = true, imp = true, empty = true, len = 50)
    private String tkl;

    @FieldAnnotation(comment = "LAB价格", exp = true, imp = true, empty = true, len = 10, scale = 2)
    private Double lab_price;

    @FieldAnnotation(comment = "说明", exp = true, imp = true, empty = true, len = 50)
    private String cue;

    @FieldAnnotation(comment = "分类ID", exp = true, imp = true, empty = true, len = 10)
    private Long classify_id;

    @FieldAnnotation(comment = "LAB上架时间", exp = true, imp = true, empty = true)
    private Date sale_time;

    @FieldAnnotation(comment = "创建人", exp = true, imp = true, empty = true, len = 20)
    private String create_user;

    @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
    private Date create_time;

    @FieldAnnotation(comment = "更新人", exp = true, imp = true, empty = true, len = 20)
    private String update_user;

    @FieldAnnotation(comment = "更新时间", exp = true, imp = true, empty = true)
    private Date update_time;


    /**
     * 服装款式对象构造函数
     */
    public Fashions() {
        super();
    }

    public Fashions(String num_iid) {
        this("num_iid",num_iid);
    }

    public Fashions(String property, Object value) {
        this();
        init(property, value);
    }

    /**
     * 获得商品明文ID
     * @return String
     */
    public String getNum_iid(){
        return this.num_iid;
    }

    /**
     * 设置商品明文ID
     * @param num_iid  商品明文ID
     */
    public void setNum_iid(String num_iid){
        putField("num_iid");
        this.num_iid = num_iid;
    }
    /**
     * 获得款式名称
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * 设置款式名称
     * @param name  款式名称
     */
    public void setName(String name){
        putField("name");
        this.name = name;
    }
    /**
     * 获得款式主图
     * @return String
     */
    public String getMain_image(){
        return this.main_image;
    }

    /**
     * 设置款式主图
     * @param main_image  款式主图
     */
    public void setMain_image(String main_image){
        putField("main_image");
        this.main_image = main_image;
    }
    /**
     * 获得款式详情链接
     * @return String
     */
    public String getDetail_url(){
        return this.detail_url;
    }

    /**
     * 设置款式详情链接
     * @param detail_url  款式详情链接
     */
    public void setDetail_url(String detail_url){
        putField("detail_url");
        this.detail_url = detail_url;
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
     * 获得商品价格
     * @return Double
     */
    public Double getPrice(){
        return this.price;
    }

    /**
     * 设置商品价格
     * @param price  商品价格
     */
    public void setPrice(Double price){
        putField("price");
        this.price = price;
    }
    /**
     * 获得月销量
     * @return Long
     */
    public Long getMonth_count(){
        return this.month_count;
    }

    /**
     * 设置月销量
     * @param month_count  月销量
     */
    public void setMonth_count(Long month_count){
        putField("month_count");
        this.month_count = month_count;
    }
    /**
     * 获得收入比率(%)
     * @return Double
     */
    public Double getRate(){
        return this.rate;
    }

    /**
     * 设置收入比率(%)
     * @param rate  收入比率(%)
     */
    public void setRate(Double rate){
        putField("rate");
        this.rate = rate;
    }
    /**
     * 获得佣金
     * @return Double
     */
    public Double getCommision(){
        return this.commision;
    }

    /**
     * 设置佣金
     * @param commision  佣金
     */
    public void setCommision(Double commision){
        putField("commision");
        this.commision = commision;
    }
    /**
     * 获得卖家昵称
     * @return String
     */
    public String getNick(){
        return this.nick;
    }

    /**
     * 设置卖家昵称
     * @param nick  卖家昵称
     */
    public void setNick(String nick){
        putField("nick");
        this.nick = nick;
    }
    /**
     * 获得淘宝客短链接
     * @return String
     */
    public String getTbk_shot_url(){
        return this.tbk_shot_url;
    }

    /**
     * 设置淘宝客短链接
     * @param tbk_shot_url  淘宝客短链接
     */
    public void setTbk_shot_url(String tbk_shot_url){
        putField("tbk_shot_url");
        this.tbk_shot_url = tbk_shot_url;
    }
    /**
     * 获得淘宝客链接
     * @return String
     */
    public String getTbk_url(){
        return this.tbk_url;
    }

    /**
     * 设置淘宝客链接
     * @param tbk_url  淘宝客链接
     */
    public void setTbk_url(String tbk_url){
        putField("tbk_url");
        this.tbk_url = tbk_url;
    }
    /**
     * 获得淘口令
     * @return String
     */
    public String getTkl(){
        return this.tkl;
    }

    /**
     * 设置淘口令
     * @param tkl  淘口令
     */
    public void setTkl(String tkl){
        putField("tkl");
        this.tkl = tkl;
    }
    /**
     * 获得LAB价格
     * @return Double
     */
    public Double getLab_price(){
        return this.lab_price;
    }

    /**
     * 设置LAB价格
     * @param lab_price  LAB价格
     */
    public void setLab_price(Double lab_price){
        putField("lab_price");
        this.lab_price = lab_price;
    }
    /**
     * 获得说明
     * @return String
     */
    public String getCue(){
        return this.cue;
    }

    /**
     * 设置说明
     * @param cue  说明
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
     * 获得LAB上架时间
     * @return Date
     */
    public Date getSale_time(){
        return this.sale_time;
    }

    /**
     * 设置LAB上架时间
     * @param sale_time  LAB上架时间
     */
    public void setSale_time(Date sale_time){
        putField("sale_time");
        this.sale_time = sale_time;
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