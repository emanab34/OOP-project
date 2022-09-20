
//Eman Albadri
//student ID: R00196017
package Project_folder;

import Project_folder.controller.ModuleController;
import Project_folder.controller.StudentController;
import Project_folder.model.Module;
import Project_folder.model.Student;
import Project_folder.view.AlertBox;
import Project_folder.view.exit_alertbox;
import Project_folder.view.remove_alertbox;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jarFile.ClassDemo; //imported jar class
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Main extends Application {
    //tab 1 attribute:
    private Label enterStudentID, enterStudentName, enterStudentDOB;
    private TextField studentName, studentID;
    private DatePicker studentDOB;
    private Button addBtn, listBtn, removeBtn, loadbtn, savebtn, exitbtn;
    private TextArea studentDisplay;

    //tab 2 attributes
    private Label selectstudent, selectModule, grade;
    private ComboBox studentDropDownList,moduleDropDownList;
    private TextField gradeInput;
    private Button submitbtn;

    //tab 3 attributes:
    private TextArea moduleDisplay;
    private Label selectStudentt3;
    private ComboBox studentList;
    private Button submitbtntab3;
    private Button sortbtn;

    //controllers:
    private static StudentController studentController = new StudentController();
    private static ModuleController moduleController = new ModuleController();

    //jar class:
    private ClassDemo newclass = new ClassDemo("schedule1","term 2", "T1.2");




    public void start(Stage primaryStage){
        enterStudentID = new Label("Enter Student ID:");
        studentID = new TextField();
        enterStudentName = new Label("Enter Student Name:");
        studentName = new TextField();
        enterStudentDOB = new Label("Enter Student Date of birth:");
        studentDOB = new DatePicker();
        studentDisplay = new TextArea("All registered students");
        addBtn = new Button("Add");
        removeBtn = new Button("Remove");
        listBtn = new Button("List all students");
        loadbtn = new Button("Load");
        savebtn = new Button("Save");
        exitbtn = new Button("Exit");

        //design of tab 1 elements:
        enterStudentID.setFont(Font.font("Times New Roman",18));
        enterStudentID.setTextFill(Color.DARKCYAN);
        enterStudentName.setFont(Font.font("Times New Roman",18));
        enterStudentName.setTextFill(Color.DARKCYAN);
        enterStudentDOB.setFont(Font.font("Times New Roman",18));
        enterStudentDOB.setTextFill(Color.DARKCYAN);
        studentDisplay.setFont(Font.font("Times New Roman",18));
        addBtn.setFont(Font.font("Times New Roman",15));
        addBtn.setTextFill(Color.DARKSLATEGREY);
        removeBtn.setFont(Font.font("Times New Roman",15));
        removeBtn.setTextFill(Color.DARKSLATEGREY);
        listBtn.setFont(Font.font("Times New Roman",15));
        listBtn.setTextFill(Color.DARKSLATEGREY);
        loadbtn.setFont(Font.font("Times New Roman",15));
        loadbtn.setTextFill(Color.DARKSLATEGREY);
        savebtn.setFont(Font.font("Times New Roman",15));
        savebtn.setTextFill(Color.DARKSLATEGREY);
        exitbtn.setFont(Font.font("Times New Roman",15));
        exitbtn.setTextFill(Color.DARKSLATEGREY);



        //load from file on showing
        primaryStage.setOnShowing(e -> studentController.loadSerializable());

        //creation of new tabs
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab();
        Tab tab2 = new Tab();
        Tab tab3 = new Tab();

        //naming tabs
        tab1.setText("Insert Student");
        tab2.setText("Insert Grade");
        tab3.setText("View Modules");

        //on selecting tabs; the file is loaded, and on unselecting the objects are saved and serialised; so that information is consistent and updated throughout the tabPane
        tab1.setOnSelectionChanged(new EventHandler<Event>() {
                                       @Override
                                       public void handle(Event event) {
                                           if(tab1.isSelected()){

                                               System.out.println("Tab1 selected");
                                               //when add button is invoked either a new student is added to the list or an error message pops up
                                               addBtn.setOnAction(new EventHandler<ActionEvent>() {
                                                                      @Override
                                                                      public void handle(ActionEvent actionEvent) {
                                                                          //if any of the fields are empty show alertbox:
                                                                          if (studentID.getText().isEmpty() || studentName.getText().isEmpty()|| studentDOB.getValue() == null){
                                                                              AlertBox.display("Warning","You have not entered sufficient student details");
                                                                          }
                                                                          //else add student
                                                                          else {
                                                                              if(Pattern.matches("[R]\\d\\d\\d\\d\\d\\d", studentID.getText()) && Pattern.matches("[a-zA-Z]*\\s[a-zA-Z]*", studentName.getText())) {
                                                                                  studentController.addStudentToList(studentID.getText(), studentName.getText(), studentDOB.getValue());
                                                                              }
                                                                              else AlertBox.display("Warning","Invalid user input");
                                                                          }
                                                                      }
                                                                  }

                                               );

                                               //remove button to remove a student from the list
                                               removeBtn.setOnAction(new EventHandler<ActionEvent>() {
                                                                         @Override
                                                                         public void handle(ActionEvent actionEvent) {
                                                                             //if the fields are empty show alertbox
                                                                             if (studentID.getText().isEmpty() && studentName.getText().isEmpty()&& studentDOB.getValue()== null ){
                                                                                 AlertBox.display("Warning","You have not entered sufficient student details");
                                                                             }
                                                                             //if there are no students in the list
                                                                             else if (studentController.getSize() == 0){
                                                                                 AlertBox.display("Warning", "There are no registered students to remove");
                                                                             }
                                                                             //if the id does not exist
                                                                             else if(studentController.searchID(studentID.getText())==null){
                                                                                 AlertBox.display("Warning", "Student does not exist");
                                                                             }
                                                                             //using the ID only, the student is removed
                                                                             else{
                                                                                 remove_alertbox.display("Remove Student", "Are you sure you want to remove student?",studentID.getText(),studentController);
                                                                                 //display with edited information
                                                                             }
                                                                             String all_students = studentController.getAllStudents();
                                                                             studentDisplay.setText(all_students);
                                                                         }
                                                                     }

                                               );

                                               //list all students in the list
                                               listBtn.setOnAction(new EventHandler<ActionEvent>() {
                                                                       @Override
                                                                       public void handle(ActionEvent actionEvent) {
                                                                           String all_students = studentController.getAllStudents();
                                                                           studentDisplay.setText(all_students);
                                                                       }
                                                                   }

                                               );
                                               // exit button to save or not save work before termination
                                               exitbtn.setOnAction(new EventHandler<ActionEvent>() {
                                                   @Override
                                                   public void handle(ActionEvent actionEvent) {
                                                       exit_alertbox.display("Exit", "Do you want to save your work before exit?", primaryStage,studentController);
                                                   }
                                               });
                                               //save button to save work to text file on click
                                               savebtn.setOnAction(e -> studentController.saveSerializable());
                                               //load button to load from text file the list of students
                                               loadbtn.setOnAction(e -> studentController.loadSerializable());
                                           }
                                           else {
                                               //save on unselect
                                               studentController.saveSerializable();
                                               System.out.println("Tab1 unselected");
                                           }
                                       }
                                   }

        );


        //design of tab one :
        BorderPane bp = new BorderPane();
        Insets insetV1 = new Insets(20, 20, 20, 20);
        Insets btnInset = new Insets(10,10,10,10);
        HBox ID = new HBox(10);
        ID.getChildren().addAll(enterStudentID, studentID);
        HBox name = new HBox(10);
        name.getChildren().addAll(enterStudentName, studentName);
        HBox DOB = new HBox(10);
        DOB.getChildren().addAll(enterStudentDOB, studentDOB);
        VBox v1 = new VBox(10);
        v1.getChildren().addAll(ID,name,DOB);
        v1.setPadding(insetV1);
        HBox btnRow = new HBox(10);
        btnRow.getChildren().addAll(addBtn, listBtn, removeBtn);
        btnRow.setPadding(btnInset);
        HBox bottombtnRow = new HBox(10);
        bottombtnRow.getChildren().addAll(loadbtn, savebtn, exitbtn);
        bottombtnRow.setPadding(btnInset);
        bp.setRight(bottombtnRow);
        VBox v2 = new VBox(10);
        v2.getChildren().addAll(v1, btnRow, studentDisplay, bp);

        //set content of first tab to registering students (v2)
        tab1.setContent(v2);


        //gridpane as layout for tab:
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        selectstudent = new Label("Select Student:");
        selectModule = new Label("Select Module:");
        grade = new Label("Input Grade:");
        gradeInput = new TextField();
        submitbtn = new Button("Submit");

        //design of tab 2
        selectstudent.setFont(Font.font("Times New Roman",18));
        selectstudent.setTextFill(Color.DARKCYAN);
        selectModule.setFont(Font.font("Times New Roman",18));
        selectModule.setTextFill(Color.DARKCYAN);
        grade.setFont(Font.font("Times New Roman",18));
        grade.setTextFill(Color.DARKCYAN);
        submitbtn.setFont(Font.font("Times New Roman",15));
        submitbtn.setTextFill(Color.DARKSLATEGREY);

        //add all attributes to gridpane
        gp.add(selectstudent, 0,1);
        gp.add(selectModule,0,2);
        gp.add(grade, 0,3);
        gp.add(gradeInput,1,3);
        gp.add(submitbtn,1,4);
        gp.setPadding(insetV1);
        tab2.setContent(gp);


        tab2.setOnSelectionChanged(new EventHandler<Event>() {
                                       @Override
                                       public void handle(Event event) {
                                           if(tab2.isSelected()){
                                              // studentController.loadSerializable();
                                               System.out.println("Tab2 selected");
                                               //tab 2:
                                               //drop down list cast as observable list to display the students registered from the text file
                                               studentDropDownList = new ComboBox(FXCollections.observableArrayList(studentController.loadSerializable()));
                                               moduleDropDownList = new ComboBox(FXCollections.observableArrayList(moduleController.loadFromFile(moduleController.getModule_list().getmoduleList())));
                                               gp.add(studentDropDownList, 1, 1);
                                               gp.add(moduleDropDownList, 1,2);

                                               //on pressing the submit button will submit the grade into the hashmap
                                               submitbtn.setOnAction(new EventHandler<ActionEvent>() {
                                                   @Override
                                                   public void handle(ActionEvent actionEvent) {
                                                       //if none of the elements are empty or not selected:
                                                       if (!studentDropDownList.getSelectionModel().isEmpty() || !moduleDropDownList.getSelectionModel().isEmpty()   || !gradeInput.getText().isEmpty()){
                                                           Module selectedModule = (Module) moduleDropDownList.getSelectionModel().getSelectedItem();
                                                           Student selectedStudent = (Student) studentDropDownList.getSelectionModel().getSelectedItem();
                                                           if(Pattern.matches("\\d*", gradeInput.getText())){
                                                           //convert gradeinput to int
                                                           studentController.addModuleAndGrade(selectedModule,parseInt(gradeInput.getText()),selectedStudent);
                                                           System.out.println(studentController.getModulesandGrades(selectedStudent));
                                                           AlertBox.display("Success!", "Grade inserted successfully");
                                                       }
                                                       else AlertBox.display("Warning","Invalid grade input");
                                                       }
                                                       else {
                                                           AlertBox.display("Warning", "Please select a student and module and input grade");
                                                       }
                                                   }
                                               });

                                           }
                                           else {
                                               studentController.saveSerializable();
                                               System.out.println("Tab2 unselected");
                                           }
                                       }
                                   }

        );





        //tab3:
        GridPane tab3gp = new GridPane();
        tab3gp.setVgap(10);
        tab3gp.setHgap(10);
        submitbtntab3 = new Button("Submit");
        selectStudentt3 = new Label("Select Student to view modules and grade:");
        moduleDisplay = new TextArea("All registered Modules");
        sortbtn = new Button("Sort");


        //design of tab3
        selectStudentt3.setFont(Font.font("Times New Roman",18));
        selectStudentt3.setTextFill(Color.DARKCYAN);
        moduleDisplay.setFont(Font.font("Times New Roman", 15));
        submitbtntab3.setFont(Font.font("Times New Roman",15));
        submitbtntab3.setTextFill(Color.DARKSLATEGREY);
        sortbtn.setFont(Font.font("Times New Roman",15));
        sortbtn.setTextFill(Color.DARKSLATEGREY);
        //add to gridpane
        tab3gp.add(selectStudentt3,0,1);
        tab3gp.add(moduleDisplay, 0,3);
        HBox btnrow = new HBox(10);
        btnrow.getChildren().addAll(submitbtntab3,sortbtn);
        tab3gp.add(btnrow, 0,4);
        tab3gp.setPadding(insetV1);
        tab3.setContent(tab3gp);



        //merge 3 tabs into the tabpane
        tabPane.getTabs().addAll(tab1,tab2,tab3);

        tab3.setOnSelectionChanged(new EventHandler<Event>() {
                                       @Override
                                       public void handle(Event event) {
                                           if(tab3.isSelected()){
                                              // studentController.loadSerializable();
                                               studentList = new ComboBox(FXCollections.observableArrayList(studentController.loadSerializable()));
                                               tab3gp.add(studentList,0,2);
                                               System.out.println("Tab3 selected");
                                               //sort list
                                               sortbtn.setOnAction(new EventHandler<ActionEvent>() {
                                                   @Override
                                                   public void handle(ActionEvent actionEvent) {
                                                       HashMap<Module, Integer> newList = studentController.sortModulesandGrade((Student) studentList.getSelectionModel().getSelectedItem());
                                                       String modules = "";
                                                       for (Map.Entry<Module, Integer> entry : newList.entrySet()) {
                                                           Module key = entry.getKey();
                                                           Integer value = entry.getValue();
                                                           modules += key + " Grade: " + value + "\n";
                                                       }
                                                       moduleDisplay.setText(modules);
                                                   }
                                               });
                                               //submit button to show all modules and its grade in the text area
                                               submitbtntab3.setOnAction(new EventHandler<ActionEvent>() {
                                                   @Override
                                                   public void handle(ActionEvent actionEvent) {

                                                       //if the combobox is not empty and a student has been selected:
                                                       if (!studentList.getSelectionModel().isEmpty()){

                                                           Student selectedStudent = (Student) studentList.getSelectionModel().getSelectedItem();

                                                           HashMap<Module, Integer> allmodulesandgrade = studentController.getModulesandGrades(selectedStudent);
                                                           System.out.println(allmodulesandgrade);
                                                           String modules = "";
                                                           //if the student has an empty map of modules
                                                           if (allmodulesandgrade.isEmpty()){
                                                               moduleDisplay.setText("No modules registered");
                                                           }
                                                           else{
                                                               //map iteration through its elements and return a string with module ID, name and grade
                                                               for (Map.Entry<Module, Integer> entry : allmodulesandgrade.entrySet()) {
                                                                   Module key = entry.getKey();
                                                                   Integer value = entry.getValue();
                                                                   modules += key + " Grade: " + value + "\n";
                                                               }
                                                               moduleDisplay.setText(modules);}

                                                       }
                                                       else {
                                                           AlertBox.display("Warning", "Please select a student to view module details");
                                                       }
                                                   }
                                               });


                                           }
                                           else {
                                               studentController.saveSerializable();
                                               System.out.println("Tab3 unselected");
                                           }
                                       }
                                   }

        );



        Scene scene = new Scene(tabPane, 600, 500);

        primaryStage.setTitle("MTU Student Record System");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}


