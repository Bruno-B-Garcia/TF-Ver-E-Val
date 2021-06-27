package com.csvop.entidades.geometria;

public class Area {
    private Ponto pSupEsq, pInfDir;

    public Area(Ponto pSupEsq, Ponto pInfDir) {
        if ((pSupEsq.getX() >= pInfDir.getX()) || (pSupEsq.getY() <= pInfDir.getY())) {
            throw new IllegalArgumentException("O retangulo deve ser definido pela diagonal principal");
        } else {
            this.pSupEsq = pSupEsq;
            this.pInfDir = pInfDir;
        }
    }

    public Ponto getPSupEsq() {
        return pSupEsq;
    }

    public Ponto getPInfDir() {
        return pInfDir;
    }

    public Ponto pontoCentral(){
        int x = getPSupEsq().getX()+(Math.abs(getPInfDir().getX() - getPSupEsq().getX())/2);
        int y = getPInfDir().getY()+(Math.abs(getPInfDir().getY() - getPSupEsq().getY())/2);
        return new Ponto(x,y);
    }

    public byte codificaPonto(Ponto p) {
        byte cod = 0;
        if (p.getY() > getPSupEsq().getY()) { // Ponto acima da area
            cod = (byte) 1;
        } else if (p.getY() < getPInfDir().getY()) { // Ponto abaixo da area
            cod = (byte) 2;
        }

        if (p.getX() > getPInfDir().getX()) { // Ponto a direita da area
            cod += (byte) 4;
        } else if (p.getX() < getPSupEsq().getX()) { // Ponto a esquerda da area
            cod += (byte) 8;
        }
        return cod;
    }

    public SituacaoReta classifica(Reta reta) {
        byte cod1 = codificaPonto(reta.getP1());
        byte cod2 = codificaPonto(reta.getP2());

        if ((cod1 | cod2) == 0) {
            return SituacaoReta.TODA_DENTRO;
        } else {
            for (byte val = 1; val <= 8; val *= 2) {
                if ((cod1 & val) == val && (cod2 & val) == val) {
                    return SituacaoReta.TODA_FORA;
                }
            }
            return SituacaoReta.INTERSECTA;
        }
    }

    @Override
    public String toString() {
        return "Area [pInfDir=" + pInfDir + ", pSupEsq=" + pSupEsq + "]";
    }

    @Override
    public boolean equals(Object outro){
        if (outro instanceof Area){
            Area outra = (Area)outro;
            return ((pSupEsq.equals(outra.pSupEsq)) && 
                    (pInfDir.equals(outra.pInfDir)));
        }else{
            return false;
        }
    }
}