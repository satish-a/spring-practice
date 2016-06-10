package org.sab.invsys.persistence.model.order.purchase;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.sab.invsys.persistence.model.product.Product;

@Entity(name = "purchaseOrderItems")
public class PurchaseOrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long quantity;
	private int unitPrice;
	private int discount;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="orderId")
	private PurchaseOrder po;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="productId")
	private Product product;

	public PurchaseOrderItems(Long id, Long quantity, int unitPrice,
			int discount, PurchaseOrder po, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.po = po;
		this.product = product;
	}

	public PurchaseOrderItems() {

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

	public PurchaseOrder getPo() {
		return po;
	}

	public void setPo(PurchaseOrder po) {
		this.po = po;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "PurchaseOrderItems [id=" + id + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", discount=" + discount
				+ ", po=" + po + ", product=" + product + "]";
	}

}
