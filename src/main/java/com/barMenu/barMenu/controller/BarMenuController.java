package com.barMenu.barMenu.controller;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barMenu.barMenu.entity.MainItem;
import com.barMenu.barMenu.service.MainItemService;

@Controller
public class BarMenuController {
	
	@Autowired MainItemService mainItemServ;
	
	@GetMapping("/")
	public String home(Model model){
		model.addAttribute("list",mainItemServ.findAllItem());
		return "homepage";
	}
	
	@GetMapping("/addNewItem")
	public String addNew(Model model){
		model.addAttribute("mainItem", new MainItem());
		return "addmenuitem";
	}
	
	@RequestMapping(value="/addNewItem" , method=RequestMethod.POST)
	public String addNewItem(Model model, MainItem mainItem){
		
		try{
			model.addAttribute("MainItem", mainItemServ.addNewItem(mainItem));
		}  catch(EntityExistsException eee){
			eee.printStackTrace(); 
			model.addAttribute("error", eee.getMessage());
			model.addAttribute("MainItem", mainItem);
			return "addmenuitem";
		}
		catch(NullPointerException npe){
			npe.printStackTrace(); 
			model.addAttribute("error", npe.getMessage());
			model.addAttribute("MainItem", mainItem);
			return "addmenuitem";
		}
		
		return "redirect:/findAllItem";
	}
	
	@RequestMapping(value="/findAllItem",method=RequestMethod.GET)
	public String findPackage(Model model){
		model.addAttribute("list",mainItemServ.findAllItem());
		return "homepage";
	}
	@RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
	public String deleteMainItem(@PathVariable Long id, Model model){
		mainItemServ.deleteItem(id);
		model.addAttribute("list",mainItemServ.findAllItem());
		return "homepage";
	}

}
