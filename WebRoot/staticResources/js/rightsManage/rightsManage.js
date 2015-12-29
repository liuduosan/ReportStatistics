var culPage = "1";
var ROWS_ONE_PAGE ="10";
var $totalPage = 1;
var $searchTotalPage = 1;
var fengyeFlag = false;


$(function(){
	
	var culPage = "1";
	var ROWS_ONE_PAGE ="10";

		function createAfterStr(cul,total){
			var afterStr = "";
			if(total<5){
				for (var int2 = 0; int2 < total; int2++) {
					if(cul==(int2+1)){
						afterStr+="<span class='current' id='pagenum"+cul+"' onclick='fengye(\"1\",\"10\",\"\")'>"+cul+"</span>";
					}else{
						afterStr+="<a href=\"javascript:void(0)\" id=\"pagenum"+(int2+1)+"\"  onclick=\"fengye('"+(int2+1)+"','10','')\">"+(int2+1)+"</a>";
					}
				}
			}else{
				for (var int2 = 0; int2 < ROWS_ONE_PAGE; int2++) {
					if(cul==(int2+1)){
						afterStr+="<span class='current' id='pagenum"+cul+"' onclick='fengye(\"1\",\"10\",\"\")'>"+cul+"</span>";
					}else{
						afterStr+="<a href=\"javascript:void(0)\" id=\"pagenum"+(int2+1)+"\"  onclick=\"fengye('"+(int2+1)+"','10','')\">"+(int2+1)+"</a>";
					}
				}
			}
			$("#lastPageId").after(afterStr);
		}
	function fengye(p,r,i){
		culPage = p;
		//开始分页
		var pageNum =p;
		var rowsNum=r;
		var itcode = i;
		if(i=="部门名称或ITCode"){
			itcode="";
		}
		
		$.ajax({
			type : "post",
			url : webContext + '/userRightsManage/query',
			dataType : 'json',
			data:{
				page:pageNum,
				rows:rowsNum,
				itcode:itcode
					},
			async:false,
			success : function(data, textStatus) {
				var data = JSON.parse(data.data);
				var objs = data.rows;
				var str = "";
				var total = parseInt(data.total);
				var cul   =  parseInt(culPage);
				for (var int = 0; int < objs.length; int++) {
					var obj = objs[int].cell;
					str+="<tr>"
					str+="<td  class='text-center'>" +
						"<input id='author"+parseInt(int+1)+"' type='hidden'  value='"+obj[8]+"'>"+
						"<input id='description"+parseInt(int+1)+"' type='hidden'  value='"+obj[9]+"'>"+
							"<input id='checkbox"+parseInt(int+1)+"' type='checkbox'  value='"+obj[7]+"'>&nbsp;"+obj[0]+"</td>";
					str+="<td class='text-center'>"+obj[2]+"</td>";
					str+="<td class='text-center'>"+obj[3]+"</td>";
					str+="<td class='text-center'>"+obj[1]+"/"+obj[4]+"</td>";
					if(int==0){
						str+="<td class='text-center'><a id='eled8' href='#' onclick='showInfo("+parseInt(int+1)+")'>详细信息</a></td>";
					}else{
						str+="<td class='text-center'><a onclick='showInfo("+parseInt(int+1)+");' href='#'>详细信息</a></td>";
					}
					str+="<td class='text-center'>"+
						"<input class='btnModify' type='button' value='修改' onclick='showInfo2("+parseInt(int+1)+")'>"+
						"<input id='delete"+parseInt(int+1)+"' class='btnModify' type='button' value='删除' onclick='deleteRow("+parseInt(int+1)+")'>"+
						"</td></tr>";
				}
				$("#tbodyId").html(str);
				$("#totaPage").html(data.total);
				$("#culPageHidden").val(data.page);
				createAfterStr(cul,total);
				fengyeFlag = true;
				$totalPage = parseInt(data.total);
			}
		});
	}
	if(culPage=="1"){
		$("#lastPageId").addClass("current prev btnPress");
	}
	var itcodeSearchValue = $("#itcodeSearch").val();
	if(itcodeSearchValue=="部门名称或ITCode"){
		itcodeSearchValue="";
		fengye("1",ROWS_ONE_PAGE,"");
	}else{
		fengye("1",ROWS_ONE_PAGE,itcodeSearchValue);
	}
	
	var t7 = new PopupLayer({
		trigger : "#ele7",
		popupBlk : "#blk7",
		closeBtn : "#close7",
		useOverlay : true,
		useFx : true,
		offsets : {
			x : 0,
			y : -41
		}
	});
	
	t7.doEffects = function(way) {
        function isIE() { 
            if (!!window.ActiveXObject || "ActiveXObject" in window)
                return true;
            else
                return false;
        };
		if(isIE()){
		   w = $(document)	.width() - this.popupLayer
				.width()-34;
		}else{
			 w = $(document)	.width() - this.popupLayer
				.width();
		}
		if (way == "open") {
			this.popupLayer
					.css({
						opacity : 0.3
					})
					.show(
							400,
							function() {
								this.popupLayer
										.animate(
												{
													left : w / 2,
													top : (document.documentElement.clientHeight - this.popupLayer
															.height())
															/ 2
															+ $(
																	document)
																	.scrollTop(),
													opacity : 0.8
												},
												1000,
												function() {
													this.popupLayer
															.css(
																	"opacity",
																	1)
												}
														.binding(this));
							}.binding(this));
		} else {
			this.popupLayer.animate({
				left : this.trigger.offset().left,
				top : this.trigger.offset().top,
				opacity : 0.1
			}, {
				duration : 500,
				complete : function() {
					this.popupLayer.css("opacity", 1);
					this.popupLayer.hide()
				}.binding(this)
			});
		}
	};
	
		
		var t8 = new PopupLayer({
			trigger : "#ele8",
			popupBlk : "#blk8",
			closeBtn : "#close8",
			useOverlay : true,
			useFx : true,
			offsets : {
				x : 0,
				y : -41
			}
		});
		
		t8.doEffects = function(way) {
	        function isIE() { 
	            if (!!window.ActiveXObject || "ActiveXObject" in window)
	                return true;
	            else
	                return false;
	        };
			if(isIE()){
			   w = $(document)	.width() - this.popupLayer
					.width()-34;
			}else{
				 w = $(document)	.width() - this.popupLayer
					.width();
			}
			if (way == "open") {
				this.popupLayer
						.css({
							opacity : 0.3
						})
						.show(
								400,
								function() {
									this.popupLayer
											.animate(
													{
														left : w / 2,
														top : (document.documentElement.clientHeight - this.popupLayer
																.height())
																/ 2
																+ $(
																		document)
																		.scrollTop(),
														opacity : 0.8
													},
													1000,
													function() {
														this.popupLayer
																.css(
																		"opacity",
																		1)
													}
															.binding(this));
								}.binding(this));
			} else {
				this.popupLayer.animate({
					left : this.trigger.offset().left,
					top : this.trigger.offset().top,
					opacity : 0.1
				}, {
					duration : 500,
					complete : function() {
						this.popupLayer.css("opacity", 1);
						this.popupLayer.hide()
					}.binding(this)
				});
			}
		};
		
		var t9 = new PopupLayer({
			trigger : "#ele9",
			popupBlk : "#blk9",
			closeBtn : "#close9",
			useOverlay : true,
			useFx : true,
			offsets : {
				x : 0,
				y : -41
			}
		});
		
		t9.doEffects = function(way) {
	        function isIE() { 
	            if (!!window.ActiveXObject || "ActiveXObject" in window)
	                return true;
	            else
	                return false;
	        };
			if(isIE()){
			   w = $(document)	.width() - this.popupLayer
					.width()-34;
			}else{
				 w = $(document)	.width() - this.popupLayer
					.width();
			}
			if (way == "open") {
				this.popupLayer
						.css({
							opacity : 0.3
						})
						.show(
								400,
								function() {
									this.popupLayer
											.animate(
													{
														left : w / 2,
														top : (document.documentElement.clientHeight - this.popupLayer
																.height())
																/ 2
																+ $(
																		document)
																		.scrollTop(),
														opacity : 0.8
													},
													1000,
													function() {
														this.popupLayer
																.css(
																		"opacity",
																		1)
													}
															.binding(this));
								}.binding(this));
			} else {
				this.popupLayer.animate({
					left : this.trigger.offset().left,
					top : this.trigger.offset().top,
					opacity : 0.1
				}, {
					duration : 500,
					complete : function() {
						this.popupLayer.css("opacity", 1);
						this.popupLayer.hide()
					}.binding(this)
				});
			}
		};
		
		
		var t10 = new PopupLayer({
			trigger : "#ele10",
			popupBlk : "#blk10",
			closeBtn : "#close10",
			useOverlay : true,
			useFx : true,
			offsets : {
				x : 0,
				y : -41
			}
		});
		
		t10.doEffects = function(way) {
	        function isIE() { 
	            if (!!window.ActiveXObject || "ActiveXObject" in window)
	                return true;
	            else
	                return false;
	        };
			if(isIE()){
			   w = $(document)	.width() - this.popupLayer
					.width()-34;
			}else{
				 w = $(document)	.width() - this.popupLayer
					.width();
			}
			if (way == "open") {
				 $( "#list1").empty();
//				 $( "#list2").empty();
				this.popupLayer
						.css({
							opacity : 0.3
						})
						.show(
								400,
								function() {
									this.popupLayer
											.animate(
													{
														left : w / 2,
														top : (document.documentElement.clientHeight - this.popupLayer
																.height())
																/ 2
																+ $(
																		document)
																		.scrollTop(),
														opacity : 0.8
													},
													1000,
													function() {
														this.popupLayer
																.css(
																		"opacity",
																		1)
													}
															.binding(this));
								}.binding(this));
			} else {
				this.popupLayer.animate({
					left : this.trigger.offset().left,
					top : this.trigger.offset().top,
					opacity : 0.1
				}, {
					duration : 500,
					complete : function() {
						this.popupLayer.css("opacity", 1);
						this.popupLayer.hide()
					}.binding(this)
				});
			}
		};
		
		
		
		 $("#setAdminButton").click(function(){
			 //选择结果不能为空
			 if($("#list2")[0].childNodes.length==0){
				 layer.alert('选择结果不能为空', function(index) {
						layer.close(index);
					}); 
				 return;
			 }
			 
			 var str1Temp = "";
			 var str2Temp = "";
			  $("#list2 option").each(function(){
				  str1Temp+=$(this).val().split("/")[1]+"; ";
				  str2Temp+=$(this).val().split("/")[0]+"; ";
			  });
			  $("#itcode").val(str1Temp);
			  $("#itcodeForShow2").val(str1Temp);
			  $("#chineseName").html(str2Temp);
			  $("#chineseNameForShow2").html(str2Temp);
			document.getElementById("close10").click();
		  });
		 
			$(".eInfo .eInfoTable .allOrNone").change(function(){
				$(this).closest(".eInfoTable").find("tbody input[type='checkbox']").prop("checked",this.checked);
			});
			
			$(".queryCondition .searchTxt").focus(function(){
				if(this.value==this.defaultValue){
					$(this).val("");
					$(this).css("color","#000");
				}else{
					$(this).css("color","#000");
				}
			}).blur(function(){
				if(!this.value.replace(/^\s+|\s+$/g,'')){
					$(this).val(this.defaultValue);
					$(this).css("color","#a1a1a1");
				}else{
					$(this).css("color","#000");
				}
			});
			
			//添加清空事件
			$("#clearButton").click(function(){
				$("#codeNumber").html("");
				$("#deptName").val("");
				$("#itcode").val("");
				$("#chineseName").html("");
				$("#authorId").html("");
				$("#descriptionId").val("");
			});
			$("#saveButton").click(function(){
				if($.trim($("#deptName").val())==""){
					layer.alert('请您输入部门名称', function(index) {
						layer.close(index);
					}); 
					return;
				}
				//开始收集数据
				var codeNumberValue = $("#codeNumber").html();
				var deptNameHiddenValue = $("#deptNameHidden").val();
				var itcodeValue = $("#itcode").val();
				var authorIdValue = $("#authorId").html();
				var descriptionIdValue = $("#descriptionId").val();
				if(deptNameHiddenValue==""){
					layer.alert('请选择的部门名称不存在', function(index) {
						layer.close(index);
					}); 
					return;
				}
				if($("#itcode").val()==""){
					layer.alert('请您输入权限管理员(ITCode)', function(index) {
						layer.close(index);
					}); 
					return;
				}
				$.ajax({
					type : "post",
					url : webContext + '/userRightsManage/add',
					dataType : 'json',
					data:{
						codeNumber:codeNumberValue,
						deptName:deptNameHiddenValue,
						itcode:itcodeValue,
						authorId:authorIdValue,
						descriptionId:descriptionIdValue
							},
					async:false,
					success : function(data, textStatus) {
						layer.alert('保存成功！', function(index) {
							layer.close(index);
						}); 
					}
				});
			});
			$("#updateButton").click(function(){
				/*if($.trim($("#deptName").val())==""){
					layer.alert('请您输入部门名称', function(index) {
						layer.close(index);
					}); 
					return;
				}*/
				//开始收集数据
				
				
				var updateId = $("#updateId").val();
				var codeNumberValue = $("#codeNumberForShow2").html();
				var deptNameHiddenValue = $("#codeNumberForShow2").html();//??
				var itcodeValue = $("#itcodeForShow2").val();
				var authorIdValue = $("#authorIdForShow2").html(); //???
				var descriptionIdValue = $("#descriptionIdForShow2").val();
				/*if(deptNameHiddenValue==""){
					layer.alert('请选择的部门名称不存在', function(index) {
						layer.close(index);
					}); 
					return;
				}*/
				if($("#itcodeForShow2").val()==""){
					layer.alert('请您输入权限管理员(ITCode)', function(index) {
						layer.close(index);
					}); 
					return;
				}
				$.ajax({
					type : "post",
					url : webContext + '/userRightsManage/update',
					dataType : 'json',
					data:{
						updateId:updateId,
						codeNumber:codeNumberValue,
						deptName:deptNameHiddenValue,
						itcode:itcodeValue,
						authorId:authorIdValue,
						descriptionId:descriptionIdValue
							},
					async:false,
					success : function(data, textStatus) {
						layer.alert('保存成功！', function(index) {
							layer.close(index);
						}); 
					}
				});
			});
			$("#btnQuery").click(function(){
				if($("#keywordQuery").val()==""){
					layer.alert('请您输入关键字', function(index) {
						layer.close(index);
					}); 
				}
				 $( "#list1").empty();
//				 $( "#list2").empty();
				//ajax查询   -  /userRightsManage/findByKeyWord
					$.ajax({
						type : "post",
						url : webContext + '/userRightsManage/findByKeyWord',
						dataType : 'json',
						data:{keyword:$("#keywordQuery").val()},
						async:false,
						success : function(data, textStatus) {
							if (data.success) {
								var jsonObjs =$.parseJSON(data.data);
								jsonObjs = $.parseJSON(jsonObjs);
								 for (var int = 0; int < jsonObjs.length; int++) {
									 $("#list1").append("<option value='"+jsonObjs[int].keyword+"'>"+jsonObjs[int].keyword+"</option>"); 
								}
							}
						}
					});
				 
			});
			
			
			
		});

