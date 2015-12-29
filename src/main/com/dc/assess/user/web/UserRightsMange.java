package com.dc.assess.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dc.assess.user.dto.UserManage;
import com.dc.assess.user.service.UserService;
import com.dc.flamingo.core.support.AjaxResult;
import com.github.abel533.echarts.json.GsonUtil;

/**
 * @author liutaoq
 *
 */
@Controller
@RequestMapping(value = "/userRightsManage")
public class UserRightsMange {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/query")
	@ResponseBody
	public AjaxResult query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AjaxResult ajax = new AjaxResult();
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");  
		String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
		String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
		int limit = Integer.parseInt(rows);
		int start = (Integer.parseInt(page)-1)*limit;
		String userName = Null(request.getParameter("username"));
		userName = URLDecoder.decode( userName ,"utf-8");
		String itcode = Null(request.getParameter("itcode"));
		int totalRecord = userService.countByUserNoOrITCode(userName, itcode); // 总记录数
		int totalPage = totalRecord % Integer.parseInt(rows) == 0 ? totalRecord / Integer.parseInt(rows)
				: totalRecord / Integer.parseInt(rows) + 1; // 计算总页数
			int index = (Integer.parseInt(page) - 1) * Integer.parseInt(rows); // 开始记录数
			int pageSize = Integer.parseInt(rows);
			Object[] args = {userName, itcode};
			List<UserManage> list = userService.queryByUserNoOrITCode(userName, itcode, Integer.parseInt(page), limit);
			StringBuffer sb = new StringBuffer();
			sb.append("{\"page\":\"");
			sb.append(page);
			sb.append( "\",");
			sb.append("\"total\":"+totalPage+",");
			sb.append("\"records\":\""+totalRecord+"\",");
			sb.append("\"rows\":[");
			for (int k = 0; k < list.size(); k++) {
				UserManage userManage = list.get(k);
				sb.append(" {\"id\":\""+userManage.getId()+"\",\"cell\":[\""+(k+1)+"\",\""+userManage.getUserName()+
						"\",\""+userManage.getCheckedNodes()+"\",\""+	userManage.getDeptName()+
						"\",\""+userManage.getItCode()+"\",\""+userManage.getGender()+"\",\""+
						userManage.getIDCard()+"\",\""+userManage.getId()+"\",\""+userManage.getAuthor()+"\",\""+
						userManage.getDescription()+"\"]} ");
				if(k<list.size()-1){
					sb.append(",");
				}
			}
			sb.append("]}");
			
//			String json =  "{\"page\":\""
//			+ page
//			+ "\","
//			+ "      \"total\":"+totalPage+","
//			+ "      \"records\":\""+totalRecord+"\","
//			+ "      \"rows\":"
//			+ "          [";
//			for (int k = 0; k < list.size(); k++) {
//				UserManage userManage = list.get(k);
//				json=json+" {\"id\":\""+userManage.getId()+"\",\"cell\":[\""+(k+1)+"\",\""+userManage.getUserName()+
//						"\",\""+userManage.getUserNo()+"\",\""+	userManage.getDeptName()+
//						"\",\""+userManage.getItCode()+"\",\""+userManage.getGender()+"\",\""+
//						userManage.getIDCard()+"\",\""+userManage.getId()+"\",\""+userManage.getAuthor()+"\",\""+
//						userManage.getDescription()+"\"]} ";
//				if(k<list.size()-1){
//					json = json+",";
//				}
//			}
//			json = json+ "          ],"
//			+ "      \"userdata\":{\"amount\":3220,\"tax\":342,\"total\":3564,\"name\":\"Totals:\"}"
//			+ "    }";
//			PrintWriter out = response.getWriter();
//			out.write(json); // 将JSON数据返回页面
//			out.flush();
			ajax.put("data", sb);
			ajax.put("success", true);
			return ajax;
	}
	
	@RequestMapping(value = "/add")
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codeNumber=request.getParameter("codeNumber");
		String deptName=request.getParameter("deptName");
		String itcode=request.getParameter("itcode");
		String authorId=request.getParameter("authorId");
		String descriptionId=request.getParameter("descriptionId");
		
		String[] itcodes = itcode.split(";");
		for (String itcodeTemp : itcodes) {
			if(!itcodeTemp.trim().equals("")){
				userService.add(itcodeTemp,deptName,authorId,descriptionId);
			}
		}
		return ;
	}
	

	
	@RequestMapping(value = "/update")
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("updateId");
		String codeNumber=request.getParameter("codeNumber");
		String deptName=request.getParameter("deptName");
		String itcode=request.getParameter("itcode");
		String authorId=request.getParameter("authorId");
		String descriptionId=request.getParameter("descriptionId");
		
		String[] itcodes = itcode.split(";");
		for (String itcodeTemp : itcodes) {
			if(!itcodeTemp.trim().equals("")){
				userService.update(itcodeTemp,deptName,authorId,descriptionId,id);
			}
		}
		return ;
	}
	
	@RequestMapping(value = "/findByKeyWord")
	@ResponseBody
	public AjaxResult findByKeyWord(HttpServletRequest request, HttpServletResponse response){
		AjaxResult ajax = new AjaxResult();
		String keyword = request.getParameter("keyword");
		List<String> list = null;
		if(keyword!=null){
			list = userService.queryByUserNameOrITCode(null,keyword);
			if(list.size()==0){
				list =  userService.queryByUserNameOrITCode(keyword,null);
			}
		}
		//构造json字符串，格式如：{"keyword":"***"}
		
		PrintWriter out = null;
		StringBuffer sb  = new StringBuffer();
			sb.append ("[");
			for (int i = 0; i < list.size(); i++) {
				sb.append("{\"keyword\":\"");
				sb.append(list.get(i));
				sb.append ("\"}");
				if(i!=(list.size()-1)){
					sb.append (",");
				}
			}
			sb.append ("]");
			ajax.put("data", GsonUtil.format(sb));
			ajax.put("success", true);
			return ajax;
	}
	/**
	 * null字符串处理
	 * @param obj
	 * @return
	 */
	public String Null(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}
	
	

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toRightsManage")
	public ModelAndView toProcesslink(HttpServletRequest request){
		String itcode = request.getHeader("iv-user");
		ModelAndView mav = new ModelAndView();
		mav.addObject("itcodeUUID", itcode);
		mav.setViewName("/rightsManage/rightsManage");
		return mav;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request){
		AjaxResult ajax = new AjaxResult();
		String id = request.getParameter("id");
		userService.delete(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/rightsManage/query");
		ajax.put("success", true);
		return ajax;
	}
}