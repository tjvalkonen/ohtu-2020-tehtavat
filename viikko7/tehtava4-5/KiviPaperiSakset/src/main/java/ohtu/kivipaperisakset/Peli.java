/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

/**
 *
 * @author Tomas
 */
public class Peli {
    private IO io;
    public Pelitehdas pelit;
    
    public Peli(IO io) {
        this.io = io;
        pelit = new Pelitehdas(io);
    }
    
    public void pelaa() {
        while (true) {
            io.print("Valitse pelataanko");
            io.print(" (a) ihmistä vastaan");
            io.print(" (b) tekoälyä vastaan");
            io.print(" (c) parannettua tekoälyä vastaan");
            io.print("muilla valinnoilla lopetataan");
            
            String vastaus = io.nextLine();
 
            pelit.hae(vastaus).pelaa();
        }
    }
    
}
