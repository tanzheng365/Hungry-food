package com.sky.service;

import com.sky.dto.DishDTO;

/* *
 * ClassName: DishService
 * Package:com.sky.service
 * Description:
 * @Author Alan
 * @Create 2023/9/16-9:59
 * @Version 1.0
 */

public interface DishService {
    /**
     * 新增菜品以及口味表
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
