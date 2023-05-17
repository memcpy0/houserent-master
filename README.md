# 0. 题目说明
某房屋租赁公司欲建立一个房屋租赁服务系统，统一管理房主和租赁者的信息，从而快速地提供租赁服务。系统的用户为系统管理员、房主和租赁者，该系统具有以下功能：
（1）登记房主信息。对于每名房主，系统需登记其姓名、住址和联系电话，并将这些信息写入房主信息文件。
（2）登记房屋信息。所有在系统中登记的房屋都有一个唯一的识别号（对于新增加的房屋，系统会自动为其分配一个识别号）。除此之外，还需登记该房屋的地址、房型（如平房、带阳台的楼房、独立式住宅等）、最多能够容纳的房客数、租金及房屋状态（待租赁、已出租）。这些信息都保存在房屋信息文件中。一名房主可以在系统中登记多个待租赁的房屋。
（3）登记租赁者信息。所有想通过该系统租赁房屋的租赁者，必须首先在系统中登记个人信息，包括：姓名、住址、电话号码、出生年月和性别。这些信息都保存在租赁者信息文件中。
（4）租赁房屋。已经登记在系统中的租赁者，可以得到一份系统提供的待租赁房屋列表。一旦租赁者从中找到合适的房屋，就可以提出看房请求。系统会安排租赁者与房主见面。对于每次看房，系统会生成一条看房记录并将其写入看房记录文件中。
（5）变更房屋状态。当租赁者与房主达成租房或退房协议后，房主向系统提交变更房屋状态的请求。系统将根据房主的请求，修改房屋信息文件。

# 1. 开发说明
## 1.1 系统技术选择
后端：Spring Boot+Mybatis Plus+MySQL
前端：HTML+CSS+JS+JSP，使用font-awesome和themify图标库
系统用户包括：管理员admin、房东owner和租户customer

这一系统是基于Maven管理的SpringMVC技术的房屋出租系统，使用的数据库是MySQL，操作数据库使用的是MyBatis-Plus。
## 1.2 数据库表设计
主要包含三张表：房屋表、订单表、用户表。
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305151008100.png)

```sql
-- ----------------------------
-- Table structure for t_house
-- ----------------------------
DROP TABLE IF EXISTS `t_house`;
CREATE TABLE `t_house`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '房东用户id',
  `rent_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出租类型：整租whole/合租share',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房屋名称',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细描述内容',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址，具体到门牌号',
  `thumbnail_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图的url',
  `slide_url` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮播图url',
  `month_rent` int(11) NULL DEFAULT NULL COMMENT '月租金',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0未租出 1已租出 -1已下架 -2待审核 -3审核不通过',
  `cetificate_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房产证号',
  `toilet_num` int(2) NULL DEFAULT NULL COMMENT '卫生间数量',
  `kichen_num` int(2) NULL DEFAULT NULL COMMENT '厨房数量',
  `living_room_num` int(2) NULL DEFAULT NULL COMMENT '客厅数量',
  `bedroom_num` int(2) NULL DEFAULT NULL COMMENT '卧室数量',
  `has_air_conditioner` int(2) NULL DEFAULT NULL COMMENT '是否有空调 1有 0没有',
  `area` decimal(10, 2) NULL DEFAULT NULL COMMENT '面积',
  `floor` int(3) NULL DEFAULT NULL COMMENT '当前所在楼层数',
  `max_floor` int(3) NULL DEFAULT NULL COMMENT '房子最大楼层数',
  `has_elevator` int(1) NULL DEFAULT NULL COMMENT '是否有电梯 1是-0否',
  `build_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建成年份',
  `direction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '朝向',
  `last_order_start_time` datetime(0) NULL DEFAULT NULL COMMENT '上次开始入住时间',
  `last_order_end_time` datetime(0) NULL DEFAULT NULL COMMENT '上次结束入住时间',
  `longitude_latitude` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经纬度',
  `contact_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '房屋表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `customer_user_id` bigint(20) NULL DEFAULT NULL COMMENT '租客用户id',
  `owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '房东id',
  `house_id` bigint(20) NULL DEFAULT NULL COMMENT '房子id',
  `status` int(1) NULL DEFAULT NULL COMMENT '订单状态：-3租客已取消 -2待签合同 -1待付款 0生效中 1已到期 2退租申请 3退租申请不通过',
  `month_rent` int(11) NULL DEFAULT NULL COMMENT '月租金',
  `day_num` int(11) NULL DEFAULT NULL COMMENT '租住天数',
  `total_amount` int(11) NULL DEFAULT NULL COMMENT '总金额',
  `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名/账号',
  `user_display_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `user_pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像的url地址',
  `user_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人描述',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态 1 正常 0禁用',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色 管理员admin /房东 owner/租客 customer',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `hobby` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业余爱好',
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

