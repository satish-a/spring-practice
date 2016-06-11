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
	var userDetailsForm = Ext.create('Ext.form.Panel',
	{
	    bodyStyle:'padding:5px', labelWidth : 80, monitorValid : true, fieldDefaults: { labelAlign: 'left', msgTarget: 'side', labelWidth: 100, labelStyle: 'font-weight:bold' },
	    defaults: { anchor: '100%' }, url : '<c:url value="/customer/add" />', 
	    items : 
	    [
            {   title: 'Personal Details', layout:'column', bodyStyle:'padding:10px', border:false,
                items:
				[ 
				    {   columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
				        items: 
						[
							{ fieldLabel: 'Account Name', name : 'username', anchor:'95%', allowBlank : false }, 
							{ fieldLabel: 'Account Type', name : 'accountType', value : 'Customer', anchor:'95%', readOnly : true }
						]
					},
					{   columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'First Name', name : 'firstName', anchor:'95%', allowBlank : false },
							{ fieldLabel: 'Last Name', name : 'lastName', anchor:'95%', allowBlank : false }
						]
					} 
				]
			},
			{   title: 'Contact Details', layout:'column', bodyStyle:'padding:10px', border:false,
				items:
				[ 
				    {   columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Office Phone', name : 'officePhone', anchor:'95%', allowBlank : true },
							{ fieldLabel: 'Mobile Phone', name : 'mobilePhone', anchor:'95%', allowBlank : false },
							{ fieldLabel: 'Email', name : 'email', vtype:'email', anchor:'95%' }
						]
					},
					{  columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Billing Address', name : 'billingAddress', xtype: 'textareafield', anchor:'95%', allowBlank : false }
						]
					} 
				]
			},
			{   title: 'Others', layout:'column', bodyStyle:'padding:10px', border:false,
				items:
				[ 
					{   columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textareafield',
						items: 
						[
							{ fieldLabel: 'Description', name : 'description', anchor:'95%', allowBlank : true }
						]
					},
					{
						columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textareafield',
						items: 
						[
							{ fieldLabel: 'Notes', name : 'notes', anchor:'95%', allowBlank : true }
						]
					} 
				]
			}
		],
		buttons : 
		[
			{ text : 'Save', formBind : true, handler : function() { userDetailsSubmit(); } }, 
			{ text : 'Cancel', handler: function() { window.location = "<c:url value="/customer"/>" ;} } 
		]
	});
		
	var tabPanel = new Ext.FormPanel( 
	{   bodyStyle:'padding:5px', fieldDefaults: { labelAlign: 'left', msgTarget: 'side' }, defaults: { anchor: '100%' },
	    items:  [ userDetailsForm ]
	});
		
    function userDetailsSubmit()
	{
		userDetailsForm.getForm().submit(
		{
			success : function(form, action) {
				window.location = "<c:url value="/customer"/>" ;
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