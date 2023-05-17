/*发送验证码*/
function sendCode() {
    $.ajax({
        type: "POST",
        url: "/register/sendCode",
        data: {
            mail:$("#email").val()
        },
        success: function (data) {
            //请求成功之后进入该方法，data为成功后返回的数据
            return 1;
        },
    })
}

/*注册提交*/
function submitRegister() {
    $.ajax({
        type: "POST",
        url: "/register/submit",
        async: false,            //不是异步
        data: $("#registerForm").serialize(),
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.reload();
            }
        }
    });
}

/*登录提交*/
function submitLogin() {
    $.ajax({
        type: "POST",
        url: "/login/submit",
        async: false,            //不是异步
        data: $("#loginForm").serialize(),
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.reload();
            }
        }
    });
}

/*保存房子信息*/
function submitHouse() {
    $.ajax({
        type: "POST",
        url: "/admin/publish/submit",
        async: false,            //不是异步
        data: $("#houseForm").serialize(),
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.href = "/admin/house";
            }
        }
    });
}

/*下架房子*/
function downHouse(id) {
    $.ajax({
        type: "POST",
        url: "/admin/down",
        async: false,            //不是异步
        data: {id},
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.reload();
            }
        }
    });
}

/*上架房子*/
function upHouse(id) {
    $.ajax({
        type: "POST",
        url: "/admin/up",
        async: false,            //不是异步
        data: {id},
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.reload();
            }
        }
    });
}

/*房子审核通过*/
function checkPassHouse(id) {
    $.ajax({
        type: "POST",
        url: "/admin/checkPass",
        async: false,            //不是异步
        data: {id},
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.reload();
            }
        }
    });
}

/*房子审核不通过*/
function checkRejectHouse(id) {
    $.ajax({
        type: "POST",
        url: "/admin/checkReject",
        async: false,            //不是异步
        data: {id},
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.reload();
            }
        }
    });
}

/*删除房子*/
function deleteHouse(id) {
    $.ajax({
        type: "POST",
        url: "/admin/delete",
        async: false,            //不是异步
        data: {id},
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.reload();
            }
        }
    });
}

/*收藏房子*/
function submitMark(id) {
    $.ajax({
        type: "POST",
        url: "/mark/submit",
        async: false,            //不是异步
        data: {"houseId": id},
        success: function (data) {
            alert(data.msg)
            if (data.msg == "请先登录") {
                window.location.href = "/";
            }
        }
    });
}

/*创建订单*/
function createOrder() {
    var houseId = $("#houseId").val();
    var endDate = $("#endDate").val()
    $.ajax({
        type: "POST",
        url: "/order/create",
        async: false,            //不是异步
        data: {
            "houseId": houseId,
            "endDate": endDate
        },
        success: function (data) {
            alert(data.msg)
            if (data.msg == "请先登录") {
                window.location.href = "/";
            }
            //如果创建成功，跳转支付页面
            if (data.code == 1) {
                window.location.href = "/order/pay?orderId=" + data.result
            }
        }
    });
}

/*模拟支付*/
function submitPay() {
    var orderId = $("#orderId").val();
    alert("支付成功，请联系房东")
    $.ajax({
        type: "POST",
        url: "/order/pay/submit",
        async: false,            //不是异步
        data: {'orderId': orderId},
        success: function (data) {
            alert(data.msg)
            if (data.msg == "请先登录") {
                window.location.href = "/";
            }
            //如果支付成功，跳转我的家
            if (data.code == 1) {
                window.location.href = "/admin/home"
            }
        }
    });
}

/*个人信息保存*/
function submitProFile() {
    $.ajax({
        type: "POST",
        url: "/admin/profile/submit",
        async: false,            //不是异步
        data: $('#profileForm').serialize(),
        success: function (data) {
            alert(data.msg)

            //如果支付成功，刷新页面
            if (data.code == 1) {
                window.location.reload();
            }
            if (data.msg == "请先登录") {
                window.location.href = "/";
            }
        }
    });
}

/*取消订单*/
function cancelOrder(orderId) {
    alert("取消订单成功");
    $.ajax({
        type: "POST",
        url: "/admin/order/cancel",
        async: false,            //不是异步
        data: {'orderId': orderId},
        success: function (data) {
            //如果取消成功，刷新页面
            if (data.code == 1) {
                window.location.reload();
            }
        }
    });
}

