package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto toinenVarasto;
    Varasto kolmasVarasto;
    Varasto neljasVarasto;
    Varasto viidesVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
	toinenVarasto = new Varasto(-10);
	kolmasVarasto = new Varasto(50, 20);
	neljasVarasto = new Varasto(50, 100);
	viidesVarasto = new Varasto(-50, -10);
	
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaAlkusaldoJaTilavuus() {
	assertEquals(20, kolmasVarasto.getSaldo(), vertailuTarkkuus);
	assertEquals(50, kolmasVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusNollataan() {
	assertEquals(0, toinenVarasto.getSaldo(), vertailuTarkkuus);
    }    

    @Test 
    public void negatiivinenTilavuusNollataan2() {
	assertEquals(0, viidesVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenAlkusaldoNollataan() {
	assertEquals(0, viidesVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoaEiVoiYlitayttaaLuodessa() {
	assertEquals(50, neljasVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringToimii() {
	String merk = varasto.toString();
	String expected = "saldo = 0.0, vielä tilaa 10.0";
	assertTrue(merk.equals(expected));
    } 
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLisataEnemmanKuinTilavuus() {
	varasto.lisaaVarastoon(1000);
	assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaaminenEiVaikuta() {
	double saldo = varasto.getSaldo();
	varasto.lisaaVarastoon(-10);
	assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenEiVaikuta() {
	double saldo = varasto.getSaldo();
	varasto.otaVarastosta(-10);
	assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void saldoaIsompiOttaminenTyhjentaaVaraston() {
	double alkupSaldo = varasto.getSaldo();
	double saatuMaara = varasto.otaVarastosta(1000);
	assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
	assertEquals(alkupSaldo, saatuMaara, vertailuTarkkuus);
    }


}
