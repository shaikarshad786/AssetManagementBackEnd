package com.assetmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import com.assetmanagement.entity.Asset;
import com.assetmanagement.entity.Warehouse;
import com.assetmanagement.exception.AssetNotFoundException;
import com.assetmanagement.repository.WarehouseRepository;
import com.assetmanagement.service.AssetService;
import com.assetmanagement.service.AssetServiceImpl;

@SpringBootTest
class WarehouseServiceTest {

	@InjectMocks
	WarehouseService warehouseService = new WarehouseServiceImpl();

	@Mock
	WarehouseRepository warehouseRepository;

	@Test
	void testWarehouseById() {
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseId(10);
		warehouse.setWarehouseName("abc-1");
		warehouse.setWarehouseLocation("pune");

		Optional<Warehouse> optionalWarehouse = Optional.of(warehouse);
		when(warehouseRepository.findById(10)).thenReturn(optionalWarehouse);
		Warehouse myWarehouse = warehouseService.getWarehouseById(10);
		assertEquals("abc-1", myWarehouse.getWarehouseName());
	}

	
	@Test
	void testAddWarehouse() {
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseId(10);
		warehouse.setWarehouseName("abc-1");
		warehouse.setWarehouseLocation("pune");

		when(warehouseRepository.save(warehouse)).thenReturn(warehouse);

	}
	
	@Test
	void testUpdateWarehouse() {
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseId(10);
		warehouse.setWarehouseName("abc-1");
		warehouse.setWarehouseLocation("pune");

		Optional<Warehouse> optionalWarehouse = Optional.of(warehouse);
		when(warehouseRepository.findById(10)).thenReturn(optionalWarehouse);
		warehouseService.updateWarehouse(warehouse);

		verify(warehouseRepository, times(1)).findById(10);
		verify(warehouseRepository, times(1)).save(warehouse);
	}
	
	@Test
	void testRemoveWarehouse() {
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseId(10);
		warehouse.setWarehouseName("abc-1");
		warehouse.setWarehouseLocation("pune");

		Optional<Warehouse> optionalWarehouse = Optional.of(warehouse);
		when(warehouseRepository.findById(10)).thenReturn(optionalWarehouse);
		warehouseService.deleteWarehouse(10);

		verify(warehouseRepository, times(1)).deleteById(10);

	}
	
	@Test
	public void testGetWarehouseByName() {
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseId(10);
		warehouse.setWarehouseName("warehouse-pune");
		warehouse.setWarehouseLocation("pune");

		Optional<Warehouse> optionalWarehouse = Optional.of(warehouse);
		when(warehouseRepository.findByWarehouseName("warehouse-pune")).thenReturn(optionalWarehouse);

		Warehouse tempWarehouse = warehouseService.getByWarehouseName("warehouse-pune");
		assertEquals("warehouse-pune", tempWarehouse.getWarehouseName());
	}
	
	

	@Test
	public void testGetWarehouseByLocation() {
		Warehouse warehouse1 = new Warehouse();
		warehouse1.setWarehouseId(10);
		warehouse1.setWarehouseName("warehouse-pune");
		warehouse1.setWarehouseLocation("pune");

		Warehouse warehouse2 = new Warehouse();
		warehouse2.setWarehouseId(10);
		warehouse2.setWarehouseName("warehouse-pune");
		warehouse2.setWarehouseLocation("pune");
		
		Warehouse warehouse3 = new Warehouse();
		warehouse3.setWarehouseId(10);
		warehouse3.setWarehouseName("warehouse-pune");
		warehouse3.setWarehouseLocation("pune");
		
		
		List<Warehouse> warehouseList = new ArrayList<>();
		warehouseList.add(warehouse1);
		warehouseList.add(warehouse2);
		warehouseList.add(warehouse3);

		when(warehouseRepository.findByWarehouseLocation("pune")).thenReturn(warehouseList);

		List<Warehouse> warehouses = warehouseService.getWarehousesByLocation("pune");

		assertEquals(3, warehouses.size());
	}

	@Test
	public void testGetAllWarehouses() {

		Warehouse warehouse1 = new Warehouse();

		warehouse1.setWarehouseId(1);
		warehouse1.setWarehouseName("warehouse1");
		warehouse1.setWarehouseLocation("pune");

		Warehouse warehouse2 = new Warehouse();

		warehouse2.setWarehouseId(2);
		warehouse2.setWarehouseName("warehouse2");
		warehouse2.setWarehouseLocation("mumbai");

		Warehouse warehouse3 = new Warehouse();

		warehouse3.setWarehouseId(3);
		warehouse3.setWarehouseName("warehouse3");
		warehouse3.setWarehouseLocation("delhi");

		List<Warehouse> warehouseList = new ArrayList<>();
		warehouseList.add(warehouse1);
		warehouseList.add(warehouse2);
		warehouseList.add(warehouse3);

		when(warehouseRepository.findAll()).thenReturn(warehouseList);

		List<Warehouse> warehouses = warehouseService.getAllWarehouse();

		assertEquals(3, warehouses.size());

	}

}
