package ohtu.kivipaperisakset;

public class Paaohjelma {

    public static void main(String[] args) {      
        Peli peli = new Peli(new KonsoliIO());
        peli.pelaa();
    }
}
