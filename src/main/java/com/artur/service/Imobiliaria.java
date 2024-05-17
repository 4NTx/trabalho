package com.artur.service;

import com.artur.model.Imovel;

import java.util.ArrayList;
import java.util.List;

public class Imobiliaria {
    private List<Imovel> listaDeImoveis;

    public Imobiliaria() {
        this.listaDeImoveis = new ArrayList<>();
    }

    public void adicionarImovel(Imovel imovel) {
        listaDeImoveis.add(imovel);
    }

    public void removerImovel(int codigo) {
        listaDeImoveis.removeIf(imovel -> imovel.getCodigo() == codigo);
    }

    public void alterarImovel(int codigo, Imovel imovelAtualizado) {
        for (int i = 0; i < listaDeImoveis.size(); i++) {
            if (listaDeImoveis.get(i).getCodigo() == codigo) {
                listaDeImoveis.set(i, imovelAtualizado);
                return;
            }
        }
    }

    public List<Imovel> listarImoveis() {
        return listaDeImoveis;
    }

    public List<Imovel> filtrarPorTipo(int tipo) {
        List<Imovel> filtrados = new ArrayList<>();
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getTipo() == tipo) {
                filtrados.add(imovel);
            }
        }
        return filtrados;
    }

    public List<Imovel> filtrarPorCidade(String cidade) {
        List<Imovel> filtrados = new ArrayList<>();
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getLocalizacao().getCidade().equalsIgnoreCase(cidade)) {
                filtrados.add(imovel);
            }
        }
        return filtrados;
    }

    public List<Imovel> filtrarPorBairro(String cidade, String bairro) {
        List<Imovel> filtrados = new ArrayList<>();
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getLocalizacao().getCidade().equalsIgnoreCase(cidade) &&
                    imovel.getLocalizacao().getBairro().equalsIgnoreCase(bairro)) {
                filtrados.add(imovel);
            }
        }
        return filtrados;
    }

    public List<Imovel> filtrarPorPreco(float precoMin, float precoMax) {
        List<Imovel> filtrados = new ArrayList<>();
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getPreco() >= precoMin && imovel.getPreco() <= precoMax) {
                filtrados.add(imovel);
            }
        }
        return filtrados;
    }

    public List<Imovel> filtrarPorNumeroQuartos(int numeroMinQuartos) {
        List<Imovel> filtrados = new ArrayList<>();
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getNumeroQuartos() >= numeroMinQuartos) {
                filtrados.add(imovel);
            }
        }
        return filtrados;
    }

    public boolean imovelExiste(int codigo) {
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }
}
