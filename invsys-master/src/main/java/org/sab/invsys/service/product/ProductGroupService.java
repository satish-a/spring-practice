package org.sab.invsys.service.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.sab.invsys.common.util.extjs.FilterRequest;
import org.sab.invsys.common.util.mapper.product.ProductGroupMapper;
import org.sab.invsys.persistence.model.product.ProductGroup;
import org.sab.invsys.persistence.model.product.QProductGroup;
import org.sab.invsys.persistence.model.product.ProductGroup.COLUMNS;
import org.sab.invsys.persistence.repo.product.ProductGroupRepository;
import org.sab.invsys.web.model.product.ProductGroupUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
public class ProductGroupService {
	@Autowired
	private ProductGroupRepository repository;
	private Logger logger = Logger.getLogger(ProductService.class);

	private ProductGroupMapper mapper = new ProductGroupMapper();

	public ProductGroupUI create(ProductGroupUI uiBean) {

		ProductGroup newGroup = mapper.toPersistenceBean(uiBean);
		ProductGroup saved = repository.save(newGroup);
		logger.debug("Created Product Group : " + saved);

		return mapper.toUIBean(saved);
	}

	public ProductGroupUI find(ProductGroupUI uiBean) {
		return mapper
				.toUIBean(repository.findByGroupName(uiBean.getGroupName()));
	}

	public List<ProductGroupUI> findAll() {
		return mapper.toUIBean(repository.findAll());
	}

	public Page<ProductGroupUI> findAll(Pageable pageable,
			List<FilterRequest> filters) {

		Predicate predicate = toPredicate(filters);

		return mapper.toUIBean(repository.findAll(predicate, pageable),
				pageable);
	}

	public ProductGroupUI findByGroupName(String groupName) {
		return mapper.toUIBean(repository.findByGroupName(groupName));
	}

	public ProductGroupUI update(ProductGroupUI uiBean) {
		ProductGroup existing = repository.findByGroupName(uiBean
				.getGroupName());

		if (existing == null) {
			return null;
		}

		existing.setDescription(uiBean.getDescription());

		ProductGroup saved = null;

		try {
			saved = repository.save(existing);
		} catch (Exception e) {
			logger.error(e);
		}

		return mapper.toUIBean(saved);
	}

	public Boolean delete(ProductGroupUI uiBean) {
		ProductGroup existing = repository.findByGroupName(uiBean
				.getGroupName());

		if (existing == null) {
			return false;
		}

		repository.delete(existing);
		return true;
	}

	private Predicate toPredicate(final List<FilterRequest> filters) {
		logger.info("Entering predicates :: " + filters);

		QProductGroup group = QProductGroup.productGroup;
		BooleanExpression result = null;

		try {
			for (FilterRequest filter : filters) {

				COLUMNS column = COLUMNS.valueOf(filter.getProperty()
						.toUpperCase());
				BooleanExpression expression = null;

				switch (column) {
				case GROUPNAME:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = group.groupName.like("%"
								+ filter.getValue() + "%");
					}
					break;
				case DESCRIPTION:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = group.description.like("%"
								+ filter.getValue() + "%");
					}
					break;
				}
				if (expression != null) {
					if (result != null) {
						result = result.and(expression);
					} else {
						result = expression;
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		logger.info("Result Predicate :: "
				+ (result != null ? result.toString() : ""));

		logger.info("Exiting predicates");
		return result;
	}
}
