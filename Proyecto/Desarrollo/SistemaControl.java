public class SistemaControl {
    // Código del sistema de control del ascensor
    private boolean botonesFuncionan;
    private boolean puertasFuncionan;
    private boolean ascensorAtascado;
    public SistemaControl() {
        this.botonesFuncionan = true;
        this.puertasFuncionan = true;
        this.ascensorAtascado = false;
    }
    public boolean verificarBotones() {
        return botonesFuncionan;
    }
    public boolean verificarPuertas() {
        return puertasFuncionan;
    }
    public boolean verificarAscensor() {
        return !ascensorAtascado;
    }
}
