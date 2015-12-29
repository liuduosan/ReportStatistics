package com.dc.assess.validate.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dc.assess.validate.service.ValidateUserService;

@Controller
@RequestMapping(value="/validate")
public class ValidateUserController {

	@Autowired
	private ValidateUserService validateUserService;
	@RequestMapping(value="/login")
	public ModelAndView validateUser(HttpServletRequest request,HttpServletResponse response){
		String itcode = "liutaoq";
		ModelAndView mav = new ModelAndView();
		if(itcode==null || itcode.equals("")){
			try {
				response.sendRedirect("http://dcone.digitalchina.com/dcoa7/DCPerformance2015/DigiFlowAssessRelation.nsf/DCPFrame?OpenForm");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("没有登录");
		}else{
			if(!validateUserService.validateItCode(itcode)){
				mav.setViewName("/login/error");
				System.out.println("没有权限");
			}else{
				//判断是否是系统管理员
				List<Map<java.lang.String, Object>>  adminlist =validateUserService.getItcodeAdministrators(itcode);
				String admin=adminlist.size()+"";
				Cookie cookie = new Cookie("admin", admin );
				cookie.setMaxAge(-1);
				cookie.setPath("/");
				response.addCookie(cookie);
				mav.setViewName("/processrhythm/processrhythm");
				System.out.println("登陆成功");
			}
		}
		return mav;
	}
}