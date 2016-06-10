package org.sab.invsys.common.util.extjs;

public class FilterRequest {

	private String property;
	private String value;

	public FilterRequest(String property, String value) {
		super();
		this.property = property;
		this.value = value;
	}

	public FilterRequest() {

	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return "Property: " + property + " -- Value: " + value;
	}
}
