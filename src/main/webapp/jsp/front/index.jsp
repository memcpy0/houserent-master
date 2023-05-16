<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp" %>
<div class="image-cover hero_banner" style="background: #334aca url('/assets/img/banner.jpg') no-repeat;"
     data-overlay="1">
    <div class="container">
        <h1 class="big-header-capt mb-0">找房子，上安居！开始一段美好的租房体验！</h1>
    </div>
</div>
<section class="gray-simple">
    <div class="container">
        <div class="row justify-content-center">
            <div class="sec-heading center">
                <h2>最新整租</h2>
                <p>推荐了一些最新发布的整租房子</p>
            </div>
        </div>
        <div class="row">
            <c:forEach items="${recentWholeHouseList}" var="c">
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
            <div class="col-lg-12 col-md-12 col-sm-12 text-center">
                <a href="/house?rentType=whole" class="btn btn-theme arrow-btn bg-2">查看更多
                    <span><i class="ti-arrow-right"></i></span>
                </a>
            </div>
        </div>
    </div>
</section>
<section class="gray-simple">
    <div class="container">
        <div class="row justify-content-center">
            <div class="sec-heading center">
                <h2>最新合租</h2>
                <p>推荐了一些最新发布的合租房子</p>
            </div>
        </div>
        <div class="row">
            <c:forEach items="${recentShareHouseList}" var="c">
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
            <div class="col-lg-12 col-md-12 col-sm-12 text-center">
                <a href="/house?rentType=share" class="btn btn-theme arrow-btn bg-2">查看更多
                    <span><i class="ti-arrow-right"></i></span>
                </a>
            </div>
        </div>
    </div>
</section>
<%@ include file="../common/footer.jsp" %>