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
	Ext.define('Purchase', 
	{
		extend : 'Ext.data.Model',
		fields : 
		[ 
			{name : 'id', type : 'int', useNull : true}, 
			'orderId', 'orderType', 'comments', 'referenceNumber','userName', 'orderDate', 'dueDate'
		]
	});

	var purchaseStore = Ext.create('Ext.data.Store', 
	{
		model : 'Purchase', autoLoad : true, remoteFilter : 'true', remoteSort : 'true',
		proxy : 
		{
			type : 'ajax',
			api : { read : '<c:url value="/purchaseorder/list"/>' },
			reader :  { type : 'json', root : 'data', successProperty : 'success' },
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

	var purchaseList = Ext.create('Ext.grid.Panel',
	{
		store : purchaseStore, frame : true, height : 500, selModel : sm, iconCls : 'icon-user',
		columns : 
		[
			{ text : 'Order Id', flex : 1, sortable : true, filterable: true, dataIndex : 'orderId', hideable: false, 
				renderer : function(value, metaData, record, rowIndex, colIndex, store) 
				{
					return '<a href="<c:url value="/purchaseorder/view/"/>' + value + '">' + value + '</a>';
				}
			}, 
			{ text : 'Order Type', flex : 1, sortable : true, filterable: true, dataIndex : 'orderType'}, 
			{ text : 'Comments', flex : 1, sortable : true, filterable: true, hideable: true, hidden: true, dataIndex : 'comments'},
			{ text : 'Reference Number', flex : 1, sortable : true, filterable: true, hideable: true, hidden: true, dataIndex : 'referenceNumber'},
			{ text : 'Vendor', flex : 1, sortable : true, filterable: true, dataIndex : 'userName', hideable: false}, 
			{ text : 'Order Date', flex : 1, sortable : true, filterable: true, dataIndex : 'orderDate', hideable: false},
			{ text : 'Due Date', flex : 1, sortable : true, filterable: true, dataIndex : 'dueDate', hideable: false}
		],
		dockedItems : 
		[
			{
				items : 
				[
					'-',
					{ text : 'Add', itemId : 'add', handler: function() { window.location = "<c:url value="/purchaseorder/add"/>" ;}}, 
					'-',
					{ text : 'Delete', action : 'delete', handler: function() { processMarkedDeletions(); } }
				],
				xtype : 'pagingtoolbar', dock : 'bottom', store : purchaseStore, displayInfo : true,
				displayMsg : 'Displaying purchase orders {0} - {1} of {2}', emptyMsg : "No purchase orders to display"
					
			}
		]
	});
	
	function processMarkedDeletions()
	{
		var sels = sm.getSelection();
		if (sels.length > 0) 
		{
			var purchases = [];
			Ext.each(sels, function(sel) 
			{
				purchases.push(sel.get('orderId'));
			});
			                
			Ext.Msg.show(
			{
				title: 'Delete Purchase Orders?', buttons: Ext.MessageBox.YESNO, msg: 'Do you want delete the selected purchase orders?',
				fn: function(btn)
				{
					if (btn == 'yes')
					{
						sendMarkedDeletions(purchases);	 
					}
				}
			});
		}
	}
	
	function sendMarkedDeletions(purchases)
	{
		Ext.Ajax.request(
		{ 
			url: '<c:url value="/purchaseorder/delete"/>', method:'POST', params: { 'purchases': Ext.JSON.encode(purchases) },
			success: function(response)
			{
				Ext.Msg.alert('Success', 'Removed Purchase Orders Successfully');
				purchaseStore.load();
			}
		});
	}
	
	var filterForm = new Ext.FormPanel(
	{
		title : 'Purchase Orders', labelWidth : 80, frame : true, collapsible:true, collapsed:true, monitorValid : true,
		bodyStyle:'padding:10px', fieldDefaults: { labelAlign: 'left', msgTarget: 'side' }, defaults: { anchor: '100%' },
		items : 
		[
			{ 	layout:'column', border:false,
	            items:
				[
					{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Order Id', id: 'orderId_filter', anchor:'95%' }, 
							{ fieldLabel: 'Vendor Name', id: 'userName_filter', anchor:'95%' }
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
					purchaseStore.filters.clear();
					purchaseStore.filter(
					[
						{property: "orderId", value: Ext.getCmp("orderId_filter").getValue()},
						{property: "username", value: Ext.getCmp("userName_filter").getValue()},
						{property: "orderType", value: Ext.getCmp("orderType_filter").getValue()},
						{property: "dueDate", value: Ext.getCmp("dueDate_filter").getValue()}
					]);
				}
			}, 
			{ 	text : 'Show All', handler: function() { purchaseStore.clearFilter();} } 
		]
	});

	filterForm.render('filterForm');
	purchaseList.render('grid');
});
</script>

</html>