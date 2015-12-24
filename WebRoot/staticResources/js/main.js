/* header nav 切换效果 */
/*$("#header .nav ul li").click(function(){
	if(!$(this).hasClass('active')){
		$(this).addClass('active').siblings().removeClass('active');
	}
});*/
$(function(){
	var index=$("#type").val();
	if(!$("#header .nav ul li").eq(index).hasClass("active")){
		$("#header .nav ul li").eq(index).addClass('active').siblings().removeClass('active');
	}
});

function check(){
	$("li").removeClass('active');
	$("#export").addClass('active');
}