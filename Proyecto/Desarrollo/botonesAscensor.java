public class botonesAscensor extends Botones {

    private int pisoDestino;
    private Puertas puertas;

    public botonesAscensor(boolean presionado, boolean luz, boolean sonido, int pisoDestino) {
        super(presionado, luz, sonido);
        if (pisoDestino < 1 || pisoDestino > 5) {
            System.out.println("Piso inválido. El piso debe estar entre 1 y 5. Se asignará el piso 1 por defecto.");
            this.pisoDestino = 1; // Asignar un valor predeterminado válido
        } else {
            this.pisoDestino = pisoDestino;
        }
        this.puertas = new Puertas();
    }

    public int seleccionarPiso() {
        presionarBoton();
        iluminar();
        activarSonido();
        System.out.println("Piso " + pisoDestino + " seleccionado");
        return pisoDestino;
    }

    public void mantenerPuertasAbiertas() {
        puertas.abrirPuerta();
        presionarBoton();
        iluminar();
        activarSonido();
        System.out.println("Manteniendo puertas abiertas");
    }
}