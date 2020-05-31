package com.yaya.sell.convert;

import com.yaya.sell.dataobject.ProductCategory;
import com.yaya.sell.form.CategoryForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author yaomengya
 * @date 2020/5/4
 */
@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    @Mappings({})
    ProductCategory convert(CategoryForm categoryForm);

    void updateProductCategoryFromCategoryForm(CategoryForm categoryForm, @MappingTarget ProductCategory productCategory);
}
