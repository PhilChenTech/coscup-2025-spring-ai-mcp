# 📚 Spring AI Demo

這是一個使用 **Spring Boot** 和 **Java** 開發的書籍查詢範例應用，提供一個簡易的 **MCP Server**，可讓 AI Agent（如 Claude Desktop、GitHub Copilot Chat 等）查詢書籍清單與特定書籍資訊。

---

## ✨ 功能特色

- ✅ 查詢所有書籍清單
- 🔍 根據書名查詢特定書籍資料

---

## 🛠 技術棧

- Java 17+
- Spring Boot 3.x
- Gradle 8.x

---

## 🚀 如何執行

1. 使用 Gradle 建置專案：
   ```bash
   ./gradlew build
   ```
2. 將檔案放到你指定的目錄


## 🤖 整合使用說明

### Claude Desktop 設定
注意:記得把C:\\dev\\spring-ai-demo-0.0.1-SNAPSHOT.jar換成自己的路徑
```json
{
  "mcpServers": {
    "spring-ai-demo": {
      "command": "java",
      "args": [
        "-jar",
        "C:\\dev\\spring-ai-demo-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}
```

### GitHub Copilot Chat 設定
注意:記得把C:\\dev\\spring-ai-demo-0.0.1-SNAPSHOT.jar換成自己的路徑
```json
{
  "servers": {
    "spring-ai-demo": {
      "command": "java",
      "args": [
        "-jar",
        "C:\\dev\\spring-ai-demo-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}
```

---

## 📬 聯絡我們

如有任何問題或建議，歡迎聯絡：

**兔田建設**  
✉️ philchen3000tech@gmail.com

---

## 📄 授權條款

本專案採用 [MIT License](LICENSE)。
