import java.util.LinkedList;


public class GrafoDirigido {

    protected int cantidadVertices;
    protected LinkedList<Integer>[] aristas;
    protected int cantidadAristas;


    public GrafoDirigido(int cantidadVertices) {
        this.cantidadVertices = cantidadVertices;
        this.cantidadAristas = 0;
        this.aristas = new LinkedList[cantidadVertices];

        for (int i = 0; i < cantidadVertices; i++) {
            this.aristas[i] = new LinkedList<>();
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


    public  GrafoDirigido getGrafoTraspuesto() {
        GrafoDirigido complemento = new GrafoDirigido(this.getCantidadVertices());

        for (int u = 0; u < this.getCantidadVertices(); u++) {
            for (int v : this.aristas[u] ) {
                complemento.agregarArista(v, u);
            }
        }
        return complemento;
    }

    public void printGrafo() {
        System.out.println("Vertices: " + this.getCantidadVertices());
        System.out.println("Aristas: " + this.getCantidadAristas());
        for (int i = 0; i < this.getCantidadVertices(); i++) {
            System.out.println(i + "-->" + this.getVerticesAdyacentesA(i));
        }
    }

}
