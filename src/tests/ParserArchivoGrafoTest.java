package tests;

import algoritmos.GrafoNoDirigido;
import algoritmos.ParserArchivoGrafo;
import junit.framework.TestCase;

import java.util.LinkedList;


public class ParserArchivoGrafoTest extends TestCase{

    public void testParsearArchivo1() {

        ParserArchivoGrafo parser = new ParserArchivoGrafo();

        GrafoNoDirigido grafo = parser.parsearGrafoNoDirigido("src/tests/grafoTest1.txt");

        assertEquals(2, grafo.getCantidadVertices());
        assertEquals(1, grafo.getCantidadAristas());

        LinkedList<Integer> adyacentes0 = grafo.getVerticesAdyacentesA(0);
        assertEquals(1, adyacentes0.size() );
        assertTrue( adyacentes0.contains(1) );

        LinkedList<Integer> adyacentes1 = grafo.getVerticesAdyacentesA(1);
        assertEquals(1, adyacentes1.size() );
        assertTrue( adyacentes1.contains(0) );
    }


    public void testParsearArchivo2() {

        ParserArchivoGrafo parser = new ParserArchivoGrafo();

        GrafoNoDirigido grafo = parser.parsearGrafoNoDirigido("src/tests/grafoTest2.txt");

        assertEquals(7, grafo.getCantidadVertices());
        assertEquals(4, grafo.getCantidadAristas());

        LinkedList<Integer> adyacentes0 = grafo.getVerticesAdyacentesA(0);
        assertEquals(3, adyacentes0.size() );
        assertTrue( adyacentes0.contains(1) );
        assertTrue( adyacentes0.contains(2) );
        assertTrue( adyacentes0.contains(3) );

        LinkedList<Integer> adyacentes1 = grafo.getVerticesAdyacentesA(1);
        assertEquals(1, adyacentes1.size() );
        assertTrue( adyacentes1.contains(0) );

        LinkedList<Integer> adyacentes2 = grafo.getVerticesAdyacentesA(2);
        assertEquals(1, adyacentes2.size() );
        assertTrue( adyacentes2.contains(0) );

        LinkedList<Integer> adyacentes3 = grafo.getVerticesAdyacentesA(3);
        assertEquals(1, adyacentes3.size() );
        assertTrue( adyacentes3.contains(0) );

        LinkedList<Integer> adyacentes4 = grafo.getVerticesAdyacentesA(4);
        assertEquals(1, adyacentes4.size() );
        assertTrue( adyacentes4.contains(5) );

        LinkedList<Integer> adyacentes5 = grafo.getVerticesAdyacentesA(5);
        assertEquals(1, adyacentes5.size() );
        assertTrue( adyacentes5.contains(4) );

        LinkedList<Integer> adyacentes6 = grafo.getVerticesAdyacentesA(6);
        assertEquals(0, adyacentes6.size() );

    }

}
