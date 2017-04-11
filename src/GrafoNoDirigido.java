/**
 * Created by User on 11/04/2017.
 */
public class GrafoNoDirigido extends  GrafoDirigido {


    public GrafoNoDirigido(int cantidadVertices) {
        super(cantidadVertices);
    }

    public void agregarArista(int origen, int destino) {
        this.aristas[origen].add(destino);
        this.aristas[destino].add(origen);

        this.cantidadAristas++;
    }
}