function patchDelete(){
	var chks = $("tbody input[type='checkbox']");
	var n = $("tbody input[type='checkbox']").length;
	var m = $("tbody input[type='checkbox'][checked]").length;
	var checkSum = 0;
	$("tbody input[type='checkbox']").each(function(n){
		if(this.checked==true){
			checkSum++
		}
	});
	if(checkSum==0){
		layer.alert('请选择要删除的数据', function(index) {
			layer.close(index);
		}); 
		return;
	}

		if(n==1){
			if($(chks[0]).val()==""){
				layer.alert('请选择要删除的数据.', function(index) {
					layer.close(index);
				}); 
				return;
			}
		}else{
			//批量删除数据
			layer.confirm("您确定要批量删除吗？", function(index) {
				var deleteId = "";
				$("tbody input[type='checkbox']").each(function(n){
					if(this.checked==true){
						deleteId+=$(this).val();
						deleteId+=",";
					}
				});
				
				$.ajax({
					type : "post",
					url : webContext + '/userRightsManage/delete',
					dataType : 'json',
					data:{
						id:deleteId
					},
					async:true,
					success : function(data, textStatus) {

						layer.alert('删除成功!', function(index) {
											layer.close(index);
						}); 
					}
				});
				
				flag  = true;
				layer.close(index);	
				searchBtnIdQuery();
			});
		}
}
function showInfo(rowId){
	document.getElementById("ele8").click();
	//详细信息
	var checkbox_id = "checkbox"+rowId;
	var author_id ="author"+rowId;
	var description_id= "description"+rowId;
	var obj =  document.getElementById(checkbox_id);
	var nodes = $(obj.parentElement).nextAll();
	$("#codeNumberForShow").html(nodes[0].innerHTML);
	$("#deptNameForShow").html(nodes[1].innerHTML);
	$("#itcodeForShow").html(nodes[2].innerHTML.split("/")[1]);
	$("#chineseNameForShow").html(nodes[2].innerHTML.split("/")[0]);
	$("#authorIdForShow").html($("#"+author_id).val());
	$("#descriptionIdForShow").val($("#"+description_id).val());
}

