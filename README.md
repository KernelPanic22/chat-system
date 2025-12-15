# WebSocket Chat System

A learning project to understand design patterns, WebSocket communication, and scalable architecture.

## Quick Start

### Prerequisites
- Java 21
- Docker (for Redis in later phases)

### Run the Project

```bash
# Windows
gradlew.bat bootRun

# Linux/Mac
./gradlew bootRun
```

Visit: http://localhost:8080

## What's This Project About?

This is a **bare-bones starting point** for building a scalable real-time chat system. 

You'll learn:
- WebSocket communication with STOMP
- Design patterns (Factory, Strategy, Observer, Singleton, Builder)
- Scalability with Redis
- Distributed systems concepts

## Learning Path

📖 **Start here**: [LEARNING_GUIDE.md](LEARNING_GUIDE.md)

The learning guide walks you through:
1. Understanding WebSockets
2. Building basic chat
3. Implementing design patterns
4. Adding Redis for scalability
5. Advanced features

## Project Structure

```
chat-system/
├── build.gradle              # Gradle configuration
├── src/main/
│   ├── java/com/learning/chat/
│   │   └── ChatApplication.java    # Main application
│   └── resources/
│       ├── application.yml         # Config
│       └── static/
│           └── index.html          # Test page
└── LEARNING_GUIDE.md              # Your roadmap!
```

## Next Steps

1. Read the `LEARNING_GUIDE.md`
2. Start with Phase 1: Understanding WebSockets
3. Build incrementally
4. Have fun learning!

## Resources

- [Spring WebSocket Docs](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket)
- [STOMP Protocol](https://stomp.github.io/)
- [Redis Documentation](https://redis.io/documentation)
