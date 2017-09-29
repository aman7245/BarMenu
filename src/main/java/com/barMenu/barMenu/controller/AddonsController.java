package com.barMenu.barMenu.controller;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barMenu.barMenu.entity.Addons;
import com.barMenu.barMenu.entity.AddonsVO;
import com.barMenu.barMenu.entity.MainItem;
import com.barMenu.barMenu.service.AddonsService;
import com.barMenu.barMenu.service.MainItemService;

@Controller
@RequestMapping("addons")
public class AddonsController {

	@Autowired MainItemService mainItemServ;
	@Autowired AddonsService addonServ;
	
	@GetMapping("/addAddons/{id}")
	public String start(@PathVariable Long id, Model model){
		MainItem mainItem = mainItemServ.findByMainItemId(id);
		AddonsVO addonsvo = new AddonsVO();
		
		model.addAttribute("addonsvo", addonsvo);
		model.addAttribute("addonsList", addonServ.findAllAddonsByItemId(id));
		addonsvo.setMainItemName(mainItem.getItemName());
		addonsvo.setMainItemId(id);
		
		return "addaddons";
	}
	@PostMapping("/addnewaddons")
	public String createTopic(Model model,AddonsVO addonsvo){
		try{
			model.addAttribute(addonServ.addNewAddons(addonsvo));
		} catch(EntityExistsException ee){	
			ee.printStackTrace();
			model.addAttribute("error", ee.getMessage());
			model.addAttribute("addonsvo", addonsvo);
			model.addAttribute("addonsList", addonServ.findAllAddonsByItemId(addonsvo.getMainItemId()));
			return "adsaddons";
			}
		catch(NullPointerException npe){
			npe.printStackTrace(); 
			model.addAttribute("error", npe.getMessage());
			model.addAttribute("addonsvo", addonsvo);
			return "adsaddons";
		}
			
		return "redirect:/addons/addAddons/"+addonsvo.getMainItemId();	
	}
	@RequestMapping(value="/findaddons/{id}",method=RequestMethod.GET)
	public String findAddons(@PathVariable Long id, Model model) {
		model.addAttribute("addonsList",addonServ.findAllAddons());
		return "addaddons";
	}
	@RequestMapping(value = "/deleteaddon/{addonId}", method = RequestMethod.GET)
	public String deleteAddon(@PathVariable Long addonId, Model model){
		Addons addon = addonServ.findByAddonsId(addonId);
		long mainItemId = addon.getMainItem().getId();
		addonServ.deleteAddons(addonId);
		return "redirect:/addons/addAddons/"+mainItemId;
	}
	
	
}
