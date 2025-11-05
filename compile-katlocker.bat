@echo off
echo 🐱 Compilando KatLocker con Maven...
echo.

REM Buscar Maven en ubicaciones comunes
set "MVN_CMD="
if exist "C:\Program Files\Apache Software Foundation\maven\bin\mvn.cmd" (
    set "MVN_CMD=C:\Program Files\Apache Software Foundation\maven\bin\mvn.cmd"
) else if exist "C:\apache-maven\bin\mvn.cmd" (
    set "MVN_CMD=C:\apache-maven\bin\mvn.cmd"
) else if exist "%MAVEN_HOME%\bin\mvn.cmd" (
    set "MVN_CMD=%MAVEN_HOME%\bin\mvn.cmd"
) else (
    REM Probar si maven está en PATH
    where mvn >nul 2>&1
    if %ERRORLEVEL%==0 (
        set "MVN_CMD=mvn"
    )
)

if "%MVN_CMD%"=="" (
    echo ❌ Maven no encontrado. Por favor instala Maven o agrega la ruta al PATH.
    echo.
    echo Puedes descargar Maven desde: https://maven.apache.org/download.cgi
    echo.
    pause
    exit /b 1
)

echo ✅ Maven encontrado: %MVN_CMD%
echo.

REM Verificar que existe gato.png
if not exist "gato.png" (
    echo ⚠️ ADVERTENCIA: No se encontró gato.png en el directorio actual.
    echo El programa usará iconos por defecto.
    echo.
)

echo 🧹 Limpiando proyecto anterior...
"%MVN_CMD%" clean

echo.
echo 🔨 Compilando código fuente...
"%MVN_CMD%" compile

if %ERRORLEVEL% neq 0 (
    echo.
    echo ❌ Error en la compilación. Revisa los errores arriba.
    pause
    exit /b 1
)

echo.
echo 📦 Creando JAR ejecutable...
"%MVN_CMD%" package -DskipTests

if %ERRORLEVEL% neq 0 (
    echo.
    echo ❌ Error creando el JAR. Revisa los errores arriba.
    pause
    exit /b 1
)

echo.
echo ✅ ¡Compilación exitosa!
echo.
echo 📁 El JAR ejecutable está en: target\katlocker-1.0.0.jar
echo.
echo 🚀 Para ejecutar:
echo    java -jar target\katlocker-1.0.0.jar
echo.
echo 🐱 O usar el script: run-katlocker.bat
echo.
pause