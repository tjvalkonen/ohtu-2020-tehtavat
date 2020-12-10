/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.HashMap;

/**
 *
 * @author Tomas
 */
public class Pelitehdas {
    
    private HashMap<String, KiviPaperiSakset> pelit;
    private KiviPaperiSakset ihminen;
    IO io;

    public Pelitehdas(IO io) {
        this.io = io;
        pelit = new HashMap<String, KiviPaperiSakset>();
        pelit.put("a", new KPSPelaajaVsPelaaja(io));
        pelit.put("b", new KPSTekoaly(io));
        pelit.put("c", new KPSParempiTekoaly(io));
        ihminen = new KPSPelaajaVsPelaaja(io); 
    }
    
    public KiviPaperiSakset hae(String peli) {
        // jos väärä komento >>>
        io.print("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        
        return pelit.getOrDefault(peli, ihminen);
    }
}
