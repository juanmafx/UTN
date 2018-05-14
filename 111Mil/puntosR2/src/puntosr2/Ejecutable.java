/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntosr2;

import java.util.Scanner;

/**
 *
 * @author MARTIN
 */
public class Ejecutable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Punto miPunto = new Punto();
        float x, y, pendiente;
        Recta miRecta = new Recta();

        System.out.println(" Ingrese coordenada X del punto: ");
        x = input.nextFloat();
        
        System.out.println(" Ingrese coordenada Y del punto: ");
        y = input.nextFloat();
        
               
        miPunto.setX(x);
        miPunto.setY(y);
        
        //Otra forma: Punto unPunto = new Punto(x, y);
        
        //Mostrar el estado del objeto:
        System.out.println(" Estado: " + miPunto.mostrarPunto());
        
        //Mostrar la distancia al origen:
        System.out.println(" Distancia al Origen: " + miPunto.calcularDistancia());
        
        //Mostrar el cuadrante:
        System.out.println(" Cuadrante: " + miPunto.mostrarCuadrante());
        
        //Mostrar Ecuacion:
        System.out.println(" Ecuacion: ");
        System.out.println(miRecta.mostrarEcuacion());
        
        //modificar pendiente
         System.out.println(" Ingrese nueva pendiente: ");
         pendiente = input.nextFloat();
         miRecta.setPendiente(pendiente);
         
         
         System.out.println(" Ecuacion: ");
         System.out.println(miRecta.mostrarEcuacion());
        
        
        

    }

}
