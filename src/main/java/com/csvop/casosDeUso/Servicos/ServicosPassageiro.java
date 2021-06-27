package com.csvop.casosDeUso.Servicos;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.csvop.casosDeUso.Politicas.CustoViagem;
import com.csvop.casosDeUso.Politicas.CalculoCustoViagem;
import com.csvop.casosDeUso.Repositorios.RepositorioBairros;
import com.csvop.casosDeUso.Repositorios.RepositorioPassageiros;
import com.csvop.entidades.Bairro;
import com.csvop.entidades.Passageiro;
import com.csvop.entidades.Roteiro;
import com.csvop.entidades.Viagem;

public class ServicosPassageiro {
    private RepositorioBairros repBairros;
    private RepositorioPassageiros repPassageiros;
    private CustoViagem custoViagem;

    public ServicosPassageiro(RepositorioBairros repBairros, RepositorioPassageiros repPassageiros,
            CalculoCustoViagem ccv) {
        this.repBairros = repBairros;
        this.repPassageiros = repPassageiros;
        this.custoViagem = new CustoViagem(ccv);
    }

    public List<Object> getListaBairros(){
        return repBairros.recuperaListaBairros()
                .stream()
                .map(b->b.getNome())
                .collect(Collectors.toList());
    }

    public List<Object> getPassageirosCadastrados(){
        return repPassageiros.listaPassageiros()
                .stream()
                .map(p->p.getNome())
                .collect(Collectors.toList());
    }

    public Roteiro criaRoteiro(String bairroOrigem,String bairroDestino){
        Collection<Bairro> todosBairros = repBairros.recuperaListaBairros();
        Bairro bOrigem = repBairros.recuperaPorNome(bairroOrigem);
        Bairro bDestino = repBairros.recuperaPorNome(bairroDestino);
        return new Roteiro(bOrigem,bDestino,todosBairros);
    }

    public Viagem criaViagem(int id,Roteiro roteiro,String cpfPassageiro){
        LocalDateTime data = LocalDateTime.now();
        Passageiro passageiro = repPassageiros.recuperaPorCPF(cpfPassageiro);
        double valorCobrado = custoViagem.custoViagem(roteiro, passageiro);
        return new Viagem(id,data,roteiro,passageiro,valorCobrado);
    }
}