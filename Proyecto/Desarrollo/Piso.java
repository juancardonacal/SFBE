public class Piso {
    private int numeroPiso;

    // Se inicializan los botones (se usa el constructor simple de Botones)
    botonesPiso botonSubir = new botonesPiso(false, false, false, "Arriba");
    botonesPiso botonBajar = new botonesPiso(false, false, false, "Abajo");

    public Piso(int numeroPiso) {
        // CRÍTICO: Eliminado el bucle infinito. Se asigna un valor por defecto si es inválido.
        if (numeroPiso >= 1 && numeroPiso <= 5) {
            this.numeroPiso = numeroPiso;
        } else {
            System.out.println("Piso inválido. Se asigna piso 1.");
            this.numeroPiso = 1;
        }
    }

    public void presionarBotonSubir() {
        if (numeroPiso == 5) {
            System.out.println("[Piso] Ya estás en el piso más alto. No hay botón de subir.");
            return;
        }
        botonSubir.subir();
    }

    public void presionarBotonBajar() {
        if (numeroPiso == 1) {
            System.out.println("[Piso] Ya estás en el piso más bajo. No hay botón de bajar.");
            return;
        }
        botonBajar.bajar();
    }
    
    // Getters cruciales para que SistemaControl pueda leer y apagar los botones
    public int getNumeroPiso() {
        return numeroPiso;
    }
    
    public botonesPiso getBotonSubir() {
        return botonSubir;
    }
    
    public botonesPiso getBotonBajar() {
        return botonBajar;
    }
}