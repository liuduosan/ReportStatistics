var flag = true;
function OnRightClick(event, treeId, treeNode) {
    if (treeNode) {
        var top = $(window).scrollTop();
    
        zTree.selectNode(treeNode);
        if (treeNode.getParentNode()) {
            var isParent = treeNode.isParent;
            if(isParent){//非叶子节点
                showRMenu("firstNode", event.clientX, event.clientY+top);//处理位置，使用的是绝对位置
            }else{//叶子节点
            	  showRMenu("firstNode", event.clientX, event.clientY+top);//处理位置，使用的是绝对位置
//                showRMenu("secondNode", event.clientX, event.clientY+top);
            }
        } else {
            showRMenu("root", event.clientX, event.clientY+top);//根节点
        }
    }
}

function showRMenu(type, x, y) {
    $("#rMenu ul").show();
    if (type == "root") {
        $("#m_add").show();
        $("#m_edit").hide();
        $("#m_editFile").hide();
        $("#m_delete").hide();
    } else if(type == "firstNode"){
        $("#m_add").show();
        $("#m_edit").show();
        $("#m_editFile").hide();
        $("#m_delete").show();
    }else if(type == "secondNode"){
        $("#m_add").hide();
        $("#m_edit").show();
        $("#m_editFile").show();
        $("#m_delete").show();
    }
    
    rMenu.css({
        "top" : y + "px",
        "left" : x + "px",
        "visibility" : "visible"
    });

    //在当前页面绑定 鼠标事件
    $(document).bind("mousedown", onBodyMouseDown);
}

//事件触发 隐藏菜单
function onBodyMouseDown(event) {
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
        rMenu.css({
            "visibility" : "hidden"
        });
    }
}

//隐式 隐藏右键菜单
function hideRMenu() {
    if(rMenu){
        rMenu.css({
            "visibility" : "hidden"
        });
    }
    //取消绑定
    $(document).unbind("mousedown", onBodyMouseDown);
}

//异步加载完成
function onAsyncSuccess(event, treeId, treeNode, msg) {
    
}

//单击节点 显示节点
function menuShowNode() {
    var node = zTree.getSelectedNodes()[0];
    if (node) {
        if(!node.isParent){
            
        }
    } else {
        alert("未找到节点");
    }
}


var rMenu;//右键菜单元素

var zNodes = [{
    id : "0",
    name : "根节点",
    pId : "-1",
    url1 : "",
    icon : "${ctx}/images/menu_p.png",
    isParent : true   //是否是父节点，对象分组的时候，要进行设置
}];

$(document).ready(function() {
    zTree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    rMenu = $("#rMenu");
    
    //展开根节点
    expandNodes(zTree.getNodes());
    
});


//右键菜单 添加节点
function menuAddNode() {
    //隐藏菜单
    hideRMenu();
    
    var node = zTree.getSelectedNodes()[0];
    if (node) {
        pId = node.id;
    } else {
        alert("未找到节点");
    }
}

//右键菜单 编辑节点
function menuEditNode() {
    hideRMenu();
    var node = zTree.getSelectedNodes()[0];
    if (node) {
        
    } else {
        alert("未找到节点");
    }
}

//右键菜单 内容节点
function menuEditContentNode() {
    hideRMenu();
    var node = zTree.getSelectedNodes()[0];
    if (node) {
        
    } else {
        alert("未找到节点");
    }
}

//右键菜单删除节点
function menuDeleteNode() {
    hideRMenu();
    var node = zTree.getSelectedNodes()[0];
    if (node) {
        //easyUI 消息框
        top.$.messager.confirm("删除节点下的所有信息","确定要删除此节点吗？", function (r) {  
            if (r) {  
                        var id = node.getParentNode().id;
                                    $.ajax({
                                        url : node.id,
                                        beforeSend:function(){
                                
                                },
                                        success : function(data) {
                                            var msg = "删除成功";
                                            if ($.trim(data) == "success") {
                                                refreshNode(id);
                                                
                                                //location.href = "about:blank";
                                            } else {
                                                msg = "删除失败";
                                            }
                                            
                                            top.$.messager.show({
                                                showType:'fade',
                                                showSpeed:2000,
                                                msg:msg,
                                                title:'删除操作',
                                                timeout:1
                                            });
                                        }
                                    });
                        }  
                 });  
        } else {
            alert("未找到节点");
        }
}


    //焦点
function focusNode(id) {
    var node = zTree.getNodeByParam("id", id, null);
    if (node) {
        zTree.selectNode(node);
    } else {
        setTimeout(function() {
            focusNode(id);
        }, 10);
    }

}

function expandNodes(nodes) {
    zTree.expandNode(nodes[0], true, false, false);
}

//刷新节点
function refreshNode(id) {
    var node = zTree.getNodeByParam("id", id, null);
    if (node) {
        zTree.reAsyncChildNodes(node, "refresh");
    } else {
        setTimeout(function() {
            refreshNode(id);
        }, 10);
    }
}

//刷新父节点
function refreshParentNode(id) {
    var node = zTree.getNodeByParam("id", id, null);
    var pNode = node.getParentNode();
    if (pNode) {
        refreshNode(pNode.id);
    } else {
        refreshNode("0");
    }
}

