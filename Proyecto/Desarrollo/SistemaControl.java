public class SistemaControl {

    private Ascensor ascensor;

    // Botones externos (uno por piso)
    private BotonPiso botonPiso1;
    private BotonPiso botonPiso2;
    private BotonPiso botonPiso3;
    private BotonPiso botonPiso4;
    private BotonPiso botonPiso5;

    // Botones internos (dentro del ascensor)
    private BotonAscensor botonA1;
    private BotonAscensor botonA2;
    private BotonAscensor botonA3;
    private BotonAscensor botonA4;
    private BotonAscensor botonA5;

    // Solicitud actual
    private int solicitudActual;   // 0 = ninguna

    private String direccionActual;  // "subir", "bajar", "ninguna"

    public SistemaControl(Ascensor ascensor) {
        this.ascensor = ascensor;
        this.direccionActual = "ninguna";
        this.solicitudActual = 0; 

        // Crear botones de piso
        botonPiso1 = new BotonPiso(1);
        botonPiso2 = new BotonPiso(2);
        botonPiso3 = new BotonPiso(3);
        botonPiso4 = new BotonPiso(4);
        botonPiso5 = new BotonPiso(5);

        // Crear botones internos
        botonA1 = new BotonAscensor(1);
        botonA2 = new BotonAscensor(2);
        botonA3 = new BotonAscensor(3);
        botonA4 = new BotonAscensor(4);
        botonA5 = new BotonAscensor(5);
    }


    // Botones externos de piso
    public void presionarBotonPiso(int piso) {

        if (piso < 1 || piso > 5) {
            System.out.println("Piso inválido.");
            return;
        }

        System.out.println("[CONTROL] Botón externo del piso " + piso + " presionado.");

        solicitudActual = piso;  // Solo guardamos una solicitud a la vez
    }


    // Boton interno del ascensor
    public void presionarBotonAscensor(int destino) {

        if (destino < 1 || destino > 5) {
            System.out.println("Destino inválido.");
            return;
        }

        System.out.println("[CONTROL] Botón interno del ascensor: ir al piso " + destino);

        solicitudActual = destino;
    }


    // Procesar la solicitud actual

    public void procesarSolicitud() {

        if (solicitudActual == 0) {
            System.out.println("[CONTROL] No hay solicitudes pendientes.");
            return;
        }

        actualizarDireccion(solicitudActual);

        int pisoDestino = solicitudActual;
        solicitudActual = 0; // limpiamos la solicitud

        System.out.println("[CONTROL] Moviendo ascensor hacia el piso " + pisoDestino);

        ascensor.irAPiso(pisoDestino);
        ascensor.abrirPuertas();

        if (ascensor.unObstaculoEnLaPuerta()) {
            System.out.println("[CONTROL] Obstáculo detectado.");
            return;
        }

        ascensor.cerrarPuertas();
        direccionActual = "ninguna";
    }


    // Actualizar la dirección del ascensor

    private void actualizarDireccion(int pisoDestino) {

        int pisoActual = ascensor.getPisoActual();

        if (pisoDestino > pisoActual) direccionActual = "subir";
        else if (pisoDestino < pisoActual) direccionActual = "bajar";
        else direccionActual = "ninguna";

        System.out.println("[CONTROL] Dirección: " + direccionActual);
    }

    public boolean haySolicitud() {
        return solicitudActual != 0;
    }
}

