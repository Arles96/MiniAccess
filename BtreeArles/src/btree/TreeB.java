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
                splitFather(r);
            }
        }
    }
    
    private void splitFather(Page r){
        //System.out.println(r.getFather().getNode(0).getKey());
        if (r.getFather().isFull()) {
            splitFather(r.getFather());
        }else {
            if (!r.isFather()) {
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
            }else {
                //Realizando el split
                Node [] nodeLeft = r.splitNodeLeft();
                Node [] nodeRight = r.splitNodeRight();
                Node center = r.center();
                //Diviediendo las paginas nietas
                Page [] pagesLeft = r.splitPageLeft();
                Page [] pagesRight = r.splitPageRight();
                //Promovemos al nodo central
                r.getFather().addNode(center);
                //Creamos las nuevas paginas hijas del padre
                Page pageLeft = new Page(grade);
                Page pageRight = new Page(grade);
                //Le agregamos las nuevas paginas al padre
                r.getFather().setPage(r.getFather().getCounterPage()-1, pageLeft);
                r.getFather().addPage(pageRight);
                //Le agregamos los nodos a las nuevas pagina
                pageLeft.setNodes(nodeLeft);
                pageRight.setNodes(nodeRight);
                //Le agregamos las hijas a las nuevas paginas
                pageLeft.setPages(pagesLeft);
                pageRight.setPages(pagesRight);
                //Le seteamos los nuevos padres
                pageLeft.setFather(r.getFather());
                pageRight.setFather(r.getFather());
            }
            if (!r.getFather().isRoot()) {
                if (r.getFather().isFull()) {
                    splitFather(r.getFather());
                }
            }
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
    
    public void print(String space, Page p){
        System.out.println(space + p.toString());
        if (p.isFather()) {
            space += "--";
            for (int i = 0; i < p.getCounterPage(); i++) {
                print(space, p.getPage(i));
            }
        }
    }
    
    //Administation methods

    public void add(int key){
        if (root==null) {
            root = new Page(grade);
            root.addNode(new Node(key));
            root.setRoot(true);
        }else {
            if (!root.isFather()) {
                root.addNode(new Node(key));
                if (root.isFull()) {
                    //Hacemos el split con los nodos
                    Node [] left = root.splitNodeLeft();
                    Node [] right = root.splitNodeRight();
                    Node center = root.center();
                    //Creamos un nuevo root
                    root = null;
                    root = new Page(grade);
                    //le decimos que es la raiz
                    root.setRoot(true);
                    //Creamos las nuevas paginas y le agregamos los split
                    Page pageRight = new Page(grade);
                    pageRight.setNodes(right);
                    Page pageLeft = new Page(grade);
                    pageLeft.setNodes(left);
                    //Le agregamos a la raiz el nodo primovido y las nuevas paginas
                    root.addNode(center);
                    root.addPage(pageLeft);
                    root.addPage(pageRight);
                    //Le seteamos a las nuevas paginas el nuevo padre
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
                    //Asignando los padres
                    pageLeft.setFather(root);
                    pageRight.setFather(root);
                }
            }
            
        }
    }

    public void print(){
        if (root != null) {
            print(root);
        }        
    }
    
    public void print2(){
        print("", root);
    }

}