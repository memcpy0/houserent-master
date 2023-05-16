package com.memcpy0.houserent.enums;

/**
 * 反馈状态枚举
 */
public enum FeedbackStatusEnum {
    // 状态：0 待处理 1已经处理


    /*待处理*/
    NOT_HANDLE(0),

    /*已经处理*/
    HAS_HANDLE(1),

    ;
    private Integer value;


    FeedbackStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
