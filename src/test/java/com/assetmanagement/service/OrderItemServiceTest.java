package com.assetmanagement.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.assetmanagement.entity.Order;
import com.assetmanagement.entity.OrderItem;
import com.assetmanagement.entity.Shipment;
import com.assetmanagement.entity.User;
import com.assetmanagement.repository.OrderItemRepository;

@SpringBootTest
public class OrderItemServiceTest {

 @InjectMocks
private OrderItemService orderItemService=new OrderItemServiceImpl();
@Mock
private OrderItemRepository orderItemRepository;

 @Test
public void testgetOrderItemById() {
	 
	 User user=new User();
	 user.setUserId(1);
	 user.setUserName("Shekhar");
	 user.setMobileNumber(123456789);
	 user.setAddress("21/600,K.H.M street");
	 user.setEmailId("shekhar@gmail.com");
	 user.setPassword("9876543");
	 
	 Shipment shipment=new Shipment();
	 shipment.setShipmentId(1);
	 shipment.setShipmentAssets("Laptops");
	 shipment.setShipmentLocation("Hyderabad");
	 shipment.setShipmentSubLocation("Ameepet");
	 shipment.setFromWarehouseId(1);
	 shipment.setToWarehouseId(2);
	 shipment.setShipmentDate(LocalDate.now());
	 shipment.setShipmentStatus("Pending");
	 shipment.setState("TS");
	 shipment.setCountry("India");
	 
	 
	 Order order =new Order();
	 order.setOrderId(1);
	 order.setOrderStatus("Pending");
	 order.setOrderDate(LocalDate.now());
	 order.setFromWarehouseId(1);
	 order.setToWarehouseId(2);
	 order.setUser(user);
	 
	 Order order1 =new Order();
	 order1.setOrderId(1);
	 order1.setOrderStatus("Pending");
	 order1.setOrderDate(LocalDate.now());
	 order1.setFromWarehouseId(1);
	 order1.setToWarehouseId(2);
	 order1.setUser(user);
	 
	 Set<Order> orders=new HashSet<>();
	 orders.add(order1);
	 orders.add(order);
	 user.setGetListOfOrders(orders);
	 
	 OrderItem orderItem=new OrderItem();
	 orderItem.setAssetId(1);
	 orderItem.setOrderItemId(1);
	 orderItem.setQuantity(3);
	 orderItem.setOrder(order);
	 
	 OrderItem orderItem1=new OrderItem();
	 orderItem1.setAssetId(1);
	 orderItem1.setOrderItemId(1);
	 orderItem1.setQuantity(3);
	 orderItem1.setOrder(order);
	 
	 Set<OrderItem> orderItems=new HashSet<>();
	 orderItems.add(orderItem1);
	 orderItems.add(orderItem);
	 order.setOrderitems(orderItems);

 
Optional<OrderItem> optionOrderItem=Optional.of(orderItem);
when(orderItemRepository.findById(1)).thenReturn(optionOrderItem);
OrderItem myOrderItem=orderItemService.getOrderItemById(1);
assertEquals(1,myOrderItem.getOrderItemId());

orderItemService.getOrderItemById(1);
}
}