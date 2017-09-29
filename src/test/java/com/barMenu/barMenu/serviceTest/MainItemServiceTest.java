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
import com.barMenu.barMenu.entity.MainItem;
import com.barMenu.barMenu.repository.MainItemRepository;
import com.barMenu.barMenu.service.MainItemService;

@Transactional
public class MainItemServiceTest extends BarMenuApplicationTests{

	private MainItemRepository mainItemRepo;
	private MainItemService mainItemServ;
	
	@Before
	public void setUp(){
	
		mainItemRepo = Mockito.mock(MainItemRepository.class);
		mainItemServ = new MainItemService(mainItemRepo);
	}
	
	@Test
	public void testAddNewItem(){
		MainItem existingMainItem = new MainItem();
		existingMainItem.setId(1L);
		existingMainItem.setItemCode("CODE");
		existingMainItem.setItemName("Test Main Item");
		List<MainItem> itemList = new ArrayList<>();
		itemList.add(existingMainItem);
		
		MainItem mainItem = new MainItem();
		mainItem.setItemCode("NCODE");
		mainItem.setItemName("Test New Item");
		
		when(mainItemRepo.findAll()).thenReturn(itemList);
		when(mainItemRepo.save(mainItem)).thenReturn(mainItem);
		
		MainItem mi = mainItemServ.addNewItem(mainItem);
		Assert.assertNotNull(mi);
		Assert.assertEquals(mainItem,mi);
	}
	
	@Test
	public void testFindAllItem(){
		MainItem mainItem = new MainItem();
		mainItem.setItemCode("CODE");
		mainItem.setItemName("Test Main Item");
		List<MainItem> itemList = new ArrayList<>();
		itemList.add(mainItem);
		
		when(mainItemRepo.findAll()).thenReturn(itemList);
		List<MainItem> newitemList = mainItemServ.findAllItem();
		
		Assert.assertNotNull("Expected not null", newitemList);
		Assert.assertEquals("Expected Size", 1, newitemList.size());
	}
	
	@Test
	public void testFindOneItem(){
		MainItem mainItem = new MainItem();
		mainItem.setId(1L);
		mainItem.setItemCode("CODE");
		mainItem.setItemName("Test Main Item");
		
		when(mainItemRepo.findOne(1L)).thenReturn(mainItem);
		MainItem newitem = mainItemServ.findByMainItemId(1L);
		
		Assert.assertNotNull("Expected not null", newitem);
		Assert.assertEquals("Expected Size", 1L, newitem.getId());
	}
}
