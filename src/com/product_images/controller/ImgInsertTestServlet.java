package com.product_images.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProDAOimpl;
import com.product.model.ProVO;
import com.product_images.model.ProImgDAO;
import com.product_images.model.ProImgDAOimpl;
import com.product_images.model.ProImgVO;

@MultipartConfig
@WebServlet("/ImgInsertTestServlet")
public class ImgInsertTestServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Part> parts = (ArrayList)request.getParts();
		
		ProImgDAO Imgdao = new ProImgDAOimpl();
		ProDAOimpl proDao = new ProDAOimpl();
		List<ProVO> all = proDao.getAll();
		for (int i = 0; i < parts.size(); i++) {
			byte[] buf = null;
			InputStream in = parts.get(i).getInputStream();
			ProImgVO proImgVO = new ProImgVO();
			//匯入圖片
			if(in.available() !=0) {
				buf =new byte[in.available()];
				in.read(buf);
				in.close();
				ProVO proVO = all.get(i);//需自行設定商品起始ID
				System.out.println(i);
				System.out.println(proVO);
				proImgVO.setProi_pro_id(proVO.getPro_id());
				proImgVO.setProi_images(buf);
				Imgdao.insertProImg(proImgVO);
			}
		}
			
			



			
		
			

	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
