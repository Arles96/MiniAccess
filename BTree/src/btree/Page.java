/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author jorge
 */
public class Page {

    private Node[] Nodes;
    private Page[] Pages;
    private int nodeSize;
    private int pageSize;

    public Page(int m) {
        Pages = new Page[m];
        Nodes = new Node[m];
        this.nodeSize = 0;
        this.pageSize = 0;
    }

    public Page(int m, Node[] Nodes) {
        this.Nodes = Nodes;
        this.Pages = new Page[m];
        this.nodeSize = 0;
        this.pageSize = 0;
    }

    public Node[] getNodes() {
        return Nodes;
    }

    public void setNodes(Node[] Nodes) {
        this.Nodes = Nodes;
    }

    public Page[] getPages() {
        return Pages;
    }

    public void setPages(Page[] Pages) {
        this.Pages = Pages;
    }

    public void addNode(Node nodo) {
        try {
            Nodes[nodeSize] = nodo;
            nodeSize++;
        } catch (Exception e) {
            System.out.println("Nodes full");
        }
    }

    public void addPage(Page page) {
        try {
            Pages[pageSize] = page;
            pageSize++;
        } catch (Exception e) {
            System.out.println("Pages full");
        }
    }

    public int getNodeSize() {
        return nodeSize;
    }

    public void setNodeSize(int nodeSize) {
        this.nodeSize = nodeSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return Arrays.toString(Nodes);
    }

}
