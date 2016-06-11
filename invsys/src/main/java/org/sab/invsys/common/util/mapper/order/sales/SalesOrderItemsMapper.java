package org.sab.invsys.common.util.mapper.order.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.sab.invsys.persistence.model.order.sales.SalesOrderItems;
import org.sab.invsys.persistence.model.product.Product;
import org.sab.invsys.web.model.order.sales.SalesOrderItemsUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class SalesOrderItemsMapper {
	Logger logger = Logger.getLogger(SalesOrderItemsMapper.class);

	public SalesOrderItemsUI toUIBean(SalesOrderItems data) {
		SalesOrderItemsUI ui = new SalesOrderItemsUI();

		ui.setDiscount(data.getDiscount());
		ui.setId(data.getId());
		ui.setProductName(data.getProduct().getProductName());
		ui.setQuantity(data.getQuantity());
		ui.setUnitPrice(data.getUnitPrice());
		ui.setTotal(data.getTotal());

		return ui;
	}

	public List<SalesOrderItemsUI> toUIBean(List<SalesOrderItems> data) {
		List<SalesOrderItemsUI> ui = new ArrayList<SalesOrderItemsUI>();
		logger.debug(data);

		for (SalesOrderItems item : data) {
			ui.add(toUIBean(item));
		}

		return ui;
	}

	public List<SalesOrderItemsUI> toUIBean(Set<SalesOrderItems> data) {
		List<SalesOrderItemsUI> ui = new ArrayList<SalesOrderItemsUI>();
		logger.debug(data);

		for (SalesOrderItems item : data) {
			ui.add(toUIBean(item));
		}

		return ui;
	}

	public Page<SalesOrderItemsUI> toUIBean(Page<SalesOrderItems> data,
			Pageable pageable) {
		return new PageImpl<SalesOrderItemsUI>(toUIBean(data.getContent()));
	}

	public SalesOrderItems toPersistenceBean(SalesOrderItemsUI ui) {
		SalesOrderItems data = new SalesOrderItems();

		data.setDiscount(ui.getDiscount());
		data.setId(ui.getId());
		data.setQuantity(ui.getQuantity());
		data.setUnitPrice(ui.getUnitPrice());
		data.setTotal(ui.getTotal());

		Product product = new Product();
		product.setProductName(ui.getProductName());
		data.setProduct(product);

		return data;
	}

	public List<SalesOrderItems> toPersistenceBean(List<SalesOrderItemsUI> ui) {
		List<SalesOrderItems> data = new ArrayList<SalesOrderItems>();
		logger.debug(ui);

		if (ui != null) {
			for (SalesOrderItemsUI item : ui) {
				data.add(toPersistenceBean(item));
			}
		}

		return data;
	}
}
