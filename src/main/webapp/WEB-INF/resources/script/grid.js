/**
* author:Michael.Y
* date:2015-3-1
* version:1.0
* depend:jquery-1.11.2.js
* description:动态创建Table
* options = {
		columns:[
			{text:"ID",index:"id"},

		]
	}
*/

$.fn.extend({
	"grid":function(opts){
		function initTable(optsParam,num){//参数初始化加载
			//加载样式
			var defaultCSS = {//表格的默认样式
				'border':'1px solid #f0f8ff',
				'width': '90%',
				'line-height':'30px',
				'text-align':'center',
				'margin':'auto',
			}
			var theadTrCSS = {//表格标题的默认样式
				'background':'#bbb',
				'font-weight':'bold',
				'color':'#fff'
			}
			var tbodyTrCSS = {//数据行的默认样式
				'background':'#f0f7ee',
				'color':'#555',
				'font-size':'12px',
				'letter-spacing':'1px'
			};
			var onTbodyTrCSS = {//鼠标经过数据行时的样式
				'background':'#dedede',	
				'color':'#555',
			};
			var tbodyTdClickCSS ={//列被选中的样式
				'background':'#ccc',
				'font-weight':'bold',
				'font-size':'14px',
				'color':'#555'
			}
			var tbodyInputCSS ={//列被选中之内的input样式
				'border':'0px',
				'text-align':'left',
				'font-size':'14px',
				'color':'#ccc',
			}
			optsParam.defaultCSS = $.extend(defaultCSS,optsParam.defaultCSS);//获取前段传人的table 样式
			optsParam.theadTrCSS =  $.extend(theadTrCSS,optsParam.theadTrCSS);//获取表格标题的样式
			optsParam.tbodyTrCSS  = $.extend(tbodyTrCSS,optsParam.tbodyTrCSS);//获取表格内容的样式
			optsParam.onTbodyTrCSS = $.extend(onTbodyTrCSS,opts.onTbodyTrCSS);//获取表格获取鼠标时的样式
			optsParam.tbodyInputCSS = $.extend(tbodyInputCSS,tbodyTdClickCSS);//获取单击列时输入框的样式
			
			var tableID  = (num && num !== undefined) ? num :0;//默认为0
			var defaultParam = { //初始化默认的常用参数
				'page':1,//当前页
				'pageSize':10,//默认显示十条数据
				'pageShow':false,//是否支持显示分页
				
				'checkBox':true,//是否需要添加checkBox
				'dataSort':false,//是否支持排序
				'clsSwap':false,//是否允许列交换

				'tableID' :tableID,//当前表格的序号
				'tableName' :'gridTable',//当前表格的序号
				'tableClass':'',//当前表格的样式
				'tableCSS':true,//如果存在 tableClass属性 则为false 否则为True
				/**
				* eg {'text':'生日','index':'birthday',write:true,render:function(value){},renderObject:function(value){}}
				*    text:列名  
				*	index : 属性名称 
				*	write ：该列是否可写
				*	render: 该列对应的属性值是否需要进行处理
				*   renderObject: 自定义输出该列的内容，
				*/
				'columns':'',//不允许为空,为空返回null  
				'checkBoxName': 'gridCheckBox',//多选框的名称 当checkBox为true时
			}
			optsParam  = $.extend(defaultParam,optsParam);//汇总参数
			//optsParam.tableCSS = (optsParam.tableClass && optsParam.tableClass !== undefined) ? false : true;//如果外部有样式 则优先使用
			//加载数据网络路径
			//optsParam.ajaxLoadData = {'type':'POST','url':'#','params':{}}
			return optsParam;
		}
		function createTable(optsParam){//创建表格
			var table  = $("<table>");
			table.attr("name",optsParam.tableName);
			table.attr("id",optsParam.tableName+"_"+optsParam.tableID);
			var thead = $("<thead>");//创建头部标签
			thead.appendTo(table);
			var tr = $("<tr>");//第一行用于存放列名
			tr.appendTo(thead);
			
			if(optsParam.tableCSS){//判断是否需要加载默认样式
				table.css(optsParam.defaultCSS);//添加表格样式
				thead.children('tr').css(optsParam.theadTrCSS);
				
			}
			if(optsParam.tableClass && optsParam.tableClass !== undefined){//判断是否需要加载外部样式
				table.addClass(optsParam.tableClass);
			}
			//判断是否需要添加全部选中行
			if(optsParam.checkBox){
				var th = $("<th>");th.appendTo(tr);
				var cb  = $("<input type='checkbox'  name='"+optsParam.checkBoxName+"'  id='gridCheckBoxAll_"+optsParam.tableID+"'/>");
				cb.appendTo(th);
				th.children("input").on("click",function(){//添加全部选中事件
					$('input[name="'+optsParm.checkBoxName+"_"+optsParam.tableID+'"]').prop('checked',$(this).prop('checked'));
				});
			}
			//打印表格头部列名
			for(var i=0;i<optsParam.columns.length;i++){
				var columnName  =  optsParam.columns[i].text;
				var th = $("<th>"+columnName+"</th>");
				th.appendTo(tr);
			}
			return table;
		}

		function updateTbody(optsParam,table){//添加数据到表中
			//加载数据
			loadData(optsParam);
			if(optsParam.data && optsParam.data !== undefined &&optsParam.data.length<1){//不存在数据
				return;
			}
			//再次请求时清空原来的表数据
			table.find('tbody').remove();

			var tbody = $("<tbody>");//创建tbody
			tbody.appendTo(table);

			for(var j=0;j<optsParam.data.length;j++){//写入数据
				var tr = $("<tr>");
				tr.appendTo(tbody);

				if(optsParam.checkBox){//判断是否需要添加checkbox
					var cb = $("<td><input type='checkbox'  name='"+optsParam.checkBoxName+"_"+optsParam.tableID+"' id='gridCB_"+optsParam.tableID+"_"+j+"'/></td>");
					cb.appendTo(tr);
				}
				for(var i=0;i<optsParam.columns.length;i++){
					var value  =  optsParam.data[j];//获取该列名对应的值
					var clNames  = optsParam.columns[i].index.split(/\./);//解决嵌套属性
					for(var k=0;k<clNames.length;k++){//获取最终的属性值
						value = value[clNames[k]];
					}
					var td  = $('<td>');td.appendTo(tr);
					if(value === undefined && !optsParam.columns[i].renderObject){//如果value为空则 跳出本次循环
						continue;//如果没有值 则结束当次循环，进入下次循环
					}
					if(optsParam.columns[i].renderObject){//判断该列是否需要添加事件
						var obj = optsParam.columns[i].renderObject(optsParam.data[j][optsParam.columns[i].index],optsParam.data[j]);
						td.append(obj);
						continue;//结束本次循环
					}
					if(opts.columns[i].render) {//判断该列是否需要进行数据转换
						value  = optsParam.columns[i].render(value);
					}
					td.html(value);
					//判断该列是否为可写，如果是，则添加click 事件
					if(!optsParam.columns[i].write && optsParam.columns[i].write !== undefined){
						td.on('click',function(){//添加鼠标双击事件
							if( $(this).children('input') !== undefined && $(this).children('input') && $(this).children('input').length>0){
								$(this).children('input[name="gridTempText"]').focus();return;
							}
							var input = $("<input type='text' name='girdTempText' value ='"+$(this).text()+"' />");	
							$(this).html('');
							var width = $(this).width();
							var height = $(this).height();
							var selectTd  = $(this);
							input.appendTo($(this));
							input.css({'width':width,'height':height}).focus();
							$(this).css({'width':width,'height':height});
							if(optsParam.tableCSS)input.css(optsParam.tbodyInputCSS);
							input.on("mouseout",function(){//添加鼠标离开事件
								var value  = $(this).val();
								$(this).parent().text(value);
								$(this).remove();
							});
							input.change(function(){//添加鼠标离开事件
								var value  = $(this).val();
								if(optsParam.updateAction  && optsParam.updateAction !== undefined ){//添加动态更新事件
								}
							});
						});
					}
				}
			}
			//对所有的tbody下的tr 进行统一样式设置，并添加常用事件方法
			if(optsParam.tableCSS) tbody.children('tr').css(optsParam.tbodyTrCSS);
			tbody.children('tr').css(optsParam.tbodyTrCSS);
			tbody.children('tr').on("mouseover",function(){//添加获取鼠标事件
					if(optsParam.tableCSS) $(this).css(optsParam.onTbodyTrCSS);
					$(this).children('td').css({"background":$(this).css('background')});
					$(this).children('td').css({"color":$(this).css('color')});
					$(this).children('td').css({"font-weight":$(this).css('font-weight')});
				});
			tbody.children('tr').on("mouseout",function(){//添加鼠标离开事件
					if(optsParam.tableCSS) $(this).css(optsParam.tbodyTrCSS);
					$(this).children('td').css({"font-weight":$(this).css('font-weight')});
					$(this).children('td').css({"font-size":$(this).css('font-size')});
				});
		}
		
		function loadData(optsParam){//加载数据
			/*$.ajax({
				   type: optsParam.ajaxLoad.type,
				   url: optsParam.ajaxLoad.url,
				   data: "name=John&location=Boston",
				   success: function(msg){
					 alert( "Data Saved: " + msg );
				   }
			});
			*/
		}
		function loadPage(optsParam,table){//加载分页


		}
		$(this).each(function(num,div){//如果是选择了多个，则分别新建表格
			if(opts.columns === null || opts.columns === undefined || opts.columns.length<1){//没有列名无法新建表
				return $(this);
			}
			//加载初始化参数
			opts = initTable(opts,num);
			//创建表格
			var table  = createTable(opts);
			//加载表格内容
			updateTbody(opts,table);
			return $(this).append(table);
		});
	}
});