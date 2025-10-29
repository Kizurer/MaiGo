# 🎵 Maigo
> "A network protocol relay layer designed for a certain 8-key rhythm arcade game server."

[🇨🇳 中文版](https://github.com/Kizurer/Maigo/blob/main/README.md) | [🌐 English Version](./README_EN.MD)

---

## 📖 Introduction

**Maigo** is a **Network Protocol Relay Layer** designed for the GA server of a certain 8-key rhythm arcade game.  
It serves as a relay layer between the client and the game server, handling message redirection, data repackaging, and protocol adaptation.

Key capabilities include:

- ✨ **Transparent communication proxy**
- ⚙️ **Flexible protocol conversion and custom handling**
- 📦 **Automatic packet structure parsing and repackaging**

Most of the known packets have been re-implemented, though some remain under development and testing.

---

## 🚀 Features

- ✅ Supports the majority of official communication packet formats
- 🔄 Handles request and response structures automatically
- 🧩 Modular architecture — easily extendable for new packet types
- 🪶 Lightweight, easy to deploy, open-source under the MIT License

---

## 🧠 Architecture Overview

Maigo acts as a middleware between the **Game Client** and the **Official / Simulated Title Server**.  
It intercepts HTTP / JSON requests, validates and repackages the data, then forwards them to the designated server.

```
┌───────────────┐       ┌──────────────┐       ┌────────────────┐
│   Game Client │  ⇄→  │   Maigo API  │  ⇄→  │  Title Server   │
└───────────────┘       └──────────────┘       └────────────────┘
```

Maigo does **not** modify the client’s behavior directly — it only provides a protocol-level proxy and debugging layer.

---

## 🧩 Example Usage

A simple example that calls the `GET_USER_PREVIEW` API:

```java
public class Test {
    public static void main(String[] args) {
        long uid = 114514L;

        MaiGoAPI maiGoAPI = new MaiGoAPI(
                "ur key here",
                "ur key here",
                "ur title server ip here",
                "client_id",
                "place_name",
                "http://114514.cn",
                "salt here",
                "word",
                114514,
                1919810
        );

        System.out.println(maiGoAPI.sendPacket(APIEnum.GET_USER_PREVIEW, uid));
    }
}
```

The output is typically a JSON object containing the user’s preview data.

---

## ⚙️ Build & Run

### Requirements
- **JDK 17+**
- **Gradle / Maven**
- Network access to the target Title Server

### Run
1. Clone this repository:
   ```bash
   git clone https://github.com/Kizurer/Maigo.git
   cd Maigo
   ```
2. Build the project:
   ```bash
   ./gradlew build
   ```
3. Import `MaiGoAPI` in your Java project and start making API calls.

---

## 📜 Current Progress

| Module | Status | Notes |
|--------|--------|--------|
| User Preview | ✅ Implemented | Working normally |
| User Data | ✅ Implemented | |
| Friend System | 🚧 In development | |
| Miscellaneous Packets | 🕓 Pending | |

---

## 🤝 Contribution

Contributions of all kinds are welcome — code, documentation, testing, or suggestions.  
If you encounter bugs or protocol mismatches, please open an issue or submit a PR.

---

## 📄 License

This project is licensed under the [MIT License](../LICENSE).

```
MIT License © 2025 NekoRainy (K)
```

---

## 💬 Acknowledgments

- Thanks to everyone involved in reverse engineering, analysis, and testing
- Special thanks to the open protocol research community
- Special thanks to **LEAKER**
- And **you**

---

> 🌧️ *Maigo — “A lost protocol, now reborn.”*
