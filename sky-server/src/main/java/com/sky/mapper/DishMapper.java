package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜品数据
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)//公共字段自动填充
    void insert(Dish dish);


    /**
     * 菜品的分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuary(DishPageQueryDTO dishPageQueryDTO);

    /***
     * 菜品批量删除
     * @param ids
     */
    @Delete("delete from dish where id= (#{ids})")
    void deleteBatch(List<Long> ids);

    /**
     * 根据id获取菜品
     * @param id
     * @return
     */
    @Select("select * from dish where id= #{id} ")
    Dish getById(Long id);

    /**
     * 根据id删除菜品
     * @param id
     */
    @Delete("delete from dish where id= #{id}")
    void deleteById(Long id);

    /**
     * 根据要删除的菜品集合批量删除菜品数据
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 修改菜品的基本信息
     * @param dish
     */
//    所有的修改操作都需要加上修改数据--》自动填充
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);
}
