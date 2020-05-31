package com.yaya.sell.service.impl;

import com.yaya.sell.dataobject.ProductInfo;
import com.yaya.sell.dto.CartDTO;
import com.yaya.sell.enums.ProductStatusEnum;
import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.exception.SellException;
import com.yaya.sell.repository.ProductInfoRepository;
import com.yaya.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author yaomengya
 * @date 2020/3/20
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productRepository.save(productInfo);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void increaseStock(List<CartDTO> cartDTOList) {
        cartDTOList.forEach(
                cartDTO -> {
                    ProductInfo productInfo = productRepository.findById(cartDTO.getProductId())
                            .orElseThrow(() -> new SellException(ResultEnum.PRODUCT_IS_NOT_EXIST));
                    int result = productInfo.getProductStock() + cartDTO.getProductQuantity();
                    productInfo.setProductStock(result);
                    productRepository.save(productInfo);
                }
        );
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void decreaseStock(List<CartDTO> cartDTOList) {
        cartDTOList.forEach(
                cartDTO -> {
                    ProductInfo productInfo = productRepository.findById(cartDTO.getProductId())
                            .orElseThrow(() -> new SellException(ResultEnum.PRODUCT_IS_NOT_EXIST));
                    int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
                    if (result < 0) {
                        throw new SellException(ResultEnum.PRODUCT_STOCK_NOT_ENOUGH);
                    }
                    productInfo.setProductStock(result);
                    productRepository.save(productInfo);
                }
        );
    }

    @Override
    public ProductInfo onSale(String productId) {

        ProductInfo productInfo = productRepository.findById(productId)
                .orElseThrow(() -> new SellException(ResultEnum.PRODUCT_IS_NOT_EXIST));

        if (productInfo.getProductStatusEnum().equals(ProductStatusEnum.UP)) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productRepository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {

        ProductInfo productInfo = productRepository.findById(productId)
                .orElseThrow(() -> new SellException(ResultEnum.PRODUCT_IS_NOT_EXIST));

        if (productInfo.getProductStatusEnum().equals(ProductStatusEnum.DOWN)) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productRepository.save(productInfo);
    }
}
