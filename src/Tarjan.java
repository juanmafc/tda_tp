import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 11/04/2017.
 */
public class Tarjan {



    private boolean[] verticeVisitado;
    private int[] predecesor;
    private int[] tiempoDescubierto;
    int tiempo;

    private Set<Integer> puntosDeArticulacion;
    private int[] nivelMasBajo;

    private GrafoNoDirigido grafo;

    public Tarjan(GrafoNoDirigido grafo) {
        this.inicializar(grafo);

        for (int v = 0; v < this.grafo.getCantidadVertices(); v++) {
            if (!this.verticeVisitado[v]) {
                this.analizar(v);
            }
        }
    }


    private void inicializar(GrafoNoDirigido grafo) {
        this.grafo = grafo;
        int cantidadDeVertices = grafo.getCantidadVertices();

        this.verticeVisitado = new boolean[cantidadDeVertices];
        this.predecesor = new int[cantidadDeVertices];
        this.tiempoDescubierto = new int[cantidadDeVertices];

        this.puntosDeArticulacion = new HashSet<>();
        this.nivelMasBajo = new int[cantidadDeVertices];

        for (int v = 0; v < cantidadDeVertices; v++) {
            this.verticeVisitado[v] = false;
            this.predecesor[v] = -1;
            this.tiempoDescubierto[v] = -1;
        }
        this.tiempo = 0;
    }

    private void analizar(int vertice) {

        int cantidadHijos = 0;

        this.verticeVisitado[vertice] = true;
        //TODO: agregar vertice a los arboles DFSs
        this.tiempo++;
        this.tiempoDescubierto[vertice] = this.tiempo;
        this.nivelMasBajo[vertice] = this.tiempoDescubierto[vertice];

        for (int verticeAdyacente : this.grafo.getVerticesAdyacentesA(vertice) ) {
            if ( !this.verticeVisitado[verticeAdyacente] ) {

                cantidadHijos++; //si el vertice adyacente no fue visitado entonces pasa a ser un hijo de vertice

                this.predecesor[verticeAdyacente] = vertice;
                this.analizar(verticeAdyacente);
                this.nivelMasBajo[vertice] = Math.min(this.nivelMasBajo[vertice], this.nivelMasBajo[verticeAdyacente]);

                //si es raiz y tiene mas de un hijo --> es pto de articulacion
                if ( ( this.predecesor[vertice] == -1)  && ( cantidadHijos > 1)   ) {
                    this.puntosDeArticulacion.add(vertice);
                }

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
    }





    /**
     * Devuelve el conjunto de vértices que son puntos de articulación del grafo.
     * @return   El conjunto de vértices que son puntos de articulación.
     */
    public Set<Integer> getPuntosDeArticulacion()  {
        return this.puntosDeArticulacion;
    }


}
