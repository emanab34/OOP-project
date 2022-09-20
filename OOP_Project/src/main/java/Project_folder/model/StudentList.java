
//Eman Albadri
//student ID: R00196017
package Project_folder.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentList implements Serializable {
    private ArrayList<Student> students;

    public StudentList() {
        this.students = new ArrayList<Student>();
    }

    public void addStudent(Student student){
        this.students.add(student);
    }


    public Student getStudent(int i){
        if ((i>-1) && (i < this.students.size())){
            return this.students.get(i);
        }
        else{
            return null;
        }
    }

    public Student searchStudent(Student student){
        for(int i = 0; i<this.students.size();i++){
            if(this.students.get(i).equals(student)){
                return student;
            }
        }
        return student;
    }



    public int getSize(){return this.students.size();}

    //remove student through the student ID
    public void removeStudent(String ID) {
        for (int i = 0; i < this.students.size(); i++){
            if (getStudent(i).getStudent_id().equals(ID))
                this.students.remove(i);
        }

}
    public String getID(String ID){
        for (int i = 0; i < this.students.size(); i++){
            if (getStudent(i).getStudent_id().equals(ID))
                return getStudent(i).getStudent_id();
        }
        return null;
    }


    public ArrayList<Student> getList() {
        return this.students;
    }
    public void saveList() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("studentslist.txt"));
            {
                os.writeObject(students);
            }
            System.out.println("save");
            os.close();
        } catch (Exception ex) {
            System.out.println("could not save");
            ex.printStackTrace();
        }
    }

    public ArrayList<Student> loadAllList(){
        try
        {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("studentslist.txt"));
            students = (ArrayList<Student>) is.readObject();
            System.out.println("got here and the list has "+students.size()+" students in it\n");
            is.close() ;
            return students;
        }
        catch (Exception ex) {
            System.out.println("could not load");
            ex.printStackTrace();
        }
        return null;
    }

    public HashMap<Module, Integer> sortList(Student student){
       Student stdnt =  searchStudent(student);
       HashMap<Module, Integer> studenthm = stdnt.getModulesAndGrades();
       return stdnt.sortMap(studenthm);

    }


}


