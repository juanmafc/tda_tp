package algoritmos;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by User on 11/04/2017.
 */
public class GrafoNoDirigido extends  GrafoDirigido {


    public GrafoNoDirigido(int cantidadVertices) {
        super(cantidadVertices);
    }

    public void agregarArista(int origen, int destino) {
        this.aristas[origen].getValue().add(destino);
        this.aristas[destino].getValue().add(origen);

        this.cantidadAristas++;
    }

    public void colapsar(int vertice1, int vertice2) {
        Pair<Set<Integer>,LinkedList<Integer>>[] listaDeAdyacenciaColapsada = new Pair[this.aristas.length - 1];
        for (int i = 0; i < listaDeAdyacenciaColapsada.length; i++) {
            listaDeAdyacenciaColapsada[i] = new Pair(new HashSet<>(), new LinkedList<>() );
        }

        //Me quedo con el vertice de menor indice, a este numero van a colapsar ambos vertices
        int indiceMinimo = Math.min(vertice1, vertice2);
        int indiceMaximo = Math.max(vertice1, vertice2);

        //Los vertices que sean menores al otro indice que NO sea el minimo van a tener como indice (su indice anterior - 1)
        for (int i = 0; i < this.aristas.length; i++ ) {

            if (i < indiceMaximo) {
                //Le agrego todos los vertices que representa el vertice i
                listaDeAdyacenciaColapsada[i].getKey().addAll(this.aristas[i].getKey());

                LinkedList<Integer> listaDeAdyacencia = this.aristas[i].getValue();
                for (int verticeAdyacente : listaDeAdyacencia) {
                    if (verticeAdyacente < indiceMaximo){
                        //Pasa igual
                        listaDeAdyacenciaColapsada[i].getValue().add(verticeAdyacente);
                    }
                    if (verticeAdyacente > indiceMaximo) {
                        //Pasa con un -1
                        listaDeAdyacenciaColapsada[i].getValue().add(verticeAdyacente - 1);
                    }
                    if (verticeAdyacente == indiceMaximo) {
                        //Si el indice adyacente es igual al indicemaximo, como colapso al indice minimo, pongo este ultimo como adyacente
                        listaDeAdyacenciaColapsada[i].getValue().add(indiceMinimo);
                    }
                }
            }
            if (i > indiceMaximo) {
                //Lo mismo que arriba pero el indice de la lista de adyacencia colapsada es (i - 1)
                listaDeAdyacenciaColapsada[i - 1].getKey().addAll(this.aristas[i].getKey());
                LinkedList<Integer> listaDeAdyacencia = this.aristas[i].getValue();
                for (int verticeAdyacente : listaDeAdyacencia) {
                    if (verticeAdyacente < indiceMaximo){
                        //Pasa igual
                        listaDeAdyacenciaColapsada[i - 1].getValue().add(verticeAdyacente);
                    }
                    if (verticeAdyacente > indiceMaximo) {
                        //Pasa con un -1
                        listaDeAdyacenciaColapsada[i - 1].getValue().add(verticeAdyacente - 1);
                    }
                    if (verticeAdyacente == indiceMaximo) {
                        //Si el indice adyacente es igual al indicemaximo, como colapso al indice minimo, pongo este ultimo como adyacente
                        listaDeAdyacenciaColapsada[i - 1].getValue().add(indiceMinimo);
                    }
                }
            }
        }

        //Por ultimo colapso todas las aristas y los vertices originales que representa el indice maximo en el indice minimo
        listaDeAdyacenciaColapsada[indiceMinimo].getKey().addAll(this.aristas[indiceMaximo].getKey());
        LinkedList<Integer> listaDeAdyacencia = this.aristas[indiceMaximo].getValue();
        for (int verticeAdyacente : listaDeAdyacencia) {
            if (verticeAdyacente < indiceMaximo){
                //Pasa igual
                listaDeAdyacenciaColapsada[indiceMinimo].getValue().add(verticeAdyacente);
            }
            if (verticeAdyacente > indiceMaximo) {
                //Pasa con un -1
                listaDeAdyacenciaColapsada[indiceMinimo].getValue().add(verticeAdyacente - 1);
            }
            if (verticeAdyacente == indiceMaximo) {
                //Si el indice adyacente es igual al indicemaximo, como colapso al indice minimo, pongo este ultimo como adyacente
                listaDeAdyacenciaColapsada[indiceMinimo].getValue().add(indiceMinimo);
            }
        }

        this.aristas = listaDeAdyacenciaColapsada;
    }
}
