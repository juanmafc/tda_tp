package algoritmos;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Boolean done = new Boolean (false);
        Scanner scanner =new Scanner (System.in);
        while(!done)
        {
            System.out.println("Elija que ejercicio correr:" );
            System.out.println("    Asignación de residencias: Escriba AR y parámetros aplicantes y hospitales" );
            System.out.println("    Puntos de falla: Escriba PF" );
            System.out.println("    Comunidades en redes: Escriba CR" );
            System.out.println("    Para terminar escriba Done" );

            Scanner linea = new Scanner(scanner.nextLine());
            String input = linea.next();

            switch (input) {
                case "AR":

                    int aplicantes = Integer.parseInt( linea.next() );
                    int hospitales = Integer.parseInt( linea.next() );

                    /*

                    Acá hay que poner la función tuya, juampa

                     */

                    break;

                case "PF":
                    puntosFalla();
                    break;

                case "CR":
                    comunidadesRedes();
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


    private static void comunidadesRedes() {
        ParserArchivoGrafo parser = new ParserArchivoGrafo();
        for (int i = 1; i <7; i++) {

            String filePath = "src/algoritmos/KosarajuFiles/d" + i + ".txt";

            GrafoDirigido grafo = parser.parsearGrafoDirigido(filePath);

            Kosaraju kosaraju = new Kosaraju(grafo);
            System.out.println("Cantidad de CFCs:" + kosaraju.getCantidadCFC() );
            kosaraju.printCFCs();
        }


    }


    private static void puntosFalla() {

        ParserArchivoGrafo parser = new ParserArchivoGrafo();
        for (int i = 1; i <7; i++) {
            String filePath = "src/algoritmos/TarjanFiles/g" + i + ".txt";

            GrafoNoDirigido grafo = parser.parsearGrafoNoDirigido(filePath);

            Tarjan dfs = new Tarjan(grafo);
            System.out.println("Ptos de articulacion: " + dfs.getPuntosDeArticulacion());
        }
    }



}
