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
import org.springframework.boot.test.context.SpringBootTest;

import com.assetmanagement.entity.Asset;
import com.assetmanagement.exception.AssetNotFoundException;
import com.assetmanagement.repository.AssetRepository;


@SpringBootTest
 class AssetServiceTest {

	@InjectMocks
	AssetService assetService = new AssetServiceImpl();

	@Mock
	AssetRepository assetRepository;

	@Test
	void testAssetById() {
		Asset assets = new Asset();
		assets.setAssetId(1);
		assets.setAssetName("Dell-12300");
		assets.setManufacturer("Dell-INC");
		assets.setAssetPrice(49500);
		assets.setAssetCategory("Furniture");
		assets.setAssetType("Laptop");

		Optional<Asset> optionalAsset = Optional.of(assets);
		when(assetRepository.findById(1)).thenReturn(optionalAsset);
		Asset myAssets = assetService.getAssetById(1);
		assertEquals("Dell-12300", myAssets.getAssetName());
	}

	@Test
	void testGetAssetByIdWithException() {
		when(assetRepository.findById(1)).thenThrow(AssetNotFoundException.class);
		assertThrows(AssetNotFoundException.class, () -> assetService.getAssetById(1));
	}

	@Test
	void testAddAsset() {
		Asset assets = new Asset();
		assets.setAssetId(1);
		assets.setAssetName("Dell-12300");
		assets.setManufacturer("Dell-INC");
		assets.setAssetPrice(49500);
		assets.setAssetCategory("Furniture");
		assets.setAssetType("Laptop");

		when(assetRepository.save(assets)).thenReturn(assets);

	}

	@Test
	void testremoveAsset() {
		Asset assets = new Asset();
		assets.setAssetId(1);
		assets.setAssetName("Dell-12300");
		assets.setManufacturer("Dell-INC");
		assets.setAssetPrice(49500);
		assets.setAssetCategory("Electronics");
		assets.setAssetType("Laptop");

		Optional<Asset> optionalAsset = Optional.of(assets);
		when(assetRepository.findById(1)).thenReturn(optionalAsset);
		assetService.deleteAsset(1);

		verify(assetRepository, times(1)).deleteById(1);

	}

	@Test
	void testModifyAsset() {
		Asset assets = new Asset();
		assets.setAssetId(1);
		assets.setAssetName("Dell-12300");
		assets.setManufacturer("Dell-INC");
		assets.setAssetPrice(49500);
		assets.setAssetCategory("Electronics");
		assets.setAssetType("Laptop");

		Optional<Asset> optionalAsset = Optional.of(assets);
		when(assetRepository.findById(1)).thenReturn(optionalAsset);
		assetService.modifyAsset(assets);

		verify(assetRepository, times(1)).findById(1);
		verify(assetRepository, times(1)).save(assets);
	}

	@Test
	void testfetchAllAssets() {
		Asset assets = new Asset();
		assets.setAssetId(1);
		assets.setAssetName("Dell-12300");
		assets.setManufacturer("Dell-INC");
		assets.setAssetPrice(49500);
		assets.setAssetCategory("Electronics");
		assets.setAssetType("Laptop");

		Asset asset1 = new Asset();
		asset1.setAssetId(2);
		asset1.setAssetName("Dell-12300");
		asset1.setManufacturer("Dell-INC");
		asset1.setAssetPrice(49500);
		asset1.setAssetCategory("Electronics");
		asset1.setAssetType("Laptop");

		List<Asset> assetList = new ArrayList<>();
		assetList.add(asset1);
		assetList.add(assets);

		when(assetRepository.findByAssetName("Dell-12300")).thenReturn(assetList);
		List<Asset> myAssets = assetService.getAllAssetsByName("Dell-12300");
		assertEquals(2, myAssets.size());

	}

	

}