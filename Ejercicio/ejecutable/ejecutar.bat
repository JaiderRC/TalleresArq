@echo off
setlocal

:: Ejecutar la aplicación
java --module-path "../java-fx/lib" --add-modules javafx.controls,javafx.fxml -jar app.jar

pause