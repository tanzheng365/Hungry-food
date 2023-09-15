package com.sky.annotation;

/* *
 * ClassName: AutoFill
 * Package:com.sky.annotation
 * Description:自定义注解，用于标识每个方法需要进行功能字段自动填充处理
 * @Author Alan
 * @Create 2023/9/15-15:51
 * @Version 1.0
 */

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//表示当前注解加在方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    //指定数据库操作类型 ：update，insert
    OperationType value();

}
