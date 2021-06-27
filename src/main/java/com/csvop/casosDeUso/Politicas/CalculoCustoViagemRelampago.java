package com.csvop.casosDeUso.Politicas;

public class CalculoCustoViagemRelampago extends CalculoCustoViagemBasico {
    @Override
    public double descontoPontuacao() {
        double custobasico = calculoCustoBasico();
        if (getPassageiro().getPontuacaoMedia() > 5.0 &&
            getPassageiro().getQtdadeAvaliacoes() > 30){
            return custobasico * 0.05;
        }else{
            return 0.0;
        }
    }

    @Override
    public double descontoPromocaoSazonal() {
        int qtdadeBairros = getRoteiro().bairrosPercoridos().size();
        double cb = calculoCustoBasico();
        if (qtdadeBairros > 3){
            return cb*0.05;
        }else{
            return 0.0;
        }
    }  
}