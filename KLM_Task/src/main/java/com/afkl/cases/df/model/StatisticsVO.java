package com.afkl.cases.df.model;

public class StatisticsVO implements java.io.Serializable{
	private static final long serialVersionUID = -7789406927875847546L;
	private int totalRequestCount;
	private int status2xxRequestCount;
	private int status4xxRequestCount;
	private int status5xxRequestCount;

	public int getTotalRequestCount() {
		return totalRequestCount;
	}

	public void setTotalRequestCount(int totalRequestCount) {
		this.totalRequestCount = totalRequestCount;
	}

	public int getStatus2xxRequestCount() {
		return status2xxRequestCount;
	}

	public void setStatus2xxRequestCount(int status2xxRequestCount) {
		this.status2xxRequestCount = status2xxRequestCount;
	}

	public int getStatus4xxRequestCount() {
		return status4xxRequestCount;
	}

	public void setStatus4xxRequestCount(int status4xxRequestCount) {
		this.status4xxRequestCount = status4xxRequestCount;
	}

	public int getStatus5xxRequestCount() {
		return status5xxRequestCount;
	}

	public void setStatus5xxRequestCount(int status5xxRequestCount) {
		this.status5xxRequestCount = status5xxRequestCount;
	}

}
