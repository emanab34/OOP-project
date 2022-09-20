package Project_folder.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ModuleList implements Serializable{
    //fields
    private ArrayList<Module> modules;

    //constructor
    public ModuleList() {
        this.modules = new ArrayList<Module>();
    }

    //getter
    public ArrayList<Module> getmoduleList() {
        return this.modules;
    }

   /* public void saveList() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("moduleslist.txt"));
            {
                os.writeObject(modules);
            }
            os.close();
        } catch (Exception ex) {
            System.out.println("could not save");
            ex.printStackTrace();
        }
    }

    public void loadAllList(){
        try
        {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("moduleslist.txt"));
            modules = (ArrayList<Module>) is.readObject();
            System.out.println("got here and the list has "+modules.size()+" cars in it\n");
            is.close() ;
        }
        catch (Exception ex) {
            System.out.println("could not load");
            ex.printStackTrace();
        }
    }*/



}


