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
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 批量插入口味味道
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);
}
