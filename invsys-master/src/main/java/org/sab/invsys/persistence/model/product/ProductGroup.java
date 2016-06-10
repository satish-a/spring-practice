package org.sab.invsys.persistence.model.product;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "productGroup")
public class ProductGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String groupName;
	private String description;

	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	private int createadBy;
	private int modifiedBy;

	@OneToMany(mappedBy = "group" , fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Product> product;
	
	public static enum COLUMNS {
		GROUPNAME, DESCRIPTION, CREATEDBY, CREATEDDATE, MODIFIEDBY, MODIFIEDDATE
	}

	public ProductGroup(Long id, String groupName, String description,
			Date createdDate, Date modifiedDate, int createadBy, int modifiedBy) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createadBy = createadBy;
		this.modifiedBy = modifiedBy;
	}

	public ProductGroup(String groupName, String description) {
		super();
		this.groupName = groupName;
		this.description = description;
	}

	public ProductGroup() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductGroup [id=" + id + ", groupName=" + groupName
				+ ", description=" + description + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate
				+ ", createadBy=" + createadBy + ", modifiedBy=" + modifiedBy
				+ "]";
	}
}