INSERT INTO `t_user` VALUES (2, '2022-06-08 11:39:24', 'memset0', '租客', '18691433704', 'asfsdf@qq.com', 'wdmm1234', '还没填写', '/assets/img/default-avatar.jpg', '还没有填写！', 1, 'customer', '保密', '喜欢撸串', '');
INSERT INTO `t_user` VALUES (6, '2022-06-08 13:13:26', 'admin', '管理员', '18691433704', '123456789@qq.com', '123456', '152652513523564456', '/assets/img/default-avatar.jpg', '管理员', 1, 'admin', '保密', '超爱写代码！', '互联网/IT/电子/通信');
INSERT INTO `t_user` VALUES (9, '2022-06-08 13:28:07', 'test2', '房东', '12345647578', 'asfsdf@qq.com', '123456', '还没填写', '/assets/img/default-avatar.jpg', '还没有填写！', 1, 'owner', '保密', '还没有填写!', '还没有填写!');
INSERT INTO `t_user` VALUES (11, '2023-05-14 11:53:15', 'memcpy0', 'memcpy0', '18169257213', '2183927003@qq.com,2183927003@qq.com', 'wdmm1234', '还没填写', '/assets/img/default-avatar.jpg', '还没有填写！', 1, 'owner', '保密', '还没有填写!', '还没有填写!');
```
此外还有一张收藏表，记录每个用户收藏的出租信息：
```sql
-- ----------------------------
-- Table structure for t_mark
-- ----------------------------
DROP TABLE IF EXISTS `t_mark`;
CREATE TABLE `t_mark`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '收藏时间',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `house_id` bigint(20) NULL DEFAULT NULL COMMENT '房子id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_mark
-- ----------------------------
INSERT INTO `t_mark` VALUES (1, '2022-06-15 11:06:02', 2, 33);
INSERT INTO `t_mark` VALUES (2, '2023-05-15 16:27:03', 11, 39);
INSERT INTO `t_mark` VALUES (3, '2023-05-15 16:27:21', 11, 41);
```
## 1.3 界面说明
未登录状态下可以看到首页（列出最新的合租或整租房子）、合租（房子列表）、整租（房子列表）三个页面。未登录状态下如图：
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305151300526.png)
登录后，可通过个人头像进入相关管理页面：
- 所有用户都可进入个人信息、我的收藏、密码修改等页面
- 房东可以进入：房子管理（发布/查看/编辑/上架/下架/删除房子信息）、订单管理（通过/拒绝「租住自己房子的用户」退租申请），已租出的房子信息不能编辑/下架/删除。
- 管理员在房东功能的基础上，还可额外进入的界面：**用户管理**（查看用户列表，启用/禁用用户账号），还能使用额外的功能：房子审核通过/驳回，已租出的房子信息不能编辑/上架/下架/删除。

## 1.4 流程图
管理员的功能涵盖了其他人的功能：
![500](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305172357291.jpg)
大体流程：
1. 租客浏览租房信息，确定租期，预定房子
2. 进入支付，支付完成，订单生效，房子租出，无需房东确认
3. 没有支付，则订单不会生效，管理员或房东可以取消订单
4. 租客可以申请退租，管理员或房东通过申请，租期就会结束
5. 房东或管理员可以发布租房信息，包括租金、面积、位置、房屋图片等信息；管理员审核通过后，就可以在租房信息列表中看到。

---
# 2. 基础代码说明
## 2.1 基类
提供了三个简单的基类，分别是 `BaseEntity, BaseService, BaseController` ，**分别被各实体类、服务接口和控制器类继承**。`BaseEntity` 中包含了实体类公共的属性：自动增长的主键和自动填写的创建时间：
```java
package com.memcpy0.houserent.base;

