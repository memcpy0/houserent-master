<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp" %>

<div class="pt-5 pb-5 gray-simple">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10 col-sm-12">
                <div class="prt_detail_three_clicks">
                    <div class="pdt_clicks_title"><h3>${house.title}</h3></div>
                    <div class="pdt_clicks_price"><h4>￥${house.monthRent}/月</h4></div>
                    <div class="pdt_clicks_location"><i class="ti-location-pin"></i>${house.address}</div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<div class="featured_slick_gallery-slide align-content-center" style="width: 95%;">--%>
<%--    <c:forEach items="${house.slideImgList}" var="url">--%>
<%--        <div class="featured_slick_padd">--%>
<%--            <a href="${url}" class="mfp-gallery">--%>
<%--                <img src="${url}" style="width: 80%;height: 400px" class="img-fluid mx-auto">--%>
<%--            </a>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--</div>--%>
<section class="gray">
    <div class="container">

        <div class="row">
            <div class="gray-simple">
                <div id="carouselIndicators" class="carousel slide gray
                    col-lg-12 col-md-12 col-xs-12 col-sm-12" data-ride="carousel" style="width:1180px;">
                    <div class="carousel-inner gray">
                        <c:forEach items="${house.slideImgList}" var="url" varStatus="varStatus">
                            <c:if test="${varStatus.index==0}">
                                <div class="carousel-item active" data-interval="15000">
                                    <img src="${url}" style="width:1200px; height:650px">
                                </div>
                            </c:if>
                            <c:if test="${varStatus.index!=0}">
                                <div class="carousel-item" data-interval="15000">
                                    <img src="${url}" style="width:1200px; height:650px">
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <button class="carousel-control-prev" type="button" style="margin-left: 13px; width:7%;opacity: 0.4"
                            data-target="#carouselIndicators" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" style="margin-right: 13px; width:7%;opacity: 0.4"
                            data-target="#carouselIndicators" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true">Next</span>
                    </button>

                    <ol class="carousel-indicators">
                        <c:forEach items="${house.slideImgList}" var="url" varStatus="varStatus">
                            <c:if test="${varStatus.index==0}">
                                <li data-target="#carouselExampleIndicators" data-slide-to="${varStatus.index}" class="active"></li>
                            </c:if>
                            <c:if test="${varStatus.index!=0}">
                                <li data-target="#carouselExampleIndicators" data-slide-to="${varStatus.index}"></li>
                            </c:if>
                        </c:forEach>
                    </ol>
                </div>
            </div>
        </div>
        <div class="row">
            <span>&nbsp;</span>
        </div>
        <div class="row">
            <span>&nbsp;</span>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-12">
                <div class="property-sidebar">
                    <div class="like_share_wrap b-0">
                        <ul class="like_share_list">
                            <li>
                                <a href="javascript:void(0)" class="btn btn-likes"
                                   onclick="submitMark(${house.id})">
                                    <i class="fas fa-heart"></i>收藏出租信息
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="like_share_wrap b-0">
                        <div class="side-booking-header">
                            <h3 class="price">
                                ￥<fmt:formatNumber value="${house.monthRent/30}" pattern="#"
                                                   type="number"></fmt:formatNumber>&nbsp;/ 天
                            </h3>
                        </div>
                        <div class="side-booking-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label>入住日期，即今日</label>
                                        <div class="cld-box">
                                            <i class="ti-calendar"></i>
                                            <input type="text" name="checkin" id="startDate" class="form-control"
                                                   disabled/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label>退租日期，请选择</label>
                                        <div class="cld-box">
                                            <i class="ti-calendar"></i>
                                            <input type="text" name="checkout" id="endDate" class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <c:choose>
                                            <c:when test="${house.status == 0}">
                                                <a href="javascript:void (0);" class="btn btn-md full-width bg-2"
                                                   onclick="createOrder()"><b>立即预定</b></a>
                                            </c:when>
                                            <c:when test="${house.status == 1}">
                                                <a href="javascript:void (0);" class="btn btn-md full-width bg-1"
                                                   style="pointer-events: none" onclick="createOrder()">已租出</a>
                                            </c:when>
                                            <c:when test="${house.status == -1}">
                                                <a href="javascript:void (0);" class="btn btn-md full-width bg-red"
                                                   style="pointer-events: none" onclick="createOrder()">已下架</a>
                                            </c:when>
                                            <c:when test="${house.status == -2}">
                                                <a href="javascript:void (0);" class="btn btn-md full-width bg-red"
                                                   style="pointer-events: none" onclick="createOrder()">待审核</a>
                                            </c:when>
                                            <c:when test="${house.status == -3}">
                                                <a href="javascript:void (0);" class="btn btn-md full-width bg-red"
                                                   style="pointer-events: none" onclick="createOrder()">审核不通过</a>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--联系房东--%>
                    <div class="like_share_wrap b-0">
                        <div class="side-booking-header">
                            <h3 class="title">联系房东</h3>
                        </div>
                        <div class="side-booking-body">
                            <form id="contactForm">
                                <input type="hidden" id="houseId" name="houseId" value="${house.id}">
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-6">
                                        <div class="form-group bg--facebook">
                                            <p class="title"><b>联系电话：</b></p>
                                            <div style="margin-left: 50px">
                                                <span><b>${house.contactPhone}</b></span>
                                            </div>
                                        </div>
                                        <span>&nbsp;</span>
                                        <div class="form-group bg--facebook">
                                            <p class="title"><b>联系时间：</b></p>
                                            <div style="margin-left: 50px">
                                                <span><b>上午：8:30 - 11:30</b></span><br>
                                                <span><b>下午：14:30 - 17:30</b></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8 col-md-12">
                <div class="property_block_wrap style-2">
                    <div data-toggle="collapse" href="#clOne" class="property_block_wrap_header">
                        <h4 class="property_block_title">房子详细信息</h4>
                    </div>
                    <div class="panel-collapse collapse show" id="clOne">
                        <div class="block-body">
                            <ul class="deatil_features">
                                <li>
                                    <strong>状态：</strong>
                                    <c:choose>
                                        <c:when test="${house.status == 1}">已租出</c:when>
                                        <c:when test="${house.status == 0}">未租出</c:when>
                                        <c:when test="${house.status == -1}">已下架</c:when>
                                        <c:when test="${house.status == -2}">待审核</c:when>
                                        <c:when test="${house.status == -3}">审核驳回</c:when>
                                        <c:otherwise>未知状态</c:otherwise>
                                    </c:choose>
                                </li>
                                <li>
                                    <strong>类型：</strong>
                                    <c:choose>
                                        <c:when test="${house.rentType == 'share'}">合租</c:when>
                                        <c:when test="${house.rentType == 'whole'}">整租</c:when>
                                    </c:choose>
                                </li>
                                <li><strong>租金：</strong>${house.monthRent}</li>
                                <li><strong>房产证编号：</strong>${house.cetificateNo}</li>
                                <li><strong>卧室数量：</strong>${house.bedroomNum}</li>
                                <li><strong>卫生间数量：</strong>${house.toiletNum}</li>
                                <li><strong>厨房数量：</strong>${house.kichenNum}</li>
                                <li><strong>客厅数量：</strong>${house.livingRoomNum}</li>
                                <li><strong>房屋面积：</strong>${house.area}平米</li>
                                <li>
                                    <strong>是否有空调：</strong>
                                    <c:choose>
                                        <c:when test="${house.hasAirConditioner == 0}">没有空调</c:when>
                                        <c:otherwise>有空调</c:otherwise>
                                    </c:choose>
                                </li>
                                <li><strong>建成年份：</strong>${house.buildYear}</li>
                                <li><strong>朝向：</strong>${house.direction}</li>
                                <li><strong>楼层：</strong>${house.floor}/${house.maxFloor}</li>
                                <li>
                                    <strong>是否有电梯：</strong>
                                    <c:choose>
                                        <c:when test="${house.hasAirConditioner == 0}">没有电梯</c:when>
                                        <c:otherwise>有电梯</c:otherwise>
                                    </c:choose>
                                </li>
                                <li>
                                    <strong>最后一次入住开始时间：</strong>
                                    <fmt:formatDate pattern="yyy-MM-dd" value="${house.lastOrderStartTime}"/>
                                </li>
                                <li>
                                    <strong>最后一次入住结束时间：</strong>
                                    <fmt:formatDate pattern="yyy-MM-dd" value="${house.lastOrderEndTime}"/>
                                </li>
                                <li><strong>联系人姓名：</strong>${house.contactName}</li>
                                <li><strong>联系人电话：</strong>${house.contactPhone}</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="property_block_wrap style-2">
                    <div data-toggle="collapse" href="#clTwo" class="property_block_wrap_header">
                        <h4 class="property_block_title">详细描述</h4>
                    </div>
                    <div id="clTwo" class="panel-collapse collapse show">
                        <div class="block-body">
                            ${house.content}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file="../common/footer.jsp" %>

<script>
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var today = month + "/" + date + "/" + year;
    $('input[name="checkin"]').val(today);
    $('input[name="checkout"]').val(today);
    $('input[name="checkout"]').daterangepicker({
        singleDatePicker: true,
        format: "yyyy-MM-dd"
    });
</script>