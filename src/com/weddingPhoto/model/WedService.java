package com.weddingPhoto.model;

import java.util.List;

public class WedService {
	WedDAOImpl dao = new WedDAOImpl();
	public WedVO findByPrimaryKey(Integer wed_id) {
		return dao.findByPrimaryKey(wed_id);
	}
	
	public List<WedVO> findByForeignKey(Integer wed_wor_id){
		return dao.findByForeignKey(wed_wor_id);
	}
}
