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
                //this.DFSRecursivo(v);
                this.DFSIterativo(v);
            }
        }

        /*
        System.out.println("Final:");
        this.printearPredecesores();
        this.printearCantidadDeHijos();
        this.printearTiempoDescubiertoYLow();
        */

    }



    private void DFSRecursivo(int vertice) {
        this.verticeVisitado[vertice] = true;

        this.tiempo++;
        this.tiempoDescubierto[vertice] = this.tiempo;
        this.low[vertice] = this.tiempoDescubierto[vertice];



        for (int verticeAdyacente : this.grafo.getVerticesAdyacentesA(vertice) ) {

            if ( !this.verticeVisitado[verticeAdyacente] ) {
                
                this.cantidadHijos[vertice]++; //si el vertice adyacente no fue visitado entonces pasa a ser un hijo de vertice

                this.predecesor[verticeAdyacente] = vertice;
                this.DFSRecursivo(verticeAdyacente);
                this.low[vertice] = Math.min(this.low[vertice], this.low[verticeAdyacente]);

                //si es raiz y tiene mas de un hijo --> es pto de articulacion
                if ( ( this.predecesor[vertice] == -1)  && ( this.cantidadHijos[vertice] > 1)   ) {
                    this.puntosDeArticulacion.add(vertice);
                }


                if ( ( this.low[verticeAdyacente] >= this.tiempoDescubierto[vertice]) &&
                     ( this.predecesor[vertice] != -1) ) { //O sea, y NO es raiz del arbol DFS
                    this.puntosDeArticulacion.add(vertice);
                }
            }
            else {
                //Si ese adyacente YA fue visitado, entonces me interesa comparar con su tiempoDescubierto
                //Si el que saque es diferente al predecesor de del que estoy analizando
                if ( verticeAdyacente != this.predecesor[vertice] ) {
                    this.low[vertice] = Math.min(this.low[vertice], this.tiempoDescubierto[verticeAdyacente]);
                }


            }
        }

    }


    private void DFSIterativo(int vertice) {

        //El tiempo es el momoento en que fue pusheado al stack
        this.tiempo++;
        this.tiempoDescubierto[vertice] = this.tiempo;
        this.low[vertice] = this.tiempoDescubierto[vertice];


        int verticeAnalizado = -1;

        this.stackDFS.push(vertice);
        this.verticeVisitado[vertice] = true; //Lo considero visitado cuando entra al stack

        while (!this.stackDFS.empty()){

            verticeAnalizado = this.stackDFS.peek();
            LinkedList<Integer> verticesAdyacentes = this.grafo.getVerticesAdyacentesA(verticeAnalizado);

            //Si tiene adyacentes, saco el primer adyacente
            if (!verticesAdyacentes.isEmpty()) {
                int verticeAdyacente = verticesAdyacentes.pollFirst();

                //Si el adyacente NO fue visitado lo pusheo al stack, e inicializo si tiempo, low y predecesor
                //Ademas cuento a ese verticeAdyacente como hijo del verticeAnalizado (si el adyacente no fue visitado, el verticeAnalizado lo considera su hijo)
                if (!this.verticeVisitado[verticeAdyacente]) {
                    this.stackDFS.push(verticeAdyacente);
                    this.verticeVisitado[verticeAdyacente] = true;

                    this.tiempo++;
                    this.tiempoDescubierto[verticeAdyacente] = this.tiempo;
                    this.low[verticeAdyacente] = this.tiempoDescubierto[verticeAdyacente];

                    this.predecesor[verticeAdyacente] = verticeAnalizado;
                    this.cantidadHijos[verticeAnalizado]++;
                }
                else {
                    //Si el adyacente ya fue visitado y es diferente al predecesor del que estoy analizando
                    //Si ese adyacente YA fue visitado, entonces me interesa comparar con su tiempoDescubierto
                    if ( verticeAdyacente != this.predecesor[verticeAnalizado] ) {
                        this.low[verticeAnalizado] = Math.min(this.low[verticeAnalizado], this.tiempoDescubierto[verticeAdyacente]);
                    }
                }
            }
            else {
                //Si ya no tiene adyacentes, entonces lo desapilo (o sea, que no tiene mas vertices para visitar)
                int verticeVisitado = this.stackDFS.pop();

                //Si NO es raiz
                if (this.predecesor[verticeVisitado] != -1 ) {
                    //Saco el predecesor del vertice que estoy analizando y comparo los lows para saber si hay que actualizar el low del verticePredecesor y si es pto de art
                    int verticePredecesor = this.predecesor[verticeVisitado];
                    this.low[verticePredecesor] = Math.min(this.low[verticeVisitado], this.low[verticePredecesor]);

                    if ( (this.low[verticeVisitado] >= this.tiempoDescubierto[verticePredecesor]) &&
                         ( ( this.predecesor[verticePredecesor ] != -1) ) ) {
                        this.puntosDeArticulacion.add(verticePredecesor);
                    }
                }
                else {
                    //Es raiz y tiene mas de un hijo -> es pto de art
                    if ( this.cantidadHijos[verticeVisitado] > 1 ) {
                        this.puntosDeArticulacion.add(verticeVisitado);
                    }
                }
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

    public void printearTiempoDescubiertoYLow() {
        System.out.print("TiempoDescubierto/Low [ ");
        for (int i = 0; i < this.grafo.getCantidadVertices(); i++ ) {
            System.out.print(i + ":" + this.tiempoDescubierto[i] + "/" + this.low[i] + "; ");
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
