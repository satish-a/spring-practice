package org.sab.invsys.persistence.repo.order.purchase;

import org.sab.invsys.persistence.model.order.purchase.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>,
		QueryDslPredicateExecutor<PurchaseOrder> {

	public PurchaseOrder findByOrderId(String orderId);
}
