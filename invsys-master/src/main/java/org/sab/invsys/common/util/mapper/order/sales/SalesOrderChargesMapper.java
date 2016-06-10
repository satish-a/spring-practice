package org.sab.invsys.common.util.mapper.order.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.sab.invsys.persistence.model.order.sales.SalesOrderCharges;
import org.sab.invsys.web.model.order.sales.SalesOrderChargesUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class SalesOrderChargesMapper {
	public SalesOrderChargesUI toUIBean(SalesOrderCharges data) {
		SalesOrderChargesUI ui = new SalesOrderChargesUI();

		ui.setAmount(data.getAmount());
		ui.setChargeName(data.getChargeName());
		ui.setId(data.getId());

		return ui;
	}

	public List<SalesOrderChargesUI> toUIBean(List<SalesOrderCharges> data) {
		List<SalesOrderChargesUI> ui = new ArrayList<SalesOrderChargesUI>();

		for (SalesOrderCharges charge : data) {
			ui.add(toUIBean(charge));
		}

		return ui;
	}

	public List<SalesOrderChargesUI> toUIBean(Set<SalesOrderCharges> data) {
		List<SalesOrderChargesUI> ui = new ArrayList<SalesOrderChargesUI>();

		for (SalesOrderCharges charge : data) {
			ui.add(toUIBean(charge));
		}

		return ui;
	}

	public Page<SalesOrderChargesUI> toUIBean(Page<SalesOrderCharges> data,
			Pageable pageable) {
		return new PageImpl<SalesOrderChargesUI>(toUIBean(data.getContent()));
	}

	public SalesOrderCharges toPersistenceBean(SalesOrderChargesUI ui) {
		SalesOrderCharges data = new SalesOrderCharges();

		data.setAmount(ui.getAmount());
		data.setId(ui.getId());
		data.setChargeName(ui.getChargeName());

		return data;
	}

	public List<SalesOrderCharges> toPersistenceBean(
			List<SalesOrderChargesUI> ui) {
		List<SalesOrderCharges> data = new ArrayList<SalesOrderCharges>();

		for (SalesOrderChargesUI tax : ui) {
			data.add(toPersistenceBean(tax));
		}

		return data;
	}
}
