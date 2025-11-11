public class botonesAscensor extends Botones {

    private int pisoDestino;

    public botonesAscensor(boolean presionado, boolean luz, boolean sonido, int pisoDestino) {
        super(presionado, luz, sonido);
        do {
            if (pisoDestino < 1 || pisoDestino > 5) {
                System.out.println("Piso inválido. El piso debe estar entre 1 y 5.");
                this.pisoDestino = pisoActual; // Asignar un valor predeterminado válido
            } else {
                this.pisoDestino = pisoDestino;
            }
        } while (pisoDestino < 1 || pisoDestino > 5);
    }

    public int seleccionarPiso() {
        presionarBoton();
        iluminar();
        activarSonido();
        System.out.println("Piso " + pisoDestino + " seleccionado");
        return pisoDestino;
    }

    public void mantenerPuertasAbiertas() {
        presionarBoton();
        iluminar();
        activarSonido();
        System.out.println("Manteniendo puertas abiertas");
    }
}