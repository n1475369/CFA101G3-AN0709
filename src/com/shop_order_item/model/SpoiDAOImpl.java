package com.shop_order_item.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SpoiDAOImpl implements SpoiDAO{
	
	private static final String INSERT = "insert into SHOP_ORDER_ITEM (spo_id,pro_id,spoi_quantity,spoi_totalprice) values (?,?,?,?)";
	
	//新增商品訂單明細(產生商品訂單時用)
	@Override
	public void insertByShopOrder(SpoiVO spoiVO, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, spoiVO.getSpo_id());
			pstmt.setInt(2, spoiVO.getProvo().getPro_id());
			pstmt.setInt(3, spoiVO.getSpoi_quantity());
			pstmt.setInt(4, spoiVO.getSpoi_totalprice());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if(con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
