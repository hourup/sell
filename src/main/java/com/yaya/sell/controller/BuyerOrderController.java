package com.yaya.sell.controller;

import com.yaya.sell.convert.OrderFormConvert;
import com.yaya.sell.dto.OrderDTO;
import com.yaya.sell.enums.OrderStatusEnum;
import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.exception.SellException;
import com.yaya.sell.form.OrderForm;
import com.yaya.sell.service.BuyerService;
import com.yaya.sell.service.OrderService;
import com.yaya.sell.utils.ResultUtil;
import com.yaya.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author changhr2013
 * @date 2020/3/22
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     *
     * @param orderForm     orderForm
     * @param bindingResult bindingResult
     * @return Map<String, String>
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，orderForm=[{}]", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderFormConvert.INSTANCE.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单]购物车不能为空，orderForm=[{}]", orderForm);
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO resultDTO = orderService.create(orderDTO);
        Map<String, String> data = new HashMap<>(1);
        data.put("orderId", resultDTO.getOrderId());

        return ResultUtil.success(data);
    }

    /**
     * 订单列表
     *
     * @param openid 用户 openid
     * @param page   page，页数索引
     * @param size   size，每页数量
     * @return List<OrderDTO>
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("[查询订单列表]openid 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
        return ResultUtil.success(orderDTOPage.getContent());
    }

    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultUtil.success(orderDTO);
    }

    @PostMapping("/cancel")
    public ResultVO<String> cancel(@RequestParam("openid") String openid,
                                   @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultUtil.success();
    }
}
