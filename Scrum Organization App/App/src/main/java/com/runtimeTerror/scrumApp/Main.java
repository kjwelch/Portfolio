package com.runtimeTerror.scrumApp;

import com.runtimeTerror.scrumApp.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Modality;
import model.*;

import java.io.IOException;

/**
 * Main Class
 * @author Thomas Breimer, Guy Tallent, Kevin Welch
 */

public class Main extends Application {
    private MainScenesController controller;
    private Backlog backlog;
    private Sprint sprint;
    private SprintArchive archive;
    private Classroom classroom;
    private String userRole;
    private String username;
    @Override
    public void start(Stage stage) throws IOException {
        try {


            // Login Window
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/com/runtimeTerror/scrumApp/loginDialog.fxml"));
            Parent loginRoot = (Parent) loginLoader.load();
            LoginDialogController loginController = (LoginDialogController) loginLoader.getController();
            loginController.setMain(this);

            Scene loginScene = new Scene(loginRoot);
            Stage loginStage = new Stage();

            loginStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.setScene(loginScene);
            loginStage.setTitle("Login");
            loginStage.showAndWait();

            // Main Window
            this.backlog = new Backlog(0);
            this.sprint = new Sprint("Unnamed Sprint", 0);
            this.archive = new SprintArchive(0);
            this.classroom = new Classroom();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScenes.fxml"));
            Parent root = loader.load();
            controller = (MainScenesController) loader.getController();

            System.out.println("Loading information from database..");

            controller.setObjects(backlog, sprint, archive, classroom, this, userRole, username);
            controller.setUserRole(userRole);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Runtime Terror Task Manager™");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

            stage.show();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Sets the role of the user. Called by LoginDialogController upon login
     * @param role "student" or "professor"
     * @param username name of student if logged in as student
     */
    public void setRole(String role, String username) {
        this.userRole = role;
        this.username = username;
    }

    /**
     *
     * @return the user role
     */
    public String getRole() {
        return userRole;
    }

    public static void main(String[] args) {
        launch(args);
    }
}