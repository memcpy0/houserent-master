package com.memcpy0.houserent.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.memcpy0.houserent.base.BaseEntity;
import lombok.Data;

/**
 * 用户信息
 */
@Data
@TableName("t_user")
public class User extends BaseEntity {

    // 登录名
    @TableField("user_name")
    private String username;
    // 姓名
    private String userDisplayName;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 密码
    private String userPass;
    // 身份证
    private String idCard;
    // 头像
    private String userAvatar;
    // 个人描述
    private String userDesc;
    // 1正常 0禁用
    private Integer status;
    // 角色 管理员admin/房东owner/租客customer
    private String role;
    // 性别
    private String sex;
    // 业余爱好
    private String hobby;
    // 职业
    private String job;
    // 注册时使用验证码
    @TableField(exist = false)
    private String verifyCode;
}
