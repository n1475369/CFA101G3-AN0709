package com.weddingPhoto.model;

import java.util.List;

public interface WedDAO {
	public WedVO findByPrimaryKey(Integer wed_id);
	public List<WedVO> findByForeignKey(Integer wed_wor_id);
}
