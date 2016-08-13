package com.rogrand.buynew.domain;
import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import java.util.Date;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-02 <br/>
 * 描述：品牌类
 */
public class Brand extends Base {

    @FieldAnnotation(comment = "brand_id", exp = false, imp = false, empty = true, len = 10, pk = true)
    private Long brand_id;

    @FieldAnnotation(comment = "品牌名称", exp = true, imp = true, empty = true, len = 50)
    private String name;

    @FieldAnnotation(comment = "英文名称", exp = true, imp = true, empty = true, len = 50)
    private String en_name;

    @FieldAnnotation(comment = "品牌logo", exp = true, imp = true, empty = true, len = 200)
    private String logo;

    @FieldAnnotation(comment = "国家", exp = true, imp = true, empty = true, len = 20)
    private String country;

    @FieldAnnotation(comment = "国旗", exp = true, imp = true, empty = true, len = 20)
    private String flag;

    @FieldAnnotation(comment = "简介", exp = true, imp = true, empty = true, len = 200)
    private String synopsis;

    @FieldAnnotation(comment = "是否主推", exp = true, imp = true, empty = true, len = 1)
    private String is_main;

    @FieldAnnotation(comment = "是否筛选热门", exp = true, imp = true, empty = true, len = 1)
    private String is_hot;

    @FieldAnnotation(comment = "品牌馆", exp = true, imp = true, empty = true, len = 10)
    private Long venue_id;

    @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
    private Date create_time;

    @FieldAnnotation(comment = "创建人", exp = true, imp = true, empty = true, len = 20)
    private String create_user;

    @FieldAnnotation(comment = "置顶时间", exp = true, imp = true, empty = true)
    private Date stick_time;

    @FieldAnnotation(comment = "更新时间", exp = true, imp = true, empty = true)
    private Date update_time;

    @FieldAnnotation(comment = "更新人", exp = true, imp = true, empty = true, len = 20)
    private String update_user;


    /**
     * 品牌对象构造函数
     */
    public Brand() {
        super();
    }

    public Brand(String brand_id) {
        this("brand_id",brand_id);
    }

    public Brand(String property, Object value) {
        this();
        init(property, value);
    }

    /**
     * 获得brand_id
     * @return Long
     */
    public Long getBrand_id(){
        return this.brand_id;
    }

    /**
     * 设置brand_id
     * @param brand_id  brand_id
     */
    public void setBrand_id(Long brand_id){
        putField("brand_id");
        this.brand_id = brand_id;
    }
    /**
     * 获得品牌名称
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * 设置品牌名称
     * @param name  品牌名称
     */
    public void setName(String name){
        putField("name");
        this.name = name;
    }
    /**
     * 获得英文名称
     * @return String
     */
    public String getEn_name(){
        return this.en_name;
    }

    /**
     * 设置英文名称
     * @param en_name  英文名称
     */
    public void setEn_name(String en_name){
        putField("en_name");
        this.en_name = en_name;
    }
    /**
     * 获得品牌logo
     * @return String
     */
    public String getLogo(){
        return this.logo;
    }

    /**
     * 设置品牌logo
     * @param logo  品牌logo
     */
    public void setLogo(String logo){
        putField("logo");
        this.logo = logo;
    }
    /**
     * 获得国家
     * @return String
     */
    public String getCountry(){
        return this.country;
    }

    /**
     * 设置国家
     * @param country  国家
     */
    public void setCountry(String country){
        putField("country");
        this.country = country;
    }
    /**
     * 获得国旗
     * @return String
     */
    public String getFlag(){
        return this.flag;
    }

    /**
     * 设置国旗
     * @param flag  国旗
     */
    public void setFlag(String flag){
        putField("flag");
        this.flag = flag;
    }
    /**
     * 获得简介
     * @return String
     */
    public String getSynopsis(){
        return this.synopsis;
    }

    /**
     * 设置简介
     * @param synopsis  简介
     */
    public void setSynopsis(String synopsis){
        putField("synopsis");
        this.synopsis = synopsis;
    }
    /**
     * 获得是否主推
     * @return String
     */
    public String getIs_main(){
        return this.is_main;
    }

    /**
     * 设置是否主推
     * @param is_main  是否主推
     */
    public void setIs_main(String is_main){
        putField("is_main");
        this.is_main = is_main;
    }
    /**
     * 获得是否筛选热门
     * @return String
     */
    public String getIs_hot(){
        return this.is_hot;
    }

    /**
     * 设置是否筛选热门
     * @param is_hot  是否筛选热门
     */
    public void setIs_hot(String is_hot){
        putField("is_hot");
        this.is_hot = is_hot;
    }
    /**
     * 获得品牌馆
     * @return Long
     */
    public Long getVenue_id(){
        return this.venue_id;
    }

    /**
     * 设置品牌馆
     * @param venue_id  品牌馆
     */
    public void setVenue_id(Long venue_id){
        putField("venue_id");
        this.venue_id = venue_id;
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