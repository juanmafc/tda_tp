package algoritmos;

import javafx.util.Pair;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {

/*
        Set<Integer> numeros = new LinkedHashSet<>();
        numeros.add(104);
        numeros.add(102);
        numeros.add(201);
        numeros.add(101);
        SubsetSumAproximada subsetSum = new SubsetSumAproximada(numeros, 308, 0.40);
*/

/*
        int[] valores = new int[7];
        valores[0] = 9;
        valores[1] = 3;
        valores[2] = 5;
        valores[3] = 4;
        valores[4] = 10;
        valores[5] = 1;
        valores[6] = 9;
        PrediccionDeAcciones predicciones = new PrediccionDeAcciones(valores);
        predicciones.getMejorParDeDiasHastaElDia(6);
*/

/*
        int[] valores = new int[5];
        valores[0] = 9;
        valores[1] = 7;
        valores[2] = 6;
        valores[3] = 4;
        valores[4] = 1;
        PrediccionDeAcciones predicciones = new PrediccionDeAcciones(valores);
        predicciones.getMejorParDeDiasHastaElDia(4);
*/

/*
        int[] valores = new int[5];
        valores[0] = 9;
        valores[1] = 7;
        valores[2] = 8;
        valores[3] = 4;
        valores[4] = 1;
        PrediccionDeAcciones predicciones = new PrediccionDeAcciones(valores);
        int ultimoDia = valores.length - 1;
        Pair<Integer, Integer> parDeDiasOptimo = predicciones.getMejorParDeDiasHastaElDia(ultimoDia);
        int diaOptimoDeCompra = parDeDiasOptimo.getKey();
        int diaOptimoDeVenta = parDeDiasOptimo.getValue();
        int gananciaOptima = predicciones.getMejorGananciaHastaElDia(ultimoDia);
        System.out.println("Lo mejor es comprar el dia " + diaOptimoDeCompra + " a " + valores[diaOptimoDeCompra] + "$ y vender al dia " + diaOptimoDeVenta + " a " + valores[diaOptimoDeVenta] + "$ para tener una ganancia de " + gananciaOptima + "$");
*/

/*
        GrafoNoDirigido grafo = new GrafoNoDirigido(5);
        grafo.agregarArista(0,1);
        grafo.agregarArista(0,4);
        grafo.agregarArista(1,2);
        grafo.agregarArista(2,3);
        grafo.agregarArista(3,4);

        grafo.colapsar(1,3);
        grafo.printGrafo();
*/

/*
        GrafoNoDirigido grafo = new GrafoNoDirigido(3);
        grafo.agregarArista(0,1);
        grafo.agregarArista(0,2);

        grafo.printGrafo();
        System.out.println("------------");
        grafo.colapsar(0,1);
        grafo.printGrafo();
*/


        GrafoNoDirigido grafo = new GrafoNoDirigido(5);
        grafo.agregarArista(0,1);
        grafo.agregarArista(0,2);
        grafo.agregarArista(0,4);

        grafo.agregarArista(1,2);
        grafo.agregarArista(1,4);
        grafo.agregarArista(1,4);

        grafo.agregarArista(2,3);
        grafo.agregarArista(2,4);


        grafo.printGrafo();
        System.out.println("Aristas " + grafo.getListaDeAristas());

        System.out.println("------------");
        grafo.colapsar(1,4);
        grafo.printGrafo();
        System.out.println("Aristas " + grafo.getListaDeAristas());

        System.out.println("------------");
        grafo.colapsar(2,3);
        grafo.printGrafo();
        System.out.println("Aristas " + grafo.getListaDeAristas());

        System.out.println("------------");
        grafo.colapsar(2,1);
        grafo.printGrafo();
        System.out.println("Aristas " + grafo.getListaDeAristas());

        System.out.println("------------");
        System.out.println("Aristas " + grafo.getListaDeAristas());

    }
/*
    public static void main(String[] args){

        Boolean done = new Boolean (false);
        Scanner scanner = new Scanner (System.in);
        while(!done)
        {
            System.out.println("Elija que ejercicio correr:" );
            System.out.println("    Asignación de residencias: Escriba AR (advertencia puede tomar mucho tiempo y memoria)" );
            System.out.println("    Puntos de falla: Escriba PF" );
            System.out.println("    Comunidades en redes: Escriba CR" );
            System.out.println("    Para terminar escriba Done" );

            Scanner linea = new Scanner(scanner.nextLine());
            String input = linea.next();

            switch (input) {
                case "AR":

                    asignacionGenerica();

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



    private static void asignacionGenerica() {
        for (int i = 1; i <3; i++) {
            String filePath = "src/algoritmos/AsignacionFiles/a" + i + ".txt";
            ParserArchivoAsignacion parser = new ParserArchivoAsignacion(filePath);

            long startTime = System.nanoTime();
            System.out.println("Asignaciones para el archivo a" + i);

            AsignacionGenerica asignaciones = new AsignacionGenerica(parser.getEstudiantes(), parser.getEstudiantes(), parser.getVacantes());

            long endTime = System.nanoTime();
            double duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
            System.out.println("Duracion: " + duration/1000000000 + " segundos");
            System.out.println();

            try{
                String savePath = "src/algoritmos/AsignacionFiles/asignaciones" + i + ".txt";
                PrintWriter writer = new PrintWriter(savePath, "UTF-8");
                writer.println("Estudiante - Hospital");
                writer.println(asignaciones.getAsignaciones());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }









    private static void crearArchivosAsignacion(int cantEstudiantes, int cantHospitales) {
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


        escribirArchivo(estudiantes, hospitales, vacantes);
        System.out.println("FIN inicializacion");
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
            PrintWriter writer = new PrintWriter("a1.txt", "UTF-8");
            //writer.println("Cantidad de estudiantes: " + estudiantes.length);
            writer.println(estudiantes.length);
            for(int i = 0; i < estudiantes.length; i++){
                for(int j = 0; j < hospitales.length; j++){
                    writer.print(estudiantes[i][j] + " ");
                }
                writer.println("");
            }
            //writer.println(" ");

            //writer.println("Cantidad de hospitales: " + hospitales.length);
            writer.println(hospitales.length);
            for(int i = 0; i < hospitales.length; i++){
                for(int j = 0; j < estudiantes.length; j++){
                    writer.print(hospitales[i][j] + " ");
                }
                writer.println("");
            }
            //writer.println(" ");


            //writer.print("Vacantes por hospital: ");

            for(int i = 0; i< hospitales.length; i++){
                writer.print(vacantes[i] + " ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

*/
}
