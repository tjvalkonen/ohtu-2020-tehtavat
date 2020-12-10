package ohtu.kivipaperisakset;

public abstract class KiviPaperiSakset {

    public IO io;
    
    public KiviPaperiSakset (IO io) {
        this.io = io;
    }
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari(); 

        String ekanSiirto;
        
        String tokanSiirto;

        while (true) {
            ekanSiirto = ensimmaisenSiirto();
            if(!onkoOkSiirto(ekanSiirto)){
                break;
            }
            
            tokanSiirto = toisenSiirto(ekanSiirto);
            if(!onkoOkSiirto(tokanSiirto)){
                break;
            }
            
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.println(tuomari.toString());
            io.println("");
        }
        
        io.println("");
        io.println("Kiitos!");
        io.println(tuomari.toString());
    }
    
    protected String ensimmaisenSiirto() {
        io.println("Ensimmäisen pelaajan siirto: ");
        return io.nextLine();
    }
    
    abstract protected String toisenSiirto(String ekanSiirto);
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
