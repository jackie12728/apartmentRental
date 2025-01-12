# 租屋平台 QuickLease
<img src="src/main/resources/static/images/listingImages/apartment.jpg" alt="Photo by Luke van Zyl on Unsplash" width="200">

## 目錄
 - [專題背景](#專題背景)
 - [專題目的](#專題目的)
 - [系統設計](#系統設計)
 - [技術細節](#技術細節)
 - [資料庫架構](#資料庫架構)
 - [網站架構](#網站架構)
 - [畫面與功能](#畫面與功能)

## 專題背景
　　靈感來自我在學生時期的租屋經驗。對許多學生來說，租屋是新學期開始前必須面對的課題，但這個過程並不輕鬆。常見的問題包括資訊來源分散，無法一次掌握所有選項；租屋流程繁瑣，與房東溝通效率低；甚至還有安全問題，例如不可靠的房東。這些痛點讓我深刻意識到，需要一個能簡化租屋流程、提高資訊透明度的解決方案。因此，QuickLease 應運而生。

## 專題目的
　　QuickLease 的核心目的是解決傳統租屋模式中的各種痛點。我們希望透過簡化流程、提高資訊透明度，構建一個安全可靠的平臺，來改善租屋經驗。讓用戶能輕鬆找到理想的房子。

## 系統設計
<img src="src/main/resources/static/images/listingImages/system_design.jpg" alt="system_design" style="max-width: 100%; height: auto;">

## 技術細節
- 前端：HTML, CSS, JavaScript, Material-UI
- 後端：Java, Spring boot
- 資料庫：MySQL, Redis
- 架構：MVC (Model-View-Controller), 包括 Controller, Service, DAO
- 安全性：
  - CAPTCHA 圖形驗證碼防止機器人攻擊，加強網站安全
  - SHA-256 密碼加鹽防止彩虹表攻擊，保護用戶隱私

## 資料庫架構
<img src="src/main/resources/static/images/listingImages/database_architecture.jpg" alt="database_architecture" style="max-width: 100%; height: auto;">

## 網站架構
<img src="src/main/resources/static/images/listingImages/website_architecture.jpg" alt="website_architecture" style="max-width: 100%; height: auto;">

## 畫面與功能
### 註冊、登入
<img src="src/main/resources/static/images/listingImages/screen_and_function.jpg" alt="screen_and_function" style="max-width: 100%; height: auto;">
<img src="src/main/resources/static/images/listingImages/screen_and_function2.jpg" alt="screen_and_function2" style="max-width: 100%; height: auto;">

### 首頁、預約看房
<img src="src/main/resources/static/images/listingImages/screen_and_function3.jpg" alt="screen_and_function3" style="max-width: 100%; height: auto;">
<img src="src/main/resources/static/images/listingImages/screen_and_function4.jpg" alt="screen_and_function4" style="max-width: 100%; height: auto;">

### 使用者管理、房屋管理
<img src="src/main/resources/static/images/listingImages/screen_and_function5.jpg" alt="screen_and_function5" style="max-width: 100%; height: auto;">
<img src="src/main/resources/static/images/listingImages/screen_and_function6.jpg" alt="screen_and_function6" style="max-width: 100%; height: auto;">

### 預約紀錄、收藏紀錄
<img src="src/main/resources/static/images/listingImages/screen_and_function7.jpg" alt="screen_and_function7" style="max-width: 100%; height: auto;">
<img src="src/main/resources/static/images/listingImages/screen_and_function8.jpg" alt="screen_and_function8" style="max-width: 100%; height: auto;">
