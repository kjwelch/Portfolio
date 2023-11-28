package com.runtimeTerror.scrumApp.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controller for the add student dialog
 *
 * @author Thomas Breimer
 * @version 5/29/23
 */

public class AddStudentDialogController {


    public TextField nameField;
    public TextField idField;
    public Button addButton;
    public Button cancelButton;

    private GroupController groupController;

    private int groupPosition;

    /**
     * Sets the group controller
     * @param groupController the group controller
     */
    public void setMainController(GroupController groupController, int groupPosition) {
        this.groupController = groupController;
        this.groupPosition = groupPosition;
    }

    /**
     * Calls the group controller to add a new student when the add student button is clicked
     * @param mouseEvent
     */
    public void addNewStudent(MouseEvent mouseEvent) {
        groupController.addNewStudent(nameField.getText(), idField.getText(), groupPosition);
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the window when the cancel button is clicked
     * @param mouseEvent
     */
    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
