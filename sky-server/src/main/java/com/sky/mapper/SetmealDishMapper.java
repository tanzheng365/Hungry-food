package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/* *
 * ClassName: SetmealDishMapper
 * Package:com.sky.mapper
 * Description:
 * @Author Alan
 * @Create 2023/9/16-17:45
 * @Version 1.0
 */
@Mapper
public interface SetmealDishMapper{

    /**
     * 根据菜品id来查询对应的套餐id
     * @param dishIds
     * @return
     */
    //select setmeal_id from setmeal_dish where dish in (1,2,3)
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
}
