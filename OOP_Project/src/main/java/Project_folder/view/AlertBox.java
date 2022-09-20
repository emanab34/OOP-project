//Eman Albadri
//student ID: R00196017
package Project_folder.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message) {


        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL); //set so scene before cannot be used
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> window.close()); //closes the window

        Insets inset = new Insets(10,10,10,10);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(inset);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}

