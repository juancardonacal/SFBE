import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SistemaControl {

    private Ascensor ascensor;
    private Piso[] pisos; 

    private List<Integer> destinosInternos; 
    private List<Integer> solicitudesExternasSubir; 
    private List<Integer> solicitudesExternasBajar; 

    private String direccionAscensor; 

    public SistemaControl(Ascensor ascensor, Piso[] pisos) {
        this.ascensor = ascensor;
        this.pisos = pisos; 

        this.destinosInternos = new ArrayList<>();
        this.solicitudesExternasSubir = new ArrayList<>();
        this.solicitudesExternasBajar = new ArrayList<>();

        this.direccionAscensor = "NINGUNA";
    }

    // --- BOTONES EXTERNOS ---
    public void presionarSubir(int piso) {
        if (piso < 1 || piso > 5 || piso == 5) return;
        Piso pisoObj = pisos[piso - 1]; 
        pisoObj.getBotonSubir().subir(); // Enciende luz externa
        
        if (!solicitudesExternasSubir.contains(piso)) {
            solicitudesExternasSubir.add(piso);
            Collections.sort(solicitudesExternasSubir); 
        }
        System.out.println("Sistema: Solicitud externa registrada (Piso " + piso + " UP)");
    }

    public void presionarBajar(int piso) {
        if (piso < 1 || piso > 5 || piso == 1) return;
        Piso pisoObj = pisos[piso - 1]; 
        pisoObj.getBotonBajar().bajar(); // Enciende luz externa
        
        if (!solicitudesExternasBajar.contains(piso)) {
            solicitudesExternasBajar.add(piso);
            Collections.sort(solicitudesExternasBajar, Collections.reverseOrder()); 
        }
        System.out.println("Sistema: Solicitud externa registrada (Piso " + piso + " DOWN)");
    }

    // --- BOTONES INTERNOS (AQUÍ ESTABA EL POSIBLE PROBLEMA) ---
    public void presionarInterno(int destino) {
        if (destino < 1 || destino > 5) return;
        
        // LÍNEA OBLIGATORIA: Enciende la luz del botón DENTRO del ascensor
        ascensor.presionarBoton(destino); 

        if (!destinosInternos.contains(destino)) {
            destinosInternos.add(destino);
            Collections.sort(destinosInternos); 
        }
        System.out.println("Sistema: Destino interno " + destino + " registrado en memoria.");
    }
    
    public void presionarMantenerPuertasAbiertas() {
        System.out.println("Manteniendo puertas...");
        ascensor.abrirPuertas();
    }

    // --- LÓGICA DE PROCESAMIENTO ---
    public void procesar() {
        if (destinosInternos.isEmpty() && solicitudesExternasSubir.isEmpty() && solicitudesExternasBajar.isEmpty()) {
            direccionAscensor = "NINGUNA";
            return;
        }
        if (direccionAscensor.equals("NINGUNA")) {
            direccionAscensor = determinarDireccionInicial();
            if (direccionAscensor.equals("NINGUNA")) return;
        }
        
        int pisoActual = ascensor.getPisoActual();
        System.out.println("\n>> PROCESANDO: Piso " + pisoActual + " | Dirección " + direccionAscensor);

        if (debeParar(pisoActual, direccionAscensor)) {
            atenderSolicitud(pisoActual, direccionAscensor);
            return; 
        }

        if (direccionAscensor.equals("ARRIBA")) {
            if (haySolicitudesEnDireccion("ARRIBA")) {
                if (pisoActual < 5) ascensor.irAPiso(pisoActual + 1); 
            } else {
                System.out.println("Fin de ruta SUBIDA. Cambiando a BAJAR.");
                direccionAscensor = "ABAJO"; 
            }
        } else if (direccionAscensor.equals("ABAJO")) {
            if (haySolicitudesEnDireccion("ABAJO")) {
                if (pisoActual > 1) ascensor.irAPiso(pisoActual - 1); 
            } else {
                System.out.println("Fin de ruta BAJADA. Cambiando a SUBIR.");
                direccionAscensor = "ARRIBA"; 
            }
        }
    }

    private String determinarDireccionInicial() {
        int actual = ascensor.getPisoActual();
        boolean arriba = false, abajo = false;
        for (int p : destinosInternos) { if (p > actual) arriba = true; if (p < actual) abajo = true; }
        for (int p : solicitudesExternasSubir) { if (p > actual) arriba = true; if (p < actual) abajo = true; }
        for (int p : solicitudesExternasBajar) { if (p > actual) arriba = true; if (p < actual) abajo = true; }
        if (arriba) return "ARRIBA";
        if (abajo) return "ABAJO";
        return "NINGUNA";
    }

    private boolean debeParar(int piso, String direccion) {
        if (destinosInternos.contains(piso)) return true;
        if (direccion.equals("ARRIBA") && solicitudesExternasSubir.contains(piso)) return true;
        if (direccion.equals("ABAJO") && solicitudesExternasBajar.contains(piso)) return true;
        if (direccion.equals("ARRIBA") && !hayMasArriba(piso) && solicitudesExternasBajar.contains(piso)) return true;
        if (direccion.equals("ABAJO") && !hayMasAbajo(piso) && solicitudesExternasSubir.contains(piso)) return true;
        return false;
    }

    private boolean haySolicitudesEnDireccion(String direccion) {
        int actual = ascensor.getPisoActual();
        if (direccion.equals("ARRIBA")) return hayMasArriba(actual);
        if (direccion.equals("ABAJO")) return hayMasAbajo(actual);
        return false;
    }
    
    private boolean hayMasArriba(int pisoRef) {
        for (int p : destinosInternos) if (p > pisoRef) return true;
        for (int p : solicitudesExternasSubir) if (p > pisoRef) return true;
        for (int p : solicitudesExternasBajar) if (p > pisoRef) return true; 
        return false;
    }

    private boolean hayMasAbajo(int pisoRef) {
        for (int p : destinosInternos) if (p < pisoRef) return true;
        for (int p : solicitudesExternasSubir) if (p < pisoRef) return true; 
        for (int p : solicitudesExternasBajar) if (p < pisoRef) return true; 
        return false;
    }
    
    private void atenderSolicitud(int piso, String direccion) {
        System.out.println("*** LLEGADA AL PISO " + piso + " ***");
        ascensor.abrirPuertas();
        
        if (destinosInternos.contains(piso)) {
            destinosInternos.remove((Integer) piso);
            ascensor.apagarBoton(piso); // APAGA LUZ INTERNA
        }

        Piso pisoObj = pisos[piso - 1];
        boolean atendioAlgo = false;
        
        if (direccion.equals("ARRIBA") && solicitudesExternasSubir.contains(piso)) {
            solicitudesExternasSubir.remove((Integer) piso);
            pisoObj.getBotonSubir().reiniciar(); // APAGA LUZ EXTERNA
            atendioAlgo = true;
        }
        if (direccion.equals("ABAJO") && solicitudesExternasBajar.contains(piso)) {
            solicitudesExternasBajar.remove((Integer) piso);
            pisoObj.getBotonBajar().reiniciar(); // APAGA LUZ EXTERNA
            atendioAlgo = true;
        }
        
        if (!atendioAlgo) {
             if (solicitudesExternasBajar.contains(piso)) {
                 solicitudesExternasBajar.remove((Integer) piso);
                 pisoObj.getBotonBajar().reiniciar();
             } else if (solicitudesExternasSubir.contains(piso)) {
                 solicitudesExternasSubir.remove((Integer) piso);
                 pisoObj.getBotonSubir().reiniciar();
             }
        }

        try { Thread.sleep(1000); } catch (InterruptedException e) {} 
        ascensor.cerrarPuertas();
    }
}