...

/**
 * 所有实体类公共的属性
 */
@Data
public class BaseEntity implements Serializable {
    //ID，主键，自动生成
    @TableId(type = IdType.AUTO)
    private Long id;
    //创建时间
    @JsonFormat(pattern = "yyyy—MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
```
`BaseService` 接口模仿了一下MybatisPlus的 `IService` 接口，包装MybatisPlus提供的 `BaseMapper` 接口提供的方法，提供了一些查询、修改、更新、删除的方法：
```java
/**
 * 所有service接口的基础接口
 */
@Service
public interface BaseService<E, ID extends Serializable> {
    /**
     * mapper对象
     *
     * @return
     */
    BaseMapper<E> getRepository();

    /**
     * 根据主键ID 获取对象
     */
    default E get(ID id) {
        return getRepository().selectById(id);
    }

    /**
     * 获取所有列表
     */
    default List<E> getAll() {
        return getRepository().selectList(null);
    }

    /**
     * 添加一条记录
     */
    default E insert(E entity) {
        getRepository().insert(entity);
        return entity;
    }

    /**
     * 修改一条记录
     */
    default E update(E entity) {
        getRepository().updateById(entity);
        return entity;
    }

    /**
     * 添加或者更新
     */
    default E insertOrUpdate(E entity) {
        try {
            Object id = entity.getClass().getMethod("getId").invoke(entity);
            if (id == null) {        //没有id，说明需要插入
                insert(entity);
            } else {                  //有id，说明需要更新
                update(entity);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 根据主键ID 删除一条记录
     */
    default int delete(ID id) {
        return getRepository().deleteById(id);
    }

    /**
     * 批量删除
     */
    default void batchDelect(List<ID> ids) {
        getRepository().deleteBatchIds(ids);
    }

    /**
     * 分页查询
     */
    default Page<E> findAll(Page<E> page) {
        return (Page<E>) getRepository().selectPage(page, null);
    }

    /**
     * 获得查询器
     */
    QueryWrapper<E> getQueryWrapper(E e);

    /**
     * 获得带参数的查询器
     */
    QueryWrapper<E> getQueryWrapper(Map<String, Object> condition);

    /**
     * 根据单个查询条件分页查询
     */
    default Page<E> findAll(Page<E> page, E e) {
        QueryWrapper<E> queryWrapper = getQueryWrapper(e);
        return (Page<E>) getRepository().selectPage(page, queryWrapper);
    }

    /**
     * 根据查询条件分页查询
     */
    default Page<E> findAll(Page<E> page, Map<String, Object> condition) {
        QueryWrapper<E> queryWrapper = getQueryWrapper(condition);
        return (Page<E>) getRepository().selectPage(page, queryWrapper);
    }
}
```
`BaseController` 包装了几个常用的工具方法，判断当前用户是否为管理员、房东或租客：
```java
package com.memcpy0.houserent.base;


import com.memcpy0.houserent.constant.Constant;
import com.memcpy0.houserent.enums.UserRoleEnum;
import com.memcpy0.houserent.entity.User;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 所有controller控制器的基类
 */
@Controller
public class BaseController {

    @Resource
    protected HttpServletRequest request;

    /**
     * 获得当前登录用户
     */
    public User getLoginUser(){
        User user = (User)request.getSession().getAttribute(Constant.SESSION_USER_KEY);
        return user;
    }

    /**
     * 获得当前用户ID
     */    public Long getLoginUserId(){
        User user = (User)request.getSession().getAttribute(Constant.SESSION_USER_KEY);
        if(user==null){
            return null;
        }
        return user.getId();
    }

    /**
     * 当前用户是管理员
     */
    public Boolean loginUserIsAdmin(){
        User user = getLoginUser();
        if(user == null){
            return false;
        }
        return UserRoleEnum.ADMIN.getValue().equalsIgnoreCase(user.getRole());
    }

    /**
     * 当前用户是租户
     */
    public Boolean loginUserIsCustomer(){
        User user = getLoginUser();
        if(user == null){
            return false;
        }
        return UserRoleEnum.CUSTOMER.getValue().equalsIgnoreCase(user.getRole());
    }

    /**
     * 当前用户是房东
     */
    public Boolean loginUserIsOwner(){
        User user = getLoginUser();
        if(user == null){
            return false;
        }
        return UserRoleEnum.OWNER.getValue().equalsIgnoreCase(user.getRole());
    }

    /**
     * 渲染404页面
     */
    public String renderNotFond(){
        return "forward:/404";
    }

    /**
     * 没有权限
     */
    public String renderNotAllowAccsee(){
        return "forward:/403";
    }

    /**
     * 服务器异常
     */
    public String renderServerException(){
        return "forward:/500";
    }

    /**
     * 其他错误
     */
    public String renderError(){
        return "forward:/error";
    }
}
```
## 2.2 访问控制
使用拦截器进行拦截：
- 未登录状态下，可以看到的三个前端界面不拦截；
- 注册和登录请求不拦截；
- 拦截对个人信息、我的收藏、密码修改、我租住的房子、房子管理、订单管理、用户管理的访问，要求必须登录；
- **拦截对我租住的房子这一功能的访问，要求必须是租客**；
- **拦截对房子管理、订单管理的访问，要求必须是房东或管理员**（或不是租客）；
- **拦截对用户管理的访问，要求必须是管理员**。
- ……

下面是通用拦截器，必须要求用户已经登录：
```java
/**
 * 通用拦截器，必须登录后有用户信息才能访问
 */
@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER_KEY); // 如果用户未登录﹐拦截
        if (user == null) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
```
下面的拦截器要求用户是租户、
```java
/**
 * 租客接口拦截器
 */
@Component
public class CustomerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
        // 如果用户不是租客﹐拦截
        return UserRoleEnum.CUSTOMER.getValue().equalsIgnoreCase(user.getRole());
    }
}
```
下面的拦截器要求用户必须是房东或管理员：
```java
/**
 * 房东或管理员接口拦截器
 */
@Component
public class OwnerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
        // 如果用户是租客﹐拦截
        if (UserRoleEnum.CUSTOMER.getValue().equalsIgnoreCase(user.getRole())) {
            return false;
        }
        return true;
    }
}
```
下面的拦截器要求用户必须是管理员：
```java
/**
 * 管理员接口拦截器
 */
