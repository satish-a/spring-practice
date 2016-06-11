package org.sab.invsys.web.model.user;

import java.util.Date;

public class UserUI {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	private String accountType;
	private String billingAddress;
	private String mobilePhone;
	private String officePhone;
	private String email;
	private String description;
	private String notes;
	private long balance;

	private Date createdDate;
	private Date modifiedDate;
	private int createadBy;
	private int modifiedBy;
	private Date lastLoggedIn;

	private RoleUI role;

	public UserUI() {
	}

	public UserUI(Long id, String firstName, String lastName,
			String username, String password, String accountType,
			String billingAddress, String mobilePhone, String officePhone,
			String email, String description, String notes, Date createdDate,
			Date modifiedDate, int createadBy, int modifiedBy,
			Date lastLoggedIn, RoleUI role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.billingAddress = billingAddress;
		this.mobilePhone = mobilePhone;
		this.officePhone = officePhone;
		this.email = email;
		this.description = description;
		this.notes = notes;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createadBy = createadBy;
		this.modifiedBy = modifiedBy;
		this.lastLoggedIn = lastLoggedIn;
		this.role = role;
	}

	public UserUI(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleUI getRole() {
		return role;
	}

	public void setRole(RoleUI role) {
		this.role = role;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getCreateadBy() {
		return createadBy;
	}

	public void setCreateadBy(int createadBy) {
		this.createadBy = createadBy;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}

	public void setLastLoggedIn(Date lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String toString()
	{
		StringBuffer result = new StringBuffer();
		
		result.append("id:" + id);
		result.append(" firstName:" + firstName);
		result.append(" lastName:" + lastName);
		result.append(" username:" + username);
		result.append(" password:" + password);
		result.append(" accountType:" + accountType);
		result.append(" billingAddress:" + billingAddress);
		result.append(" mobilePhone:" + mobilePhone);
		result.append(" officePhone:" + officePhone);
		result.append(" email:" + email);
		result.append(" description:" + description);
		result.append(" notes:" + notes);
		result.append(" createdDate:" + createdDate);
		result.append(" modifiedDate:" + modifiedDate);
		result.append(" createadBy:" + createadBy);
		result.append(" modifiedBy:" + modifiedBy);
		result.append(" lastLoggedIn:" + lastLoggedIn);
		
		return result.toString();
	}
}
