package com.lixa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lixa.bean.Province;
import com.lixa.bean.User;
import com.lixa.dao.ProvinceDao;
import com.lixa.dao.UserDao;
import com.lixa.util.DBUtil;

public class ProvinceDaoImpl implements ProvinceDao {

	@Override
	public int addProvince(Province p) {

		int flag = 0;
		String sql = "insert into province(name)values(?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{p.getName()});
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
