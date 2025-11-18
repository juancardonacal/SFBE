public class SistemaControl {

    private Ascensor ascensor;

    // Solicitud externa
    private int solicitudExternaPiso;        
    private String solicitudExternaDireccion; // "subir", "bajar", "ninguna"

    // Solicitud interna
    private int solicitudInternaDestino;     

    // Dirección actual del ascensor
    private String direccionAscensor; // "subir", "bajar", "ninguna"


    // -----------------------------------
    //         CONSTRUCTOR
    // -----------------------------------
    public SistemaControl(Ascensor ascensor) {
        this.ascensor = ascensor;

        this.solicitudExternaPiso = 0;
        this.solicitudExternaDireccion = "ninguna";

        this.solicitudInternaDestino = 0;

        // Ascensor inicia sin dirección
        this.direccionAscensor = "ninguna";
    }


    // ============================================================
    //           BOTONES EXTERNOS (subir / bajar)
    // ============================================================

    public void presionarSubir(int piso) {
        if (piso < 1 || piso > 5) {
            System.out.println("Piso inválido.");
            return;
        }

        solicitudExternaPiso = piso;
        solicitudExternaDireccion = "subir";

        System.out.println("[CONTROL] Solicitud externa en piso " + piso + " (subir)");
    }

    public void presionarBajar(int piso) {
        if (piso < 1 || piso > 5) {
            System.out.println("Piso inválido.");
            return;
        }

        solicitudExternaPiso = piso;
        solicitudExternaDireccion = "bajar";

        System.out.println("[CONTROL] Solicitud externa en piso " + piso + " (bajar)");
    }


    // ============================================================
    //           BOTÓN INTERNO DEL ASCENSOR
    // ============================================================

    public void presionarInterno(int destino) {
        if (destino < 1 || destino > 5) {
            System.out.println("Destino inválido.");
            return;
        }

        solicitudInternaDestino = destino;
        System.out.println("[CONTROL] Botón interno: destino " + destino);
    }


    // ============================================================
    //           PROCESAR SOLICITUDES
    // ============================================================

    public void procesar() {

        // PRIORIDAD 1 → Si no hay dirección y hay solicitud externa
        if (direccionAscensor.equals("ninguna") && haySolicitudExterna()) {

            actualizarDireccion(solicitudExternaPiso);
            moverAscensor(solicitudExternaPiso);

            ascensor.abrirPuertas();
            if (!ascensor.unObstaculoEnLaPuerta()) {
                ascensor.cerrarPuertas();
            }

            // limpiar solicitud externa
            solicitudExternaPiso = 0;
            solicitudExternaDireccion = "ninguna";

            return;
        }

        // PRIORIDAD 2 → Solicitud interna
        if (haySolicitudInterna()) {

            actualizarDireccion(solicitudInternaDestino);
            moverAscensor(solicitudInternaDestino);

            ascensor.abrirPuertas();
            if (!ascensor.unObstaculoEnLaPuerta()) {
                ascensor.cerrarPuertas();
            }

            solicitudInternaDestino = 0;
            direccionAscensor = "ninguna";

            return;
        }

        // PRIORIDAD 3 → Nada pendiente
        System.out.println("[CONTROL] No hay solicitudes.");
        direccionAscensor = "ninguna";
    }


    // ============================================================
    //           MÉTODOS AUXILIARES
    // ============================================================

    private boolean haySolicitudExterna() {
        return solicitudExternaPiso != 0;
    }

    private boolean haySolicitudInterna() {
        return solicitudInternaDestino != 0;
    }

    private void actualizarDireccion(int destino) {

        int actual = ascensor.getPisoActual();

        if (destino > actual)
            direccionAscensor = "subir";
        else if (destino < actual)
            direccionAscensor = "bajar";
        else
            direccionAscensor = "ninguna";

        System.out.println("[CONTROL] Dirección: " + direccionAscensor);
    }

    private void moverAscensor(int destino) {
        System.out.println("[CONTROL] Moviendo ascensor hacia piso " + destino + "...");
        ascensor.irAPiso(destino);
    }
}
