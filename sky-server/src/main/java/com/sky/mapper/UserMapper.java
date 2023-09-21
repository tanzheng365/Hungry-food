package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/* *
 * ClassName: UserMapper
 * Package:com.sky.mapper
 * Description:
 * @Author Alan
 * @Create 2023/9/21-17:00
 * @Version 1.0
 */
@Mapper

public interface UserMapper {

    /**
     * 根据openid查询数据
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 插入数据
     * @param user
     */
    void insert(User user);
}
