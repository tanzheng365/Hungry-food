package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

/* *
 * ClassName: ShoppingCartService
 * Package:com.sky.service
 * Description:
 * @Author Alan
 * @Create 2023/9/22-11:33
 * @Version 1.0
 */
public interface ShoppingCartService {

    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 展示购物车的商品
     * @return
     */
    List<ShoppingCart> showShoppingCart();

    /**
     * 清空购物车所有商品
     * @return
     */
    Object clean();

    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
