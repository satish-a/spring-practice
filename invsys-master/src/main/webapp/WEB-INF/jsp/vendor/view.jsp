<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<jsp:include page="../menu/header.jsp" />
<jsp:include page="../menu/includescripts.jsp" />

<body>
	<jsp:include page="../menu/menu.jsp" />

	<hr class="soften">
	<div class="container">
		<div id="form"></div>
	</div>
</body>

<script>
Ext.onReady(function() 
{
	Ext.QuickTips.init();

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
		model : 'Payment', autoLoad : false, remoteFilter : 'true', remoteSort : 'true',
		proxy : 
		{
			type : 'ajax',
			api : { read : '<c:url value="/payments/list"/>' },
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

	var purchaseSm = new Ext.selection.CheckboxModel();
	var paymentList = Ext.create('Ext.grid.Panel',
	{
		frame : true, height : 500, store : paymentStore, selModel : purchaseSm, iconCls : 'icon-user',
		columns : 
		[
			{ 	text : 'Account Name', flex : 1, sortable : true, filterable: true, dataIndex : 'userName', 
				renderer : function(value, metaData, record, rowIndex, colIndex, store) 
				{
					return '<a href="<c:url value="/payments/view/"/>' + record.get('id') + '">' + value + '</a>';
				}
			}, 
			{ text : 'Amount', flex : 1, sortable : true, filterable: true, dataIndex : 'amount' }, 
			{ text : 'Payment Date', flex : 1, sortable : true, filterable: true, dataIndex : 'paymentDate' },
			{ text : 'Payment Type', flex : 1, sortable : true, filterable: true, dataIndex : 'paymentType' },
			{ text : 'Notes', flex : 1, sortable : true, filterable: true, dataIndex : 'notes' }
		],
		dockedItems : 
		[
			{ 	items : 
				[
					'-',
					{ 	text : 'Add', itemId : 'add', 
						handler: function() { window.location = "<c:url value="/payments/add"/>" ;}
					}, 
					'-',
					{ text : 'Delete', action : 'delete', handler: function() { } }
				],
				xtype : 'pagingtoolbar', dock : 'bottom', store : paymentStore, displayInfo : true,
				displayMsg : 'Displaying payments {0} - {1} of {2}', emptyMsg : "No payments to display"
			}
		]
	});

	Ext.define('Sales', 
	{
		extend : 'Ext.data.Model',
		fields : 
		[ 
			{name : 'id', type : 'int', useNull : true}, 
			'orderId', 'orderType', 'comments', 'referenceNumber','userName', 'orderDate', 'dueDate'
		]
	});

	var salesStore = Ext.create('Ext.data.Store', 
	{
		autoLoad : false, model : 'Sales', remoteFilter : 'true', remoteSort : 'true',
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

	var salesSM = new Ext.selection.CheckboxModel();

	var salesList = Ext.create('Ext.grid.Panel',
	{
		store : salesStore,	frame : true, height : 500, selModel : salesSM, iconCls : 'icon-user',
		columns : 
		[
			{ 	text : 'Order Id', flex : 1, sortable : true, filterable: true, dataIndex : 'orderId', hideable: false, 
				renderer : function(value, metaData, record, rowIndex, colIndex, store) 
				{
					return '<a href="<c:url value="/sales/view/"/>' + value + '">' + value + '</a>';
				}
			}, 
			{ text : 'Order Type', flex : 1, sortable : true, filterable: true, dataIndex : 'orderType' }, 
			{ text : 'Comments', flex : 1, sortable : true, filterable: true, hideable: true, hidden: true, dataIndex : 'comments' },
			{ text : 'Reference Number', flex : 1, sortable : true, filterable: true, hideable: true, hidden: true, dataIndex : 'referenceNumber' },
			{ text : 'Customer', flex : 1, sortable : true, filterable: true, dataIndex : 'userName', hideable: false }, 
			{ text : 'Order Date', flex : 1, sortable : true, filterable: true, dataIndex : 'orderDate', hideable: false },
			{ text : 'Due Date', flex : 1, sortable : true, filterable: true, dataIndex : 'dueDate', hideable: false }
		],
		dockedItems : 
		[
			{	items : 
				[
					'-',
					{ 	text : 'Add', itemId : 'add', 
						handler: function() { window.location = "<c:url value="/sales/add"/>" ;}
					}, 
					'-',
					{ text : 'Delete', action : 'delete', handler: function() {  } }
				],
				xtype : 'pagingtoolbar', dock : 'bottom', store : salesStore, displayInfo : true,
				displayMsg : 'Displaying Sales orders {0} - {1} of {2}', emptyMsg : "No Sales orders to display"
			}
		]
	});

	var userDetailsForm = Ext.create('Ext.form.Panel',
	{
		bodyStyle:'padding:5px', labelWidth : 80, monitorValid : true,
	    fieldDefaults: { labelAlign: 'left', msgTarget: 'side', labelWidth: 100, labelStyle: 'font-weight:bold' }, defaults: { anchor: '100%' },
		url : '<c:url value="/vendor/update" />',
		items : 
		[
			{ 	layout:'column', border:false,
				items:
				[ 
					{ 	title: 'Personal Details', columnWidth:.5, border: false, layout: 'anchor', defaultType: 'textfield', bodyStyle:'padding:10px',
						items: 
						[
							{ fieldLabel: 'Account Name', name : 'username', value : '<c:out value="${userBean.username}" />', anchor:'95%', allowBlank : false, readOnly: true }, 
							{ fieldLabel: 'Account Type', name : 'accountType', value : '<c:out value="${userBean.accountType}" />', anchor:'95%', readOnly: true },
							{ fieldLabel: 'First Name', name : 'firstName', value : '<c:out value="${userBean.firstName}" />', anchor:'95%', allowBlank : false },
							{ fieldLabel: 'Last Name', name : 'lastName', value : '<c:out value="${userBean.lastName}" />', anchor:'95%', allowBlank : false }
						]
					},
					{   columnWidth:.5, border: false, defaultType: 'textfield', bodyStyle:'padding:10px', xtype: 'panel', flex: 1, frame: true,
						html: '<h3>Outstanding Balance# <c:out value="${userBean.balance}" /> INR</h3><h3>Outstanding Invoices# </h3><h3>Payments Till Date# </h3><h3>Total Invoices# </h3><h3>Last Transaction Amount# </h3>'
					} 
				]
			},
			{ 	title: 'Contact Details', layout:'column', bodyStyle:'padding:10px', border:false,
				items:
				[ 
					{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Office Phone', name : 'officePhone', value : '<c:out value="${userBean.officePhone}" />', anchor:'95%', allowBlank : true },
							{ fieldLabel: 'Mobile Phone', name : 'mobilePhone', value : '<c:out value="${userBean.mobilePhone}" />', anchor:'95%', allowBlank : false },
							{ fieldLabel: 'Email', name : 'email', value : '<c:out value="${userBean.email}" />',  vtype:'email', anchor:'95%' }
						]
					},
					{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Billing Address', name : 'billingAddress', value : '<c:out value="${userBean.billingAddress}" />', xtype: 'textareafield', anchor:'95%', allowBlank : false }
						]
					} 
				]
			},
			{ 	title: 'Others', layout:'column', bodyStyle:'padding:10px', border:false,
				items:
				[ 
					{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textareafield',
						items: 
						[
							{ fieldLabel: 'Description', name : 'description', value : '<c:out value="${userBean.description}" />', anchor:'95%', allowBlank : true }
						]
					},
					{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textareafield',
						items: 
						[
							{ fieldLabel: 'Notes', name : 'notes', value : '<c:out value="${userBean.notes}" />', anchor:'95%', allowBlank : true }
						]
					} 
				]
			}
		],
		buttons : 
		[
			{ text : 'Save', formBind : true, handler : function() { userDetailsSubmit(); } }, 
			{ text : 'Cancel', handler: function() { window.location = "<c:url value="/parties/vendors"/>" ;} } 
		]
	});
		
	var tabPanel = new Ext.tab.Panel( 
	{
		bodyStyle:'padding:5px', fieldDefaults: { labelAlign: 'left', msgTarget: 'side' }, plain: true, defaults: { bodyStyle:'padding:10px'}, activeTab: 0,
        items:
		[
			{ 	title: 'Vendor <c:out value=" -- ${userBean.firstName}" />', defaultType: 'textfield', items: [userDetailsForm] },
			{  	title: 'Invoices', defaultType: 'textfield', items: [salesList], 
				listeners: 
				{
				    activate: function(panel) 
				    {
				    	salesStore.filters.clear();
				    	salesStore.filter(
						[
							{property: "username", value: '<c:out value="${userBean.username}" />'}
						]);
					}
				}
			},
			{  	title:'Payments', defaultType: 'textfield', items: [paymentList], 
				listeners: 
				{
				    activate: function(panel) 
				    {
				    	paymentStore.filters.clear();
				    	paymentStore.filter(
						[
							{property: "username", value: '<c:out value="${userBean.username}" />'}
						]);
					}
				}
			},
			{ 	title:'Sale Hisory', defaultType: 'textfield', items: [] }
		]
	});
		
	function userDetailsSubmit()
	{
		userDetailsForm.getForm().submit(
		{
			success : function(form, action) 
			{
				Ext.Msg.alert('Success', action.result.message);
			},
			failure : function(form, action) 
			{
				Ext.Msg.alert('Warning', action.result.message);
			}
		});
	}
		
	tabPanel.render("form");
});
</script>

</html>