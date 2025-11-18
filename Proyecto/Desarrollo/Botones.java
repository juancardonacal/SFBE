public abstract class Botones {

    protected boolean presionado;
    protected boolean Luz;
    protected boolean sonido;

    public Botones() {
        this.presionado = false;
        this.Luz = false;
        this.sonido = false;
    }

    public void iluminar() {
        this.Luz = true;
        // ESTE MENSAJE DEBE SALIR AL PRESIONAR
        System.out.println("   >>> [LUZ] El botón se ha ILUMINADO (ON) <<<");
    }

    public void activarSonido() {
        this.sonido = true;
        // ESTE MENSAJE DEBE SALIR AL PRESIONAR
        System.out.println("   >>> [SONIDO] *BEEP* Botón presionado <<<");
    }

    // Este método activa todo a la vez
    public void presionarBoton() {
        this.presionado = true;
        activarSonido(); // Llama al sonido
        iluminar();      // Llama a la luz
    }

    // Este método apaga todo
    public void reiniciar() {
        this.presionado = false;
        this.Luz = false;
        this.sonido = false;
        // ESTE MENSAJE DEBE SALIR AL LLEGAR EL ASCENSOR
        System.out.println("   >>> [LUZ] Solicitud atendida. El botón se ha APAGADO (OFF) <<<");
    }
    
    public boolean estaPresionado() {
        return presionado;
    }
}