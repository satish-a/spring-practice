package org.sab.invsys.common.util.mapper.order.sales;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sab.invsys.persistence.model.order.sales.SalesOrder;
import org.sab.invsys.persistence.model.order.sales.SalesOrderCharges;
import org.sab.invsys.persistence.model.order.sales.SalesOrderItems;
import org.sab.invsys.persistence.model.order.sales.SalesOrderTaxes;
import org.sab.invsys.persistence.model.user.User;
import org.sab.invsys.web.model.order.sales.SalesOrderUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class SalesOrderMapper {
	public SalesOrderUI toUIBean(SalesOrder data) {
		SalesOrderUI ui = new SalesOrderUI();

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

	public List<SalesOrderUI> toUIBean(List<SalesOrder> data) {
		List<SalesOrderUI> ui = new ArrayList<SalesOrderUI>();

		for (SalesOrder po : data) {
			ui.add(toUIBean(po));
		}

		return ui;
	}

	public Page<SalesOrderUI> toUIBean(Page<SalesOrder> data, Pageable pageable) {
		return new PageImpl<SalesOrderUI>(toUIBean(data.getContent()));
	}

	public SalesOrder toPersistenceBean(SalesOrderUI ui) {
		SalesOrder data = new SalesOrder();

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
				SalesOrderChargesMapper chargeMapper = new SalesOrderChargesMapper();
				Set<SalesOrderCharges> charges = new HashSet<SalesOrderCharges>(
						chargeMapper.toPersistenceBean(ui.getCharges()));
				data.setCharges(charges);
			}
			if (ui.getSoldItems() != null && ui.getSoldItems().size() > 0) {
				SalesOrderItemsMapper itemsMapper = new SalesOrderItemsMapper();
				List<SalesOrderItems> pItems = itemsMapper.toPersistenceBean(ui
						.getSoldItems());
				Set<SalesOrderItems> items = new HashSet<SalesOrderItems>();
				if (pItems != null) {
					items.addAll(pItems);
				}
				data.setItems(items);
			}
			if (ui.getTaxes() != null && ui.getTaxes().size() > 0) {
				SalesOrderTaxesMapper taxMapper = new SalesOrderTaxesMapper();
				Set<SalesOrderTaxes> taxes = new HashSet<SalesOrderTaxes>(
						taxMapper.toPersistenceBean(ui.getTaxes()));
				data.setTaxes(taxes);
			}
		}

		return data;
	}

	public List<SalesOrder> toPersistenceBean(List<SalesOrderUI> ui) {
		List<SalesOrder> data = new ArrayList<SalesOrder>();

		for (SalesOrderUI po : ui) {
			data.add(toPersistenceBean(po));
		}

		return data;
	}
}
