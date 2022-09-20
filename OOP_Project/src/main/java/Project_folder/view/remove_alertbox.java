//Eman Albadri
//student ID: R00196017
package Project_folder.view;

import Project_folder.controller.StudentController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class remove_alertbox  {
   // private static StudentController studentController = new StudentController();
    public static void display(String title, String message, String id, StudentController studentController){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("yes");

        //on click 'yes' button the a student is removed using the student controller
        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                studentController.removeStudentFromList(studentController.searchID(id));
                window.close();

            }
        });
        Insets inset = new Insets(10,10,10,10);
        Button noButton = new Button("no");

        //no button only closes the new scene
        noButton.setOnAction(e -> window.close());
        HBox btnlayout = new HBox(10);
        VBox layout = new VBox(10);
        btnlayout.getChildren().addAll(yesButton, noButton);
        btnlayout.setPadding(inset);
        layout.getChildren().addAll(label,btnlayout);
        layout.setPadding(inset);
        btnlayout.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
