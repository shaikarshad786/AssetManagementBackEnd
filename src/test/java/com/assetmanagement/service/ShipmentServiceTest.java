package com.assetmanagement.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.assetmanagement.entity.Shipment;
import com.assetmanagement.exception.ShipmentNotFoundException;
import com.assetmanagement.repository.ShipmentRepository;

@SpringBootTest
public class ShipmentServiceTest {

 @InjectMocks
private ShipmentService shipmentService = new ShipmentServiceImpl();

 @Mock
private ShipmentRepository shipmentRepository;

 @Test
public void testGetShipmentById() {

 Shipment shipment = new Shipment();
shipment.setShipmentId(10);
shipment.setShipmentAssets("Hardware");
shipment.setShipmentStatus("deliverd_succesfully");
shipment.setShipmentDate(LocalDate.now());
shipment.setShipmentLocation("Banaglore");
shipment.setShipmentSubLocation("RRNagar");
shipment.setState("Karnataka");
shipment.setCountry("India");
shipment.setFromWarehouseId(1);
shipment.setToWarehouseId(2);

 Optional<Shipment> optionalShipment = Optional.of(shipment);

 when(shipmentRepository.findById(10)).thenReturn(optionalShipment);

 Shipment myShipment = shipmentService.getShipmentById(10);

 assertEquals("Hardware", myShipment.getShipmentAssets());

 }

 @Test
public void testGetShipmentByIdWithException() {

 when(shipmentRepository.findById(10)).thenThrow(ShipmentNotFoundException.class);

 assertThrows(ShipmentNotFoundException.class, () -> shipmentService.getShipmentById(10));
}

 @Test
public void tsetGelAllProduct() {

 Shipment shipment1 = new Shipment();
shipment1.setShipmentId(10);
shipment1.setShipmentAssets("Hardware");
shipment1.setShipmentStatus("deliverd_succesfully");
shipment1.setShipmentDate(LocalDate.now());
shipment1.setShipmentLocation("Banaglore");
shipment1.setShipmentSubLocation("RRNagar");
shipment1.setState("Karnataka");
shipment1.setCountry("India");
shipment1.setFromWarehouseId(1);
shipment1.setToWarehouseId(2);

 Shipment shipment2 = new Shipment();
shipment2.setShipmentId(20);
shipment2.setShipmentAssets("Hardware");
shipment2.setShipmentStatus("pending");
shipment2.setShipmentDate(LocalDate.now());
shipment2.setShipmentLocation("Chennai");
shipment2.setShipmentSubLocation("Adyar");
shipment2.setState("Tamil_nadu");
shipment2.setCountry("India");
shipment2.setFromWarehouseId(2);
shipment2.setToWarehouseId(1);

 Shipment shipment3 = new Shipment();
shipment3.setShipmentId(30);
shipment3.setShipmentAssets("Software");
shipment3.setShipmentStatus("yet_to_be_delivered");
shipment3.setShipmentDate(LocalDate.now());
shipment3.setShipmentLocation("Mumbai");
shipment3.setShipmentSubLocation("Dharavi");
shipment3.setState("Maharastra");
shipment3.setCountry("India");
shipment3.setFromWarehouseId(1);
shipment3.setToWarehouseId(2);

 List<Shipment> shipmentList = new ArrayList<>();
shipmentList.add(shipment1);
shipmentList.add(shipment2);
shipmentList.add(shipment3);

 when(shipmentRepository.findAll()).thenReturn(shipmentList);

 List<Shipment> shipment = shipmentService.getAllShipment();

 assertEquals(3, shipment.size());

 }

 @Test
public void testSaveShipment() {

 Shipment shipment = new Shipment();
shipment.setShipmentId(10);
shipment.setShipmentAssets("Hardware");
shipment.setShipmentStatus("deliverd_succesfully");
shipment.setShipmentDate(LocalDate.now());
shipment.setShipmentLocation("Banaglore");
shipment.setShipmentSubLocation("RRNagar");
shipment.setState("Karnataka");
shipment.setCountry("India");
shipment.setFromWarehouseId(1);
shipment.setToWarehouseId(2);

 when(shipmentRepository.save(shipment)).thenReturn(shipment);

 Shipment newShipment = shipmentService.saveShipment(shipment);

 assertEquals("Hardware", newShipment.getShipmentAssets());

 verify(shipmentRepository, times(1)).save(shipment);
}

 @Test
public void testDeleteShipment() {

 Shipment shipment = new Shipment();
shipment.setShipmentId(10);
shipment.setShipmentAssets("Hardware");
shipment.setShipmentStatus("deliverd_succesfully");
shipment.setShipmentDate(LocalDate.now());
shipment.setShipmentLocation("Banaglore");
shipment.setShipmentSubLocation("RRNagar");
shipment.setState("Karnataka");
shipment.setCountry("India");
shipment.setFromWarehouseId(1);
shipment.setToWarehouseId(2);

 Optional<Shipment> optionalShipment = Optional.of(shipment);

 when(shipmentRepository.findById(10)).thenReturn(optionalShipment);

 shipmentService.deleteShipmentById(10);

 verify(shipmentRepository, times(1)).findById(10);
verify(shipmentRepository, times(1)).deleteById(10);
}

 @Test
public void testUpdateShipment() {

 Shipment shipment = new Shipment();
shipment.setShipmentId(10);
shipment.setShipmentAssets("Hardware");
shipment.setShipmentStatus("deliverd_succesfully");
shipment.setShipmentDate(LocalDate.now());
shipment.setShipmentLocation("Banaglore");
shipment.setShipmentSubLocation("RRNagar");
shipment.setState("Karnataka");
shipment.setCountry("India");
shipment.setFromWarehouseId(1);
shipment.setToWarehouseId(2);

 Optional<Shipment> optionalShipment = Optional.of(shipment);

 when(shipmentRepository.findById(10)).thenReturn(optionalShipment);

 shipmentService.updateShipment(shipment);

 verify(shipmentRepository, times(1)).findById(10);
verify(shipmentRepository, times(1)).save(shipment);

 }

}