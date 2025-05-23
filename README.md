# 🎬 Movie Ticket Ordering App

一個基於 Android 的電影票訂購應用，提供使用者瀏覽電影資訊、詳細介紹、登入註冊、搜尋推薦等功能。

## 📱 功能介紹

- 🔐 使用者登入 / 註冊
- 🎞️ 熱門電影與即將上映電影瀏覽
- 🔍 電影搜尋與關鍵字推薦系統
- 📄 多部電影的詳細介紹頁面（如 Godzilla、Furiosa、Panda 4、The Full Guy 等）
- 🧾 個人檔案頁面管理
- 🖼️ 圖片輪播與 RecyclerView 排列

## 📁 專案結構

```
MovieTicketOrdering/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/movieticketordering/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── LoginActivity.java
│   │   │   │   ├── SignUpActivity.java
│   │   │   │   ├── profilePage.java
│   │   │   │   ├── SearchingPage.java
│   │   │   │   ├── Adapter/...
│   │   │   │   ├── KeyWord_package/...
│   │   │   │   └── [電影詳細頁 *.java]
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
```

## 🧰 技術棧

- 📱 **Android SDK**
- ☕ **Java**
- 🧱 **Gradle Kotlin DSL**
- 📦 Firebase（含 `google-services.json`）
- 🎨 XML Layouts, RecyclerView, Adapter 模式

## 🚀 執行方式

1. 開啟 Android Studio 並導入本專案資料夾
2. 確保你已配置 Firebase 並更新 `google-services.json`
3. 點擊「執行」即可部署至模擬器或實體裝置

## 📌 注意事項

- 確保你的 Android Studio 支援 Kotlin DSL（建議使用 Arctic Fox 或以上版本）
- 使用者登入功能需要 Firebase Auth 設定
- 如有使用 Firestore 或 Storage 功能，請至 Firebase 控制台啟用並設定權限
