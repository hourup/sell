<html>
<header>
    <title>订单详情</title>
    <#include "../common/header.ftlh">
</header>
<body>
<div id="wrapper" class="toggled">
    <#-- 边栏 sidebar -->
    <#include "../common/nav.ftlh">
    <#-- 主要内容 content -->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-6 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>订单 ID</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="success">
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>商品 ID</th>
                            <th>商品名称</th>
                            <th>商品价格</th>
                            <th>商品数量</th>
                            <th>商品总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTO.orderDetailList as detail>
                            <tr>
                                <td>${detail.productId}</td>
                                <td>${detail.productName}</td>
                                <td>${detail.productPrice}</td>
                                <td>${detail.productQuantity}</td>
                                <td>${detail.productPrice * detail.productQuantity}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <#if orderDTO.getOrderStatusEnum().getMassage() == "新订单">
                        <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}">
                            <button type="button" class="btn btn-default btn-primary">完结订单</button>
                        </a>
                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">
                            <button type="button" class="btn btn-default btn-danger">取消订单</button>
                        </a>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>