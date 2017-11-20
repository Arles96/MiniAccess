/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

import java.util.Scanner;

/**
 *
 * @author Arles Cerrato
 */
public class Btree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //testPage();
        testBtree();
    }
    
    public static void testPage(){
        Scanner read = new Scanner(System.in);
        Page page = new Page(5);
        int counter = 0;
        while (counter < 5){
            System.out.print("Ingrese un numero: ");
            int number = read.nextInt();
            page.addNode(new Node(number));
            for (int i = 0; i < page.getCounterNode(); i++) {
                System.out.println(page.getNodes()[i].getKey());
            }
            counter++;
        }
        System.out.println("Fin");
        for (int i = 0; i < page.getCounterNode(); i++) {
                System.out.println(page.getNodes()[i].getKey());
        }
    }
    
    public static void testBtree(){
        /*Scanner read = new Scanner(System.in);
        TreeB tree = new TreeB(5);
        String answer = "s";
        while (answer.equalsIgnoreCase("s")){
            System.out.println("Menu Principal\n");
            System.out.println("1.- Agregar al Arbol B.");
            System.out.println("2.- Imprimir el Arbol B.");
            System.out.print("Ingrese una opcion: ");
            String option = read.next();
            if (option.equals("1")) {
                System.out.print("Ingrese un numero: ");
                int number = read.nextInt();
                tree.add(number);
                System.out.println("Se ha ingresado el numero con exito.");
            }else {
                System.out.println("Impresion del arbol.");
                tree.print();
            }
            System.out.print("Desea continuar [s/n]: ");
            answer = read.next();
        }*/
        TreeB tree = new TreeB(5);
        tree.add(5);
        tree.add(4);
        tree.add(1);
        tree.add(6);
        tree.add(7);
        tree.add(8);
        tree.add(15);
        tree.add(12);
        tree.add(20);
        tree.add(25);
        tree.add(30);
        tree.add(31);
        tree.add(40);
        tree.add(50);
        tree.add(45);
        tree.add(35);
        tree.add(51);
        tree.print();
    }
    
}
