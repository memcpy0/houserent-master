<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp" %>
<section class="p-0">
    <div class="container-fluid p-0">
        <div class="row">
            <%@ include file="../common/admin-left.jsp" %>
            <div class="col-lg-9 col-md-8 col-sm-12">
                <div class="dashboard-body">
                    <div class="dashboard-wraper">
                        <div class="frm_submit_block">
                            <h4>订单管理</h4>
                        </div>
                        <table class="property-table-wrap responsive-table bkmark">
                            <tbody>
                            <tr>
                                <th><i class="fa fa-file"></i>订单列表</th>
                                <th>租客</th>
                                <th>房东</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${pageInfo.records}" var="c">
                                <tr>
                                    <td class="dashboard_propert_wrapper">
                                        <img src="${c.house.thumbnailUrl}">
                                        <div class="title">
                                            <h4><a href="/house/detail/${c.house.id}"
                                                   target="_blank">${c.house.title}</a>
                                            </h4>
                                            <span>${c.house.address}</span>
                                            <input type="hidden" id="orderId" value="${c.id}">
                                            <span class="table-property-price">
                                            开始时间：<fmt:formatDate value="${c.startDate}"
                                                                 pattern="yyyy-MM-dd"></fmt:formatDate><br>
                                            到期时间：<fmt:formatDate value="${c.endDate}"
                                                                 pattern="yyyy-MM-dd"></fmt:formatDate>
                                        </span><br>
                                            <span class="table-property-price">
                                            总金额：￥${c.totalAmount}
                                        </span>
                                                <%--订单状态：-3租客已取消 -2待签合同  -1待付款 0生效中 1已到期 2退租申请 3退租申请不通过--%>
                                            <c:choose>
                                                <c:when test="${c.status == -3}"><span
                                                        class="text-warning">租客已取消</span></c:when>
                                                <c:when test="${c.status == -1}"><span
                                                        class="text-warning">待付款</span></c:when>
                                                <c:when test="${c.status == 0}"><span
                                                        class="text-warning">生效中</span></c:when>
                                                <c:when test="${c.status == 1}"><span
                                                        class="text-warning">已到期</span></c:when>
                                                <c:when test="${c.status == 2}"><span
                                                        class="text-warning">退租申请</span></c:when>
                                                <c:when test="${c.status == 3}"><span
                                                        class="text-warning">退租申请不通过</span></c:when>
                                            </c:choose>
                                        </div>
                                    </td>
                                    <td>
                                            ${c.customerUser.userDisplayName}<br>${c.customerUser.phone}
                                    </td>
                                    <td>
                                            ${c.ownerUser.userDisplayName}<br>${c.ownerUser.phone}
                                    </td>
                                    <td>
                                        <c:if test="${c.status == -1}">
                                            <a href="/admin/order" onclick="cancelOrder(${c.id})">取消订单</a><br>
                                        </c:if>
                                        <c:if test="${c.status == -1 && sessionScope.user.role == 'customer'}">
                                            <a href="/order/pay?orderId=${c.id}" style="margin-left: 5px;">去支付</a>
                                        </c:if>
                                        <c:if test="${(c.status == 0 || c.status == 3) && (sessionScope.user.role == 'customer')}"> <!-- 租户才能申请退租 -->
                                            <a href="/admin/order" onclick="endOrder(${c.id})">申请退租</a>
                                        </c:if>
                                        <c:if test="${c.status == 2 && (sessionScope.user.role == 'admin' || sessionScope.user.role == 'owner')}">
                                            <a href="/admin/order" onclick="endOrderPass(${c.id})">退租申请通过</a><br>
                                            <a href="/admin/order" onclick="endOrderReject(${c.id})">退租申请拒绝</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <%@ include file="../common/page.jsp" %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file="../common/footer.jsp" %>
