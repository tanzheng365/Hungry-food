package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

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

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /***
     * 菜品批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
