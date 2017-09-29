package com.barMenu.barMenu.serviceTest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.barMenu.barMenu.BarMenuApplicationTests;
import com.barMenu.barMenu.entity.Addons;
import com.barMenu.barMenu.entity.AddonsVO;
import com.barMenu.barMenu.entity.MainItem;
import com.barMenu.barMenu.repository.AddonsRepository;
import com.barMenu.barMenu.repository.MainItemRepository;
import com.barMenu.barMenu.service.AddonsService;

@Transactional
public class AddonsServiceTest extends BarMenuApplicationTests{

	private MainItemRepository mainItemRepo;
	private AddonsRepository addonsRepo;
	private AddonsService addonServ;
	
	@Before
	public void setUp(){
		addonsRepo = Mockito.mock(AddonsRepository.class);
		addonServ = new AddonsService(mainItemRepo, addonsRepo);
	}
	
	@Test
	public void testAddAddons(){
		
		MainItem mainItem = new MainItem();
		mainItem.setId(1L);
		mainItem.setItemCode("CODE");
		mainItem.setItemName("Test Main Item");
		
		AddonsVO addonsvo = new AddonsVO();
		addonsvo.setMainItemId(1L);
		addonsvo.setMainItemName("Test Main Item");
		addonsvo.setAddonName("Test Addons");
		
		Addons addons = new Addons();
		addons.setAddonName("Test Addons");
		addons.setMainItem(mainItem);
		
		when(mainItemRepo.findOne(addonsvo.getMainItemId())).thenReturn(mainItem);
		when(addonsRepo.save(addons)).thenReturn(addons);
		
		Addons newAddon = addonServ.addNewAddons(addonsvo);
		
		Assert.assertNotNull(newAddon);
		Assert.assertEquals(addons,newAddon);
	}
	
	@Test
	public void testFindAll(){
		MainItem mainItem = new MainItem();
		mainItem.setId(1L);
		mainItem.setItemCode("CODE");
		mainItem.setItemName("Test Main Item");
		
		Addons addons = new Addons();
		addons.setAddonName("Test Addons");
		addons.setMainItem(mainItem);
		List<Addons> list = new ArrayList<>();
		list.add(addons);
		
		when(addonsRepo.findAll()).thenReturn(list);
		List<Addons> newList = addonServ.findAllAddons();
		
		Assert.assertNotNull("Expected not null", newList);
		Assert.assertEquals("Expected Size", 1, newList.size());
	}
	
	@Test
	public void testFindAllByItemId(){
		MainItem mainItem = new MainItem();
		mainItem.setId(1L);
		mainItem.setItemCode("CODE");
		mainItem.setItemName("Test Main Item");
		
		Addons addons = new Addons();
		addons.setAddonName("Test Addons");
		addons.setMainItem(mainItem);
		List<Addons> list = new ArrayList<>();
		list.add(addons);
		
		when(addonsRepo.findByMainItemId(1L)).thenReturn(list);
		List<Addons> newList = addonServ.findAllAddonsByItemId(1L);
		
		Assert.assertNotNull("Expected not null", newList);
		Assert.assertEquals("Expected Size", 1, newList.size());
	}
	
	@Test
	public void testFindOne(){
		MainItem mainItem = new MainItem();
		mainItem.setId(1L);
		mainItem.setItemCode("CODE");
		mainItem.setItemName("Test Main Item");
		
		Addons addons = new Addons();
		addons.setAddonId(1L);
		addons.setAddonName("Test Addons");
		addons.setMainItem(mainItem);
		
		when(addonsRepo.findOne(1L)).thenReturn(addons);
		Addons foundAddon = addonServ.findByAddonsId(1L);
		
		Assert.assertNotNull("Expected not null", foundAddon);
		Assert.assertEquals("Expected Size", 1L, foundAddon.getAddonId());
	}
	
}
