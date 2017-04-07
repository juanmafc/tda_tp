import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DFS {

    private boolean[] verticeVisitado;
    private int[] predecesor;
    private int[] tiempoDescubierto;
    int tiempo;

    private GrafoDirigido grafo;
    //private Set<Integer> verticesVisitados = new HashSet<>();

    public DFS(GrafoDirigido grafo) {
        this.grafo = grafo;
        int cantidadDeVertices = grafo.getCantidadVertices();

        this.verticeVisitado = new boolean[cantidadDeVertices ];
        this.predecesor = new int[cantidadDeVertices];
        this.tiempoDescubierto = new int[cantidadDeVertices];

        for (int v = 0; v < cantidadDeVertices; v++) {
            this.verticeVisitado[v] = false;
            this.predecesor[v] = -1;
            this.tiempoDescubierto[v] = -1;
        }


        this.tiempo = 0;
        for (int v = 0; v < cantidadDeVertices; v++) {

            if (!this.verticeVisitado[v]){
                this.analizar(v);
            }
        }

    }

    private void analizar(int vertice) {
        this.verticeVisitado[vertice] = true;
        //TODO: agregar vertice a los arboles DFSs
        this.tiempo++;
        this.tiempoDescubierto[vertice] = this.tiempo;

        for (int verticeAdyacente : this.grafo.getVerticesAdyacentesA(vertice) ) {
            if ( !this.verticeVisitado[verticeAdyacente] ) {
                this.predecesor[verticeAdyacente] = vertice;
                this.analizar(verticeAdyacente);
            }
        }
    }


    public void printearDFS() {
        //TODO: no funcina asi
        System.out.println(this.predecesor);
    }

}
