package com.csvop.interfaces.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csvop.casosDeUso.Repositorios.RepositorioBairros;
import com.csvop.entidades.Bairro;
import com.csvop.entidades.geometria.Ponto;

public class RepositorioBairrosImplMem implements RepositorioBairros {
    private Map<String, Bairro> bairros;

    public RepositorioBairrosImplMem() {
        bairros = new HashMap<>();

        bairros.put("Petropolis", Bairro.novoBairroQuadrado("Petropolis", new Ponto(0, 12), 4, 10));
        bairros.put("Ipiranga", Bairro.novoBairroRetangular("Ipiranga", new Ponto(4, 10), 4, 2, 6));
        bairros.put("Solidao", Bairro.novoBairroRetangular("Solidao", new Ponto(0, 8), 2, 4, 5));
        bairros.put("Bom Fim", Bairro.novoBairroRetangular("Bom Fim", new Ponto(2, 8), 6, 4, 10));
        bairros.put("Vila Nova", Bairro.novoBairroRetangular("Vila Nova", new Ponto(8, 10), 5, 6, 15));
        bairros.put("Alegria", Bairro.novoBairroRetangular("Alegria", new Ponto(0, 4), 5, 4, 10));
        bairros.put("Boa Vista", Bairro.novoBairroRetangular("Boa Vista", new Ponto(5, 4), 5, 4, 15));
        bairros.put("Gavea", Bairro.novoBairroRetangular("Gavea", new Ponto(10, 4), 2, 3, 20));
    }

    @Override
    public Bairro recuperaPorNome(String nomeBairro) {
        Bairro bairro = bairros.get(nomeBairro);
        if (bairro == null) {
            throw new IllegalArgumentException("Bairro inexistente: " + nomeBairro);
        }
        return bairro;
    }

    @Override
    public List<Bairro> recuperaListaBairros() {
        return new ArrayList<Bairro>(bairros.values());
    }
}