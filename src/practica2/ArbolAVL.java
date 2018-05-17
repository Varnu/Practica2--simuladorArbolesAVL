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
    
    public ArbolAVL() {
        this.raiz = null;
    }
    
    public NodoAVL retornaRaiz() {
        return this.raiz;
    }

    public void asignaRaiz(NodoAVL x) {
        raiz = x;
    }
    
    public void insertaNodoEnAVL(Object d) {
        NodoAVL x = new NodoAVL(d);
        NodoAVL p, q, pivote, pp;
        int aux;
        if (this.retornaRaiz() == null) {
            raiz = x;
            return;
        }
        p = raiz;
        q = null;
        pivote = raiz;
        pp = null;
        while (p != null) {
            if (p.retornaDato() == d) {
                System.out.println("El dato ya existe");
                return;
            }
            if (p.retornaFb() != 0) {
                pivote = p;
                pp = q;
            }
            q = p;
            if ((int) d < (int) p.retornaDato()) {
                p = p.retornaLi();
            } else {
                p = p.retornaLd();
            }
        }
        if ((int) d < (int) q.retornaDato()) {
            q.asignaLi(x);
        } else {
            q.asignaLd(x);
        }
        aux = (int) pivote.retornaFb();
        if ((int) d < (int) pivote.retornaDato()) {
            pivote.asignaFb(aux + 1);
            q = pivote.retornaLi();
        } else {
            pivote.asignaFb(aux - 1);
            q = pivote.retornaLd();
        }
        p = q;
        while (p != x) {
            if ((int) d < (int) p.retornaDato()) {
                p.asignaFb(1);
                p = p.retornaLi();
            } else {
                p.asignaFb(-1);
                p = p.retornaLd();
            }
        }
        if (Math.abs(pivote.retornaFb()) < 2) {
            return;
        }
        if (pivote.retornaFb() == 2) {
            if (q.retornaFb() == 1) {
                unaRotALaDer(pivote, q);
            } else {
                q = dobleRotALaDer(pivote, q);
            }
        } else {
            if (q.retornaFb() == -1) {
                unaRotALaIzq(pivote, q);
            } else {
                dobleRotALaIzq(pivote, q);
            }
        }
        if (pivote == this.retornaRaiz()) {
            this.asignaRaiz(q);
            return;
        }
        if (pp.retornaLi() == pivote) {
            pp.asignaLi(q);
        } else {
            pp.asignaLd(q);
        }
    }
    public void unaRotALaDer(NodoAVL p, NodoAVL q){
        p.asignaLi(q.retornaLd());
        q.asignaLd(p);
        p.asignaFb(0);
        q.asignaFb(0);
    }
    public void unaRotALaIzq(NodoAVL p, NodoAVL q){
        p.asignaLd(q.retornaLi());
        q.asignaLi(p);
        p.asignaFb(0);
        q.asignaFb(0);
    }
    public NodoAVL dobleRotALaIzq(NodoAVL p, NodoAVL q){
        NodoAVL r = q.retornaLi();
        q.asignaLi(r.retornaLd());
        r.asignaLd(q);
        p.asignaLd(r.retornaLi());
        r.asignaLi(p);
        switch(r.retornaFb()){
            case 0:
                p.asignaFb(0);
                q.asignaFb(0);
                break;
            case 1:
                p.asignaFb(-1);
                q.asignaFb(0);
                break;
            case -1:
                p.asignaFb(0);
                q.asignaFb(1);
                break;
        }
        r.asignaFb(0);
        q = r;
        return q;
    }
    public NodoAVL dobleRotALaDer(NodoAVL p, NodoAVL q){
        NodoAVL r = q.retornaLd();
        q.asignaLd(r.retornaLi());
        r.asignaLi(q);
        p.asignaLi(r.retornaLd());
        r.asignaLd(p);
        switch(r.retornaFb()){
            case 0:
                p.asignaFb(0);
                q.asignaFb(0);
                break;
            case 1:
                p.asignaFb(-1);
                q.asignaFb(0);
                break;
            case -1:
                p.asignaFb(0);
                q.asignaFb(1);
                break;
        }
        r.asignaFb(0);
        q = r;
        return q;
    }
    public void mostrar(){
        System.out.println("w");
    }
}
