package algoritmos;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by User on 11/04/2017.
 */
public class Tarjan {



    private boolean[] verticeVisitado;
    private int[] predecesor;
    private int[] tiempoDescubierto;
    private int[] cantidadHijos;
    int tiempo;

    private Set<Integer> puntosDeArticulacion;
    private int[] low;

    private GrafoNoDirigido grafo;


    private Stack<Integer> stackDFS;


    private void inicializar(GrafoNoDirigido grafo) {
        this.grafo = grafo;
        int cantidadDeVertices = grafo.getCantidadVertices();

        this.verticeVisitado = new boolean[cantidadDeVertices];
        this.predecesor = new int[cantidadDeVertices];
        this.tiempoDescubierto = new int[cantidadDeVertices];
        this.cantidadHijos = new int[cantidadDeVertices];

        this.puntosDeArticulacion = new HashSet<>();
        this.low = new int[cantidadDeVertices];

        this.stackDFS = new Stack<>();

        for (int v = 0; v < cantidadDeVertices; v++) {
            this.verticeVisitado[v] = false;
            this.predecesor[v] = -1;
            this.tiempoDescubierto[v] = -1;
            this.cantidadHijos[v] = 0;
        }
        this.tiempo = 0;
    }






    public Tarjan(GrafoNoDirigido grafo) {
        this.inicializar(grafo);

        for (int v = 0; v < this.grafo.getCantidadVertices(); v++) {
            if (!this.verticeVisitado[v]) {
                this.analizar(v);
            }
        }

        //si es raiz y tiene mas de un hijo --> es pto de articulacion
        for (int vertice = 0; vertice < this.grafo.getCantidadVertices(); vertice++ ) {
            if ((this.predecesor[vertice] == -1) && (this.cantidadHijos[vertice] > 1)) {
                this.puntosDeArticulacion.add(vertice);
            }
        }
    }



    private void analizar(int vertice) {
        //TODO: AL PASARLO A ITERATIVO, LA CANTIDAD DE HIJOS VA A TENER QUE ESTAR AFUERA cantidadHijos[i]
        int cantidadHijos = 0;

        this.verticeVisitado[vertice] = true;

        this.tiempo++;
        this.tiempoDescubierto[vertice] = this.tiempo;
        this.low[vertice] = this.tiempoDescubierto[vertice];


        //this.stackDFS.addAll( this.grafo.getVerticesAdyacentesA(vertice) );


        for (int verticeAdyacente : this.grafo.getVerticesAdyacentesA(vertice) ) {
        //while (!this.stackDFS.empty()){
            //int verticeAdyacente = this.stackDFS.pop();

            if ( !this.verticeVisitado[verticeAdyacente] ) {

                //cantidadHijos++; //si el vertice adyacente no fue visitado entonces pasa a ser un hijo de vertice
                this.cantidadHijos[vertice]++; //si el vertice adyacente no fue visitado entonces pasa a ser un hijo de vertice

                this.predecesor[verticeAdyacente] = vertice;

                this.analizar(verticeAdyacente);

                //Lo que sigue SOLO se corre luego de visitar TODA una rama, por eso sirve el cantidadHijos > 1, o sea, si tiene dos ramas ya esta
                //PERO es lo mismo fijarme antes o despues si tiene mas de un hijo y ningun predecesor y es O(V)

                this.low[vertice] = Math.min(this.low[vertice], this.low[verticeAdyacente]);

                if ( ( this.low[verticeAdyacente] >= this.tiempoDescubierto[vertice]) &&
                        ( this.predecesor[vertice] != -1) ) { //O sea, y NO es raiz del arbol DFS
                    this.puntosDeArticulacion.add(vertice);
                }
            }
            else {
                if ( verticeAdyacente != this.predecesor[vertice] ) {
                    this.low[vertice] = Math.min(this.low[vertice], this.tiempoDescubierto[verticeAdyacente]);
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
