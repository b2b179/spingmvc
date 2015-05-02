<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ft" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>组别管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.js"></script>
	<style type="text/css">
		*{font: 12px tahoma, Arial, Verdana, sans-serif;}
	   .grand_tr{background:#fff;color:#000}
	   .father_tr{background:#eee;color:#000111}
	   .son_tr{background:#ddd;color:#000222}
	   .list img{width:60px;height:60px;}
	</style>
  </head>
  <body>
  	<div class="path">
		<a href="">首页</a> »组别列表 <span>(共<span id="pageTotal">${pageBean.countData}</span>条记录)</span>
	</div>
	<form id="listForm" action="${pageContext.request.contextPath}/groups/queryAll" method="get">
		<div class="bar">
			<a href="${pageContext.request.contextPath}/groups/addData" class="iconButton"> <span class="addIcon">&nbsp;</span>添加</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton"><span class="deleteIcon">&nbsp;</span>删除 </a> 
				<a href="javascript:;" id="refreshButton" class="iconButton"><span class="refreshIcon">&nbsp;</span>刷新</a>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button"> 每页显示 <span id="showPageSize">${pageBean.pageSize }</span> 条 <span class="arrow">&nbsp;</span> </a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li><a href="javascript:;" <c:if test="${pageBean.pageSize ==10}">class="current"</c:if> >10</a></li>
							<li><a href="javascript:;" <c:if test="${pageBean.pageSize==2}">class="current"</c:if> >2</a></li>
							<li><a href="javascript:;" <c:if test="${pageBean.pageSize==3}">class="current"</c:if> >3</a></li>
							<li><a href="javascript:;" <c:if test="${pageBean.pageSize==4}">class="current"</c:if> >4</a></li>
							<li><a href="javascript:;" <c:if test="${pageBean.pageSize==5}">class="current"</c:if> >5</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span> 
					<input type="text" id="searchValue" name="search" value="${search}" maxlength="200">
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li><a href="javascript:;">名称</a></li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
		<tbody>
			<tr>
				<th class="check"><input type="checkbox" id="selectAll"></th>
				<th><span>ID</span></th>
				<th><span>名称</span></th>
				<th><span>创建日期</span></th>
				<th><span>操作</span></th>
			</tr>
		<c:forEach items="${pageBean.data}"  var="group">
			<tr class="grand_tr">
				<td><input type="checkbox" name="ids" value="${group.groupId }"></td>
				<td>${group.groupId }</td>
				<td>${group.groupName }</td>
				<td>${group.createDate }</td>
				<td><a href="${pageContext.request.contextPath}/groups/updateData/${group.groupId}">[编辑]</a></td>
			</tr>
		</c:forEach>
			</tbody>
		</table>
		<input type="hidden" id="pageSize" name="pageSize" value="${pageBean.pageSize}">
		<input type="hidden" id="countPages" name="countPages" value="${pageBean.countPages}">
		<input type="hidden" id="currentPage" name="currentPage" value="${pageBean.page }">
		<div class="pagination">
			<c:if test="${pageBean.page<2}">
				<a class="firstPage no-firstPage" href="javascript:void(0);">&nbsp;</a>
				<a class="previousPage no-previousPage" href="javascript:void(0);">&nbsp;</a>
			</c:if>
			<c:if test="${pageBean.page>1 }">
				<a class="firstPage" href="javascript: $.pageSkip(1);">&nbsp;</a>
				<a class="previousPage" href="javascript: $.pageSkip(${pageBean.page-1});">&nbsp;</a>
			</c:if>
			<c:forEach var="pg" begin="${pageBean.showStartPage}" end="${pageBean.showEndPage}" step="1">
				<c:if test="${pageBean.page != pg}">
				<a id="page${pg}" href="javascript:$.pageSkip(${pg});">${pg}</a>
				</c:if>
				<c:if test="${pageBean.page == pg}"><a id="page${pg}" class="currentPage" href="javascript:void(0);" >${pg}</a></c:if>
			</c:forEach>
			<span class="pageBreak">...</span>
			<c:if test="${pageBean.countPages<2 }">
				<a class="nextPage no-nextPage" href="javascript:void(0);">&nbsp;</a>
				<a class="lastPage no-lastPage" href="javascript:void(0);">&nbsp;</a>
			</c:if>
			<c:if test="${pageBean.countPages>1}">
				<a class="nextPage" href="javascript: $.pageSkip(${pageBean.page+1});">&nbsp;</a>
				<a class="lastPage" href="javascript: $.pageSkip(${pageBean.countPages});">&nbsp;</a>
			</c:if>
			<span class="pageSkip"> 共${pageBean.countPages}页 到第<input id="pageNumber" name="pageNumber" value="1" maxlength="9" onpaste="return false;">页<button type="button" id="jumpPage">&nbsp;</button>
			</span>
		</div>
	</form>
</body>
 <script type="text/javascript">
		$().ready(function() {
			var $listForm = $("#listForm");
			
			var $pageSizeSelect=$("#pageSizeSelect");
			var $pageSizeOption = $("#pageSizeOption a");
			
			var $pageSize = $("#pageSize");
			var $pageNow = $("#pageNow");
			
			var $searchPropertySelect = $("#searchPropertySelect");
			var $searchPropertyOption = $("#searchPropertyOption a");
			
			var $refreshButton = $("#refreshButton");
			var $deleteButton = $("#deleteButton");
			var $selectAll = $("#selectAll");
			var $ids = $("#listTable input[name='ids']");
			
			// 每页记录数选项
			$pageSizeSelect.mouseover( function() {
				var $this = $(this);
				var offset = $this.offset();
				var $menuWrap = $this.closest("div.menuWrap");
				var $popupMenu = $menuWrap.children("div.popupMenu");
				$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
				$menuWrap.mouseleave(function() {
					$popupMenu.hide();
				});
			});
			// 每页记录数
			$pageSizeOption.click( function() {
				var pageSize  = $(this).html();
				$('#pageSize').val(pageSize);
				window.location.href='${pageContext.request.contextPath}/groups/queryAll?page='+$('#currentPage').val()+"&pageSize="+$('#pageSize').val()+"&search="+encodeURI($('#searchValue').val(),'UTF-8');
			});
			
			// 搜索选项
			$searchPropertySelect.mouseover( function() {
				var $this = $(this);
				var offset = $this.offset();
				var $menuWrap = $this.closest("div.menuWrap");
				var $popupMenu = $menuWrap.children("div.popupMenu");
				$popupMenu.css({left: offset.left - 1, top: offset.top + $this.height() + 2}).show();
				$menuWrap.mouseleave(function() {
					$popupMenu.hide();
				});
			});
			// 搜索选项
			$searchPropertyOption.click( function() {
				var $this = $(this);
				$searchPropertyOption.removeClass("current");
				$this.addClass("current");
				return false;
			});
			// 刷新
			$refreshButton.click( function() {
				$('#listForm').trigger('submit');
			});
			// 页码跳转
			$.pageSkip = function(pageNumber) {
				if(pageNumber !=null && pageNumber>0){
					var $tempVal = $('#currentPage').val();//原始页面
					$('#currentPage').val(pageNumber);//跳转页面
					$.getJSON(
						'${pageContext.request.contextPath}/groups/operactorByAjax',
						{'page':$('#currentPage').val(),'pageSize':$('#pageSize').val(),'command':'queryAll','search':encodeURI($('#searchValue').val(),'UTF-8')},
						function(jsonBean){
							pageBean  = jsonBean.data[0];
							$('#page'+$tempVal).removeClass('currentPage');
							if(pageNumber>1){//要调转的页面大于1
								$('.firstPage').attr('href','javascript:$.pageSkip(1);');
								$('.previousPage').attr('href','javascript:$.pageSkip('+(pageNumber-1)+');');
								$('.firstPage').removeClass('no-firstPage');
								$('.previousPage').removeClass('no-previousPage');
							}else{
								$('.firstPage').attr('href','javascript:void(0);');
								$('.firstPage').addClass('no-firstPage');
								$('.previousPage').attr('href','javascript:void(0);');
								$('.previousPage').addClass('no-previousPage');
							}
							var countPages  = $('#countPages').val();
							if(pageNumber<countPages){
								$('.nextPage').attr('href','javascript:$.pageSkip('+(pageNumber+1)+');');
								$('.lastPage').attr('href','javascript:$.pageSkip('+countPages+');');
								$('.nextPage').removeClass('no-nextPage');
								$('.lastPage').removeClass('no-lastPage');
							}else{
								$('.nextPage').attr('href','javascript:void(0);');
								$('.lastPage').attr('href','javascript:void(0);');
								$('.nextPage').addClass('no-nextPage');
								$('.lastPage').addClass('no-lastPage');
							}
							$('#page'+$tempVal).attr('href','javascript:$.pageSkip('+$tempVal+');');
							$('#page'+pageBean.page).addClass("currentPage");
							$('#page'+pageBean.page).attr('href','javascript:void(0);');
							$('#currentPage').val(pageBean.page);
							$('#pageSize').val(pageBean.pageSize);
							$('#pageTotal').val(pageBean.countPages);
							$('#listTable tr:gt(0)').remove();
							for(var i=0;i<pageBean.data.length;i++){
								var data  = pageBean.data[i];
								var $tr = $("<tr>");
								$tr.appendTo("#listTable");
								$tr.append('<td><input type="checkbox" name="ids" value="'+data.groupId+'"></td>');
								$tr.append('<td>'+data.groupId+'</td>');
								$tr.append('<td>'+data.groupName+'</td>');
								$tr.append('<td>'+data.createDate+'</td>');
								$tr.append("<td><a href='${pageContext.request.contextPath}/groups/updateData/"+data.groupId+">[编辑]</a></td>");
							}
						}
					);
				}
			}
			//页码调转
			$('#jumpPage').on('click',function(){
				var pageNum  = $('#pageNumber').val();
				pageNum  = pageNum<1 ? 1 : pageNum;
				var countPages = $('#countPages').val();
				pageNum  = pageNum >countPages ? countPages : pageNum;
				$.pageSkip(pageNum);
			});
			 //  全选   --------------qtt 
			 $selectAll.click(function(){
			 	if($(this).attr('checked') !=undefined && $(this).attr('checked') !=null){
			 		$("input[type='checkbox'][name='ids']").attr('checked',$(this).attr('checked'));
			 	}else{
			 		$("input[type='checkbox'][name='ids']").removeAttr('checked');
			 	}
			 });
			 // 单选-------------qtt
			$ids.click(function(){
				//alert("自己做");
			});
			 
			 //  删除  -------------- qtt
		$deleteButton.click(function(){
			var inputs = $("input[type='checkbox'][name='ids']:checked");
			var ids = [];
			for(var i=0;i<inputs.length;i++){
				ids.push ({'id':$(inputs[i]).val()});
			}
			if(ids.length<1)alert("请选择你要删除的数据");
			if(confirm("确认要删除吗？ 该组别下的所有参数都会被删除掉...")){
				$.ajax({
					url:"${pageContext.request.contextPath}/groups/operactorByAjax",
					type:'POST',
					data:{'ids':ids,'command':'removeData'},
					//contentType: 'application/json',
					//processData:false,//不允许转换格式
					success:function(jsonData){
						if(jsonData!=null && jsonData.result){
							inputs.parent().parent().remove();
							$.message("success","删除成功");
						}else {
							$.message("error","删除失败");
						}
					},
					dataType:'json'
				})
			}
		 });
	});
</script>
</html>