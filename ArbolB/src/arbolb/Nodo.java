/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolb;

/**
 *
 * @author jorge
 */
public class Nodo {

    private int ID;
    private String Contenido;

    public Nodo() {
    }

    public Nodo(int ID) {
        this.ID = ID;
    }

    public Nodo(int ID, String Contenido) {
        this.ID = ID;
        this.Contenido = Contenido;
    }

    public int getID() {
        return ID;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }

    @Override
    public String toString() {
        return "" + ID; //+ "," + Contenido;
    }

}
