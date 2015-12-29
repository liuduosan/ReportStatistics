<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>流程节奏</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/staticResources/css/rightsManage/core.css"><jsp:include
	page="/WEB-INF/view/common/import.jsp"></jsp:include>
<script
	src="${pageContext.request.contextPath}/staticResources/js/rightsManage/rightsManage.js"></script>
<script
	src="${pageContext.request.contextPath}/staticResources/js/rightsManage/lenoveInput.js"></script>
<script
	src="${pageContext.request.contextPath}/staticResources/js/rightsManage/popup_layer.js"></script>
<script type="text/javascript">
	var deptInfo;
</script>
</head>
<body>
	<input type="hidden" id="type" value="2" ><jsp:include
		page="/WEB-INF/view/common/header.jsp"></jsp:include>
	<div id="main">
		<div class="sidebar">
			<h2 class="title">层级部门树形结构</h2>
			<div id="ztreeSidebar" class="ztree"></div>
		</div>
		<div class="content">
			<div class="titleContentBox queryCondition">
				<h2>部门管理权限</h2>
				<div class="ct">
					<div class="block" style="width: 800px;">
						<div class="search">
							<input class="searchTxt" type="text" value="部门名称或ITCode" id="itcodeSearch" name="itcodeSearch">
							<input  id="searchBtnId"	onclick="searchBtnIdQuery()"	type="button" class="searchBtn">
						</div>
						<div class="insert">
							<input type="hidden"   id="ele7"/>
							<input type="hidden"   id="ele8"/>
							<input type="button" value="- 批量删除" class="multiDel" onclick="patchDelete()">
							<input  id="ele9" type="button" value="+ 增加" class="btnInsert">
						</div>
					</div>
					<div class="eInfo">
						<table class="eInfoTable">
							<thead>
								<tr>
									<th style="width: 70px;"><input type="checkbox" name=""
										id="" class="allOrNone">&nbsp;序号</th>
									<th style="width: 100px;">部门编号</th>
									<th style="width: 165px;">部门名称</th>
									<th style="width: 265px;">考核管理员</th>
									<th style="width: 80px;">查看</th>
									<th style="width: 120px;">操作</th>
								</tr>
							</thead>
							<tbody id="tbodyId">
							</tbody>
						</table>
						<div class="pageDiv">
							<div class="pagination" id="pagination">
								<input type="hidden" id="culPageHidden"/>
								<span  id="lastPageId" class="next btnPress" onclick="lastPage()" style="color:#000000">&lt;上一页</span>
								<a href="javascript:void(0)" class="next btnPress" onclick="nextPage()">下一页&gt;</a>
								<span class="pageTotle">共<em id="totaPage"></em>页&nbsp;&nbsp;</span>
								<div class="lf jump-div">到<input type="text"
										id="jumpPageNo" name="jumpPageNo" >页&nbsp;<input onclick="goPage()"  type="button" value="确定" />
								</div>
							</div>
						</div>
						<div>
							<a class="downloadModal" style="text-decoration:none;vertical-align:top;line-height: 28px;" href="${pageContext.request.contextPath}/upload1/downloadExcel">模版下载.xls</a>
						</div>
						<div>
							<div class="upload" id="div-addInsuranceTemplate" style="overflow:hidden;overflow-x:hidden;border:1px solid #fff; display: inline-block;"></div> 
							<span id="InsuranceTemplate"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span id="attach_span"></span>
							<input type="button" class="ensureUpload" name="batchAddingButton" id='batchAddingButton' value="上传" onClick="batchAddingButton()" style="display: none;background-color: #f48148;color:#fff;font-weight:700;border-radius: 3px;border:0;padding: 3px 10px;width:70px;vertical-align: top;"/>
							<p><span id="tips" style="color:red;"></span></p>
						</div>
						<div id="pType4"></div>
					</div>
				</div>
			</div>
		</div>
		</div>

	<DIV id=emample9 class=example>
	<DIV id="blk7" class="blk" style="DISPLAY: none">
			<DIV class=head>
				<DIV class=head-right></DIV>
			</DIV>
			<DIV class="main">
				<div class="dam-header">
						<h2>管理权限修改</h2>
				</div>
				<input type="hidden" id="updateId" /> 
				<div class="dam-main" id="dam-main-idForShow2">
					<div class="line">
						<label>部门编号：</label> <span id="codeNumberForShow2"></span>
					</div>
					<div class="line" id="lenoveInputForShow">
						<label>部门名称：</label> <input type="hidden"  id="deptNameHiddenForShow2"/>
						<span id="deptNameForShow2"  style="width:178px;"></span>
						<span id="chooseDeptForShow2"></span>
					</div>
					<div class="line">
						<label>权限管理员(ITCode)：</label> <!-- <span  id="itcodeForShow2"></span> -->
						<input class="cOneManagerIpt" id="itcodeForShow2" 
							type="text" name="" > <input onclick="document.getElementById('ele10').click()"
							type="button" value="查询" class="query btnFunc"> <input
							type="button" value="清空" class="empty btnFunc" id="clearButton">
					</div>
					<div class="line">
						<label>中文名：</label> <span id="chineseNameForShow2"></span>
					</div>
					<div class="line">
						<label>作者：</label> <span id="authorIdForShow2"></span>
					</div>
					<div class="line">
						<label>描述：</label> <span><textarea name=""
								id="descriptionIdForShow2" cols="36" rows="5"></textarea></span>
					</div>
				</div>
				<div class="dam-footer" style="padding-top: 5px;">
					<input type="button" value="保存" class="btn save" id="updateButton">
					<input type="button" value="退出" class="btn quit" id="close7">
				</div>

			</DIV>
			<DIV class=foot>
				<DIV class=foot-right></DIV>
			</DIV>
		</DIV>
	<DIV id="blk8" class="blk" style="DISPLAY: none">
			<DIV class=head>
				<DIV class=head-right></DIV>
			</DIV>
			<DIV class="main">
				<div class="dam-header">
						<h2>详细信息</h2>
				</div>
				<div class="dam-main" id="dam-main-idForShow">
					<div class="line">
						<label>部门编号：</label> <span id="codeNumberForShow"></span>
					</div>
					<div class="line" id="lenoveInputForShow">
						<label>部门名称：</label> <input type="hidden"  id="deptNameHiddenForShow"/><span id="deptNameForShow"  style="width:178px;"></span><span id="chooseDeptForShow"></span>
					</div>
					<div class="line">
						<label>权限管理员(ITCode)：</label> <span  id="itcodeForShow"></span>
					</div>
					<div class="line">
						<label>中文名：</label> <span id="chineseNameForShow"></span>
					</div>
					<div class="line">
						<label>作者：</label> <span id="authorIdForShow"></span>
					</div>
					<div class="line">
						<label>描述：</label> <span><textarea name=""
								id="descriptionIdForShow" cols="36" rows="5"></textarea></span>
					</div>
				</div>
				<div class="dam-footer" style="padding-top: 5px;">
					<input type="button" value="关闭" class="btn quit" id="close8">
				</div>

			</DIV>
			<DIV class=foot>
				<DIV class=foot-right></DIV>
			</DIV>
		</DIV>
		<DIV id="blk9" class=blk style="DISPLAY: none">
			<DIV class=head>
				<DIV class=head-right></DIV>
			</DIV>
			<DIV class="main">
				<div class="dam-header">
					<h2>管理权限新增</h2>
				</div>
				<div class="dam-main" id="dam-main-id">
					<div class="line">
						<label>部门编号：</label> <span id="codeNumber"></span>
					</div>
					<div class="line" id="lenoveInput">
						<label>部门名称：</label> <input type="hidden"  id="deptNameHidden"/><input class="dNameIpt" type="text" name="" onfocus="inputThinking('deptName',1)"
							id="deptName"  style="width:178px;"><span id="chooseDept"></span>
					</div>
					<div class="line">
						<label>权限管理员(ITCode)：</label> <input class="cOneManagerIpt"
							type="text" name="" id="itcode"> <input id="ele10"
							type="button" value="查询" class="query btnFunc"> <input
							type="button" value="清空" class="empty btnFunc" id="clearButton">
					</div>
					<div class="line">
						<label>中文名：</label> <span id="chineseName"></span>
					</div>
					<div class="line">
						<label>作者：</label> <span id="authorId">${itcodeUUID}</span>
					</div>
					<div class="line">
						<label>描述：</label> <span><textarea name=""
								id="descriptionId" cols="36" rows="5"></textarea></span>
					</div>
				</div>
				<div class="dam-footer" style="padding-top: 5px;">
					<input type="button" value="保存" class="btn save" id="saveButton">
					<input type="button" value="退出" class="btn quit" id="close9">
				</div>

			</DIV>
			<DIV class=foot>
				<DIV class=foot-right></DIV>
			</DIV>
		</DIV>
		<form name="form1" action="" method="POST"> 
		<DIV id="blk10" class=blk style="DISPLAY: none">
			<DIV class=head>
				<DIV class=head-right></DIV>
			</DIV>
			<DIV class="main">
				<div class="dam-header">
			<h2>ITCode查询</h2>
		</div>
	<div class="damQuery-main">
		<div class="searchArea">
			<div class="search">
				<label>输入关键字查询：</label>
				<input class="keywordQuery" type="text" id="keywordQuery" name="keyword">
				<input class="btnQuery" type="button" value="查询" id="btnQuery">
			</div>
			<p class="tip">提示：输入中文或者ITCode后点击查询或回车，在搜索结果中双击即可选中</p>
		</div>
		<div class="result">
			<div class="result-l">
				<h2>
								<img
									src="${pageContext.request.contextPath}/staticResources/images/icon/011.png"
									alt="">搜索结果</h2>
				<div class="result-box">
					<select multiple size="5" name="list1"  id="list1"
									style="width:219px;height: 269px;"> 
					<option>&nbsp;</option> 
					</select>
				</div>
			</div>
			<div class="funcQueryBtns">
				<input type="button" value="多个添加" class="multiAdd"
								onclick="moveall(document.getElementById('list1'),document.getElementById('list2'))">
				<input type="button" value="单个添加" class="add"
								onclick="move(document.getElementById('list1'),document.getElementById('list2'))">
				<input type="button" value="单个删除" class="add"
								onclick="move(document.getElementById('list2'),document.getElementById('list1'))">
				<input type="button" value="多个删除" class="multiDel"
								onclick="moveall(document.getElementById('list2'),document.getElementById('list1'))">
			</div>
			<div class="result-r" id="chooseResult">
				<h2>
								<img
									src="${pageContext.request.contextPath}/staticResources/images/icon/011.png"
									alt="">选择结果</h2>
				<div class="result-box">
					<select multiple size="5" name="list2"  id="list2"
									style="width:219px;height: 269px;"> 
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="dam-footer">
		<input type="button" value="保存" class="btn save" id="setAdminButton">
		<input type="button" value="退出" class="btn quit" id="close10">
	</div>
			
			</DIV>
			<DIV class=foot>
				<DIV class=foot-right></DIV>
			</DIV>
		</DIV>
		</form>
	</DIV>
	
	

	<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
</body>
<script
	src="${pageContext.request.contextPath}/staticResources/js/rightsManage/swfobject.js"></script>
<script
	src="${pageContext.request.contextPath}/staticResources/js/rightsManage/upload.js"></script>
</html>