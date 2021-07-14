package com.product_images.model;

import java.util.List;

public class ProImgService {

	ProImgDAO dao = new ProImgDAOimpl(); 
	
//	public void insertProImg(ProImgVO proImgVO) {
//		
//	ProImgVO vo = new ProImgVO();
//	vo.setProi_pro_id(proi_pro_id);
//	
//}
	
	public ProImgVO findByPrimaryKey(Integer proi_id) {
		return dao.findByPrimaryKey(proi_id);
	}
	
	public List<ProImgVO> findByForeignKey(Integer proi_pro_id){
		return dao.findByForeignKey(proi_pro_id);
	}
	
	public List<ProImgVO> findByCateList(Integer pro_proc_id){
		return dao.findByCateList(pro_proc_id);
	}
	
	
}
