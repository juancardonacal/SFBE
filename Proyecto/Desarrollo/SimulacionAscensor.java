import java.util.Scanner;
public class SimulacionAscensor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Piso piso;
        System.out.print("Ingrese el piso inicial del ascensor (1-5): ");
        Ascensor ascensor = new Ascensor(input.nextInt());
        SistemaControl sistemaControl = new SistemaControl();

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. solicitar ascensor");
            System.out.println("2. no hacer nada");
            int opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("solicitar ascensor");
                    int numeroPiso = input.nextInt();
                    ascensor.irAPiso(pisoDestino);
                    break;
                case 2:
                    System.out.println("No hacer nada");
                    input.close();
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");

                
            }
        } while (true);

        do {
            System.out.println("entrando en el ascensor");
            System.out.println("1. primer piso (el ascensor se queda estatico)");
            System.out.println("2. ir al segundo piso");
            System.out.println("3. ir al tercer piso");
            System.out.println("4. ir al cuarto piso");
            System.out.println("5. ir al quinto piso");
            int opcion = input.nextInt();

        } while (true);
    }
}