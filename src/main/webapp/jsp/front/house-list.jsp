<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp" %>
<section class="gray-simple">
    <div class="container">
        <div class="row">
            <c:forEach items="${pageInfo.records}" var="c">
                <div class="col-lg-4 col-md-6 col-sm-12">
                    <div class="single_property_style property_style_2_modern">
                        <div class="listing_thumb_wrapper">
                            <a href="/house/detail/${c.id}"><img src="${c.thumbnailUrl}" class="img-fluid mx-auto"></a>
                        </div>
                        <div class="property_caption_wrappers pb-0" style="margin-left: 20px">
                            <div class="property_short_detail">
                                <div class="pr_type_status">
                                    <h4 class="pr-property_title mb-1"><a href="/house/detail/${c.id}">${c.title}</a>
                                    </h4>
                                    <div class="listing-location-name">
                                        <a href="/house/detail/${c.id}">${c.address}</a>
                                    </div>
                                </div>
                                <div class="property-real-price">
                                    <a href="/house/detail/${c.id}" class="cl-blue">￥${c.monthRent}<span
                                            class="price_status">/月</span></a>
                                </div>
                            </div>
                        </div>
                        <div class="modern_property_footer">
                            <div class="property-lists flex-1">
                                <ul>
                                    <li>
                                        <span class="flatcons"><img src="/assets/img/bed.svg"></span>${c.bedroomNum}卧室
                                    </li>
                                    <li>
                                        <span class="flatcons"><img src="/assets/img/bath.svg"></span>${c.toiletNum}卫生间
                                    </li>
                                </ul>
                            </div>
                            <div class="fp_types">
                                <a href="javascript:void(0)" class="markHouse" onclick="submitMark(${c.id})">收藏</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="row">
            <%@ include file="../common/page.jsp"%>
        </div>
    </div>
</section>
<%@ include file="../common/footer.jsp" %>