import java.util.*;

public class DFS {

    private boolean[] verticeVisitado;
    private int[] predecesor;
    private int[] tiempoDescubierto;
    private int[] tiempoFinalizacion;
    private Stack<Integer> verticesTerminados;
    int tiempo;

    private Set<Integer> puntosDeArticulacion;
    private int[] nivelMasBajo;

    private GrafoDirigido grafo;
    //private Set<Integer> verticesVisitados = new HashSet<>();

    public DFS(GrafoDirigido grafo) {
        this.inicializarDFS(grafo);

        for (int v = 0; v < this.grafo.getCantidadVertices(); v++) {
            if (!this.verticeVisitado[v]){
                this.analizar(v);
                //TODO: falta el caso en que la raiz del arbol DFS sea el punto de articulacion
            }
        }
    }

    public DFS(GrafoDirigido grafoTraspuesto, Stack<Integer> stackVertices) {
        this.inicializarDFS(grafoTraspuesto);

        for (Integer v : stackVertices) {
            if (!this.verticeVisitado[v]){
                this.analizar(v);
            }
        }
    }


    private void inicializarDFS(GrafoDirigido grafo) {
        this.grafo = grafo;
        int cantidadDeVertices = grafo.getCantidadVertices();

        this.verticeVisitado = new boolean[cantidadDeVertices];
        this.predecesor = new int[cantidadDeVertices];
        this.tiempoDescubierto = new int[cantidadDeVertices];
        this.tiempoFinalizacion = new int[cantidadDeVertices];
        this.verticesTerminados = new Stack<>();

        this.puntosDeArticulacion = new HashSet<>();
        this.nivelMasBajo = new int[cantidadDeVertices];

        for (int v = 0; v < cantidadDeVertices; v++) {
            this.verticeVisitado[v] = false;
            this.predecesor[v] = -1;
            this.tiempoDescubierto[v] = -1;
            this.tiempoFinalizacion[v] = -1;
        }
        this.tiempo = 0;
    }

    private void analizar(int vertice) {
        this.verticeVisitado[vertice] = true;
        //TODO: agregar vertice a los arboles DFSs
        this.tiempo++;
        this.tiempoDescubierto[vertice] = this.tiempo;
        this.nivelMasBajo[vertice] = this.tiempoDescubierto[vertice];

        for (int verticeAdyacente : this.grafo.getVerticesAdyacentesA(vertice) ) {
            if ( !this.verticeVisitado[verticeAdyacente] ) {
                this.predecesor[verticeAdyacente] = vertice;
                this.analizar(verticeAdyacente);
                this.nivelMasBajo[vertice] = Math.min(this.nivelMasBajo[vertice], this.nivelMasBajo[verticeAdyacente]);

                if ( ( this.nivelMasBajo[verticeAdyacente] >= this.tiempoDescubierto[vertice]) &&
                     ( this.predecesor[vertice] != -1) ) { //O sea, y NO es raiz del arbol DFS
                    this.puntosDeArticulacion.add(vertice);
                }
            }
            else {
                if ( verticeAdyacente != this.predecesor[vertice] ) {
                    this.nivelMasBajo[vertice] = Math.min(this.nivelMasBajo[vertice], this.tiempoDescubierto[verticeAdyacente]);
                }

            }
        }
        this.tiempo++;
        this.tiempoFinalizacion[vertice] = this.tiempo;
        //Se van agregando en orden de finalizacion (primero que termina, primero que se agrega), al final el ultimo en finalizar queda primero en el stack
        this.verticesTerminados.push(vertice);
    }


    public void printearDFS() {
        //TODO: no funcina asi
        System.out.println(this.predecesor);
    }

    public Stack<Integer> getStackDeFinalizacion() {
        return this.verticesTerminados;
    }

    public Set<Integer> getPuntosDeArticulacion() {
        return this.puntosDeArticulacion;
    }
}
