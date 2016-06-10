<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Inventory System</title>

<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/ext-neptune.css"/>' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/sink.css"/>' />

<script type='text/javascript' src='<c:url value="/resources/js/ext-all.js"/>'></script>
<script type='text/javascript' src='<c:url value="/resources/js/all-classes.js"/>'></script>
<script type='text/javascript' src='<c:url value="/resources/js/ext-neptune.js"/>'></script>
</head>

<script>
Ext.onReady(function () {
	Ext.define('SalesSum', { extend : 'Ext.data.Model', fields : ['day', 'amount'] });
					
	var yearlySummaryStore = Ext.create('Ext.data.Store', 
	{
		model : 'SalesSum', autoLoad : true, remoteFilter : 'true', remoteSort : 'true',
		proxy : 
		{ 
			type : 'ajax', api : { read : '<c:url value="/saleorder/yealySummary"/>' },
			reader : { type : 'json', root : 'data', successProperty : 'success' },
			listeners : 
			{
				exception : function(proxy, response, operation) 
				{
					Ext.MessageBox.show(
						{ title : 'REMOTE EXCEPTION', msg : operation.getError(), icon : Ext.MessageBox.ERROR, buttons : Ext.Msg.OK }); 
				}
			}
		}
	});

	var yearlySummary = Ext.create('Ext.chart.Chart', 
	{
		xtype: 'chart', store: yearlySummaryStore, animate: true, height: 240, width: 350,
		axes: 
		[
			{ type: 'Numeric', position: 'left', fields: ['amount'], title: 'Amount', grid: true, minimum: 0,
				label: { renderer: Ext.util.Format.numberRenderer('0,0') }
			}, 
			{ type: 'Category', position: 'bottom', fields: ['day'], title: 'Day of the Month'}
		],
		series: 
		[
			{ type: 'column', axis: 'left', highlight: true, xField: 'day', yField: 'amount',
				tips: 
				{ 
					trackMouse: true, width: 140, height: 28,
					renderer: function(storeItem, item) 
					{
						this.setTitle(storeItem.get('day') + ': ' + storeItem.get('amount') + ' INR');
					}
				},
				label: { display: 'insideEnd', 'text-anchor': 'middle', field: 'amount', renderer: Ext.util.Format.numberRenderer('0'),
						orientation: 'vertical', color: '#333' }
			}
		]
	});

	var monthlySummaryStore = Ext.create('Ext.data.Store', 
	{
		model : 'SalesSum', autoLoad : true, remoteFilter : 'true', remoteSort : 'true',
		proxy : 
		{ 
			type : 'ajax', api : { read : '<c:url value="/saleorder/monthlySummary"/>' },
			reader : { type : 'json', root : 'data', successProperty : 'success' },
			listeners : 
			{
				exception : function(proxy, response, operation) 
				{
					Ext.MessageBox.show(
						{ title : 'REMOTE EXCEPTION', msg : operation.getError(), icon : Ext.MessageBox.ERROR, buttons : Ext.Msg.OK }); 
				}
			}
		}
	});
			
	var monthlySummary = Ext.create('Ext.chart.Chart', 
	{
		xtype: 'chart', store: monthlySummaryStore, animate: true, height: 240, width: 350,
		axes: 
		[
			{ type: 'Numeric', position: 'left', fields: ['amount'], title: 'Amount', grid: true, minimum: 0,
				label: { renderer: Ext.util.Format.numberRenderer('0,0') }
			}, 
			{ type: 'Category', position: 'bottom', fields: ['day'], title: 'Day of the Month'}
		],
		series: 
		[
			{ type: 'column', axis: 'left', highlight: true, xField: 'day', yField: 'amount',
				tips: 
				{ 
					trackMouse: true, width: 140, height: 28,
					renderer: function(storeItem, item) 
					{
						this.setTitle(storeItem.get('day') + ': ' + storeItem.get('amount') + ' INR');
					}
				},
				label: { display: 'insideEnd', 'text-anchor': 'middle', field: 'amount', renderer: Ext.util.Format.numberRenderer('0'),
					orientation: 'vertical', color: '#333' }
			}
		]
	});
	
	Ext.define('PurchaseSum', { extend : 'Ext.data.Model', fields : ['day', 'amount'] });
	
	var yearlyPurchaseStore = Ext.create('Ext.data.Store', 
	{
		model : 'PurchaseSum', autoLoad : true, remoteFilter : 'true', remoteSort : 'true',
		proxy : 
		{ 
			type : 'ajax', api : { read : '<c:url value="/purchaseorder/yealySummary"/>' },
			reader : { type : 'json', root : 'data', successProperty : 'success' },
			listeners : 
			{
				exception : function(proxy, response, operation) 
				{
					Ext.MessageBox.show(
						{ title : 'REMOTE EXCEPTION', msg : operation.getError(), icon : Ext.MessageBox.ERROR, buttons : Ext.Msg.OK }); 
				}
			}
		}
	});

	var yearlyPurchaseSummary = Ext.create('Ext.chart.Chart', 
	{
		xtype: 'chart', store: yearlyPurchaseStore, animate: true, height: 240, width: 350,
		axes: 
		[
			{ type: 'Numeric', position: 'left', fields: ['amount'], title: 'Amount', grid: true, minimum: 0,
				label: { renderer: Ext.util.Format.numberRenderer('0,0') }
			}, 
			{ type: 'Category', position: 'bottom', fields: ['day'], title: 'Day of the Month'}
		],
		series: 
		[
			{ type: 'column', axis: 'left', highlight: true, xField: 'day', yField: 'amount',
				tips: 
				{ 
					trackMouse: true, width: 140, height: 28,
					renderer: function(storeItem, item) 
					{
						this.setTitle(storeItem.get('day') + ': ' + storeItem.get('amount') + ' INR');
					}
				},
				label: { display: 'insideEnd', 'text-anchor': 'middle', field: 'amount', renderer: Ext.util.Format.numberRenderer('0'),
						orientation: 'vertical', color: '#333' }
			}
		]
	});

	var monthlyPurchaseStore = Ext.create('Ext.data.Store', 
	{
		model : 'PurchaseSum', autoLoad : true, remoteFilter : 'true', remoteSort : 'true',
		proxy : 
		{ 
			type : 'ajax', api : { read : '<c:url value="/purchaseorder/monthlySummary"/>' },
			reader : { type : 'json', root : 'data', successProperty : 'success' },
			listeners : 
			{
				exception : function(proxy, response, operation) 
				{
					Ext.MessageBox.show(
						{ title : 'REMOTE EXCEPTION', msg : operation.getError(), icon : Ext.MessageBox.ERROR, buttons : Ext.Msg.OK }); 
				}
			}
		}
	});
			
	var monthlyPurchaseSummary = Ext.create('Ext.chart.Chart', 
	{
		xtype: 'chart', store: monthlyPurchaseStore, animate: true, height: 240, width: 350,
		axes: 
		[
			{ type: 'Numeric', position: 'left', fields: ['amount'], title: 'Amount', grid: true, minimum: 0,
				label: { renderer: Ext.util.Format.numberRenderer('0,0') }
			}, 
			{ type: 'Category', position: 'bottom', fields: ['day'], title: 'Day of the Month'}
		],
		series: 
		[
			{ type: 'column', axis: 'left', highlight: true, xField: 'day', yField: 'amount',
				tips: 
				{ 
					trackMouse: true, width: 140, height: 28,
					renderer: function(storeItem, item) 
					{
						this.setTitle(storeItem.get('day') + ': ' + storeItem.get('amount') + ' INR');
					}
				},
				label: { display: 'insideEnd', 'text-anchor': 'middle', field: 'amount', renderer: Ext.util.Format.numberRenderer('0'),
					orientation: 'vertical', color: '#333' }
			}
		]
	});


			
	var tabPanel = Ext.create('Ext.Panel', 
	{
		height: 650, layout: { type: 'vbox', align: 'center', pack: 'center' },
		defaults: { defaults: { width: 400, height: 300, bodyPadding: 10, autoScroll: true, margin: 10 }, margin: '0 0 10 0' },
	    items: 
	    [
			{ 	xtype: 'panel', style: 'background:transparent', bodyStyle: 'background:transparent',
                layout: { type: 'hbox', align: 'center', pack: 'center' },
                items: 
				[
					{ 	xtype: 'panel', frame: true,  title: 'Daily sales for current month', items: [monthlySummary] },
					{ 	xtype: 'panel', frame: true,  title: 'Monthly sales for current year',items: [yearlySummary] }
				]
			},
			{ 	xtype: 'panel', style: 'background:transparent', bodyStyle: 'background:transparent',
                layout: { type: 'hbox', align: 'center', pack: 'center' },
                items: 
				[
					{ 	xtype: 'panel', frame: true, title: 'Daily purchases for current month', items: [monthlyPurchaseSummary] },
					{ 	xtype: 'panel', frame: true, title: 'Monthly purchases for current year', items: [yearlyPurchaseSummary]  }
				]
			}
		]
	});
    
	tabPanel.render('chart');
});
</script>

<body>
	<jsp:include page="../menu/menu.jsp" />

	<hr class="soften">
	<div class="container">
		<div id="chart">
		</div>
	</div>
</body>

</html>