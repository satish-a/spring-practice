package org.sab.invsys.common.util.mapper.order.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.sab.invsys.persistence.model.order.sales.SalesOrderTaxes;
import org.sab.invsys.web.model.order.sales.SalesOrderTaxesUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class SalesOrderTaxesMapper {

	public SalesOrderTaxesUI toUIBean(SalesOrderTaxes data) {
		SalesOrderTaxesUI ui = new SalesOrderTaxesUI();

		ui.setAmount(data.getAmount());
		ui.setId(data.getId());
		ui.setTaxName(data.getTaxName());

		return ui;
	}

	public List<SalesOrderTaxesUI> toUIBean(List<SalesOrderTaxes> data) {
		List<SalesOrderTaxesUI> ui = new ArrayList<SalesOrderTaxesUI>();

		for (SalesOrderTaxes tax : data) {
			ui.add(toUIBean(tax));
		}

		return ui;
	}

	public List<SalesOrderTaxesUI> toUIBean(Set<SalesOrderTaxes> data) {
		List<SalesOrderTaxesUI> ui = new ArrayList<SalesOrderTaxesUI>();

		for (SalesOrderTaxes tax : data) {
			ui.add(toUIBean(tax));
		}

		return ui;
	}

	public Page<SalesOrderTaxesUI> toUIBean(Page<SalesOrderTaxes> data,
			Pageable pageable) {
		return new PageImpl<SalesOrderTaxesUI>(toUIBean(data.getContent()));
	}

	public SalesOrderTaxes toPersistenceBean(SalesOrderTaxesUI ui) {
		SalesOrderTaxes data = new SalesOrderTaxes();

		data.setAmount(ui.getAmount());
		data.setId(ui.getId());
		data.setTaxName(ui.getTaxName());

		return data;
	}

	public List<SalesOrderTaxes> toPersistenceBean(List<SalesOrderTaxesUI> ui) {
		List<SalesOrderTaxes> data = new ArrayList<SalesOrderTaxes>();

		for (SalesOrderTaxesUI tax : ui) {
			data.add(toPersistenceBean(tax));
		}

		return data;
	}

}
