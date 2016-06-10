package org.sab.invsys.service.payments;

import java.util.List;

import org.apache.log4j.Logger;
import org.sab.invsys.common.util.mapper.payment.UserPaymentMapper;
import org.sab.invsys.persistence.model.payments.UserPayment;
import org.sab.invsys.persistence.model.user.User;
import org.sab.invsys.persistence.repo.payments.UserPaymentRepository;
import org.sab.invsys.persistence.repo.user.UserRepository;
import org.sab.invsys.web.model.payments.UserPaymentUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPaymentService {

	@Autowired
	private UserPaymentRepository repository;

	@Autowired
	private UserRepository userRepository;

	private Logger logger = Logger.getLogger(UserPaymentService.class);

	private UserPaymentMapper mapper = new UserPaymentMapper();

	@Transactional
	public UserPaymentUI create(UserPaymentUI uiBean) {

		UserPayment newUserPayment = mapper.toPersistenceBean(uiBean);

		if (uiBean.getUserName() == null) {
			return null;
		}

		User user = userRepository.findByUsername(uiBean.getUserName());
		newUserPayment.setUser(user);

		UserPayment saved = repository.save(newUserPayment);
		logger.debug("Added Payment : " + saved);

		return mapper.toUIBean(saved);
	}

	public UserPaymentUI find(long paymentId) {
		return mapper.toUIBean(repository.findById(paymentId));
	}

	public List<UserPaymentUI> findAll() {
		return mapper.toUIBean(repository.findAll());
	}

	public List<UserPaymentUI> findByUser(String userName) {
		User user = new User();
		user.setUsername(userName);
		return mapper.toUIBean(repository.findByUser(user));
	}

	public UserPaymentUI update(UserPaymentUI uiBean) {
		UserPayment existing = repository.findById(uiBean.getId());

		if (existing == null) {
			return null;
		}

		existing.setAmount(uiBean.getAmount());
		existing.setNotes(uiBean.getNotes());

		UserPayment saved = null;

		try {
			saved = repository.save(existing);
		} catch (Exception e) {
			logger.error(e);
		}

		return mapper.toUIBean(saved);
	}

	public Boolean delete(UserPaymentUI uiBean) {
		UserPayment existing = repository.findById(uiBean.getId());

		if (existing == null) {
			return false;
		}

		repository.delete(existing);
		return true;
	}
}
