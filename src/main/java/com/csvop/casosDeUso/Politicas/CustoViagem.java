package com.csvop.casosDeUso.Politicas;

import com.csvop.entidades.Passageiro;
import com.csvop.entidades.Roteiro;

public class CustoViagem {
    private CalculoCustoViagem ccv;
    
    public CustoViagem(CalculoCustoViagem ccv){
        this.ccv = ccv;
    }            

    public double custoViagem(Roteiro roteiro,Passageiro passageiro){
        ccv.defineRoteiro(roteiro);
        ccv.definePassageiro(passageiro);
        return ccv.custoViagem();
    }
}