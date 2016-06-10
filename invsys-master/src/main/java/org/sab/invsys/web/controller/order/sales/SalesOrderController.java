package org.sab.invsys.web.controller.order.sales;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sab.invsys.common.util.extjs.FilterRequest;
import org.sab.invsys.common.util.extjs.ResponseMap;
import org.sab.invsys.common.util.json.SalesOrderItemJson;
import org.sab.invsys.service.order.sales.SalesOrderService;
import org.sab.invsys.web.controller.product.ProductController;
import org.sab.invsys.web.model.order.TransactionAmountSummary;
import org.sab.invsys.web.model.order.sales.SalesOrderItemsUI;
import org.sab.invsys.web.model.order.sales.SalesOrderUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/saleorder")
public class SalesOrderController {
	@Autowired
	private SalesOrderService service;

	@Autowired
	private ResponseMap<SalesOrderUI> response;
	@Autowired
	private ResponseMap<SalesOrderItemsUI> itemsResponse;
	@Autowired
	private ResponseMap<TransactionAmountSummary> salesResponse;

	SalesOrderItemJson itemsJson = new SalesOrderItemJson();

	private Logger logger = Logger.getLogger(ProductController.class);

	@RequestMapping
	public String startPage() {
		return "saleorder/summary";
	}

	@RequestMapping(value = "/orderList")
	public String listPage() {
		return "saleorder/list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody
	Map<String, ? extends Object> list(@RequestParam int page,
			@RequestParam int start, @RequestParam int limit,
			@RequestParam(required = false) Object filter) throws Exception {
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			Page<SalesOrderUI> saleOrders = null;

			List<FilterRequest> filters = new ArrayList<FilterRequest>();

			if (filter != null) {
				filters.addAll(SalesOrderItemJson
						.getListFromJsonArray((String) filter));
			}

			saleOrders = service.findAll(pageable, filters);
			long total = saleOrders.getTotalElements();
			logger.debug("Sale orders : " + saleOrders.getContent());

			return response.mapOK(saleOrders.getContent(), total);
		} catch (Exception e) {
			logger.error(e);
			return response.mapError("Error retrieving Saless from database.");
		}
	}

	@RequestMapping(value = "/view/{orderId}")
	public String getSalesOrder(@PathVariable String orderId, Model model)
			throws Exception {
		SalesOrderUI saleOrder = service.findByOrderId(orderId);

		model.addAttribute("soBean", saleOrder);
		model.addAttribute("operationName", "update");

		return "saleorder/view";
	}

