package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.DogService;
import com.javaex.vo.DogVo;
import com.javaex.vo.SessionVo;
import com.javaex.vo.VisitVo;

@Controller
@RequestMapping("/pet")
public class DogController {

	@Autowired
	private DogService DogService;

	//	기본화면
	@RequestMapping("/petBasic")
	public String petBasic(Model model, HttpSession session) {
		
		SessionVo sessionVo = (SessionVo)session.getAttribute("session");
		int userNo = sessionVo.getUserNo();
		
		List<DogVo> pList = DogService.getList(userNo);
		model.addAttribute("pList", pList);
		
		List<VisitVo> rList = DogService.getrList(userNo);
		model.addAttribute("rList", rList);
		return "pet/petBasic";
	}

	//	마이펫 추가폼
	@RequestMapping("/petAdd")
	public String petAdd() {
		
		return "pet/petAdd";
	}
 
	//	마이펫 추가
	@RequestMapping("/add")
	public String add(@ModelAttribute DogVo dogVo , MultipartFile file, HttpSession session ,Model model) {
		SessionVo sessionVo = (SessionVo)session.getAttribute("session");
		
		int userNo = sessionVo.getUserNo();
		
		DogService.add(dogVo,file,userNo);
		return "redirect:/pet/petBasic";
	}	
	
	// 	마이펫 수정
	@RequestMapping("/petModify")
	public String petInfoModify(String userNo, Model model) {
		DogVo DogVo = DogService.petBasicMain("userNo");
		model.addAttribute("DogVo", DogVo);

		return userNo;
	}
	
}
