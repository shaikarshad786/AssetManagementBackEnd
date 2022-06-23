package com.assetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmanagement.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
	
	
}
