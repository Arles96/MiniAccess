/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
        if (Root.getNodeSize() == 0) {
            Root.addNode(node);
        } else {
            recursiveInsert(Root, node);
        }
    }

    private Page recursiveInsert(Page page, Node node) {
        Page downNode = null;
        Page upNode = null;
        boolean left = false;//En caso de que no encuentre el ID que sea menor a uno de los nodos de la página.
        //Busca por los nodos del árbol para encontrar a qué lugar pertenece el nodo a insertar.
        for (int i = 0; i < page.getNodeSize(); i++) {
            if (node.getID() > page.getNodes()[i].getID()) {
                if (page.getPageSize() == 0) {
                    page.addNode(node);
                } else {
                    downNode = recursiveInsert(page.getPages()[i], node);
                }
                left = true;
                break;
            }
        }
        //En caso de que no encuentre el ID
        //que sea menor a uno de los nodos de la página.
        if (!left) {
            if (page.getPageSize() == 0) {
                page.addNode(node);
            } else {
                downNode = recursiveInsert(page.getPages()[page.getPageSize()], node);
            }
        }
        //Agregar el nodo de la página
        if (downNode != null) {
            page.addNode(downNode.getNodes()[0]);
            page.getPages()[page.getPageSize() - 1] = downNode.getPages()[0];
            page.getPages()[page.getPageSize()] = downNode.getPages()[1];
        }
        //Ordenar la página en caso de que se pase
        if (page.getNodeSize() == Grade) {

        }
        return upNode;
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
