package tests;

import algoritmos.MatrimoniosEstables;
import javafx.util.Pair;
import junit.framework.TestCase;

import java.util.List;


public class MatrimoniosEstablesTest extends TestCase {


    private boolean compromisosContienePareja(int hombre, int mujer, List<Pair<Integer, Integer>> compromisos) {
        return compromisos.contains(new Pair<>(hombre, mujer));
    }

    public void testMatrimoniosConN2() {
        int n = 2;
        int[][] hombres = new int[n][n];
        int[][] mujeres = new int[n][n];

        hombres[0][0] = 0;
        hombres[0][1] = 1;
        hombres[1][0] = 1;
        hombres[1][1] = 0;

        mujeres[0][0] = 0;
        mujeres[0][1] = 1;
        mujeres[1][0] = 1;
        mujeres[1][1] = 0;



        MatrimoniosEstables matrimonios = new MatrimoniosEstables(hombres, mujeres);
        List<Pair<Integer, Integer>> compromisos = matrimonios.getCompromisos();

        assertEquals(2, compromisos.size());

        assertTrue(compromisosContienePareja(0,0,compromisos));
        assertTrue(compromisosContienePareja(1,1,compromisos));

        System.out.println(matrimonios.getCompromisos());
    }

    public void testMatrimoniosConN2YMismaPreferencia() {
        int n = 2;
        int[][] hombres = new int[n][n];
        int[][] mujeres = new int[n][n];

        hombres[0][0] = 0;
        hombres[0][1] = 1;
        hombres[1][0] = 0;
        hombres[1][1] = 1;

        mujeres[0][0] = 0;
        mujeres[0][1] = 1;
        mujeres[1][0] = 0;
        mujeres[1][1] = 1;



        MatrimoniosEstables matrimonios = new MatrimoniosEstables(hombres, mujeres);
        List<Pair<Integer, Integer>> compromisos = matrimonios.getCompromisos();

        assertEquals(2, compromisos.size());
        assertTrue(compromisosContienePareja(0,0,compromisos));
        assertTrue(compromisosContienePareja(1,1,compromisos));

        System.out.println(matrimonios.getCompromisos());
    }

    public void testMatrimoniosConN2YPreferneciasOpuestas() {
        int n = 2;
        int[][] hombres = new int[n][n];
        int[][] mujeres = new int[n][n];

        hombres[0][0] = 0;
        hombres[0][1] = 1;
        hombres[1][0] = 1;
        hombres[1][1] = 0;

        mujeres[0][0] = 1;
        mujeres[0][1] = 0;
        mujeres[1][0] = 0;
        mujeres[1][1] = 1;



        MatrimoniosEstables matrimonios = new MatrimoniosEstables(hombres, mujeres);
        List<Pair<Integer, Integer>> compromisos = matrimonios.getCompromisos();

        assertEquals(2, compromisos.size());
        assertTrue(compromisosContienePareja(0,0,compromisos));
        assertTrue(compromisosContienePareja(1,1,compromisos));

        System.out.println(matrimonios.getCompromisos());
    }


    public void testMatrimoniosConN4EjemploTransformadoDeHospitales1() {
        int n = 4;
        int[][] estudiantes = new int[n][n];
        int[][] hospitales = new int[n][n];


        //TODO: IMPORTANTE: AL HACER LA TRANSFORMACION, AL REPETIR HOSPITALES, NO SE LES PUEDE DAR EL MISMO NUMERO
        //todo: ACA H0->0, 1, 2;  H1->3

        /*ASI NO SIRVE
        estudiantes[0][0] = 0;
        estudiantes[0][1] = 0;
        estudiantes[0][2] = 0;
        estudiantes[0][3] = 1;

        estudiantes[1][0] = 1;
        estudiantes[1][1] = 0;
        estudiantes[1][2] = 0;
        estudiantes[1][3] = 0;

        estudiantes[2][0] = 1;
        estudiantes[2][1] = 0;
        estudiantes[2][2] = 0;
        estudiantes[2][3] = 0;

        estudiantes[3][0] = 1;
        estudiantes[3][1] = 0;
        estudiantes[3][2] = 0;
        estudiantes[3][3] = 0;
        */

        estudiantes[0][0] = 0;
        estudiantes[0][1] = 1;
        estudiantes[0][2] = 2;
        estudiantes[0][3] = 3;

        estudiantes[1][0] = 3;
        estudiantes[1][1] = 0;
        estudiantes[1][2] = 1;
        estudiantes[1][3] = 2;

        estudiantes[2][0] = 3;
        estudiantes[2][1] = 0;
        estudiantes[2][2] = 1;
        estudiantes[2][3] = 2;

        estudiantes[3][0] = 3;
        estudiantes[3][1] = 0;
        estudiantes[3][2] = 1;
        estudiantes[3][3] = 2;






        hospitales[0][0] = 0;
        hospitales[0][1] = 1;
        hospitales[0][2] = 3;
        hospitales[0][3] = 2;

        hospitales[1][0] = 0;
        hospitales[1][1] = 1;
        hospitales[1][2] = 3;
        hospitales[1][3] = 2;

        hospitales[2][0] = 0;
        hospitales[2][1] = 1;
        hospitales[2][2] = 3;
        hospitales[2][3] = 2;

        hospitales[3][0] = 2;
        hospitales[3][1] = 0;
        hospitales[3][2] = 1;
        hospitales[3][3] = 3;



        MatrimoniosEstables matrimonios = new MatrimoniosEstables(estudiantes, hospitales);
        List<Pair<Integer, Integer>> compromisos = matrimonios.getCompromisos();


        System.out.println(matrimonios.getCompromisos());
        assertEquals(4, compromisos.size());
        assertTrue(compromisosContienePareja(0,0,compromisos));
        assertTrue(compromisosContienePareja(1,1,compromisos));
        assertTrue(compromisosContienePareja(2,3,compromisos));
        assertTrue(compromisosContienePareja(3,2,compromisos));

    }
}
