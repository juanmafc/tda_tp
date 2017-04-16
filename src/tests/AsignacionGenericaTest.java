package tests;

import algoritmos.AsignacionGenerica;
import javafx.util.Pair;
import junit.framework.TestCase;

import java.util.List;

public class AsignacionGenericaTest extends TestCase{

    private boolean asignacionesContiene(int estudiante, int hospital, List<Pair<Integer, Integer>> asignaciones) {
        return asignaciones.contains(new Pair<>(estudiante, hospital));
    }


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

        AsignacionGenerica asignacion = new AsignacionGenerica(estudiantes, hospitales, vacantes);
        List<Pair<Integer, Integer>> asignaciones = asignacion.getAsignaciones();
        assertEquals(4, asignaciones.size());

        assertTrue(asignacionesContiene(0,0, asignaciones));
        assertTrue(asignacionesContiene(1,0, asignaciones));
        assertTrue(asignacionesContiene(2,1, asignaciones));
        assertTrue(asignacionesContiene(3,0, asignaciones));
    }



    public void testEjemploAsignacion2() {

        int n = 6;
        int m = 3;

        int[][] estudiantes = new int[n][m];
        int[][] hospitales = new int[m][n];
        int[] vacantes = new int[m];

        estudiantes[0][0] = 0;
        estudiantes[0][1] = 1;
        estudiantes[0][2] = 2;

        estudiantes[1][0] = 1;
        estudiantes[1][1] = 0;
        estudiantes[1][2] = 2;

        estudiantes[2][0] = 1;
        estudiantes[2][1] = 2;
        estudiantes[2][2] = 0;

        estudiantes[3][0] = 1;
        estudiantes[3][1] = 2;
        estudiantes[3][2] = 0;

        estudiantes[4][0] = 0;
        estudiantes[4][1] = 2;
        estudiantes[4][2] = 1;

        estudiantes[5][0] = 2;
        estudiantes[5][1] = 1;
        estudiantes[5][2] = 0;



        hospitales[0][0] = 0;
        hospitales[0][1] = 1;
        hospitales[0][2] = 3;
        hospitales[0][3] = 4;
        hospitales[0][4] = 5;
        hospitales[0][5] = 2;

        hospitales[1][0] = 1;
        hospitales[1][1] = 2;
        hospitales[1][2] = 0;
        hospitales[1][3] = 4;
        hospitales[1][4] = 5;
        hospitales[1][5] = 3;

        hospitales[2][0] = 4;
        hospitales[2][1] = 2;
        hospitales[2][2] = 0;
        hospitales[2][3] = 5;
        hospitales[2][4] = 1;
        hospitales[2][5] = 3;

        vacantes[0] = 1;
        vacantes[1] = 3;
        vacantes[2] = 2;

        AsignacionGenerica asignacion = new AsignacionGenerica(estudiantes, hospitales, vacantes);

        List<Pair<Integer, Integer>> asignaciones = asignacion.getAsignaciones();
        assertEquals(6, asignaciones.size());

        assertTrue(asignacionesContiene(0,0, asignaciones));
        assertTrue(asignacionesContiene(1,1, asignaciones));
        assertTrue(asignacionesContiene(2,1, asignaciones));
        assertTrue(asignacionesContiene(3,1, asignaciones));
        assertTrue(asignacionesContiene(4,2, asignaciones));
        assertTrue(asignacionesContiene(5,2, asignaciones));
    }

}
