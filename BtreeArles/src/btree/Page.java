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
public class Page {
    
    //Atributes
    
    private Page father;
    private int size;
    private Node nodes [];
    private int counterNode = 0;
    private int counterPage = 0;
    private Page pages [];
    
    //Constructor

    public Page(int size) {
        this.size = size;
        nodes = new Node [this.size-1];
        pages = new Page[this.size];
    }
    
    //Getter eand setter

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
        counterNode = 0;
        for (Node node : this.nodes) {
            if (node != null) {
                //System.out.println(node.getKey());
                counterNode++;
            }
        }
    }

    public int getCounterNode() {
        return counterNode;
    }

    public void setCounterNode(int counterNode) {
        this.counterNode = counterNode;
    }

    public Page[] getPages() {
        return pages;
    }

    public void setPages(Page[] pages) {
        this.pages = pages;
    }

    public Page getFather() {
        return father;
    }

    public void setFather(Page father) {
        this.father = father;
    }

    public int getCounterPage() {
        return counterPage;
    }

    public void setCounterPage(int counterPage) {
        this.counterPage = counterPage;
    }
    
    //private methods
    
    private void BubbleSort(){
        for (int i = 0; i < counterNode; i++) {
            for (int j = i+1; j < counterNode; j++) {
                if (nodes[i].getKey()>nodes[j].getKey()) {
                    Node aux = nodes[j];
                    nodes[j] = nodes [i];
                    nodes[i] = aux;
                }
            }
        }
    }
    

    //Admisnitracion methods
    
    public void addNode(Node node){
        if (counterNode < size-1) {
            nodes[counterNode] = node;
            counterNode++;
            if (counterNode>1) {
                BubbleSort();
            }
        }
    }
    
    public boolean isFull(){
        return counterNode==size-1;
    }
    
    public boolean isFather(){
        return counterPage>0;
    }
    
    public Node[] splitNodeLeft(){
        Node [] nodeLeft = new Node[size-1];
        if (size%2==1) {
            int length = (this.size-1)/2;
            for (int i = 0; i < length; i++) {
                //System.out.println(nodes[i].getKey());
                nodeLeft[i] = nodes[i];
            }
        }else {
            int length = (this.size/2)-1;
            System.arraycopy(nodes, 0, nodeLeft, 0, length);
        }
        return nodeLeft;
    }
    
    public Node[] splitNodeRight(){
        Node [] nodesRight = new Node[this.size-1];
        if (size%2==1) {
            int begin = (this.size/2)+2;
            int counter = 0;
            for (int i = begin-1 ; i < this.size-1; i++) {
                //System.out.println(nodes[i].getKey());
                nodesRight[counter] = nodes[i];
                counter++;
            }
        }else{
            int length = (this.size/2)+1;
            System.arraycopy(nodes, length-1, nodesRight, 0, length);
        }
        return nodesRight;
    }
    
    public Node center(){
        if (size%2==1) {
            int i = ((this.size-1)/2);
            //System.out.println(i);
            //System.out.println(nodes[i].getKey());
            return nodes[i];
        }else {
            int i = (this.size/2)-1;
            return nodes[i];
        }
    }
    
    public void addPage (Page page){
        if (counterPage<size) {
            pages[counterPage] = page;
            counterPage++;
        }
    }
    
    public Node getNode(int i){
        if (i>=0 && i<counterNode) {
            return nodes[i];
        }else {
            return null;
        }
    }
    
    public Page getPage(int i){
        if (i>=0 && i<counterPage) {
            return pages[i];
        }else {
            return null;
        }
    }
    
    public void setPage(int i, Page p){
        if (i>0 && i<counterPage) {
           pages[i] = p;
        }
    }
    
    public void print(){
        //System.out.println(counterNode);
        for (int i = 0; i < counterNode; i++) {
            //if (nodes[i]!=null) {
                System.out.print(nodes[i].getKey() + ",");
            //}
        }
        System.out.println("");
    }
    
}
