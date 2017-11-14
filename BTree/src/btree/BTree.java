/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jorge
 */
public class BTree {

    private int Grade;

    private Page Root;
    private ArrayList<Node> Nodes = new ArrayList();
    private ArrayList<Page> Pages = new ArrayList();

    public BTree(int Grade) {
        Root = new Page(Grade);
        this.Grade = Grade;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int Grade) {
        this.Grade = Grade;
    }

    public Page getRoot() {
        return Root;
    }

    public void setRoot(Page Root) {
        this.Root = Root;
    }

    public ArrayList<Node> getNodes() {
        return Nodes;
    }

    public void setNodes(ArrayList<Node> Nodes) {
        this.Nodes = Nodes;
    }

    public ArrayList<Page> getPages() {
        return Pages;
    }

    public void setPages(ArrayList<Page> Pages) {
        this.Pages = Pages;
    }

    public void addPage(Page newPage) {
        Pages.add(newPage);
    }

    public void insert(Node node) {
        if (Root.getPageSize()==0) {
            
        }
    }

    public static void printTree(Page root, int nivel) {
        for (int i = 0; i < nivel; i++) {
            System.out.print("---");
        }
        System.out.println(root);
        for (int i = 0; i < root.getPageSize(); i++) {
            printTree(root.getPages()[i], nivel + 1);
        }
    }

}
