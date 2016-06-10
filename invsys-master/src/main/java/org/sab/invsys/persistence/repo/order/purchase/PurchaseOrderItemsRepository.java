package org.sab.invsys.persistence.repo.order.purchase;

import java.util.List;

import org.sab.invsys.persistence.model.order.purchase.PurchaseOrder;
import org.sab.invsys.persistence.model.order.purchase.PurchaseOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface PurchaseOrderItemsRepository extends
		JpaRepository<PurchaseOrderItems, Long>,
		QueryDslPredicateExecutor<PurchaseOrderItems> {
	
	public List<PurchaseOrderItems> findByPo(PurchaseOrder po);
}
