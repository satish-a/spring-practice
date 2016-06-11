package org.sab.invsys.common.util.mapper.order.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.sab.invsys.persistence.model.order.purchase.PurchaseOrderCharges;
import org.sab.invsys.web.model.order.purchase.PurchaseOrderChargesUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PurchaseOrderChargesMapper {
	public PurchaseOrderChargesUI toUIBean(PurchaseOrderCharges data) {
		PurchaseOrderChargesUI ui = new PurchaseOrderChargesUI();

		ui.setAmount(data.getAmount());
		ui.setChargeName(data.getChargeName());
		ui.setId(data.getId());

		return ui;
	}

	public List<PurchaseOrderChargesUI> toUIBean(List<PurchaseOrderCharges> data) {
		List<PurchaseOrderChargesUI> ui = new ArrayList<PurchaseOrderChargesUI>();

		for (PurchaseOrderCharges charge : data) {
			ui.add(toUIBean(charge));
		}

		return ui;
	}

	public List<PurchaseOrderChargesUI> toUIBean(Set<PurchaseOrderCharges> data) {
		List<PurchaseOrderChargesUI> ui = new ArrayList<PurchaseOrderChargesUI>();

		for (PurchaseOrderCharges charge : data) {
			ui.add(toUIBean(charge));
		}

		return ui;
	}

	public Page<PurchaseOrderChargesUI> toUIBean(
			Page<PurchaseOrderCharges> data, Pageable pageable) {
		return new PageImpl<PurchaseOrderChargesUI>(toUIBean(data.getContent()));
	}

	public PurchaseOrderCharges toPersistenceBean(PurchaseOrderChargesUI ui) {
		PurchaseOrderCharges data = new PurchaseOrderCharges();

		data.setAmount(ui.getAmount());
		data.setId(ui.getId());
		data.setChargeName(ui.getChargeName());

		return data;
	}

	public List<PurchaseOrderCharges> toPersistenceBean(
			List<PurchaseOrderChargesUI> ui) {
		List<PurchaseOrderCharges> data = new ArrayList<PurchaseOrderCharges>();

		for (PurchaseOrderChargesUI tax : ui) {
			data.add(toPersistenceBean(tax));
		}

		return data;
	}
}
