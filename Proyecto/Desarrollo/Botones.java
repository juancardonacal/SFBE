public class Botones {

    protected boolean presionado;
    protected boolean Luz;
    protected boolean sonido;

    public Botones(boolean presionado, boolean luz, boolean sonido) {
        this.presionado = false;
        this.Luz = false;
        this.sonido = false;
    }

    public void iluminar() {
        if (Luz == false) {
            Luz = true;
            System.out.println("Luz encendida");
        } else {
            Luz = false;
            System.out.println("Luz apagada");
    }


    }

    public void activarSonido() {
        if (sonido == false) {
            if (presionado == true) {
                System.out.println("boton presionado, sonando...");
                return;
            }
            sonido = true;
            System.out.println("Sonido activado");
        } else {
            sonido = false;
            System.out.println("Sonido desactivado");
        }
    }
    public void presionarBoton() {
        presionado = true;
        System.out.println("Boton presionado");
    }

}