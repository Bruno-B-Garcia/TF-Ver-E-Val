package com.csvop.casosDeUso.Repositorios;

import java.util.List;

import com.csvop.entidades.Passageiro;

public interface RepositorioPassageiros {
    List<Passageiro> listaPassageiros();
    Passageiro recuperaPorCPF(String cpf);
    void atualizaPassageiro(Passageiro passageiro);
}