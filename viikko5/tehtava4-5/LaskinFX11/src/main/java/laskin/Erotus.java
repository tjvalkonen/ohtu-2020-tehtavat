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
public class Erotus extends Komento {
    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    } 
    
    @Override
    public void laske() {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }     
        sovellus.miinus(arvo);
    }
        
    @Override
    public void peruKomento() {
        sovellus.plus(edellinenSyotekentta); 
    }
}
