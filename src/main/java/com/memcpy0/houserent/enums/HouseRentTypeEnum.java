package com.memcpy0.houserent.enums;

/**
 * 房屋的出租类型
 */
public enum HouseRentTypeEnum {
    /*整租*/
    WHOLE("whole"),

    /*合租*/
    SHARE("share")

    ;
    private String value;


    HouseRentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
