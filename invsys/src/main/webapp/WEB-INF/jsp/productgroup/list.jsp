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
	Ext.define('ProductGroup', 
	{
		extend : 'Ext.data.Model',
		fields : 
		[ 
			{name : 'id', type : 'int', useNull : true}, 'groupName', 'description'
		]
	});
			
	var productGroupStore = Ext.create('Ext.data.Store', 
	{
		model : 'ProductGroup', autoLoad : true, remoteFilter : 'true', remoteSort : 'true',
		proxy : 
		{
			type : 'ajax',
			api : { read : '<c:url value="/productgroup/list"/>' },
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
		store : productGroupStore, frame : true, selModel : sm, iconCls : 'icon-user', height: 500,
		columns : 
		[
			{ 	text : 'Group Name', flex : 1, sortable : true, filterable: true, dataIndex : 'groupName', 
				renderer : function(value, metaData, record, rowIndex, colIndex, store) 
				{
					return '<a href="<c:url value="/productgroup/view/"/>' + value + '">' + value + '</a>';
				}
			}, 
			{ text : 'Description', flex : 1, sortable : true, filterable: true, dataIndex : 'description'}
		],
		dockedItems : 
		[
			{ 	items : 
				[
					'-',
					{ text : 'Add', itemId : 'add', handler: function() { window.location = "<c:url value="/productgroup/add"/>" ;}}, 
					'-',
					{ text : 'Delete', action : 'delete', handler: function() { processMarkedDeletions(); } }
				],
				xtype : 'pagingtoolbar', dock : 'bottom', store : productGroupStore, displayInfo : true,
				displayMsg : 'Displaying product groups {0} - {1} of {2}', emptyMsg : "No product groups to display"
			}
		]
	});
			
	function processMarkedDeletions()
	{
		var sels = sm.getSelection();
		if (sels.length > 0) 
		{
			var groups = [];
			Ext.each(sels, function(sel) 
			{
				products.push(sel.get('groupName'));
			});
			Ext.Msg.show(
			{
				title: 'Delete Product Groups?', buttons: Ext.MessageBox.YESNO, msg: 'Do you want delete the selected products?',
				fn: function(btn)
				{
					if (btn == 'yes')
					{
						sendMarkedDeletions(groups);	 
					}
				}
			});
		}
	}
			
	function sendMarkedDeletions(groups)
	{
		Ext.Ajax.request(
		{ 
			url: '<c:url value="/productgroup/delete"/>', method:'POST', params: { 'groups': Ext.JSON.encode(groups) },
			success: function(response)
			{
				Ext.Msg.alert('Success', action.result.message);
				productGroupStore.load();
			}
		});
	}
			
	var filterForm = new Ext.FormPanel(
	{
		title : 'Product Groups', labelWidth : 80, frame : true, collapsible:true, collapsed:true, monitorValid : true, bodyStyle:'padding:10px',
		fieldDefaults: { labelAlign: 'left', msgTarget: 'side' }, defaults: { anchor: '100%' },
		items : 
		[
			{ 	layout:'column', border:false,
				items:
				[
					{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Group Name', id: 'groupName_filter', anchor:'95%' }
						]
					},
					{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Description', id : 'description_filter', anchor:'95%' }
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
					productGroupStore.filters.clear();
					productGroupStore.filter(
					[
						{property: "groupName", value: Ext.getCmp("groupName_filter").getValue()},
						{property: "description", value: Ext.getCmp("description_filter").getValue()}
					]);
				}
			}, 
			{ 	text : 'Show All', handler: function() { productGroupStore.clearFilter(); } } 
		]
	});

	filterForm.render('filterForm');
	productList.render('grid');

});
</script>

</html>