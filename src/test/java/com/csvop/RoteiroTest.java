package com.csvop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.csvop.entidades.Bairro;
import com.csvop.entidades.Roteiro;
import com.csvop.entidades.geometria.Ponto;
import com.csvop.entidades.geometria.Reta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoteiroTest {
    private List<Bairro> bairros;
    private Roteiro roteiro;
    private Roteiro roteiro2;

    @BeforeEach
    public void setup(){
        bairros = new ArrayList<>();
        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));
        roteiro = new Roteiro(bairros.get(1), bairros.get(4), bairros);
        roteiro2 = new Roteiro(bairros.get(2), bairros.get(4), bairros);
    }

    @Test
    public void testaRota(){
        Reta rotaEsp = new Reta(new Ponto(40,35),new Ponto(50,15));
        assertEquals(rotaEsp,roteiro.getRota());
    }

    @Test
    public void testaBairrosPercorridos(){
        Roteiro roteiro = new Roteiro(bairros.get(1), bairros.get(4), bairros);
        Collection<Bairro> esperado = new ArrayList<>();
        esperado.add(bairros.get(1));
        esperado.add(bairros.get(2));
        esperado.add(bairros.get(3));
        esperado.add(bairros.get(4));
        Collection<Bairro> observado = roteiro.bairrosPercoridos();
        assertEquals(esperado, observado);
    }

    @Test
    void testaToString() {
        assertEquals("Roteiro [bairroDestino=Bairro [area=Area [pInfDir=Ponto [x=60, y=10], pSupEsq=Ponto [x=40, y=20]], nome=Boa Vista], bairroOrigem=Bairro [area=Area [pInfDir=Ponto [x=50, y=30], pSupEsq=Ponto [x=30, y=40]], nome=Independencia]]", roteiro.toString());
    }

    @Test
    public void testaGetBairroOrigem(){
        assertEquals(bairros.get(1), roteiro.getBairroOrigem());
    }
    
    @Test
    public void testaGetBairroDestino(){
        assertEquals(bairros.get(4), roteiro.getBairroDestino());
    }

    @Test
    public void testaEqualsVerdadeiro(){
        assertEquals(true, roteiro.equals(roteiro));
    }

    @Test
    public void testaEqualsFalso(){
        assertEquals(false, roteiro.equals("roteiro2"));
    }
}