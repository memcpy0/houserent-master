<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="light-footer skin-light-footer style-2" style="padding-top: 0">
    <div class="footer-bottom">
        <div class="container company-info">
            <p class="mb-0 text-black-50" style="text-align: center">
                公司地址：山东省青岛市城阳区蔚蓝国际-安居网科技有限公司
            </p>
            <p class="mb-0 text-black-50" style="text-align: center">
                联系方式：010-11111111(电话)&nbsp;&nbsp;&nbsp;admin@admin.com
            </p>
            <p class="mb-0 text-black-50" style="text-align: center">
                安居网©2023 All rights reserved 小组成员：张宏喆 张平&nbsp;&nbsp;
            </p>
            <p class="mb-0 text-black-50" style="text-align: center">
                京ICP备11111111号-4 &nbsp;&nbsp;&nbsp;&nbsp;
                <img src="http://static.nowcoder.com/company/images/res/ghs.png" style="width:18px;" />
                京公网安备 11111111111111号
            </p>
        </div>
    </div>
</footer>

</body>

</html>

<%--注册--%>
<div class="modal" id="signup">
    <div class="modal-dialog">
        <header>
            <div class="hm_nav">
                <h3 class="hm_nav_title">注册</h3>
                <span class="mod-close" data-dismiss="modal"><i class="ti-close"></i></span>
            </div>
        </header>
        <div class="modal-content" id="sign-up">
            <div class="modal-body">
                <form id="registerForm">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>姓名</label>
                                <div class="input-with-icon">
                                    <input type="text" name="userDisplayName" class="form-control" placeholder="姓名">
                                    <i class="ti-user"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group col-lg-8 col-md-8">
                                <label>邮箱</label>
                                <div class="input-with-icon" style="float: left">
                                    <input type="email" name="email" id="email" class="form-control" placeholder="邮箱">
                                    <i class="ti-email" ></i>
                                </div>
                            </div>
                            <button class="btn btn-md bg-2" onclick="sendCode()" style="margin-top: -10px; float: right" type="button">
                                获取验证码
                            </button>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>验证码</label>
                                <div class="input-with-icon">
                                    <input type="email" name="verifyCode" class="form-control" placeholder="验证码">
                                    <i class="ti-comment-detail"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>手机号</label>
                                <div class="input-with-icon">
                                    <input type="text" name="phone" class="form-control" placeholder="手机号">
                                    <i class="ti-mobile"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>账号</label>
                                <div class="input-with-icon">
                                    <input type="text" name="username" class="form-control" placeholder="登录账号">
                                    <i class="ti-user"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>密码</label>
                                <div class="input-with-icon">
                                    <input type="password" name="userPass" class="form-control" placeholder="登录密码">
                                    <i class="ti-unlock"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>角色</label>
                                <div class="simple">
                                    <select name="role" class="form-control">
                                        <option value="customer">租客</option>
                                        <option value="owner">房东</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="button" onclick="submitRegister()" class="btn btn-md full-width pop-login bg-2">
                            创建账号
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%--登录--%>
<div class="modal" id="login">
    <div class="modal-dialog">
        <header>
            <div class="hm_nav">
                <h3 class="hm_nav_title">登录</h3>
                <span class="mod-close" data-dismiss="modal"><i class="ti-close"></i></span>
            </div>
        </header>
        <div class="modal-content">
            <div class="modal-body">
                <form id="loginForm">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>账号</label>
                                <div class="input-with-icon">
                                    <input type="text" name="username" class="form-control" placeholder="请输入账号">
                                    <i class="ti-user"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>密码</label>
                                <div class="input-with-icon">
                                    <input type="password" name="userPass" class="form-control" placeholder="请输入密码">
                                    <i class="ti-unlock"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="button" onclick="submitLogin()" class="btn btn-md full-width pop-login bg-2">登录
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>