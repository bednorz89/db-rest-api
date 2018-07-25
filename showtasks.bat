call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browser
.echo
echo error in runcrud.bat
goto fail

:browser
start "" http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open browser and show web
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.