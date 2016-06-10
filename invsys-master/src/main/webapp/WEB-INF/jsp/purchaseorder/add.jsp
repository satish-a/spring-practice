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
						title : 'REMOTE EXCEPTION', msg : operation.getError(), icon : Ext.MessageBox.ERROR, buttons : Ext.Msg.OK
					});
				}
			}
		}
	});
	Ext.define('PurchasedItems', 
	{
		extend : 'Ext.data.Model',
		fields : 
		[ 
			{name : 'id', type : 'int', useNull : true},
			{name: 'order', type: 'string', defaultValue: 'Purchase Order'},
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
		model : 'PurchasedItems', autoLoad : false, groupField: 'order',
		proxy : 
		{
			type : 'ajax',
			api : { read : '<c:url value="/purchaseorder/view/${poBean.id}/itemList"/>' },
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
		model : 'User', autoLoad : true, queryMode: 'local',
		proxy : 
		{
			type : 'ajax',
			api : {	read : '<c:url value="/vendor/list"/>' },
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

    var rowEditing = Ext.create( 'Ext.grid.plugin.RowEditing', { clicksToMoveEditor: 1, autoCancel: false } );
    
    Ext.util.Format.Currency = function(v)
    {
    	v = (Math.round((v-0)*100))/100;
    	v = (v == Math.floor(v)) ? v + ".00" : ((v*10 == Math.floor(v*10)) ? v + "0" : v);
    	return ('INR ' + v);
    };

	// Purchase Order Item List
	var itemsList = Ext.create('Ext.grid.Panel',
    {
		title : "Items", store: itemsStore, frame : true, height : 250, iconCls : 'icon-user',
		columns : 
		[
			{ header : 'Product Name', flex : 1, sortable : true, filterable: true, dataIndex : 'productName', 
				editor: { allowBlank: false, xtype: 'combobox', store: productStore, displayField: 'productName'}}, 
			{ header : 'Quantity', flex : 1, sortable : true, filterable: true, dataIndex : 'quantity', 
				editor: { allowBlank: false}, summaryType: 'sum'}, 
			{ header : 'Unit Price', flex : 1, sortable : true, filterable: true, dataIndex : 'unitPrice', 
				editor: { allowBlank: false}},
			{ header : 'Discount', flex : 1, sortable : true, filterable: true, dataIndex : 'discount', 
				editor: { allowBlank: false}},
			{ header : 'Purchase Order', flex : 1, sortable : true, filterable: true, dataIndex : 'order', 
				editor: { allowBlank: false, xtype: 'textfield' }},
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
		plugins: [rowEditing],
        features: [ { id: 'group', ftype: 'groupingsummary', groupHeaderTpl: '{order}', hideGroupedHeader: true, enableGroupingMenu: false } ]
	});

	var purchaseOrderForm = Ext.create('Ext.form.Panel',
	{
		bodyStyle:'padding:5px', url : '<c:url value="/purchaseorder/add" />',
		items : 
		[
			{
				title: 'Purchase Order', layout:'column', bodyStyle:'padding:10px', border:false, labelWidth : 80, monitorValid : true,
				fieldDefaults: { labelAlign: 'left', msgTarget: 'side' }, defaults: { anchor: '100%' },
				items:
				[ 
					{	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ 	fieldLabel : 'Order Number', id : 'orderId', name : 'orderId', anchor:'95%', allowBlank : false}, 
							{ 	fieldLabel : 'Vendor', name : 'userName', anchor:'95%', allowBlank : false,
								xtype: 'combobox', store: userStore, displayField: 'firstName',
								listConfig: {
							        getInnerTpl: function() {
							            return '<div data-qtip="{username}">{firstName} {lastName}</div>';
							        }
							    }}, 
							{ 	fieldLabel : 'Reference Number', name : 'referenceNumber', anchor:'95%', allowBlank : false},
						]
					},
					{	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel : 'Order Type', name : 'orderType', anchor:'95%', allowBlank : false}, 
							{ fieldLabel : 'Order Date', name : 'orderDate', xtype: 'datefield', anchor:'95%', allowBlank : false},
							{ fieldLabel : 'Due Date', name : 'dueDate', xtype: 'datefield', anchor:'95%', allowBlank : false}
						]
					}
				]
			},
			{	border:false, bodyStyle:'padding:10px', layout: 'anchor', defaultType: 'textfield',
				items: [ { fieldLabel : 'Comments', name : 'comments', xtype: 'textareafield', anchor:'98%', allowBlank : false} ]
			},
			{
				border:false, bodyStyle:'padding:10px', layout: 'anchor', defaultType: 'textfield', items: [ itemsList ]
			}
		],
		buttons : 
		[
			{ text : 'Save', formBind : true, handler : function() { purchaseOrderSubmit(); } }, 
			{ text : 'Cancel', handler: function() { window.location = "<c:url value="/purchaseorder"/>" ;} } 
		],
		buttonAlign : 'left'
	});
		
	function addItemToList()
	{
		rowEditing.cancelEdit();
		var r = Ext.create('PurchasedItems', 
		{
			productName: '', quantity: '', unitPrice: '', discount: ''
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
	
	function purchaseOrderSubmit()
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
		purchaseOrderForm.getForm().submit(
		{
			params : {items: Ext.encode(myJson)},
			success : function(form, action) 
			{
				Ext.Msg.alert('Success', action.result.message, function(btn){
				    if (btn == 'ok'){
				    	window.location = "<c:url value="/purchaseorder/view/"/>" + Ext.getCmp("orderId").getValue() ;
				    }
				});
			},
			failure : function(form, action) 
			{
				Ext.Msg.alert('Warning', action.result.message);
			}
		});
	}
		
	purchaseOrderForm.render("form");
});
</script>

</html>