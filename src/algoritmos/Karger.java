package algoritmos;

import javafx.util.Pair;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by User on 19/06/2017.
 */
public class Karger {

    private class Corte {
        public int tamanio;
        public Set<Integer> A;
        public Set<Integer> B;
    }

    public Karger (GrafoNoDirigido grafo) {

        int cantidadVertices = grafo.getCantidadVertices();
        GrafoNoDirigido grafoOriginal = grafo;
        Corte corteMinimo = null;
        for (int i = 0; i < cantidadVertices; i++) {
            GrafoNoDirigido grafoAuxiliar = new GrafoNoDirigido(grafoOriginal);
            Corte nuevoCorte = this.adivinarCorteMinimo(grafoAuxiliar);
            if (corteMinimo == null) {
                corteMinimo = nuevoCorte;
            }
            else {
                if ( nuevoCorte.tamanio < corteMinimo.tamanio ) {
                    corteMinimo = nuevoCorte;
                }
            }

        }

        System.out.println("El corte minimo es: " + corteMinimo.tamanio);
    }

    public Corte adivinarCorteMinimo(GrafoNoDirigido grafo) {
        List<Pair<Integer, Integer>> listaDeAristas = grafo.getListaDeAristas();
        for (int i = 0; i < grafo.getCantidadVertices() - 2; i++ ) {
            Pair<Integer, Integer> aristaRandom = this.elegirAristaRandom(listaDeAristas);
            grafo.colapsar(aristaRandom.getKey(), aristaRandom.getValue());
            //Actualizo la lista de aristas
            listaDeAristas = grafo.getListaDeAristas();
        }

        grafo.printGrafo();
        System.out.println("---------");
        //return en unico corte del grafo colapsado
        Corte corteMinimoAdivinado = new Corte();
        corteMinimoAdivinado.A = grafo.getVerticesRepresentadosPor(0);
        corteMinimoAdivinado.B = grafo.getVerticesRepresentadosPor(1);
        corteMinimoAdivinado.tamanio = grafo.getListaDeAristas().size();
        return corteMinimoAdivinado;
    }

    private Pair<Integer, Integer> elegirAristaRandom(List<Pair<Integer, Integer>> listaDeAristas) {
        Random randomGenerator = new Random();
        int aristaElegida = randomGenerator.nextInt(listaDeAristas.size());
        return listaDeAristas.get( aristaElegida );
    }

}
