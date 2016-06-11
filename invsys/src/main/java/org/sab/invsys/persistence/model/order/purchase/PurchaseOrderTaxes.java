package org.sab.invsys.persistence.model.order.purchase;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "purchaseOrderTaxes")
public class PurchaseOrderTaxes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String taxName;
	private int amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="orderId")
	private PurchaseOrder po;

	public PurchaseOrderTaxes(Long id, String taxName, int amount,
			PurchaseOrder po) {
		super();
		this.id = id;
		this.taxName = taxName;
		this.amount = amount;
		this.po = po;
	}

	public PurchaseOrderTaxes() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public PurchaseOrder getPo() {
		return po;
	}

	public void setPo(PurchaseOrder po) {
		this.po = po;
	}

	@Override
	public String toString() {
		return "PurchaseOrderTaxes [id=" + id + ", taxName=" + taxName
				+ ", amount=" + amount + ", po=" + po + "]";
	}

}
