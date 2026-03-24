@echo off
setlocal enabledelayedexpansion

REM Compile
echo Compiling Chat Application...
javac -encoding UTF-8 ChatServer.java ChatClient.java 2>&1

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Choose an option:
echo 1. Run Chat Server
echo 2. Run Chat Client
echo 3. Exit
echo.

set /p choice="Enter choice (1-3): "

if "%choice%"=="1" (
    echo.
    echo === STARTING CHAT SERVER ===
    echo.
    java ChatServer
) else if "%choice%"=="2" (
    echo.
    echo === STARTING CHAT CLIENT ===
    echo.
    java ChatClient
) else (
    echo Goodbye!
)

pause
