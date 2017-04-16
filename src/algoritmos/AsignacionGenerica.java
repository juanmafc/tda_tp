package algoritmos;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 15/04/2017.
 */
public class AsignacionGenerica {

    List<Pair<Integer, Integer>> asignaciones;


    /*
        estudiantes[i] es la lista de preferencia del estudiante i
        hospitales[i] es el orden de merito del hospital i
        vacantes[i] tiene la cantidad de vacantes del hospital i
     */
    public AsignacionGenerica(int[][] estudiantes, int[][] hospitales, int[] vacantes) {

        int n = estudiantes.length;
        int m = hospitales.length;
        int[][] hombres = new int[n][n];
        int[][] mujeres = new int[n][n];


        //offsetBaseHospital[i] me dice en que numero comienza el nuevo indice del hospital i
        //por ejemplo, si tengo H0->3 vacantes, H1->2, H2->2 vacantes
        // offsetBaseHospital[0] = 0
        // offsetBaseHospital[1] = 3
        // offsetBaseHospital[2] = 5
        int[] offsetBaseHospital = new int[n];
        offsetBaseHospital[0] = 0;
        for (int i = 1; i < m; i++) {
            offsetBaseHospital[i] = offsetBaseHospital[i-1] + vacantes[i-1];
        }
        //TODO: ESTO NO LO HARIA O(N*M*V) ? o debido a que la sumatoria de las vacantes es = n seria O(N^2)
        for (int e = 0; e < estudiantes.length; e++) {
            int indiceHombres = 0;
            for (int i = 0; i < m; i++) {
                int hospital = estudiantes[e][i];
                //O sea, saco el primer hospital de la lista de preferencia del estudiante e y ese hospital
                //lo agrego vacantes[hospital] veces a la matriz de hombres
                int offset = 0;
                for (int v = 0; v < vacantes[hospital]; v++) {
                    hombres[e][indiceHombres] = offsetBaseHospital[hospital] + offset;
                    offset++;
                    indiceHombres++;
                }
            }
        }


        //traduccionMujerAHospital[i] = h -> me dice que la mujer i es equivalente al hospital h
        int[] traduccionMujerAHospital = new int[n];
        int indiceMujeres = 0;
        for (int h = 0; h < hospitales.length; h++) {
            for (int v = 0; v < vacantes[h]; v++ ) {
                traduccionMujerAHospital[indiceMujeres] = h;
                mujeres[indiceMujeres] = hospitales[h];
                indiceMujeres++;
            }
        }


        System.out.println("Hombres:");
        for (int i = 0; i < n; i++) {
            System.out.println();
            for (int j = 0; j < n; j++) {
                System.out.print(hombres[i][j] + "  ");
            }
        }

        System.out.println();
        System.out.println("Mujeres:");
        for (int i = 0; i < n; i++) {
            System.out.println();
            for (int j = 0; j < n; j++) {
                System.out.print(mujeres[i][j] + "  ");
            }
        }


        MatrimoniosEstables matrimonios = new MatrimoniosEstables(hombres, mujeres);
        List<Pair<Integer, Integer>> compromisos = matrimonios.getCompromisos();

        this.asignaciones = new LinkedList<>();

        for (Pair<Integer, Integer> compromiso : compromisos) {
            this.asignaciones.add(new Pair<>(compromiso.getKey(), traduccionMujerAHospital[compromiso.getValue()]) );
        }

        System.out.println();
        System.out.println("Asignaciones:");
        System.out.println(this.getAsignaciones());
    }

    public List<Pair<Integer, Integer>> getAsignaciones() {
        return this.asignaciones;
    }
}
