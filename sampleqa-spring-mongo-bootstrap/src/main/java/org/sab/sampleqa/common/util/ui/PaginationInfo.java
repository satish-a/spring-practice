package org.sab.sampleqa.common.util.ui;

import java.util.Map;

public class PaginationInfo {
	private int begin;
	private long end;
	private int previous;
	private int next;

	private String nextPage;
	private String previousPage;
	private String firstPage;
	private String lastPage;

	private final String PAGE = "page";
	private final String SIZE = "pageSize";

	public PaginationInfo() {

	}

	public PaginationInfo(int current, long totalRecords, int pageSize,
			String listAction) {
		super();
		this.previous = current - 1 > 1 ? current - 1 : 1;
		this.next = current * pageSize < totalRecords ? current + 1 : current;
		this.begin = 1;
		this.end = totalRecords % pageSize == 0 ? totalRecords / pageSize
				: totalRecords / pageSize + 1;

		this.nextPage = listAction + "?" + PAGE + "=" + next + "&" + SIZE + "="
				+ pageSize;
		this.previousPage = listAction + "?" + PAGE + "=" + previous + "&"
				+ SIZE + "=" + pageSize;
		this.firstPage = listAction + "?" + PAGE + "=" + begin + "&" + SIZE
				+ "=" + pageSize;
		this.lastPage = listAction + "?" + PAGE + "=" + end + "&" + SIZE + "="
				+ pageSize;
	}

	public PaginationInfo(int current, long totalRecords, int pageSize,
			String listAction, Map<String, String> listActionParams) {
		this(current, totalRecords, pageSize, listAction);

		if (listActionParams != null) {
			for (String key : listActionParams.keySet()) {
				this.nextPage += "&" + key + "=" + listActionParams.get(key);
				this.previousPage += "&" + key + "="
						+ listActionParams.get(key);
				this.firstPage += "&" + key + "=" + listActionParams.get(key);
				this.lastPage += "&" + key + "=" + listActionParams.get(key);
			}
		}
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

	public String getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public String getLastPage() {
		return lastPage;
	}

	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}

	@Override
	public String toString() {
		return "PaginationInfo [begin=" + begin + ", end=" + end
				+ ", previous=" + previous + ", next=" + next + ", nextPage="
				+ nextPage + ", previousPage=" + previousPage + ", firstPage="
				+ firstPage + ", lastPage=" + lastPage + ", PAGE=" + PAGE
				+ ", SIZE=" + SIZE + "]";
	}
}
