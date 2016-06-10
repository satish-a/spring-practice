package org.sab.invsys.persistence.model.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.sab.invsys.persistence.model.order.purchase.PurchaseOrder;
import org.sab.invsys.persistence.model.payments.UserPayment;

@Entity(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String username;
	private String password;
	private String firstName;
	private String lastName;

	private String accountType;
	private String billingAddress;
	private String mobilePhone;
	private String officePhone;
	private String email;
	private String description;
	private String notes;

	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	private int createadBy;
	private int modifiedBy;
	@Temporal(TemporalType.DATE)
	private Date lastLoggedIn;

	@OneToOne(mappedBy = "user", cascade = { CascadeType.ALL })
	private Role role;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Set<PurchaseOrder> purchaseOrder;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<UserPayment> userPayments;

	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
	private UserBalance balance;

	public static enum COLUMNS {
		USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ACCOUNTTYPE, BILLINGADDRESS, MOBILEPHONE, OFFICEPHONE, EMAIL, DESCRIPTION, NOTES, CREATEDBY, CREATEDDATE, MODIFIEDBY, MODIFIEDDATE, LASTLOGGEDIN
	}

	public User(Long id, String username, String password, String firstName,
			String lastName, String accountType, String billingAddress,
			String mobilePhone, String officePhone, String email,
			String description, String notes, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountType = accountType;
		this.billingAddress = billingAddress;
		this.mobilePhone = mobilePhone;
		this.officePhone = officePhone;
		this.email = email;
		this.description = description;
		this.notes = notes;
		this.role = role;
	}

	public User() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public Set<PurchaseOrder> getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(Set<PurchaseOrder> purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Set<UserPayment> getUserPayments() {
		return userPayments;
	}

	public void setUserPayments(Set<UserPayment> userPayments) {
		this.userPayments = userPayments;
	}

	public UserBalance getBalance() {
		return balance;
	}

	public void setBalance(UserBalance balance) {
		this.balance = balance;
	}

	public String toString() {
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
