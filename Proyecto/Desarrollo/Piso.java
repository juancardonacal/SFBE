public class Piso {
    private int numeroPiso;

    public Piso(int numeroPiso) {
        do {
            if (numeroPiso >= 1 && numeroPiso <= 5) {
                this.numeroPiso = numeroPiso;
            } else {
                System.out.println("El número de piso debe estar entre 1 y 5");
            }
        } while (numeroPiso < 1 || numeroPiso > 5);
    }

    botonesPiso botonSubir = new botonesPiso(false, false, false, "Arriba");
    botonesPiso botonBajar = new botonesPiso(false, false, false, "Abajo");

    public void presionarBotonSubir() {
        if (numeroPiso == 5) {
            System.out.println("Ya estás en el piso más alto, no puedes subir más.");
            return;
        }
        botonSubir.subir();
    }

    public void presionarBotonBajar() {
        if (numeroPiso == 1) {
            System.out.println("Ya estás en el piso más bajo, no puedes bajar más.");
            return;
        }
        botonBajar.bajar();
    }

    


}