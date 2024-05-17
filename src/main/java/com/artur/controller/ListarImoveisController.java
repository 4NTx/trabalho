package com.artur.controller;

import com.artur.model.Imovel;
import com.artur.service.Imobiliaria;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class ListarImoveisController {

    private Imobiliaria imobiliaria;
    private ObservableList<Imovel> listaImoveis;

    public ListarImoveisController(Imobiliaria imobiliaria, ObservableList<Imovel> listaImoveis) {
        this.imobiliaria = imobiliaria;
        this.listaImoveis = listaImoveis;
    }

    @FXML
    public void handleListarImoveis() {
        listaImoveis.setAll(imobiliaria.listarImoveis());
    }
}
