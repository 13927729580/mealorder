package com.lixa.dao;

import java.util.List;

import com.lixa.bean.OrderLog;


public interface OrderLogDao {
	
	/**
	 * 获取列表
	 */
	List<OrderLog> getAllOrderLog();
	
	/**
	 * 修改状态
	 */
	int updateOrDeleteOrderLog(String id, String opType);
	
	/**
	 * 查询但条记录
	 */
	OrderLog getOrderLogById(String id);
}
