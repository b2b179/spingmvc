<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>组别信息修改</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
 	<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.js"></script>
	<style type="text/css">
		*{font: 12px tahoma, Arial, Verdana, sans-serif;}
		.brands label {width: 150px;display: block;float: left;padding-right: 6px;}
		.categoryError{font-size:10px;color:red;}
		.grandSelect,.parentSelect{width:190px;line-height: 24px;height: 24px;}
		.parentTr{display:;}
	</style>
  </head>
  <body>
    <div class="path"><a href="#">首页</a>  » 修改组别信息</div>
	<form id="inputForm"   action="${pageContext.request.contextPath}/groups/updateData" method="post">
		<table class="input">
			<tbody>
				<tr>
					<th><span class="requiredField">*</span>ID:</th>
					<td width="194">${data.groupId }</td>
					<td id="groupIdError"></td>
				</tr>
				<tr>
					<th><span class="requiredField">*</span>名称:</th>
					<td width="194"><input type="text"  name="groupName" value="${data.groupName }" class="text" maxlength="200" id="groupName"></td>
					<td id="groupNameError"></td>
				</tr>
				<tr>
					<th><span class="requiredField">*</span>日期:</th>
					<td width="194">${data.createDate }</td>
					<td id="createDateError"></td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td>
						<input type="hidden"  name="groupId"  id="groupId" value="${data.groupId }"/>		
						<input type="hidden"  name="parent.groupId"  id="pgroupId" value="${data.parent.groupId }"/>								
						<input type="submit"  class="button" value="确&nbsp;&nbsp;定" name="submit">
					</td><td></td>
				</tr>
		</tbody></table>
	</form>
  </body>
</html>