//引入js，css文件操作
function Import() {
	var importCssJs = {
		css: function(path) {
			if(!path || path.length === 0) {
				throw new Error('参数"path"错误');
			}
			var head = document.getElementsByTagName('head')[0];
			var links = document.createElement("link");
			links.href = path;
			links.rel = "stylesheet";
			links.type = "text/css";
			head.appendChild(links);
		},
		js: function(path) {
			if(!path || path.length === 0) {
				throw new Error('参数"path"错误');
			}
			var head = document.getElementsByTagName("head")[0];
			var scripts = document.createElement("script");
			scripts.src = path;
			scripts.type = "text/javascript";
			head.appendChild(scripts);
		}
	}
	//--------引入css文件
	importCssJs.css("../static/uimaker/easyui.css");
	importCssJs.css("../static/uimaker/icon.css");
	importCssJs.css("../static/uimaker/panel.css");
	importCssJs.css("../static/uimaker/linkbutton.css");
	importCssJs.css("../static/css/providers1.css");

	//--------引入js文件
	//	importCssJs.js("js/jquery-1.8.0.min.js");
	//	importCssJs.js("js/jquery.easyui.min.js");
	//	importCssJs.js("js/jeasyui-lang-zh_CN.js");
}

//-------服务器端分页表格设置
function init_datagrid(idName, title_name, jsonUrl, arr_columns, m_method, t_toolbar) {
	$(idName).datagrid({
		title: title_name,
		url: jsonUrl,
		method: m_method,
		autoRowHeight: false,
		singleSelect: true,
		multiSort: true,
		striped: true,
		remoteSort: true, //定义从服务器对数据进行排序。
		pagination: true, //在DataGrid控件底部显示分页工具栏。
		pageNumber: 1,
		pageSize: 10,
		pageList: [5, 10, 15, 20],
		fitColumns: true,
		columns: [arr_columns],
		rownumbers: true,
		loadMsg: '数据正在努力加载，请稍后...',
		checkbox: true,
		emptyMsg: '列表为空',
		selectOnCheck: false,
		checkOnSelect: false,
		toolbar: t_toolbar,
		multiSort: true,
	})
}

//-------客户端分页表格设置
function init_get_datagrid(idName, title_name, arr_columns, t_toolbar, data) {
	$(idName).datagrid({
		autoRowHeight: false,
		singleSelect: true,
		multiSort: true,
		striped: true,
		pagination: true, //在DataGrid控件底部显示分页工具栏。
		fitColumns: true,
		rownumbers: true,
		loadMsg: '数据正在努力加载，请稍后...',
		checkbox: true,
		emptyMsg: '列表为空',
		selectOnCheck: false,
		checkOnSelect: false,
		toolbar: t_toolbar,
		title: title_name,
		data: data.slice(0, 10),
		columns: [arr_columns]
	});

	var pager = $(idName).datagrid("getPager");
	pager.pagination({
		total: data.length,
		onSelectPage: function(pageNo, pageSize) {
			var start = (pageNo - 1) * pageSize;
			var end = start + pageSize;
			$(idName).datagrid("loadData", data.slice(start, end));
			pager.pagination('refresh', {
				total: data.length,
				pageNumber: pageNo
			});
		}
	});

}

//window窗口设置
function WindowSetup(id_name, _title, _width, _height) {
	$(id_name).window({
		title: _title,
		width: _width,
		height: _height,
		modal: true,
		closed: false,
		shadow: true,
		minimizable: false
	});
}

//dialog窗口设置(对话窗口)
function DialogSetup(id_name, _title, _width, _height, dialog_btns) {
	$(id_name).dialog({
		title: _title,
		width: _width,
		height: _height,
		modal: true,
		shadow: true,
		minimizable: false,
		maximizable: false,
		buttons: dialog_btns
	});
}

//panel容器设置（面板）
function PanelSetup(id_name, _title, _width, _height) {
	$(id_name).panel({
		width: _width,
		height: _height,
		title: _title,
		collapsible:true
	});
}