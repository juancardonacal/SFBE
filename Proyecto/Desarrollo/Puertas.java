public class Puertas {

    private boolean obstaculo;
    private boolean abierta;

    public Puertas() {
        this.obstaculo = false;
        this.abierta = false;
    }

    public void abrirPuerta() {
        if (!abierta) {
            abierta = true;
            System.out.println("-> Puerta abierta");
        } else {
            System.out.println("-> La puerta ya está abierta");
        }
    }

    public void cerrarPuerta() {
        // Verifica el obstáculo antes de cerrar (Criterio 6)
        if (unObstaculoEnLaPuerta()) {
            System.out.println("-> [ALARMA] ¡Obstáculo! No se permite el cierre.");
        } else if (abierta) {
            abierta = false;
            System.out.println("-> Puerta cerrada");
        } else {
            System.out.println("-> La puerta ya está cerrada");
        }
    }

    public boolean unObstaculoEnLaPuerta() {
        return obstaculo;
    }

    public boolean quitarObstaculo() {
        if (obstaculo) {
            obstaculo = false;
            System.out.println("-> Obstáculo retirado de la puerta");
            return true;
        }
        return false;
    }

    // Método para SIMULAR el obstáculo desde el menú principal (para pruebas)
    public void simularObstaculo(boolean estado) {
        this.obstaculo = estado;
        if (estado) {
            System.out.println("-> [DEBUG] Obstáculo SIMULADO.");
        } else {
            System.out.println("-> [DEBUG] Obstáculo RETIRADO.");
        }
    }
}