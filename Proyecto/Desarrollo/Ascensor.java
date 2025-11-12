public class Ascensor {

    private int pisoActual;
    private Puertas puertas;
    private botonesAscensor botones;
    public Ascensor(int pisoActual) {
        do {
            if (pisoActual >= 1 && pisoActual <= 5) {
                this.pisoActual = pisoActual;
            } else {
                System.out.println("El número de piso debe estar entre 1 y 5");
            }
        } while (pisoActual < 1 || pisoActual > 5);
        this.puertas = new Puertas();
        this.botones = new botonesAscensor(false, false, false, pisoActual);
    }

    public void irAPiso(int pisoDestino) {
        do {
            if (pisoDestino < 1 || pisoDestino > 5) {
                System.out.println("Piso inválido. El piso debe estar entre 1 y 5.");
            } else if (pisoDestino == pisoActual) {
                System.out.println("Ya estás en el piso " + pisoActual);
            } else {
                System.out.println("Moviendo ascensor del piso " + pisoActual + " al piso " + pisoDestino);
                pisoActual = pisoDestino;
            }
        } while (pisoDestino < 1 || pisoDestino > 5 || pisoDestino == pisoActual);
    }

    public void abrirPuertas() {
        puertas.abrirPuerta();
    }

    public void cerrarPuertas() {
        puertas.cerrarPuerta();
    }

    public void botonMantenerPuertasAbiertas() {
        botones.mantenerPuertasAbiertas();
        System.out.println("Manteniendo puertas abiertas");
    }

    public boolean unObstaculoEnLaPuerta() {
        return puertas.unObstaculoEnLaPuerta();
    }

    public boolean quitarObstaculo() {
        return puertas.quitarObstaculo();
    }

}