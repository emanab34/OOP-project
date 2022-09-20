package Project_folder.model;

import java.io.Serializable;
import java.util.HashMap;

public class Module implements Serializable{
    private String module_id;
    public String module_name;

    public Module(String module_id, String module_name){
        this.module_id = module_id;
        this.module_name = module_name;
    }

    public String getModule_name(){return this.module_name;}

    public String getModule_id(){return this.module_id;}
    //use compareTo for sorting the module alphabetically

    public String toString(){
        String s = "module ID: " + getModule_id() + " module name : " + getModule_name();
        return s;
    }


}
