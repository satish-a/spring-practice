package org.sab.invsys.web.model.payments;

import java.util.Date;

public class UserPaymentUI {
	private Long id;
	private String notes;
	private String paymentType;
	private int amount;
	private Date paymentDate;
	private String userName;

	public UserPaymentUI(Long id, String notes, String paymentType, int amount,
			Date paymentDate, String userName) {
		super();
		this.id = id;
		this.notes = notes;
		this.paymentType = paymentType;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.userName = userName;
	}

	public UserPaymentUI() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserPayment [id=" + id + ", notes=" + notes + ", paymentType="
				+ paymentType + ", amount=" + amount + ", paymentDate="
				+ paymentDate + ", userName=" + userName + "]";
	}

}
