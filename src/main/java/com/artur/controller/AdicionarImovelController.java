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
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class AdicionarImovelController extends BaseController {

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

    public AdicionarImovelController(Imobiliaria imobiliaria, ObservableList<Imovel> listaImoveis) {
        this.imobiliaria = imobiliaria;
        this.listaImoveis = listaImoveis;
    }

    @FXML
    public void handleAdicionarImovel() {
        Dialog<Imovel> dialog = new Dialog<>();
        dialog.setTitle("Adicionar Imóvel");
        dialog.setHeaderText("Informe os detalhes do novo imóvel");

        ButtonType adicionarButtonType = new ButtonType("Adicionar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(adicionarButtonType, ButtonType.CANCEL);

        GridPane grid = createFormGridPane();

        dialog.getDialogPane().setContent(grid);

        Button adicionarButton = (Button) dialog.getDialogPane().lookupButton(adicionarButtonType);
        adicionarButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            try {
                int codigo = parseInt(codigoField.getText(), "Código");
                if (imobiliaria.imovelExiste(codigo)) {
                    mostrarErro("Código já existente",
                            "O código informado já existe. Por favor, insira um código único.");
                    event.consume();
                    return;
                }

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
                imobiliaria.adicionarImovel(imovel);
                listaImoveis.setAll(imobiliaria.listarImoveis());
            } catch (NumberFormatException e) {
                mostrarErroDeFormato(e);
                event.consume();
            }
        });

        Button dadosAleatorios = new Button("Aleatorio");
        dadosAleatorios.setOnAction(event -> preencherCamposComDadosAleatorios());

        grid.add(dadosAleatorios, 1, 8);

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

    private void preencherCamposComDadosAleatorios() {
        Random dadosAleatorios = new Random();

        codigoField.setText(String.valueOf(dadosAleatorios.nextInt(10000)));
        areaConstruidaField.setText(String.format("%.2f", dadosAleatorios.nextFloat() * 1000));
        areaTotalField.setText(String.format("%.2f", dadosAleatorios.nextFloat() * 2000));
        numeroQuartosField.setText(String.valueOf(dadosAleatorios.nextInt(10) + 1));
        tipoComboBox.setValue(dadosAleatorios.nextBoolean() ? "Casa (0)" : "Apartamento (1)");
        precoField.setText(String.format("R$ %.2f", dadosAleatorios.nextFloat() * 1000000));
        cidadeField.setText(RandomStringUtils.randomAlphabetic(8));
        bairroField.setText(RandomStringUtils.randomAlphabetic(8));
    }
}
