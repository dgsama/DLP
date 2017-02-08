cls
cd %~dp0
cd src
java -cp ..\tools\jflex\JFlex.jar JFlex.Main -d scanner scanner\scanner.jflex
pause