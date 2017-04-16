package algoritmos;

import java.util.*;

/**
 * Created by User on 10/04/2017.
 */
public class Kosaraju {

    private List<Set<Integer>> componentesFuertementeConexas;

    public Kosaraju(GrafoDirigido grafo) {
        this.componentesFuertementeConexas = new LinkedList<>();

        Stack<Integer> stackFinalizacion = this.primerDFS(grafo);
        GrafoDirigido grafoTraspuesto = grafo.getGrafoTraspuesto();
        this.segundoDFS(grafoTraspuesto, stackFinalizacion);
    }

    private void segundoDFS(GrafoDirigido grafoTraspuesto, Stack<Integer> stackVertices) {

        int cantidadDeVertices = grafoTraspuesto.getCantidadVertices();
        boolean[] verticeVisitado = new boolean[cantidadDeVertices ];
        for (int v = 0; v < cantidadDeVertices; v++) {
            verticeVisitado[v] = false;
        }

        while (!stackVertices.empty()){
            int v = stackVertices.pop();
            if (!verticeVisitado[v]){
                Set<Integer> componenteConexaActiva = new HashSet<>();
                this.componentesFuertementeConexas.add(componenteConexaActiva);
                this.segundoDFSRecursivo(v, verticeVisitado, grafoTraspuesto, componenteConexaActiva);
            }
        }


    }

    private void segundoDFSRecursivo(Integer vertice, boolean[] verticeVisitado, GrafoDirigido grafoTraspuesto, Set<Integer> componenteConexaActiva) {
        verticeVisitado[vertice] = true;
        componenteConexaActiva.add(vertice);

        for (int verticeAdyacente : grafoTraspuesto.getVerticesAdyacentesA(vertice) ) {
            if ( !verticeVisitado[verticeAdyacente] ) {
                this.segundoDFSRecursivo(verticeAdyacente, verticeVisitado, grafoTraspuesto, componenteConexaActiva);
            }
        }


    }

    private Stack<Integer> primerDFS(GrafoDirigido grafo) {

        Stack<Integer> verticesTerminados = new Stack<>();

        int cantidadDeVertices = grafo.getCantidadVertices();
        boolean[] verticeVisitado = new boolean[cantidadDeVertices ];
        for (int v = 0; v < cantidadDeVertices; v++) {
            verticeVisitado[v] = false;
        }

        for (int v = 0; v < cantidadDeVertices; v++) {
            if (!verticeVisitado[v]){
                this.primerDFSRecursivo(v, verticeVisitado, verticesTerminados, grafo);
            }
        }

        return verticesTerminados;
    }

    private void primerDFSRecursivo(int vertice, boolean[] verticeVisitado, Stack<Integer> verticesTerminados, GrafoDirigido grafo) {
        verticeVisitado[vertice] = true;
        for (int verticeAdyacente : grafo.getVerticesAdyacentesA(vertice) ) {
            if ( !verticeVisitado[verticeAdyacente] ) {
                this.primerDFSRecursivo(verticeAdyacente, verticeVisitado, verticesTerminados, grafo);
            }
        }
        //Se van agregando en orden de finalizacion (primero que termina, primero que se agrega), al final el ultimo en finalizar queda primero en el stack
        verticesTerminados.push(vertice);
    }


    /**
     * Devuelve el número de componentes fuertemente conexas del grafo.
     * @return  El número de componentes fuertemente conexas.
     */
    public int getCantidadCFC() {
        return this.componentesFuertementeConexas.size();
    }

    /**
     * Devuelve un conjunto de vértices que pertenecen a la componente fuertemente
     *     conexa cuyo número es pasado por parámetro.
     * @param cfcId   El número de componente fuertemente conexa.
     * @return        El conjunto de vértices que forman parte de la componente.
     * @throws IllegalArgumentException si no se cumple que {@code 0 <= sccId < getSCCNumber()}
     */
    public Set<Integer> getMiembrosDeCFC(int cfcId) {
        return this.componentesFuertementeConexas.get(cfcId);
    }


    public void printCFCs() {
        System.out.println("CFCs: " + this.componentesFuertementeConexas);
    }
}
