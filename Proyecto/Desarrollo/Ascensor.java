public class Ascensor {

    private int pisoActual;
    private Puertas puertas;
    public Ascensor(int pisoActual) {

    if (pisoActual >= 1 && pisoActual <= 5) {
                this.pisoActual = pisoActual;
            } else {
                this.pisoActual = 1;
                System.out.println("El número de piso debe estar entre 1 y 5");
            }
        this.puertas = new Puertas();
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

    public int getPisoActual() {
        return pisoActual;
    }

    public boolean unObstaculoEnLaPuerta() {
        return puertas.unObstaculoEnLaPuerta();
    }

    public boolean quitarObstaculo() {
        return puertas.quitarObstaculo();
    }

}