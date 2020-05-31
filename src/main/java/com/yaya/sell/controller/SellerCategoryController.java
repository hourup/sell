package com.yaya.sell.controller;

import com.yaya.sell.convert.ProductCategoryConvert;
import com.yaya.sell.dataobject.ProductCategory;
import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.exception.SellException;
import com.yaya.sell.form.CategoryForm;
import com.yaya.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author yaomengya
 * @date 2020/5/4
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("/category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null && categoryId > 0) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            if (productCategory == null) {
                throw new SellException(ResultEnum.CATEGORY_IS_NOT_EXIST);
            }
            map.put("productCategory", productCategory);
        }
        return new ModelAndView("category/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        try {
            ProductCategory productCategory;
            if (categoryForm.getCategoryId() != null) {
                productCategory = categoryService.findOne(categoryForm.getCategoryId());
                ProductCategoryConvert.INSTANCE.updateProductCategoryFromCategoryForm(categoryForm, productCategory);
            } else {
                productCategory = ProductCategoryConvert.INSTANCE.convert(categoryForm);
            }
            categoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "保存/修改成功");
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