//为添加节点刷新
function refreshForAddNode(id) {
    var node = zTree.getNodeByParam("id", id, null);
    zTree.addNodes(node, {
        id : "xx",
        name : "new node"
    });
    zTree.reAsyncChildNodes(node, "refresh");
}
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
					var jsonData = jQuery.parseJSON(data.data);
					$.fn.zTree.init($("#tree"), setting, jsonData);
					$.fn.zTree.init($("#tree2"), setting, jsonData);
				} else {
					
					alert(data.error);
				}
			},
			error : function(xhr, ts, et) {
			}
		});
	}
	flag = true;
	$(".nav ul li span.title").click(function(){		
		$(this).closest("li").find(".ztree").toggle();
	});
	$('.nav ul li span.title').trigger("click");
	$(".menu .flow").click(function(){
		$(".nav .performance-list").hide();
		$(".nav .flow-list").show();
	});
	$(".menu .performance").click(function(){
		$(".nav .performance-list").show();
		$(".nav .flow-list").hide();
	});


	//流程节奏选中部门点击确定按钮
	$(".nav ul.flow-list li .confirm").click(function(){
		var treeObj=$.fn.zTree.getZTreeObj("tree"),
	    nodes=treeObj.getCheckedNodes(true),
	    v="";
		t="";
	    for(var i=0;i<nodes.length;i++){
	    	if(nodes.length-1==i){
	    		v+="'"+nodes[i].departNo2+"'";
	    		t+="'"+nodes[i].deptname+"'";
	    	}else{
	    		v+="'"+nodes[i].departNo2+"'" + ",";
	    		t+="'"+nodes[i].deptname+"'" + ",";
	    	}
	    //获取选中节点的值
	    }
	    console.log(i);
	    console.log(v);
	    console.log(t);
		$(this).closest('li').find(".ztree").hide();
	});

	//业绩达成选中部门点击确定按钮
	$(".nav ul.performance-list li .confirm").click(function(){
		var treeObj=$.fn.zTree.getZTreeObj("tree2"),
	    nodes=treeObj.getCheckedNodes(true),
	    v="";
	    for(var i=0;i<nodes.length;i++){
	    	if(nodes.length-1==i){
	    		v+="'"+nodes[i].departNo2+"'";
	    	}else{
	    		v+="'"+nodes[i].departNo2+"'" + ",";
	    	}
	    //获取选中节点的值
	    }
	    console.log(v)
		$(this).closest('li').find(".ztree").hide();
	});



	//按部门分析
	$(".nav ul.performance-list li.section").click(function(){
		var treeObj=$.fn.zTree.getZTreeObj("tree2"),
	    nodes=treeObj.getCheckedNodes(true),
	    v="";
	    for(var i=0;i<nodes.length;i++){
	    	if(nodes.length-1==i){
	    		v+="'"+nodes[i].departNo2+"'";
	    	}else{
	    		v+="'"+nodes[i].departNo2+"'" + ",";
	    	}
	    //获取选中节点的值
	    }
	    console.log(v)
		$(this).closest('li').find(".ztree").hide();
	});
	//按职级分析
	$(".nav ul.performance-list li.class").click(function(){
		var treeObj=$.fn.zTree.getZTreeObj("tree2"),
	    nodes=treeObj.getCheckedNodes(true),
	    v="";
	    for(var i=0;i<nodes.length;i++){
	    	if(nodes.length-1==i){
	    		v+="'"+nodes[i].departNo2+"'";
	    	}else{
	    		v+="'"+nodes[i].departNo2+"'" + ",";
	    	}
	    //获取选中节点的值
	    }
	    console.log(v)
		$(this).closest('li').find(".ztree").hide();
	});
	//按年龄分析
	$(".nav ul.performance-list li.age").click(function(){
		var treeObj=$.fn.zTree.getZTreeObj("tree2"),
	    nodes=treeObj.getCheckedNodes(true),
	    v="";
	    for(var i=0;i<nodes.length;i++){
	    	if(nodes.length-1==i){
	    		v+="'"+nodes[i].departNo2+"'";
	    	}else{
	    		v+="'"+nodes[i].departNo2+"'" + ",";
	    	}
	    //获取选中节点的值
	    }
	    console.log(v)
		$(this).closest('li').find(".ztree").hide();
	});
	//按职类分析
	$(".nav ul.performance-list li.job").click(function(){
		var treeObj=$.fn.zTree.getZTreeObj("tree2"),
	    nodes=treeObj.getCheckedNodes(true),
	    v="";
	    for(var i=0;i<nodes.length;i++){
	    	if(nodes.length-1==i){
	    		v+="'"+nodes[i].departNo2+"'";
	    	}else{
	    		v+="'"+nodes[i].departNo2+"'" + ",";
	    	}
	    //获取选中节点的值
	    }
	    console.log(v);
		$(this).closest('li').find(".ztree").hide();
	});


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
		onRightClick : OnRightClick,//右键事件
		onAsyncSuccess: onAsyncSuccess,//回调函数，在异步的时候，进行节点处理（有时间延迟的），后续章节处理
        onClick : menuShowNode
		/* rightClick:function(event,treeId,treeNode){
             tree.pNode = treeNode;
             tree.showRightMenu({//显示右键菜单
                 x:event.clientX,   
                 y:event.clientY    
             });
         }*/
	},
	 init:{
         initEvent:{
             initRMenu:function(){
                 $("#rMenu").hover(function(){//设置进入右键菜单事件
                     tree.bindClick($("#r_addFolder"),function(){
                         tree.addFolder();
                     });

                     tree.bindClick($("#r_addNode"),function(){
                         tree.addNode();
                     });

                     tree.bindClick($("#r_updateNode"),function(){
                         tree.updateNode();
                     });

                     tree.bindClick($("#r_deleteNode"),function(){
                         tree.deleteNode();
                     });
                 },function(){//设置离开右键菜单事件
                     tree.hideItem();
                 });                    
             }
         }
     },
     showRightMenu:function(postionJson){
         $("#rMenu").css({//设置右键菜单的位置
             top:postionJson.y + "px",
             left:postionJson.x+2 + "px",
             display:"block"
         });
         if(tree.pNode.id==1){//如果是根节点则禁用“删除”、“修改名称”选项
             tree.showItem(["#r_addFolder","#r_addNode"]);
         }else if(tree.pNode.isParent){//如果是文件夹节点，则显示所有菜单选项
             tree.showItem(["#r_addFolder","#r_addNode","#r_updateNode","#r_deleteNode"]);
         }else{//此选项为节点，则禁用“增加节点”、“增加文件夹”选项
             tree.showItem(["#r_deleteNode","#r_updateNode"]);
         }            
         tree.init.initEvent.initRMenu();//加载菜单选项的事件
     },
     showItem:function(itemArray){//显示某些域
         for(var i = 0; i<itemArray.length; i++){
             $(itemArray[i]).show();
         }
     },
     hideItem:function(itemArray){//隐藏某些域
         if(itemArray==undefined){//如果为传入值，则禁用缺省的域
             tree.hideItem(["#rMenu","#r_addFolder","#r_addNode","#r_updateNode","#r_deleteNode"]);
         }
         for(var i = 0; i<itemArray.length; i++){
             $(itemArray[i]).hide();
         }
     },
     addFolder: function(){//添加文件夹节点
         var folderName = window.prompt("请输入文件夹的名称");
         if(folderName==""){
             alert("操作失败！文件夹的名称不能为空!");
         }else{
             if(folderName!=null){
                 tree.zTree.addNodes(tree.pNode,[{//添加节点
                     id:tree.createNodeId(),
                     pId:tree.pNode.id,
                     name:folderName,
                     isParent:true
                 }]);
             }
         }
     },
     addNode: function(){//添加节点
         var nodeName = window.prompt("请输入节点名称");
         if(nodeName==""){
             alert("操作失败！节点名称不能为空!");
         }else{
             if(nodeName!=null){
                 tree.zTree.addNodes(tree.pNode,[{//添加节点
                     id:tree.createNodeId(),
                     pId:tree.pNode.id,
                     name:nodeName,
                     isParent:false
                 }]);
             }
         }
     },
     updateNode:function(){//更新节点-修改节点名称
         var newName = window.prompt("输入新名称",tree.pNode.name);
         if(newName!=tree.pNode.name && newName!=null && newName!=undefined){
             tree.pNode.name = newName;
             tree.zTree.updateNode(tree.pNode,true);
         }
     },
     deleteNode:function(){//删除节点
         if(tree.pNode.isParent){//判断该节点是否是文件夹节点，并且检查是否有子节点
             if(window.confirm("如果删除将连同子节点一起删掉。请确认！")){                    
                 var parentNode = tree.zTree.getNodeByParam("id",tree.pNode.pid);//获取父节点对象
                 tree.zTree.removeNode(tree.pNode);//移除节点
                 parentNode.isParent = true;//设置父节点为文件夹节点
                 tree.zTree.refresh();
             }
         }else{//该节点为不是文件夹节点
             if(window.confirm("确认要删除?")){
                 var parentNode = tree.zTree.getNodeByParam("id",tree.pNode.pid);
                 tree.zTree.removeNode(tree.pNode);//移除节点
                 parentNode.isParent = true;//设置父节点为文件夹节点
                 tree.zTree.refresh();
             }
         }
     },
     createNodeId:function(){//动态生成节点id。生成策略：在父节点id后追加递增数字
         var nodes = tree.zTree.getNodesByParam("pid",tree.pNode.id);
         if(nodes.length==0){//生成id的算法
             return tree.pNode.id+"1";
         }else{
             return nodes[nodes.length-1].id+1;
         }
     },
     bindClick:function(obj,fn){//绑定click事件
         obj.unbind("click");
         obj.bind("click",fn);
     },
	check : {
		autoChecckTrigger : false,
		chkboxType : {"Y":"s","N":"s"},
		chkStyle : "checkbox",
		enable : true,
		nocheckInherit : false,
		chkDisabledInherit : false
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







