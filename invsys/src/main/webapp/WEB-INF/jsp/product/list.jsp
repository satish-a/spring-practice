<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<jsp:include page="../menu/header.jsp" />
<jsp:include page="../menu/includescripts.jsp" />

<body>
	<jsp:include page="../menu/menu.jsp" />
	
	<hr class="soften">
	
	<div class="container" style="height: 500px">
		<div id="filterForm"></div>
		<div id="grid"></div>
	</div>
</body>

<script>
Ext.onReady(function() 
{
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
		model : 'Product', autoLoad : true, remoteFilter : 'true', remoteSort : 'true',
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

	var sm = new Ext.selection.CheckboxModel();

	var productList = Ext.create('Ext.grid.Panel',
	{
		store : productStore, frame : true, selModel : sm, iconCls : 'icon-user', height: 500,
		columns : 
		[
			{ text : 'Product Name', flex : 1, sortable : true, filterable: true, dataIndex : 'productName', 
				renderer : function(value, metaData, record, rowIndex, colIndex, store) 
				{
					return '<a href="<c:url value="/product/view/"/>' + value + '">' + value + '</a>';
				}
			}, 
			{ text : 'Description', flex : 1, sortable : true, filterable: true, dataIndex : 'description'}, 
			{ text : 'Product Group', flex : 1, sortable : true, filterable: true, dataIndex : 'productGroup'},
			{ text : 'Price', flex : 1, sortable : true, filterable: true, dataIndex : 'price'}
		],
		dockedItems : 
		[
			{ 	items : 
				[
					'-',
					{ 	text : 'Add', itemId : 'add', 
						handler: function() { window.location = "<c:url value="/product/add"/>" ;}
					}, 
					'-',
					{ 	text : 'Delete', action : 'delete', handler: function() { processMarkedDeletions(); } }
				],
				xtype : 'pagingtoolbar', dock : 'bottom', store : productStore, displayInfo : true,
				displayMsg : 'Displaying products {0} - {1} of {2}', emptyMsg : "No products to display"
			}
		]
	});
			
	function processMarkedDeletions()
	{
		var sels = sm.getSelection();
		if (sels.length > 0) 
		{
			var products = [];
			Ext.each(sels, function(sel) 
			{
				products.push(sel.get('productName'));
			});
					                
			Ext.Msg.show(
			{
				title: 'Delete Products?', buttons: Ext.MessageBox.YESNO, msg: 'Do you want delete the selected products?',
				fn: function(btn)
				{
					if (btn == 'yes')
					{
						sendMarkedDeletions(products);	 
					}
				}
			});
		}
	}
			
	function sendMarkedDeletions(products)
	{
		Ext.Ajax.request(
		{ 
			url: '<c:url value="/product/delete"/>', method:'POST', params: { 'products': Ext.JSON.encode(products) },
			success: function(response)
			{
				Ext.Msg.alert('Success', action.result.message);
				productStore.load();
			}
		});
	}
			
	var filterForm = new Ext.FormPanel(
	{
		title : 'Products', labelWidth : 80, frame : true, collapsible:true, collapsed:true, monitorValid : true,
		bodyStyle:'padding:10px', fieldDefaults: { labelAlign: 'left', msgTarget: 'side' }, defaults: { anchor: '100%' },
		items : 
		[
			{ 	layout:'column', border:false,
			    items:
				[
					{	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Product Name', id: 'productName_filter', anchor:'95%' }, 
							{ fieldLabel: 'Description', id: 'description_filter', anchor:'95%' }
						]
					},
			        { 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
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
			{ 	text : 'Filter', formBind : true,
				handler : function() 
				{ 
					productStore.filters.clear();
					productStore.filter(
					[
						{property: "productName", value: Ext.getCmp("productName_filter").getValue()},
						{property: "description", value: Ext.getCmp("description_filter").getValue()},
						{property: "productGroup", value: Ext.getCmp("productGroup_filter").getValue()},
						{property: "price", value: Ext.getCmp("price_filter").getValue()}
					]);
				}
			}, 
			{ 	text : 'Show All', handler: function() { productStore.clearFilter();} } 
		]
	});

	filterForm.render('filterForm');
	productList.render('grid');
});
</script>

</html>