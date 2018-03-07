package PIDTuner;

import java.io.Serializable;

public class DataObject implements Serializable{
    String name, data;
    private static final long serialVersionUID = 9277L;

    public DataObject(String name, String data){
        this.name = name;
        this.data = data;
    }

    public String getName(){
        return name;
    }

    public String getData(){
        return data;
    }
}
