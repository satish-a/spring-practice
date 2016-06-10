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
	Ext.define('Payment', 
	{
		extend : 'Ext.data.Model',
		fields : 
		[ 
			{name : 'id', type : 'int', useNull : true}, 
			'notes', 'paymentType', 'amount', 'paymentDate', 'userName'
		]
	});

	var paymentStore = Ext.create('Ext.data.Store', 
	{
		autoLoad : true,
		model : 'Payment',
		remoteFilter : 'true',
		remoteSort : 'true',
		proxy : 
		{
			type : 'ajax',
			api : { read : '<c:url value="/payments/list"/>' },
			reader : { type : 'json', root : 'data', successProperty : 'success' },
			listeners : {
				exception : function(proxy, response, operation) 
				{
					Ext.MessageBox.show(
					{
						title : 'REMOTE EXCEPTION',
						msg : operation.getError(),
						icon : Ext.MessageBox.ERROR,
						buttons : Ext.Msg.OK
					});
				}
			}
		}
	});

	var sm = new Ext.selection.CheckboxModel();

	var paymentList = Ext.create('Ext.grid.Panel',
	{
		frame : true, height : 500, store : paymentStore, selModel : sm,
		iconCls : 'icon-user',
		columns : 
		[
		 { text : 'Account Name', flex : 1, sortable : true, filterable: true, dataIndex : 'userName', 
			field : { xtype : 'textfield'},
				renderer : function(value, metaData, record, rowIndex, colIndex, store) 
				{
					return '<a href="<c:url value="/payments/view/"/>' + record.get('id') + '">' + value + '</a>';
				}
			}, 
			{ text : 'Amount', flex : 1, sortable : true, filterable: true, dataIndex : 'amount', 
				field : { xtype : 'textfield' }}, 
			{ text : 'Payment Date', flex : 1, sortable : true, filterable: true, dataIndex : 'paymentDate', 
				field : { xtype : 'textfield' }},
			{ text : 'Payment Type', flex : 1, sortable : true, filterable: true, dataIndex : 'paymentType',
				field : { xtype : 'textfield' }},
			{ text : 'Notes', flex : 1, sortable : true, filterable: true, dataIndex : 'notes',
				field : { xtype : 'textfield' }}
		],
		dockedItems : 
		[
			{
				items : 
				[
					'-',
					{ text : 'Add', itemId : 'add', 
						handler: function() { window.location = "<c:url value="/payments/add"/>" ;}}, 
					'-',
					{ text : 'Delete', action : 'delete', handler: function() { processMarkedDeletions(); } }
				],
				xtype : 'pagingtoolbar', dock : 'bottom', store : paymentStore, displayInfo : true,
				displayMsg : 'Displaying payments {0} - {1} of {2}', emptyMsg : "No payments to display"
					
			}
		]
	});
	
	function processMarkedDeletions()
	{
		var sels = sm.getSelection();
		if (sels.length > 0) 
		{
			var payments = [];
			Ext.each(sels, function(sel) 
			{
				payments.push(sel.get('id'));
			});
			                
			Ext.Msg.show(
			{
				title: 'Delete Payments?',
				buttons: Ext.MessageBox.YESNO,
				msg: 'Do you want delete the selected payments?',
				fn: function(btn)
				{
					if (btn == 'yes')
					{
						sendMarkedDeletions(payments);	 
					}
				}
			});
		}
	}
	
	function sendMarkedDeletions(payments)
	{
		Ext.Ajax.request(
		{ 
			url: '<c:url value="/payments/delete"/>',
			method:'POST',
			params: { 'payments': Ext.JSON.encode(payments) },
			success: function(response)
			{
				// var text = response.responseText;
				Ext.Msg.alert('Success', action.result.message);
				paymentStore.load();
			}
		});
	}
	
	var filterForm = 
		new Ext.FormPanel(
		{
			title : 'Payments', labelWidth : 80, frame : true, collapsible:true,
			collapsed:true, monitorValid : true,
			bodyStyle:'padding:10px',
	        fieldDefaults: { labelAlign: 'left', msgTarget: 'side' },
	        defaults: { anchor: '100%' },
			items : 
			[
				{
	            	layout:'column', border:false,
	            	items:
					[
						{
			                columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
							items: 
							[
								{ fieldLabel: 'Product Name', id: 'productName_filter', anchor:'95%' }, 
								{ fieldLabel: 'Description', id: 'description_filter', anchor:'95%' }
							]
	            		},
	            		{
	                		columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
	                		items: 
							[
								{ fieldLabel: 'Product Group', id: 'productGroup_filter', anchor:'95%' },
								{ fieldLabel: 'Price', id: 'price_filter', vtype:'email', anchor:'95%' }
							]
						}
					]
				}
			],
			buttons : 
			[
				{
					text : 'Filter',
					formBind : true,
					handler : function() 
					{ 
						paymentStore.filters.clear();
						paymentStore.filter(
						[
							{property: "productName", value: Ext.getCmp("productName_filter").getValue()},
							{property: "description", value: Ext.getCmp("description_filter").getValue()},
							{property: "productGroup", value: Ext.getCmp("productGroup_filter").getValue()},
							{property: "price", value: Ext.getCmp("price_filter").getValue()}
						]);
					}
				}, 
				{
					text : 'Show All',
					handler: function() { paymentStore.clearFilter();}
				} 
			]
		});

	filterForm.render('filterForm');
	paymentList.render('grid');

});
</script>

</html>