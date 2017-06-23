package algoritmos;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Boolean done = new Boolean (false);
        Scanner scanner = new Scanner (System.in);
        while(!done)
        {
            System.out.println("Elija que ejercicio correr y sus argumentos. Luego presione Enter:" );
            System.out.println("    Algoritmos randomizados: Escriba AR y cantidad de nodos." );
            System.out.println("    Algoritmos aproximados: Escriba AP, valor de n y factor de aproximacion" );
            System.out.println("    Para terminar escriba Done" );

            Scanner linea = new Scanner(scanner.nextLine());
            String input = linea.next();

            switch (input) {
                case "AR":

                    int cantidadDeNodos = Integer.parseInt( linea.next() );

                    GrafoNoDirigido grafoConexo = GrafoConexoBuilder.build(cantidadDeNodos);

                    System.out.println(grafoConexo.getCantidadVertices());
                    System.out.println(grafoConexo.getCantidadAristas());

                    grafoConexo.printGrafo();
                    Karger karger = new Karger(grafoConexo);

                    break;

                case "AP":

                    Integer n = Integer.parseInt( linea.next() );

                    Double factorAproximacion = Double.parseDouble( linea.next() );


                    SubSetSumBuilder sSSBuilder = new SubSetSumBuilder();
                    SubSet subset = sSSBuilder.build(n);

                    SubsetSumAproximada subsetSumAproximada = new SubsetSumAproximada(subset.getSet(), subset.getT(), factorAproximacion);


                    break;

                case "Done":
                    done = true;
                    break;

                default:
                    System.out.println("Comando incorrecto" );
                    break;
            }

        }

    }
    public static class GrafoConexoBuilder{

        public static GrafoNoDirigido build(Integer cantidadVertices){

            Random rand = new Random();

            GrafoNoDirigido grafoConexo = new GrafoNoDirigido(cantidadVertices);

            for(int i = 0; i < cantidadVertices-1; i++) {
                grafoConexo.agregarArista(i, i+1);
            }

            while(grafoConexo.getCantidadAristas() != 2*cantidadVertices){
                int u = rand.nextInt(cantidadVertices);
                int v = rand.nextInt(cantidadVertices);
                if(u != v) grafoConexo.agregarArista(u,v);
            }

            return grafoConexo;
        }
    }
}