@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER_KEY);
	// 如果用户不是管理员﹐拦截
        if (!UserRoleEnum.ADMIN.getValue().equalsIgnoreCase(user.getRole())) {
            return false;
        }
        return true;
    }
}
```
在 `WebMvcConfig` 配置类中配置拦截器：
```java
/**
 * 配置JSP的访问前后缀
 * @return
 */
@Bean
public InternalResourceViewResolver setupViewResolver(){
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    // jsp的访问前缀
    resolver.setPrefix("/jsp/");
    // jsp的访问后缀
    resolver.setSuffix(".jsp");
    return resolver;
}

@Override
public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(new CommonInterceptor())
            .addPathPatterns("/admin/profile") //  个人信息
            .addPathPatterns("/admin/profile/submit") //  个人信息修改
            .addPathPatterns("/admin/mark") //  收藏列表
            .addPathPatterns("/admin/mark/cancel") // 取消收藏
            .addPathPatterns("/admin/password") //  密码修改
            .addPathPatterns("/admin/password/submit") //  密码修改信息提交
            .addPathPatterns("/admin/home")  //  我的家
            .addPathPatterns("/admin/house") //  房子管理
            .addPathPatterns("/admin/publish") // 发布新房子
            .addPathPatterns("/admin/publish/submit")
            .addPathPatterns("/admin/down")
            .addPathPatterns("/admin/up") // 上架和下架房子
            .addPathPatterns("/admin/delete") // 删除房子
            .addPathPatterns("/admin/checkPass")
            .addPathPatterns("/admin/checkReject") // 审核通过或拒绝新发布的租房信息
            .addPathPatterns("/admin/order") //  订单管理 //  这些功能必须登录
            .addPathPatterns("/order/create") // 预定租房请求
            .addPathPatterns("/order/pay") // 处理支付请求
            .addPathPatterns("/mark/submit") // 收藏房子信息的请求(必须登录才能收藏)
            .addPathPatterns("/admin/user") //  用户管理
            .addPathPatterns("/admin/user/disable") // 启用/禁用用户
            .addPathPatterns("/admin/user/enable");

    registry.addInterceptor(new CustomerInterceptor())
            .addPathPatterns("/admin/home") //  我的家 //  这些功能必须是租户
            .addPathPatterns("/order/create") // 预定租房请求
            .addPathPatterns("/order/pay"); // 处理支付请求

    registry.addInterceptor(new OwnerInterceptor())
            .addPathPatterns("/admin/house") // 查询房子列表 // 这些功能必须是房东或管理员
            .addPathPatterns("/admin/publish") // 发布新房子
            .addPathPatterns("/admin/publish/submit")
            .addPathPatterns("/admin/down")
            .addPathPatterns("/admin/up") // 上架和下架房子
            .addPathPatterns("/admin/delete"); // 删除房子

    registry.addInterceptor(new AdminInterceptor())
            .addPathPatterns("/admin/user") //  这些功能必须是管理员
            .addPathPatterns("/admin/user/disable") // 启用/禁用用户
            .addPathPatterns("/admin/user/enable")
            .addPathPatterns("/admin/checkPass")
            .addPathPatterns("/admin/checkReject"); // 审核通过或拒绝新发布的租房信息
}
```
## 2.3 统一日志记录
使用Spring AOP+CGLIB动态代理，定义统一的日志记录器，记录每个用户在何时访问了业务层的哪个方法：
```java
package com.memcpy0.houserent.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class ServiceLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Pointcut("execution(* com.memcpy0.houserent.service.impl.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // 用户[1.2.3.4],在[xxx],访问了[com.memcpy0.houserent.service.xxx()].
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteHost();
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String target = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        logger.info(String.format("用户[%s],在[%s],访问了[%s].", ip, now, target));
    }
}
```
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305170051404.png)

## 2.4 邮件发送说明
为了发送含有验证码的邮件，需要使用Spring Email（Spring Boot Mail Starter）。先导入JAR包：
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```
Spring Boot对发送邮件提供了支持，可以通过MailProperties对邮件参数进行配置。
- 可以配置邮件服务器的域名和端口
- 可以配置发件人的账号及密码
- 可以配置发送邮件的协议类型

