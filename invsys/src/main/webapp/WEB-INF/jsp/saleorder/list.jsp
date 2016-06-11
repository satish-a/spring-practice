<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<jsp:include page="../menu/header.jsp" />
<jsp:include page="../menu/includescripts.jsp" />

<body>
	<jsp:include page="../menu/menu.jsp" />
	<hr class="soften">
	<div class="container">
		<div id="filterForm"></div>
		<div id="grid"></div>
	</div>
</body>

<script type='text/javascript'>

Ext.onReady(function() 
{
	Ext.define('Sales', 
	{
		extend : 'Ext.data.Model',
		fields : [ 
			{name : 'id', type : 'int', useNull : true}, 
			'orderId', 'orderType', 'comments', 'referenceNumber','userName', 'orderDate', 'dueDate', 'total']
	});

	var salesStore = Ext.create('Ext.data.Store', 
	{
		autoLoad : true, model : 'Sales', remoteFilter : 'true', remoteSort : 'true',
		proxy : 
		{
			type : 'ajax',
			api : { read : '<c:url value="/saleorder/list"/>' },
			reader : { type : 'json', root : 'data', successProperty : 'success' },
			listeners : 
			{
				exception : function(proxy, response, operation) 
				{
					Ext.MessageBox.show(
					{
						title : 'REMOTE EXCEPTION', msg : operation.getError(), icon : Ext.MessageBox.ERROR, buttons : Ext.Msg.OK
					});
				}
			}
		}
	});

	var sm = new Ext.selection.CheckboxModel();

	var salesList = Ext.create('Ext.grid.Panel',
	{
		store : salesStore, frame : true, height : 500, selModel : sm, iconCls : 'icon-user',
		columns : 
		[
			{ 	text : 'Order Id', flex : 1, sortable : true, filterable: true, dataIndex : 'orderId', hideable: false, 
				renderer : function(value, metaData, record, rowIndex, colIndex, store) 
				{
					return '<a href="<c:url value="/saleorder/view/"/>' + value + '">' + value + '</a>';
				}
			}, 
			{ text : 'Order Type', flex : 1, sortable : true, filterable: true, dataIndex : 'orderType' }, 
			{ text : 'Comments', flex : 1, sortable : true, filterable: true, hideable: true, hidden: true, dataIndex : 'comments' },
			{ text : 'Reference Number', flex : 1, sortable : true, filterable: true, hideable: true, hidden: true, dataIndex : 'referenceNumber' },
			{ text : 'Customer', flex : 1, sortable : true, filterable: true, dataIndex : 'userName', hideable: false }, 
			{ text : 'Order Date', flex : 1, sortable : true, filterable: true, dataIndex : 'orderDate', hideable: false },
			{ text : 'Due Date', flex : 1, sortable : true, filterable: true, dataIndex : 'dueDate', hideable: false },
			{ text : 'Total', flex : 1, sortable : true, filterable: true, dataIndex : 'total', hideable: false }
		],
		dockedItems : 
		[
			{ 	items : 
				[
					'-',
					{ text : 'Add', itemId : 'add', 
						handler: function() { window.location = "<c:url value="/saleorder/add"/>" ;}}, 
					'-',
					{ text : 'Delete', action : 'delete', handler: function() { processMarkedDeletions(); } }
				],
				xtype : 'pagingtoolbar', dock : 'bottom', store : salesStore, displayInfo : true,
				displayMsg : 'Displaying Sales orders {0} - {1} of {2}', emptyMsg : "No Sales orders to display"
					
			}
		]
	});
	
	function processMarkedDeletions()
	{
		var sels = sm.getSelection();
		if (sels.length > 0) 
		{
			var sales = [];
			Ext.each(sels, function(sel) 
			{
				sales.push(sel.get('orderId'));
			});
			                
			Ext.Msg.show(
			{
				title: 'Delete Sales Orders?', buttons: Ext.MessageBox.YESNO, msg: 'Do you want delete the selected Sales orders?',
				fn: function(btn)
				{
					if (btn == 'yes')
					{
						sendMarkedDeletions(sales);	 
					}
				}
			});
		}
	}
	
	function sendMarkedDeletions(sales)
	{
		Ext.Ajax.request(
		{ 
			url: '<c:url value="/saleorder/delete"/>', method:'POST', params: { 'sales': Ext.JSON.encode(sales) },
			success: function(response)
			{
				Ext.Msg.alert('Success', 'Removed Sales Orders Successfully');
				salesStore.load();
			}
		});
	}
	
	var filterForm = new Ext.FormPanel(
	{
		title : 'Sales Orders',	labelWidth : 80, frame : true, collapsible:true, collapsed:true, monitorValid : true, bodyStyle:'padding:10px',
		fieldDefaults: { labelAlign: 'left', msgTarget: 'side' }, defaults: { anchor: '100%' },
		items : 
		[
			{ 	layout:'column', border:false,
	            items:
				[
					{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Order Id', id: 'orderId_filter', anchor:'95%' }, 
							{ fieldLabel: 'Customer Name', id: 'userName_filter', anchor:'95%' }
						]
	            	},
	            	{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
	                	items: 
						[
							{ fieldLabel: 'Order Type', id: 'orderType_filter', anchor:'95%' },
							{ fieldLabel: 'Due Date', id: 'dueDate_filter', xtype: 'datefield', anchor:'95%' }
						]
					}
				]
			}
		],
		buttons : 
		[
			{ 	text : 'Filter', formBind : true,
				handler : function() 
				{
					salesStore.filters.clear();
					salesStore.filter(
					[
						{property: "orderId", value: Ext.getCmp("orderId_filter").getValue()},
						{property: "username", value: Ext.getCmp("userName_filter").getValue()},
						{property: "orderType", value: Ext.getCmp("orderType_filter").getValue()},
						{property: "dueDate", value: Ext.getCmp("dueDate_filter").getValue()}
					]);
				}
			}, 
			{ 	text : 'Show All', handler: function() { salesStore.clearFilter();} } 
		]
	});

	filterForm.render('filterForm');
	salesList.render('grid');
});
</script>

</html>