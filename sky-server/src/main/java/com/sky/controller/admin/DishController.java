package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.List;
import java.util.Set;

/* *
 * ClassName: DishController
 * Package:com.sky.controller.admin
 * Description:
 * @Author Alan
 * @Create 2023/9/16-9:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j

public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);

        dishService.saveWithFlavor(dishDTO);

        //清理缓存数据
        String key = "dish-" + dishDTO.getCategoryId();
        cleanCache(key);
        return Result.success();


//        //清除指定缓存数据
//        String key = "dish" + dishDTO.getCategoryId();
//        //清楚redis缓存数据
//        cleanCache(key);
//        return Result.success();

    }

    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批菜品量删除
     *
     * @param ids
     * @return
     */

    @DeleteMapping()
    @ApiOperation("菜品批量删除")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("菜品批量删除{}", ids);
        dishService.deleteBatch(ids)
        ;
        //清楚redis缓存数据
        cleanCache("dish-*");
        return Result.success();

    }

    /**
     * 根据id获取菜品
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id获取菜品以及口味信息")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * x修改菜品
     *
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品：{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        //将所有的菜品缓存数据清理掉，所有dish_开头的都删除
        //清楚redis缓存数据
        cleanCache("dish-");

        return Result.success();
    }

    /**
     * 菜品起售停售
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售停售")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        dishService.startOrStop(status, id);
        //将所有的菜品缓存数据清理掉，所有dish_开头的都删除
        //清楚redis缓存数据
        cleanCache("*dish-*");
        return Result.success();
    }


    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId) {
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }


    /**
     * 统一清除缓存数据
     *
     * @param pattern
     */
    private void cleanCache(String pattern) {
        //将所有的菜品缓存数据清理掉，所有dish_开头的都删除
        Set keys = redisTemplate.keys(pattern);//先获取所有下划线都是dish_开头的
        redisTemplate.delete(keys);

    }

}
