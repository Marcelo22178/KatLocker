# KatLocker

A cross-platform keyboard and mouse locking utility built with Java 21 LTS.

## Features

- **Global Hotkey**: Use `Ctrl + Alt + K` to toggle lock mode
- **Universal Compatibility**: Works on Windows, macOS, and Linux
- **Modern Java**: Built with Java 21 LTS for optimal performance and security
- **Native Integration**: Uses JNativeHook for system-level input monitoring

## Requirements

- Java 21 or newer
- Maven 3.6+ (for building from source)

## Building

```bash
# Compile the project
mvn compile

# Create executable JAR
mvn package
```

## Running

```bash
java --enable-native-access=ALL-UNNAMED -jar target/katlocker-1.0.0.jar
```

## Usage

1. Start the application
2. Press `Ctrl + Alt + K` to enable "Cat Mode" (locks keyboard and mouse)
3. Press `Ctrl + Alt + K` again to disable lock mode

## Technical Details

- **Language**: Java 21 LTS
- **Build Tool**: Apache Maven
- **Native Library**: JNativeHook 2.2.2
- **Minimum Java Version**: Java 21

## License

This project is open source. See LICENSE file for details.