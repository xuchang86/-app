package com.rogrand.core.util;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-4-21 <br/>
 * 描述：〈描述〉
 */
public interface Constants {

    public static final double DEFAULT_SEND_DISTANCE = 5;// 商户默认配送距离5公里

    // 推荐海报图片类型
    public static final int RECOMMEND_IMAGE_USER = 1;// 用户推荐图片地址
    public static final int RECOMMEND_IMAGE_MERCHANT_STAFF = 2;// 药店图片地址

    // 星期类型
    public static final int Sunday = 1; // 星期日
    public static final int Monday = 2; // 星期一
    public static final int Tuesday = 3; // 星期二
    public static final int Wednesday = 4; // 星期三
    public static final int Thursday = 5; // 星期四
    public static final int Friday = 6; // 星期五
    public static final int Saturday = 7; // 星期六

    // 资讯模块-资讯类型
    public static final Long CONTENT_TYPE = 1l; // 发布资讯类型
    public static final Long NOTE_TYPE = 2l; // 发布帖子类型

    // 资讯模块-审核状态
    public static final Long AUD_READY_TYPE = 0l; // 草稿
    public static final Long AUD_ON_TYPE = 1l; // 审核中
    public static final Long AUD_PASS_TYPE = 2l; // 审核通过
    public static final Long AUD_CALLBACK_TYPE = 3l; // 回收

    // 热销药品图片大小
    public static final String HOST_SELLING_DRUG_LIST = "_295-295"; // 药品列表图片
    public static final String IMG_MYPHARMA_BASE = "http://img1.mypharma.com/"; // 药品服务器地址

    // 订单状态
    public static final int ORDER_STATE_CREATE = 1; // 创建状态
    public static final int ORDER_STATE_CONFIRM = 2; // 配送确认
    public static final int ORDER_STATE_CANCEL = 3; // 取消状态
    public static final int ORDER_STATE_DELIVERY = 4; // 配送中
    public static final int ORDER_STATE_RECEIPT = 5; // 已收货
    public static final int ORDER_STATE_EVALUATION = 6; // 已评价

    // 康康小助手账号
    public static final String KKHELP_MERCHANT_TEL = "rg_kkxzs";

    // 超级管理员权限
    public static final String SYS_ADMIN_ROLE = "1";
    
    // 黑名单缓存KEY
    public static final String CACHE_KEY_BLACK_LIST = "com.rogrand.kkmy.sms.blacklist";
    // 白名单缓存KEY
    public static final String CACHE_KEY_WHITE_LIST = "com.rogrand.kkmy.sms.whitelist";
}
