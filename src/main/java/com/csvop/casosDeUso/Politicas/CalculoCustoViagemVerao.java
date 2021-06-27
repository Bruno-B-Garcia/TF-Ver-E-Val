package com.csvop.casosDeUso.Politicas;

public class CalculoCustoViagemVerao extends CalculoCustoViagemBasico {
    @Override
    public double descontoPontuacao() {
        double custobasico = calculoCustoBasico();
        if (getPassageiro().getPontuacaoMedia() > 9.0){
            return custobasico * 0.9; 
        }else{
            return 0.0;
        }
    }

    @Override
    public double descontoPromocaoSazonal() {
        int qtdadeBairros = getRoteiro().bairrosPercoridos().size();
        double cb = calculoCustoBasico();
        if (qtdadeBairros > 2){
            return cb*0.1;
        }else{
            return 0.0;
        }
    }  
}