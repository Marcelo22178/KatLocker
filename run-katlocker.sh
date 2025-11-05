#!/bin/bash
# KatLocker Launcher for Linux/macOS

# Get the directory where the script is located
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$DIR"

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo ""
    echo "Please install Java 21 or higher from:"
    echo "https://adoptium.net/"
    echo ""
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{print $1}')
if [ "$JAVA_VERSION" -lt 21 ]; then
    echo "WARNING: Java 21 or higher is recommended"
    echo "Current version: $JAVA_VERSION"
fi

# Run KatLocker
echo "Starting KatLocker..."
echo "Look for the lock icon in the system tray."
echo ""

java -jar katlocker-1.0.0.jar &

echo "KatLocker is running in the background."
echo "Press Ctrl+Alt+L to lock/unlock keyboard and mouse."
