package org.sab.invsys.common.util.mapper.product;

import java.util.ArrayList;
import java.util.List;

import org.sab.invsys.persistence.model.product.ProductGroup;
import org.sab.invsys.web.model.product.ProductGroupUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ProductGroupMapper {
	public ProductGroupUI toUIBean(ProductGroup group) {
		ProductGroupUI ui = null;

		if (group != null) {
			ui = new ProductGroupUI();
			ui.setCreateadBy(group.getCreateadBy());
			ui.setCreatedDate(group.getCreatedDate());
			ui.setDescription(group.getDescription());
			ui.setId(group.getId());
			ui.setModifiedBy(group.getModifiedBy());
			ui.setModifiedDate(group.getModifiedDate());
			ui.setGroupName(group.getGroupName());
		}

		return ui;
	}

	public List<ProductGroupUI> toUIBean(List<ProductGroup> groups) {
		List<ProductGroupUI> ui = new ArrayList<ProductGroupUI>();

		for (ProductGroup group : groups) {
			ui.add(toUIBean(group));
		}
		return ui;
	}

	public Page<ProductGroupUI> toUIBean(Page<ProductGroup> groups,
			Pageable pageable) {
		Page<ProductGroupUI> ui = new PageImpl<ProductGroupUI>(
				toUIBean(groups.getContent()), pageable,
				groups.getTotalElements());

		return ui;
	}

	public ProductGroup toPersistenceBean(ProductGroupUI ui) {
		ProductGroup group = null;

		if (ui != null) {
			group = new ProductGroup();

			group.setCreateadBy(ui.getCreateadBy());
			group.setCreatedDate(ui.getCreatedDate());
			group.setDescription(ui.getDescription());
			group.setId(ui.getId());
			group.setModifiedBy(ui.getModifiedBy());
			group.setModifiedDate(ui.getModifiedDate());
			group.setGroupName(ui.getGroupName());

		}
		return group;
	}

	public List<ProductGroup> toPersistenceBean(List<ProductGroupUI> uiBeans) {
		List<ProductGroup> groups = new ArrayList<ProductGroup>();

		for (ProductGroupUI uiBean : uiBeans) {
			groups.add(toPersistenceBean(uiBean));
		}
		return groups;
	}

}
