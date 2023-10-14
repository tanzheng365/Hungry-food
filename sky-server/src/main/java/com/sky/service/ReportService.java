package com.sky.service;

/* *
 * ClassName: ReportService
 * Package:com.sky.service.impl
 * Description:
 * @Author Alan
 * @Create 2023/10/14-16:37
 * @Version 1.0
 */


import com.sky.vo.TurnoverReportVO;
import java.time.LocalDate;

public interface ReportService {

    /**
     * 根据时间区间统计营业额
     * @param beginTime
     * @param endTime
     * @return
     */
    TurnoverReportVO getTurnover(LocalDate beginTime, LocalDate endTime);
}