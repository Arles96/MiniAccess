/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public class BTree {

    ArrayList<Node> Nodes = new ArrayList();
    ArrayList<Page> Pages = new ArrayList();

    public BTree() {
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

    public static void printTree(Page Root) {

    }

}
