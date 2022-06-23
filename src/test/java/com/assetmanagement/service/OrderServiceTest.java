package com.assetmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.assetmanagement.entity.Order;
import com.assetmanagement.entity.OrderItem;
import com.assetmanagement.entity.User;
import com.assetmanagement.entity.Warehouse;
import com.assetmanagement.exception.OrderNotFoundException;
import com.assetmanagement.repository.OrderRepository;

@SpringBootTest
class OrderServiceTest {

	@InjectMocks
	 OrderService orderService = new OrderServiceImpl();
	
	@Mock
	private OrderRepository orderRepository;
	
	@Test
	void testOrderById() {
		
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseId(1);
		warehouse.setWarehouseLocation("Banglore");
		warehouse.setWarehouseName("Arshad's warehouse");
		
		Warehouse warehouse1 = new Warehouse();
		warehouse1.setWarehouseId(2);
		warehouse1.setWarehouseLocation("Hyderabad");
		warehouse1.setWarehouseName("bangs warehouse");
		
		User user = new User();
		user.setAddress("Banglore");
		user.setEmailId("aaa@gmail.com");
		user.setMobileNumber(756773836);
		user.setPassword("AAA12");
		user.setUserId(1);
		user.setUserName("AAA");
		
		
		OrderItem orderItem = new OrderItem();
		orderItem.setAssetId(1);
		orderItem.setQuantity(3);
		
		Set<OrderItem> orderItems = new HashSet<>();
		
		
		Order order = new Order();
		order.setFromWarehouseId(1);
		order.setOrderDate(LocalDate.now());
		order.setOrderitems(orderItems);
		
		orderItem.setOrder(order);
		
		
		orderItems.add(orderItem);
		order.setFromWarehouseId(1);
		order.setToWarehouseId(2);
		order.setUser(user);
		order.setOrderId(1);
		
		Set<Order> listOfOrder = new HashSet<>();
		listOfOrder.add(order);
		user.setGetListOfOrders(listOfOrder);
		
		Optional<Order> optionalOrder = Optional.of(order);
		when(orderRepository.findById(1)).thenReturn(optionalOrder);
		
		Order myOrder = orderService.getOrderById(1);
		assertEquals(2,myOrder.getToWarehouseId());
	}

	@Test
	void testOrderSaveOrder() {
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseId(1);
		warehouse.setWarehouseLocation("Banglore");
		warehouse.setWarehouseName("Arshad's warehouse");
		
		Warehouse warehouse1 = new Warehouse();
		warehouse1.setWarehouseId(2);
		warehouse1.setWarehouseLocation("Hyderabad");
		warehouse1.setWarehouseName("bangs warehouse");
		
		User user = new User();
		user.setAddress("Banglore");
		user.setEmailId("aaa@gmail.com");
		user.setMobileNumber(756773836);
		user.setPassword("AAA12");
		user.setUserId(1);
		user.setUserName("AAA");
		
		
		OrderItem orderItem = new OrderItem();
		orderItem.setAssetId(1);
		orderItem.setQuantity(3);
		
		Set<OrderItem> orderItems = new HashSet<>();
		
		
		Order order = new Order();
		order.setFromWarehouseId(1);
		order.setOrderDate(LocalDate.now());
		order.setOrderitems(orderItems);
		
		orderItem.setOrder(order);
		
		
		orderItems.add(orderItem);
		order.setFromWarehouseId(1);
		order.setToWarehouseId(2);
		order.setUser(user);
		order.setOrderId(1);
		
		Set<Order> listOfOrder = new HashSet<>();
		listOfOrder.add(order);
		user.setGetListOfOrders(listOfOrder);
		
		when(orderRepository.save(order)).thenReturn(order);
		
		Optional<Order> optionalOrder = Optional.of(order);
		when(orderRepository.findById(1)).thenReturn(optionalOrder);
		Order order1 = orderService.getOrderById(1);
		assertEquals(1,order1.getFromWarehouseId());
	}
	
	@Test
	void testGetOrderByIdWithException() {
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseId(1);
		warehouse.setWarehouseLocation("Banglore");
		warehouse.setWarehouseName("Arshad's warehouse");
		
		Warehouse warehouse1 = new Warehouse();
		warehouse1.setWarehouseId(2);
		warehouse1.setWarehouseLocation("Hyderabad");
		warehouse1.setWarehouseName("bangs warehouse");
		
		User user = new User();
		user.setAddress("Banglore");
		user.setEmailId("aaa@gmail.com");
		user.setMobileNumber(756773836);
		user.setPassword("AAA12");
		user.setUserId(1);
		user.setUserName("AAA");
		
		
		OrderItem orderItem = new OrderItem();
		orderItem.setAssetId(1);
		orderItem.setQuantity(3);
		
		Set<OrderItem> orderItems = new HashSet<>();
		
		
		Order order = new Order();
		order.setFromWarehouseId(1);
		order.setOrderDate(LocalDate.now());
		order.setOrderitems(orderItems);
		
		orderItem.setOrder(order);
		
		
		orderItems.add(orderItem);
		order.setFromWarehouseId(1);
		order.setToWarehouseId(2);
		order.setUser(user);
		order.setOrderId(1);
		
		Set<Order> listOfOrder = new HashSet<>();
		listOfOrder.add(order);
		user.setGetListOfOrders(listOfOrder);
	
		
		
		when(orderRepository.findById(100)).thenThrow(OrderNotFoundException.class);
		
		assertThrows(OrderNotFoundException.class,()->orderService.getOrderById(100));
	}
}