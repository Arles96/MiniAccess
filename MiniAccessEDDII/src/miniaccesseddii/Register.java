/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaccesseddii;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Arles Cerrato
 */
public class Register implements Serializable{
    
    //Atributes
    
    private ArrayList<Field> fields;
    private Field field;

    //Constructors
    
    public Register(Field field) {
        this.fields = new ArrayList();
        this.field = field;
    }

    public Register() {
        this.fields = new ArrayList();
    }
    
    //Getter and Setter

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
    
    //Administration methods
    
    public void addField(Field field){
        fields.add(field);
    }  
    
    public void removeField(int i){
        fields.remove(i);
    }
    
    
}
