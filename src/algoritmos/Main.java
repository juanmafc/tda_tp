package algoritmos;

import java.util.*;

public class Main {

    public static void main(String[] args){

        Boolean done = new Boolean (false);
        Scanner scanner = new Scanner (System.in);
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

                    int estudiantes = Integer.parseInt( linea.next() );
                    int hospitales = Integer.parseInt( linea.next() );


                    /*

                    Acá hay que poner la función tuya, juampa

                     */

                    asignacionGenerica(estudiantes, hospitales);

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



    private static void asignacionGenerica(int cantEstudiantes, int cantHospitales) {

        int[][] estudiantes = new int[cantEstudiantes][cantHospitales];
        int[][] hospitales = new int[cantHospitales][cantEstudiantes];
        int[] vacantes = new int[cantHospitales];

        //Una lista con todos los hospitales
        List<Integer> rankings = new ArrayList<>();
        for (int i = 0; i < cantHospitales; i++) {
            rankings.add(i);
        }

        //Por cada estudiante se mezcla la lista de hospitales en un orden random y se copia la lista a la lista de preferencia de ese estudiante
        for(int j = 0; j < cantEstudiantes; j++){
            Collections.shuffle(rankings);
            for(int k = 0; k < cantHospitales; k++){
                estudiantes[j][k] = rankings.get(k);
            }
        }

        //Se hace lo mismo con los hospitales y su lista de merito
        List<Integer> examenes = new ArrayList<>();
        for (int i = 0; i < cantEstudiantes; i++) {
            examenes.add(i);
        }

        for(int j = 0; j < cantHospitales; j++){
            Collections.shuffle(examenes);
            for(int k = 0; k < cantEstudiantes; k++){
                hospitales[j][k] = examenes.get(k);
            }
        }


        //Se asignan tantas vacantes como estudiantes haya
        int cantVacantes =  cantEstudiantes;
        for (int i = 0; i < hospitales.length; i++) {
            if (i + 1 == hospitales.length) {
                vacantes[i] = cantVacantes;
            } else {
                Random rand = new Random();
                Integer randomInt = rand.nextInt(cantVacantes);
                vacantes[i] = randomInt;
                cantVacantes = cantVacantes - randomInt;

            }
        }

        new AsignacionGenerica(estudiantes,hospitales, vacantes);
    }



}
