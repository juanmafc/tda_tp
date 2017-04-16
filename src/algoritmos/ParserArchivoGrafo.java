package algoritmos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by User on 16/04/2017.
 */
public class ParserArchivoGrafo {


    public GrafoNoDirigido parsearGrafoNoDirigido(String archivo) {

        GrafoNoDirigido grafo = null;

        try {
            Scanner scan = new Scanner(new FileReader(archivo));

            int cantidadVertices = Integer.parseInt(  ( new Scanner(scan.nextLine()) ).next()  );
            int cantidadAristas = Integer.parseInt(  ( new Scanner(scan.nextLine()) ).next()  );

            grafo = new GrafoNoDirigido(cantidadVertices);

            while(scan.hasNextLine())
            {
                Scanner linea = new Scanner(scan.nextLine());
                while(linea.hasNext()){

                    int origen = Integer.parseInt( linea.next() );
                    int destino = Integer.parseInt( linea.next() );

                    grafo.agregarArista(origen, destino);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return grafo;
    }


    public GrafoDirigido parsearGrafoDirigido(String archivo) {

        GrafoDirigido grafo = null;

        try {
            Scanner scan = new Scanner(new FileReader(archivo));

            int cantidadVertices = Integer.parseInt(  ( new Scanner(scan.nextLine()) ).next()  );
            int cantidadAristas = Integer.parseInt(  ( new Scanner(scan.nextLine()) ).next()  );

            grafo = new GrafoDirigido(cantidadVertices);

            while(scan.hasNextLine())
            {
                Scanner linea = new Scanner(scan.nextLine());
                while(linea.hasNext()){

                    int origen = Integer.parseInt( linea.next() );
                    int destino = Integer.parseInt( linea.next() );

                    grafo.agregarArista(origen, destino);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return grafo;
    }


}
