package com.yaya.sell.controller;

import com.yaya.sell.convert.ProductInfoConvert;
import com.yaya.sell.dataobject.ProductCategory;
import com.yaya.sell.dataobject.ProductInfo;
import com.yaya.sell.service.CategoryService;
import com.yaya.sell.service.ProductService;
import com.yaya.sell.utils.ResultUtil;
import com.yaya.sell.vo.ProductVO;
import com.yaya.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品信息控制器
 * @author yaomengya
 * @date 2020/3/20
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO<List<ProductVO>> list() {

        // 查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 抽取所有上架商品的商品类型
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        // 查询所有的商品类型详情
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 按商品类型分类商品，组装返回的视图数据对象
        List<ProductVO> productVO = productCategoryList.stream()
                .map(
                        productCategory -> new ProductVO()
                                .setCategoryName(productCategory.getCategoryName())
                                .setCategoryType(productCategory.getCategoryType())
                                .setProductInfoVOList(ProductInfoConvert.INSTANCE.convert(productInfoList.stream()
                                        .filter(productInfo -> productInfo.getCategoryType().equals(productCategory.getCategoryType()))
                                        .collect(Collectors.toList())))
                )
                .collect(Collectors.toList());

        return ResultUtil.success(productVO);
    }
}
