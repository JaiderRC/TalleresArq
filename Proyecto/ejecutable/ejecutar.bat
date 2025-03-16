@echo off
:: Ejecutar la aplicación
start "" javaw --module-path "../java-fx/lib" --add-modules javafx.controls,javafx.fxml -jar app.jar

exit