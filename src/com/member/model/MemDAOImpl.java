package com.member.model;

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

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MemDAOImpl implements MemDAO{
	private static final String INSERT = "INSERT INTO member (mem_username, mem_password, mem_name, mem_role, mem_phone, mem_city, mem_cityarea, mem_street, mem_shop_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ONE = "SELECT * FROM member WHERE mem_id = ?";
	private static final String GET_ONE_USERNAME_PASSWORD = "SELECT * FROM member WHERE mem_username = ? AND mem_password = ?";
	private static final String GET_ONE_USERNAME = "SELECT * FROM member WHERE mem_username = ?";
	private static final String UPDATE_EMAILSTATUS = "UPDATE member SET mem_status = 1 WHERE mem_username = ?";
	private static final String UPDATE_PASSWORD = "UPDATE member SET mem_password = ? WHERE mem_username = ?";
	private static final String GET_ALL_BUYMEMBER = "SELECT * FROM member WHERE mem_role = 10";
	private static final String UPDATE_BUYMEMBER = "UPDATE member SET mem_name = ?,mem_phone = ?,mem_status = ? WHERE mem_id = ?";
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	JdbcTemplate template = new JdbcTemplate(ds);
	
	//驗證帳密是否存在
	@Override
	public MemVO findByUsernameAndPassword(String username,String password) {
		MemVO member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			 con = ds.getConnection();
			 pstmt = con.prepareStatement(GET_ONE_USERNAME_PASSWORD);
			 pstmt.setString(1, username);
			 pstmt.setString(2, password);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 member = new MemVO();
				 member.setMem_id(rs.getInt("mem_id"));
				 member.setMem_username(rs.getString("mem_username"));
				 member.setMem_name(rs.getString("mem_name"));
				 member.setMem_role(rs.getInt("mem_role"));
				 member.setMem_phone(rs.getString("mem_phone"));
				 member.setMem_city(rs.getString("mem_city"));
				 member.setMem_cityarea(rs.getString("mem_cityarea"));
				 member.setMem_street(rs.getString("mem_street"));
				 member.setMem_status(rs.getInt("mem_status"));
				 member.setMem_shop_name(rs.getString("mem_shop_name"));
				 member.setMem_shop_content(rs.getString("mem_shop_content"));
				 member.setMem_shop_logo(rs.getBytes("mem_shop_logo"));
				 member.setMem_shop_banner(rs.getBytes("mem_shop_banner"));
				 member.setMem_shop_status(rs.getInt("mem_shop_status"));
				 member.setMem_headshot(rs.getBytes("mem_headshot"));
				 member.setMem_review_count(rs.getInt("mem_review_count"));
				 member.setMem_review_score(rs.getInt("mem_review_score"));
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
		return member;
	}
	
	//驗證帳號是否存在
	@Override
	public MemVO findByUsername(String username) {
		MemVO member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_USERNAME);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new MemVO();
				member.setMem_id(rs.getInt("mem_id"));
				member.setMem_username(rs.getString("mem_username"));
				member.setMem_password(rs.getString("mem_password"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_role(rs.getInt("mem_role"));
				member.setMem_phone(rs.getString("mem_phone"));
				member.setMem_city(rs.getString("mem_city"));
				member.setMem_cityarea(rs.getString("mem_cityarea"));
				member.setMem_street(rs.getString("mem_street"));
				member.setMem_status(rs.getInt("mem_status"));
				member.setMem_shop_name(rs.getString("mem_shop_name"));
				member.setMem_shop_content(rs.getString("mem_shop_content"));
				member.setMem_shop_logo(rs.getBytes("mem_shop_logo"));
				member.setMem_shop_banner(rs.getBytes("mem_shop_banner"));
				member.setMem_shop_status(rs.getInt("mem_shop_status"));
				member.setMem_headshot(rs.getBytes("mem_headshot"));
				member.setMem_review_count(rs.getInt("mem_review_count"));
				member.setMem_review_score(rs.getInt("mem_review_score"));
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
		return member;
	}
	
	//註冊會員
	@Override
	public int insert(MemVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, member.getMem_username());
			pstmt.setString(2, member.getMem_password());
			pstmt.setString(3, member.getMem_name());
			pstmt.setInt(4, member.getMem_role());
			pstmt.setString(5, member.getMem_phone());
			pstmt.setString(6, member.getMem_city());
			pstmt.setString(7, member.getMem_cityarea());
			pstmt.setString(8, member.getMem_street());
			pstmt.setString(9, member.getMem_shop_name());
			int executeUpdate = pstmt.executeUpdate();
			return executeUpdate;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			if(pstmt != null)	{
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
	}
	
	//更新會員信箱驗證狀態
	@Override
	public void updateEmailStatus(String username) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EMAILSTATUS);
			pstmt.setString(1, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
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

	}
	
	//更新買家會員個人資料
	@Override
	public void updateBuyProfile(MemVO member) {
		String sql = "update member set mem_name = ?, mem_phone = ?, mem_city = ?, mem_cityarea = ?, mem_street = ? where mem_username = ?";
		template.update(sql,member.getMem_name(),member.getMem_phone(),member.getMem_city(),member.getMem_cityarea(),member.getMem_street(),member.getMem_username());
	}
	
	//更新買家會員個人頭像
	@Override
	public void updateBuyHeadshot(MemVO member) {
		String sql = "update member set mem_headshot = ? where mem_username = ?";
		template.update(sql,member.getMem_headshot(),member.getMem_username());
	}
	
	//更新會員密碼
	@Override
	public void updatePassword(MemVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PASSWORD);
			pstmt.setString(1, member.getMem_password());
			pstmt.setString(2, member.getMem_username());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
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
		
	}
	//獲取所有買家會員
	@Override
	public List<MemVO> getAllByBuyMember() {
		List<MemVO> list = new ArrayList();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BUYMEMBER);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_username(rs.getString("mem_username"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_city(rs.getString("mem_city"));
				memVO.setMem_cityarea(rs.getString("mem_cityarea"));
				memVO.setMem_street(rs.getString("mem_street"));
				memVO.setMem_status(rs.getInt("mem_status"));
				list.add(memVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	//分頁顯示返回買家會員list集合 start:從哪頁開始查詢 rowsPerPage:每次查幾筆
	@Override
	public List<MemVO> findBuyMemberByPagination(int start, int rowsPerPage,String find_username,String find_name,String find_status) {
		String sql = "select * from member where mem_role = 10";
		StringBuilder sb = new StringBuilder();
		sb.append(sql);
		if(find_username != null && !find_username.trim().isEmpty()) {
			sb.append(" and mem_username like '%"+find_username.trim()+"%'");
		}
		if(find_name != null && !find_name.trim().isEmpty()) {
			sb.append(" and mem_name like '%"+find_name.trim()+"%'");
		}
		if(find_status != null && !find_status.trim().isEmpty()) {
			sb.append(" and mem_status = "+find_status.trim());
		}
		sb.append(" limit ?,?");
		sql = sb.toString();
		
		List<MemVO> list = new ArrayList();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, rowsPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_username(rs.getString("mem_username"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_city(rs.getString("mem_city"));
				memVO.setMem_cityarea(rs.getString("mem_cityarea"));
				memVO.setMem_street(rs.getString("mem_street"));
				memVO.setMem_status(rs.getInt("mem_status"));
				list.add(memVO);
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
	
	//獲取個人會員資料
	@Override
	public MemVO getOne(Integer mem_id) {
		MemVO member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, mem_id);
			rs = pstmt.executeQuery();
			 while(rs.next()) {
				 member = new MemVO();
				 member.setMem_id(rs.getInt("mem_id"));
				 member.setMem_username(rs.getString("mem_username"));
				 member.setMem_name(rs.getString("mem_name"));
				 member.setMem_role(rs.getInt("mem_role"));
				 member.setMem_phone(rs.getString("mem_phone"));
				 member.setMem_city(rs.getString("mem_city"));
				 member.setMem_cityarea(rs.getString("mem_cityarea"));
				 member.setMem_street(rs.getString("mem_street"));
				 member.setMem_status(rs.getInt("mem_status"));
				 member.setMem_shop_name(rs.getString("mem_shop_name"));
				 member.setMem_shop_content(rs.getString("mem_shop_content"));
				 member.setMem_shop_logo(rs.getBytes("mem_shop_logo"));
				 member.setMem_shop_banner(rs.getBytes("mem_shop_banner"));
				 member.setMem_shop_status(rs.getInt("mem_shop_status"));
				 member.setMem_headshot(rs.getBytes("mem_headshot"));
				 member.setMem_review_count(rs.getInt("mem_review_count"));
				 member.setMem_review_score(rs.getInt("mem_review_score"));
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
		return member;
	}
	
	//管理員修改買家會員資料
	@Override
	public void updateBuyMember(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BUYMEMBER);
			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_phone());
			pstmt.setInt(3, memVO.getMem_status());
			pstmt.setInt(4, memVO.getMem_id());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
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
		
	}
	//分頁顯示返回多重查詢總筆數
	@Override
	public int getBuyMemberRowNumber(String find_username, String find_name, String find_status) {
		String sql = "select count(*) from member where mem_role = 10";
		StringBuilder sb = new StringBuilder();
		sb.append(sql);
		if(find_username != null && !find_username.trim().isEmpty()) {
			sb.append(" and mem_username like '%"+find_username.trim()+"%'");
		}
		if(find_name != null && !find_name.trim().isEmpty()) {
			sb.append(" and mem_name like '%"+find_name.trim()+"%'");
		}
		if(find_status != null && !find_status.trim().isEmpty()) {
			sb.append(" and mem_status = "+find_status.trim());
		}
		sql = sb.toString();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
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
		return count;
	}
}


