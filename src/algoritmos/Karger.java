package algoritmos;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by User on 19/06/2017.
 */
public class Karger {

    public Karger (GrafoNoDirigido grafo) {
        this.adivinarCorteMinimo(grafo);
    }

    public void adivinarCorteMinimo(GrafoNoDirigido grafo) {

        List<Pair<Integer, Integer>> listaDeAristas = grafo.getListaDeAristas();
        for (int i = 0; i < grafo.getCantidadVertices() - 2; i++ ) {
            Pair<Integer, Integer> aristaRandom = this.elegirAristaRandom(listaDeAristas);
            grafo.colapsar(aristaRandom.getKey(), aristaRandom.getValue());
            //Actualizo la lista de aristas
            listaDeAristas = grafo.getListaDeAristas();
        }

        grafo.printGrafo();
        //return en unico corte del grafo colapsado
    }

    private Pair<Integer, Integer> elegirAristaRandom(List<Pair<Integer, Integer>> listaDeAristas) {
        Random randomGenerator = new Random();
        int aristaElegida = randomGenerator.nextInt(listaDeAristas.size());
        return listaDeAristas.get( aristaElegida );
    }

}
