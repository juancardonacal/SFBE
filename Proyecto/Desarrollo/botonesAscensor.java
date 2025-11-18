public class botonesAscensor extends Botones {

    private int pisoDestino;

    public botonesAscensor(boolean presionado, boolean luz, boolean sonido, int pisoDestino) {
        super(); // Llama al constructor de la clase padre (Botones)
        
        if (pisoDestino < 1 || pisoDestino > 5) {
            this.pisoDestino = 1;
        } else {
            this.pisoDestino = pisoDestino;
        }
    }

    // Método para seleccionar un piso desde adentro
    public int seleccionarPiso() {
        System.out.println("[Panel Interno] Seleccionando piso " + pisoDestino + "...");
        
        // IMPORTANTE: Llamamos a este método del padre para activar Luz y Sonido
        presionarBoton(); 
        
        return pisoDestino;
    }

    // Método para el botón de mantener puertas abiertas
    public void mantenerPuertasAbiertas() {
        System.out.println("[Panel Interno] Presionando botón Mantener Puertas...");
        
        // IMPORTANTE: También activa luz y sonido
        presionarBoton(); 
    }
    
    public int getPisoDestino() {
        return pisoDestino;
    }
}