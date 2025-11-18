public class Ascensor {

    private int pisoActual;
    private Puertas puertas;
    // ARRAY CRÍTICO: Aquí viven los botones físicos
    private botonesAscensor[] botonesInternos;

    public Ascensor(int pisoActual) {
        if (pisoActual >= 1 && pisoActual <= 5) {
            this.pisoActual = pisoActual;
        } else {
            this.pisoActual = 1;
        }
        this.puertas = new Puertas();
        
        // INICIALIZACIÓN CRÍTICA: Creamos los 5 botones
        this.botonesInternos = new botonesAscensor[5];
        for (int i = 0; i < 5; i++) {
            // Cada botón se crea listo para usarse
            botonesInternos[i] = new botonesAscensor(false, false, false, i + 1);
        }
    }

    // ESTE ES EL MÉTODO QUE FALTABA O ESTABA DESCONECTADO
    public void presionarBoton(int pisoDestino) {
        if (pisoDestino >= 1 && pisoDestino <= 5) {
            // Llamamos directamente al objeto botón para que haga Luz y Sonido
            botonesInternos[pisoDestino - 1].seleccionarPiso(); 
        } else {
            System.out.println("[ERROR] Piso destino inválido en Ascensor");
        }
    }
    
    public void apagarBoton(int piso) {
        if (piso >= 1 && piso <= 5) {
            botonesInternos[piso - 1].reiniciar(); 
        }
    }

    public void irAPiso(int nuevoPiso) {
        if (nuevoPiso == pisoActual) return;
        System.out.println("... Ascensor moviéndose al piso " + nuevoPiso + " ...");
        pisoActual = nuevoPiso;
    }

    public void abrirPuertas() { puertas.abrirPuerta(); }
    public void cerrarPuertas() { puertas.cerrarPuerta(); }
    public int getPisoActual() { return pisoActual; }
    public boolean unObstaculoEnLaPuerta() { return puertas.unObstaculoEnLaPuerta(); }
    public void simularObstaculo(boolean estado) { puertas.simularObstaculo(estado); }
}