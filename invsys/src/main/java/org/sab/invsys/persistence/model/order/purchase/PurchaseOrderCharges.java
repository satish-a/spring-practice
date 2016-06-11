package org.sab.invsys.persistence.model.order.purchase;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "purchaseOrderCharges")
public class PurchaseOrderCharges {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String chargeName;
	private int amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="orderId")
	private PurchaseOrder po;

	public PurchaseOrderCharges(Long id, String chargeName, int amount,
			PurchaseOrder po) {
		super();
		this.id = id;
		this.chargeName = chargeName;
		this.amount = amount;
		this.po = po;
	}

	public PurchaseOrderCharges() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
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
		return "PurchaseOrderCharges [id=" + id + ", chargeName=" + chargeName
				+ ", amount=" + amount + ", po=" + po + "]";
	}
}