function showInfo2(rowId){
	document.getElementById("ele7").click();
	//详细信息
	var checkbox_id = "checkbox"+rowId;
	var author_id ="author"+rowId;
	var description_id= "description"+rowId;
	var obj =  document.getElementById(checkbox_id);
	var nodes = $(obj.parentElement).nextAll();
	$("#codeNumberForShow2").html(nodes[0].innerHTML);
	$("#deptNameForShow2").html(nodes[1].innerHTML);
	$("#itcodeForShow2").val(nodes[2].innerHTML.split("/")[1]);
	$("#chineseNameForShow2").html(nodes[2].innerHTML.split("/")[0]);
	$("#authorIdForShow2").html($("#"+author_id).val());
	$("#descriptionIdForShow2").val($("#"+description_id).val());
	$("#updateId").val(obj.value);
	
	/*var options = $(this);
	for (var int = 0; int < deptInfo.length; int++) {
		var obj  =  deptInfo[int];
		if(obj.departNo2==a.value){
			$("#deptName").val(obj.deptname);
			break;
		}
	}
	$("#codeNumber").html(a.value);
	$("#deptNameHidden").val(a.value);*/
	
}

function deleteRow(rowId){
	
	var flag = false;
	layer.confirm("确定要删除吗？", function(index) {
		
		var deleteId  = $("#checkbox"+rowId).val();
		$.ajax({
			type : "post",
			url : webContext + '/userRightsManage/delete',
			dataType : 'json',
			data:{
				id:deleteId
			},
			async:true,
			success : function(data, textStatus) {

				layer.alert('删除成功!', function(index) {
									layer.close(index);
				}); 
			}
		});
		
		flag  = true;
		layer.close(index);	
		searchBtnIdQuery();
	});
			
}

