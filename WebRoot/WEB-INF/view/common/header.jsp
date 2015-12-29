<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
    String name="";
    if(null != request.getCookies()){
        Cookie[] cookie = request.getCookies();
        for(int i = 0; i < cookie.length; i++)
        {
            if(cookie[i].getName().equals("admin"))
            {
                name = cookie[i].getValue();
                break;
            }
        }
    }
    %> 
    <% if(name == null||"".equals(name)){ %>
    <script type="text/javascript">
    window.location.href=window.location.href;
	</script>
    <%} %>
	<div id="header">
		<div class="title">
			绩效管理与激励系统
		</div>
		<div class="nav">
			<ul>
			<% if(name.equals("1")){ %>
				<li class="flowstep active">
					<a href="${pageContext.request.contextPath}/TableName/toProcessRhythm">流程节奏</a>
				</li>
				<li class="achievement">
					<a href="${pageContext.request.contextPath}/TableName/toResultsAchieved">业绩达成</a>
				</li>
				<li class="authority">
					<a href="${pageContext.request.contextPath}/userRightsManage/toRightsManage">权限配置</a>
				</li>
				<li class="export"  id="export" >
					<a href="${pageContext.request.contextPath}/download/excel" onclick="check()" >导出未考核人员</a>
				</li>
				<%}else{ %>
				<li class="flowstep active">
					<a href="${pageContext.request.contextPath}/TableName/toProcessRhythm">流程节奏</a>
				</li>
				<li class="achievement">
					<a href="${pageContext.request.contextPath}/TableName/toResultsAchieved">业绩达成</a>
				</li>
				<%
				}%>
			</ul>
		</div>
	</div>
