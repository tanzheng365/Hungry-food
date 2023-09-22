package com.sky.mapper;

import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/* *
 * ClassName: OrderDetailMapper
 * Package:com.sky.mapper
 * Description:
 * @Author Alan
 * @Create 2023/9/22-17:22
 * @Version 1.0
 */
@Mapper
public interface OrderDetailMapper {

    /**
     * 批量插入订单明细数据
     * @param orderDetailList
     */
    void insertBatch(List<OrderDetail> orderDetailList);
}
