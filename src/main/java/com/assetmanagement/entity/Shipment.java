package com.assetmanagement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shipment_tbl")
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ship_id")
	private int shipmentId;

	@NotNull(message="Shipment assets is required")
	@Column(name = "ship_assets")
	private String shipmentAssets;

	@NotNull(message="Shipment status is required")
	@Column(name = "ship_status")
	private String shipmentStatus;

	@NotNull(message="Shipment sub location is required")
	@Column(name = "ship_sub_location")
	private String shipmentSubLocation;

	@NotNull(message="shipment location is required")
	@Column(name = "ship_location")
	private String shipmentLocation;

	@NotNull(message="State is required")
	@Column(name = "state")
	private String state;

	@NotNull(message="Country is required")
	@Column(name = "country")
	private String country;

	@FutureOrPresent(message="Give valid date")
	@Column(name = "date")
	private LocalDate shipmentDate;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public LocalDate getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(LocalDate shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	private int fromWarehouseId;

	private int toWarehouseId;

	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getShipmentAssets() {
		return shipmentAssets;
	}

	public void setShipmentAssets(String shipmentAssets) {
		this.shipmentAssets = shipmentAssets;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getShipmentSubLocation() {
		return shipmentSubLocation;
	}

	public void setShipmentSubLocation(String shipmentSubLocation) {
		this.shipmentSubLocation = shipmentSubLocation;
	}

	public String getShipmentLocation() {
		return shipmentLocation;
	}

	public void setShipmentLocation(String shipmentLocation) {
		this.shipmentLocation = shipmentLocation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getFromWarehouseId() {
		return fromWarehouseId;
	}

	public void setFromWarehouseId(int fromWarehouseId) {
		this.fromWarehouseId = fromWarehouseId;
	}

	public int getToWarehouseId() {
		return toWarehouseId;
	}

	public void setToWarehouseId(int toWarehouseId) {
		this.toWarehouseId = toWarehouseId;
	}

}