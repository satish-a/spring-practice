package org.sab.invsys.persistence.model.order.sales;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.sab.invsys.persistence.model.product.Product;

@Entity(name = "salesOrderItems")
public class SalesOrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long quantity;
	private int unitPrice;
	private int discount;
	private int total;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId")
	private SalesOrder so;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId")
	private Product product;

	public SalesOrderItems(Long id, Long quantity, int unitPrice, int discount,
			SalesOrder po, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.so = po;
		this.product = product;
	}

	public SalesOrderItems() {

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public SalesOrder getSo() {
		return so;
	}

	public void setSo(SalesOrder so) {
		this.so = so;
	}

	@Override
	public String toString() {
		return "PurchaseOrderItems [id=" + id + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", discount=" + discount
				+ ", po=" + so + ", product=" + product + "]";
	}

}
