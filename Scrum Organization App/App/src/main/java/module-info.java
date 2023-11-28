module com.runtimeTerror.scrumApp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.google.gson;
    requires java.sql;

    opens com.runtimeTerror.scrumApp to javafx.fxml;
    exports com.runtimeTerror.scrumApp;
    exports model;
    exports com.runtimeTerror.scrumApp.controllers;
    opens com.runtimeTerror.scrumApp.controllers to javafx.fxml;
}