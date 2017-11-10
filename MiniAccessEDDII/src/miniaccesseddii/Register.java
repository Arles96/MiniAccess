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
public class Register implements Serializable {

    //Atributes
    private ArrayList<Field> fields;

    //Constructors
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

    //Administration methods
    public void addField(Field field) {
        fields.add(field);
    }

    public void removeField(int i) {
        fields.remove(i);
    }

    @Override
    public String toString() {
        String txt = "";
        
        for (Field field : fields) {
            txt += "|" + field.getContent().trim();
        }
        
        txt = ((char)txt.length()) + txt;
        
        return txt;
    }

}
