package arbolb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author jorge
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolB b = new ArbolB(5);
        for (int i = 0; i < 17; i++) {
            b.insertar(new Nodo(i + 1));
        }
        b.imprimir();
    }

    //Insertar con búsqueda
//        ArbolB b = new ArbolB(5);
//        Pagina raiz = b.getRaiz();
//        for (int i = 0; i < 4; i++) {
//            raiz.addNodo(new Nodo((i+1) * 2));
//            raiz.addPagina(new Pagina());
//        }
//        raiz.addPagina(new Pagina());
//        b.insertar(new Nodo(0));
//        b.insertar(new Nodo(1));
//        b.insertar(new Nodo(3));
//        b.insertar(new Nodo(5));
//        b.insertar(new Nodo(7));
//        b.insertar(new Nodo(9));
//        b.imprimir();    
    //Imprimir
//        ArbolB b = new ArbolB(3);
//        Pagina r = b.getRaiz();
//        r.addNodo(new Nodo(0));
//        r.addPagina(new Pagina());
//        r.getPaginas().get(0).addNodo(new Nodo(2));
//        r.getPaginas().get(0).addPagina(new Pagina());
//        r.getPaginas().get(0).getPaginas().get(0).addNodo(new Nodo(3));
//        r.addPagina(new Pagina());
//        r.getPaginas().get(1).addNodo(new Nodo(4));
//        b.imprimir();
}
