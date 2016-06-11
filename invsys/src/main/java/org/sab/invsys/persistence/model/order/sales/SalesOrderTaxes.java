package org.sab.invsys.persistence.model.order.sales;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "salesOrderTaxes")
public class SalesOrderTaxes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String taxName;
	private int amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId")
	private SalesOrder so;

	public SalesOrderTaxes(Long id, String taxName, int amount, SalesOrder po) {
		super();
		this.id = id;
		this.taxName = taxName;
		this.amount = amount;
		this.so = po;
	}

	public SalesOrderTaxes() {

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

	public SalesOrder getSo() {
		return so;
	}

	public void setSo(SalesOrder so) {
		this.so = so;
	}

	@Override
	public String toString() {
		return "PurchaseOrderTaxes [id=" + id + ", taxName=" + taxName
				+ ", amount=" + amount + ", po=" + so + "]";
	}
}
