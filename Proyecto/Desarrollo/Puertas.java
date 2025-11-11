public class Puertas {

    private boolean abierta;
    public Puertas() {
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
}