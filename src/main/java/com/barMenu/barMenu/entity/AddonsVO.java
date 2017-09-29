package com.barMenu.barMenu.entity;

public class AddonsVO {
	
	private long mainItemId;				//auto generated id of MainItem domain;
	private String mainItemName;
	private String addonName;
	private long addonId;
	public long getMainItemId() {
		return mainItemId;
	}
	public void setMainItemId(long mainItemId) {
		this.mainItemId = mainItemId;
	}
	public String getMainItemName() {
		return mainItemName;
	}
	public void setMainItemName(String mainItemName) {
		this.mainItemName = mainItemName;
	}
	public String getAddonName() {
		return addonName;
	}
	public void setAddonName(String addonName) {
		this.addonName = addonName;
	}
	public long getAddonId() {
		return addonId;
	}
	public void setAddonId(long addonId) {
		this.addonId = addonId;
	}

}
