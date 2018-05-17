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
        a.insertaNodoEnAVL(80);
        a.insertaNodoEnAVL(12);
        a.insertaNodoEnAVL(8);
        a.insertaNodoEnAVL(7);
        a.insertaNodoEnAVL(23);
        a.insertaNodoEnAVL(50);
        a.insertaNodoEnAVL(9);
        a.mostrar();
    }
    
}