以下是我个人的配置信息：
```yml
spring:
  mail:
    host: smtp.sina.com
    port: 465
    username: memcpy0@sina.com
    password: 97e3d34d6482962f
    protocol: smtps
    properties:
      mail:
        smtp:
          ssl:
            enable: true
```
工具类：
```java
package com.memcpy0.houserent.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Component // 通用Bean,哪个层次都可用
public class MailClient {
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);
    @Autowired
    private JavaMailSender mailSender; // 发送者(固定是我们自己,直接注入)
    @Value("${spring.mail.username}")
    private String from;
    /**
     * 接收者
     * 邮件主题
     * 内容
     */
    public void sendMail(String to, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("【验证码】");
            helper.setText(content, false); // true表示是html
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败:" + e.getMessage());
        }
    }
}
```
发送验证码的方法如下，会把验证码存储到Session中：
```java
@Autowired
private MailClient mailClient;
/**
 * 发送验证码
 */
@RequestMapping(value = "/sendCode", method = RequestMethod.POST)
@ResponseBody
public JsonResult sendCode(String mail, HttpSession session) throws MessagingException, UnsupportedEncodingException {
    String code = CreateCode.createCode(6);
    String message = "您好,您的验证码是" + code;
    session.setAttribute("MessageCode", code); // 将验证码存入session中
    session.setMaxInactiveInterval(60 * 60);
    mailClient.sendMail(mail, message);
    return JsonResult.success("验证码已经发送");
}
```
下面是收到的邮件：
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305170150054.png)

