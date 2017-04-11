
public class Main {

    public static void main(String[] args){
/*
        pruebaGrafo1();
        pruebaGrafo2();
        pruebaGrafo3();
*/
/*
        pruebaGrafoTraspuesto1();
        pruebaGrafoTraspuesto2();
*/

/*
        pruebaDFS1();
        pruebaDFS2();
        pruebaDFS3();
*/

        //TODO: ¿que pasa si el grafo no es conexo?
        pruebaPtoArt1();
        pruebaPtoArt2();
        pruebaPtoArt3();
    }

    private static void pruebaPtoArt3() {
        GrafoNoDirigido grafo = new GrafoNoDirigido(7);

        grafo.agregarArista(0,1);
        grafo.agregarArista(0,2);
        grafo.agregarArista(1,3);
        grafo.agregarArista(2,3);
        grafo.agregarArista(2,4);
        grafo.agregarArista(3,5);
        grafo.agregarArista(3,6);
        grafo.agregarArista(5,6);


        DFS dfs = new DFS(grafo);
        System.out.println("Ptos de articulacion (2, 3) " + dfs.getPuntosDeArticulacion());
    }

    private static void pruebaPtoArt2() {
        GrafoNoDirigido grafo = new GrafoNoDirigido(1);
        DFS dfs = new DFS(grafo);
        System.out.println("Ptos de articulacion () " + dfs.getPuntosDeArticulacion());
    }

    private static void pruebaPtoArt1() {
        GrafoNoDirigido grafo = new GrafoNoDirigido(3);

        grafo.agregarArista(0,1);
        grafo.agregarArista(1,2);

        DFS dfs = new DFS(grafo);
        System.out.println("Ptos de articulacion (1) " + dfs.getPuntosDeArticulacion());
    }

    private static void pruebaGrafoTraspuesto2() {
        GrafoDirigido grafo = new GrafoDirigido(8);

        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);

        grafo.agregarArista(1, 0);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);


        grafo.agregarArista(2, 0);
        grafo.agregarArista(2, 1);
        grafo.agregarArista(2, 4);
        grafo.agregarArista(2, 6);
        grafo.agregarArista(2, 7);

        grafo.agregarArista(3, 1);
        grafo.agregarArista(3, 4);

        grafo.agregarArista(4, 1);
        grafo.agregarArista(4, 2);
        grafo.agregarArista(4, 3);
        grafo.agregarArista(4, 5);

        grafo.agregarArista(5, 4);

        grafo.agregarArista(6, 2);
        grafo.agregarArista(6, 7);

        grafo.agregarArista(7, 2);
        grafo.agregarArista(7, 6);

        GrafoDirigido traspuesto = grafo.getGrafoTraspuesto();
        System.out.println("Grafo original");
        grafo.printGrafo();
        System.out.println("Grafo traspuesto");
        traspuesto.printGrafo();
    }


    private static void pruebaGrafoTraspuesto1() {
        GrafoDirigido grafo = new GrafoDirigido(5);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 3);
        grafo.agregarArista(0, 2);
        GrafoDirigido traspuesto = grafo.getGrafoTraspuesto();
        System.out.println("Grafo original");
        grafo.printGrafo();
        System.out.println("Grafo traspuesto");
        traspuesto.printGrafo();
    }


    private static void pruebaDFS3() {
        GrafoDirigido grafo = new GrafoDirigido(5);

        grafo.agregarArista(0,1);
        grafo.agregarArista(1,2);
        grafo.agregarArista(1,3);
        grafo.agregarArista(2,0);
        grafo.agregarArista(3,4);


        DFS dfs = new DFS(grafo);
        dfs.printearDFS();
        System.out.println("Stack de finalizacion" + dfs.getStackDeFinalizacion() );
    }

    private static void pruebaDFS2() {
        GrafoDirigido grafo = new GrafoDirigido(1);

        DFS dfs = new DFS(grafo);
        dfs.printearDFS();
    }


    private static void pruebaDFS1() {
        GrafoDirigido grafo = new GrafoDirigido(8);

        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);

        grafo.agregarArista(1, 0);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);


        grafo.agregarArista(2, 0);
        grafo.agregarArista(2, 1);
        grafo.agregarArista(2, 4);
        grafo.agregarArista(2, 6);
        grafo.agregarArista(2, 7);

        grafo.agregarArista(3, 1);
        grafo.agregarArista(3, 4);

        grafo.agregarArista(4, 1);
        grafo.agregarArista(4, 2);
        grafo.agregarArista(4, 3);
        grafo.agregarArista(4, 5);

        grafo.agregarArista(5, 4);

        grafo.agregarArista(6, 2);
        grafo.agregarArista(6, 7);

        grafo.agregarArista(7, 2);
        grafo.agregarArista(7, 6);


        DFS dfs = new DFS(grafo);
        dfs.printearDFS();
    }


    private static void pruebaGrafo3() {
        GrafoDirigido grafo = new GrafoDirigido(7);

        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);

        grafo.agregarArista(1, 0);
        grafo.agregarArista(1, 2);

        grafo.agregarArista(2, 0);
        grafo.agregarArista(2, 1);
        grafo.agregarArista(2, 3);
        grafo.agregarArista(2, 4);
        grafo.agregarArista(2, 5);

        grafo.agregarArista(3, 2);
        grafo.agregarArista(3, 6);

        grafo.agregarArista(4, 2);
        grafo.agregarArista(4, 5);

        grafo.agregarArista(5, 2);
        grafo.agregarArista(5, 4);

        grafo.agregarArista(6, 3);

        System.out.println("Vertices (7)" + grafo.getCantidadVertices());
        System.out.println("Aristas (16)" + grafo.getCantidadAristas());
        System.out.println("Adyacentes a 2: 0,1,3,4,5 " + grafo.getVerticesAdyacentesA(2));
    }

    private static void pruebaGrafo2() {
        GrafoDirigido grafo = new GrafoDirigido(5);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 3);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(0, 2);
        System.out.println("Vertices (5)" + grafo.getCantidadVertices());
        System.out.println("Aristas (4)" + grafo.getCantidadAristas());
        System.out.println("Adyacentes a 0: 1,3,2,2 " + grafo.getVerticesAdyacentesA(0));
        System.out.println(grafo.getVerticesAdyacentesA(2));
        System.out.println(grafo.getVerticesAdyacentesA(4));
    }

    private static void pruebaGrafo1() {
        GrafoDirigido grafo = new GrafoDirigido(2);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(1, 0);
        System.out.println("Vertices " + grafo.getCantidadVertices());
        System.out.println("Aristas " + grafo.getCantidadAristas());
        System.out.println(grafo.getVerticesAdyacentesA(0));
        System.out.println(grafo.getVerticesAdyacentesA(1));
    }
}
