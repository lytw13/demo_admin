/**
 * 
 */
var table;
$(document).ready(function() {
	    //选中行的数据
		var param;
		window.operateEvents={
				//添加权限
			    "click #addPermission":function (e, value, row, index) {
					param = row;
					//window.location.href="/role/modify?id="+param;
					layer.open({
							type: 2,
							area: ['700px', '450px'],
							fixed: false, //不固定
							maxmin: true,
						content: '/role/modifyPage'
					});
				}
		    };
		
		
		
		//用户状态格式更改
		function statusFormat(value, row, index){
			if (row.status == 1) {
				return '<i class=\"fa fa-toggle-on fa-2x\" onclick="disEnable(\'' + row.id + '\',\'' + row.status + '\')"></i> ';
			} else {
				return '<i class=\"fa fa-toggle-off fa-2x\" onclick="enable(\'' + row.id + '\',\'' + row.status + '\')"></i> ';
			}
		};
		table = $('#myTable').bootstrapTable({
			url: "/role/list",
			toolbar: "#toolbar",
			classes: "table table-bordered table-hover table-striped",  //添加表格斑马条 鼠标覆盖时效果 以及表格边框
		    pagination: true,  //开启分页
		    locale:'zh-CN', //页面使用中文
		    sortable: true,  //字段排序
		    pageSize: 10, //默认的分页记录行数显示
		    pageList: [10,20,50], //可供选择的每页的行数（*）
			showToggle:true,
			showColumns: true,                  //是否显示所有的列
			paginationPreText: '上一页',
			paginationNextText: '下一页',
			singleSelect: true,  //单选
			formatLoadingMessage: '正在加载，请稍候。。。',
			 showExport: true, //是否显示导出按钮
		        exportDataType: "basic", //basic', 'all', 'selected'.
		        exportTypes:['excel','xlsx'], //导出类型
		        //exportButton: $('#myExportData'), //为按钮btn_export 绑定导出事件 自定义导出按钮(可以不用)
		        exportOptions:{
		        //ignoreColumn: [0,0], //忽略某一列的索引
		        fileName: '数据导出'
		        //onMsoNumberFormat: DoOnMsoNumberFormat
		        },
			columns: [{
			    field: 'Id',
			    checkbox: true
			  },{
			    field: 'id',
			    title: '角色id',
			    visible: false
			  }, {
			    field: 'name',
			    title: '角色名',
			    width:100
			  },{
				 field: 'status',
				 formatter: statusFormat,
				 title: '角色激活状态',
				 width:100
			 },{
				 field: 'operate',
				 formatter: operateFormat,
				 events: operateEvents,
				 title: '操作',
				 width:100
			 }]
		    
		});
		
		function operateFormat(index,row,value) {
			return '<button type="button" id="addPermission" class="btn btn-primary" style="margin-right:15px;">添加权限</button>';
		};
	});

	$("#addButton").on("click", function() {
		layer.open({
			type: 2,
			area: ['700px', '450px'],
			fixed: false, //不固定
			maxmin: true,
			content: '/role/addPage'
		});
	});

	$("#modifyButton").click(function() {
			var rows = $('#myTable').bootstrapTable("getSelections");
			if(rows.length == 0) {
				layer.alert("请先选中要编辑的数据");
				return;
			}
			param = rows[0];
			//window.location.href="/role/modify?id="+param;
		layer.open({
			type: 2,
			area: ['700px', '450px'],
			fixed: false, //不固定
			maxmin: true,
			content: '/role/modifyPage'
		});
		});
		
		
		$("#deleteButton").click(function() {
			var rows = $('#myTable').bootstrapTable("getSelections");
			if(rows.length == 0) {
				layer.alert("请先选中要删除的数据");
				return;
			}
			layer.confirm('确认要删除选中的数据吗？', {
				  btn: ['确定','返回'] //按钮
			}, function(){
			window.location.href="/role/delete?id="+rows[0].id+"&name="+rows[0].name;
			});
		});

		function enable(id,status) {
			//询问框
			layer.confirm('确认要启用该用户么？', {
				btn: ['确定', '返回'] //按钮
			}, function () {
				$.ajax({
					type: "POST",
					url: "/role/changeStatus",
					data: "id="+id+"&status="+status,
					success: function(msg){
						//关闭窗口
						layer.close(layer.index);
						//重新渲染表格
						table.bootstrapTable('refresh');
					}
				});
			});
		};
		function disEnable(id,status) {
			//询问框
			layer.confirm('确认要停用该用户么？', {
				btn: ['确定', '返回'] //按钮
			}, function () {
				$.ajax({
					type: "POST",
					url: "/role/changeStatus",
					data: "id="+id+"&status="+status,
					success: function(msg){
						//关闭窗口
						layer.close(layer.index);
						//重新渲染表格
						table.bootstrapTable('refresh');
					}
				});
			});
		}