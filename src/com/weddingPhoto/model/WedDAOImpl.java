package com.weddingPhoto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class WedDAOImpl implements WedDAO{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ONE = "select * from WEDDING_PHOTO where WED_ID = ?";
	private static final String GET_REFERENCE = "select * from WEDDING_PHOTO where WED_WOR_ID = ?";
	
	
	@Override
	public WedVO findByPrimaryKey(Integer wed_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WedVO wedVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, wed_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				wedVO = new WedVO();
				wedVO.setWed_id(rs.getInt("wed_id"));
				wedVO.setWed_wor_id(rs.getInt("wed_wor_id"));
				wedVO.setWed_images(rs.getBytes("wed_images"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return wedVO;
	}



	@Override
	public List<WedVO> findByForeignKey(Integer wed_wor_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WedVO wedVO = null;
		List<WedVO> list = new ArrayList<WedVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_REFERENCE);
			pstmt.setInt(1, wed_wor_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				wedVO = new WedVO();
				wedVO.setWed_id(rs.getInt("wed_id"));
				wedVO.setWed_wor_id(rs.getInt("wed_wor_id"));
				list.add(wedVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	
}
