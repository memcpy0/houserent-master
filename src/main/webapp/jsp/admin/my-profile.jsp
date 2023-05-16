<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp" %>
<section class="p-0">
    <div class="container-fluid p-0">
        <div class="row">
            <%@ include file="../common/admin-left.jsp" %>
            <div class="col-lg-9 col-md-8 col-sm-12">
                <div style="margin-bottom: 50px" class="dishboard-warper">
                    <h4>个人信息</h4>
                    <div class="frm_submit_block">
                        <form id="profileForm">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label>账号</label>
                                    <input type="text" class="form-control" name="userName" value="${user.username}" disabled>
                                </div>
                                <div class="form-group col-md-6">
                                    <label>姓名</label>
                                    <input type="text" class="form-control" name="userDisplayName" value="${user.userDisplayName}">
                                </div>
                                <div class="form-group col-md-6">
                                    <label>电子邮箱</label>
                                    <input type="email" class="form-control" name="email" value="${user.email}">
                                </div>
                                <div class="form-group col-md-6">
                                    <label>手机号</label>
                                    <input type="text" class="form-control" name="phone" value="${user.phone}">
                                </div>
                                <div class="form-group col-md-6">
                                    <label>身份证号</label>
                                    <input type="text" class="form-control" name="idCard" value="${user.idCard}">
                                </div>
                                <div class="form-group col-md-6">
                                    <label>性别</label>
                                    <select name="sex" class="form-control">
                                        <option value="保密"<c:if test="${user.sex == '保密'}">selected</c:if>>保密</option>
                                        <option value="男"<c:if test="${user.sex == '男'}">selected</c:if>>男</option>
                                        <option value="女"<c:if test="${user.sex == '女'}">selected</c:if>>女</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label>职业</label>
                                        <select name="job" class="form-control">
                                        <option value="互联网/IT/电子/通信"<c:if test="${user.job == '互联网/IT/电子/通信'}">selected</c:if>>互联网/IT/电子/通信</option>
                                        <option value="房地产/建筑"<c:if test="${user.job == '房地产/建筑'}">selected</c:if>>房地产/建筑</option>
                                        <option value="教育培训/科研"<c:if test="${user.job == '教育培训/科研'}">selected</c:if>>教育培训/科研</option>
                                        <option value="广告/传媒/文化/体育"<c:if test="${user.job == '广告/传媒/文化/体育'}">selected</c:if>>广告/传媒/文化/体育</option>
                                        <option value="制药/医疗"<c:if test="${user.job == '制药/医疗'}">selected</c:if>>制药/医疗</option>
                                        <option value="批发/零售/贸易"<c:if test="${user.job == '批发/零售/贸易'}">selected</c:if>>批发/零售/贸易</option>
                                        <option value="制造业"<c:if test="${user.job == '制造业'}">selected</c:if>>制造业</option>
                                        <option value="汽车"<c:if test="${user.job == '汽车'}">selected</c:if>>汽车</option>
                                        <option value="交通运输/仓储/物流"<c:if test="${user.job == '交通运输/仓储/物流'}">selected</c:if>>交通运输/仓储/物流</option>
                                        <option value="专业服务"<c:if test="${user.job == '专业服务'}">selected</c:if>>专业服务</option>
                                        <option value="生活服务"<c:if test="${user.job == '生活服务'}">selected</c:if>>生活服务</option>
                                        <option value="能源/环保/矿产"<c:if test="${user.job == '能源/环保/矿产'}">selected</c:if>>能源/环保/矿产</option>
                                        <option value="政府/非盈利机构"<c:if test="${user.job == '政府/非盈利机构'}">selected</c:if>>政府/非盈利机构</option>
                                        <option value="农/林/牧/渔"<c:if test="${user.job == '农/林/牧/渔'}">selected</c:if>>农/林/牧/渔</option>
                                        <option value="学生"<c:if test="${user.job == '学生'}">selected</c:if>>学生</option>
                                        <option value="其他"<c:if test="${user.job == '其他'}">selected</c:if>>其他</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label>爱好</label>
                                    <input type="text" class="form-control" name="hobby" value="${user.hobby}">
                                </div>
                                <div class="form-group col-md-12">
                                    <label>个人说明</label>
                                    <textarea class="form-control" name="userDesc" >${user.userDesc}</textarea>
                                </div>
                                <div class="form-group col-md-12">
                                    <button class="btn btn-theme bg-2" type="button" onclick="submitProFile()" style="margin-top: 20px; margin-left: 500px">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file="../common/footer.jsp" %>