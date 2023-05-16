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
                            <h4>我的收藏</h4>
                        </div>
                        <table class="property-table-wrap responsive-table bkmark">
                            <tbody>
                            <tr>
                                <th><i class="fa fa-file"></i>收藏列表</th>
                                <th></th>
                            </tr>
                            <c:forEach items="${pageInfo.records}" var="entity">
                                <tr>
                                    <td class="dashboard_propert_wrapper">
                                        <img src="${entity.house.thumbnailUrl}">
                                        <div class="title">
                                            <h4><a href="/house/detail/${entity.house.id}"
                                                   target="_blank">${entity.house.title}</a>
                                            </h4>
                                            <span>${entity.house.address}</span>
                                            <span class="table-property-price">
                                                ￥${entity.house.monthRent} / 月
                                           </span>
                                        </div>
                                    </td>
                                    <td>

                                        <a href="/admin/mark" class="delete" onclick="cancelMark(${entity.id})"><i class="ti-close">取消收藏</i></a>
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
