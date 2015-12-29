/** 
* 联想输入框的实现 
* 
* @param objid 页面输入框对应的obj标签的Id 
* @param flag 联想输入对应的操作,数字,与action中实现对应 
*/ 
function inputThinking(objid, flag) { 
// 输入框父节点Id 
var pid = "lenoveInput"; 

//输入框的Id 
var inputId = objid; 

// 获取输入框宽度，根据宽度自适应 
var width = $("#" + pid).css("width"); 
if(width && width.indexOf('px') > 0) { 
width = width.substring(0, width.indexOf('px')) - 2; 
} else { 
width = 158; 
} 

// 定义联想输入页面HTML, 定义输入框及联想输入页面的状态,状态1表示焦点状态，状态0表示非焦点状态  
var tipHTML = "<div id='thinking' style='background-color:white;position:fixed;left:"
	+(document.getElementById("deptName").offsetLeft+$(".popupLayer")[0].offsetLeft)+
	"px;z-index:999999;border:1px solid #CDE4EF; width:" + 425 + "px;overflow:hidden;overflow:hidden;display:none;background-image: theme/default/imagesmember_right1.png'><li>&nbsp;</li></div><input type='hidden' id='inputId' value='" + inputId + "'><input type='hidden' id='status' value='1'><input type='hidden' id='thinking_status' value='0'><style>li.sel{background:#4d77bb;color:white;}li{font-size:14px;height:18px;overflow:hidden;}</style>" 

/********** 将联想输入页面嵌入原始页面 **********/ 
$("#" + inputId).parent().append(tipHTML); 

/********** 绑定输入框事件 **********/ 

// 输入框内容修改事件 
$("#" + inputId).bind("keyup", function(e) { 
if (e.keyCode == 38) { 
// 向上 
next(false); 
} else if(e.keyCode == 40) { 
// 向下 
next(true); 
} else { 
showThinking(inputId, flag); 
} 
}); 

// 单击事件,将输入框的状态置为1 
$("#" + inputId).bind("click", function() { 
// 设置输入框的状态为1 
$("#status").val(1); 

showThinking(inputId, flag); 
}); 

// 单击事件,将输入框的状态置为1 
$("#" + inputId).bind("focus", function() { 
// 设置输入框的状态为1 
$("#status").val(1); 

showThinking(inputId, flag); 
}); 

// 失去焦点事件,将输入框的状态置为0 
$("#" + inputId).bind("focusout", function() { 
// 设置输入框的状态为0 
$("#status").val(0); 

// 当联想输入页面和输入框的状态均为0时隐藏联想输入页面 
if($("#thinking_status").val() && $("#thinking_status").val() == 0) { 
$("#thinking").css("display", "none"); 
} 
}); 

/********** 绑定联想输入页面事件 **********/ 

// 鼠标移动事件 
$("#thinking").bind("mousemove", function() { 
// 设置联想输入页面的状态为1 
$("#thinking_status").val(1); 
}); 

// 鼠标移出事件 
$("#thinking").bind("mouseout", function() { 
// 设置联想输入页面的状态为0 
$("#thinking_status").val(0); 

// 当联想输入页面和输入框的状态均为2时隐藏联想输入页面 
if($("#status").val() && $("#status").val() == 0) { 
$("#thinking").css("display", "none"); 
} 
}); 

/********** end **********/ 
} 

function optionClick(a){
//	var sTemp = a.innerHTML
//	sTemp = sTemp.substr(sTemp.indexOf(a),sTemp.length)
//	sTemp = sTemp.substr(sTemp.indexOf(">"),sTemp.indexOf("option"))
//	alert(sTemp);
	var options = $(this);
	for (var int = 0; int < deptInfo.length; int++) {
		var obj  =  deptInfo[int];
		if(obj.departNo2==a.value){
			$("#deptName").val(obj.deptname);
			break;
		}
	}
	$("#codeNumber").html(a.value);
	$("#deptNameHidden").val(a.value);
}
/** 
* 根据状态刷新数据，并显示联想输入框 
* 
* @param inputId 页面input输入框对应的Id 
* @param flag 联想输入对应的操作,数字,与action中实现对应 
*/ 
function showThinking(inputId, flag) { 
var value = $.trim($("#" + inputId).val()); 
// 只有输入框非空的时候才调用输入联想 
if(value && value != null && value != '') { 
// 发送ajax初始化输入联想页面 style='width:426px;height: 269px;'
	var htmlStr = " &nbsp;&nbsp;&nbsp;<select  style='width: 200px;' onmouseleave='optionClick(this)'>" ;
	for(var i=0; i<deptInfo.length; i++){
		var objTemp = deptInfo[i];
		var flatCode = objTemp.flatCode;
		var nodeName = objTemp.deptname;
		if(flatCode==null){
			flatCode = "";
		}
		if(nodeName.indexOf($("#deptName").val())!=-1){
			htmlStr+="<option onclick='optionClick(this)'  value='"+objTemp.departNo2+"' >"+nodeName+"</option>" ; 
		}
	}
	
	htmlStr+="</select>";
	
	$("#thinking").html(htmlStr);
	$("#chooseDept").html(htmlStr);
	//selectThisEnt("aaaa")
	// 显示联想输入页面 
//	$("#thinking").css("display", "block"); 
} else { 
		$("#thinking").html(""); 
} 
} 

function next(flag) { 
var obj = get($("#thinking li.sel"), 0); 
var next = null; 
if(obj) { 
next = get(flag? $(obj).next("li"): $(obj).prev("li"), 0); 
} else { 
next = flag? $("#thinking li").first(): $("#thinking li").last(); 
} 
if(next) { 
if(obj) { 
$(obj).removeClass("sel"); 
} 
$(next).addClass("sel"); 
} 
} 

function get(array, index) { 
if(array.length && array.length > index) { 
return array[index]; 
} else { 
return null; 
} 
} 

/** 
* 将选择的数据填充到输入框 
* 
* @param name 显示的数据内容 
*/ 
function selectThisEnt(name) { 
$("#deptName").val(name); 
//$("#deptName").change(); // 触发change事件，更新输入联想提示内容 
$("#thinking").css('display', 'none'); 
} 

/** 
* 将选择的节点数据填充到输入框 
* 
* @param obj 选择的节点 
*/ 
function selectThinking(obj) { 
$("#" + $("#inputId").val()).val($(obj).text()); 
$("#" + $("#inputId").val()).change(); // 触发change事件，更新输入联想提示内容 
$("#thinking").css('display', 'none'); 
} 
