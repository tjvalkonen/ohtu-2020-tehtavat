package ohtu.kivipaperisakset;

public class Peli {
    private IO io;
    public Pelitehdas pelit;
    
    public Peli(IO io) {
        this.io = io;
        pelit = new Pelitehdas(io);
    }
    
    public void pelaa() {
        while (true) {
            io.println("Valitse pelataanko");
            io.println(" (a) ihmistä vastaan");
            io.println(" (b) tekoälyä vastaan");
            io.println(" (c) parannettua tekoälyä vastaan");
            io.println("muilla valinnoilla lopetataan");
            
            String vastaus = io.nextLine();
            if (vastaus.equals("a") || vastaus.equals("b") || vastaus.equals("c")) {
                io.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");                
            } else {
                break;
            }
            pelit.hae(vastaus).pelaa();
        }
    }  
}
