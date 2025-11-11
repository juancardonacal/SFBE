public class botonesPiso extends Botones {

    private String direccion;
    public botonesPiso(boolean presionado, boolean luz, boolean sonido, String direccion) {
        super(presionado, luz, sonido);
        this.direccion = "ninguna";
    }

    public void subir() {
            direccion = "Arriba";
            presionarBoton();
            iluminar();
            activarSonido();
    }
    public void bajar() {
            direccion = "Abajo";
            presionarBoton();
            iluminar();
            activarSonido();
    }

    public String elegirDireccion() {
        do {
            if (direccion.equalsIgnoreCase("Arriba")) {
                System.out.println("Direccion arriba elegida");
                subir();
            } else if (direccion.equalsIgnoreCase("Abajo")) {
                System.out.println("Direccion abajo elegida");
                bajar();
            } else {
                System.out.println("Direccion invalida, elija 'Arriba' o 'Abajo'");
            }
        } while (!direccion.equalsIgnoreCase("Arriba") && !direccion.equalsIgnoreCase("Abajo"));
        return direccion;

    }
}