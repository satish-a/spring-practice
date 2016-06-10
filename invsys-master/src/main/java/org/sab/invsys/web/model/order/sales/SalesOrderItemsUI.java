package org.sab.invsys.web.model.order.sales;

public class SalesOrderItemsUI {
	private Long id;

	private Long quantity;
	private int unitPrice;
	private int discount;
	private int total;
	private String productName;

	public SalesOrderItemsUI(Long id, Long quantity, int unitPrice,
			int discount, String productName) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.productName = productName;
	}

	public SalesOrderItemsUI() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "SalesOrderItemsUI [id=" + id + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", discount=" + discount
				+ ", productName=" + productName + "]";
	}
}
