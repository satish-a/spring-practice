package org.sab.invsys.service.order.sales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.sab.invsys.common.util.extjs.FilterRequest;
import org.sab.invsys.common.util.mapper.order.sales.SalesOrderMapper;
import org.sab.invsys.common.util.mapper.order.sales.SalesOrderItemsMapper;
import org.sab.invsys.persistence.model.order.sales.SalesOrder;
import org.sab.invsys.persistence.model.order.sales.SalesOrderItems;
import org.sab.invsys.persistence.model.product.Product;
import org.sab.invsys.persistence.model.user.User;
import org.sab.invsys.persistence.repo.order.sales.SalesOrderItemsRepository;
import org.sab.invsys.persistence.repo.order.sales.SalesOrderRepository;
import org.sab.invsys.persistence.repo.product.ProductRepository;
import org.sab.invsys.persistence.repo.user.UserRepository;
import org.sab.invsys.service.product.ProductService;
import org.sab.invsys.web.model.order.TransactionAmountSummary;
import org.sab.invsys.web.model.order.sales.SalesOrderItemsUI;
import org.sab.invsys.web.model.order.sales.SalesOrderUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;

@Service
public class SalesOrderService {

	@Autowired
	private SalesOrderRepository soRepository;
	@Autowired
	private SalesOrderItemsRepository itemsRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@PersistenceContext
	private EntityManager entityManager;

	private SalesOrderMapper soMapper = new SalesOrderMapper();
	private SalesOrderItemsMapper itemsMapper = new SalesOrderItemsMapper();

	private Logger logger = Logger.getLogger(ProductService.class);

	@Transactional
	public SalesOrderUI create(SalesOrderUI uiBean) {
		SalesOrder so = soMapper.toPersistenceBean(uiBean);
		Set<SalesOrderItems> items = so.getItems();
		so.setItems(null);

		long total = 0;
		if (items != null && items.size() > 0) {
			for (SalesOrderItems item : items) {
				total += item.getQuantity() * item.getUnitPrice() * ( 100 - item.getDiscount() ) / 100 ;
			}
		}
		so.setTotal(total);
		
		User user = userRepository.findByUsername(uiBean.getUserName());
		so.setUser(user);

		SalesOrder saved = soRepository.save(so);
		if (items != null && items.size() > 0) {
			for (SalesOrderItems item : items) {
				logger.debug("Item :: " + item);
				Product product = productRepository.findByProductName(item
						.getProduct().getProductName());
				item.setProduct(product);
				SalesOrder order = soRepository.findByOrderId(uiBean
						.getOrderId());
				item.setSo(order);
				logger.debug("Item :: " + item);
			}
			itemsRepository.save(items);
		}
		logger.debug("Created Account : " + saved);

		return soMapper.toUIBean(saved);
	}

	public SalesOrderUI find(SalesOrderUI uiBean) {
		return soMapper
				.toUIBean(soRepository.findByOrderId(uiBean.getOrderId()));
	}

	public List<SalesOrderUI> findAll() {
		return soMapper.toUIBean(soRepository.findAll());
	}

	public Page<SalesOrderUI> findAll(Pageable pageable,
			List<FilterRequest> filters) {
		Predicate predicate = toPredicate(filters);
		return soMapper.toUIBean(soRepository.findAll(predicate, pageable),
				pageable);
	}

	public SalesOrderUI findByOrderId(String orderId) {
		return soMapper.toUIBean(soRepository.findByOrderId(orderId));
	}

	public List<SalesOrderItemsUI> findItemsByOrderId(int orderId) {
		return itemsMapper.toUIBean(itemsRepository.findBySo(new SalesOrder(
				new Long(orderId))));
	}

