/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Tomas
 */
public abstract class Komento {
    // protected Event event;
    public int edellinenTuloskentta; 
    protected TextField tuloskentta;
    public int edellinenSyotekentta;
    protected TextField syotekentta; 
    protected Button nollaa;
    protected Button undo;
    protected Sovelluslogiikka sovellus;
    
    public Komento (TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        // this.edellinenTuloskentta = tuloskentta;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }
    
    public void suorita() {
        laske();
        
        try {
            edellinenTuloskentta = Integer.parseInt(tuloskentta.getText());
            } catch (Exception e) {
            }
        try {
            edellinenSyotekentta = Integer.parseInt(syotekentta.getText());
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
    
    public void peru () {
        peruKomento();
        
        int laskunTulos = sovellus.tulos();
        
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
        
        if ( laskunTulos==0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }

        undo.disableProperty().set(true);
    }
    
    public abstract void laske();
    
    public abstract void peruKomento();
}
