import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GrafoDirigido {

    private int cantidadVertices;

    private LinkedList<Integer>[] aristas;
    private int cantidadAristas;


    public GrafoDirigido(int cantidadVertices) {
        this.cantidadVertices = cantidadVertices;
        this.cantidadAristas = 0;
        this.aristas = new LinkedList[cantidadVertices];

        for (int i = 0; i < cantidadVertices; i++) {
            this.aristas[i] = new LinkedList<Integer>();
        }

    }

    public void agregarArista(int origen, int destino) {
        this.aristas[origen].add(destino);
        this.cantidadAristas++;
    }

    public int getCantidadVertices() {
        return this.cantidadVertices;
    }

    public int getCantidadAristas() {
        return this.cantidadAristas;
    }


    public  LinkedList<Integer> getVerticesAdyacentesA(int vertice) {
        return this.aristas[vertice];
    }

}
