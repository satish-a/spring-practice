package org.sab.invsys.persistence.model.order.sales;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "salesOrderCharges")
public class SalesOrderCharges {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String chargeName;
	private int amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId")
	private SalesOrder so;

	public SalesOrderCharges(Long id, String chargeName, int amount,
			SalesOrder po) {
		super();
		this.id = id;
		this.chargeName = chargeName;
		this.amount = amount;
		this.so = po;
	}

	public SalesOrderCharges() {

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

	public SalesOrder getSo() {
		return so;
	}

	public void setSo(SalesOrder so) {
		this.so = so;
	}

	@Override
	public String toString() {
		return "PurchaseOrderCharges [id=" + id + ", chargeName=" + chargeName
				+ ", amount=" + amount + ", po=" + so + "]";
	}
}
