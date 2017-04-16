package algoritmos;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by User on 15/04/2017.
 */
public class MatrimoniosEstables {

    private List<Pair<Integer, Integer>> compromisos;

    /*
        hombres[i] es la lista de preferencia del hombre i
        mujeres[i] es la lista de preferencia de la mujer i
     */
    public MatrimoniosEstables(int[][] hombres, int[][] mujeres) {

        int n = hombres.length;

        Queue<Integer> pendientes = new LinkedList<>();
        int[] siguienteDeseado = new int[n];

        /*Indexado por mujeres
          mujerComprometidaCon[5] = 8 significa que la mujer 5 esta comprometida con el hombre 8
        */
        int [] mujerComprometidaCon = new int[n];

        /*
            preferenciaMujeres[2][5] = 3 significa que la mujer 2, tiene al hombre 5 como 3er favorito
         */
        int[][] preferenciaMujeres = new int[n][n];

        for (int i = 0; i < n; i++) {
            pendientes.add(i);
            siguienteDeseado[i] = 0; //El siguiente deseado de todos es el primero de su lista de preferencia
            mujerComprometidaCon[i] = -1; //-1 es que no estan comprometidas con nadie

            for (int j = 0; j < n; j++ ) {
                preferenciaMujeres[i][ (mujeres[i][j]) ] = j;
            }
        }


        while (!pendientes.isEmpty()) {
            int hombre = pendientes.poll();

            int mujerDeseada = hombres[hombre][ (siguienteDeseado[hombre]) ];
            siguienteDeseado[hombre]++;

            int rival = mujerComprometidaCon[mujerDeseada];

            if ( rival < 0 ) {
                mujerComprometidaCon[mujerDeseada] = hombre;
            }
            else if (preferenciaMujeres[mujerDeseada][hombre] < preferenciaMujeres[mujerDeseada][rival]) {
                mujerComprometidaCon[mujerDeseada] = hombre;
                pendientes.add(rival);
            }
            else {
                pendientes.add(hombre);
            }
        }


        this.compromisos = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            this.compromisos.add(new Pair<>(mujerComprometidaCon[i], i));
        }

    }


    public List<Pair<Integer, Integer>> getCompromisos() {
        return this.compromisos;
    }



}
