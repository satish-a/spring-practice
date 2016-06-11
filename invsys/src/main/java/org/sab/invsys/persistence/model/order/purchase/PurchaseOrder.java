package org.sab.invsys.persistence.model.order.purchase;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.sab.invsys.persistence.model.user.User;

@Entity(name = "purchaseOrder")
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String orderId;
	private String orderType;
	private String comments;
	private String referenceNumber;
	private int discount;
	private long total;
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	private int createadBy;
	private int modifiedBy;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="accountId")
	private User user;

	@OneToMany(mappedBy = "po", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
	private Set<PurchaseOrderItems> items;

	@OneToMany(mappedBy = "po", fetch = FetchType.LAZY)
	private Set<PurchaseOrderCharges> charges;

	@OneToMany(mappedBy = "po", fetch = FetchType.LAZY)
	private Set<PurchaseOrderTaxes> taxes;

	public PurchaseOrder(Long id, String orderId, String orderType,
			String comments, String referenceNumber, int discount,
			Date orderDate, Date dueDate, Date createdDate, Date modifiedDate,
			int createadBy, int modifiedBy, User user,
			Set<PurchaseOrderItems> items, Set<PurchaseOrderCharges> charges,
			Set<PurchaseOrderTaxes> taxes) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.orderType = orderType;
		this.comments = comments;
		this.referenceNumber = referenceNumber;
		this.discount = discount;
		this.orderDate = orderDate;
		this.dueDate = dueDate;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createadBy = createadBy;
		this.modifiedBy = modifiedBy;
		this.user = user;
		this.items = items;
		this.charges = charges;
		this.taxes = taxes;
	}

	public PurchaseOrder(Long id) {
		this.id = id;
	}

	public PurchaseOrder() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getCreateadBy() {
		return createadBy;
	}

	public void setCreateadBy(int createadBy) {
		this.createadBy = createadBy;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<PurchaseOrderItems> getItems() {
		return items;
	}

	public void setItems(Set<PurchaseOrderItems> items) {
		this.items = items;
	}

	public Set<PurchaseOrderCharges> getCharges() {
		return charges;
	}

	public void setCharges(Set<PurchaseOrderCharges> charges) {
		this.charges = charges;
	}

	public Set<PurchaseOrderTaxes> getTaxes() {
		return taxes;
	}

	public void setTaxes(Set<PurchaseOrderTaxes> taxes) {
		this.taxes = taxes;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", orderId=" + orderId
				+ ", orderType=" + orderType + ", comments=" + comments
				+ ", referenceNumber=" + referenceNumber + ", discount="
				+ discount + ", orderDate=" + orderDate + ", dueDate="
				+ dueDate + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", createadBy=" + createadBy + ", modifiedBy="
				+ modifiedBy + /*", user=" + user + ", items=" + items
				+ ", charges=" + charges + ", taxes=" + taxes +*/ "]";
	}
}
