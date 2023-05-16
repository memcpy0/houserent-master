<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-lg-3 col-md-4 col-sm-12" style="min-height: 765px">
    <div class="property_dashboard_navbar bg-inverse">
        <div class="dash_user_menues">
            <ul>
                <c:if test="${sessionScope.user.role == 'admin' || sessionScope.user.role == 'owner' }">
                    <li <c:if test="${tab == 'house-list'}"> class="active"</c:if>>
                        <a href="/admin/house" style="color: white">
                            <i class="ti-layers"></i>&nbsp;房子管理
                        </a>
                    </li>
                </c:if>
                <li <c:if test="${tab == 'order-list'}"> class="active"</c:if>>
                    <a href="/admin/order" style="color: white">
                        <i class="ti-gift"></i>&nbsp;订单管理
                    </a>
                </li>
                <c:if test="${sessionScope.user.role == 'admin'}">
                    <li <c:if test="${tab == 'user-list'}"> class="active"</c:if>>
                        <a href="/admin/user" style="color: white">
                            <i class="ti-user"></i>&nbsp;用户管理
                        </a>
                    </li>
                </c:if>
                <li <c:if test="${tab == 'my-profile'}"> class="active"</c:if>>
                    <a href="/admin/profile" style="color: white">
                        <i class="ti-user"></i>&nbsp;个人信息
                    </a>
                </li>
                <c:if test="${sessionScope.user.role == 'customer'}">
                    <li <c:if test="${tab == 'home'}"> class="active"</c:if>>
                        <a href="/admin/home" style="color: white">
                            <i class="ti-user"></i>&nbsp;我的家
                        </a>
                    </li>
                </c:if>
                <li <c:if test="${tab == 'mark-list'}"> class="active"</c:if>>
                    <a href="/admin/mark" style="color: white">
                        <i class="ti-bookmark"></i>&nbsp;我的收藏
                    </a>
                </li>
                <li <c:if test="${tab == 'password'}"> class="active"</c:if>>
                    <a href="/admin/password" style="color: white">
                        <i class="ti-unlock"></i>&nbsp;密码修改
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>