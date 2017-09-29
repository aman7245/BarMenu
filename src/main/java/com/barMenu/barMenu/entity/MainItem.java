package com.barMenu.barMenu.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "MAINITEM")
public class MainItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Id")
	private long id;
	
	@NotNull
	@Column(name="ItemCode")
	private String itemCode;
	
	@NotNull
	@Column(name="ItemName")
	private String itemName;
	
	@OneToMany(mappedBy = "mainItem", cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch=FetchType.EAGER, orphanRemoval=true)
	private Set<Addons> addons = new HashSet<>();

	public MainItem() { 	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Set<Addons> getAddons() {
		return addons;
	}

	public void setAddons(Set<Addons> addons) {
		this.addons = addons;
	}

	@Override
	public String toString() {
		return "MainItem [id=" + id + ", itemCode=" + itemCode + ", itemName=" + itemName + ", addons=" + addons + "]";
	}

}