---
# 3. 实体类说明
`House` 出租信息类：
```java
/**
 * 房子信息
 */
@Data
@TableName("t_house")
public class House extends BaseEntity {
    // 房东用户ID
    private Long userId;
    // 出租类型：整租whole/合租share
    private String rentType;
    // 房屋名称
    private String title;
    // 详细描述
    private String content;
    // 城市名称
    private String city;
    // 详细地址,具体到门牌号
    private String address;
    // 缩略图url
    private String thumbnailUrl;
    // 轮播图url
    private String slideUrl;
    // 月租金
    private Integer monthRent;
    // 状态：0未租出 1已租出 -1已下架 -2待审核 -3审核不通过
    private Integer status;
    // 房产证号
    private String cetificateNo;
    // 卫生间数量
    private Integer toiletNum;
    // 厨房数量
    private Integer kichenNum;
    // 客厅数量
    private Integer livingRoomNum;
    // 卧室数量
    private Integer bedroomNum;
    // 是否有空调 1有 0没有
    private Integer hasAirConditioner;
    // 面积
    private Double area;
    // 当前所在楼层数
    private Integer floor;
    // 房子最大楼层数
    private Integer maxFloor;
    // 是否有电梯 1是-0否
    private Integer hasElevator;
    // 建成年份
    private String buildYear;
    // 朝向
    private String direction;
    // 上次开始入住时间
    private Date lastOrderStartTime;
    // 上次结束入住时间
    private Date lastOrderEndTime;
    // 经纬度
    private String longitudeLatitude;
    // 联系人姓名
    private String contactName;
    // 联系人电话
    private String contactPhone;

    // 轮播图列表
    // 声明非数据库字段
    @TableField(exist = false)
    private List<String> slideImgList;

    // 合租房子
    // 声明非数据库字段
    @TableField(exist = false)
    private List<House> shareHouseList;
}
```
用户信息类：
```java
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
}
```
订单信息类：
```java
/**
 * 订单实体类
 */
@Data
@TableName("t_order")
public class Order extends BaseEntity {
    // 租客用户id
    private Long customerUserId;
    // 房东id
    private Long ownerUserId;
    // 房子id
    private Long houseId;
    // 订单状态：-3租客已取消 -2待签合同 -1待付款 0生效中 1已到期 2退租申请 3退租申请不通过
    private Integer status;
    // 月租金
    private Integer monthRent;
    // 租住天数
    private Integer dayNum;
    // 总金额
    private Integer totalAmount;
    // 开始日期
    private Date startDate;
    // 结束日期
    private Date endDate;

    // 关联的房子信息
    @TableField(exist = false)
    private House house;

    // 租客用户信息
    @TableField(exist = false)
    private User customerUser;

    // 房东用户信息
    @TableField(exist = false)
    private User ownerUser;
}
```
收藏信息类：
```java
/**
 * 收藏
 */
@Data
@TableName("t_mark")
public class Mark extends BaseEntity {
    // 收藏者id
    private Long userId;

    // 房子id
    private Long houseId;

    // 房子信息
    @TableField(exist = false)
    private House house;
}
```
---
# 4. 控制器类说明
主要的业务实现在这些类中，因此给出对应UML图。
## 4.1 `IndexController` 控制器
 `IndexController` 控制器，返回房屋租赁系统的首页（包括合租和整租的几个最新房子列表）。

![500](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305151809030.png)

## 4.2 `HouseDetailController` 控制器
`HouseDetailController` 控制器：处理对房屋详情的查询、房屋列表的分页查询。

![500](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305151726959.png)

## 4.3 `LoginController` 和 `RegisterController` 控制器
 `LoginController` 控制器，校验登录信息，判断登录是否正确，并将用户信息存到Session中；还负责登出，即清除Session中的用户信息。

![600](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305152136144.png)
`RegisterController` 控制器，负责注册，在注册时请求验证码（并将前端填写的邮箱发送过来），服务器生成固定产长度的随机字符串作为验证码，并发送验证码邮件给用户。注册时校验填写的用户信息是否符合要求，并存储用户信息到数据库中。

![700](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305152144844.png)

