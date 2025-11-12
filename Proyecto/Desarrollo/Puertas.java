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
            System.out.println("Puerta abierta");
        } else {
            System.out.println("La puerta ya está abierta");
        }
    }
    public void cerrarPuerta() {
        if (abierta) {
            abierta = false;
            System.out.println("Puerta cerrada");
        } else {
            System.out.println("La puerta ya está cerrada");
        }
    }

    public boolean unObstaculoEnLaPuerta() {
        // Simulación de detección de obstáculo
        if (obstaculo) {
            System.out.println("Obstáculo detectado en la puerta");
            return true;
        } else {
            System.out.println("No hay obstáculos en la puerta");
            return false;
        }
    }

    public boolean quitarObstaculo() {
        if (obstaculo) {
            obstaculo = false;
            System.out.println("Obstáculo retirado de la puerta");
            return true;
        } else {
            System.out.println("No hay obstáculos para retirar");
            return false;
        }
    }
}