package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.aop.CheckUserSession;
import com.example.demo.model.dto.ListingDTO;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.ListingService;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /listingmanage
 * ----------------------------------
 * POST   /save   新增房屋
 * POST   /modify 更新房屋
 * DELETE /delete 刪除房屋
 * */

@RestController
@RequestMapping("/listingmanage")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ListingController {
	
	@Autowired
	private ListingService listingService;
	
	// 新增房屋
	@PostMapping("/save")
	@CheckUserSession
	public ResponseEntity<ApiResponse<String>> createListing(
            @RequestPart("listing") ListingDTO listingDTO,
            @RequestPart("images") MultipartFile[] images) throws IOException {
            
        // 取得專案根目錄
        String projectRoot = System.getProperty("user.dir");
        // 設定圖片儲存目錄
        String uploadDir = projectRoot + File.separator + "src" + File.separator + "main" + 
                          File.separator + "resources" + File.separator + "static" + 
                          File.separator + "images" + File.separator + "listingImages";
        
        // 確保目錄存在
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        // 處理圖片上傳
        List<String> imagePaths = new ArrayList<>();
        
        for (MultipartFile image : images) {
            // 生成唯一檔名
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            // 建立完整的檔案路徑
            Path targetLocation = directory.toPath().resolve(fileName);
            
            try {
                // 使用Files.copy來儲存檔案
                Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                // 儲存相對路徑到資料庫
                imagePaths.add("/images/listingImages/" + fileName);
            } catch (IOException e) {
                throw new IOException("Could not store file " + fileName + ". Please try again!", e);
            }
        }
        
        // 設定圖片路徑到 DTO
        listingDTO.setImagePaths(imagePaths);
        
        // 呼叫 service 儲存資料
        Optional<ListingDTO> optListingDTO = listingService.saveListing(listingDTO);
        if(optListingDTO.isEmpty()) {
            return ResponseEntity.status(400).body(ApiResponse.error(400, "新增房屋失敗"));
        }
        
        return ResponseEntity.ok(ApiResponse.success("新增房屋成功", "新增房屋成功"));
    }
	
	// 編輯房屋
	@PostMapping("/modify")
	@CheckUserSession
	public ResponseEntity<ApiResponse<String>> modifyListing(@RequestBody ListingDTO listingDTO) {
		Optional<ListingDTO> optListingDTO = listingService.modifyListing(listingDTO);
		if(optListingDTO.isEmpty()) {
			return ResponseEntity.status(400).body(ApiResponse.error(400, "更新房屋失敗"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("更新房屋成功", "更新房屋成功"));
	}
	
	// 刪除房屋
	@DeleteMapping("/delete")
	@CheckUserSession
	public ResponseEntity<ApiResponse<String>> deleteListing(@RequestBody Long listingId) {
		Optional<ListingDTO> optListingDTO = listingService.deleteListing(listingId);
		if(optListingDTO.isEmpty()) {
			return ResponseEntity.status(400).body(ApiResponse.error(400, "刪除房屋失敗"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("刪除房屋成功", "刪除房屋成功"));
	}
	
}
