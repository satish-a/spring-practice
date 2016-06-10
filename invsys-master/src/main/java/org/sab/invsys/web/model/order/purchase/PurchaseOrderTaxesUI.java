package org.sab.invsys.web.model.order.purchase;


public class PurchaseOrderTaxesUI {
	private Long id;

	private String taxName;
	private int amount;

	public PurchaseOrderTaxesUI(Long id, String taxName, int amount) {
		super();
		this.id = id;
		this.taxName = taxName;
		this.amount = amount;
	}

	public PurchaseOrderTaxesUI() {

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

	@Override
	public String toString() {
		return "PurchaseOrderTaxesUI [id=" + id + ", taxName=" + taxName
				+ ", amount=" + amount + "]";
	}
}
