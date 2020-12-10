/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

// import java.util.Scanner;
/**
 *
 * @author Tomas
 */
public abstract class KiviPaperiSakset {
    
    // private static final Scanner scanner = new Scanner(System.in);
    public IO io;
    
    public KiviPaperiSakset (IO io) {
        this.io = io;
    }
    
    public void pelaa() {
        // io.print("Oikeesti eka rivi kun pelataan");
        Tuomari tuomari = new Tuomari(); 

        //
        // System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = ensimmaisenSiirto();
        
        // System.out.print("Toisen pelaajan siirto: ");
        
        String tokanSiirto = toisenSiirto();
    
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            io.print("Tulostuuko whilessä?");
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.print(tuomari.toString());
            io.print("");

            ekanSiirto = ensimmaisenSiirto();

            tokanSiirto = toisenSiirto();
        }
        
        io.print("");
        io.print("Kiitos!");
        io.print(tuomari.toString());
    }
    
    protected String ensimmaisenSiirto() {
        io.print("Ensimmäisen pelaajan siirto: ");
        return io.nextLine(); //"" ; // scanner.nextLine();
    }
    
    abstract protected String toisenSiirto();
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
