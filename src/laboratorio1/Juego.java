package laboratorio1;

import java.util.Random;
import java.util.Scanner;

public class Juego {

    private static final int[][] puntosEstantes = {
        {1, 1, 1, 1, 2}, 
        {1, 1, 1, 1, 2}, 
        {2, 2, 2, 2, 2} //estantes los balones multicolor son representados con 2 y los oficiales con 1
    };

    private static final int NUM_ESTANTES = 3;
    private static final int BALONES_POR_ESTANTE = 5;
    private static int contadorJuegos = 0; // Contador de juegos

    private static Scanner scanner = new Scanner(System.in);

    public static void jugar() {
        contadorJuegos++; // subir el numero de juego
        System.out.println("Número de juego: " + contadorJuegos);

        String jugador1 = "Jugador 1";
        String jugador2 = "Jugador 2";

        // arreglos para contar balones encestados por estante
        int[] balonesJugador1 = new int[NUM_ESTANTES];
        int[] balonesJugador2 = new int[NUM_ESTANTES];

        int puntajeJugador1 = lanzarBalones(jugador1, balonesJugador1);
        int puntajeJugador2 = lanzarBalones(jugador2, balonesJugador2);

        imprimirResultados(jugador1, puntajeJugador1, balonesJugador1, jugador2, puntajeJugador2, balonesJugador2);
    }

    private static int lanzarBalones(String jugador, int[] balonesEncestados) {
        int puntajeTotal = 0;
        Random random = new Random();
        boolean[] posicionesElegidas = new boolean[BALONES_POR_ESTANTE];

        // lanzar balones de cada estante
        for (int estante = 0; estante < NUM_ESTANTES; estante++) {
            System.out.println(jugador + ", es tu turno en el estante " + (estante + 1) + ". Debes lanzar 5 veces.");

            // reiniciar el rastreador de posiciones elegidas para cada estante
            for (int tiro = 0; tiro < BALONES_POR_ESTANTE; tiro++) {
                int posicionElegida;

                // pedir la posición hasta que el jugador ingrese una válida
                while (true) {
                    System.out.print("Selecciona la posición (1-5) para tu tiro " + (tiro + 1) + ": ");
                    posicionElegida = scanner.nextInt(); // entrada del jugador

                    if (posicionElegida >= 1 && posicionElegida <= BALONES_POR_ESTANTE) {
                        // Verificar si la posición ya ha sido elegida
                        if (!posicionesElegidas[posicionElegida - 1]) {
                            posicionesElegidas[posicionElegida - 1] = true; // marcar la posición como elegida
                            break; // salir del bucle si la posición es válida y no repetida
                        } else {
                            System.out.println("Posicion ya elegida. Debes elegir otra posicion.");
                        }
                    } else {
                        System.out.println("Posicion no valida. Debes elegir entre 1 y 5.");
                    }
                }

                int numeroAleatorio = random.nextInt(5) + 1; // numero entre 1 y 5
                System.out.println(jugador + " lanza desde el estante " + (estante + 1) + ", posicion " + posicionElegida);

                if (numeroAleatorio == posicionElegida) {
                    puntajeTotal += puntosEstantes[estante][posicionElegida - 1]; // sumar puntos (ajustando el índice)
                    balonesEncestados[estante]++; // Contar el balón encestado
                    System.out.println("Encesto Puntos ganados: " + puntosEstantes[estante][posicionElegida - 1]);
                } else {
                    System.out.println("Fallo");
                }
            }
            // reiniciar el arreglo de posiciones elegidas para el proximo estante
            posicionesElegidas = new boolean[BALONES_POR_ESTANTE];
        }
        return puntajeTotal;
    }

    private static void imprimirResultados(String jugador1, int puntajeJugador1, int[] balonesJugador1, String jugador2, int puntajeJugador2, int[] balonesJugador2) {
        //imprimir resultados de jugador 1
        System.out.println("Cantidad de puntos del " + jugador1 + ": " + puntajeJugador1);
        for (int i = 0; i < NUM_ESTANTES; i++) {
            System.out.println("• Cantidad de balones encestados en el estante #" + (i + 1)
                    + " y puntos obtenidos por el " + jugador1 + ": encestó "
                    + balonesJugador1[i] + " balones para un total de "
                    + (balonesJugador1[i] * puntosEstantes[i][0]) + " puntos.");
        }
        //imprimir resultados de jugador 2
        System.out.println("Cantidad de puntos del " + jugador2 + ": " + puntajeJugador2);
        for (int i = 0; i < NUM_ESTANTES; i++) {
            System.out.println("Cantidad de balones encestados en el estante #" + (i + 1)
                    + " y puntos obtenidos por el " + jugador2 + ": encestó "
                    + balonesJugador2[i] + " balones para un total de "
                    + (balonesJugador2[i] * puntosEstantes[i][0]) + " puntos.");
        }
        //Ganadores si jugador 1 es mayor que jugador 2 gana el jugador 1 y viceversa
        if (puntajeJugador1 > puntajeJugador2) {
            System.out.println("Ganador: " + jugador1);
        } else if (puntajeJugador2 > puntajeJugador1) {
            System.out.println("Ganador: " + jugador2);
        } else {
            System.out.println("Es un empate");
            // logica de desempate
            System.out.println("Desempate:");
            System.out.println("Cantidad de balones encestados en el estante #3 por " + jugador1 + ": " + balonesJugador1[2]);
            System.out.println("Cantidad de balones encestados en el estante #3 por " + jugador2 + ": " + balonesJugador2[2]);
            if (balonesJugador1[2] > balonesJugador2[2]) { //evalua si el jugador 1 encestó más balones en el estante 3 que el jugador 2.
                System.out.println("Ganador: " + jugador1);
            } else if (balonesJugador2[2] > balonesJugador1[2]) {
                System.out.println("Ganador: " + jugador2);
            } else {
                System.out.println("El desempate también es un empate");
            }
        }
    }
}
