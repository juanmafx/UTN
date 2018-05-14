/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntosr2;

/**
 *
 * @author MARTIN
 */
public class Punto {

    private float x, y;

    //constructor por defecto
    public Punto() {
        x = y = 0;
    }

    //constructor con parámetros: unX e unY
    public Punto(float unX, float unY) {
        x = unX;
        y = unY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    //Permite calcular la distancia desde el punto al centro de coordenadas
    public double calcularDistancia() {
        return Math.sqrt(x * x + y * y);
    }

    //Permite mostrar el punto como un par ordenado:
    //Fotmato:"(x;y)"
    public String mostrarPunto() {
        return "(" + x + ";" + y + ")";
    }

    //Permite mostrar el cuadrante donde se encuentra el punto
    //Caso especial: x=0 o y=0, devuelve "Sobre los ejes"
    public String mostrarCuadrante() {
        String cuad = "Sobre los ejes";

        if (x > 0 && y > 0) {
            cuad = "Cuadrante I";
        }
        if (x > 0 && y < 0) {
            cuad = "Cuadrante IV";
        }
        if (x < 0 && y > 0) {
            cuad = "Cuadrante II";
        }
        if (x < 0 && y < 0) {
            cuad = "Cuadrante III";
        }

        return cuad;
    }
}
