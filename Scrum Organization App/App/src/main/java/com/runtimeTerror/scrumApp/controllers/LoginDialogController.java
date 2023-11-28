package com.runtimeTerror.scrumApp.controllers;

import com.runtimeTerror.scrumApp.Main;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controller for the login dialog
 *
 * @author Thomas Breimer
 * @version 5/29/23
 */
public class LoginDialogController {

    public TextField usernameField;
    private Main mainApp;
    public void setMain(Main main) {
        this.mainApp = main;
    }

    /**
     * Called when login as prof is clicked
     * @param mouseEvent
     */
    public void loginAsProf(MouseEvent mouseEvent) {
        mainApp.setRole("professor", "");
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    /**
     * Called when login as student is clicked
     * @param mouseEvent
     */
    public void loginAsStudent(MouseEvent mouseEvent) {
        mainApp.setRole("student", usernameField.getText());
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
}
