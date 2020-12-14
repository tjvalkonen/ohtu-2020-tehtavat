package ohtu.kivipaperisakset;

import java.util.HashMap;

public class Pelitehdas {
    
    private HashMap<String, KiviPaperiSakset> pelit;
    IO io;

    public Pelitehdas(IO io) {
        this.io = io;
        pelit = new HashMap<String, KiviPaperiSakset>();
        pelit.put("a", new KPSPelaajaVsPelaaja(io));
        pelit.put("b", new KPSTekoaly(io));
        pelit.put("c", new KPSParempiTekoaly(io));
    }
    
    public KiviPaperiSakset hae(String peli) {
        return pelit.get(peli);
    }
}
