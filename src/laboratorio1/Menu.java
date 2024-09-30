package laboratorio1;

import java.util.Scanner;

public class Menu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        
        while (opcion != 2) {
            System.out.println("------ Menú ------");
            System.out.println("1. Iniciar juego");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            
            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                
                
                switch (opcion) {
                    case 1:
                        System.out.println("El juego ha iniciado...");
                        Juego.jugar();
                        break;
                    case 2:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija 1 o 2.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
       
        scanner.close();
    }
}
