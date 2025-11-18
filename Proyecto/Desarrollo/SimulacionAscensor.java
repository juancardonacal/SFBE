import java.util.Scanner;

public class SimulacionAscensor {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        // Configuración del edificio
        Piso[] edificio = new Piso[5]; 
        for (int i = 0; i < 5; i++) {
            edificio[i] = new Piso(i + 1); 
        }
        
        int pisoInicial = 1;
        Ascensor ascensor = new Ascensor(pisoInicial);
        SistemaControl sistemaControl = new SistemaControl(ascensor, edificio);

        // Variables de estado
        boolean dentroDelAscensor = false; 
        int miPisoActual = 1; 
        int opcion;

        System.out.println("Simulación iniciada.");

        // Bucle principal
        do {
            if (!dentroDelAscensor) {
                // ------------------------------------------------
                // MENÚ FUERA DEL ASCENSOR (Adaptativo por piso)
                // ------------------------------------------------
                System.out.println("\n--- ESTÁS EN EL PISO " + miPisoActual + " (FUERA) ---");
                System.out.println("[Ascensor está en piso: " + ascensor.getPisoActual() + "]");

                // CASO 1: ESTAMOS EN EL PISO 1 (Solo subir)
                if (miPisoActual == 1) {
                    System.out.println("1. Llamar para SUBIR");
                    System.out.println("2. Esperar (sirve para que el ascensor procese solicitudes)");
                    System.out.println("3. Entrar al ascensor");
                    System.out.println("4. Caminar a otro piso");
                    System.out.println("5. Salir");
                    System.out.print("Opción: ");
                    
                    if (input.hasNextInt()) {
                        opcion = input.nextInt();
                        switch (opcion) {
                            case 1: sistemaControl.presionarSubir(miPisoActual); break;
                            case 2: sistemaControl.procesar(); break;
                            case 3: intentarEntrar(ascensor, miPisoActual); dentroDelAscensor = (ascensor.getPisoActual() == miPisoActual); break;
                            case 4: miPisoActual = caminar(input); break;
                            case 5: return;
                            default: System.out.println("Opción no válida.");
                        }
                    } else { input.next(); }

                // CASO 2: ESTAMOS EN EL PISO 5 (Solo bajar)
                } else if (miPisoActual == 5) {
                    System.out.println("1. Llamar para BAJAR");
                    System.out.println("2. Esperar (sirve para que el ascensor procese solicitudes)");
                    System.out.println("3. Entrar al ascensor");
                    System.out.println("4. Caminar a otro piso");
                    System.out.println("5. Salir");
                    System.out.print("Opción: ");

                    if (input.hasNextInt()) {
                        opcion = input.nextInt();
                        switch (opcion) {
                            case 1: sistemaControl.presionarBajar(miPisoActual); break;
                            case 2: sistemaControl.procesar(); break;
                            case 3: intentarEntrar(ascensor, miPisoActual); dentroDelAscensor = (ascensor.getPisoActual() == miPisoActual); break;
                            case 4: miPisoActual = caminar(input); break;
                            case 5: return;
                            default: System.out.println("Opción no válida.");
                        }
                    } else { input.next(); }

                // CASO 3: PISOS INTERMEDIOS (Subir y Bajar)
                } else {
                    System.out.println("1. Llamar para SUBIR");
                    System.out.println("2. Llamar para BAJAR");
                    System.out.println("3. Esperar (sirve para que el ascensor procese solicitudes)");
                    System.out.println("4. Entrar al ascensor");
                    System.out.println("5. Caminar a otro piso");
                    System.out.println("6. Salir");
                    System.out.print("Opción: ");

                    if (input.hasNextInt()) {
                        opcion = input.nextInt();
                        switch (opcion) {
                            case 1: sistemaControl.presionarSubir(miPisoActual); break;
                            case 2: sistemaControl.presionarBajar(miPisoActual); break;
                            case 3: sistemaControl.procesar(); break;
                            case 4: intentarEntrar(ascensor, miPisoActual); dentroDelAscensor = (ascensor.getPisoActual() == miPisoActual); break;
                            case 5: miPisoActual = caminar(input); break;
                            case 6: return;
                            default: System.out.println("Opción no válida.");
                        }
                    } else { input.next(); }
                }

            } else {
                // ------------------------------------------------
                // MENÚ DENTRO DEL ASCENSOR
                // ------------------------------------------------
                System.out.println("\n--- ESTÁS DENTRO DEL ASCENSOR (Piso actual: " + ascensor.getPisoActual() + ") ---");
                System.out.println("1. Seleccionar piso destino");
                System.out.println("2. Mantener puertas abiertas");
                System.out.println("3. Poner/Quitar obstáculo");
                System.out.println("4. Esperar (sirve para que el ascensor procese solicitudes)");
                System.out.println("5. Salir del ascensor");
                System.out.print("Opción: ");

                if (input.hasNextInt()) {
                    opcion = input.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.print("Destino (1-5): ");
                            int destino = input.nextInt();
                            sistemaControl.presionarInterno(destino);
                            break;
                        case 2: sistemaControl.presionarMantenerPuertasAbiertas(); break;
                        case 3: 
                            boolean estado = ascensor.unObstaculoEnLaPuerta();
                            ascensor.simularObstaculo(!estado);
                            break;
                        case 4: sistemaControl.procesar(); break;
                        case 5:
                            miPisoActual = ascensor.getPisoActual();
                            dentroDelAscensor = false;
                            System.out.println("Has salido en el piso " + miPisoActual);
                            break;
                        default: System.out.println("Opción no válida.");
                    }
                } else { input.next(); }
            }

        } while (true);
    }

    // Métodos auxiliares para limpiar el main
    public static void intentarEntrar(Ascensor ascensor, int miPiso) {
        if (ascensor.getPisoActual() == miPiso) {
            System.out.println("Has entrado al ascensor.");
        } else {
            System.out.println(">>> NO PUEDES ENTRAR. El ascensor está en el piso " + ascensor.getPisoActual());
        }
    }

    public static int caminar(Scanner input) {
        System.out.print("¿A qué piso vas caminando? (1-5): ");
        if (input.hasNextInt()) {
            int p = input.nextInt();
            if (p >= 1 && p <= 5) {
                System.out.println("Caminaste al piso " + p);
                return p;
            }
        }
        System.out.println("Piso inválido.");
        return 1; // Retorno por defecto si falla
    }
}