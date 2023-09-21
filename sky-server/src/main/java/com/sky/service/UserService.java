package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/* *
 * ClassName: UserService
 * Package:com.sky.service
 * Description:
 * @Author Alan
 * @Create 2023/9/21-16:24
 * @Version 1.0
 */
public interface UserService {
   //登陆方法，并且返回登陆方法

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin (UserLoginDTO userLoginDTO);
}
