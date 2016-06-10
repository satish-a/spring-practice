<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<jsp:include page="../menu/header.jsp" />
<body>

	<div class="container">
		<div id="login">
		</div>
	</div>

</body>

<jsp:include page="../menu/includescripts.jsp" />

<style type="text/css">
      /* Override some defaults */
      html, body {
        background-color: #eee;
      }
      body {
        padding-top: 40px;
      }
      .container {
        width: 300px;
      }
 
      /* The white background content wrapper */
      .container > .content {
        background-color: #fff;
        padding: 20px;
        margin: 0 -20px;
        -webkit-border-radius: 10px 10px 10px 10px;
           -moz-border-radius: 10px 10px 10px 10px;
                border-radius: 10px 10px 10px 10px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                box-shadow: 0 1px 2px rgba(0,0,0,.15);
      }
 
      .login-form {
        margin-left: 65px;
      }
 
    </style>
    
<script type='text/javascript'>

Ext.onReady(function()
{
	Ext.QuickTips.init();

	var login = new Ext.FormPanel(
	{
		title:'Please Login', url:'j_spring_security_check', labelWidth:80,  frame:true, defaultType:'textfield', width:300, height:150,
		monitorValid:true, bodyStyle:'padding:10px', 
		items:
		[
			{ 	fieldLabel:'User name', name:'j_username', id: 'userName', allowBlank:false,
				listeners: {
			          afterrender: function(field) 
			          {
			            field.focus();
			          }
				}
			},
			{ 	fieldLabel:'Password', name:'j_password', inputType:'password', allowBlank:false }
		],
		buttons:
		[
			{ 	text : 'Login', formBind: true, handler: function() { loginSubmit(); } },
			{ 	text : 'Reset', formBind: true, handler: function() { reset(); } }
		],
		listeners: 
		{
            afterRender: function(thisForm, options)
            {
                this.keyNav = Ext.create('Ext.util.KeyNav', this.el, 
                {
                    enter: loginSubmit,
                    scope: this
                });
            }
		}
	});
	
	function reset()
	{
		login.getForm().reset();
	}
	
	function loginSubmit()
	{
		login.getForm().submit(
		{
			method:'POST', 
			success : function(form, action)
			{
				window.location = 'home';
			},
			failure : function(form, action)
			{
				Ext.Msg.alert('Login Failed!', 'Login Failed!');
				reset();
			} 
		});
	}

	login.render('login');
});
</script>

</html>