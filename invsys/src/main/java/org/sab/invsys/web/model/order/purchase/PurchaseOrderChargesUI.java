package org.sab.invsys.web.model.order.purchase;

public class PurchaseOrderChargesUI {
	private Long id;

	private String chargeName;
	private int amount;

	public PurchaseOrderChargesUI(Long id, String chargeName, int amount) {
		super();
		this.id = id;
		this.chargeName = chargeName;
		this.amount = amount;
	}

	public PurchaseOrderChargesUI() {

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
		return "PurchaseOrderChargesUI [id=" + id + ", chargeName="
				+ chargeName + ", amount=" + amount + "]";
	}
}
