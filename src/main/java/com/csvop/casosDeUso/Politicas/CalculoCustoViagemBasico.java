package com.csvop.casosDeUso.Politicas;

import com.csvop.entidades.Passageiro;
import com.csvop.entidades.Roteiro;

public class CalculoCustoViagemBasico implements CalculoCustoViagem {
    private Roteiro roteiro;
    private Passageiro passageiro;

    @Override
    public void defineRoteiro(Roteiro roteiro) {
        this.roteiro = roteiro;
    }

    @Override
    public void definePassageiro(Passageiro passageiro){
        this.passageiro = passageiro;
    }

	public Roteiro getRoteiro() {
		return roteiro;
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}
    
    @Override
    public double calculoCustoBasico() {
        return roteiro.bairrosPercoridos()
               .stream()
               .mapToDouble(b->b.getCustoTransporte())
               .sum();
    }

    @Override
    public double descontoPontuacao() {
        return 0.0;
    }

    @Override
    public double descontoPromocaoSazonal() {
        return 0.0;
    }

    @Override
    public double custoViagem() {
        return calculoCustoBasico() - 
                descontoPontuacao() -
                descontoPromocaoSazonal();
    }
}