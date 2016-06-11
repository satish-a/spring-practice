package org.sab.invsys.persistence.model.product;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String productName;
	private String description;
	private int price;

	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	private int createadBy;
	private int modifiedBy;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="productGroup")
	private ProductGroup group;
	
	public static enum COLUMNS {
		PRODUCTNAME, DESCRIPTION, PRODUCTGROUP, PRICE, CREATEDBY, CREATEDDATE, MODIFIEDBY, MODIFIEDDATE
	}

	public Product(Long id, String productName, String description, int price,
			Date createdDate, Date modifiedDate, int createadBy,
			int modifiedBy, ProductGroup group) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createadBy = createadBy;
		this.modifiedBy = modifiedBy;
		this.group = group;
	}

	public Product(Long id, String productName, String description, int price,
			Date createdDate, Date modifiedDate, int createadBy, int modifiedBy) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createadBy = createadBy;
		this.modifiedBy = modifiedBy;
	}

	public Product() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public ProductGroup getGroup() {
		return group;
	}

	public void setGroup(ProductGroup group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName
				+ ", description=" + description + ", price=" + price
				+ ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", createadBy=" + createadBy + ", modifiedBy="
				+ modifiedBy + "]";
	}

}
