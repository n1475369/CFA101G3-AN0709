package com.proJOINpc.model;

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

import com.product.model.ProVO;

public class ProJoinPcDAOimpl implements ProJoinPcDAO {

	//獲取DS使用連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//測試JOIN
	@Override
	public List<ProJoinPcVO> getAll(){
		//準備好 SQL list vo con pstmt rs 1.宣告2.取值3.拿來用
		java.lang.String GETALL = "select P.PRO_NAME, P.PRO_PRICE,P.PRO_CONTENT,PC.PROC_NAME from PRODUCT P JOIN PRODUCT_CATEGORY PC ON P.PRO_PROC_ID = PC.PROC_ID order by PRO_ID";
		
		List<ProJoinPcVO> list = new ArrayList<ProJoinPcVO>();
		
		ProJoinPcVO pjpcvo = null;
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		//連線開始
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			//集合集撈出每一筆
			while(rs.next()) {
				pjpcvo = new ProJoinPcVO();
				pjpcvo.setPro_name(rs.getString("pro_name"));
				pjpcvo.setPro_price(rs.getInt("pro_price"));
				pjpcvo.setPro_content(rs.getString("pro_content"));
				pjpcvo.setProc_name(rs.getString("proc_name"));
				list.add(pjpcvo);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("資料錯誤"+
			se.getMessage());
		}finally {
		if(rs != null) {
		try {
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
				}
			}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
				}
			}
		}
		return list;
	}
	
}
