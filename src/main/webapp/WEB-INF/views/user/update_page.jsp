<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>用户信息修过</title>
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
  </head>
  <body>
    <div class="path"><a href="#">首页</a>  » 修改用户信息</div>
	<form id="inputForm"   action="${pageContext.request.contextPath}/users/updateData" method="post">
		<table class="input">
			<tbody>
				<tr>
					<th>组别:</th>
					<td>
					<select class="grandSelect" name="group.groupId">
						<option value="0">--请选择--</option>
						<c:forEach items="${groupList }" var="group">
							<option value='${group.groupId}' <c:if test="${group.groupId==data.group.groupId}">selected=true</c:if>>${group.groupName}</option>
						</c:forEach>
					</select>
					</td><td></td>
				</tr>
				<tr>
					<th><span class="requiredField">*</span>名称:</th>
					<td width="194"><input type="text"  name="userName" value="${data.userName}" class="text" maxlength="200" id="userName"></td>
					<td id="userNameError"></td>
				</tr>
				<tr>
					<th><span class="requiredField">*</span>年龄:</th>
					<td width="194"><input type="text"  name="age" value="${data.age}" class="text" maxlength="200" id="age"></td>
					<td id="ageError"></td>
				</tr>
				<tr>
					<th><span class="requiredField">*</span>邮箱:</th>
					<td width="194"><input type="text"  name="email" value="${data.email }" class="text" maxlength="200" id="email"></td>
					<td id="emailError"></td>
				</tr>
				<tr>
					<th><span class="requiredField">*</span>图片:</th>
					<td width="194">
						 <input  class="inp_fileToUpload" name="uploadFile" type="file" id="uploadFile" class="inp_fileToUpload" multiple="multiple"/> 
					</td>
					<td id="ageError">
					 图片预览：<img src="${data.image}" class="img_upload" id="img" style=" <c:if test="${ empty data.image || data.image==null}">display:none;</c:if>width:120px;height:120px;"/> 
					</td>
				</tr>
				<tr>
					<th><span class="requiredField">*</span>介绍:</th>
					<td width="194"><textarea rows="3" cols="30" name="description">${data.description}</textarea></td>
					<td id="descriptionError"></td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td>
						<input type="hidden"  name="image"  id="imageId" value="${data.image}"/>					
						<input type="hidden"  name="userId"  id="userId" value="${data.userId}"/>					
						<input type="submit"  class="button" value="确&nbsp;&nbsp;定" name="submit">
					</td><td></td>
				</tr>
		</tbody></table>  
	</form>
 	<script type="text/javascript">
		$(function() { 
			$(".inp_fileToUpload").live("change", function() {//现在这个已经适用于多个file表单。 
				ajaxFileUpload($(this).attr("id"), "img"); 
			}) 
		}) 
		function ajaxFileUpload(file_id, img_id) { 
			jQuery.ajaxFileUpload({ 
				url : "${pageContext.request.contextPath}/images/uploadFile", //用于文件上传的服务器端请求地址 
				secureuri : false, //是否需要安全协议，一般设置为false 
				fileElementId : file_id, //文件上传域的ID 
				dataType : 'json', //返回值类型 一般设置为json 
				success : function(jsonData, status){ //服务器成功响应处理函数 
					if (jsonData.result) {
						var data = jsonData.data[0];
						var url = "${pageContext.request.contextPath}/";
						url += data.url.substring(1);
						$("#" +img_id).attr("src", url); 
						$("#" +img_id).css("display", ''); 
						$("#imageId").val(url);
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
  </body>
</html>