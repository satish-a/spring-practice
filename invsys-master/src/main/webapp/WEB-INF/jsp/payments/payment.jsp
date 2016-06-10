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
			autoLoad : true, model : 'User', queryMode: 'local',
			proxy : 
			{
				type : 'ajax',
				api : {	read : '<c:url value="/parties/list"/>' },
				reader : { type : 'json', root : 'data', successProperty : 'success' },
				listeners : 
				{
					exception : function(proxy, response, operation) 
					{
						Ext.MessageBox.show({ title : 'REMOTE EXCEPTION', msg : response.message, icon : Ext.MessageBox.ERROR, buttons : Ext.Msg.OK});
					}
				}
			}
		});
		var paymentForm = Ext.create('Ext.form.Panel',
		{
			title: 'Payment Details', bodyStyle:'padding:5px', labelWidth : 80, monitorValid : true,
	        fieldDefaults: { labelAlign: 'left', msgTarget: 'side' },
	        defaults: { anchor: '100%' },
			url : '<c:url value="/payments/"/><c:out value="${operationName}" />',
			items : 
			[
				{
					layout:'column', bodyStyle:'padding:10px', border:false,
					items:
					[ 
						{
							columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
							items: 
							[
								{ fieldLabel: 'User Name', name : 'userName', value : '<c:out value="${paymentBean.userName}" />', anchor:'95%', allowBlank : false, 
									xtype: 'combobox', store: userStore, displayField: 'username',
									listConfig: {
								        getInnerTpl: function() {
								            return '<div data-qtip="{username}">{firstName} {lastName}</div>';
								        }
								    }}, 
								{ fieldLabel: 'Amount', name : 'amount', value : '<c:out value="${paymentBean.amount}" />', anchor:'95%', allowBlank : false },
							]
						},
						{
							columnWidth:.5, border:false, layout: 'anchor', defaultType: 'textfield',
							items: 
							[
								{ fieldLabel: 'Payment Date', name : 'paymentDate', value : '<c:out value="${paymentBean.paymentDate}" />', 
									anchor:'95%', xtype: 'datefield', allowBlank : false },
								{ fieldLabel: 'Payment Type', name : 'paymentType', value : '<c:out value="${paymentBean.paymentType}" />', anchor:'95%', allowBlank : false },
							]
						}
					]
				},
				{
					border:false, layout: 'anchor', defaultType: 'textfield', bodyStyle:'padding:10px',
					items: 
					[
						{ fieldLabel: 'Notes', name : 'notes', value : '<c:out value="${paymentBean.notes}" />', anchor:'98%', 
							allowBlank : false, xtype: 'textareafield' }
					]
				},
				{
					hidden: true, name : 'id', value : '<c:out value="${paymentBean.id}" />', anchor:'98%', 
						allowBlank : false, xtype: 'hidden'
				}
			],
			buttons : 
			[
				{ text : 'Save', formBind : true, handler : function() { paymentFormSubmit(); } }, 
				{ text : 'Cancel', handler: function() { window.location = "<c:url value="/payments"/>" ;} } 
			],
			buttonAlign : 'left'
		});
		
		function paymentFormSubmit()
		{
			paymentForm.getForm().submit(
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
		
		paymentForm.render("form");
	});
</script>

</html>