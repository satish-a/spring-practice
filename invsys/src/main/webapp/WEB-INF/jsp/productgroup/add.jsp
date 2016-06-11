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
	var productGroupDetailsForm = Ext.create('Ext.form.Panel',
	{
		bodyStyle:'padding:5px', labelWidth : 80, monitorValid : true, border:false, fieldDefaults: { labelAlign: 'left', msgTarget: 'side' },
		defaults: {  anchor: '100%' }, url : '<c:url value="/productgroup/add" />',
		items : 
		[
			{ 	title: 'Product Group Details', layout:'column', bodyStyle:'padding:10px', border:false,
				items:
				[ 
					{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Group Name', name : 'groupName', anchor:'95%', allowBlank : false }
						]
					},
					{ 	columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
						items: 
						[
							{ fieldLabel: 'Description', name : 'description', anchor:'95%', allowBlank : false }
						]
					}
				]
			}
		],
		buttons : 
		[
			{ text : 'Save', formBind : true, handler : function() { productGroupDetailsSubmit(); } }, 
			{ text : 'Cancel', handler: function() { window.location = "<c:url value="/productgroup"/>" ;} } 
		]
	});
			
	var tabPanel = new Ext.FormPanel( 
	{
		bodyStyle:'padding:5px', border:false, fieldDefaults: { labelAlign: 'left', msgTarget: 'side' }, defaults: { anchor: '100%' },
		items: [productGroupDetailsForm] 
    });
			
	function productGroupDetailsSubmit()
	{
		productGroupDetailsForm.getForm().submit(
		{
			success : function(form, action) 
			{
				window.location = "<c:url value="/productgroup"/>" ;
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