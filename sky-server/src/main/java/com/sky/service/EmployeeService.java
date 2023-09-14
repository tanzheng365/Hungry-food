package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);


    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);


    /**
     * 员工列表分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用停用员工账号
     * @param status
     * @param id
     */
    void starOrStop(Integer status, long id);

    /**
     * 根据员工Id获取信息
     * @param id
     * @return
     */
    Employee getById(long id);

    void update(EmployeeDTO employeeDTO);
}
