/**
* author:Michael.Y
* date:2015-3-1
* version:1.0
* depend:jquery-1.11.2.js
* description:��̬����Table
* options = {
		columns:[
			{text:"ID",index:"id"},

		]
	}
*/

$.fn.extend({
	"grid":function(opts){
		function initTable(optsParam,num){//������ʼ������
			//������ʽ
			var defaultCSS = {//����Ĭ����ʽ
				'border':'1px solid #f0f8ff',
				'width': '90%',
				'line-height':'30px',
				'text-align':'center',
				'margin':'auto',
			}
			var theadTrCSS = {//�������Ĭ����ʽ
				'background':'#bbb',
				'font-weight':'bold',
				'color':'#fff'
			}
			var tbodyTrCSS = {//�����е�Ĭ����ʽ
				'background':'#f0f7ee',
				'color':'#555',
				'font-size':'12px',
				'letter-spacing':'1px'
			};
			var onTbodyTrCSS = {//��꾭��������ʱ����ʽ
				'background':'#dedede',	
				'color':'#555',
			};
			var tbodyTdClickCSS ={//�б�ѡ�е���ʽ
				'background':'#ccc',
				'font-weight':'bold',
				'font-size':'14px',
				'color':'#555'
			}
			var tbodyInputCSS ={//�б�ѡ��֮�ڵ�input��ʽ
				'border':'0px',
				'text-align':'left',
				'font-size':'14px',
				'color':'#ccc',
			}
			optsParam.defaultCSS = $.extend(defaultCSS,optsParam.defaultCSS);//��ȡǰ�δ��˵�table ��ʽ
			optsParam.theadTrCSS =  $.extend(theadTrCSS,optsParam.theadTrCSS);//��ȡ���������ʽ
			optsParam.tbodyTrCSS  = $.extend(tbodyTrCSS,optsParam.tbodyTrCSS);//��ȡ������ݵ���ʽ
			optsParam.onTbodyTrCSS = $.extend(onTbodyTrCSS,opts.onTbodyTrCSS);//��ȡ����ȡ���ʱ����ʽ
			optsParam.tbodyInputCSS = $.extend(tbodyInputCSS,tbodyTdClickCSS);//��ȡ������ʱ��������ʽ
			
			var tableID  = (num && num !== undefined) ? num :0;//Ĭ��Ϊ0
			var defaultParam = { //��ʼ��Ĭ�ϵĳ��ò���
				'page':1,//��ǰҳ
				'pageSize':10,//Ĭ����ʾʮ������
				'pageShow':false,//�Ƿ�֧����ʾ��ҳ
				
				'checkBox':true,//�Ƿ���Ҫ���checkBox
				'dataSort':false,//�Ƿ�֧������
				'clsSwap':false,//�Ƿ������н���

				'tableID' :tableID,//��ǰ�������
				'tableName' :'gridTable',//��ǰ�������
				'tableClass':'',//��ǰ������ʽ
				'tableCSS':true,//������� tableClass���� ��Ϊfalse ����ΪTrue
				/**
				* eg {'text':'����','index':'birthday',write:true,render:function(value){},renderObject:function(value){}}
				*    text:����  
				*	index : �������� 
				*	write �������Ƿ��д
				*	render: ���ж�Ӧ������ֵ�Ƿ���Ҫ���д���
				*   renderObject: �Զ���������е����ݣ�
				*/
				'columns':'',//������Ϊ��,Ϊ�շ���null  
				'checkBoxName': 'gridCheckBox',//��ѡ������� ��checkBoxΪtrueʱ
			}
			optsParam  = $.extend(defaultParam,optsParam);//���ܲ���
			//optsParam.tableCSS = (optsParam.tableClass && optsParam.tableClass !== undefined) ? false : true;//����ⲿ����ʽ ������ʹ��
			//������������·��
			//optsParam.ajaxLoadData = {'type':'POST','url':'#','params':{}}
			return optsParam;
		}
		function createTable(optsParam){//�������
			var table  = $("<table>");
			table.attr("name",optsParam.tableName);
			table.attr("id",optsParam.tableName+"_"+optsParam.tableID);
			var thead = $("<thead>");//����ͷ����ǩ
			thead.appendTo(table);
			var tr = $("<tr>");//��һ�����ڴ������
			tr.appendTo(thead);
			
			if(optsParam.tableCSS){//�ж��Ƿ���Ҫ����Ĭ����ʽ
				table.css(optsParam.defaultCSS);//��ӱ����ʽ
				thead.children('tr').css(optsParam.theadTrCSS);
				
			}
			if(optsParam.tableClass && optsParam.tableClass !== undefined){//�ж��Ƿ���Ҫ�����ⲿ��ʽ
				table.addClass(optsParam.tableClass);
			}
			//�ж��Ƿ���Ҫ���ȫ��ѡ����
			if(optsParam.checkBox){
				var th = $("<th>");th.appendTo(tr);
				var cb  = $("<input type='checkbox'  name='"+optsParam.checkBoxName+"'  id='gridCheckBoxAll_"+optsParam.tableID+"'/>");
				cb.appendTo(th);
				th.children("input").on("click",function(){//���ȫ��ѡ���¼�
					$('input[name="'+optsParm.checkBoxName+"_"+optsParam.tableID+'"]').prop('checked',$(this).prop('checked'));
				});
			}
			//��ӡ���ͷ������
			for(var i=0;i<optsParam.columns.length;i++){
				var columnName  =  optsParam.columns[i].text;
				var th = $("<th>"+columnName+"</th>");
				th.appendTo(tr);
			}
			return table;
		}

		function updateTbody(optsParam,table){//������ݵ�����
			//��������
			loadData(optsParam);
			if(optsParam.data && optsParam.data !== undefined &&optsParam.data.length<1){//����������
				return;
			}
			//�ٴ�����ʱ���ԭ���ı�����
			table.find('tbody').remove();

			var tbody = $("<tbody>");//����tbody
			tbody.appendTo(table);

			for(var j=0;j<optsParam.data.length;j++){//д������
				var tr = $("<tr>");
				tr.appendTo(tbody);

				if(optsParam.checkBox){//�ж��Ƿ���Ҫ���checkbox
					var cb = $("<td><input type='checkbox'  name='"+optsParam.checkBoxName+"_"+optsParam.tableID+"' id='gridCB_"+optsParam.tableID+"_"+j+"'/></td>");
					cb.appendTo(tr);
				}
				for(var i=0;i<optsParam.columns.length;i++){
					var value  =  optsParam.data[j];//��ȡ��������Ӧ��ֵ
					var clNames  = optsParam.columns[i].index.split(/\./);//���Ƕ������
					for(var k=0;k<clNames.length;k++){//��ȡ���յ�����ֵ
						value = value[clNames[k]];
					}
					var td  = $('<td>');td.appendTo(tr);
					if(value === undefined && !optsParam.columns[i].renderObject){//���valueΪ���� ��������ѭ��
						continue;//���û��ֵ ���������ѭ���������´�ѭ��
					}
					if(optsParam.columns[i].renderObject){//�жϸ����Ƿ���Ҫ����¼�
						var obj = optsParam.columns[i].renderObject(optsParam.data[j][optsParam.columns[i].index],optsParam.data[j]);
						td.append(obj);
						continue;//��������ѭ��
					}
					if(opts.columns[i].render) {//�жϸ����Ƿ���Ҫ��������ת��
						value  = optsParam.columns[i].render(value);
					}
					td.html(value);
					//�жϸ����Ƿ�Ϊ��д������ǣ������click �¼�
					if(!optsParam.columns[i].write && optsParam.columns[i].write !== undefined){
						td.on('click',function(){//������˫���¼�
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
							input.on("mouseout",function(){//�������뿪�¼�
								var value  = $(this).val();
								$(this).parent().text(value);
								$(this).remove();
							});
							input.change(function(){//�������뿪�¼�
								var value  = $(this).val();
								if(optsParam.updateAction  && optsParam.updateAction !== undefined ){//��Ӷ�̬�����¼�
								}
							});
						});
					}
				}
			}
			//�����е�tbody�µ�tr ����ͳһ��ʽ���ã�����ӳ����¼�����
			if(optsParam.tableCSS) tbody.children('tr').css(optsParam.tbodyTrCSS);
			tbody.children('tr').css(optsParam.tbodyTrCSS);
			tbody.children('tr').on("mouseover",function(){//��ӻ�ȡ����¼�
					if(optsParam.tableCSS) $(this).css(optsParam.onTbodyTrCSS);
					$(this).children('td').css({"background":$(this).css('background')});
					$(this).children('td').css({"color":$(this).css('color')});
					$(this).children('td').css({"font-weight":$(this).css('font-weight')});
				});
			tbody.children('tr').on("mouseout",function(){//�������뿪�¼�
					if(optsParam.tableCSS) $(this).css(optsParam.tbodyTrCSS);
					$(this).children('td').css({"font-weight":$(this).css('font-weight')});
					$(this).children('td').css({"font-size":$(this).css('font-size')});
				});
		}
		
		function loadData(optsParam){//��������
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
		function loadPage(optsParam,table){//���ط�ҳ


		}
		$(this).each(function(num,div){//�����ѡ���˶������ֱ��½����
			if(opts.columns === null || opts.columns === undefined || opts.columns.length<1){//û�������޷��½���
				return $(this);
			}
			//���س�ʼ������
			opts = initTable(opts,num);
			//�������
			var table  = createTable(opts);
			//���ر������
			updateTbody(opts,table);
			return $(this).append(table);
		});
	}
});