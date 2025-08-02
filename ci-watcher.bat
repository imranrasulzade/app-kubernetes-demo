@echo off
setlocal enabledelayedexpansion

set REPO=imranrasulzade/app-kubernetes-demo
set "WORKFLOW=CI - Build & Push Docker"
set DEPLOY_FILE=deployment.yaml

set LAST_RUN_ID=

:loop
:: Son run ID-ni götür
for /f "usebackq delims=" %%i in (`gh run list -R %REPO% --workflow "%WORKFLOW%" --limit 1 --json databaseId --jq "\".[0].databaseId\""`) do (
    set RUN_ID=%%i
)

if not "%RUN_ID%"=="%LAST_RUN_ID%" (
    echo Yeni workflow tapildi: %RUN_ID%. Status yoxlanilir...

    :check_status
    for /f "usebackq delims=" %%j in (`gh run view %RUN_ID% -R %REPO% --json status --jq ".status"`) do (
        set STATUS=%%j
    )

    if "!STATUS!"=="completed" (
        for /f "usebackq delims=" %%k in (`gh run view %RUN_ID% -R %REPO% --json conclusion --jq ".conclusion"`) do (
            set CONCLUSION=%%k
        )

        if "!CONCLUSION!"=="success" (
            echo ✅ CI/CD ugurla bitdi. Deployment edilir...
            kubectl apply -f %DEPLOY_FILE%
            echo 🔄 Pod yenilənir...
            kubectl rollout restart deployment springboot-app
            echo 📡 Rollout status gözlənilir...
            kubectl rollout status deployment springboot-app
        ) else (
            echo ❌ CI/CD tamamlandi amma success deyil: !CONCLUSION!
        )
        set LAST_RUN_ID=!RUN_ID!
    ) else (
        echo Workflow hələ davam edir (!STATUS!). 10 saniyə gözlənilir...
        timeout /t 10 >nul
        goto check_status
    )
)

timeout /t 30 >nul
goto loop

pause
