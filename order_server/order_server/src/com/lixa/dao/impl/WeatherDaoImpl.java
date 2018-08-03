package com.lixa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lixa.bean.Weather;
import com.lixa.dao.WeatherDao;
import com.lixa.util.DBUtil;

public class WeatherDaoImpl implements WeatherDao {

	@Override
	public int addWeather(Weather w) {

		int flag = 0;
		String sql = "insert into weather (name,descp) values(?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{w.getName(),w.getDescp()});
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			flag = 0;
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt);
		}
		
		return flag;
	
	}

}