	@Transactional
	public SalesOrderUI update(SalesOrderUI uiBean) {
		SalesOrder so = soMapper.toPersistenceBean(uiBean);
		Set<SalesOrderItems> items = so.getItems();

		SalesOrder existing = soRepository.findByOrderId(uiBean.getOrderId());
		if (existing == null) {
			return null;
		}

		existing.setComments(so.getComments());
		existing.setDiscount(so.getDiscount());
		existing.setDueDate(so.getDueDate());
		existing.setOrderDate(so.getOrderDate());
		existing.setReferenceNumber(so.getReferenceNumber());
		existing.setTotal(so.getTotal());

		User user = userRepository.findByUsername(uiBean.getUserName());
		existing.setUser(user);
		existing.setItems(null);

		long total = 0;
		SalesOrder saved = soRepository.save(existing);
		if (items != null && items.size() > 0) {
			List<SalesOrderItems> existingItems = itemsRepository
					.findBySo(saved);

			Map<String, SalesOrderItems> SalesdItemsMap = new HashMap<String, SalesOrderItems>();
			for (SalesOrderItems pim : items) {
				SalesdItemsMap.put(pim.getProduct().getProductName(), pim);
			}
			Map<String, SalesOrderItems> existingItemsMap = new HashMap<String, SalesOrderItems>();
			for (SalesOrderItems i : existingItems) {
				existingItemsMap.put(i.getProduct().getProductName(), i);
			}

			/* Delete Sales Order Items */
			List<SalesOrderItems> deleteItems = new ArrayList<SalesOrderItems>();
			for (SalesOrderItems deleteItem : existingItems) {
				if (!SalesdItemsMap.containsKey(deleteItem.getProduct()
						.getProductName())) {
					deleteItems.add(deleteItem);
				}
			}
			itemsRepository.delete(deleteItems);

			/* Updating/Inserting Sales Order Items */
			List<SalesOrderItems> toBeSavedItems = new ArrayList<SalesOrderItems>();
			for (SalesOrderItems item : items) {
				SalesOrderItems existingItem = existingItemsMap.get(item
						.getProduct().getProductName());
				Product product = productRepository.findByProductName(item
						.getProduct().getProductName());
				SalesOrder order = soRepository.findByOrderId(uiBean
						.getOrderId());
				if (existingItem != null) {
					existingItem.setQuantity(item.getQuantity());
					existingItem.setUnitPrice(item.getUnitPrice());
					existingItem.setDiscount(item.getDiscount());
					existingItem.setTotal(item.getTotal());
					toBeSavedItems.add(existingItem);
					item = existingItem;
				} else {
					item.setProduct(product);
					item.setSo(order);
					toBeSavedItems.add(item);
				}
				total += item.getQuantity() * item.getUnitPrice() * ( 100 - item.getDiscount() ) / 100 ;
			}
			saved.setTotal(total);
			itemsRepository.save(toBeSavedItems);

		}
		logger.debug("update Sales order : " + saved);

		return soMapper.toUIBean(saved);
	}

	public Boolean delete(SalesOrderUI uiBean) {
		return true;
	}

	private Predicate toPredicate(final List<FilterRequest> filters) {
		return null;
	}

	public List<TransactionAmountSummary> monthlySales(int month, int year) {
		
		String query = "select new org.sab.invsys.web.model.order.TransactionAmountSummary(DAYOFMONTH(orderDate), sum(total)) " 
				+ " from salesOrder where month(orderDate) = " + month + " and " + " year(orderDate) = " + year 
				+ " group by orderDate";
		
		return entityManager.createQuery(query).getResultList();
	}
	
	public List<TransactionAmountSummary> yearlySales(int year) {

		String query = "select new org.sab.invsys.web.model.order.TransactionAmountSummary(month(orderDate), sum(total)) " 
				+ " from salesOrder where year(orderDate) = " + year  
				+ " group by month(orderDate)";
		
		return entityManager.createQuery(query).getResultList();
	}
	
	public List<TransactionAmountSummary> allYearSales() {
		
		String query = "select new org.sab.invsys.web.model.order.TransactionAmountSummary(year(orderDate), sum(total)) " +
				" from salesOrder " +
				" group by year(orderDate)";
		
		return entityManager.createQuery(query).getResultList();
	}
}
