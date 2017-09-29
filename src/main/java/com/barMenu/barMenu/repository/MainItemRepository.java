package com.barMenu.barMenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barMenu.barMenu.entity.MainItem;

@Repository
public interface MainItemRepository extends JpaRepository<MainItem, Long>{
	
}
