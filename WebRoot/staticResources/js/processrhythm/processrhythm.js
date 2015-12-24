var flag = true;
$(function(){
	$(".sidebar").height($("#main .content").height());
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
					$.fn.zTree.init($("#ztreeSidebar"), setting, jsonData);
					var treeObj = $.fn.zTree.getZTreeObj("ztreeSidebar");
					var idsString = data.checkedNodes;
					var ids = idsString.split(",");
					for (var g = 0; g <ids.length; g++) {
						var id = ids[g];
						var node2= treeObj.getNodeByParam("departNo2", id, null);
						treeObj.showNode(node2);
						treeObj.checkNode(node2, true, true);
					}
					var nodes = treeObj.getNodes();
					var checkedNodes = treeObj.transformToArray(nodes);
					for (var int = 0; int < checkedNodes.length; int++) {
						var obj = checkedNodes[int];
						if(obj.checked==false){
							treeObj.hideNodes( treeObj.transformTozTreeNodes(obj)); 
						}
					}
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


//根据条件查询流程节奏报表
function query(){
	var treeObj=$.fn.zTree.getZTreeObj("ztreeSidebar"),
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
	
	//获取季度
	var quarter=$("#quarter option:selected").val();
	//获取月份
	var month=$("#month option:selected").val();
	//获取日期
	var date=$("#date").val();
	if(v==''){
		layer.alert('部门不能为空!', function(index) {
			layer.close(index);
		});
		return false;
	}
	if(date==''){
		layer.alert('历史数据时点不能为空!', function(index) {
			layer.close(index);
		});
		return false;
	}
	
	//柱状图
	$.ajax({
		type : "post",
		url : webContext + '/TableName/getByDeptNoProcessrhythmBar',
		dataType : 'json',
		data : {deptno:v,quarter:quarter,month:month,time:date},
		async:false,
		success : function(data, textStatus) {
			if (data.success) {
				var name = $.parseJSON(data.name);
				var value = $.parseJSON(data.value);
				var myChart1 = echarts.init(document.getElementById('main1'));
				myChart1.setOption({
					 title : {
					        text: '统计流程节奏所处环节比例',
					        subtext: '绩效考核系统'
					    },
						grid:{
							x:180,
							y:60
						},
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					    	data : []
					    },
					    toolbox: {
					        show : true,
					        x:'right',
					        y:'top',
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            magicType: {show: false},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'value',
					            boundaryGap : [0, 0.01],
					            axisLabel:{					            	
						        	margin:16
						        }
					        }					        
					    ],
					    yAxis : [
					        {
					            type : 'category',
					            data : name
					        }
					    ],
					    series : [
					        {
					            type:'bar',
					            data:value,
					            itemStyle: {
					                normal: {
					                	color: function(params) {
					                        // build a color map as your need.
					                       // var colorList = ['#4fc4f6','##4b5cc4','#99072b','#3b2e7e','#be002f','#8d4bbb','#e62545','#1685a9','#e64c65','#c392cb'];
					                        var colorList = ['#4fc4f6','#4b5cc4','#99072b','#3b2e7e','#be002f','#8d4bbb','#e62545','#1685a9','#e64c65','#c392cb'];
					                        return colorList[params.dataIndex]
					                    },
					                    label: {
					                        show: true,
					                        position: 'right',
					                        formatter: '{c}'
					                    }
					                }
								 }
					        }
					    ]
				});
			
				
			} else {
				alert(data.error);
			}
		},
		error : function(xhr, ts, et) {
		}
	});
	
	//饼状图
	$.ajax({
		type : "post",
		url : webContext + '/TableName/getByDeptNoProcessrhythmPie',
		dataType : 'json',
		data : {deptno:v,quarter:quarter,month:month,time:date},
		async:false,
		success : function(data, textStatus) {
			if (data.success) {
				var jsonData = $.parseJSON(data.data);
				var myChart2 = echarts.init(document.getElementById('main2'));
				myChart2.setOption({
					title : {
						text : '统计流程节奏所处环节比例',
						subtext : '绩效考核系统',
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						orient : 'vertical',
						x : 'left',
						data : []
					},
					toolbox : {
						show : true,
				        x:'right',
				        y:'top',
						feature : {
							mark : {
								show : true
							},
							dataView : {
								show : true,
								readOnly : false
							},
							magicType : {
								show : false						
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					calculable : true,
					series : [ {
						name : '访问来源',
						type : 'pie',
						radius : '55%',
						center : [ '50%', '65%' ],
						data : jsonData,
						 itemStyle: {
			                normal: {
			                    label: {
			                        show: true,
			                        position: 'outer',
			                        formatter: '{b} {d}%'
			                    },
								labelLine: {
						            show: true,
						            length:30
						        }
			                }
						 }
						 
						
					} ],
					color:['#4fc4f6','#4b5cc4','#99072b','#3b2e7e','#be002f','#8d4bbb','#e62545','#1685a9','#e64c65','#c392cb']
				});
				
			} else {
				alert(data.error);
			}
		},
		error : function(xhr, ts, et) {
		}
	});
}

$("#quarterOrMonthSelect").on("change",function(){
	if($(this).val()=="月份"){
		var month='<select id="month"><option value="" selected="selected">全部</option><option value="1">1月</option><option value="2">2月</option><option value="3">3月</option><option value="4">4月</option><option value="5">5月</option><option value="6">6月</option><option value="7">7月</option><option value="8">8月</option><option value="9">9月</option><option value="10">10月</option><option value="11">11月</option><option value="12">12月</option></select>';
		$("#quarterOrMonthBox").html(month);
	}else{
		var quarter='<select id="quarter"><option value="" selected="selected">全部</option><option value="1">第一季度</option><option value="2">第二季度</option><option value="3">第三季度</option><option value="4">第四季度</option></select>';
		$("#quarterOrMonthBox").html(quarter);
	}
});

