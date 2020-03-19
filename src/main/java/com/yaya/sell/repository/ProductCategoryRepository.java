package com.yaya.sell.repository;

import com.yaya.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author changhr2013
 * @date 2020/3/17
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
