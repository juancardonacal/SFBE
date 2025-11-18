public class botonesPiso extends Botones {

    private String direccion;

    public botonesPiso(boolean presionado, boolean luz, boolean sonido, String direccion) {
        super(); // Llama al constructor de Botones
        this.direccion = direccion;
    }

    public void subir() {
        direccion = "Arriba";
        System.out.println("[Panel Pasillo] Presionando botón SUBIR...");
        presionarBoton(); // <--- IMPORTANTE: Esto activa la luz y el sonido
    }

    public void bajar() {
        direccion = "Abajo";
        System.out.println("[Panel Pasillo] Presionando botón BAJAR...");
        presionarBoton(); // <--- IMPORTANTE: Esto activa la luz y el sonido
    }
    
    public String getDireccion() {
        return direccion;
    }
}