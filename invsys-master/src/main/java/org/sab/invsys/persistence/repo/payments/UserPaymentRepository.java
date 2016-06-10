package org.sab.invsys.persistence.repo.payments;

import java.util.List;

import org.sab.invsys.persistence.model.payments.UserPayment;
import org.sab.invsys.persistence.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface UserPaymentRepository extends
		JpaRepository<UserPayment, Long>,
		QueryDslPredicateExecutor<UserPayment> {

	List<UserPayment> findByUser(User user);

	UserPayment findById(Long id);

}
