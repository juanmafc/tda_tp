package algoritmos;

import java.util.*;

public class SubsetSumAproximada {

    public SubsetSumAproximada(Set<Integer> numeros, int t, double factorAproximacion) {

        int n = numeros.size();
        SortedSet<Integer> setSumasPosibles = new TreeSet<>();
        setSumasPosibles.add(0);

        for (int numero : numeros) {
            setSumasPosibles = this.agregarNuevasSumas(setSumasPosibles, numero);
            System.out.println("setSumasPosibles " + setSumasPosibles);
            setSumasPosibles = this.podarSumasPosibles(setSumasPosibles, factorAproximacion/(2*n));
            System.out.println("setSumasPosibles " + setSumasPosibles);
            setSumasPosibles = this.removerSumasQueSuperanLimite(setSumasPosibles, t);
            System.out.println("setSumasPosibles " + setSumasPosibles);
            System.out.println();

        }

        System.out.println("El valor mas grande es " + setSumasPosibles.last());
        System.out.println(setSumasPosibles);
    }

    private SortedSet<Integer> agregarNuevasSumas(SortedSet<Integer> setSumasPosibles, int numero) {

        SortedSet<Integer> setNuevasSumas = new TreeSet<>(setSumasPosibles);

        for (int sumaPosible : setSumasPosibles) {
            setNuevasSumas.add(sumaPosible + numero);
        }

        return setNuevasSumas;
    }

    private SortedSet<Integer> podarSumasPosibles(SortedSet<Integer> setSumasPosibles, double delta) {

        SortedSet<Integer> setSumasPosiblesPodado = new TreeSet<>();

        int last = setSumasPosibles.first();;
        setSumasPosiblesPodado.add( setSumasPosibles.first() );

        for (int yi : setSumasPosibles) {
            //Si NO es el primero (para que el for sea del 2do al ultimo)
            if (yi != setSumasPosibles.first()) {
                if ( yi > (last * (1 + delta)) ) {
                    setSumasPosiblesPodado.add(yi);
                    last = yi;
                }
            }
        }
        return setSumasPosiblesPodado;
    }

    private SortedSet<Integer> removerSumasQueSuperanLimite(SortedSet<Integer> setSumasPosibles, int limite) {

        SortedSet<Integer> setSumasPosiblesQueNoSuperanLimite= new TreeSet<>();

        for (int sumaPosible : setSumasPosibles) {
            if (sumaPosible <= limite) {
                setSumasPosiblesQueNoSuperanLimite.add(sumaPosible);
            }
        }

        return setSumasPosiblesQueNoSuperanLimite;
    }

}
