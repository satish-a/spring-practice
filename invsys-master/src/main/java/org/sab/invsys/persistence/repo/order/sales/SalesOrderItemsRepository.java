package org.sab.invsys.persistence.repo.order.sales;

import java.util.List;

import org.sab.invsys.persistence.model.order.sales.SalesOrder;
import org.sab.invsys.persistence.model.order.sales.SalesOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface SalesOrderItemsRepository extends
		JpaRepository<SalesOrderItems, Long>,
		QueryDslPredicateExecutor<SalesOrderItems> {

	public List<SalesOrderItems> findBySo(SalesOrder so);
}
