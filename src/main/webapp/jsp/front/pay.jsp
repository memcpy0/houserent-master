<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp" %>
<link rel="stylesheet" href="/assets/css/pay.css">

<section class="gray-simple">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="mod-ct">
                    <div class="order">
                        ${order.house.title}
                    </div>
                    <div class="amount" id="money">
                        ￥${order.totalAmount}
                    </div>
                    <div style="position: relative;display: inline-block">
                        <img src="/assets/img/alipay_qrcode.png"
                             width="200" height="200" style="display: block;margin:20px;">
                    </div>
                    <div class="time-item">
                        <h1>支付完成后，将跳转到订单列表页面</h1>
                    </div>
                    <div class="tip">
                        <div class="ico-scan-ali"></div>
                        <div class="tip-text">
                            打开支付宝[扫一扫]
                        </div>
                        <div class="tip-text">
                            <a href="/">
                                稍后支付，回到首页
                            </a>
                        </div>
                        <div class="tip-text">
                            <a href="/admin/order">
                                <input type="hidden" id="orderId" value="${order.id}">
                                <button onclick="submitPay()"
                                        class="btn btn-small btn-success"
                                        style="color: #FFFFFF" title="模拟支付成功">模拟支付成功
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file="../common/footer.jsp" %>