public class asig1{
public static void main(String[] args){

int[][] r = new int[4][4];
int agregaDos = 2;
		
		
        // Recorre las filas
        for (int i = 0; i < 4; i++){
            // Recorre las columnas
			
            for (int j = 0; j < 4; j++) {
                // Le agrega un valor a la posición [i][j]
                r[i][j] = agregaDos;
                // imprime el valor agregado
                System.out.print(" " +r[i][j]);
                agregaDos=agregaDos+2;
            }
            System.out.println();
        }

}
}


/*
Haz un programa en Java
que muestre la siguiente matriz de números:
2   4  6  8
10 12 14 16
18 20 22 24
26 28 30 32

Genera un código en Java que corra adecuadamente utilizando los bucles y la matriz correctos.

Una vez que tengas tu código, debes:

Crear tu archivo con la extensión: .java
Subir tu archivo en este espacio.
Revisar el trabajo de tres compañeros con los criterios que adelante se especifican.
Dar respuesta a la pregunta que se plantea para agregaDosribuir al trabajo de tus compañeros.
Una vez que tres de tus compañeros revisen tu tarea, aparecerá tu calificación.
Review criterialess 
En esta actividad, tus compañeros evaluarán que tu programa:

Corra adecuadamente.
Utilice bucles "for".
Utilice operaciones matemáticas para llenar la matriz.
Sea visualizada una matriz.

*/