## 4.4 `MarkController` 和 `AdminMarkController` 控制器
`MarkController` 负责收藏任务，需要登录后收藏。`AdminMarkController` 在个人中心-收藏管理中，返回收藏列表，还可取消对应收藏。

![600](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305160030714.png)
## 4.5 `OrderController` 控制器
这一控制器，处理预订房子的请求。需要是租户。
1. 当房子不存在、已租出且未释放、退租日期不合法、最少租住时间太短等情况发生时，返回一个JSON对象，描述相关错误。
2. 否则，创建订单并存入数据库，订单状态为未支付。
3. 成功下订单后，返回订单ID并跳转到支付页面，支付页面中可用支付宝进行支付——这里请求服务器 `OrderController` 控制器的 `paySubmit` 方法，模拟支付过程。
4. 成功支付后，处理支付请求（设置房屋出租状态）后，跳转到用户租房界面。

![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305160049460.png)
下面是下订单在服务器端的序列图，先是校验，然后创建订单：

![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305160051180.png)
## 4.6 `ProfileController` 控制器
 `ProfileController` 控制器返回个人的信息列表，还可处理个人信息的编辑请求，保存/更新提交的个人信息。**更新用户信息时，还一并更新Session中存储的当前用户信息**。

![500](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305160106430.png)
## 4.7 `HomeController` 控制器
 `HomeController` 控制器返回租户租住（过）的房子的列表信息，当然，需要分页查询：

![400](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305160107915.png)
## 4.8 `FileController` 和 `HouseController` 控制器
`FileController` 控制器主要负责文件上传，即负责轮播图上传的处理，每完成一张图片的传输， 就将该图片URL存储到Session中，最后相当于将一个房子的所有轮播图片路径存为一个 `List<String>` ，并存放在Session中。

![500](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305161500441.png)

`HouseController` 控制器：
- 方法 `publish` 用于发布新的租房信息，前端完成轮播图的上传后，在后端的Session中已经存储有轮播图的路径列表，前端提交租房信息后，**后端中，从Session中取出轮播图路径列表**，将其与传来的租房信息存入 `House` 数据表中。
    ![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305180007660.png)
- 方法 `houseList` 返回房子列表，管理员可以看到所有房子，房东只能看到自己的房子。
- 方法 `downHouse` 和 `upHouse` 分别下架和上架房子。
- 方法 `deleteHouse` 删除房子。
- 方法 `checkPassHouse, checkRejectHouse` 审核通过或拒绝新发布的房子信息。

![500](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305161637756.png)

## 4.9 `UserController` 控制器
`UserController` 控制器主要负责返回用户列表、启用/禁用用户账号（管理员专用）、修改密码等功能：

![500](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305161509821.png)

## 4.10 `AdminOrderController` 控制器
 `AdminOrderController` 控制器对租客、管理员或房东，返回订单列表，**只是他们能做的事情不同**：
 - 对租客，返回他租过或准备租的房子相对应的订单列表；对房东，返回他出租的房子对应的订单列表；对管理员，返回所有订单列表。
 - 租客可以提出退租申请，管理员或房东可以驳回或同意退租申请。
 - 房东可以同意或拒绝租房请求（即取消租房订单）。

![500](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305161750190.png)

---
# 5. 部署说明
为了部署项目到服务器上，需要放行数据库端口3306和项目访问端口8088。且Java项目打包成为JAR包以后，在Linux服务器上通过 `java -jar` 命令运行。Linux是无法解压Jar包的，也就是无法访问到 `resources/static` 里面存放的静态图片。Jar包只能用于跑代码。

