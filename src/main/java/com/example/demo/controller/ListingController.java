package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<ApiResponse<String>> saveListing(@RequestBody ListingDTO listingDTO) {
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
