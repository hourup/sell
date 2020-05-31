<html>
<header>
    <title>订单列表</title>
    <#include "../common/header.ftlh">
</header>
<body>
<div id="wrapper" class="toggled">
    <#-- 边栏 sidebar -->
    <#include "../common/nav.ftlh">
    <#-- 主要内容 content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().getMassage()}</td>
                                <td>${orderDTO.getPayStatusEnum().getMessage()}</td>
                                <td>${orderDTO.createTime?string('yyyy-MM-dd hh:mm:ss')}</td>
                                <td>
                                    <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                                </td>
                                <td>
                                    <#if orderDTO.getOrderStatusEnum().getMassage() == "新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                    <#else>
                                        ${orderDTO.getOrderStatusEnum().getMassage()}
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <#-- 分页 -->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">

                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..orderDTOPage.totalPages as index>
                            <#if currentPage == index>
                                <li class="disabled"><a
                                            href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if currentPage gte orderDTOPage.totalPages>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var simpleWebSocket = null;
    if ('WebSocket' in window) {
        simpleWebSocket = new WebSocket('ws://changhr.nat100.top/sell/webSocket');
    } else {
        alert("该浏览器不支持 WebSocket！");
    }

    simpleWebSocket.onopen = function (event) {
        console.log("建立连接！");
    }

    simpleWebSocket.onclose = function (event) {
        console.log("连接关闭！");
    }

    simpleWebSocket.onmessage = function (event) {
        console.log("收到消息：" + event.data);
        // 弹窗提醒
    }

    simpleWebSocket.onerror = function () {
        alert("WebSocket 通信发生错误！");
    }

    window.onbeforeunload = function () {
        simpleWebSocket.close();
    }
</script>

</body>
</html>