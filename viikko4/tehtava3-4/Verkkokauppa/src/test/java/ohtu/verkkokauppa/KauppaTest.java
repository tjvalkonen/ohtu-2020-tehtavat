package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;

    @Before
    public void setUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void tuotteenPoistoOstoskoristaToimiiJaOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanJaTiedotOvatOikein() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.poistaKorista(1);     // Poistetaan korista tuote numero 1
        k.tilimaksu("pekka", "12345");


        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikealla asiakkaalla, viitteellä ja tilinumerolla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikealla asiakkaalla, viitteellä ja tilinumerolla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void kahdenEriOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        // määritellään että tuote numero 2 on piimä jonka hinta on 4 ja saldo 20
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(20); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 4));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli piimää
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikealla asiakkaalla, viitteellä ja tilinumerolla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(9));
    }

    @Test
    public void kahdenSamanOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 2 on piimä jonka hinta on 4 ja saldo 20
        when(varasto.saldo(2)).thenReturn(20); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 4));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli piimää
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli piimää
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikealla asiakkaalla, viitteellä ja tilinumerolla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(8));
    }

    @Test
    public void kahdenOstoksenJoistaToinenLoppuPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // määritellään että tuote numero 2 on piimä jonka hinta on 4 ja saldo 0
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 4));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli piimää
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikealla asiakkaalla, viitteellä ja tilinumerolla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void eriOstoskertojenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // määritellään että viitegeneraattori palauttaa viitteet 1, 2 ja 3
        when(viite.uusi())
            .thenReturn(1)
            .thenReturn(2)
            .thenReturn(3);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // määritellään että tuote numero 2 on piimä jonka hinta on 4 ja saldo 20
        when(varasto.saldo(2)).thenReturn(20); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 4));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset 1
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli piimää
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikealla asiakkaalla, viitteellä ja tilinumerolla
        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), anyString(), eq(9));

        // tehdään ostokset 2
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli piimää
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli piimää
        k.tilimaksu("Tero", "5555");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikealla asiakkaalla, viitteellä ja tilinumerolla
        verify(pankki).tilisiirto(eq("Tero"), eq(2), eq("5555"), anyString(), eq(13));

        // tehdään ostokset 3
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli piimää
        k.tilimaksu("Lasse", "33");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikealla asiakkaalla, viitteellä ja tilinumerolla
        verify(pankki).tilisiirto(eq("Lasse"), eq(3), eq("33"), anyString(), eq(14));
    }
}