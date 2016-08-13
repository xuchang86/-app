package com.rogrand.buynew.domain;
import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import java.util.Date;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-08 <br/>
 * 描述：商品专区类
 */
public class BuyPools extends Base {

    @FieldAnnotation(comment = "id", exp = false, imp = false, empty = true, len = 10, pk = true)
    private Long id;

    @FieldAnnotation(comment = "专区名称", exp = true, imp = true, empty = true, len = 20)
    private String name;

    @FieldAnnotation(comment = "专区说明", exp = true, imp = true, empty = true, len = 100)
    private String instruction;

    @FieldAnnotation(comment = "专区图片", exp = true, imp = true, empty = true, len = 200)
    private String image;

    @FieldAnnotation(comment = "期次", exp = true, imp = true, empty = true, len = 10)
    private Long phase;

    @FieldAnnotation(comment = "app显示位", exp = true, imp = true, empty = true, len = 20)
    private String show_postion;

    @FieldAnnotation(comment = "置顶时间", exp = true, imp = true, empty = true)
    private Date stick_time;

    @FieldAnnotation(comment = "创建人", exp = true, imp = true, empty = true, len = 20)
    private String create_user;

    @FieldAnnotation(comment = "创建时间", exp = true, imp = true, empty = true)
    private Date create_time;

    @FieldAnnotation(comment = "更新人", exp = true, imp = true, empty = true, len = 20)
    private String update_user;

    @FieldAnnotation(comment = "更新时间", exp = true, imp = true, empty = true)
    private Date update_time;


    /**
     * 商品专区对象构造函数
     */
    public BuyPools() {
        super();
    }

    public BuyPools(String id) {
        this("id",id);
    }

    public BuyPools(String property, Object value) {
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
     * 获得专区名称
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * 设置专区名称
     * @param name  专区名称
     */
    public void setName(String name){
        putField("name");
        this.name = name;
    }
    /**
     * 获得专区说明
     * @return String
     */
    public String getInstruction(){
        return this.instruction;
    }

    /**
     * 设置专区说明
     * @param instruction  专区说明
     */
    public void setInstruction(String instruction){
        putField("instruction");
        this.instruction = instruction;
    }
    /**
     * 获得专区图片
     * @return String
     */
    public String getImage(){
        return this.image;
    }

    /**
     * 设置专区图片
     * @param image  专区图片
     */
    public void setImage(String image){
        putField("image");
        this.image = image;
    }
    /**
     * 获得期次
     * @return Long
     */
    public Long getPhase(){
        return this.phase;
    }

    /**
     * 设置期次
     * @param phase  期次
     */
    public void setPhase(Long phase){
        putField("phase");
        this.phase = phase;
    }
    /**
     * 获得app显示位
     * @return String
     */
    public String getShow_postion(){
        return this.show_postion;
    }

    /**
     * 设置app显示位
     * @param show_postion  app显示位
     */
    public void setShow_postion(String show_postion){
        putField("show_postion");
        this.show_postion = show_postion;
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