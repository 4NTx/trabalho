package com.artur.controller;

import com.artur.model.Endereco;
import com.artur.model.Imovel;
import com.artur.service.Imobiliaria;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class AlterarImovelController extends BaseController {

    private Imobiliaria imobiliaria;
    private ObservableList<Imovel> listaImoveis;

    @FXML
    private TextField codigoField;
    @FXML
    private TextField areaConstruidaField;
    @FXML
    private TextField areaTotalField;
    @FXML
    private TextField numeroQuartosField;
    @FXML
    private ComboBox<String> tipoComboBox;
    @FXML
    private TextField precoField;
    @FXML
    private TextField cidadeField;
    @FXML
    private TextField bairroField;

    public AlterarImovelController(Imobiliaria imobiliaria, ObservableList<Imovel> listaImoveis) {
        this.imobiliaria = imobiliaria;
        this.listaImoveis = listaImoveis;
    }

    @FXML
    public void handleAlterarImovel() {
        if (listaImoveis.isEmpty()) {
            mostrarErro("Nenhum Imóvel Encontrado", "Não há registros para alterar.");
            return;
        }

        Dialog<Imovel> dialog = new Dialog<>();
        dialog.setTitle("Alterar Imóvel");
        dialog.setHeaderText("Digite o código do imóvel a ser alterado e os novos detalhes");

        ButtonType alterarButtonType = new ButtonType("Alterar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(alterarButtonType, ButtonType.CANCEL);

        GridPane grid = createFormGridPane();

        dialog.getDialogPane().setContent(grid);

        Button alterarButton = (Button) dialog.getDialogPane().lookupButton(alterarButtonType);
        alterarButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            try {
                int codigo = parseInt(codigoField.getText(), "Código");

                if (tipoComboBox.getValue() == null) {
                    mostrarErro("Tipo não selecionado", "Por favor, selecione um tipo para o imóvel.");
                    event.consume();
                    return;
                }

                float areaConstruida = parseFloat(areaConstruidaField.getText(), "Área Construída");
                float areaTotal = parseFloat(areaTotalField.getText(), "Área Total");
                int numeroQuartos = parseInt(numeroQuartosField.getText(), "Número de Quartos");
                int tipo = tipoComboBox.getValue().equals("Casa (0)") ? 0 : 1;
                float preco = parseFloat(precoField.getText(), "Preço");
                String cidade = cidadeField.getText();
                String bairro = bairroField.getText();

                Endereco endereco = new Endereco(cidade, bairro);
                Imovel imovel = new Imovel(codigo, areaConstruida, areaTotal, numeroQuartos, tipo, preco, endereco);
                imobiliaria.alterarImovel(codigo, imovel);
                listaImoveis.setAll(imobiliaria.listarImoveis());
            } catch (NumberFormatException e) {
                mostrarErroDeFormato(e);
                event.consume();
            }
        });

        dialog.showAndWait();
    }

    private GridPane createFormGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        codigoField = new TextField();
        codigoField.setPromptText("Código");
        areaConstruidaField = new TextField();
        areaConstruidaField.setPromptText("Área Construída (cm²)");
        areaTotalField = new TextField();
        areaTotalField.setPromptText("Área Total (cm²)");
        numeroQuartosField = new TextField();
        numeroQuartosField.setPromptText("Número de Quartos");
        tipoComboBox = new ComboBox<>();
        tipoComboBox.setPromptText("Tipo");
        tipoComboBox.getItems().addAll("Casa (0)", "Apartamento (1)");
        precoField = new TextField();
        precoField.setPromptText("Preço (R$)");
        cidadeField = new TextField();
        cidadeField.setPromptText("Cidade");
        bairroField = new TextField();
        bairroField.setPromptText("Bairro");

        grid.add(new Label("Código:"), 0, 0);
        grid.add(codigoField, 1, 0);
        grid.add(new Label("Área Construída:"), 0, 1);
        grid.add(areaConstruidaField, 1, 1);
        grid.add(new Label("Área Total:"), 0, 2);
        grid.add(areaTotalField, 1, 2);
        grid.add(new Label("Número de Quartos:"), 0, 3);
        grid.add(numeroQuartosField, 1, 3);
        grid.add(new Label("Tipo:"), 0, 4);
        grid.add(tipoComboBox, 1, 4);
        grid.add(new Label("Preço:"), 0, 5);
        grid.add(precoField, 1, 5);
        grid.add(new Label("Cidade:"), 0, 6);
        grid.add(cidadeField, 1, 6);
        grid.add(new Label("Bairro:"), 0, 7);
        grid.add(bairroField, 1, 7);

        GridPane.setHgrow(codigoField, Priority.ALWAYS);
        GridPane.setHgrow(areaConstruidaField, Priority.ALWAYS);
        GridPane.setHgrow(areaTotalField, Priority.ALWAYS);
        GridPane.setHgrow(numeroQuartosField, Priority.ALWAYS);
        GridPane.setHgrow(tipoComboBox, Priority.ALWAYS);
        GridPane.setHgrow(precoField, Priority.ALWAYS);
        GridPane.setHgrow(cidadeField, Priority.ALWAYS);
        GridPane.setHgrow(bairroField, Priority.ALWAYS);

        return grid;
    }
}
