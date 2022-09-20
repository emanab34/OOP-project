module com.example.oop_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires jarnonExc;


    opens Project_folder to javafx.fxml;
    exports Project_folder;
    exports Project_folder.view;
    opens Project_folder.view to javafx.fxml;
    exports Project_folder.model;
    opens Project_folder.model to javafx.fxml;
    exports Project_folder.controller;
    opens Project_folder.controller to javafx.fxml;
}