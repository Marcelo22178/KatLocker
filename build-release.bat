@echo off
REM Build script for KatLocker Release

echo ============================================
echo    KatLocker - Build Release Package
echo ============================================
echo.

REM Check if Maven is available
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo Using installed Maven...
    set MVN=C:\tools\apache-maven-3.9.9\bin\mvn.cmd
) else (
    set MVN=mvn
)

echo [1/3] Cleaning previous builds...
%MVN% clean
if %errorlevel% neq 0 (
    echo ERROR: Failed to clean project
    pause
    exit /b 1
)

echo.
echo [2/3] Compiling and packaging...
%MVN% package
if %errorlevel% neq 0 (
    echo ERROR: Failed to build project
    pause
    exit /b 1
)

echo.
echo [3/3] Creating release package...

REM Create release directory
if not exist "release" mkdir release
del /Q release\* 2>nul

REM Copy JAR file
copy target\katlocker-1.0.0.jar release\katlocker-1.0.0.jar

REM Copy launcher scripts
copy run-katlocker.bat release\
copy run-katlocker.sh release\

REM Copy documentation
copy README.md release\
copy GUIA_USO.md release\
copy LICENSE release\ 2>nul

echo.
echo ============================================
echo    BUILD SUCCESSFUL!
echo ============================================
echo.
echo Release package created in: release\
echo.
echo Contents:
echo   - katlocker-1.0.0.jar (Executable JAR)
echo   - run-katlocker.bat (Windows launcher)
echo   - run-katlocker.sh (Linux/macOS launcher)
echo   - README.md
echo   - GUIA_USO.md
echo   - LICENSE
echo.
echo You can now:
echo 1. Test: cd release ^&^& run-katlocker.bat
echo 2. Create ZIP for GitHub Release
echo 3. Upload to GitHub
echo.
pause
