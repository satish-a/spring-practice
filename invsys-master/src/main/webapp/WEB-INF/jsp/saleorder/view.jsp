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

	Ext.define('Product', 
	{
		extend : 'Ext.data.Model',
		fields : 
		[ 
			{name : 'id', type : 'int', useNull : true}, 
			'productName', 'description', 'productGroup', 'price'
		]
	});
	var productStore = Ext.create('Ext.data.Store', 
	{
		model : 'Product',
		remoteFilter : 'true', remoteSort : 'true', autoLoad : true,
		proxy : 
		{
			type : 'ajax',
			api : { read : '<c:url value="/product/list"/>' },
			reader : { type : 'json', root : 'data', successProperty : 'success' },
			listeners : 
			{
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
	Ext.define('SalesItems', 
	{
		extend : 'Ext.data.Model',
		fields : 
		[ 
			{name : 'id', type : 'int', useNull : true},
			'productName', 'quantity', 'unitPrice', 'discount'
		],
		validations : 
		[ 
			{ type : 'length', field : 'productName', min : 1 },
			{ type : 'length', field : 'quantity', min : 1 }, 
			{ type : 'length', field : 'unitPrice', min : 1 },
			{ type : 'length', field : 'discount', min : 1 }
		]
	});
	var itemsStore = Ext.create('Ext.data.Store', 
	{
		model : 'SalesItems', autoLoad : true,
		proxy : 
		{
			type : 'ajax',
			api : { read : '<c:url value="/saleorder/view/${soBean.id}/itemList"/>' },
			reader : { type : 'json', root : 'data', successProperty : 'success' },
			listeners : 
			{
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
	Ext.define('User', 
	{
		extend : 'Ext.data.Model',
		fields : 
		[ 
			{name : 'id', type : 'int', useNull : true}, 
			'firstName', 'lastName', 'username'
		]
	});
	var userStore = Ext.create('Ext.data.Store', 
	{
		autoLoad : true,
		model : 'User',
		queryMode: 'local',
		proxy : 
		{
			type : 'ajax',
			api : {	read : '<c:url value="/customer/list"/>' },
			reader : { type : 'json', root : 'data', successProperty : 'success' },
			listeners : 
			{
				exception : function(proxy, response, operation) 
				{
					Ext.MessageBox.show({ title : 'REMOTE EXCEPTION', msg : operation.getError(), icon : Ext.MessageBox.ERROR, buttons : Ext.Msg.OK});
				}
			}
		}
	});

    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToMoveEditor: 1,
        autoCancel: false
    });
    
    Ext.util.Format.Currency = function(v)
    {
    	v = (Math.round((v-0)*100))/100;
    	v = (v == Math.floor(v)) ? v + ".00" : ((v*10 == Math.floor(v*10)) ? v + "0" : v);
    	return ('INR ' + v);
    };

	// Sales Order Item List
	var itemsList = Ext.create('Ext.grid.Panel',
    {
		title : "Items", store: itemsStore,
		frame : true, height : 250, iconCls : 'icon-user',
		columns : 
		[
			{ header : 'Product Name', flex : 1, sortable : true, filterable: true, dataIndex : 'productName', 
				editor: { allowBlank: false, xtype: 'combobox', store: productStore, displayField: 'productName'}}, 
			{ header : 'Quantity', flex : 1, sortable : true, filterable: true, dataIndex : 'quantity', 
				editor: { allowBlank: false, xtype : 'numberfield' }, summaryType: 'sum'}, 
			{ header : 'Unit Price', flex : 1, sortable : true, filterable: true, dataIndex : 'unitPrice', 
				editor: { allowBlank: false, xtype: 'numberfield'}},
			{ header : 'Discount', flex : 1, sortable : true, filterable: true, dataIndex : 'discount', 
				editor: { allowBlank: false, xtype: 'numberfield' }},
			{ header: 'Total', flex : 1,  sortable: false, groupable: false,
				renderer: function(value, metaData, record, rowIdx, colIdx, store, view) 
				{
					return Ext.util.Format.Currency( ( record.get('quantity') * record.get('unitPrice') ) * (100 - record.get('discount') ) / 100 );
				},
				summaryType: function(records)
				{
					var i = 0,
					length = records.length,
					total = 0,
					record;
					for (; i < length; ++i) 
					{
						record = records[i];
						total += ( record.get('quantity') * record.get('unitPrice') ) * (100 - record.get('discount') ) / 100 ;
					}
					return total;
				},
				summaryRenderer: Ext.util.Format.Currency
	        }
		],
		dockedItems : 
		[
			{
				xtype: 'toolbar', dock: 'bottom', ui: 'footer', layout: { pack: 'center' },
				items: 
				[
					'-',
					{ text : 'Add', itemId : 'add', handler: function() { addItemToList(); }}, 
					'-',
					{ text : 'Delete', action : 'delete', handler: function() { deleteFromList() ;}},
					'-'
				]
			}
		],
		plugins: [rowEditing]
	});

	var SalesOrderForm = Ext.create('Ext.form.Panel',
	{
		bodyStyle:'padding:5px',
		url : '<c:url value="/saleorder/update" />',
		items : 
		[
			{
				title: 'Sales Order',
				layout:'column', bodyStyle:'padding:10px', border:false, labelWidth : 80, monitorValid : true,
				fieldDefaults: { labelAlign: 'left', msgTarget: 'side' },
				defaults: { anchor: '100%' },
				items:
				[ 
					{
						columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel : 'Order Number', name : 'orderId', value : '<c:out value="${soBean.orderId}" />', anchor:'95%', allowBlank : false, readOnly: true}, 
							{ fieldLabel : 'Customer', name : 'userName', value : '<c:out value="${soBean.userName}" />', anchor:'95%', allowBlank : false,  readOnly: true }, 
							{ fieldLabel : 'Reference Number', name : 'referenceNumber', value : '<c:out value="${soBean.referenceNumber}" />', anchor:'95%', allowBlank : false},
						]
					},
					{
						columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel : 'Order Type', name : 'orderType', value : '<c:out value="${soBean.orderType}" />', anchor:'95%', allowBlank : false}, 
							{ fieldLabel : 'Order Date', name : 'orderDate', value : '<c:out value="${soBean.orderDate}" />', xtype: 'datefield', anchor:'95%', allowBlank : false, readonly: true},
							{ fieldLabel : 'Due Date', name : 'dueDate', value : '<c:out value="${soBean.dueDate}" />', xtype: 'datefield', anchor:'95%', allowBlank : false}
						]
					}
				]
			},
			{
				border:false, bodyStyle:'padding:10px', layout: 'anchor', defaultType: 'textfield',
				items: [ { fieldLabel : 'Comments', name : 'comments', value : '<c:out value="${soBean.comments}" />', xtype: 'textareafield', anchor:'98%', allowBlank : false} ]
			},
			{
				border:false, bodyStyle:'padding:10px', layout: 'anchor', defaultType: 'textfield', items: [ itemsList ]
			},
			{   border: false, defaultType: 'textfield', xtype: 'panel', flex: 1, frame: true,
				html: '<h3>Total# <c:out value="${soBean.total}" /> INR'
			}
		],
		buttons : 
		[
			{ text : 'Save', formBind : true, handler : function() { SalesOrderSubmit(); } }, 
			{ text : 'Cancel', handler: function() { window.location = "<c:url value="/saleorder"/>" ;} } 
		],
		buttonAlign : 'left'
	});
		
	function addItemToList()
	{
		rowEditing.cancelEdit();
		var r = Ext.create('SalesItems', 
		{
			productName: '',
			quantity: '',
			unitPrice: '',
			discount: ''
		});

		itemsStore.insert(0, r);
		rowEditing.startEdit(0, 0);
	}

	function deleteFromList()
	{
		var sm = itemsList.getSelectionModel();
        rowEditing.cancelEdit();
        itemsStore.remove(sm.getSelection());
        if (itemsStore.getCount() > 0) 
        {
            sm.select(0);
        }
	}
	
	function SalesOrderSubmit()
	{
		var myItems = itemsStore.getRange();
		var myJson = [];
		for (var i in myItems) 
		{
			myJson.push(
			{
				'productName': myItems[i].get('productName'),
				'quantity': myItems[i].get('quantity'),
				'unitPrice': myItems[i].get('unitPrice'),
				'discount': myItems[i].get('discount')
			});
		}
		SalesOrderForm.getForm().submit(
		{
			params : {items: Ext.encode(myJson)},
			success : function(form, action) 
			{
				Ext.Msg.alert('Success', action.result.message, function(btn){
				    if (btn == 'ok'){
				    	window.location = "<c:url value="/saleorder/view/${soBean.orderId}"/>" ;
				    }
				});
				
				 
			},
			failure : function(form, action) 
			{
				Ext.Msg.alert('Warning', action.result.message);
			}
		});
	}
		
	SalesOrderForm.render("form");
});
</script>

</html>