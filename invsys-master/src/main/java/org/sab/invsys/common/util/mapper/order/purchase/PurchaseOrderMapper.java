package org.sab.invsys.common.util.mapper.order.purchase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sab.invsys.persistence.model.order.purchase.PurchaseOrder;
import org.sab.invsys.persistence.model.order.purchase.PurchaseOrderCharges;
import org.sab.invsys.persistence.model.order.purchase.PurchaseOrderItems;
import org.sab.invsys.persistence.model.order.purchase.PurchaseOrderTaxes;
import org.sab.invsys.persistence.model.user.User;
import org.sab.invsys.web.model.order.purchase.PurchaseOrderUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PurchaseOrderMapper {
	public PurchaseOrderUI toUIBean(PurchaseOrder data) {
		PurchaseOrderUI ui = new PurchaseOrderUI();

		if (data != null) {
			ui.setComments(data.getComments());
			ui.setCreateadBy(data.getCreateadBy());
			ui.setCreatedDate(data.getCreatedDate());
			ui.setDiscount(data.getDiscount());
			ui.setDueDate(data.getDueDate());
			ui.setId(data.getId());
			ui.setModifiedBy(data.getModifiedBy());
			ui.setModifiedDate(data.getModifiedDate());
			ui.setOrderDate(data.getOrderDate());
			ui.setOrderId(data.getOrderId());
			ui.setOrderType(data.getOrderType());
			ui.setReferenceNumber(data.getReferenceNumber());
			ui.setUserName(data.getUser().getUsername());
			ui.setTotal(data.getTotal());

		}

		return ui;
	}

	public List<PurchaseOrderUI> toUIBean(List<PurchaseOrder> data) {
		List<PurchaseOrderUI> ui = new ArrayList<PurchaseOrderUI>();

		for (PurchaseOrder po : data) {
			ui.add(toUIBean(po));
		}

		return ui;
	}

	public Page<PurchaseOrderUI> toUIBean(Page<PurchaseOrder> data,
			Pageable pageable) {
		return new PageImpl<PurchaseOrderUI>(toUIBean(data.getContent()));
	}

	public PurchaseOrder toPersistenceBean(PurchaseOrderUI ui) {
		PurchaseOrder data = new PurchaseOrder();

		if (ui != null) {
			data.setComments(ui.getComments());
			data.setCreateadBy(ui.getCreateadBy());
			data.setCreatedDate(ui.getCreatedDate());
			data.setDiscount(ui.getDiscount());
			data.setDueDate(ui.getDueDate());
			data.setId(ui.getId());
			data.setModifiedBy(ui.getModifiedBy());
			data.setModifiedDate(ui.getModifiedDate());
			data.setOrderDate(ui.getOrderDate());
			data.setOrderId(ui.getOrderId());
			data.setOrderType(ui.getOrderType());
			data.setReferenceNumber(ui.getReferenceNumber());

			User user = new User();
			user.setUsername(ui.getUserName());
			data.setUser(user);

			if (ui.getCharges() != null && ui.getCharges().size() > 0) {
				PurchaseOrderChargesMapper chargeMapper = new PurchaseOrderChargesMapper();
				Set<PurchaseOrderCharges> charges = new HashSet<PurchaseOrderCharges>(
						chargeMapper.toPersistenceBean(ui.getCharges()));
				data.setCharges(charges);
			}
			if (ui.getItems() != null && ui.getItems().size() > 0) {
				PurchaseOrderItemsMapper itemsMapper = new PurchaseOrderItemsMapper();
				List<PurchaseOrderItems> pItems = itemsMapper
						.toPersistenceBean(ui.getItems());
				Set<PurchaseOrderItems> items = new HashSet<PurchaseOrderItems>();
				if (pItems != null) {
					items.addAll(pItems);
				}
				data.setItems(items);
			}
			if (ui.getTaxes() != null && ui.getTaxes().size() > 0) {
				PurchaseOrderTaxesMapper taxMapper = new PurchaseOrderTaxesMapper();
				Set<PurchaseOrderTaxes> taxes = new HashSet<PurchaseOrderTaxes>(
						taxMapper.toPersistenceBean(ui.getTaxes()));
				data.setTaxes(taxes);
			}
		}

		return data;
	}

	public List<PurchaseOrder> toPersistenceBean(List<PurchaseOrderUI> ui) {
		List<PurchaseOrder> data = new ArrayList<PurchaseOrder>();

		for (PurchaseOrderUI po : ui) {
			data.add(toPersistenceBean(po));
		}

		return data;
	}
}
