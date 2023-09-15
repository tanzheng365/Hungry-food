package com.sky.aspect;

/* *
 * ClassName: AutoFillAspect
 * Package:com.sky.aspect
 * Description:自定义切面类，实现公共字段填充的处理逻辑
 * @Author Alan
 * @Create 2023/9/15-15:56
 * @Version 1.0
 */

import com.fasterxml.jackson.databind.JsonSerializable;
import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component//切面类也是一个bean，要交给容器统一管理
@Slf4j
public class AutoFillAspect {

    //定义切入点（对那些类的那些方法进行拦截的定义）
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    //拦截到了com.sky.mapper所有的类和所有的方法,接口同样会被匹配到,&&同时满足要再annotation.AutoFill里面的
    public  void autoFillPointCut(){

    }
    /*
    通知类型：
        前置通知
        后置通知
        环绕通知
        异常通知
     */
        //在这里使用前置通知,在通知中进行公共字段赋值
    @Before("autoFillPointCut()")//@Before("切入点——》方法名称")
    public void autoFill(JoinPoint joinPoint){
        log.info("开始进行公共字段的自动填充...");

        //1.获取当前拦截的数据库语句类型
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();//获取方法签名对象，向下转型
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获取方法上的注解对象？？
        OperationType operationType = autoFill.value();//获得数据库操作类型
        //2.获取方法参数----实体对象
        Object[] args = joinPoint.getArgs();//获取的了方法的所有参数
        //防止出现空指针
        if (args == null || args.length ==0){
            return;
        }
        Object entity = args[0];    //获得了实体对象entity

        //3.准备为要赋值的对象的数据  time username
        LocalDateTime localDateTime = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        //4.根据当前不同的操作类型，为对应的属性通过反射来赋值
        if (operationType==OperationType.INSERT){
            //为四个公共字段赋值
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                //通过反射为对象属性赋值
                setCreateTime.invoke(entity,localDateTime);
                setCreateUser.invoke(entity,currentId);
                setUpdateTime.invoke(entity,localDateTime);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //为两个公共字段赋值
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(entity,localDateTime);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
