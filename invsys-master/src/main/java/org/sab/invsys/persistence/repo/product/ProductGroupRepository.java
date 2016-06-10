package org.sab.invsys.persistence.repo.product;

import org.sab.invsys.persistence.model.product.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ProductGroupRepository extends
		JpaRepository<ProductGroup, Long>,
		QueryDslPredicateExecutor<ProductGroup> {
	ProductGroup findByGroupName(String groupName);
}
