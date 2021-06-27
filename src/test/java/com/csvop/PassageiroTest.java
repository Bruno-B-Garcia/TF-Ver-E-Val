package com.csvop;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.csvop.entidades.Bairro;
import com.csvop.entidades.Passageiro;
import com.csvop.entidades.geometria.Area;
import com.csvop.entidades.geometria.Ponto;
import com.csvop.entidades.geometria.Reta;
import com.csvop.entidades.geometria.SituacaoReta;

public class PassageiroTest {
    private Passageiro passageiro;

    @BeforeEach
    public void setup(){
        passageiro = Passageiro.novoPassageiro("2443242523", "Jorge");
        passageiro = Passageiro.passageiroExistente("83718317312", "Bruno", 8, 1);
    }

    @Test
    public void testaInfoPontuacaoValida(){
        passageiro.infoPontuacao(10);
        assertEquals(2, passageiro.getQtdadeAvaliacoes());
        assertEquals(18, passageiro.getPontuacaoAcumulada());
    }

    @Test
    public void testaInfoPontuacaoInvalida(){
        String erro = "";
        try {
            passageiro.infoPontuacao(-10);
        } catch (IllegalArgumentException e) {
            erro = "erro";
        }
        assertEquals(1, passageiro.getQtdadeAvaliacoes());
        assertEquals(8, passageiro.getPontuacaoAcumulada());
        assertEquals("erro", erro);
    }

    @Test
    public void testaToString(){
        assertEquals("Passageiro [cpf=83718317312, nome=Bruno, pontuacaoAcumulada=8, qtdadeAvaliacoes=1]", passageiro.toString());
    } 

    @Test
    public void testaCpf(){
        assertEquals("83718317312", passageiro.getCPF());
    }

    @Test
    public void testaNome(){
        assertEquals("Bruno", passageiro.getNome());
    }

    @Test
    public void testaPontuacaoMedia(){
        assertEquals(8, passageiro.getPontuacaoMedia());
    } 

    @Test
    public void testaPontuacaoAcumulada(){
        assertEquals(8, passageiro.getPontuacaoAcumulada());
    }
    
    @Test
    public void testaQtdadeAvaliacoes(){
        assertEquals(1, passageiro.getQtdadeAvaliacoes());
    } 
}
