/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vector5x5;

import java.util.*;

/**
 *
 * @author alumno
 */
public class Vector5x5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Random r = new Random();

        int m[][];
        m = new int[5][5];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                m[i][j] = r.nextInt(5);

            }

        }

        for (int x = 0; x < m.length; x++) {
            System.out.println("---------");

            for (int y = 0; y < m[x].length; y++) {
                System.out.print("|" + m[x][y]);
            }
            System.out.println("|");
        }

    }

}
