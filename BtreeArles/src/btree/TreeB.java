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
public class TreeB {
    
    //Atributes
    
    private Page root;
    private int grade;
    
    //Constructor

    public TreeB(int grade) {
        this.grade = grade;
    }
    
    //Getter and setter

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Page getRoot() {
        return root;
    }
    
    //private methods
    
    private void addKey(Page r, int key){
        if (r.isFather()) {
            if (r.getCounterNode()==1) {
                if (r.getNode(0).getKey()>key) {
                    addKey(r.getPage(0), key);
                }else {
                    addKey(r.getPage(1), key);
                }
            }else {
                for (int i = 0; i < r.getCounterNode(); i++) {
                    if (i==0) {
                        if (key<r.getNode(i).getKey()) {
                            addKey(r.getPage(i), key);
                            break;
                        }
                        if (key>r.getNode(i).getKey() && key<r.getNode(i+1).getKey()) {
                            addKey(r.getPage(i+1), key);
                            break;
                        }
                    }else if (i==r.getCounterNode()-1) {
                        if (key>r.getNode(i).getKey()) {
                            addKey(r.getPage(i+1), key);
                            break;
                        }else {
                            addKey(r.getPage(i), key);
                            break;
                        }
                    }else {
                        if (key>r.getNode(i).getKey() && key<r.getNode(i+1).getKey()) {
                             addKey(r.getPage(i+1), key);
                        }
                    }
                }
            }
        }else {
            if (r.isFull()) {
                splitFather(r, key);
            }else {
                r.addNode(new Node(key));
            }
        }
    }
    
    private void splitFather(Page r, int key){
        //TODO: Error de Nullpointer Exception al momento de ingresar 7 numeros
        if (r.getFather().isFull()) {
            splitFather(r.getFather(), key);
        }else {
            Node [] nodeLeft = r.splitNodeLeft();
            Node [] nodeRight = r.splitNodeRight();
            Node center = r.center();
            r.getFather().addNode(center);
            Page pageLeft = new Page(grade);
            Page pageRight = new Page(grade);
            pageLeft.setNodes(nodeLeft);
            pageRight.setNodes(nodeRight);
            r.getFather().setPage(key, pageLeft);
            r.getFather().addPage(pageRight);
            addKey(r, key);
        }
    }
    
    private void print(Page r){
        r.print();
        if (r.isFather()) {
            for (int i = 0; i < r.getCounterPage(); i++) {
                print(r.getPage(i));
            }
        }
    }
    
    //Administation methods
    
    public void add(int key){
        if (root==null) {
            root = new Page(grade);
            root.addNode(new Node(key));
        }else{
            if (root.isFull()) {
                //TODO: Error en este codigo
                Node [] left = root.splitNodeLeft();
                Node [] right = root.splitNodeRight();
                for (int i = 0; i < right.length; i++) {
                    if (right[i]!=null) {
                        System.out.println(right[i].getKey());
                    }
                }
                Node center = root.center();
                root = null;
                root = new Page(grade);
                Page pageRight = new Page(grade);
                pageRight.setNodes(right);
                //pageRight.print();
                Page pageLeft = new Page(grade);
                pageLeft.setNodes(left);
                root.addNode(center);
                root.addPage(pageLeft);
                root.addPage(pageRight);
                pageLeft.setFather(root);
                pageRight.setFather(root);
                addKey(root, key);
            }else {
                if (root.isFather()) {
                    addKey(root, key);
                }else {
                    root.addNode(new Node(key));
                }
            }
        }
    }

    public void print(){
        print(root);
    }

}