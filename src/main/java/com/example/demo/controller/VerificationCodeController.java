package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.ApiResponse;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@RestController
@RequestMapping("/api/verification")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class VerificationCodeController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;
    
    @Autowired
    private ValueOperations<String, String> redisValueOperations;

    /**
     * 取得Base64格式的驗證碼
     * @return ApiResponse包含uuid和驗證碼圖片的Base64字串
     */
    @GetMapping("/code/base64")
    public ResponseEntity<ApiResponse<Map<String, String>>> getVerificationCodeBase64() {
        try {
            String kaptchaText = defaultKaptcha.createText();
            BufferedImage image = defaultKaptcha.createImage(kaptchaText);
            
            // 轉換圖片為Base64
            String base64Code = "";
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                ImageIO.write(image, "jpg", outputStream);
                base64Code = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            }
            
            // 產生UUID並存入Redis
            String uuid = UUID.randomUUID().toString();
            Map<String, String> result = new HashMap<>();
            result.put("uuid", uuid);
            result.put("code", "data:image/png;base64," + base64Code);
            
            // 將驗證碼存入Redis，設置60秒過期
            redisValueOperations.set(uuid, kaptchaText, 60L, TimeUnit.SECONDS);
            
            return ResponseEntity.ok(ApiResponse.success("驗證碼產生成功", result));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(ApiResponse.error(500, "驗證碼產生失敗: " + e.getMessage()));
        }
    }

    /**
     * 驗證用戶輸入的驗證碼
     * @param uuid Redis中的key
     * @param code 用戶輸入的驗證碼
     * @return 驗證結果
     */
    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<Boolean>> verifyCode(
            @RequestParam String uuid,
            @RequestParam String code) {
        try {
            String storedCode = redisValueOperations.get(uuid);
            if (storedCode == null) {
                return ResponseEntity.ok(ApiResponse.error(400, "驗證碼已過期"));
            }
            
            boolean isValid = storedCode.equalsIgnoreCase(code);
            if (isValid) {
                // 驗證成功後刪除Redis中的驗證碼
                redisValueOperations.getOperations().delete(uuid);
                return ResponseEntity.ok(ApiResponse.success("驗證成功", true));
            } else {
                return ResponseEntity.ok(ApiResponse.error(400, "驗證碼錯誤"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(ApiResponse.error(500, "驗證失敗: " + e.getMessage()));
        }
    }
}