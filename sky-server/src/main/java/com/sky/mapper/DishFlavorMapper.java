package com.sky.mapper;

/* *
 * ClassName: DishFlavorMapper
 * Package:com.sky.mapper
 * Description:
 * @Author Alan
 * @Create 2023/9/16-10:27
 * @Version 1.0
 */

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 批量插入口味味道
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品的id删除口味表
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 根据菜品集合删除菜品口味
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * 根据菜品id查询口味数据
     * @param dishId
     * @return
     */
    @Select("select * from dish_flavor where dish_id =#{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
