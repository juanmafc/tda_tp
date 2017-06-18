package algoritmos;

import javafx.util.Pair;

/**
 * Created by User on 18/06/2017.
 */
public class PrediccionDeAcciones {

    int optimo[];
    int precioMinimo[];
    int valores[];
    public PrediccionDeAcciones(int[] valores) {
        int cantidadDias = valores.length;
        this.optimo = new int[cantidadDias];
        this.precioMinimo = new int[cantidadDias];
        this.valores = valores;

        int ultimoDia = valores.length - 1;
        this.analizar(ultimoDia);

        System.out.println();
    }

    private int analizar(int dia) {

        if (dia == 0) {
            this.optimo[dia] = 0;
            this.precioMinimo[dia] = this.valores[dia];
            return 0;
        }

        this.optimo[dia] = Math.max(this.analizar(dia-1), this.valores[dia] - this.precioMinimo[dia - 1]);
        this.precioMinimo[dia] = Math.min(this.valores[dia], this.precioMinimo[dia - 1]);
        return this.optimo[dia];
    }

    public Pair<Integer, Integer> getMejorParDeDiasHastaElDia(int dia) {

        boolean diaDeVentaOptimoEncontrado = false;
        int diaDeVentaOptimo = 0;
        while ( (!diaDeVentaOptimoEncontrado) && (dia > 0) ) {
            if (this.optimo[dia] > this.optimo[dia - 1 ]){
                diaDeVentaOptimo = dia;
                diaDeVentaOptimoEncontrado = true;
            }
            dia--;
        }

        boolean diaDeCompraOptimoEncontrado = false;
        int diaDeCompraOptimo = 0;
        while ( (!diaDeCompraOptimoEncontrado) && (dia > 0) ) {
            if (this.precioMinimo[dia] < this.precioMinimo[dia - 1 ]){
                diaDeCompraOptimo = dia;
                diaDeCompraOptimoEncontrado = true;
            }
            dia--;
        }
        return new Pair<>(diaDeCompraOptimo, diaDeVentaOptimo);
    }


    public int getMejorGananciaHastaElDia(int dia) {
        return this.optimo[dia];
    }
}