function lastPage(){
	if(parseInt(culPage)<=2){
		$("#lastPageId").addClass("current prev btnPress");
		fengye("1",ROWS_ONE_PAGE,$("#itcodeSearch").val());
	}else{
		$("#lastPageId").addClass("next btnPress"); 
		fengye(parseInt(culPage)-1,ROWS_ONE_PAGE,$("#itcodeSearch").val());
	}
}
function nextPage(){
	if(parseInt(culPage)>=1){
		$("#lastPageId").css("color","#000000");
	}
	if($("#itcodeSearch").val()=="部门名称或ITCode" ||  $("#itcodeSearch").val()==""){
		if(parseInt(culPage)<$totalPage){
			fengye(parseInt(culPage)+1,ROWS_ONE_PAGE,$("#itcodeSearch").val());
		}else{
			fengye($totalPage,ROWS_ONE_PAGE,$("#itcodeSearch").val());
		}
	}else{
		if(parseInt(culPage)<$searchTotalPage){
			fengye(parseInt(culPage)+1,ROWS_ONE_PAGE,$("#itcodeSearch").val());
		}else{
			fengye($searchTotalPage,ROWS_ONE_PAGE,$("#itcodeSearch").val());
		}
	}
	
	
	
	
}

function createAfterStr(cul,total){
	var afterStr = "";
	if(total<5){
		for (var int2 = 0; int2 < total; int2++) {
			if(cul==(int2+1)){
				afterStr+="<span class='current'>"+cul+"</span>";
			}else{
				afterStr+="<a href=\"javascript:void(0)\"  onclick=\"fengye('"+(int2+1)+"','10','')\">"+(int2+1)+"</a>";
			}
		}
	}else{
		for (var int2 = 0; int2 < 5; int2++) {
			if(cul==(int2+1)){
				afterStr+="<span class='current'>"+cul+"</span>";
			}else{
				afterStr+="<a href=\"javascript:void(0)\" >"+(int2+1)+"</a>";
			}
		}
	}
	$("#lastPageId").after(afterStr);
}
function fengye(p,r,i){
	culPage = p;
	
	for (var int = 0; int < $totalPage; int++) {
		$("#pagenum"+parseInt(int+1)).css("background-color","#FFFFFF");
		$("#pagenum"+parseInt(int+1)).css("color","#000000");
	}
	
	$("#pagenum"+parseInt(culPage)).css("background-color","#4fc4f6");

	$("#culPageHidden").val(p);
	//开始分页
	var pageNum =p;
	var rowsNum=r;
	var itcode = i;
	if(i=="部门名称或ITCode"){
		itcode="";
	}
	$.ajax({
		type : "post",
		url : webContext + '/userRightsManage/query',
		dataType : 'json',
		data:{
			page:pageNum,
			rows:rowsNum,
			itcode:itcode
				},
		async:false,
		success : function(data, textStatus) {
			var data = JSON.parse(data.data);
			var objs = data.rows;
			var str = "";
			for (var int = 0; int < objs.length; int++) {
				var obj = objs[int].cell;
//				$("input").trigger("click");
				
				str+="<tr>"
					str+="<td  class='text-center'>" +
						"<input id='author"+parseInt(int+1)+"' type='hidden'  value='"+obj[8]+"'>"+
						"<input id='description"+parseInt(int+1)+"' type='hidden'  value='"+obj[9]+"'>"+
							"<input id='checkbox"+parseInt(int+1)+"' type='checkbox'  value='"+obj[7]+"'>&nbsp;"+obj[0]+"</td>";
				str+="<td class='text-center'>"+obj[2]+"</td>";
				str+="<td class='text-center'>"+obj[3]+"</td>";
				str+="<td class='text-center'>"+obj[1]+"/"+obj[4]+"</td>";
				str+="<td class='text-center'><a onclick='$(\"#ele8\").trigger(\"click\");showInfo("+parseInt(int+1)+");' href='#'>详细信息</a></td>";
				str+="<td class='text-center'>"+
					"<input class='btnModify' type='button' value='修改'  onclick='showInfo2("+parseInt(int+1)+")'>"+
					"<input id='delete"+parseInt(int+1)+"' class='btnModify' type='button' value='删除' onclick='deleteRow("+parseInt(int+1)+")'>"+
					"</td></tr>";
			}
			$("#tbodyId").html(str);
			$("#totaPage").html(data.total);
			$("#culPageHidden").val(data.page);
			var t = parseInt(data.total);
			
			if(fengyeFlag==false){
				
				var k = $totalPage;
				if($searchTotalPage>=$totalPage){
					k =$searchTotalPage;
				}
				if(k==1){
					k=t;
				}
				for (var int = 0; int < k; int++) {
					if(int>=$searchTotalPage){
						
						$("#pagenum"+parseInt(int+1)).css("display","none");
					}else{
						$("#pagenum"+parseInt(int+1)).css("display","");
					}
				}
			}{
				if(i=="部门名称或ITCode" ||  i==""){
					for (var int = 0; int < $totalPage; int++) {
						$("#pagenum"+parseInt(int+1)).css("display","");
					}
				}else{
					for (var int = 0; int < $totalPage; int++) {
						if(int<$searchTotalPage){
							
							$("#pagenum"+parseInt(int+1)).css("display","");
						}else{
							$("#pagenum"+parseInt(int+1)).css("display","none");
						}
					}
				}
				
				
//				if(i=="部门名称或ITCode" ||  i==""){
//					fengyeFlag = true;
//				}else{
//					fengyeFlag = false;
//				}
			}
			
			
//			$totalPage = parseInt(data.total);
		}
	});
}


		var flag = true;
		$(function(){
			if(flag){
				flag = false;
				$.ajax({
					type : "post",
					url : webContext + '/DeptStruc/getDeptStruc',
					dataType : 'json',
					async:false,
					success : function(data, textStatus) {
						if (data.success) {
							var jsonData = jQuery.parseJSON(data.format);
							var tree = $.fn.zTree.init($("#ztreeSidebar"), setting, jsonData);
							deptInfo =  tree.transformToArray(tree.getNodes());
						} else {
							alert(data.error);
						}
					},
					error : function(xhr, ts, et) {
					}
				});
			}
			flag = true;
		});

		var IDMark_Switch = "_switch",  
		IDMark_Icon = "_ico",  
		IDMark_Span = "_span",  
		IDMark_Input = "_input",  
		IDMark_Check = "_check",  
		IDMark_Edit = "_edit",  
		IDMark_Remove = "_remove",  
		IDMark_Ul = "_ul",  
		IDMark_A = "_a";  

		var setting = {
		treeNodeKey : "departNo2",
		checkable : true,

		callback : {
			 //onCheck: zTreeOnCheck
			// beforeCheck: zTreeBeforeChecks
		},

		check : {
			autoChecckTrigger : false,
			chkboxType : {"Y":"ps","N":"ps"},
			chkStyle : "checkbox",
			enable : true,
			nocheckInherit : false,
			chkDisabledInherit : false
		},
		view:{
			showIcon: false,
			fontCss : {color:"#fff"},
			nameIsHTML: true
		},
		data : {
			key : {
				id:"departNo2",
				checked : "checked",
				children: "children",
				name:"deptname"
			},
			simpleData:{
				enable: true,
				idKey: "departNo2",
				pIdKey: "departNo1",
				rootPId: null
			}
		}
		};
		
		// 左右选择框
		sortitems = 1;  // Automatically sort items within lists? (1 or 0) 

		 function move(fbox,tbox) { 
		  for(var i=0; i<fbox.options.length; i++) { 
		    if(fbox.options[i].selected && fbox.options[i].value != "") { 
		    var no = new Option(); 
		    no.value = fbox.options[i].value; 
		    no.text = fbox.options[i].text; 
		    tbox.options[tbox.options.length] = no; 
		    fbox.options[i].value = ""; 
		    fbox.options[i].text = ""; 
		       } 
		  } 
		 BumpUp(fbox); 
		 if (sortitems) SortD(tbox); 
		 } 

		   function moveall(fbox,tbox) { 
		  for(var i=0; i<fbox.options.length; i++) { 
		    if(fbox.options[i].value != "") { 
		    var no = new Option(); 
		    no.value = fbox.options[i].value; 
		    no.text = fbox.options[i].text; 
		    tbox.options[tbox.options.length] = no; 
		    fbox.options[i].value = ""; 
		    fbox.options[i].text = ""; 
		       } 
		  } 
		 BumpUp(fbox); 
		 if (sortitems) SortD(tbox); 
		 } 


		 function BumpUp(box)  { 
		  for(var i=0; i<box.options.length; i++) { 
		    if(box.options[i].value == "")  { 
		       for(var j=i; j<box.options.length-1; j++)  { 
		       box.options[j].value = box.options[j+1].value; 
		       box.options[j].text = box.options[j+1].text; 
		       } 
		    var ln = i; 
		    break; 
		       } 
		  } 
		  if(ln < box.options.length)  { 
		  box.options.length -= 1; 
		  BumpUp(box); 
		     } 
		 } 

		function SortD(box)  { 
		 var temp_opts = new Array(); 
		 var temp = new Object(); 
		 for(var i=0; i<box.options.length; i++)  { 
		 temp_opts[i] = box.options[i]; 
		 } 

		 for(var x=0; x<temp_opts.length-1; x++)  { 
		   for(var y=(x+1); y<temp_opts.length; y++)  { 
		     if(temp_opts[x].text > temp_opts[y].text)  { 
		     temp = temp_opts[x].text; 
		     temp_opts[x].text = temp_opts[y].text; 
		     temp_opts[y].text = temp; 
		     temp = temp_opts[x].value; 
		     temp_opts[x].value = temp_opts[y].value; 
		     temp_opts[y].value = temp; 
		        } 
		      } 
		 } 

		 for(var i=0; i<box.options.length; i++)  { 
		 box.options[i].value = temp_opts[i].value; 
		 box.options[i].text = temp_opts[i].text; 
		    } 
		} 
		
//回车事件
		  document.onkeydown=function(event){  
//			  alert(t10);
			 
			 var e = event || window.event || arguments.callee.caller.arguments[0];          
			        if(e && e.keyCode==13 &&  !$("#chooseResult").is(":hidden")){ // enter 键        
			        	 document.getElementById("btnQuery").click();	
						 }      
			  }
		  
		  
		  function goPage(){
			  fengye($("#jumpPageNo").val(),ROWS_ONE_PAGE,'');
		  }
		  
		 
		  $(function(){
				$(".sidebar").height($("#main .content").height());
			});
		  
		  function searchBtnIdQuery(){
			  var key = $("#itcodeSearch").val();
			  if(key=="部门名称或ITCode"){
				  key="";
			  }
			  //查询总页数
				$.ajax({
					type : "post",
					url : webContext + '/userRightsManage/query',
					dataType : 'json',
					data:{
						page:"1",
						rows:"10",
						itcode:key
							},
					async:false,
					success : function(data, textStatus) {
						if(key=="部门名称或ITCode" || key==""){
							$searchTotalPage = data.total;
						}else{
							$searchTotalPage = parseInt(data.total);
						}
					}
				});
			  fengye("1","10",key);
		  }
