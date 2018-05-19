    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

/**
 *
 * @author jairo
 */
public class ArbolAVL {

    private NodoAVL raiz;
    private int estadoProceso;

    public ArbolAVL() {
        this.raiz = null;
        this.estadoProceso = 0;
    }

    public NodoAVL retornaRaiz() {
        return this.raiz;
    }
    public int retornaEstadoProceso(){
        int x = estadoProceso;
        estadoProceso = 0;
        return x;
    }

    public void asignaRaiz(NodoAVL x) {
        raiz = x;
    }

    public void insertar(int dato) {
        this.asignaRaiz(insertar(this.retornaRaiz(), dato));
    }
    public void eliminar(int dato){
        this.asignaRaiz(eliminar(this.retornaRaiz(), dato));
    }

    private NodoAVL insertar(NodoAVL nodo, int dato) {
        if (nodo == null) {
            estadoProceso = 0;
            NodoAVL x = new NodoAVL(dato);
            x.asignaAltura(1);
            return x;
        }
        if (dato == (int) nodo.retornaDato()) {
            estadoProceso = 1;
            System.out.println("Dato repetido");
            return nodo;
        }
        if (dato < (int) nodo.retornaDato()) {
            nodo.asignaLi(insertar(nodo.retornaLi(), dato));
        } else {
            nodo.asignaLd(insertar(nodo.retornaLd(), dato));
        }
        nodo.asignaAltura(Math.max(this.altura(nodo.retornaLi()), this.altura(nodo.retornaLd())) + 1);

        nodo = resolverDesbalanceIns(dato, nodo);

        return nodo;
    }

    private NodoAVL resolverDesbalanceIns(int dato, NodoAVL nodo) {
        int factBalance = this.retornaBalance(nodo);
        if (factBalance > 1 && dato < (int) nodo.retornaLi().retornaDato()) {
            return rotacionALaDer(nodo);
        }
        if (factBalance < -1 && dato > (int) nodo.retornaLd().retornaDato()) {
            return rotacionALaIzq(nodo);
        }
        if (factBalance > 1 && dato > (int) nodo.retornaLi().retornaDato()) {
            nodo.asignaLi(rotacionALaIzq(nodo.retornaLi()));
            return rotacionALaDer(nodo);
        }
        if (factBalance < -1 && dato < (int) nodo.retornaLd().retornaDato()) {
            nodo.asignaLd(rotacionALaDer(nodo.retornaLd()));
            return rotacionALaIzq(nodo);
        }
        return nodo;
    }
    private NodoAVL eliminar(NodoAVL nodo, int dato){
        if(nodo == null){
            estadoProceso = 1;
            return null;
        }
        if(dato < (int)nodo.retornaDato()){
            nodo.asignaLi(eliminar(nodo.retornaLi(), dato));
        }else if(dato > (int)nodo.retornaDato()){
            nodo.asignaLd(eliminar(nodo.retornaLd(), dato));
        }else{
            estadoProceso = 0;
            if(nodo.retornaLi()==null && nodo.retornaLd() == null){
                System.out.println("eliminando hoja");
                return null;
            }
            if(nodo.retornaLi()== null){
                System.out.println("removiendo hijo derecho");
                NodoAVL tempNodo = nodo.retornaLd();
                nodo = null;
                return tempNodo;
            }else if(nodo.retornaLd() == null){
                System.out.println("Removiendo hijo izquierdo");
                NodoAVL tempNodo = nodo.retornaLi();
                nodo = null;
                return tempNodo;
            }
            System.out.println("Removiendo con 2 hijos");
            NodoAVL tempNodo = this.retornaPredecesor(nodo.retornaLi());
            nodo.asignaDato(tempNodo.retornaDato());
            nodo.asignaLi(eliminar(nodo.retornaLi(), (int)tempNodo.retornaDato()));
        }
        nodo.asignaAltura(Math.max(this.altura(nodo.retornaLi()), this.altura(nodo.retornaLd())) + 1);  
        return resolverDesbalanceDel(nodo);
    }
    private NodoAVL resolverDesbalanceDel(NodoAVL nodo){
        int factBalance = this.retornaBalance(nodo);
        if(factBalance > 1){
            if(this.retornaBalance(nodo.retornaLi())<0){
                nodo.asignaLi(rotacionALaIzq(nodo.retornaLi()));
            }
            return rotacionALaDer(nodo);
        }
        if(factBalance < -1){
            if(this.retornaBalance(nodo.retornaLd())>0){
                nodo.asignaLd(rotacionALaDer(nodo.retornaLd()));
            }
            return rotacionALaIzq(nodo);
        }
        return nodo;
    }
    /**
     * Realiza una rotacion a la derecha desde un nodo ingresado como parametro
     * que tiene un factor de balance superior a 1 o inferior a -1
     *
     * @param nodo nodo de la clase NodoAVL que tiene un factor de balance
     * superior a 1 o inferior a -1
     * @return La nueva raiz del subarbol.
     */
    private NodoAVL retornaPredecesor(NodoAVL nodo){
       NodoAVL predecesor = nodo;
       while(predecesor.retornaLd() != null){
           predecesor = predecesor.retornaLd();
       }
       return predecesor;
    }
    private NodoAVL rotacionALaDer(NodoAVL nodo) {
        System.out.println("Rotando a la derecha");
        NodoAVL tempNodoIzq = nodo.retornaLi();
        NodoAVL t = tempNodoIzq.retornaLd();
        tempNodoIzq.asignaLd(nodo);
        nodo.asignaLi(t);
        nodo.asignaAltura(Math.max(altura(nodo.retornaLi()), altura(nodo.retornaLd())) + 1);
        tempNodoIzq.asignaAltura(Math.max(altura(tempNodoIzq.retornaLi()), altura(tempNodoIzq.retornaLd())) + 1);

        return tempNodoIzq;
    }

    private NodoAVL rotacionALaIzq(NodoAVL nodo) {
        System.out.println("Rotando a la izquierda");
        NodoAVL tempNodoDer = nodo.retornaLd();
        NodoAVL t = tempNodoDer.retornaLi();
        tempNodoDer.asignaLi(nodo);
        nodo.asignaLd(t);
        nodo.asignaAltura(Math.max(altura(nodo.retornaLi()), altura(nodo.retornaLd())) + 1);
        tempNodoDer.asignaAltura(Math.max(altura(tempNodoDer.retornaLi()), altura(tempNodoDer.retornaLd())) + 1);

        return tempNodoDer;
    }

    public void inOrden(NodoAVL raiz) {
        if (raiz != null) {
            inOrden(raiz.retornaLi());
            System.out.print(" " + raiz.retornaDato() + " ");
            inOrden(raiz.retornaLd());
        }
    }

    public void preOrden(NodoAVL raiz) {
        if (raiz != null) {
            System.out.print(" " + raiz.retornaDato() + " ");
            preOrden(raiz.retornaLi());
            preOrden(raiz.retornaLd());
        }
    }
    public void posOrden(NodoAVL raiz) {
        if (raiz != null) {
            posOrden(raiz.retornaLi());
            posOrden(raiz.retornaLd());
            System.out.print(" " + raiz.retornaDato() + " ");
        }
    }

    private int altura(NodoAVL nodo) {
        if (nodo == null) {
            return -1;
        }
        return nodo.retornaAltura();
    }

    private int retornaBalance(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.retornaLi()) - altura(nodo.retornaLd());
    }
}
