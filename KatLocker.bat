@echo off
title KatLocker
echo.
echo ============================================
echo    KatLocker - Bloqueo de Teclado y Mouse
echo ============================================
echo.
echo Iniciando KatLocker...
echo Revisa el icono en la bandeja del sistema
echo.

cd /d "%~dp0"
start /B javaw -jar target\katlocker-1.0.0.jar

echo.
echo KatLocker se esta ejecutando en segundo plano
echo Busca el icono del candado en la bandeja del sistema
echo.
echo Presiona cualquier tecla para cerrar esta ventana...
pause >nul
