# 🎵 Maigo
> 「为某八个按键律动游戏机服务器设计的网络通讯协议转接层」
> 
[🇨🇳 中文版](./README.md) | [🌐 English Version](https://github.com/Kizurer/Maigo/blob/main/docs/README_EN.MD)

---

## 📖 简介

**Maigo** 是一个专为特定某八键律动游戏的GA服务器设计的**网络通讯协议转接层 (Network Protocol Relay Layer)**。  
它旨在在客户端与服务器之间进行中继、重定向与数据重新封装，从而实现：

- ✨ **透明的通信代理**
- ⚙️ **灵活的协议转换与自定义处理**
- 📦 **Packet 结构的自动解析与重新包装**

目前项目已实现了大部分协议包（Packet）的重新封装与适配，仍有部分包处于开发与测试阶段。

---

## 🚀 特性

- ✅ 支持绝大多数已知的官方通信包格式
- 🔄 自动处理请求与响应结构，简化 API 调用
- 🧩 可插拔式架构，方便扩展新协议或自定义逻辑
- 🪶 轻量、易部署、开源（MIT License）

---

## 🧠 工作原理概述

Maigo 位于 **客户端** 与 **官方 / 模拟服务器** 之间，  
通过拦截与重定向 HTTP / JSON 请求，进行数据校验、解包、再封装，最后转发给目标服务器。

```
┌───────────────┐       ┌──────────────┐       ┌────────────────┐
│   Game Client │  ⇄→  │   Maigo API  │  ⇄→  │  Title Server   │
└───────────────┘       └──────────────┘       └────────────────┘
```

Maigo 不直接修改客户端行为，只提供协议级别的代理层与调试接口。

---

## 🧩 使用示例

下面是一个简单的示例，用于调用 `GET_USER_PREVIEW` 接口：

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

输出结果通常为对应用户的预览数据 JSON。

---

## ⚙️ 构建与运行

### 依赖
- **JDK 17+**
- **Gradle / Maven**
- 网络环境可访问目标 Title Server

### 运行
1. 克隆本仓库：
   ```bash
   git clone https://github.com/YourUsername/Maigo.git
   cd Maigo
   ```
2. 构建项目：
   ```bash
   ./gradlew build
   ```
3. 在你的代码中引入 `MaiGoAPI` 并调用需要的接口即可。

---

## 📜 当前进度

| 模块 | 状态 | 备注 |
|------|------|------|
| 用户预览 (User Preview) | ✅ 已实现 | 可正常调用 |
| 用户数据 (User Data) | ✅ 已实现 | |
| 好友系统 (Friend List) | 🚧 开发中|
| 其他未分类 Packet | 🕓 待补充 |  |

---

## 🤝 贡献

欢迎任何形式的贡献（代码、文档、测试、建议）。  
如果你发现 bug 或协议异常，请通过 issue 或 PR 提交。

---

## 📄 许可证

本项目采用 [MIT License](./LICENSE)。

```
MIT License © 2025 NekoRainy (K)
```

---

## 💬 致谢

- 感谢所有参与逆向、分析、与调试的开发者们
- 特别感谢社区对开放协议研究的贡献
- Special thanks to <strong>LEAKER</strong>
- 你

---
