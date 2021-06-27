package com.csvop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.csvop.casosDeUso.Politicas.CalculoCustoViagem;
import com.csvop.casosDeUso.Politicas.CustoViagem;
import com.csvop.casosDeUso.Repositorios.RepositorioBairros;
import com.csvop.casosDeUso.Repositorios.RepositorioPassageiros;
import com.csvop.entidades.Bairro;
import com.csvop.entidades.Passageiro;
import com.csvop.entidades.Roteiro;
import com.csvop.entidades.geometria.Area;
import com.csvop.entidades.geometria.Ponto;
import com.csvop.casosDeUso.Servicos.ServicosPassageiro;

import org.junit.jupiter.api.Test;

public class ServicosPassageiroTest {
    static ServicosPassageiro servicoPassageiro; 

    @Test
    public void getListaBairrosTest() {
        List<Object> lista = new ArrayList<>();
        lista.add(new Bairro("Teste1", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));
        lista.add(new Bairro("Teste2", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));
        lista.add(new Bairro("Teste3", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));
        lista.add(new Bairro("Teste4", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));

        RepositorioBairros rb = mock(RepositorioBairros.class);

        servicoPassageiro = new ServicosPassageiro(rb, null, null);

        when(servicoPassageiro.getListaBairros()).thenReturn(lista);

        assertEquals(lista.stream().map(b->((Bairro) b).getNome()).collect(Collectors.toList()), servicoPassageiro.getListaBairros());
    }

    @Test
    public void getListaPassageirosCadastradosTest() {
        List<Object> lista = new ArrayList<>();
        lista.add(Passageiro.novoPassageiro("123456", "Teste-1"));
        lista.add(Passageiro.novoPassageiro("123456", "Teste-1"));
        lista.add(Passageiro.novoPassageiro("123456", "Teste-1"));
        lista.add(Passageiro.passageiroExistente("123456", "Teste-1", 2, 5));

        RepositorioPassageiros rp = mock(RepositorioPassageiros.class);

        servicoPassageiro = new ServicosPassageiro(null, rp, null);

        when(servicoPassageiro.getPassageirosCadastrados()).thenReturn(lista);

        assertEquals(lista.stream().map(b->((Passageiro) b).getNome()).collect(Collectors.toList()), servicoPassageiro.getPassageirosCadastrados());
    }
    
    @Test
    public void testaCriaRoteiro() {

        List<Object> lista = new ArrayList<>();
        lista.add(new Bairro("Teste1", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));
        lista.add(new Bairro("Teste2", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));
        lista.add(new Bairro("Teste3", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));
        lista.add(new Bairro("Teste4", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));

        RepositorioBairros rb = mock(RepositorioBairros.class);

        servicoPassageiro = new ServicosPassageiro(rb, null, null);
        when(servicoPassageiro.getListaBairros()).thenReturn(lista);
        when(rb.recuperaPorNome(lista.get(0).toString())).thenReturn(new Bairro("Teste1", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));
        when(rb.recuperaPorNome(lista.get(3).toString())).thenReturn(new Bairro("Teste2", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));

        assertEquals("Roteiro [bairroDestino=Bairro [area=Area [pInfDir=Ponto [x=60, y=35], pSupEsq=Ponto [x=20, y=40]], nome=Teste2], bairroOrigem=Bairro [area=Area [pInfDir=Ponto [x=60, y=35], pSupEsq=Ponto [x=20, y=40]], nome=Teste1]]", servicoPassageiro.criaRoteiro(lista.get(0).toString(), lista.get(3).toString()).toString());
    }

    @Test
    public void testaCriaViagem() {
        Collection<Bairro> listaBairros = new ArrayList<>();
        listaBairros.add(new Bairro("Teste1", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));
        listaBairros.add(new Bairro("Teste2", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0));

        Roteiro roteiro = new Roteiro(new Bairro("Teste1", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0), new Bairro("Teste2", new Area(new Ponto(20, 40), new Ponto(60, 35)), 50.0), listaBairros);

        Passageiro passageiro = Passageiro.novoPassageiro("123.456.789-12", "Teste-1");

        RepositorioPassageiros rp = mock(RepositorioPassageiros.class);
        CalculoCustoViagem ccv = mock(CalculoCustoViagem.class);
        CustoViagem cv = mock(CustoViagem.class);

        servicoPassageiro = new ServicosPassageiro(null, rp, ccv);

        when(rp.recuperaPorCPF("123.456.789-12")).thenReturn(passageiro);
    
        when(cv.custoViagem(roteiro, passageiro)).thenReturn(0.0);

        assertEquals(1, servicoPassageiro.criaViagem(1, roteiro, "123.456.789-12").getId());
        assertEquals(roteiro, servicoPassageiro.criaViagem(1, roteiro, "123.456.789-12").getRoteiro());
        assertEquals(0.0, servicoPassageiro.criaViagem(1, roteiro, "123.456.789-12").getValorCobrado());
        assertEquals(passageiro.toString(), servicoPassageiro.criaViagem(1, roteiro, "123.456.789-12").getPassageiro().toString());
    }
}
