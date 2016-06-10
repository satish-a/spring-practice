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

<script type='text/javascript'>

Ext.onReady(function() 
{
	Ext.define('User', 
	{   extend : 'Ext.data.Model',
	    fields : 
	    [ 
			{name : 'id', type : 'int', useNull : true}, 
			'firstName', 'lastName', 'username', 'accountType', 'billingAddress', 'mobilePhone', 'email'
		]
	});

	var userStore = Ext.create('Ext.data.Store', 
	{   model : 'User', autoLoad : true, remoteFilter : 'true', remoteSort : 'true',
		proxy : 
		{
			type : 'ajax', api : { read : '<c:url value="/customer/list"/>' },
			reader : { type : 'json', root : 'data', successProperty : 'success' },
			listeners : {
				exception : function(proxy, response, operation) 
				{
					Ext.MessageBox.show(
					{ title : 'REMOTE EXCEPTION', msg : operation.getError(), icon : Ext.MessageBox.ERROR, buttons : Ext.Msg.OK });
				}
			}
		}
	});

	var sm = new Ext.selection.CheckboxModel();

	var userList = Ext.create('Ext.grid.Panel',
	{
		store : userStore, frame : true, selModel : sm, iconCls : 'icon-user', height : 500,
		columns : 
		[
			{ text : 'Account Name', flex : 1, sortable : true, filterable: true, dataIndex : 'username',
				renderer : function(value, metaData, record, rowIndex, colIndex, store) 
				{
					return '<a href="<c:url value="/customer/view/"/>' + value + '">' + value + '</a>';
				}
			}, 
			{ text : 'First Name', flex : 1, sortable : true, filterable: true, dataIndex : 'firstName'}, 
			{ text : 'Last Name', flex : 1, sortable : true, filterable: true, dataIndex : 'lastName'},
			{ text : 'Mobile Phone', flex : 1, sortable : true, filterable: true, dataIndex : 'mobilePhone'},
			{ text : 'Email Address', flex : 1, sortable : true, filterable: true, dataIndex : 'email'}, 
			{ text : 'Billing Address', flex : 1, sortable : true, filterable: true, dataIndex : 'billingAddress'} 
		],
		dockedItems : 
		[
			{   items : 
			    [
					'-',
					{ text : 'Add', itemId : 'add', 
						handler: function() { window.location = "<c:url value="/customer/add"/>" ;}}, 
					'-',
					{ text : 'Delete', action : 'delete', handler: function() { processMarkedDeletions(); } }
				],
				xtype : 'pagingtoolbar', dock : 'bottom', store : userStore, displayInfo : true,
				displayMsg : 'Displaying customers {0} - {1} of {2}', emptyMsg : "No customers to display"
					
			}
		]
	});
	
	function processMarkedDeletions()
	{
		var sels = sm.getSelection();
		if (sels.length > 0) 
		{
			var users = [];
			Ext.each(sels, function(sel) 
			{
				users.push(sel.get('username'));
			});
			                
			Ext.Msg.show(
			{
				title: 'Delete Customers?', buttons: Ext.MessageBox.YESNO, msg: 'Do you want delete the selected customers?',
				fn: function(btn)
				{
					if (btn == 'yes')
					{
						sendMarkedDeletions(users);	 
					}
				}
			});
		}
	}
	
	function sendMarkedDeletions(users)
	{
		Ext.Ajax.request(
		{ 
			url: '<c:url value="/customer/delete"/>', method:'POST', params: { 'users': Ext.JSON.encode(users) },
			success: function(response)
			{
				Ext.Msg.alert('Success', 'Removed Customers Successfully');
				userStore.load();
			}
		});
	}
	
	var filterForm = new Ext.FormPanel(
	{
		title : 'Customers', labelWidth : 80, frame : true, collapsible:true, collapsed:true, monitorValid : true, bodyStyle:'padding:10px',
	    fieldDefaults: { labelAlign: 'left', msgTarget: 'side' }, defaults: { anchor: '100%' },
	    items : 
		[
			{   layout:'column', border:false,
	            items:
				[
					{   columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
					    items: 
						[
							{ fieldLabel: 'Account Name', id: 'username_filter', anchor:'95%' }, 
							{ fieldLabel: 'First Name', id: 'firstName_filter', anchor:'95%' }
						]
	            	},
	            	{   columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
	                	items: 
						[
							{ fieldLabel: 'Last Name', id: 'lastName_filter', anchor:'95%' },
							{ fieldLabel: 'Email', id: 'email_filter', vtype:'email', anchor:'95%' }
						]
					}
				]
			}
		],
		buttons : 
		[
			{   text : 'Filter', formBind : true,
				handler : function() 
				{
					userStore.filters.clear();
					userStore.filter(
					[
						{property: "username", value: Ext.getCmp("username_filter").getValue()},
						{property: "firstName", value: Ext.getCmp("firstName_filter").getValue()},
						{property: "lastName", value: Ext.getCmp("lastName_filter").getValue()},
						{property: "email", value: Ext.getCmp("email_filter").getValue()}
					]);
				}
			}, 
			{   text : 'Show All', handler: function() { userStore.clearFilter();} } 
		]
	});

	filterForm.render('filterForm');
	userList.render('grid');
});
</script>

</html>