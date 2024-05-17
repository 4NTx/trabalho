package com.artur.controller;

import com.artur.model.Imovel;
import com.artur.service.Imobiliaria;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class ExcluirImovelController extends BaseController {

    private Imobiliaria imobiliaria;
    private ObservableList<Imovel> listaImoveis;

    public ExcluirImovelController(Imobiliaria imobiliaria, ObservableList<Imovel> listaImoveis) {
        this.imobiliaria = imobiliaria;
        this.listaImoveis = listaImoveis;
    }

    @FXML
    public void handleExcluirImovel() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Excluir Imóvel");
        dialog.setHeaderText("Excluir Imóvel");
        dialog.setContentText("Digite o código do imóvel a ser excluído:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(codigo -> {
            try {
                int codigoInt = parseInt(codigo, "Código");
                imobiliaria.removerImovel(codigoInt);
                listaImoveis.setAll(imobiliaria.listarImoveis());
            } catch (NumberFormatException e) {
                mostrarErroDeFormato(e);
            }
        });
    }
}
