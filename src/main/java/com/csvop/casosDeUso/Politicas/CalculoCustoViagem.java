package com.csvop.casosDeUso.Politicas;

import com.csvop.entidades.Passageiro;
import com.csvop.entidades.Roteiro;

public interface CalculoCustoViagem {
    void defineRoteiro(Roteiro roteiro);
    void definePassageiro(Passageiro passageiro);
	public Roteiro getRoteiro();
	public Passageiro getPassageiro();
    double calculoCustoBasico();
    double descontoPontuacao();
    double descontoPromocaoSazonal();
    double custoViagem();
}