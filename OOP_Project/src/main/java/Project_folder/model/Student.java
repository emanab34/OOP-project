//Eman Albadri
//student ID: R00196017
package Project_folder.model;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Student implements Serializable {
    //fields:
    private String name;
    private String student_id;
    private LocalDate date_of_birth;
    //hashmap to represent the module object and the grade they have gotten in a module
    private HashMap<Module, Integer> modulesAndGrades = new HashMap<Module, Integer>();

    //constuctor:
    public Student(String student_id, String name, LocalDate date_of_birth) {
        this.name = name;
        this.student_id = student_id;
        this.date_of_birth = date_of_birth;
    }


    //getters:
    public String getName() {
        return name;
    }


    public String getStudent_id() {
        return student_id;
    }


    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    //toString overridden to display objects in a readable manner
    public String toString(){
        String s = "Student ID: " + getStudent_id() + " name : " + getName() + " date of birth: "+ getDate_of_birth();
        return s;
    }

    //get module and grades hashmap
    public HashMap<Module, Integer> getModulesAndGrades() {
        return this.modulesAndGrades;
    }

    //add module and grade to the hashmap
    public void addModuleandGrade(Module module, int grade){
        this.modulesAndGrades.put(module, grade);
    }

    //sort hashmap:
    public HashMap<Module, Integer> sortMap(HashMap<Module, Integer> modulesandgrades){
        //convert from hashmap to a linked list
        List<Map.Entry <Module, Integer>> newList = new LinkedList<Map.Entry<Module,Integer>>(modulesandgrades.entrySet());

        //sort
        Collections.sort(newList, new Comparator<Map.Entry<Module, Integer>>() {
            @Override
            public int compare(Map.Entry<Module, Integer> o1, Map.Entry<Module, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<Module, Integer> newhm = new LinkedHashMap<Module, Integer>();
        for(Map.Entry<Module,Integer> value : newList){
            newhm.put(value.getKey(), value.getValue());
        }
        return newhm;
    }


}

//there are no setters in the Student class to ensure that student can only be set through the GUI