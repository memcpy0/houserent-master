package com.memcpy0.houserent.constant;

/**
 * 常量类
 */
public class Constant {
    /**
     * 用户session的Key
     */
    public static final String SESSION_USER_KEY = "user";

    /**
     * 相对路径
     */
//    public static final String UPLOADS_PATH = "/src/main/resources/static/assets/img/uploads/";
    public static final String UPLOADS_PATH = "/uploads/";

    /**
     * 上传的地址
     */
    public static final String UPLOADS_ABSOLUTE_PATH = System.getProperty("user.dir") + UPLOADS_PATH;

    /**
     * 图片session的前缀
     */
    public static final String SESSION_IMG_PREFIX = "SESSION_IMG_";

    /**
     * 最小租住的天数
     */
    public static final Integer MIN_RENT_DAYS = 7;

    /**
     * 首页显示的房子数量
     */
    public static final Integer INDEX_HOUSE_NUM = 10;
}
