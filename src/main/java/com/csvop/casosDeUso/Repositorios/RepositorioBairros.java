package com.csvop.casosDeUso.Repositorios;

import java.util.List;

import com.csvop.entidades.Bairro;

public interface RepositorioBairros {
    Bairro recuperaPorNome(String nomeBairro);
    List<Bairro> recuperaListaBairros();
}