package org.sab.invsys.common.util.mapper.order.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.sab.invsys.persistence.model.order.purchase.PurchaseOrderItems;
import org.sab.invsys.persistence.model.product.Product;
import org.sab.invsys.web.model.order.purchase.PurchaseOrderItemsUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PurchaseOrderItemsMapper {
	Logger logger = Logger.getLogger(PurchaseOrderItemsMapper.class);

	public PurchaseOrderItemsUI toUIBean(PurchaseOrderItems data) {
		PurchaseOrderItemsUI ui = new PurchaseOrderItemsUI();

		ui.setDiscount(data.getDiscount());
		ui.setId(data.getId());
		ui.setProductName(data.getProduct().getProductName());
		ui.setQuantity(data.getQuantity());
		ui.setUnitPrice(data.getUnitPrice());

		return ui;
	}

	public List<PurchaseOrderItemsUI> toUIBean(List<PurchaseOrderItems> data) {
		List<PurchaseOrderItemsUI> ui = new ArrayList<PurchaseOrderItemsUI>();
		logger.debug(data);

		for (PurchaseOrderItems item : data) {
			ui.add(toUIBean(item));
		}

		return ui;
	}

	public List<PurchaseOrderItemsUI> toUIBean(Set<PurchaseOrderItems> data) {
		List<PurchaseOrderItemsUI> ui = new ArrayList<PurchaseOrderItemsUI>();
		logger.debug(data);

		for (PurchaseOrderItems item : data) {
			ui.add(toUIBean(item));
		}

		return ui;
	}

	public Page<PurchaseOrderItemsUI> toUIBean(Page<PurchaseOrderItems> data,
			Pageable pageable) {
		return new PageImpl<PurchaseOrderItemsUI>(toUIBean(data.getContent()));
	}

	public PurchaseOrderItems toPersistenceBean(PurchaseOrderItemsUI ui) {
		PurchaseOrderItems data = new PurchaseOrderItems();

		data.setDiscount(ui.getDiscount());
		data.setId(ui.getId());
		data.setQuantity(ui.getQuantity());
		data.setUnitPrice(ui.getUnitPrice());

		Product product = new Product();
		product.setProductName(ui.getProductName());
		data.setProduct(product);

		return data;
	}

	public List<PurchaseOrderItems> toPersistenceBean(
			List<PurchaseOrderItemsUI> ui) {
		List<PurchaseOrderItems> data = new ArrayList<PurchaseOrderItems>();
		logger.debug(ui);

		if (ui != null) {
			for (PurchaseOrderItemsUI item : ui) {
				data.add(toPersistenceBean(item));
			}
		}

		return data;
	}
}
