package org.sab.invsys.web.model.product;

import java.util.Date;

public class ProductGroupUI {

	private Long id;
	private String groupName;
	private String description;
	private Date createdDate;
	private Date modifiedDate;
	private int createadBy;
	private int modifiedBy;

	public ProductGroupUI(Long id, String groupName, String description,
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

	public ProductGroupUI() {

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

	@Override
	public String toString() {
		return "ProductGroupUI [id=" + id + ", groupName=" + groupName
				+ ", description=" + description + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate
				+ ", createadBy=" + createadBy + ", modifiedBy=" + modifiedBy
				+ "]";
	}

}
