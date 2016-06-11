package org.sab.invsys.web.controller.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sab.invsys.common.util.extjs.JsonUtils;
import org.sab.invsys.common.util.extjs.ResponseMap;
import org.sab.invsys.common.util.extjs.FilterRequest;
import org.sab.invsys.service.product.ProductGroupService;
import org.sab.invsys.web.model.product.ProductGroupUI;
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
@RequestMapping("/productgroup")
public class ProductGroupController {
	@Autowired
	private ProductGroupService service;

	@Autowired
	private ResponseMap<ProductGroupUI> extJS;

	private Logger logger = Logger.getLogger(ProductController.class);

	@RequestMapping
	public String startPage() {
		return "productgroup/list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody
	Map<String, ? extends Object> list(@RequestParam int page,
			@RequestParam int start, @RequestParam int limit,
			@RequestParam(required = false) Object filter) throws Exception {
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			Page<ProductGroupUI> groups = null;

			List<FilterRequest> filters = new ArrayList<FilterRequest>();

			if (filter != null) {
				filters.addAll(JsonUtils.getListFromJsonArray((String) filter));
			}

			groups = service.findAll(pageable, filters);
			long total = groups.getTotalElements();
			logger.debug("products : " + groups.getContent());

			return extJS.mapOK(groups.getContent(), total);
		} catch (Exception e) {
			logger.error(e);
			return extJS
					.mapError("Error retrieving Product Groups from database.");
		}
	}

	@RequestMapping(value = "/view/{groupName}")
	public String view(@PathVariable String groupName, Model model)
			throws Exception {
		ProductGroupUI group = service.findByGroupName(groupName);

		model.addAttribute("productGroupBean", group);
		model.addAttribute("operationName", "update");

		return "productgroup/view";
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	Map<String, ? extends Object> update(ProductGroupUI data)
			throws Exception {
		try {
			ProductGroupUI group = service.update(data);

			if (group != null) {
				return extJS.mapOK(group, "Product Group Updated Succesfully");
			} else {
				return extJS.mapError("Error trying to update product Group.");
			}
		} catch (Exception e) {
			logger.error(e);
			return extJS.mapError("Error trying to update product Group.");
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() throws Exception {
		return "productgroup/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> add(ProductGroupUI data) {
		try {
			if (service.findByGroupName(data.getGroupName()) != null) {
				return extJS.mapError("Product Group Name already exists");
			}

			ProductGroupUI saved = service.create(data);
			if (saved != null) {
				return extJS.mapOK(saved, "Product Group Created Succesfully");
			} else {
				return extJS.mapError("Error trying to create product Group.");
			}
		} catch (Exception e) {
			return extJS.mapError("Error trying to create product Group.");
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> delete(
			@RequestParam(value = "groups") String name) {
		try {

			List<ProductGroupUI> deletedGroups = new ArrayList<ProductGroupUI>();

			for (String groupName : name.substring(1, name.length() - 1)
					.replaceAll("\"", "").split(",")) {
				ProductGroupUI product = service.findByGroupName(groupName);

				if (product != null) {
					service.delete(product);
					deletedGroups.add(product);
				}
			}

			return extJS.mapOK(deletedGroups);
		} catch (Exception e) {
			e.printStackTrace();
			return extJS.mapError("Error trying to update product Group.");
		}
	}
}
