package com.barMenu.barMenu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barMenu.barMenu.entity.Addons;

public interface AddonsRepository extends JpaRepository<Addons, Long>{
	
	public List<Addons> findByMainItemId(Long id);    // returns all addons related to a particular Item.

}
