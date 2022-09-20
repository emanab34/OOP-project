package Project_folder.controller;

import Project_folder.model.Module;
import Project_folder.model.ModuleList;
import Project_folder.model.Student;
import Project_folder.model.StudentList;

import java.io.*;
import java.util.ArrayList;

public class ModuleController {
    private ModuleList module_list;

    public ModuleController(){
        module_list = new ModuleList();
    }

    public ModuleList getModule_list() {return this.module_list;}

    //modules are hardcoded and saved in a text file, future improvements may be allowed for addition of modules through main.
    public ArrayList<Module> loadFromFile(ArrayList<Module> modulelist) {
        try {
            FileReader fileReader = new FileReader("modules.txt");
            BufferedReader inputFile = new BufferedReader(fileReader);
            String line = inputFile.readLine();
            modulelist.clear();
            while (line != null) {
                String[] data = line.split(",");
                modulelist.add(new Module(data[0], data[1]));
                line = inputFile.readLine();
            }
            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modulelist;
    }


   // public void sortList(){module_list.sortList();}


}
