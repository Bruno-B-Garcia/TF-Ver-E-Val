package com.csvop.interfaces.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csvop.casosDeUso.Repositorios.RepositorioPassageiros;
import com.csvop.entidades.Passageiro;

public class RepositorioPassageirosImplMem implements RepositorioPassageiros {
    private Map<String, Passageiro> passageiros;

    public RepositorioPassageirosImplMem() {
        passageiros = new HashMap<>();

        passageiros.put("123456789", Passageiro.novoPassageiro("123456789", "Ze"));
        passageiros.put("123456798", Passageiro.novoPassageiro("123456798", "Prima do Ze"));
        passageiros.put("123457689", Passageiro.novoPassageiro("123457689", "Irmao do Ze"));
        passageiros.put("123546789", Passageiro.novoPassageiro("123546789", "Amiga do Ze"));
    }

    @Override
    public Passageiro recuperaPorCPF(String cpf) {
        return passageiros.get(cpf);
    }

    @Override
    public void atualizaPassageiro(Passageiro passageiro) {
        passageiros.put(passageiro.getCPF(), passageiro);
    }

    @Override
    public List<Passageiro> listaPassageiros() {
        List<Passageiro> lst = new ArrayList<>(passageiros.values());
        return lst;
    }
}