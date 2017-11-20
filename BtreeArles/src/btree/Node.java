/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

/**
 *
 * @author Arles Cerrato
 */
public class Node {
    
    //Atributes
    
    private int key;

    // Contructor
    
    public Node(int key) {
        this.key = key;
    }
    
    //Getter and setter
    
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
    
}
