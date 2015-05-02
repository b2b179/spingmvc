<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/ajaxfileupload.js"></script>
	<style type="text/css">
		*{font: 12px tahoma, Arial, Verdana, sans-serif;}
		.brands label {width: 150px;display: block;float: left;padding-right: 6px;}
		.categoryError{font-size:10px;color:red;}
		.grandSelect,.parentSelect{width:190px;line-height: 24px;height: 24px;}
		.parentTr{display:;}
	</style>
	<title>Image File Upload</title>
</head>
<body>
	<div style="width:300px;height:400px;background:#eee;margin: auto;margin-top:50px;">
		<form action="${pageContext.request.contextPath}/images/uploadFile" enctype="multipart/form-data" method="post">
		<table>
			<tr><th>姓名：</th><td><input type="text" name="user.userName"/></td></tr>
			<tr><th>年龄：</th><td><input type="text" name="user.age"/></td></tr>
			<tr><th>邮箱：</th><td><input type="text" name="user.email"/></td></tr>
			<tr><th>图片：</th>
				<td>
					<input type="file" name="uploadFile" class="inp_fileToUpload" id="uploadFile"/>
					<input type="hidden" name="image" id="image"/>
				</td>
			</tr>
			<tr><th>预览：</th><td id="showImage"></td></tr>
			<tr><td colspan="2">
				<input type="submit" name="submit" value="submit"/></td>
			</tr>
		</table>
		</form>
	</div>
</body>
<script type="text/javascript">
		$(function() { 
			$(".inp_fileToUpload").live("change", function() {//现在这个已经适用于多个file表单。 
				ajaxFileUpload($(this).attr("id"), $("#showImage").attr("id")); 
			}) 
		}) 
		function ajaxFileUpload(file_id, img_id) { 
			jQuery.ajaxFileUpload({ 
				url : "${pageContext.request.contextPath}/images/uploadFile", //用于文件上传的服务器端请求地址 
				secureuri : false, //是否需要安全协议，一般设置为false 
				fileElementId : file_id, //文件上传域的ID 
				dataType : 'json', //返回值类型 一般设置为json 
				data:{'deleteImg':$('#image').val()},
				success : function(jsonData, status){ //服务器成功响应处理函数 
					if (jsonData.result) {
						var data = jsonData.data[0];
						var url = "${pageContext.request.contextPath}/";
						url += data.url.substring(1);
						$("#showImage").append("<img>");
						$("#showImage>img").css("width",'60px');
						$("#showImage>img").css("height",'60px');
						$("#showImage>img").attr("src",url);
						$('#image').val(url);//获取新的图片
					} else { 
						alert(data.msg); 
					} 
				}, 
				error : function(data, status, e){ //服务器响应失败处理函数 
					alert(e); 
				} 
			}) 
			return false; 
		} 
	</script>
</html>