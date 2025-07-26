@echo off
set REPO=imranrasulzade/app-kubernetes-demo
set "WORKFLOW=CI - Build & Push Docker"

set LAST_RUN_ID=

:loop
for /f "usebackq tokens=*" %%i in (`gh run list -R %REPO% --workflow "%WORKFLOW%" --limit 1 --json databaseId --jq ".[0].databaseId"`) do set RUN_ID=%%i

if not "%RUN_ID%"=="%LAST_RUN_ID%" (
    for /f "usebackq tokens=*" %%j in (`gh run view %RUN_ID% -R %REPO% --json conclusion --jq ".conclusion"`) do set STATUS=%%j
    if "%STATUS%"=="success" (
        echo ✅ CI/CD ugurla bitdi. Pod restart olunur...
        kubectl rollout restart deployment springboot-app
    )
    set LAST_RUN_ID=%RUN_ID%
)

timeout /t 30 >nul
goto loop
