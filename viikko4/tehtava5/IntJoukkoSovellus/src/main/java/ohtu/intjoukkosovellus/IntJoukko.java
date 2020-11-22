
package ohtu.intjoukkosovellus;

public class IntJoukko {

    private int[] joukkoTaulu; //  = new int[2];      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenMaara = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        joukkoTaulu  = new int[1];
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        joukkoTaulu = new int[kapasiteetti];
    }
    

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin"); //heitin vaan jotain :D
        }
        joukkoTaulu = new int[kapasiteetti];
    }

    public boolean lisaa(int luku) {

        if (!kuuluuJoukkoon(luku)) {
            joukkoTaulu[alkioidenMaara] = luku;
            alkioidenMaara++;
            
            if (alkioidenMaara % joukkoTaulu.length == 0) {             
                int[] apuJoukkoTaulu; // = new int[joukkoTaulu.length];           
                apuJoukkoTaulu = joukkoTaulu;
                joukkoTaulu = new int[alkioidenMaara + 1];
                kopioiTaulukko(apuJoukkoTaulu, joukkoTaulu);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluuJoukkoon(int luku) {
        for (int i = 0; i < alkioidenMaara; i++) { // joukkoTaulu.length  
            if (luku == joukkoTaulu[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        if (kuuluuJoukkoon(luku)) {
            int kohta = 0; 
            for (int i = 0; i < alkioidenMaara; i++) { //   joukkoTaulu.length
                if (luku == joukkoTaulu[i]) {
                    kohta = i; //siis luku löytyy tuosta kohdasta :D
                    joukkoTaulu[kohta] = 0;
                    break;
                }
            }
            for (int j = kohta; j < alkioidenMaara - 1; j++) { // joukkoTaulu.length
                int apu = joukkoTaulu[j];
                joukkoTaulu[j] = joukkoTaulu[j + 1];
                joukkoTaulu[j + 1] = apu;
            }
            alkioidenMaara--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int getAlkioidenMaara() {
        return alkioidenMaara;
    }

    @Override
    public String toString() {
        if (alkioidenMaara == 0) {
            return "{}";
        } else if (alkioidenMaara == 1) {
            return "{" + joukkoTaulu[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenMaara - 1; i++) {
                tuotos += joukkoTaulu[i];
                tuotos += ", ";
            }
            tuotos += joukkoTaulu[alkioidenMaara - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenMaara];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukkoTaulu[i];
        }
        return taulu;
    }
  
    public int getLuku(int i) {
        if(joukkoTaulu.length > i && i >= 0) {
            return joukkoTaulu[i];            
        }
        return 0;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        for (int i = 0; i < b.getAlkioidenMaara(); i++) {
            a.lisaa(b.getLuku(i));
        }
        return a;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko apuJoukko = new IntJoukko();

        for (int i = 0; i < a.getAlkioidenMaara(); i++) {
            for (int j = 0; j < b.getAlkioidenMaara(); j++) {
                if (a.getLuku(i) == b.getLuku(j)) {
                    apuJoukko.lisaa(b.getLuku(j));
                }
            }
        }
        return apuJoukko;
    }
    
    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        for (int i = 0; i < b.getAlkioidenMaara(); i++) {
            a.poista(b.getLuku(i));
        }
        return a;
    }
}
