<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>

Ext.require([ '*' ]);

Ext.onReady(function() {
	Ext.QuickTips.init();

	var partiesMenu = Ext.create('Ext.menu.Menu', 
	{
		id: 'partiesMenu',
		items: 
		[
			{ text: 'Customers', href: "<c:url value="/customer"/>" }, 
			{ text: 'Vendors', href: "<c:url value="/vendor"/>" }, 
			{ text: 'Staff', href: "<c:url value="/staff"/>" }
        ]
	});
	
	var productsMenu = Ext.create('Ext.menu.Menu', 
	{
		id: 'productsMenu',
		items: 
		[
			{ text: 'Products', href: "<c:url value="/product"/>" }, 
			{ text: 'Product Groups', href: "<c:url value="/productgroup"/>" }
		]
	});
	
	var purchasesMenu = Ext.create('Ext.menu.Menu', 
	{
		id: 'purchasesMenu',
		items: 
		[
			{ text: 'Purchase Summary', href: "<c:url value="/purchaseorder"/>" }, 
			{ text: 'Purchase Order List', href: "<c:url value="/purchaseorder/orderList"/>" },
			{ text: 'New Purchase Order', href: "<c:url value="/purchaseorder/add"/>" }
		]
	});

	var salesMenu = Ext.create('Ext.menu.Menu', 
	{
		id: 'salesMenu',
		items: 
		[
			{ text: 'Sales Summary', href: "<c:url value="/saleorder"/>" }, 
			{ text: 'Sale Order List', href: "<c:url value="/saleorder/orderList"/>" },
			{ text: 'New Sales Order', href: "<c:url value="/saleorder/add"/>" }
		]
	});
	
	var paymentsMenu = Ext.create('Ext.menu.Menu', 
	{
		id: 'paymentsMenu',
		items: 
		[
			{ text: 'Payments List', href: "<c:url value="/payments"/>" },
			{ text: 'New Payment', href: "<c:url value="/payments/add"/>" }
		]
	});
	
	var tb = Ext.create('Ext.toolbar.Toolbar');
	tb.suspendLayout = true;
	tb.render('toolbar');

	tb.add({
		text : 'Home',
		handler: function() { window.location = "<c:url value="/home"/>" ;},
		group : 'theme'
	});
	
    tb.add({
		text : 'Purchases',
		menu: purchasesMenu
	});
    
    tb.add({
		text : 'Sales',
		menu: salesMenu
	});

    tb.add({
		text : 'Payments',
		menu: paymentsMenu
	});

    tb.add({
		text : 'Parties',
        menu: partiesMenu
	});

	tb.add({
		text : 'Products',
        menu: productsMenu
	});
	

/* 
	tb.add({
		text : 'Users',
		handler: function() { window.location = "<c:url value="/users"/>" ;},
		group : 'theme'
	});
    
    tb.add({
		text : 'Products',
		handler: function() { window.location = "<c:url value="/products"/>" ;},
		group : 'theme'
	});

    tb.add({
		text : 'Orders',
		handler: function() { window.location = "<c:url value="/orders"/>" ;},
		group : 'theme'
	});
    
    tb.add({
		text : 'Payments',
		handler: function() { window.location = "<c:url value="/payments"/>" ;},
		group : 'theme'
	});
 */
	tb.add({
		xtype: 'tbfill'
	});

    tb.add({
		text : '<%=SecurityContextHolder.getContext().getAuthentication().getName()%>',
		handler: function() { window.location = "/" ;}
	});
	
    tb.add({
		text : 'Logout',
		handler: function() { window.location = "<c:url value="/logout"/>" ;}
	});

	tb.suspendLayout = false;
	tb.doLayout();
});


</script>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<div id="toolbar"></div>
		</div>
	</div>
</div>
