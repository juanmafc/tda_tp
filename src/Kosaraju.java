import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by User on 10/04/2017.
 */
public class Kosaraju {


    private List<Set<Integer>> componentesFuertementeConexas;

    public Kosaraju(GrafoDirigido grafo) {
        this.componentesFuertementeConexas = new LinkedList<>();

        DFS primerDFS = new DFS(grafo);
        GrafoDirigido grafoTraspuesto = grafo.getGrafoTraspuesto();
        Stack<Integer> stackFinalizacion = primerDFS.getStackDeFinalizacion();
        DFS segundoDFS = new DFS(grafoTraspuesto, stackFinalizacion );
    }

    /**
     * Devuelve el número de componentes fuertemente conexas del grafo.
     * @return  El número de componentes fuertemente conexas.
     */
    public int getCantidadCFC() {
        /* ... */
        return -1;
    }

    /**
     * Devuelve un conjunto de vértices que pertenecen a la componente fuertemente
     *     conexa cuyo número es pasado por parámetro.
     * @param cfcId   El número de componente fuertemente conexa.
     * @return        El conjunto de vértices que forman parte de la componente.
     * @throws IllegalArgumentException si no se cumple que {@code 0 <= sccId < getSCCNumber()}
     */
    public Set<Integer> getMiembrosDeCFC(int cfcId) {
        /* ... */
        return null;
    }


}
