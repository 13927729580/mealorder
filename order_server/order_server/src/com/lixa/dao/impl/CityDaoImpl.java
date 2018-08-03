package com.lixa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lixa.bean.City;
import com.lixa.bean.Order;
import com.lixa.dao.CityDao;
import com.lixa.util.DBUtil;

public class CityDaoImpl implements CityDao {

	@Override
	public int addCity(City c) {

		int flag = 0;
		String sql = "insert into city (name)values(?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{c.getName()});
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			flag = 0;
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt);
		}
		
		return flag;
	
	}

	@Override
	public List<City> getAllCity() {

		List<City> list = new ArrayList<City>();
		String sql = "select * from city ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				City o = new City();
				o.setId(rs.getString("id"));
				o.setName(rs.getString("name"));
				list.add(o);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		
		return list;
	
	}

	
}
