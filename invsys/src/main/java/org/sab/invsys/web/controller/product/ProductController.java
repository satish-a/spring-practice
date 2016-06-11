package org.sab.invsys.web.controller.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sab.invsys.common.util.extjs.JsonUtils;
import org.sab.invsys.common.util.extjs.ResponseMap;
import org.sab.invsys.common.util.extjs.FilterRequest;
import org.sab.invsys.service.product.ProductService;
import org.sab.invsys.web.model.product.ProductUI;
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
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private ResponseMap<ProductUI> extJS;

	private Logger logger = Logger.getLogger(ProductController.class);

	@RequestMapping
	public String startPage() {
		return "product/list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody
	Map<String, ? extends Object> list(@RequestParam int page,
			@RequestParam int start, @RequestParam int limit,
			@RequestParam(required = false) Object filter) throws Exception {
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			Page<ProductUI> products = null;

			List<FilterRequest> filters = new ArrayList<FilterRequest>();

			if (filter != null) {
				filters.addAll(JsonUtils.getListFromJsonArray((String) filter));
			}

			products = service.findAll(pageable, filters);
			long total = products.getTotalElements();
			logger.debug("products : " + products.getContent());

			return extJS.mapOK(products.getContent(), total);
		} catch (Exception e) {
			logger.error(e);
			return extJS.mapError("Error retrieving Products from database.");
		}
	}

	@RequestMapping(value = "/view/{productName}")
	public String view(@PathVariable String productName, Model model)
			throws Exception {
		ProductUI product = service.findByProductname(productName);
		model.addAttribute("productBean", product);

		return "product/view";
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	Map<String, ? extends Object> update(ProductUI data) throws Exception {
		try {
			ProductUI product = service.update(data);

			if (product != null) {
				return extJS.mapOK(product, "Product Updated Succesfully");
			} else {
				return extJS.mapError("Error trying to update product.");
			}
		} catch (Exception e) {
			logger.error(e);
			return extJS.mapError("Error trying to update product.");
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct() throws Exception {
		return "product/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> add(ProductUI data) {
		try {
			if (service.findByProductname(data.getProductName()) != null) {
				return extJS.mapError("Product Name already exists");
			}

			ProductUI saved = service.create(data);
			if (saved != null) {
				return extJS.mapOK(saved, "Product Created Succesfully");
			} else {
				return extJS.mapError("Error trying to create product.");
			}
		} catch (Exception e) {
			return extJS.mapError("Error trying to create product.");
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> delete(
			@RequestParam(value = "products") String name) {
		try {

			List<ProductUI> deletedUsers = new ArrayList<ProductUI>();

			for (String productName : name.substring(1, name.length() - 1)
					.replaceAll("\"", "").split(",")) {
				ProductUI product = service.findByProductname(productName);

				if (product != null) {
					service.delete(product);
					deletedUsers.add(product);
				}
			}

			return extJS.mapOK(deletedUsers);
		} catch (Exception e) {
			e.printStackTrace();
			return extJS.mapError("Error trying to update product.");
		}
	}
}
