/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajtable;

import java.util.ArrayList;

/**
 *
 * @author Arles Cerrato
 */
public class Registro {
    
    private ArrayList<Campos> campos = new ArrayList();

    public Registro() {
    }

    public ArrayList<Campos> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<Campos> campos) {
        this.campos = campos;
    }
    
    // Metodos de administracion
    
    public void agregarCampo (Campos c){
        campos.add(c);
    }
    
    public void eliminarCampo(int i){
        campos.remove(i);
    }
    
    public Campos getCampo(int i){
        return campos.get(i);
    }
    
}
