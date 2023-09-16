package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/* *
 * ClassName: DishServiceImpl
 * Package:com.sky.service.impl
 * Description:
 * @Author Alan
 * @Create 2023/9/16-10:07
 * @Version 1.0
 */
@Service
@Slf4j
public class DishServiceImpl  implements DishService {

    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private DishMapper dishMapper;
    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */

    /*
    此处涉及到口味表的修改，需要添加事务注解，
    在启动类去要开启注解方式的启动类开启
     */
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
//        dishDTO中包含口味数据，通过对象的属性拷贝（前提条件：属性名相同）创建新的类
        BeanUtils.copyProperties(dishDTO,dish);

        //菜品表插入一条数据
        dishMapper.insert(dish);

        //获取insert语句生成的主键值
        Long dishId = dish.getId();

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors !=null && flavors.size()>0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
        //向口味表插入多个数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }
}
