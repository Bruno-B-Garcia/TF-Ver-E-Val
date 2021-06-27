package com.csvop.entidades;

import java.util.Collection;
import java.util.LinkedList;

import com.csvop.entidades.geometria.Ponto;
import com.csvop.entidades.geometria.Reta;
import com.csvop.entidades.geometria.SituacaoReta;

public class Roteiro {
    private Bairro bairroOrigem;
    private Bairro bairroDestino;
    private Reta rota;
    private Collection<Bairro> bairrosPercorridos;

    private void determinaBairrosPercorridos(Reta rota,Collection<Bairro> todosBairros){
        for(Bairro bairro:todosBairros){
            SituacaoReta sr = bairro.getClassificacao(rota);
            if (sr != SituacaoReta.TODA_FORA){
                bairrosPercorridos.add(bairro);
            }
        }
    }

    public Roteiro(Bairro bairroOrigem, Bairro bairroDestino,Collection<Bairro> todosBairros) {
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
        bairrosPercorridos = new LinkedList<>(); 
        Ponto pOrig = bairroOrigem.getPontoCentral();
        Ponto pDest = bairroDestino.getPontoCentral();
        rota =  new Reta(pOrig,pDest);
        determinaBairrosPercorridos(rota,todosBairros);
    }

    public Reta getRota(){
        return rota;
    }
    
    public Bairro getBairroOrigem() {
        return bairroOrigem;
    }

    public Bairro getBairroDestino() {
        return bairroDestino;
    }

    public Collection<Bairro> bairrosPercoridos(){
        return bairrosPercorridos;
    }
    
    @Override
    public String toString() {
        return "Roteiro [bairroDestino=" + bairroDestino + ", bairroOrigem=" + bairroOrigem + "]";
    }

    @Override
    public boolean equals(Object outro){
        if (outro instanceof Roteiro){
            Roteiro outroRoteiro = (Roteiro)outro;
            return this.getBairroOrigem().equals(outroRoteiro.getBairroOrigem()) && this.getBairroDestino().equals(outroRoteiro.getBairroDestino());
        }else{
            return false;
        }
    }
}