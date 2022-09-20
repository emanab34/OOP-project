//Eman Albadri
//student ID: R00196017
package Project_folder.controller;

import Project_folder.model.Module;
import Project_folder.model.StudentList;
import Project_folder.model.Student;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentController {
    //fields:
    private StudentList Student_list;

    //constructor:
    public StudentController(){
        Student_list = new StudentList();
    }

    //getter:
    public StudentList getStudent_list() {return Student_list;}

    //add a new student into the list
    public void addStudentToList(String sName, String sID, LocalDate DOB){
        Student s = new Student(sName, sID, DOB);
        Student_list.addStudent(s);
    }

    //remove student from list using ID
    public void removeStudentFromList(String ID){
        //find the student with matching id from arraylist and then remove them
            this.Student_list.removeStudent(ID);
    }

    //display all students in the array as string
    public String getAllStudents(){
        //for loop to show students
        String students="\0";
        if (Student_list.getSize() == 0){
            return "No students registered";
        }
        for(int i = 0; i <= Student_list.getSize() - 1 ; i++){
            students = students+Student_list.getStudent(i)+"\n";
        }
        return students;
    }

    //search for id in the student_list
    public String searchID(String id){
        return Student_list.getID(id);
    }

    //get size of student list
    public int getSize(){
        return Student_list.getSize();
    }

    //save updated student list to file
    public void saveToFile() {
        try {
            PrintWriter output = new PrintWriter("students.txt");
            ArrayList<Student> iterableList = Student_list.getList();
            for (Student stdnt : iterableList)
                output.println(stdnt.getStudent_id() + "," + stdnt.getName()+","+stdnt.getDate_of_birth());
            output.close();

            System.out.println("File saved successfully");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    //load student list from an existing file
    public ArrayList<Student> loadFromFile(ArrayList<Student> studentList) {
        try {
            FileReader fileReader = new FileReader("students.txt");
            BufferedReader inputFile = new BufferedReader(fileReader);
            String line = inputFile.readLine();
            studentList.clear();
            while (line != null) {
                String[] data = line.split(",");
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                studentList.add(new Student(data[0], data[1], LocalDate.parse(data[2],format))); //change text data into localdate
                line = inputFile.readLine();
            }
            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    //add module and module grade for a specific student
    public void addModuleAndGrade(Module module, int grade, Student student){
        //loop through student list and check for a student equal to the required student
            Student_list.searchStudent(student).addModuleandGrade(module, grade);

        }

    //get the Map of all the modules and its grade for a specific student
    public HashMap<Module, Integer> getModulesandGrades(Student student)
    { //search for student
        Student reqStudent = this.Student_list.searchStudent(student);
        HashMap<Module, Integer> module_list = reqStudent.getModulesAndGrades();

        return module_list;
    }

    public void saveSerializable() {Student_list.saveList();}
    public ArrayList<Student> loadSerializable(){return Student_list.loadAllList();}
    public HashMap<Module, Integer> sortModulesandGrade(Student student){return Student_list.sortList(student);}



}
