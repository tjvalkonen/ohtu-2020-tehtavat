package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    private Button undo;
    private Sovelluslogiikka sovellus;
    private Map<Button, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
        this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus) );
        this.komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
    }
    
    @Override
    public void handle(Event event) {
        
        if (event.getTarget() != undo) {
            Komento komento = this.komennot.get((Button)event.getTarget());
            this.edellinen = komento;
            komento.suorita();
        } else {
            this.edellinen.peru();
            this.edellinen = null;
        }
    }
    
    void suoritaLoppu(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        try {
                edellinen.edellinenTuloskentta = Integer.parseInt(tuloskentta.getText());
            } catch (Exception e) {
            }
        try {
                edellinen.edellinenSyotekentta = Integer.parseInt(syotekentta.getText());
            } catch (Exception e) {
            }

        int laskunTulos = sovellus.tulos();
        
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
        
        if ( laskunTulos==0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);

    }
    
    void peruKomento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        int laskunTulos = sovellus.tulos();
        
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
        
        if ( laskunTulos==0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }

        undo.disableProperty().set(true);
        edellinen = null;
    }
    
    public class Summa extends Komento {

        public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
            super(tuloskentta, syotekentta,  nollaa, undo, sovellus);
        }
        
        @Override
        public void suorita() {

            int arvo = 0;
            try {
                arvo = Integer.parseInt(edellinen.syotekentta.getText());
            } catch (Exception e) {
            }
            sovellus.plus(arvo);          
            suoritaLoppu(tuloskentta, syotekentta, nollaa, undo, sovellus);
        }
        @Override
        public void peru() {

            sovellus.miinus(edellinen.edellinenSyotekentta); 
            peruKomento(tuloskentta, syotekentta, nollaa, undo, sovellus);
        }
    }
    
    public class Erotus extends Komento {
        public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
            super(tuloskentta, syotekentta, nollaa, undo, sovellus);
        }      
        @Override
        public void suorita() {
            int arvo = 0;
            try {
                arvo = Integer.parseInt(syotekentta.getText());
            } catch (Exception e) {
            }
            sovellus.miinus(arvo);   
            suoritaLoppu(tuloskentta, syotekentta, nollaa, undo, sovellus);
        }       
        @Override
        public void peru() {
            sovellus.plus(edellinen.edellinenSyotekentta); 
            peruKomento(tuloskentta, syotekentta, nollaa, undo, sovellus);
        }
    }
    
    public class Nollaa extends Komento {
        public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
            super(tuloskentta, syotekentta,  nollaa, undo, sovellus);
        }
        
        @Override
        public void suorita() {
            sovellus.nollaa();
            suoritaLoppu(tuloskentta, syotekentta, nollaa, undo, sovellus);
        }
        
        @Override
        public void peru() {
            sovellus.plus(edellinen.edellinenTuloskentta);
            peruKomento(tuloskentta, syotekentta, nollaa, undo, sovellus);
        }
    }

}