	@RequestMapping(value = "/view/{orderId}/itemList")
	public @ResponseBody
	Map<String, ? extends Object> getOrderItemslist(
			@PathVariable Integer orderId) throws Exception {
		try {
			List<SalesOrderItemsUI> soldItems = new ArrayList<SalesOrderItemsUI>();
			if (orderId != null && !"".equals(Integer.toString(orderId))) {
				soldItems = service.findItemsByOrderId(orderId);
			}
			return itemsResponse.mapOK(soldItems);
		} catch (Exception e) {
			logger.error(e);
			return response.mapError("Error retrieving Saless from database.");
		}
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	Map<String, ? extends Object> update(@RequestParam String orderId,
			@RequestParam String userName,
			@RequestParam String referenceNumber,
			@RequestParam String orderType, @RequestParam Date orderDate,
			@RequestParam Date dueDate, @RequestParam String comments,
			@RequestParam Object items) throws Exception {
		try {
			SalesOrderUI order = new SalesOrderUI();
			order.setOrderId(orderId);
			order.setUserName(userName);
			order.setReferenceNumber(referenceNumber);
			order.setOrderType(orderType);
			order.setOrderDate(orderDate);
			order.setDueDate(dueDate);
			order.setComments(comments);
			List<SalesOrderItemsUI> soldItems = itemsJson
					.getListFromJSON((String) items);
			order.setSoldItems(soldItems);
			logger.debug("Sold Items :: " + soldItems);
			logger.debug("Sales Order :: " + order);

			SalesOrderUI saleOrder = service.update(order);

			if (saleOrder == null) {
				return response.mapError("Sales Order Id doesn't exists.");
			} else {
				return response.mapOK(saleOrder,
						"Sales Order Updated Succesfully");
			}
		} catch (Exception e) {
			logger.error(e);
			return response.mapError("Error trying to update Sales Order.");
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addSalesOrder(Model model) throws Exception {
		return "saleorder/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> add(@RequestParam String orderId,
			@RequestParam String userName,
			@RequestParam String referenceNumber,
			@RequestParam String orderType, @RequestParam Date orderDate,
			@RequestParam Date dueDate, @RequestParam String comments,
			@RequestParam Object items) {
		try {
			SalesOrderUI order = new SalesOrderUI();
			order.setOrderId(orderId);
			order.setUserName(userName);
			order.setReferenceNumber(referenceNumber);
			order.setOrderType(orderType);
			order.setOrderDate(orderDate);
			order.setDueDate(dueDate);
			order.setComments(comments);
			List<SalesOrderItemsUI> soldItems = itemsJson
					.getListFromJSON((String) items);
			order.setSoldItems(soldItems);
			logger.debug("Sold Items :: " + soldItems);
			logger.debug("Sales Order :: " + order);

			if (service.findByOrderId(order.getOrderId()).getOrderId() != null) {
				return response.mapError("Order Id already exists.");
			}
			SalesOrderUI saved = service.create(order);
			logger.debug("Sales Order Created Successfully :: " + saved);
			return response.mapOK(saved, "Sales Order Created Succesfully");
		} catch (Exception e) {
			logger.error("Error trying to create Sales order.");
			return response.mapError("Error trying to create Sales order.");
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> delete(
			@RequestParam(value = "sales") String orderIds) {
		try {

			List<SalesOrderUI> deletedSaleOrders = new ArrayList<SalesOrderUI>();

			for (String orderId : orderIds.substring(1, orderIds.length() - 1)
					.replaceAll("\"", "").split(",")) {
				SalesOrderUI product = service.findByOrderId(orderId);

				if (product != null) {
					service.delete(product);
					deletedSaleOrders.add(product);
				}
			}

			return response.mapOK(deletedSaleOrders);
		} catch (Exception e) {
			e.printStackTrace();
			return response.mapError("Error trying to delete Sales orders.");
		}
	}

	@RequestMapping(value = "/monthlySummary", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, ? extends Object> monthSalesSummary(
			@RequestParam(required = false) String month,
			@RequestParam(required = false) String year, Object filter) {
		try {
			int curMonth = month != null && Integer.parseInt(month) > 0 ? Integer
					.parseInt(month) : Calendar.getInstance().get(
					Calendar.MONTH);
			int curYear = year != null && Integer.parseInt(year) > 0 ? Integer
					.parseInt(year) : Calendar.getInstance().get(Calendar.YEAR);

			List<TransactionAmountSummary> monthlySales = service.monthlySales(
					curMonth + 1, curYear);
			return salesResponse.mapOK(monthlySales);
		} catch (Exception e) {
			e.printStackTrace();
			return response
					.mapError("Error trying to fetch monthly sales summary.");
		}
	}

	@RequestMapping(value = "/yealySummary", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, ? extends Object> yearlySalesSummary(
			@RequestParam(required = false) String year, Object filter) {
		try {
			int curYear = year != null && Integer.parseInt(year) > 0 ? Integer
					.parseInt(year) : Calendar.getInstance().get(Calendar.YEAR);
			List<TransactionAmountSummary> yearlySales = service
					.yearlySales(curYear);

			for (TransactionAmountSummary ss : yearlySales) {
				ss.setDay(new DateFormatSymbols().getMonths()[Integer
						.parseInt(ss.getDay()) - 1]);
			}

			return salesResponse.mapOK(yearlySales);
		} catch (Exception e) {
			e.printStackTrace();
			return response
					.mapError("Error trying to fetch monthly sales summary.");
		}
	}

	@RequestMapping(value = "/allSummary", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, ? extends Object> allSalesSummary(Object filter) {
		try {
			return salesResponse.mapOK(service.allYearSales());
		} catch (Exception e) {
			e.printStackTrace();
			return response
					.mapError("Error trying to fetch monthly sales summary.");
		}
	}
}
