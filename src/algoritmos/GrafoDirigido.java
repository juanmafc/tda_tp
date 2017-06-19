package algoritmos;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class GrafoDirigido {

    protected int cantidadVertices;
    protected Pair<Set<Integer>,LinkedList<Integer>>[] aristas;
    protected int cantidadAristas;


    public GrafoDirigido(int cantidadVertices) {
        this.cantidadVertices = cantidadVertices;
        this.cantidadAristas = 0;
        this.aristas = new Pair[cantidadVertices];

        for (int i = 0; i < cantidadVertices; i++) {
            Set<Integer> verticesQueRepresenta = new HashSet<>();
            verticesQueRepresenta.add(i);
            this.aristas[i] = new Pair(verticesQueRepresenta, new LinkedList<>() );
        }

    }

    public GrafoDirigido(GrafoNoDirigido grafoOriginal) {
        this.cantidadAristas = grafoOriginal.cantidadAristas;
        this.aristas = grafoOriginal.aristas;
        this.cantidadVertices = grafoOriginal.cantidadVertices;
    }

    public void agregarArista(int origen, int destino) {
        this.aristas[origen].getValue().add(destino);
        this.cantidadAristas++;
    }

    public int getCantidadVertices() {
        return this.cantidadVertices;
    }

    public int getCantidadAristas() {
        return this.cantidadAristas;
    }


    public  LinkedList<Integer> getVerticesAdyacentesA(int vertice) {
        return this.aristas[vertice].getValue();
    }


    public void printGrafo() {
        for (int i = 0; i < this.aristas.length; i++) {
            //System.out.println(i + "-->" + this.getVerticesAdyacentesA(i));
            System.out.println(i + "-->" + this.aristas[i]);
        }
    }
}
