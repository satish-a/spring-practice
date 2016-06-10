package org.sab.invsys.web.model.order.sales;

import java.util.Date;
import java.util.List;

public class SalesOrderUI {
	private Long id;
	private String orderId;
	private String orderType;
	private String comments;
	private String referenceNumber;
	private int discount;
	private long total;
	private Date orderDate;
	private Date dueDate;
	private Date createdDate;
	private Date modifiedDate;
	private int createadBy;
	private int modifiedBy;
	private String userName;
	private List<SalesOrderItemsUI> soldItems;
	private List<SalesOrderChargesUI> charges;
	private List<SalesOrderTaxesUI> taxes;

	public SalesOrderUI(Long id, String orderId, String orderType,
			String comments, String referenceNumber, int discount,
			Date orderDate, Date dueDate, Date createdDate, Date modifiedDate,
			int createadBy, int modifiedBy, String userName,
			List<SalesOrderItemsUI> items,
			List<SalesOrderChargesUI> charges,
			List<SalesOrderTaxesUI> taxes) {
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
		this.userName = userName;
		this.soldItems = items;
		this.charges = charges;
		this.taxes = taxes;
	}

	public SalesOrderUI() {

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<SalesOrderChargesUI> getCharges() {
		return charges;
	}

	public void setCharges(List<SalesOrderChargesUI> charges) {
		this.charges = charges;
	}

	public List<SalesOrderTaxesUI> getTaxes() {
		return taxes;
	}

	public void setTaxes(List<SalesOrderTaxesUI> taxes) {
		this.taxes = taxes;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<SalesOrderItemsUI> getSoldItems() {
		return soldItems;
	}

	public void setSoldItems(List<SalesOrderItemsUI> soldItems) {
		this.soldItems = soldItems;
	}

	@Override
	public String toString() {
		return "SalesOrderUI [id=" + id + ", orderId=" + orderId
				+ ", orderType=" + orderType + ", comments=" + comments
				+ ", referenceNumber=" + referenceNumber + ", discount="
				+ discount + ", orderDate=" + orderDate + ", dueDate="
				+ dueDate + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", createadBy=" + createadBy + ", modifiedBy="
				+ modifiedBy + ", userName=" + userName + ", items=" + soldItems
				+ ", charges=" + charges + ", taxes=" + taxes + "]";
	}
}
