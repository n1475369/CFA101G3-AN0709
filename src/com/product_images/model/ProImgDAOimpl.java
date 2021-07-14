package com.product_images.model;

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

public class ProImgDAOimpl implements ProImgDAO{

	//使用連線池建立連線
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String  FINDBYPK ="select * from product_images where proi_id = ?";
	private static final String  FINDBYFK ="select * from product_images where proi_pro_id = ?";
	
//	private Integer proi_id;
//	private Integer proi_pro_id;
//	private byte[] proi_images;
	//FINDBYPK
	@Override
	public ProImgVO findByPrimaryKey(Integer proi_id) {
		//備料
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProImgVO proimgVO = null;
		//開始連線
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYPK);
			pstmt.setInt(1, proi_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				proimgVO = new ProImgVO();
				proimgVO.setProi_id(rs.getInt("proi_id"));
				proimgVO.setProi_pro_id(rs.getInt("proi_pro_id"));
				proimgVO.setProi_images(rs.getBytes("proi_images"));
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
		return proimgVO;
	}

	@Override
	public List<ProImgVO> findByForeignKey(Integer proi_pro_id) {
		//備料
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProImgVO proimgVO = null;
		List<ProImgVO> list = new ArrayList<ProImgVO>();
		//開始連線
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYFK);
			pstmt.setInt(1, proi_pro_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				proimgVO = new ProImgVO();
				proimgVO.setProi_id(rs.getInt("proi_id"));
				proimgVO.setProi_pro_id(rs.getInt("proi_pro_id"));
				list.add(proimgVO);
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

	//主頁秀圖
	@Override
	public List<ProImgVO> getall(){
		java.lang.String GETALL = "SELECT * FROM PRODUCT_IMAGES";
		List<ProImgVO> list = new ArrayList<ProImgVO>();
		ProImgVO proImgVO = null;
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
				proImgVO = new ProImgVO();
				proImgVO.setProi_id(rs.getInt("proi_id"));
				proImgVO.setProi_pro_id(rs.getInt("proi_pro_id"));
				proImgVO.setProi_images(rs.getBytes("proi_images"));
				list.add(proImgVO);
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
	
	//新增圖片
	@Override
	public void insertProImg(ProImgVO proImgVO) {
		
		String ADDIMG ="INSERT INTO PRODUCT_IMAGES (PROI_PRO_ID, PROI_IMAGES) VALUES(?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ADDIMG);
			
			pstmt.setInt(1, proImgVO.getProi_pro_id());
			pstmt.setBytes(2, proImgVO.getProi_images());
			pstmt.executeUpdate();
			
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	}
	//商品秀圖
	@Override
	public List<ProImgVO> findByFKlist(Integer proi_pro_id) {
	//準備好 SQL list vo con pstmt rs 1.宣告2.取值3.拿來用
			String FINDBYFKLIST ="SELECT * FROM PRODUCT_IMAGES WHERE PROI_PRO_ID = ?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ProImgVO proImgVO = null;
			List<ProImgVO> list = new ArrayList<ProImgVO>();
			
			//開始連線
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(FINDBYFKLIST);
				pstmt.setInt(1, proi_pro_id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					proImgVO = new ProImgVO();
					proImgVO.setProi_id(rs.getInt("Proi_id"));
					proImgVO.setProi_pro_id(rs.getInt("proi_pro_id"));
					proImgVO.setProi_images(rs.getBytes("proi_images"));
					list.add(proImgVO);
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
	@Override
	public List<ProImgVO> findByCateList(Integer pro_proc_id){
		String FINDBYCATELIST ="SELECT MIN(PROI_ID),PROI_PRO_ID FROM PRODUCT_IMAGES join PRODUCT ON PROI_PRO_ID = PRO_ID WHERE PRO_PROC_ID = ? GROUP BY PROI_PRO_ID";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProImgVO proImgVO = null;
		List<ProImgVO> list = new ArrayList<ProImgVO>();
		
		//開始連線
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYCATELIST);
			pstmt.setInt(1, pro_proc_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				proImgVO = new ProImgVO();
				proImgVO.setProi_id(rs.getInt("MIN(PROI_ID)"));
				proImgVO.setProi_pro_id(rs.getInt("proi_pro_id"));
				list.add(proImgVO);
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
	





