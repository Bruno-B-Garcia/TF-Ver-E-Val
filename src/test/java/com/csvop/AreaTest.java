package com.csvop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.csvop.entidades.geometria.Area;
import com.csvop.entidades.geometria.Ponto;
import com.csvop.entidades.geometria.Reta;
import com.csvop.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AreaTest {
    private Area area;

    @BeforeEach
    public void setup(){
        area = new Area(new Ponto(20,60),new Ponto(70,20));
    }

    @Test
    public void testaPontoCentral(){
        Ponto p = area.pontoCentral();
        assertEquals(45,p.getX());
        assertEquals(40,p.getY());
    }

    @ParameterizedTest
    @CsvSource({"25,50,45,50,TODA_DENTRO",
                "25, 15,45, 15,TODA_FORA",
                "18, 18, 82, 82, INTERSECTA",})
    public void testaClassifica(int x1,int y1,int x2,int y2,String classificacao){
        Reta reta = new Reta(new Ponto(x1,y1), new Ponto(x2,y2));
        SituacaoReta sitEsp = switch(classificacao){
            case "TODA_DENTRO" -> SituacaoReta.TODA_DENTRO;
            case "TODA_FORA" -> SituacaoReta.TODA_FORA;
            case "INTERSECTA" -> SituacaoReta.INTERSECTA;
            default -> SituacaoReta.TODA_DENTRO;
        };
        SituacaoReta sitObs = area.classifica(reta);
        assertEquals(sitEsp, sitObs);
    }
}