package com.barMenu.barMenu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ADDONS")
public class Addons {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="AddonId")
	private long addonId;
	
	@NotNull
	@Column(name="PackageName")
	private String addonName;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "MainItemId")
	private MainItem mainItem;

	public Addons() {	}

	public long getAddonId() {
		return addonId;
	}
	
	public void setAddonId(long addonId) {
		this.addonId = addonId;
	}


	public String getAddonName() {
		return addonName;
	}

	public void setAddonName(String addonName) {
		this.addonName = addonName;
	}

	public MainItem getMainItem() {
		return mainItem;
	}

	public void setMainItem(MainItem mainItem) {
		this.mainItem = mainItem;
	}

	@Override
	public String toString() {
		return "Addons [addonId=" + addonId + ", addonName=" + addonName + "]";
	}

	
}
