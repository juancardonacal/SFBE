import java.util.Scanner;

public class SimulacionAscensor {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // -----------------------------------------
        // 1. Elegir piso inicial del ascensor
        // -----------------------------------------
        int pisoInicial;

        do {
            System.out.print("Ingrese el piso inicial del ascensor (1-5): ");
            pisoInicial = input.nextInt();

            if (pisoInicial < 1 || pisoInicial > 5) {
                System.out.println("Piso inválido. Debe estar entre 1 y 5.");
            }

        } while (pisoInicial < 1 || pisoInicial > 5);

        // Crear ascensor y sistema de control
        Ascensor ascensor = new Ascensor(pisoInicial);
        SistemaControl sistemaControl = new SistemaControl(ascensor);

        System.out.println("\nAscensor iniciado en el piso " + pisoInicial + ".\n");

        int opcion;

        // -----------------------------------------
        // 2. Menú principal
        // -----------------------------------------
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Solicitar ascensor (botones del piso)");
            System.out.println("2. Entrar al ascensor y elegir destino");
            System.out.println("3. Salir del programa");
            System.out.print("Seleccione una opción: ");

            opcion = input.nextInt();

            switch (opcion) {

                case 1: // Solicitud externa
                    System.out.print("¿En qué piso está usted? (1-5): ");
                    int piso = input.nextInt();

                    System.out.print("¿Desea subir (1) o bajar (2)? ");
                    int dir = input.nextInt();

                    if (dir == 1) {
                        sistemaControl.presionarSubir(piso);
                    } else if (dir == 2) {
                        sistemaControl.presionarBajar(piso);
                    } else {
                        System.out.println("Dirección inválida.");
                        break;
                    }

                    sistemaControl.procesar();
                    break;


                case 2: // Botones internos del ascensor
                    System.out.print("¿A qué piso desea ir? (1-5): ");
                    int destino = input.nextInt();

                    sistemaControl.presionarInterno(destino);
                    sistemaControl.procesar();
                    break;


                case 3:
                    System.out.println("Saliendo del programa...");
                    input.close();
                    return;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (true);
    }
}
