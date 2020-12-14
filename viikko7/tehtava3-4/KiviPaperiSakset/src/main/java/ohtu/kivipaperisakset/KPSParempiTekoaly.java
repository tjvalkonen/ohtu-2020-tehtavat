package ohtu.kivipaperisakset;

public class KPSParempiTekoaly extends KiviPaperiSakset {

    private TekoalyParannettu tekoaly = new TekoalyParannettu(20);

    public KPSParempiTekoaly(IO io) {
        super(io);
    }
    
    @Override
    protected String toisenSiirto(String ekanSiirto) {
        String tokanSiirto = tekoaly.annaSiirto();
        io.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return tokanSiirto;
    }
}