/*申请退租*/
function endOrder(orderId) {
    alert("退租申请已提交，请联系房东审核")
    $.ajax({
        type: "POST",
        url: "/admin/order/end",
        async: false,            //不是异步
        data: {'orderId': orderId},
        success: function (data) {
            //如果取消成功，刷新页面
            if (data.code == 1) {
                window.location.reload();
            }
        }
    });
}

/*退租申请通过*/
function endOrderPass(orderId) {
    alert("退租申请已通过")
    $.ajax({
        type: "POST",
        url: "/admin/order/endPass",
        async: false,            //不是异步
        data: {'orderId': orderId},
        success: function (data) {
            alert(data.msg)
            //如果取消成功，刷新页面
            if (data.code == 1) {
                window.location.reload();
            }
        }
    });
}

/*退租申请拒绝*/
function endOrderReject(orderId) {
    alert("操作成功")
    $.ajax({
        type: "POST",
        url: "/admin/order/endReject",
        async: false,            //不是异步
        data: {'orderId': orderId},
        success: function (data) {
            //如果取消成功，刷新页面
            if (data.code == 1) {
                window.location.reload();
            }
        }
    });
}

/*取消收藏*/
function cancelMark(id) {
    alert("取消收藏成功")
    $.ajax({
        type: "POST",
        url: "/admin/mark/cancel",
        async: false,            //不是异步
        data: {
            'id': id
        },

        success: function (data) {
            //如果取消成功，刷新页面
            if (data.code == 1) {
                window.location.reload();
            }
        }
    });

}

/*删除新闻*/
function deleteNews(id) {
    alert("删除成功")
    $.ajax({
        type: "POST",
        url: "/admin/news/delete",
        async: false,            //不是异步
        data: {
            'id': id
        },

        success: function (data) {
            //如果取消成功，刷新页面
            if (data.code == 1) {
                window.location.reload();
            }
        }
    });

}

/*新增新闻*/
function submitNews() {
    $.ajax({
        type: "POST",
        url: "/admin/news/publish/submit",
        async: false,            //不是异步
        data: $("#newsForm").serialize(),
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.href = "/admin/news";
            }
        }
    });
}


/*提交用户反馈信息*/
function submitFeedback() {
    $.ajax({
        type: "POST",
        url: "/feedback/submit",
        async: false,            //不是异步
        data: $("#feedbackForm").serialize(),
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.href = "/";
            }
        }
    });
}

/*给回复提交的id赋值*/
function showReplyModal(id) {
    $("#feedbackId").val(id);
}

/*提交反馈回复信息*/
function feedbackReplySubmit() {

    $.ajax({
        type: "POST",
        url: "/admin/feedback/reply/submit",
        async: false,            //不是异步
        data: $("#feedbackForm").serialize(),
        success: function (data) {
            alert(data.msg)
            if (data.code == 1) {          //如果成功，刷新页面
                window.location.reload();
            }
        }
    });
}

/*删除反馈*/
function deleteFeedback(id) {
    $.ajax({
        type: "POST",
        url: "/admin/feedback/delete",
        async: false,            //不是异步
        data: {
            'id': id
        },
        success: function (data) {
            alert(data.msg);
            //如果取消成功，刷新页面
            if (data.code == 1) {
                window.location.reload();
            }
        }
    });

}

/*启用用户*/
function enableUser(id) {
    $.ajax({
        type: "POST",
        url: "/admin/user/enable",
        async: false,            //不是异步
        data: {
            'id': id
        },
        success: function (data) {
            //如果取消成功，刷新页面
            if (data.code == 1) {
                alert(data.msg)
                window.location.reload();
            }
        }
    });

}

/*禁用用户*/
function disableUser(id) {
    $.ajax({
        type: "POST",
        url: "/admin/user/disable",
        async: false,            //不是异步
        data: {
            'id': id
        },
        success: function (data) {
            //如果取消成功，刷新页面
            if (data.code == 1) {
                alert(data.msg)
                window.location.reload();
            }
        }
    });

}


/*更新密码*/
function submitPassword() {
    $.ajax({
        type: "POST",
        url: "/admin/password/submit",
        async: false,            //不是异步
        data: $("#passwordForm").serialize(),
        success: function (data) {
            alert(data.msg)
            //如果取消成功，刷新页面
            if (data.code == 1) {
                window.location.reload();
            }
        }
    });

}