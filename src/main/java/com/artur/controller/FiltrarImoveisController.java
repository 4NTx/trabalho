package com.artur.controller;

import com.artur.model.Imovel;
import com.artur.service.Imobiliaria;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.List;
import java.util.Optional;

public class FiltrarImoveisController extends BaseController {

    private Imobiliaria imobiliaria;
    private ObservableList<Imovel> listaImoveis;

    @FXML
    private TextField precoMinField;
    @FXML
    private TextField precoMaxField;
    @FXML
    private TextField numeroQuartosField;
    @FXML
    private ComboBox<String> tipoComboBox;
    @FXML
    private TextField cidadeField;
    @FXML
    private TextField bairroField;

    public FiltrarImoveisController(Imobiliaria imobiliaria, ObservableList<Imovel> listaImoveis) {
        this.imobiliaria = imobiliaria;
        this.listaImoveis = listaImoveis;
    }

    @FXML
    public void handleFiltrarImoveis() {
        if (listaImoveis.isEmpty()) {
            mostrarErro("Nenhum Imóvel Encontrado", "Não há registros para filtrar.");
            return;
        }

        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Filtrar Imóveis");
        dialog.setHeaderText("Escolha o filtro desejado");

        ButtonType filtrarButtonType = new ButtonType("Filtrar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(filtrarButtonType, ButtonType.CANCEL);

        GridPane grid = createFormGridPane();

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == filtrarButtonType) {
                return new String[] {
                        tipoComboBox.getValue(),
                        cidadeField.getText(),
                        bairroField.getText(),
                        precoMinField.getText().replace("R$", "").trim(),
                        precoMaxField.getText().replace("R$", "").trim(),
                        numeroQuartosField.getText()
                };
            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();
        result.ifPresent(filtros -> {
            List<Imovel> filtrados = imobiliaria.listarImoveis();

            if (filtros[0] != null && !filtros[0].isEmpty() && !filtros[0].equals("Ambos")) {
                int tipo = filtros[0].equals("Casa (0)") ? 0 : 1;
                filtrados = imobiliaria.filtrarPorTipo(tipo);
            }

            if (!filtros[1].isEmpty()) {
                filtrados = imobiliaria.filtrarPorCidade(filtros[1]);
            }

            if (!filtros[2].isEmpty()) {
                filtrados = imobiliaria.filtrarPorBairro(filtros[1], filtros[2]);
            }

            if (!filtros[3].isEmpty() && !filtros[4].isEmpty()) {
                try {
                    float precoMin = parseFloat(filtros[3], "Preço Mínimo");
                    float precoMax = parseFloat(filtros[4], "Preço Máximo");
                    filtrados = imobiliaria.filtrarPorPreco(precoMin, precoMax);
                } catch (NumberFormatException e) {
                    mostrarErroDeFormato(e);
                    return;
                }
            }

            if (!filtros[5].isEmpty()) {
                try {
                    int numeroQuartos = parseInt(filtros[5], "Número de Quartos");
                    filtrados = imobiliaria.filtrarPorNumeroQuartos(numeroQuartos);
                } catch (NumberFormatException e) {
                    mostrarErroDeFormato(e);
                    return;
                }
            }

            listaImoveis.setAll(filtrados);
        });
    }

    private GridPane createFormGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        tipoComboBox = new ComboBox<>();
        tipoComboBox.setPromptText("Tipo");
        tipoComboBox.getItems().addAll("Ambos", "Casa (0)", "Apartamento (1)");
        cidadeField = new TextField();
        cidadeField.setPromptText("Cidade");
        bairroField = new TextField();
        bairroField.setPromptText("Bairro");
        precoMinField = new TextField();
        precoMinField.setPromptText("Preço Mínimo (R$)");
        precoMaxField = new TextField();
        precoMaxField.setPromptText("Preço Máximo (R$)");
        numeroQuartosField = new TextField();
        numeroQuartosField.setPromptText("Número Mínimo de Quartos");

        grid.add(new Label("Tipo:"), 0, 0);
        grid.add(tipoComboBox, 1, 0);
        grid.add(new Label("Cidade:"), 0, 1);
        grid.add(cidadeField, 1, 1);
        grid.add(new Label("Bairro:"), 0, 2);
        grid.add(bairroField, 1, 2);
        grid.add(new Label("Preço Mínimo:"), 0, 3);
        grid.add(precoMinField, 1, 3);
        grid.add(new Label("Preço Máximo:"), 0, 4);
        grid.add(precoMaxField, 1, 4);
        grid.add(new Label("Número de Quartos:"), 0, 5);
        grid.add(numeroQuartosField, 1, 5);

        GridPane.setHgrow(tipoComboBox, Priority.ALWAYS);
        GridPane.setHgrow(cidadeField, Priority.ALWAYS);
        GridPane.setHgrow(bairroField, Priority.ALWAYS);
        GridPane.setHgrow(precoMinField, Priority.ALWAYS);
        GridPane.setHgrow(precoMaxField, Priority.ALWAYS);
        GridPane.setHgrow(numeroQuartosField, Priority.ALWAYS);

        return grid;
    }
}
