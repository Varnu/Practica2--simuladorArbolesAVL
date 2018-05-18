/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.util.Stack;

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

    public int insertaNodoEnAVL(Object d) {
        NodoAVL x = new NodoAVL(d);
        NodoAVL p, q, pivote, pp;
        int aux;
        if (this.retornaRaiz() == null) {
            raiz = x;
            return 0;
        }
        p = raiz;
        q = null;
        pivote = raiz;
        pp = null;
        while (p != null) {
            if (p.retornaDato() == d) {
                return -1;
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
            return 0;
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
            return 0;
        }
        if (pp.retornaLi() == pivote) {
            pp.asignaLi(q);
        } else {
            pp.asignaLd(q);
        }
        return 0;
    }

    public void unaRotALaDer(NodoAVL p, NodoAVL q) {
        p.asignaLi(q.retornaLd());
        q.asignaLd(p);
        p.asignaFb(0);
        q.asignaFb(0);
    }

    public void unaRotALaIzq(NodoAVL p, NodoAVL q) {
        p.asignaLd(q.retornaLi());
        q.asignaLi(p);
        p.asignaFb(0);
        q.asignaFb(0);
    }

    public NodoAVL dobleRotALaIzq(NodoAVL p, NodoAVL q) {
        NodoAVL r = q.retornaLi();
        q.asignaLi(r.retornaLd());
        r.asignaLd(q);
        p.asignaLd(r.retornaLi());
        r.asignaLi(p);
        switch (r.retornaFb()) {
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

    public NodoAVL dobleRotALaDer(NodoAVL p, NodoAVL q) {
        NodoAVL r = q.retornaLd();
        q.asignaLd(r.retornaLi());
        r.asignaLi(q);
        p.asignaLi(r.retornaLd());
        r.asignaLd(p);
        switch (r.retornaFb()) {
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

    public int eliminaNodoEnAVL(Object d) {
        Stack pila = new Stack();
        NodoAVL p, q, t, r;
        boolean encontro, terminar;
        int llave, accion;
        if (this.retornaRaiz() == null) {
            System.out.println("arbol vacio");
            return -1;
        }
        encontro = false;
        terminar = false;
        p = this.retornaRaiz();
        while (!encontro && p != null) {
            pila.push(p);
            if ((int) d < (int) p.retornaDato()) {
                p = p.retornaLi();
            } else if ((int) d > (int) p.retornaDato()) {
                p = p.retornaLd();
            } else {
                encontro = true;
            }
        }
        if (!encontro) {
            System.out.println("Dato no existente en arbol");
            return -2;
        }
        t = null;
        p = (NodoAVL) pila.pop();
        llave = (int) p.retornaDato();
        if (p.retornaLi() == null && p.retornaLd() == null) {
            accion = 0;
        } else if (p.retornaLd() == null) {
            accion = 1;
        } else if (p.retornaLi() == null) {
            accion = 2;
        } else {
            accion = 3;
        }
        if (accion == 0 || accion == 1 || accion == 2) {
            if (!pila.empty()) {
                q = (NodoAVL) pila.pop();
                if ((int) llave < (int) q.retornaDato()) {
                    Object[] k;
                    switch (accion) {
                        case 0:
                        case 1:
                            q.asignaLi(p.retornaLi());
                            if (q.retornaLd() != null) {
                                k = balacearPorDerecha(q, t, terminar);
                                t = (NodoAVL) k[0];
                                terminar = (boolean) k[1];
                            } else {
                                return 0;
                            }
                            break;
                        case 2:
                            if (q.retornaLd() != null) {
                                q.asignaLi(p.retornaLd());
                                k = balacearPorDerecha(q, t, terminar);
                                t = (NodoAVL) k[0];
                                terminar = (boolean) k[1];
                            } else {
                                return 0;
                            }
                            break;
                    }
                } else {
                    Object[] k;
                    switch (accion) {
                        case 0:
                        case 2:
                            q.asignaLd(p.retornaLd());
                            k = balacearPorIzquierda(q, t, terminar);
                            t = (NodoAVL) k[0];
                            terminar = (boolean) k[1];
                            break;
                        case 1:
                            q.asignaLd(p.retornaLi());
                            k = balacearPorIzquierda(q, t, terminar);
                            t = (NodoAVL) k[0];
                            terminar = (boolean) k[1];
                            break;
                    }
                }
            } else {
                switch (accion) {
                    case 0:
                        asignaRaiz(null);
                        terminar = true;
                        break;
                    case 1:
                        asignaRaiz(p.retornaLi());
                        break;
                    case 2:
                        asignaRaiz(p.retornaLd());
                        break;
                }
            }

        } else {
            pila.push(p);
            r = p;
            p = r.retornaLd();
            q = null;
            while (p.retornaLi() != null) {
                pila.push(p);
                q = p;
                p = p.retornaLi();
            }
            r.asignaDato(p.retornaDato());//Posible error
            llave = (int) r.retornaDato();//posible error
            if (q != null) {
                q.asignaLi(p.retornaLd());
                Object[] k = balacearPorDerecha(q, t, terminar);
                t = (NodoAVL) k[0];
                terminar = (boolean) k[1];
            } else {
                r.asignaLd(p.retornaLd());
                Object[] k = balacearPorIzquierda(r, t, terminar);
                t = (NodoAVL) k[0];
                terminar = (boolean) k[1];
            }
            q = (NodoAVL) pila.pop();
        }
        while (!pila.empty() && !terminar) {
            q = (NodoAVL) pila.pop();
            if (llave < (int) q.retornaDato()) {
                if (t != null) {
                    q.asignaLi(t);
                    t = q;
                }
                Object[] k = balacearPorDerecha(q, t, terminar);
                t = (NodoAVL) k[0];
                terminar = (boolean) k[1];
            } else {
                if (t != null) {
                    q.asignaLd(t);
                    t = q;
                }
                Object[] k = balacearPorIzquierda(q, t, terminar);
                t = (NodoAVL) k[0];
                terminar = (boolean) k[1];
            }
        }
        if (t != null) {
            if (pila.empty()) {
                this.asignaRaiz(t);
            } else {
                q = (NodoAVL) pila.pop();
                if (llave < (int) q.retornaDato()) {
                    q.asignaLi(t);
                } else {
                    q.asignaLd(t);
                }
            }
        }
        return 0;
    }

    public Object[] balacearPorIzquierda(NodoAVL q, NodoAVL t, boolean terminar) {
        Object[] retorno = new Object[2];
        retorno[0] = t;
        retorno[1] = terminar;
        NodoAVL r = null;
        switch (q.retornaFb()) {
            case -1:
                q.asignaFb(0);

                break;
            case 1:
                r = q.retornaLi();
                switch (r.retornaFb()) {
                    case 1:
                        unaRotALaDer(q, r);
                        break;
                    case -1:
                        r = dobleRotALaDer(q, r);
                        break;
                    case 0:
                        q.asignaLi(r.retornaLd());
                        r.asignaLd(q);
                        r.asignaFb(-1);
                        retorno[1] = true;
                        break;
                }
                break;
            case 0:
                q.asignaFb(1);
                retorno[1] = true;
                break;
        }

        return retorno;
    }

    public Object[] balacearPorDerecha(NodoAVL q, NodoAVL t, boolean terminar) {
        Object[] retorno = new Object[2];
        retorno[0] = t;
        retorno[1] = terminar;
        NodoAVL r = null;
        switch (q.retornaFb()) {
            case -1:
                q.asignaFb(0);

                break;
            case 1:
                r = q.retornaLd();
                switch (r.retornaFb()) {
                    case 1:
                        unaRotALaIzq(q, r);
                        break;
                    case -1:
                        r = dobleRotALaIzq(q, r);
                        break;
                    case 0:
                        q.asignaLd(r.retornaLd());
                        r.asignaLi(q);
                        r.asignaFb(-1);
                        retorno[1] = true;
                        break;
                }
                break;
            case 0:
                q.asignaFb(1);
                retorno[1] = true;
                break;
        }

        return retorno;
    }

    public void preorden(NodoAVL r) {
        if (r != null) {
            System.out.print(" " + r.retornaDato() + " ");
            preorden(r.retornaLi());
            preorden(r.retornaLd());
        }
    }
    public void inorden(NodoAVL r) {
        if (r != null) {
            inorden(r.retornaLi());
            System.out.print(" " + r.retornaDato() + " ");
            inorden(r.retornaLd());
        }
    }

}
