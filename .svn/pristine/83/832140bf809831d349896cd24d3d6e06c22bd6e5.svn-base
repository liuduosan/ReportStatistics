<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>流程节奏</title>
</head>
<body>
	<input type="hidden" id="type" value="0">
	<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
	<div id="main">
		<div class="sidebar">
			<h2 class="title">层级部门树形结构</h2>
			<div id="ztreeSidebar" class="ztree">
			</div>
		</div>
		<div class="content">
			<div class="titleContentBox queryCondition">
				<h2>查询条件</h2>
				<div class="ct">
					<div class="quarterOrMonth fl">
						<span>按季度/月份：</span>
						<select id="quarterOrMonthSelect">
							<option value="季度" selected="selected">季度</option>
						    <option value="月份">月份</option>
						</select>
					</div>
					<div class="quarterOrMonthBox fl" id="quarterOrMonthBox">
						<select id="quarter">
							<option value="" selected="selected">全部</option>
							<option value="1">第一季度</option>
							<option value="2">第二季度</option>
							<option value="3">第三季度</option>
							<option value="4">第四季度</option>
						</select>
					</div>
					<div class="history fl">
						<span>历史数据时点：</span>
						<input class="Wdate" type="text" id="date" onClick="WdatePicker()">
					</div>
					<div class="query fl">
						<input class="btnQuery " type="button" value="查询" onclick="query()">
					</div>
				</div>
			</div>
			<div class="titleContentBox rate">
				<h2>所处环节比例</h2>
				<div class="ct">
					<div id="main1" style="width:900px;height:300px;border:0px solid #ccc;margin: 0 auto;padding-top: 50px;"></div>
					<div id="main2" style="width:900px;height:300px;border:0px solid #ccc;margin: 0 auto;padding-top: 50px;"></div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/view/common/import.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/staticResources/js/processrhythm/processrhythm.js"></script>
</body>
</html>