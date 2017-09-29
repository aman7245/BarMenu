package com.barMenu.barMenu.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barMenu.barMenu.entity.Addons;
import com.barMenu.barMenu.entity.AddonsVO;
import com.barMenu.barMenu.entity.MainItem;
import com.barMenu.barMenu.repository.AddonsRepository;
import com.barMenu.barMenu.repository.MainItemRepository;

@Service
public class AddonsService {

	private MainItemRepository mainItemRepository;
	private AddonsRepository addonsRepository;
	@Autowired
	public AddonsService(MainItemRepository mainItemRepository, AddonsRepository addonsRepository) {
		this.mainItemRepository = mainItemRepository;
		this.addonsRepository = addonsRepository;
	}
	
	public Addons addNewAddons(AddonsVO addonsvo){		
		
		MainItem mainItem = mainItemRepository.findOne(addonsvo.getMainItemId());
		Addons addon = new Addons();
		addon.setAddonName(addonsvo.getAddonName());
		Set<Addons> addons = mainItem.getAddons();
		for(Addons newaddon : addons){
			if(newaddon.getAddonName().equalsIgnoreCase(addonsvo.getAddonName())){
				throw new EntityExistsException("Addon Already Exist : Can't save the Detail");
			}
		}
		if(addonsvo.getAddonName()==""){
			throw new NullPointerException("Addon name can't be left blank");
		}
		addon.setMainItem(mainItem);
		addonsRepository.save(addon);
		return addon;
	}
	
	public List<Addons> findAllAddons(){			// finds all the existing addons.
		
		List<Addons> allAddons = addonsRepository.findAll();
		return allAddons;
		
	}
	
	public List<Addons> findAllAddonsByItemId(long id){			// finds all the existing addons.
		
		List<Addons> allAddonsforItem = addonsRepository.findByMainItemId(id);
		return allAddonsforItem;
		
	}
	
	public Addons findByAddonsId(long id){			// finds Addons by its id.
			
		Addons addons = addonsRepository.findOne(id);
		return addons;
			
	}
	
	public Addons deleteAddons(long id){
		
		Addons addons = addonsRepository.findOne(id);
		addonsRepository.delete(id);
		return addons;
	}

}