为了上传图片到服务器，解决方案：**在Linux中创建文件上传的文件夹，并更改文件上传的路径，还要配置静态资源放行路径**。
```java
/**
 * 配置静态资源访问路径
 */
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry){
    // 获取文件的真实路径 work_project代表项目工程名 需要更改
    String os = System.getProperty("os.name");
    registry.addResourceHandler("/assets/**") // 静态资源路径以/assets开头
            .addResourceLocations("classpath:/static/assets/");

    if (os.toLowerCase().startsWith("win")) { // 在本地机器
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///" + System.getProperty("user.dir") + "/src/main/resources/static/assets/img/uploads/");
    } else { // linux和mac系统 可以根据逻辑再做处理
	registry.addResourceHandler("/uploads/**")
		.addResourceLocations("file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
		+ "uploads" + System.getProperty("file.separator"));
    }
}
```
部署过程如下：先添加 `jstl, servlet, tomcat-embed-jasper` 依赖：
```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <scope>provided</scope>
</dependency>
```
再指定 `spring-boot-maven-plugin` 版本为1.4.2.RELEASE，并放行一些二进制文件：
```xml
<plugins>
    <plugin>
	<groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>1.4.2.RELEASE</version>
        <configuration>
	        <fork>true</fork>
        </configuration>
    </plugin>
    <plugin>
	<groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-resources-plugin</artifactId>
        <configuration>
	    <encoding>${project.build.sourceEncoding}</encoding>
            <nonFilteredFileExtensions>
	        <nonFilteredFileExtension>woff</nonFilteredFileExtension>
                <nonFilteredFileExtension>woff2</nonFilteredFileExtension>
                <nonFilteredFileExtension>eot</nonFilteredFileExtension>
                <nonFilteredFileExtension>ttf</nonFilteredFileExtension>
                <nonFilteredFileExtension>svg</nonFilteredFileExtension>
            </nonFilteredFileExtensions>
        </configuration>
    </plugin>
</plugins>
```
为了打包JSP等资源文件，还需配置如下内容：
```xml
<resources>
    <!-- 打包时将jsp文件拷贝到META-INF目录下 -->
    <resource>
	<!-- 指定resources插件处理哪个目录下的资源文件 -->
	<directory>src/main/webapp</directory>
	<!--注意此次必须要放在此目录下才能被访问到 -->
	<targetPath>META-INF/resources</targetPath>
	<includes>
	    <include>**/**</include>
	</includes>
    <filtering>false</filtering>
    </resource>
    <resource>
	<directory>src/main/resources</directory>
	<includes>
	    <include>**.*</include>
	    <include>**/**.*</include>
	</includes>
	<filtering>true</filtering>
    </resource>
</resources>
```
最后成功部署，整个过程参考了如下博客：
- [springboot打war包，成功部署](https://blog.csdn.net/qq_40036754/article/details/127328473)
- [spring boot部署war包到tomcat访问失败](https://blog.csdn.net/Alwaywon/article/details/124915909)
- [springboot之Jar包Linux后台启动部署及滚动日志查看且日志输出至文件保存](https://blog.csdn.net/m0_67400973/article/details/126114394)
- [springboot+jsp打成jar包部署404问题](https://blog.csdn.net/qq_40807739/article/details/87862968)
- [解决springboot项目打成jar包部署到linux服务器后上传图片无法访问的问题](https://www.cnblogs.com/xy2559/p/14478464.html)

---
# 6. 运行结果
未登录的首页：
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305162222241.png)
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305171825102.png)

![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305171821797.png)

登录界面：
![400](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305162223855.png)
注册界面：
![400](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305162225693.png)
登录后，以房东为例，可以使用如下功能：
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305162227750.png)

以租客为例：
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305171841641.png)

![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305171839930.png)

以管理员为例：房子管理界面：
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305171844272.png)
用户管理界面：
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305171845885.png)

订单管理界面：
![](https://image-1307616428.cos.ap-beijing.myqcloud.com/Obsidian/202305172002827.png)

---
# 7. 总结与改进
本项目仅简单完成了课程要求，总体而言，还有很多可以改进的地方：
- 进一步修改BootStrap组件的代码，使其更加符合「房屋租赁」业务的风格
- 比如添加房子信息搜索服务，通过ElasticSearch对房子地址、描述信息等进行检索
- 前端页面：可以进一步限制输入数据，用选择框或JS代码；后端，更加细致地校验数据，如用正则表达式等
- 订单列表分类查询：待支付、已支付（生效中）、已取消、已到期、退租申请、退租申请不通过等
- 验证码存储在Session中，在分布式部署时可能出现问题，可以使用Redis替代Session
- 可以用Redis+Caffeine组建多级缓存，缓存租房列表和当前登录用户信息，减少对数据库的查询，提高查询性能























