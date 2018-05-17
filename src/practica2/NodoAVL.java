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
public class NodoAVL {
    Object dato;
    NodoAVL li;
    NodoAVL ld;
    int fb;
    public NodoAVL(Object dato){
        this.dato = dato;
        li = null;
        ld = null;
        fb = 0;
    }
    public void asignaDato(Object dato){
        this.dato = dato;
    }
    public void asignaLi(NodoAVL li){
        this.li = li;
    }
    public void asignaLd(NodoAVL ld){
        this.ld = ld;
    }
    public void asignaFb(int fb){
        this.fb = fb;
    }
    public Object retornaDato(){
        return dato;
    }
    public NodoAVL retornaLi(){
        return li;
    }
    public NodoAVL retornaLd(){
        return ld;
    }
    public int retornaFb(){
        return fb;
    }
}
