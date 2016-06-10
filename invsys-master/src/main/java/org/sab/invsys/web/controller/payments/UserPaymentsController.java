package org.sab.invsys.web.controller.payments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sab.invsys.common.util.extjs.FilterRequest;
import org.sab.invsys.common.util.extjs.JsonUtils;
import org.sab.invsys.common.util.extjs.ResponseMap;
import org.sab.invsys.common.util.mapper.payment.UserPaymentMapper;
import org.sab.invsys.service.payments.UserPaymentService;
import org.sab.invsys.web.model.payments.UserPaymentUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
@RequestMapping("/payments")
public class UserPaymentsController {

	@Autowired
	private UserPaymentService service;

	@Autowired
	private ResponseMap<UserPaymentUI> extJS;
	
	private UserPaymentMapper mapper = new UserPaymentMapper();

	private Logger logger = Logger.getLogger(UserPaymentsController.class);

	@RequestMapping
	public String getPaymentsPage() {
		return "payments/payments";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody
	Map<String, ? extends Object> list(@RequestParam int page,
			@RequestParam int start, @RequestParam int limit,
			@RequestParam(required = false) Object filter) throws Exception {
		try {
			// @ TODO Add filter logic
			Pageable pageable = new PageRequest(page - 1, limit);
			Page<UserPaymentUI> payments = null;

			List<FilterRequest> filters = new ArrayList<FilterRequest>();

			if (filter != null) {
				logger.debug("Processing Filters!");
				filters.addAll(JsonUtils.getListFromJsonArray((String) filter));
			}

			payments = new PageImpl<UserPaymentUI>(service.findAll());
			long total = payments.getTotalElements();
			logger.debug("products : " + payments.getContent());

			return extJS.mapOK(payments.getContent(), total);
		} catch (Exception e) {
			logger.error(e);
			return extJS.mapError("Error retrieving Payments from database.");
		}
	}

	@RequestMapping(value = "/view/{paymentId}")
	public String getCustomerView(@PathVariable String paymentId, Model model)
			throws Exception {
		UserPaymentUI payment = service.find(new Long(paymentId)
				.longValue());

		model.addAttribute("paymentBean", payment);
		model.addAttribute("operationName", "update");

		return "payments/payment";
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	Map<String, ? extends Object> update(UserPaymentUI data)
			throws Exception {
		try {
			UserPaymentUI payment = service.update(data);

			if (payment != null) {
				return extJS.mapOK(payment, "Payment Updated Succesfully");
			} else {
				return extJS.mapError("Error trying to update payment.");
			}
		} catch (Exception e) {
			logger.error(e);
			return extJS.mapError("Error trying to update payment.");
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct(Model model) throws Exception {
		UserPaymentUI payment = new UserPaymentUI();
		model.addAttribute("paymentBean", payment);
		model.addAttribute("operationName", "add");

		return "payments/payment";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> add(UserPaymentUI data) {
		try {
			if (data.getId() != null) {
				return extJS.mapError("Payment Id already exists");
			}

			UserPaymentUI saved = service.create(data);
			if (saved != null) {
				return extJS.mapOK(saved, "Payment Created Succesfully");
			} else {
				return extJS.mapError("Error trying to create Payment.");
			}
		} catch (Exception e) {
			return extJS.mapError("Error trying to create Payment.");
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> delete(
			@RequestParam(value = "payments") String name) {
		try {

			List<UserPaymentUI> deletedPayments = new ArrayList<UserPaymentUI>();

			for (String paymentId : name.substring(1, name.length() - 1)
					.replaceAll("\"", "").split(",")) {
				UserPaymentUI payment = service.find(new Long(paymentId)
						.longValue());

				if (payment != null) {
					service.delete(payment);
					deletedPayments.add(payment);
				}
			}

			return extJS.mapOK(deletedPayments);
		} catch (Exception e) {
			e.printStackTrace();
			return extJS.mapError("Error trying to delete payment.");
		}
	}

}
