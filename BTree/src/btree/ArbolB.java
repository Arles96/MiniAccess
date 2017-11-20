/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolb;

/**
 *
 * @author jorge
 */
public class ArbolB {

    private Pagina Raiz;
    private int Orden;

    public ArbolB(int Orden) {
        this.Raiz = new Pagina();
        this.Orden = Orden;
    }

    public Pagina getRaiz() {
        return Raiz;
    }

    public void setRaiz(Pagina Raiz) {
        this.Raiz = Raiz;
    }

    public void imprimir() {
        imprimirRecursivo(Raiz, 0);
    }

    private void imprimirRecursivo(Pagina pagina, int nivel) {
        for (int i = 0; i < nivel; i++) {
            System.out.print("---");
        }
        System.out.println(pagina);
        for (int i = 0; i < pagina.getPaginas().size(); i++) {
            imprimirRecursivo(pagina.getPaginas().get(i), nivel + 1);
        }
    }

    public void insertar(Nodo nodo) {
        this.Raiz = insertarRecursivo(Raiz, nodo);
    }

    private Pagina insertarRecursivo(Pagina pagina, Nodo nodo) {
        Pagina arriba = pagina;
        Pagina abajo = null;
        if (pagina.getPaginas().size() == 0) {
            pagina.addNodo(nodo);
            if (pagina.getNodos().size() >= Orden) {
                Pagina nuevaPagina = new Pagina();
                Pagina izquierda = new Pagina();
                Pagina derecha = new Pagina();
                for (int i = 0; i < pagina.getNodos().size(); i++) {
                    if (i < pagina.getNodos().size() / 2) {
                        izquierda.addNodo(pagina.getNodos().get(i));
                    }
                    if (i == pagina.getNodos().size() / 2) {
                        nuevaPagina.addNodo(pagina.getNodos().get(i));
                    }
                    if (i > pagina.getNodos().size() / 2) {
                        derecha.addNodo(pagina.getNodos().get(i));
                    }
                }
                nuevaPagina.addPagina(izquierda);
                nuevaPagina.addPagina(derecha);
                arriba = nuevaPagina;
            }
        } else {
            int pos = 0;
            try {
                do {
                    if (pos == 0 && (pagina.getNodos().get(pos).getID() > nodo.getID())) {
                        break;
                    }
                    pos++;
                } while (pagina.getNodos().get(pos).getID() < nodo.getID());
            } catch (Exception e) {
            }
            abajo = insertarRecursivo(pagina.getPaginas().get(pos), nodo);
            if (!pagina.getPaginas().contains(abajo)) {
                pagina.addNodo(abajo.getNodos().get(0));
                pagina.getPaginas().set(pos, abajo.getPaginas().get(0));
                try {
                    pagina.getPaginas().set(pos + 1, abajo.getPaginas().get(1));
                } catch (Exception e) {
                    pagina.getPaginas().add(abajo.getPaginas().get(1));
                }
                if (pagina.getNodos().size() >= Orden) {
                    Pagina nuevaPagina = new Pagina();
                    Pagina izquierda = new Pagina();
                    Pagina derecha = new Pagina();
                    for (int i = 0; i < pagina.getNodos().size(); i++) {
                        if (i < pagina.getNodos().size() / 2) {
                            izquierda.addNodo(pagina.getNodos().get(i));
                            izquierda.addPagina(pagina.getPaginas().get(i));
                        }
                        if (i == pagina.getNodos().size() / 2) {
                            izquierda.addPagina(pagina.getPaginas().get(i));
                            nuevaPagina.addNodo(pagina.getNodos().get(i));
                        }
                        if (i > pagina.getNodos().size() / 2) {
                            derecha.addNodo(pagina.getNodos().get(i));
                            derecha.addPagina(pagina.getPaginas().get(i));
                            if (i==pagina.getNodos().size()-1) {
                                derecha.addPagina(pagina.getPaginas().get(i+1));
                            }
                        }
                    }
                    nuevaPagina.addPagina(izquierda);
                    nuevaPagina.addPagina(derecha);
                    arriba = nuevaPagina;
                }
            }
        }
        return arriba;
    }
}
