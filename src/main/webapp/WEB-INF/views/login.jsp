<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>ix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>管理中心</title>
			
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="Login page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
	<link rel="icon" href='<s:url value="/back/img/favicon.ico"/>' type="image/x-icon" />
	<link rel="stylesheet" href='<s:url value="/back/css/common.css"/>' type="text/css"></link>
	<link rel="stylesheet" href='<s:url value="/back/css/login.css" />' type="text/css"></link>
	<script type="text/javascript" src='<s:url value="/back/script/jquery.js"/>'></script>
	<script type="text/javascript" src='<s:url value="/back/script/common.js"/>'></script>
	<style type="text/css">
		.usernameError,.passwordError,.securityCodeError {width:110px;font-size:12px;color:red;}
		.checkData{display:none;}
	</style>
</head>
<body>
		<div class="login">
			<form id="loginForm" action="<s:url value="/back/index_login.action" />" method="post" >
				<table>
					<tbody>
						<tr>
							<td width="130" rowspan="2" align="center" valign="bottom"><img src="<s:url value="/back/img/header_logo.gif" />" alt="购物"/></td>
							<th>用户名:</th>
							<td><input type="text" id="username" name="username" class="text" value="" maxlength="20"/></td>
							<td  class="usernameError"><s:property value="usernameError"/></td>
					  </tr> <tr>
							<th>密&nbsp;&nbsp;&nbsp;码:</th>
							<td><input type="password" id="password" name="password" class="text" value="" maxlength="20" autocomplete="off"/></td>
							<td  class="passwordError"><s:property value="passwordError"/></td>
					  </tr><tr>
							<td>&nbsp;</td>
							<th>验证码:</th>
							<td>
								<input type="text" id="securityCode" name="securityCode" class="text captcha" maxlength="4" autocomplete="off"/>
								<img id="captchaImage" class="captchaImage" src="<s:url value="/safeCodeAction_getCode.action" />" title="点击更换验证码"/>
							</td>
							<td class="securityCodeError"><s:property value="securityCodeError"/></td>
						</tr>					
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td><label><input type="checkbox"  id="isRememberUsername" value="true"/> 记住用户名</label></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<input type="button" class="homeButton" value="" onclick="location.href='<s:url value="index.action"/>'">
							<input type="submit" name="submit" class="loginButton" value="登录">
						</td>
					</tr>
				</tbody></table>
				<div class="powered">COPYRIGHT © 2005-2013.</div>
				<div class="link">
					<a href="<s:url value="/front"/>">前台首页</a> |
					<a href="#">官方网站</a> |
					<a href="#">交流论坛</a> |
					<a href="#">关于我们</a> |
					<a href="#">联系我们</a> |
					<a href="#">授权查询</a>
				</div>
			</form>
		</div>
	<script type="text/javascript">
		$(function(){
			//点击更换验证码：
			$("#captchaImage").click(function(){//点击更换验证码
				$(this).attr('src', '<s:url value="/safeCodeAction_getCode.action" />?a='+Math.random());
				$('.securityCodeError').html('');
			});
			$("#securityCode").on('keydown',function(event){
		          if(event.which == 13){     //13等于回车键(Enter)键值,ctrlKey 等于 Ctrl
		        	 $('.securityCodeError').html('');
		        	 $("#loginForm").trigger('submit');
		          }
			});
			$("#securityCode").on("blur",function(){//验证验证码
				if($(this).val()==undefined||$(this).val()==null || $(this).val().length<4){
					$('.securityCodeError').html('请输入验证码');
				}else{
					$.ajax({
						type:'post',
						url :'<s:url value="/safeCodeAction_checkCode.action" />',
						data:'securityCode='+$('#securityCode').val(),
						success:function(data){
							if(data.result && data.result == true){//验证码正确
								addCheckData("securityCode");
							}else{//验证码错误
								$('.securityCodeError').html('验证码错误');
							}
						},
						dataType:'json'
					});
				}
			});
			$("#securityCode").on("keyup",function(){//验证验证码
				if($(this).val()!=undefined && $(this).val()!=null && $(this).val().length==4){
					$.ajax({
						type:'post',
						url :'<s:url value="/safeCodeAction_checkCode.action" />',
						data:'securityCode='+$('#securityCode').val(),
						success:function(data){
							if(data.result && data.result == true){//验证码正确
								addCheckData("securityCode");
							}else{//验证码错误
								$('.securityCodeError').html('验证码错误');
							}
						},
						dataType:'json'
					});
				}
			});
			$("#password").on("blur",function(){//验证密码
				if($(this).val()==undefined || $(this).val()==null || $(this).val().length<1){
					$('.passwordError').html('请输入密码');
				}else if($(this).val().length<6){
					$('.passwordError').html('密码长度最少6位');
				}else{//password is not null
					addCheckData("password");
				}
			});
			$("#username").on("blur",function(){//验证用户名
				if($(this).val()==undefined||$(this).val()==null || $(this).val().length<1){
					$('.usernameError').html('请输入用户名');
				}else{
					$.ajax({
						type:'post',
						url :'<s:url value="/back/index_checkUserName.action" />',
						data:'username='+$('#username').val(),
						success:function(data){
							if(data.result){//存在该用户
								addCheckData("username");
							}else{//用户名不存在
								$('.usernameError').html('该用户不存在');
							}
						},
						dataType:'json'
					});
				}
			});
			function addCheckData(valData){
				if(valData ==null || valData == undefined)return false;
				if($('.checkData').length<1){
					var checkData = $("<input type='text'>");
					checkData.addClass("checkData");
					checkData.appendTo($(document.body));
				}
				var cvd = $('.checkData').val().replace(valData+',','');//防止重复添加
				$('.checkData').val((cvd ==null||cvd.length<1) ? valData+"," : (cvd+valData));
			}
			$("#password").on("focus",function(){//clear error message
				$('.passwordError').html('');
			})
			$("#username").on("focus",function(){//clear error message
				$('.usernameError').html('');
			})
			$("#securityCode").on("focus",function(){//clear error message
				$('.securityCodeError').html('');
			})
			//  form 表单提交
			$("#loginForm").bind("submit",function(){
				if($('.checkData').length<1){
					var data = ['username','password','securityCode'];
					for(var i=0;i<data.length;i++){
						var errormsg = $('.'+data[i]+'Error').html();
						if(errormsg ==null || errormsg==undefined || errormsg.length<1) $('.'+data[i]+'Error').html("还未输入");
					}
					return false;
				}else{
					var data = $('.checkData').val().split(',');
					if(data.toString().indexOf('securityCode')<0){
						$('.securityCodeError').html('请输入验证码');
						return false;
					}
					return true;
				}
				return false;
			});
		});
	</script>
</body>
</html>