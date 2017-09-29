package com.barMenu.barMenu.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barMenu.barMenu.entity.MainItem;
import com.barMenu.barMenu.repository.MainItemRepository;

@Service
public class MainItemService {

	private MainItemRepository mainItemRepo;
	@Autowired
	public MainItemService(MainItemRepository mainItemRepo) {
		this.mainItemRepo = mainItemRepo;
	}
	
	public MainItem addNewItem(MainItem mainItem){		
		
		List<MainItem> mainItems = mainItemRepo.findAll();
		for(MainItem item : mainItems){
			if(item.getItemName().equalsIgnoreCase(mainItem.getItemName()) || item.getItemCode().equals(mainItem.getItemCode())){
				throw new EntityExistsException("Item Already Exist : Details can't be saved");
			}
		}
		if((mainItem.getItemCode()=="") || (mainItem.getItemName()=="")) {
			throw new NullPointerException(" Can't save the detail. Plz fill out every feild !!!");
		}
		
		mainItemRepo.save(mainItem);
		
		return mainItem;
	}
	
	public List<MainItem> findAllItem(){
		
		List<MainItem> allItems = mainItemRepo.findAll();
		return allItems;
		
	}
	
	public MainItem findByMainItemId(long id){
			
			MainItem mainItem = mainItemRepo.findOne(id);
			return mainItem;
	}
	
	public MainItem deleteItem(long id){
		
		MainItem mainItem = mainItemRepo.findOne(id);
		mainItemRepo.delete(id);
		return mainItem;
	}
}
