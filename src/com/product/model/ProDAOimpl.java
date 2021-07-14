package com.product.model;

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




public class ProDAOimpl implements ProDAO {

//	SQL指令：
//	private static final java.lang.String INSERT= "INSERT INTO PRODUCT(PRO_ID,PRO_NAME,PRO_PRICE,PRO_CONTENT,PRO_SMEM_ID,PRO_PROC_ID,PRO_STATUS) VALUES(?, ?, ?, ?, ?, ?, ?)";
//	private static final String UPDATE_STMT = "UPDATE USER SET NAME = ?, GENDER = ?, AGE = ?, ADDRESS = ?, LINE = ?, EMAIL =? WHERE ID = ?";
//	private static final java.lang.String FIND_BY_PK = "SELECT * FROM PRODUCT WHERE ID = ?";
//	private static final java.lang.String GET_ALL = "SELECT * FROM PRODUCT";
//	private static final String FIND_USERS = "SELECT * FROM USER WHERE NAME = ?";
//  方便複製用
//	private Integer pro_id;
//	private String pro_name;
//	private Integer pro_price;
//	private String pro_content;
//	private Integer pro_smem_id;
//	private Integer pro_proc_id;
//	private Integer pro_status;
	
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
//	JdbcTemplate template = new JdbcTemplate(ds); 不能用拉QQ
	
	//查詢商品
	@Override
	public List<ProVO> getAll(){
		//準備好 SQL list vo con pstmt rs 1.宣告2.取值3.拿來用
		java.lang.String GETALL = "SELECT * FROM PRODUCT";
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO proVO = null;
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
				proVO = new ProVO();
				proVO.setPro_id(rs.getInt("pro_id"));
				proVO.setPro_name(rs.getString("pro_name"));
				proVO.setPro_price(rs.getInt("pro_price"));
				proVO.setPro_content(rs.getString("pro_content"));
				proVO.setPro_smem_id(rs.getInt("pro_smem_id"));
				proVO.setPro_proc_id(rs.getInt("pro_proc_id"));
				proVO.setPro_status(rs.getInt("pro_status"));
				list.add(proVO);
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
	//商品上架
	@Override
	public void insert(ProVO proVO) {
		
		//準備好 SQL con pstmt 1.宣告2.取值3.拿來用
		java.lang.String INSERT= "INSERT INTO PRODUCT(PRO_SMEM_ID, PRO_NAME,PRO_PRICE,PRO_CONTENT,PRO_PROC_ID,PRO_STATUS) VALUES(?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		//連線開始
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
//			pstmt.setInt(1,proVO.getPro_id());
			pstmt.setInt(1,proVO.getPro_smem_id());
			pstmt.setString(2,proVO.getPro_name());
			pstmt.setInt(3,proVO.getPro_price());
			pstmt.setString(4,proVO.getPro_content());
			pstmt.setInt(5,proVO.getPro_proc_id());
			pstmt.setInt(6,proVO.getPro_status());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("資料錯誤"+
			se.getMessage());
		}finally {
			
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
	}

	
	//商品下架
	@Override
	public void delete(Integer pro_id) {
		//準備好 SQL con pstmt 1.宣告2.取值3.拿來用
		java.lang.String DELETE ="DELETE  FROM PRODUCT WHERE PRO_ID = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		//連線開始
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1,pro_id);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("資料錯誤"+
			se.getMessage());
		}finally {
			
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
	}
	//更改商品
	public void update(ProVO proVO) {
		//準備好 SQL con pstmt 1.宣告2.取值3.拿來用
		java.lang.String UPDATE= "UPDATE PRODUCT SET PRO_NAME = ? ,PRO_PRICE = ? ,PRO_CONTENT = ? ,PRO_SMEM_ID = ? ,PRO_PROC_ID = ? ,PRO_STATUS = ? WHERE PRO_ID = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		//開始連線
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
		//set資料
			pstmt.setString(1,proVO.getPro_name());
			pstmt.setInt(2,proVO.getPro_price());
			pstmt.setString(3,proVO.getPro_content());
			pstmt.setInt(4,proVO.getPro_smem_id());
			pstmt.setInt(5,proVO.getPro_proc_id());
			pstmt.setInt(6,proVO.getPro_status());
			pstmt.setInt(7,proVO.getPro_id());
			pstmt.executeUpdate();	
			
			
		} catch (SQLException se) {
			throw new RuntimeException("資料錯誤"+
			se.getMessage());
		}finally {
			
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
	}
	//搜尋單筆商品PK
	@Override
	public ProVO findByPK(Integer pro_id) {
		//準備好 SQL list vo con pstmt rs 1.宣告2.取值3.拿來用
		java.lang.String FIND_BY_PK ="SELECT * FROM PRODUCT WHERE PRO_ID = ?";
		ProVO proVO =null;
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		//連線開始
		try {
			con = ds.getConnection();
			pstmt = con.prepareCall(FIND_BY_PK);
			pstmt.setInt(1, pro_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				proVO = new ProVO();
				proVO.setPro_id(rs.getInt("pro_id"));
				proVO.setPro_name(rs.getString("pro_name"));
				proVO.setPro_price(rs.getInt("pro_price"));
				proVO.setPro_content(rs.getString("pro_content"));
				proVO.setPro_smem_id(rs.getInt("pro_smem_id"));
				proVO.setPro_proc_id(rs.getInt("pro_proc_id"));
				proVO.setPro_status(rs.getInt("pro_status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return proVO;
	};
	//根據會員店家ID尋找商品
	@Override
	public List<ProVO> findBySeller(Integer pro_smem_id) {
	//準備好 SQL list vo con pstmt rs 1.宣告2.取值3.拿來用
	java.lang.String FIND_BY_PK_SELLER ="SELECT * FROM PRODUCT WHERE PRO_SMEM_ID = ?";
	List<ProVO> list = new ArrayList<ProVO>();
	ProVO proVO =null;
	Connection con =null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;
	//連線開始
	try {
		con = ds.getConnection();
		pstmt = con.prepareCall(FIND_BY_PK_SELLER);
		pstmt.setInt(1, pro_smem_id);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			proVO = new ProVO();
			proVO.setPro_id(rs.getInt("pro_id"));
			proVO.setPro_name(rs.getString("pro_name"));
			proVO.setPro_price(rs.getInt("pro_price"));
			proVO.setPro_content(rs.getString("pro_content"));
			proVO.setPro_smem_id(rs.getInt("pro_smem_id"));
			proVO.setPro_proc_id(rs.getInt("pro_proc_id"));
			proVO.setPro_status(rs.getInt("pro_status"));
			list.add(proVO);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	return list;
};
	
	
}