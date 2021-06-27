package com.csvop.entidades.geometria;

public class Ponto {
    private int x;
    private int y;

    public Ponto(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public String toString() {
        return "Ponto [x=" + x + ", y=" + y + "]";
    }

    @Override
    public boolean equals(Object outro){
        if (outro instanceof Ponto){
            Ponto outroP = (Ponto)outro;
            return ((x - outroP.x)+(y-outroP.y)) == 0;
        }else{
            return false;
        }
    }
}