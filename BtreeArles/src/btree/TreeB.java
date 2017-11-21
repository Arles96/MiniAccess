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
            r.addNode(new Node(key));
            if (r.isFull()) {
                splitFather(r, key);
            }
        }
    }
    
    private void splitFather(Page r, int key){
        //System.out.println(r.getFather().getNode(0).getKey());
        if (r.getFather().isFull()) {
            splitFather(r.getFather(), key);
        }else {
            //Realizando el split
            Node [] nodeLeft = r.splitNodeLeft();
            Node [] nodeRight = r.splitNodeRight();
            Node center = r.center();
            //Agregando el centro a la pagina padre
            r.getFather().addNode(center);
            // Haciendo nuevas paginas hijas
            Page pageLeft = new Page(grade);
            Page pageRight = new Page(grade);
            //Agregando el arreglo de los nodos
            pageLeft.setNodes(nodeLeft);
            pageRight.setNodes(nodeRight);
            //Asignando los padres a las paginas hijas
            pageRight.setFather(r.getFather());
            pageLeft.setFather(r.getFather());
            r.getFather().setPage(r.getFather().getCounterPage()-1, pageLeft);
            r.getFather().addPage(pageRight);
            //addKey(r.getFather(), key);
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
    
    /*public void add(int key){
        if (root==null) {
            root = new Page(grade);
            root.addNode(new Node(key));
        }else{
            if (root.isFull()) {
                if (root.isFather()) {
                    /*Node [] left = root.splitNodeLeft();
                    Node [] right = root.splitNodeRight();
                    Node center = root.center();
                    counter++;
                    System.out.println(counter);
                }else {
                    Node [] left = root.splitNodeLeft();
                    Node [] right = root.splitNodeRight();
                    Node center = root.center();
                    root = null;
                    root = new Page(grade);
                    Page pageRight = new Page(grade);
                    pageRight.setNodes(right);
                    Page pageLeft = new Page(grade);
                    pageLeft.setNodes(left);
                    root.addNode(center);
                    root.addPage(pageLeft);
                    root.addPage(pageRight);
                    pageLeft.setFather(root);
                    pageRight.setFather(root);
                    addKey(root, key);
                }
            }else {
                if (root.isFather()) {
                    addKey(root, key);
                }else {
                    root.addNode(new Node(key));
                }
            }
        }
    }*/
    
    public void add(int key){
        if (root==null) {
            root = new Page(grade);
            root.addNode(new Node(key));
            root.setRoot(true);
        }else {
            if (!root.isFather()) {
                root.addNode(new Node(key));
                if (root.isFull()) {
                    Node [] left = root.splitNodeLeft();
                    Node [] right = root.splitNodeRight();
                    Node center = root.center();
                    root = null;
                    root = new Page(grade);
                    root.setRoot(true);
                    Page pageRight = new Page(grade);
                    pageRight.setNodes(right);
                    Page pageLeft = new Page(grade);
                    pageLeft.setNodes(left);
                    root.addNode(center);
                    root.addPage(pageLeft);
                    root.addPage(pageRight);
                    pageLeft.setFather(root);
                    pageRight.setFather(root);
                    //addKey(root, key);
                }
            }else {
                addKey(root, key);
                if (root.isFull()) {
                    //Hacemos el split con los nodos del hijo del root
                    Node [] nodeLeft = root.splitNodeLeft();
                    Node [] nodeRight = root.splitNodeRight();
                    Node center = root.center();
                    //Dividimos las paginas nietas
                    Page [] pagesLeft = root.splitPageLeft();
                    Page [] pagesRight = root.splitPageRight();
                    //Hacemos una nueva raiz
                    root = null;                    
                    root = new Page(grade);
                    //Promovemos el nodo
                    root.addNode(center);
                    //Creamos las nuevas paginas hijas de la raiz
                    Page pageLeft = new Page(grade);
                    Page pageRight = new Page(grade);
                    //Le agregamos las nuevas paginas a la raiz
                    root.addPage(pageLeft);
                    root.addPage(pageRight);
                    //Le agregamos los nodos a las paginas nuevas
                    pageLeft.setNodes(nodeLeft);
                    pageRight.setNodes(nodeRight);
                    //Le agregamos a las nuevas paginas las paginas nietas
                    pageLeft.setPages(pagesLeft);
                    pageRight.setPages(pagesRight);
                }
            }
            
        }
    }

    public void print(){
        if (root !=null) {
            print(root);
        }        
    }

}