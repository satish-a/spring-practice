<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<jsp:include page="../menu/header.jsp" />
<jsp:include page="../menu/includescripts.jsp" />

<body>
	<jsp:include page="../menu/menu.jsp" />

	<hr class="soften">
	<div class="container">
		<div id="chart">
		</div>
	</div>
</body>

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
		xtype: 'chart', store: yearlySummaryStore, animate: true, height: 600, width: 750,
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
		xtype: 'chart', store: monthlySummaryStore, animate: true, height: 600, width: 750,
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

	var allSummaryStore = Ext.create('Ext.data.Store', 
	{
		model : 'SalesSum', autoLoad : true, remoteFilter : 'true', remoteSort : 'true',
		proxy : 
		{ 
			type : 'ajax', api : { read : '<c:url value="/saleorder/allSummary"/>' },
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

	var allSummary = Ext.create('Ext.chart.Chart', 
	{
		xtype: 'chart', store: allSummaryStore, animate: true, height: 600, width: 750,
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
			
	var tabPanel = Ext.create('Ext.tab.Panel', 
	{
		height: 550, activeTab: 0, deferredRender: false, layoutOnTabChange: true, flex: 1, border: false,
		defaults:{hideMode: 'offsets', layout: 'fit'},
		items: 
		[
			{ title:'Daily Sales', items: [monthlySummary] },
			{ title:'Monthly Sales', items: [yearlySummary] },
			{ title:'Yearly Sales', items: [allSummary] }
		]
	});
    
	tabPanel.render('chart');
});
</script>

</html>