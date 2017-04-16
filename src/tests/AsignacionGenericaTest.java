package tests;

import algoritmos.AsignacionGenerica;
import junit.framework.TestCase;

public class AsignacionGenericaTest extends TestCase{

    public void testEjemploAsignacion1() {

        int n = 4;
        int m = 2;

        int[][] estudiantes = new int[n][m];
        int[][] hospitales = new int[m][n];
        int[] vacantes = new int[m];

        estudiantes[0][0] = 0;
        estudiantes[0][1] = 1;

        estudiantes[1][0] = 1;
        estudiantes[1][1] = 0;

        estudiantes[2][0] = 1;
        estudiantes[2][1] = 0;

        estudiantes[3][0] = 1;
        estudiantes[3][1] = 0;

        hospitales[0][0] = 0;
        hospitales[0][1] = 1;
        hospitales[0][2] = 3;
        hospitales[0][3] = 2;

        hospitales[1][0] = 2;
        hospitales[1][1] = 0;
        hospitales[1][2] = 1;
        hospitales[1][3] = 3;

        vacantes[0] = 3;
        vacantes[1] = 1;

        AsignacionGenerica asign = new AsignacionGenerica(estudiantes, hospitales, vacantes);
    }

}
