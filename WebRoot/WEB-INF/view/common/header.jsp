<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div id="header">
		<div class="title">
			绩效管理与激励系统
		</div>
		<div class="nav">
			<ul>
			<c:if test="${adminlist>0 }">
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
			</c:if>
			<c:if test="${adminlist==0 }">
				<li class="flowstep active">
					<a href="${pageContext.request.contextPath}/TableName/toProcessRhythm">流程节奏</a>
				</li>
				<li class="achievement">
					<a href="${pageContext.request.contextPath}/TableName/toResultsAchieved">业绩达成</a>
				</li>
			</c:if>
			</ul>
		</div>
	</div>
