@echo off
REM KatLocker Launcher for Windows
REM Runs KatLocker without console window

cd /d "%~dp0"

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java no esta instalado o no esta en el PATH
    echo.
    echo Por favor instala Java 21 o superior desde:
    echo https://adoptium.net/
    echo.
    pause
    exit /b 1
)

REM Run KatLocker without console window
start "" javaw -jar katlocker-1.0.0.jar

REM Optional: Show a brief message
echo KatLocker se esta ejecutando en segundo plano...
echo Busca el icono del candado en la bandeja del sistema.
timeout /t 2 >nul
