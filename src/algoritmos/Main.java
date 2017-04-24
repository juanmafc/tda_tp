package algoritmos;

import java.io.IOException;
import java.io.PrintWriter;
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

            long startTime = System.nanoTime();

            Kosaraju kosaraju = new Kosaraju(grafo);
            System.out.println("Cantidad de CFCs en el archivo d" + i + ":" + kosaraju.getCantidadCFC() );
            //kosaraju.printCFCs();

            long endTime = System.nanoTime();
            double duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
            System.out.println("Duracion: " + duration/1000000000 + " segundos");
            System.out.println();

        }


    }


    private static void puntosFalla() {

        ParserArchivoGrafo parser = new ParserArchivoGrafo();
        for (int i = 1; i <7; i++) {
            String filePath = "src/algoritmos/TarjanFiles/g" + i + ".txt";

            long startTime = System.nanoTime();

            GrafoNoDirigido grafo = parser.parsearGrafoNoDirigido(filePath);
            Tarjan tarjan = new Tarjan(grafo);
            System.out.println("Cantidad de puntos de articulacion en el archivo g" + i + ":" + tarjan.getPuntosDeArticulacion().size() );
            //System.out.println("Ptos de articulacion: " + dfs.getPuntosDeArticulacion());

            long endTime = System.nanoTime();
            double duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
            System.out.println("Duracion: " + duration/1000000000 + " segundos");
            System.out.println();

        }
    }



    private static void asignacionGenerica(int cantEstudiantes, int cantHospitales) {

        int[][] estudiantes = new int[cantEstudiantes][cantHospitales];
        int[][] hospitales = new int[cantHospitales][cantEstudiantes];

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
        int[] vacantes = asignarVacantes(cantEstudiantes, cantHospitales);


        //escribirArchivo(estudiantes, hospitales, vacantes);
        System.out.println("FIN inicializacion");
        new AsignacionGenerica(estudiantes,hospitales, vacantes);

    }

    //Asigna las vacantes para cada hospital de forma aleatoria, buscando que no sea muy dispareja la distribucion
    public static int[] asignarVacantes(int vacantes,int cantHospitales){

        ArrayList<Integer> lista = new ArrayList<Integer>();
        int[] ret = new int[cantHospitales];
        Random rand = new Random();
        int min = 0;
        lista.add(min);
        lista.add(vacantes);



        for(int i = 1;i<cantHospitales;i++){
            int n = rand.nextInt(vacantes) + min;
            lista.add(n);
        }
        Collections.sort(lista);
        for(int i = 0;i<lista.size()-1;i++){
            int vacantesDelHospital=lista.get(i+1)-lista.get(i);
            ret[i] = vacantesDelHospital;
        }

        return ret;
    }

    private static void escribirArchivo(int[][] estudiantes, int[][] hospitales, int[] vacantes) {

        try{
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            writer.println("Cantidad de estudiantes: " + estudiantes.length);
            for(int i = 0; i < estudiantes.length; i++){
                for(int j = 0; j < hospitales.length; j++){
                    writer.print(estudiantes[i][j] + " ");
                }
                writer.println(" ");
            }
            writer.println(" ");

            writer.println("Cantidad de hospitales: " + hospitales.length);
            for(int i = 0; i < hospitales.length; i++){
                for(int j = 0; j < estudiantes.length; j++){
                    writer.print(hospitales[i][j] + " ");
                }
                writer.println(" ");
            }
            writer.println(" ");


            writer.print("Vacantes por hospital: ");

            for(int i = 0; i< hospitales.length; i++){
                writer.print(vacantes[i] + " ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
