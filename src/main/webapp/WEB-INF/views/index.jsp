<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ft" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>百知CMS后台</title>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.js"></script>
<style type="text/css">
   *{font: 12px tahoma, Arial, Verdana, sans-serif;}
   html,body {width: 100%;height: 100%;overflow: hidden;}
   .garent_tr{backgorund:#fff;color:#000}
   .father_tr{backgorund:#eeefff;color:#000111}
   .son_tr{backgorund:#eeeeee;color:#000222}
</style>
  <script type="text/javascript">
	$(function(){
		var $nav = $("#nav a:not(:last)");
		var $menu = $("#menu dl");
		var $menuItem = $("#menu a");
		$nav.click(function() {
			var $this = $(this);
			$nav.removeClass("current");
			$this.addClass("current");
			var $currentMenu = $($this.attr("href"));
			$menu.hide();
			$currentMenu.show();
			return false;
		});
		$menuItem.click(function() {
			var $this = $(this);
			$menuItem.removeClass("current");
			$this.addClass("current");
		});
		$("#iframe").load(function(){
			$(this).height($(this).contents().find("body").height()+400);
		}); 
	});

  </script>
</head>

<body>
	<table class="main">
		<tbody>
			<tr>
				<th class="logo"><a href="#"> <img src="${pageContext.request.contextPath}/resources/img/header_logo.gif" alt="SHOP++"> </a></th>
				<th>
					<div id="nav" class="nav">
						<ul>
							<li><a href="#product">商品</a></li>
							<li><a href="#order">订单</a></li>
							<li><a href="#member">会员</a></li>
							<li><a href="#content">内容</a></li>
							<li><a href="#statistics">统计</a></li>
							<li><a href="javascript:void(0)" target="_blank">首页</a></li>
						</ul>
					</div>
					<div class="link">
						<a href="javascript:void(0)" target="_blank">官方网站</a>| <a
							href="javascript:void(0)" target="_blank">交流论坛</a>| <a
							href="javascript:void(0)" target="_blank">关于我们</a>
					</div>
					<div class="link">
						<strong><c:if test="${empty CURRENT_USER}">${CURRENT_USER.userName}</c:if></strong> 您好! 
						<a href="javascript:void(0)"target="iframe">[账号设置]</a> <a href="${pageContext.request.contextPath}/back/index_logout">[注销]</a>
					</div></th>
			</tr>
			<tr>
				<td id="menu" class="menu">
					<dl id="product" class="default">
						<dt>系统管理</dt>
						<dd>
							<a href="${pageContext.request.contextPath}/users/queryAll" target="iframe" class="current">用户管理</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath}/users/addData" target="iframe" class="">新增用户</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath}/groups/queryAll" target="iframe" class="">组别管理</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath}/groups/addData" target="iframe" class="">新增组别</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath}/index/updateIndex" target="iframe" class="">更新索引</a>
						</dd>
					</dl>
					</td>
				<td><iframe id="iframe" name="iframe" src="" frameborder="0"></iframe>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
