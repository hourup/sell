<html>
<header>
    <title>商品类目</title>
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
                        <form role="form" method="post" action="/sell/seller/category/save">
                            <div class="form-group">
                                <label>名称</label>
                                <input name="categoryName" type="text" class="form-control" value="${(productCategory.categoryName)!''}"/>
                            </div>
                            <div class="form-group">
                                <label>Type</label>
                                <input name="categoryType" type="number" class="form-control" value="${(productCategory.categoryType)!''}"/>
                            </div>
                            <input hidden type="number" name="categoryId" value="${(productCategory.categoryId)!''}">
                            <button type="submit" class="btn btn-default">提交</button>
                        </form>
                    </div>
                </div>
        </div>
    </div>
</div>

</body>
</html>