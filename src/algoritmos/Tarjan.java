package algoritmos;

import java.util.HashSet;
import java.util.LinkedList;
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
                //this.analizar(v);
                this.analizarIterativo(v);
            }
        }

        //si es raiz y tiene mas de un hijo --> es pto de articulacion
        for (int vertice = 0; vertice < this.grafo.getCantidadVertices(); vertice++ ) {
            if ((this.predecesor[vertice] == -1) && (this.cantidadHijos[vertice] > 1)) {
                this.puntosDeArticulacion.add(vertice);
            }
        }

        System.out.println("Final:");
        this.printearPredecesores();
        this.printearCantidadDeHijos();

    }



    private void analizar(int vertice) {
        this.verticeVisitado[vertice] = true;

        this.tiempo++;
        this.tiempoDescubierto[vertice] = this.tiempo;
        this.low[vertice] = this.tiempoDescubierto[vertice];




        for (int verticeAdyacente : this.grafo.getVerticesAdyacentesA(vertice) ) {

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

                //ACA ES DONDE CREO QUE TENGO QUE MOVER TODOS, AL LUGAR DE SI YA FUE VISITADO

                if ( verticeAdyacente != this.predecesor[vertice] ) {
                    this.low[vertice] = Math.min(this.low[vertice], this.tiempoDescubierto[verticeAdyacente]);
                }

            }
        }

    }


    private void analizarIterativo(int vertice) {
        //Lo borro porque se visita cuando se saca del stack
        //this.verticeVisitado[vertice] = true;



        //El tiempo deberia ser el momoento en que fue pusheado al stack
        this.tiempo++;
        this.tiempoDescubierto[vertice] = this.tiempo;
        this.low[vertice] = this.tiempoDescubierto[vertice];




        int verticePredecesor = -1;

        boolean[] verticeEntroAlStack = new boolean[this.grafo.getCantidadVertices()];
        this.stackDFS.push(vertice);
        verticeEntroAlStack[vertice] = true;

        while (!this.stackDFS.empty()){
            System.out.println(this.stackDFS);
            verticePredecesor = this.stackDFS.peek();

            LinkedList<Integer> verticesAdyacentes = this.grafo.getVerticesAdyacentesA(verticePredecesor);

            if (!verticesAdyacentes.isEmpty()) {
                if (!this.verticeVisitado[verticePredecesor]) {


                    /* TODO: SUMAR CANTIDAD DE HIJOS NO VISITADOS
                    //si el vertice adyacente no fue visitado entonces pasa a ser un hijo de vertice
                    this.cantidadHijos[verticePredecesor]++;
                    */

                    //TODO: HACER QUE SE LLENE CORRECTAMENTE EL STACK DE VISITA

                    for (int verticeAdyacente : verticesAdyacentes) {
                        if (!verticeEntroAlStack[verticeAdyacente]) {
                            this.stackDFS.push(verticeAdyacente);
                            verticeEntroAlStack[verticeAdyacente] = true;
                            this.predecesor[verticeAdyacente] = verticePredecesor;
                            this.cantidadHijos[verticePredecesor]++;
                        }
                    }
                    this.grafo.elimiarHijosDe(verticePredecesor);



                    /* TODO: HACER QUE SE GUARDE EL menorLow POR CADA NODO
                    this.low[verticePredecesor] = Math.min(this.low[verticePredecesor], this.low[verticeAdyacente]);

                    if ( ( this.low[verticeAdyacente] >= this.tiempoDescubierto[vertice]) &&
                            ( this.predecesor[verticePredecesor] != -1) ) { //O sea, y NO es raiz del arbol DFS
                        this.puntosDeArticulacion.add(vertice);
                    }
                    */
                } else {

                    //ACA ES DONDE CREO QUE TENGO QUE MOVER TODOS, AL LUGAR DE SI YA FUE VISITADO

                    /*TODO: ESTO SERIA AL MOMENTO DE DESAPILARLO PORQUE YA SE VISITO*/
                    /*
                    if ( verticeAdyacente != this.predecesor[vertice] ) {
                        this.low[vertice] = Math.min(this.low[vertice], this.tiempoDescubierto[verticeAdyacente]);
                    }
                    */

                }
            }
            else {
                //Si no tiene hijos, entonces lo desapilo (y hago otras cosas)
                this.stackDFS.pop();
                this.printearPredecesores();
                this.printearCantidadDeHijos();
            }
        }

    }


    public void printearPredecesores() {
        System.out.print("Predecesores [ ");
        for (int i = 0; i < this.grafo.getCantidadVertices(); i++ ) {
            if ( this.predecesor[i] == -1 ){
                System.out.print(i + "<-Raiz; ");
            }
            else {
                System.out.print(i + "<-" + this.predecesor[i] + "; ");
            }

        }
        System.out.println("]");
    }

    public void printearCantidadDeHijos() {
        System.out.print("CantidadHijos [ ");
        for (int i = 0; i < this.grafo.getCantidadVertices(); i++ ) {
            System.out.print(i + ":" + this.cantidadHijos[i] + "; ");
        }
        System.out.println("]");
    }


    /**
     * Devuelve el conjunto de vértices que son puntos de articulación del grafo.
     * @return   El conjunto de vértices que son puntos de articulación.
     */
    public Set<Integer> getPuntosDeArticulacion()  {
        return this.puntosDeArticulacion;
    }


}
