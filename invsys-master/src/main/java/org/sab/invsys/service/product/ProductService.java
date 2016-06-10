package org.sab.invsys.service.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.sab.invsys.common.util.extjs.FilterRequest;
import org.sab.invsys.common.util.mapper.product.ProductMapper;
import org.sab.invsys.persistence.model.product.Product;
import org.sab.invsys.persistence.model.product.ProductGroup;
import org.sab.invsys.persistence.model.product.QProduct;
import org.sab.invsys.persistence.model.product.Product.COLUMNS;
import org.sab.invsys.persistence.repo.product.ProductGroupRepository;
import org.sab.invsys.persistence.repo.product.ProductRepository;
import org.sab.invsys.web.model.product.ProductUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductGroupRepository groupRepository;
	
	private Logger logger = Logger.getLogger(ProductService.class);

	private ProductMapper mapper = new ProductMapper();

	@Transactional
	public ProductUI create(ProductUI uiBean) {

		Product newProduct = mapper.toPersistenceBean(uiBean);
		
		if(uiBean.getProductGroup() != null)
		{
			ProductGroup group = groupRepository.findByGroupName(uiBean.getProductGroup());
			newProduct.setGroup(group);
		}
		
		Product saved = repository.save(newProduct);
		logger.debug("Created Account : " + saved);

		return mapper.toUIBean(saved);
	}

	public ProductUI find(ProductUI uiBean) {
		return mapper.toUIBean(repository.findByProductName(uiBean
				.getProductName()));
	}

	public List<ProductUI> findAll() {
		return mapper.toUIBean(repository.findAll());
	}

	public Page<ProductUI> findAll(Pageable pageable,
			List<FilterRequest> filters) {

		Predicate predicate = toPredicate(filters);
		return mapper.toUIBean(repository.findAll(predicate, pageable),
				pageable);
	}

	public ProductUI findByProductname(String productName) {
		return mapper.toUIBean(repository.findByProductName(productName));
	}

	public ProductUI update(ProductUI uiBean) {
		Product existing = repository
				.findByProductName(uiBean.getProductName());

		if (existing == null) {
			return null;
		}
		
		if (! existing.getGroup().getGroupName().equals(uiBean.getProductGroup()))
		{
			ProductGroup group = groupRepository.findByGroupName(uiBean.getProductGroup()) ;
			existing.setGroup(group);
		}

		existing.setDescription(uiBean.getDescription());
		existing.setPrice(uiBean.getPrice());

		Product saved = null;

		try {
			saved = repository.save(existing);
		} catch (Exception e) {
			logger.error(e);
		}

		return mapper.toUIBean(saved);
	}

	public Boolean delete(ProductUI uiBean) {
		Product existing = repository
				.findByProductName(uiBean.getProductName());

		if (existing == null) {
			return false;
		}

		repository.delete(existing);
		return true;
	}

	private Predicate toPredicate(final List<FilterRequest> filters) {
		logger.info("Entering predicates :: " + filters);

		QProduct product = QProduct.product;
		BooleanExpression result = null;

		try {
			for (FilterRequest filter : filters) {

				COLUMNS column = COLUMNS.valueOf(filter.getProperty()
						.toUpperCase());
				BooleanExpression expression = null;

				switch (column) {
				case PRODUCTNAME:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = product.productName.like("%"
								+ filter.getValue() + "%");
					}
					break;
				case DESCRIPTION:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = product.description.like("%"
								+ filter.getValue() + "%");
					}
					break;
				case PRODUCTGROUP:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = product.group.groupName.like("%"
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
