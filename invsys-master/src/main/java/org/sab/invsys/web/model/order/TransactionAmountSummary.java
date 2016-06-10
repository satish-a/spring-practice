package org.sab.invsys.web.model.order;

public class TransactionAmountSummary {
	private String day;
	private long amount;

	public TransactionAmountSummary() {

	}

	public TransactionAmountSummary(int day, long amount) {
		this.day = Integer.toString(day);
		this.amount = amount;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
