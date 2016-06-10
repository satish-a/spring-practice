package org.sab.invsys.web.model.order.sales;

public class SalesOrderChargesUI {
	private Long id;

	private String chargeName;
	private int amount;

	public SalesOrderChargesUI(Long id, String chargeName, int amount) {
		super();
		this.id = id;
		this.chargeName = chargeName;
		this.amount = amount;
	}

	public SalesOrderChargesUI() {

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

	@Override
	public String toString() {
		return "SalesOrderChargesUI [id=" + id + ", chargeName="
				+ chargeName + ", amount=" + amount + "]";
	}
}
