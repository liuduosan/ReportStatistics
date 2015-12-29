$(function() {
	// 加载上传
	var path = webContext + "/staticResources/common/flexfile/";
	var flashvars = {
		filefilter : "*.xls;*.xlsx",
		callback : "addInsuranceTemplate",
		filetype : "Excel文件",
		app : webContext,
		sizelimit : 50000000,
		sid : 0
	};
	swfobject.embedSWF(path + "FlexFile.swf", "div-addInsuranceTemplate",
			"150", "23", "9.0.0", path + "expressInstall.swf", flashvars);
	
});


function addInsuranceTemplate(fileTitle, filePath) {
	$('#InsuranceTemplate').text(fileTitle);
	if (filePath == null || filePath == "") {
		layer.alert('上传失败', function(index) {
			layer.close(index);
		});
		return;
	} else {
		var attachLength = $("[name='attach']").length;

		$("#InsuranceTemplate").html(fileTitle);
		$("#attach_span").html(
				"<a id=\"" + filePath + "\" href=\"javascript:removeItem('"
						+ filePath + "')\">删除<input name='attach' value='"
						+ filePath + "' type='hidden'/></a>");
		$("#batchAddingButton").show();
	}
}
function removeItem(id) {
	$("[id='" + id + "']").remove();
	$("#InsuranceTemplate").html("");
}
function batchAddingButton() {
	if ($("[name='attach']").length <= 0) {
		layer.alert('请上传附件', function(index) {
			layer.close(index);
		});
		return;
	}
	var filePath = $("[name='attach']").val();
	if (filePath == "") {
		layer.alert('请上传附件', function(index) {
			layer.close(index);
		});
		return;
	}
/*	$("#batchAddingButton").attr("disabled", "disabled");
*/
	$.ajax({
		type : "post",
		url : webContext + '/upload1/uploaddeal',
		dataType : 'json',
		data : {
			fileName : filePath
		},
		success : function(data, textStatus) {
			if (data.success) {
				$("#pType4").html('');
				$("#pType4").html('Loading...');
				var html ="<table width=\"750\" cellspacing=\"1\">";
				 html += "<tr>"
	        		  +"<td>itcode</td>"	        		  
	        		  +"<td>部门编号</td>"	        		  
	        		  +"<td>作者itcode</td>"	        		  
	        		  +"<td>描述</td>"	        		  
	        		  +"<td>错误信息</td>"	        		  
	        		  +"</tr>";	 
				 $.each(data.data,function(i,item){
	 	        	  html += "<tr>"
	 	        		  +"<td>"+item.itCode+"</td>"	        		  
	 	        		  +"<td>"+item.deptNO+"</td>"	        		  
	 	        		  +"<td>"+item.author+"</td>"	        		  
	 	        		  +"<td>"+item.description+"</td>"	        		  
	 	        		  +"<td>"+item.postCode+"</td>"	        		  
	 	        		  +"</tr>";	 	        	 
	        	  });
				 html+="</table>";
	        	 $("#pType4").html(html);
			} else {
				$('#batchAddingButton').removeAttr("disabled");
				$("#tips").html('');
				$("#tips")
						.html(
								"<font color='red'>错误信息:</font><br>"
										+ "<font color='red'>" + data.error
										+ "</font>");
			}
		},
		error : function(xhr, ts, et) {
			layer.alert('上传失败', function(index) {
				layer.close(index);
			});
		}
	});

}
