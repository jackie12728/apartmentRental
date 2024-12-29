package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.ListingDTO;
import com.example.demo.service.ListingService;

@SpringBootTest
public class TestSaveListing {
    @Autowired
    private ListingService listingService;

    @Test
    public void testSaveListing() {
        // 準備其他模擬數據
        String listingname = "近捷運站、麥當勞斜對面";
        String description = "邊間~ 採光佳~ 一樓永豐銀行";
        Long cityId = 1L;
        Long regionId = 160L;
        String address = "台北市士林區南京東路五段46號12樓";
        Integer rent = 8400;
        Long userId = 1L;
        Long rentalId = 1L;

        // 設置圖片路徑模擬數據
        List<String> imagePaths = new ArrayList<>();
        imagePaths.add("/images/listingImages/testImage1.jpg");
        imagePaths.add("/images/listingImages/testImage2.jpg");

        // 構建 ListingDTO 對象
        ListingDTO listingDTO = new ListingDTO();
        listingDTO.setListingname(listingname);
        listingDTO.setDescription(description);
        listingDTO.setCityId(cityId);
        listingDTO.setRegionId(regionId);
        listingDTO.setAddress(address);
        listingDTO.setRent(rent);
        listingDTO.setUserId(userId);
        listingDTO.setRentalId(rentalId);
        listingDTO.setImagePaths(imagePaths);

        // 測試 saveListing 方法
        Optional<ListingDTO> optListing = listingService.saveListing(listingDTO);

        // 打印測試結果
        System.out.println("新增結果: " + optListing);
    }
}
