
/*
Recta(). 

Permite inicializar una recta con pendiente 0 y un centro de coordenadas como punto de origen.
de acceso y modificación

mostrarOrdenada. Que permita obtener la ordenada al origen (corte con el eje y o término b de la ecuación explícita)

mostrarEcuacion. Que permita obtener la ecuación de la recta según la fórmula explícita: ‘y=mx+b’.

pertenece: Que permita determinar si un punto cualquiera, recibido como colaborador externo, pertenece a la recta.

 */
package puntosr2;

/**
 *
 * @author alumno
 */
public class Recta {

    /*
    Recta(). 
    Permite inicializar una recta con pendiente 0 y un centro de coordenadas como punto de origen.
    de acceso y modificación
     */
    private float pendiente;
    private float ordenada;
    private Punto origen;

    public Recta() {
        pendiente = 1;
        origen = new Punto();
    }

    public float getPendiente() {
        return pendiente;
    }

    public void setPendiente(float m) {
        pendiente = m;
    }

    public Punto getPunto() {
        return origen;
    }

    public void setPunto(Punto p) {
        origen = p;
    }

    public Float pendiente() {
        return pendiente;
    }

    public Float calcularOrdenada() {
        return origen.getX() * pendiente * (-1) + origen.getY();

    }

    //mostrarEcuacion. Que permita obtener la ecuación de la recta según la fórmula explícita: ‘y=mx+b’.
    public String mostrarEcuacion() {
        float b = origen.getX() * pendiente() * (-1) + origen.getY();
        return "y = " + pendiente().toString() + " x  + " + calcularOrdenada().toString();

    }

}
