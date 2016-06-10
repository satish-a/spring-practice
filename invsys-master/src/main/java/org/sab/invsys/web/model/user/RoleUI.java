package org.sab.invsys.web.model.user;

public class RoleUI {

	private Long id;

	private Integer role;

	public RoleUI() {
	}

	public RoleUI(Integer role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}
