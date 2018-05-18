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
public class Practica2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolAVL a = new ArbolAVL();
        a.insertar(80);
        a.insertar(12);
        a.insertar(8);
        a.insertar(7);
        a.insertar(23);
        a.insertar(50);
        a.insertar(9);
        a.insertar(13);
        a.insertar(16);
        a.insertar(20);
        a.insertar(45);
        a.insertar(30);
        a.insertar(21);
        a.insertar(21);
        
        a.inorden(a.retornaRaiz());
        System.out.println(" ");
        a.preorden(a.retornaRaiz());
        System.out.println("");
        a.eliminar(22);
        
        System.out.println("");
        a.inorden(a.retornaRaiz());
        System.out.println(" ");
        a.preorden(a.retornaRaiz());
        System.out.println("");
        
    }
    
}
