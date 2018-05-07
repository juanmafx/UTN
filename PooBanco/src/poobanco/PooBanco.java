/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poobanco;


/**
 *
 * @author alumno
 */
public class PooBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cliente c1 = new cliente();
        cliente c2 = new cliente(200,1000,'i');
        
        System.out.println(c1.toS());
        System.out.println("----");
        System.out.println(c2.toS());

    }
    
}
