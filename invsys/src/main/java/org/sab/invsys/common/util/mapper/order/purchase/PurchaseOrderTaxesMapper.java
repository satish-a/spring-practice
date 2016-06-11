package org.sab.invsys.common.util.mapper.order.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.sab.invsys.persistence.model.order.purchase.PurchaseOrderTaxes;
import org.sab.invsys.web.model.order.purchase.PurchaseOrderTaxesUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PurchaseOrderTaxesMapper {

	public PurchaseOrderTaxesUI toUIBean(PurchaseOrderTaxes data) {
		PurchaseOrderTaxesUI ui = new PurchaseOrderTaxesUI();

		ui.setAmount(data.getAmount());
		ui.setId(data.getId());
		ui.setTaxName(data.getTaxName());

		return ui;
	}

	public List<PurchaseOrderTaxesUI> toUIBean(List<PurchaseOrderTaxes> data) {
		List<PurchaseOrderTaxesUI> ui = new ArrayList<PurchaseOrderTaxesUI>();

		for (PurchaseOrderTaxes tax : data) {
			ui.add(toUIBean(tax));
		}

		return ui;
	}

	public List<PurchaseOrderTaxesUI> toUIBean(Set<PurchaseOrderTaxes> data) {
		List<PurchaseOrderTaxesUI> ui = new ArrayList<PurchaseOrderTaxesUI>();

		for (PurchaseOrderTaxes tax : data) {
			ui.add(toUIBean(tax));
		}

		return ui;
	}

	public Page<PurchaseOrderTaxesUI> toUIBean(Page<PurchaseOrderTaxes> data,
			Pageable pageable) {
		return new PageImpl<PurchaseOrderTaxesUI>(toUIBean(data.getContent()));
	}

	public PurchaseOrderTaxes toPersistenceBean(PurchaseOrderTaxesUI ui) {
		PurchaseOrderTaxes data = new PurchaseOrderTaxes();

		data.setAmount(ui.getAmount());
		data.setId(ui.getId());
		data.setTaxName(ui.getTaxName());

		return data;
	}

	public List<PurchaseOrderTaxes> toPersistenceBean(
			List<PurchaseOrderTaxesUI> ui) {
		List<PurchaseOrderTaxes> data = new ArrayList<PurchaseOrderTaxes>();

		for (PurchaseOrderTaxesUI tax : ui) {
			data.add(toPersistenceBean(tax));
		}

		return data;
	}

}
