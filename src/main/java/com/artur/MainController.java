package com.artur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.List;
import java.util.Optional;

public class MainController {

    @FXML
    private TableView<Imovel> tabelaImoveis;

    @FXML
    private TableColumn<Imovel, Integer> colCodigo;

    @FXML
    private TableColumn<Imovel, Float> colAreaConstruida;

    @FXML
    private TableColumn<Imovel, Float> colAreaTotal;

    @FXML
    private TableColumn<Imovel, Integer> colNumeroQuartos;

    @FXML
    private TableColumn<Imovel, String> colTipo;

    @FXML
    private TableColumn<Imovel, Float> colPreco;

    @FXML
    private TableColumn<Imovel, String> colCidade;

    @FXML
    private TableColumn<Imovel, String> colBairro;

    private Imobiliaria imobiliaria = new Imobiliaria();
    private ObservableList<Imovel> listaImoveis;

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colAreaConstruida.setCellValueFactory(new PropertyValueFactory<>("areaConstruida"));
        colAreaTotal.setCellValueFactory(new PropertyValueFactory<>("areaTotal"));
        colNumeroQuartos.setCellValueFactory(new PropertyValueFactory<>("numeroQuartos"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));

        listaImoveis = FXCollections.observableArrayList(imobiliaria.listarImoveis());
        tabelaImoveis.setItems(listaImoveis);

        tabelaImoveis.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void handleAdicionarImovel() {
        Dialog<Imovel> dialog = new Dialog<>();
        dialog.setTitle("Adicionar Imóvel");
        dialog.setHeaderText("Informe os detalhes do novo imóvel");

        ButtonType adicionarButtonType = new ButtonType("Adicionar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(adicionarButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField codigoField = new TextField();
        codigoField.setPromptText("Código");
        TextField areaConstruidaField = new TextField();
        areaConstruidaField.setPromptText("Área Construída");
        TextField areaTotalField = new TextField();
        areaTotalField.setPromptText("Área Total");
        TextField numeroQuartosField = new TextField();
        numeroQuartosField.setPromptText("Número de Quartos");
        TextField tipoField = new TextField();
        tipoField.setPromptText("Tipo (0 - Casa, 1 - Apartamento)");
        TextField precoField = new TextField();
        precoField.setPromptText("Preço");
        TextField cidadeField = new TextField();
        cidadeField.setPromptText("Cidade");
        TextField bairroField = new TextField();
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
        grid.add(tipoField, 1, 4);
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
        GridPane.setHgrow(tipoField, Priority.ALWAYS);
        GridPane.setHgrow(precoField, Priority.ALWAYS);
        GridPane.setHgrow(cidadeField, Priority.ALWAYS);
        GridPane.setHgrow(bairroField, Priority.ALWAYS);

        dialog.getDialogPane().setContent(grid);

        Button adicionarButton = (Button) dialog.getDialogPane().lookupButton(adicionarButtonType);
        adicionarButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            try {
                int codigo = Integer.parseInt(codigoField.getText());
                float areaConstruida = Float.parseFloat(areaConstruidaField.getText());
                float areaTotal = Float.parseFloat(areaTotalField.getText());
                int numeroQuartos = Integer.parseInt(numeroQuartosField.getText());
                int tipo = Integer.parseInt(tipoField.getText());
                float preco = Float.parseFloat(precoField.getText());
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

        dialog.showAndWait();
    }

    @FXML
    private void handleListarImoveis() {
        listaImoveis.setAll(imobiliaria.listarImoveis());
    }

    @FXML
    private void handleFiltrarImoveis() {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Filtrar Imóveis");
        dialog.setHeaderText("Escolha o filtro desejado");

        ButtonType filtrarButtonType = new ButtonType("Filtrar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(filtrarButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField tipoField = new TextField();
        tipoField.setPromptText("Tipo (0 - Casa, 1 - Apartamento)");
        TextField cidadeField = new TextField();
        cidadeField.setPromptText("Cidade");
        TextField bairroField = new TextField();
        bairroField.setPromptText("Bairro");
        TextField precoMinField = new TextField();
        precoMinField.setPromptText("Preço Mínimo");
        TextField precoMaxField = new TextField();
        precoMaxField.setPromptText("Preço Máximo");
        TextField numeroQuartosField = new TextField();
        numeroQuartosField.setPromptText("Número Mínimo de Quartos");

        grid.add(new Label("Tipo:"), 0, 0);
        grid.add(tipoField, 1, 0);
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

        GridPane.setHgrow(tipoField, Priority.ALWAYS);
        GridPane.setHgrow(cidadeField, Priority.ALWAYS);
        GridPane.setHgrow(bairroField, Priority.ALWAYS);
        GridPane.setHgrow(precoMinField, Priority.ALWAYS);
        GridPane.setHgrow(precoMaxField, Priority.ALWAYS);
        GridPane.setHgrow(numeroQuartosField, Priority.ALWAYS);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == filtrarButtonType) {
                return new String[] {
                        tipoField.getText(),
                        cidadeField.getText(),
                        bairroField.getText(),
                        precoMinField.getText(),
                        precoMaxField.getText(),
                        numeroQuartosField.getText()
                };
            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();
        result.ifPresent(filtros -> {
            List<Imovel> filtrados = imobiliaria.listarImoveis();

            if (!filtros[0].isEmpty()) {
                int tipo = Integer.parseInt(filtros[0]);
                filtrados = imobiliaria.filtrarPorTipo(tipo);
            }

            if (!filtros[1].isEmpty()) {
                filtrados = imobiliaria.filtrarPorCidade(filtros[1]);
            }

            if (!filtros[2].isEmpty()) {
                filtrados = imobiliaria.filtrarPorBairro(filtros[1], filtros[2]);
            }

            if (!filtros[3].isEmpty() && !filtros[4].isEmpty()) {
                float precoMin = Float.parseFloat(filtros[3]);
                float precoMax = Float.parseFloat(filtros[4]);
                filtrados = imobiliaria.filtrarPorPreco(precoMin, precoMax);
            }

            if (!filtros[5].isEmpty()) {
                int numeroQuartos = Integer.parseInt(filtros[5]);
                filtrados = imobiliaria.filtrarPorNumeroQuartos(numeroQuartos);
            }

            listaImoveis.setAll(filtrados);
        });
    }

    @FXML
    private void handleExcluirImovel() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Excluir Imóvel");
        dialog.setHeaderText("Excluir Imóvel");
        dialog.setContentText("Digite o código do imóvel a ser excluído:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(codigo -> {
            imobiliaria.removerImovel(Integer.parseInt(codigo));
            listaImoveis.setAll(imobiliaria.listarImoveis());
        });
    }

    @FXML
    private void handleAlterarImovel() {
        Dialog<Imovel> dialog = new Dialog<>();
        dialog.setTitle("Alterar Imóvel");
        dialog.setHeaderText("Digite o código do imóvel a ser alterado e os novos detalhes");

        ButtonType alterarButtonType = new ButtonType("Alterar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(alterarButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField codigoField = new TextField();
        codigoField.setPromptText("Código");
        TextField areaConstruidaField = new TextField();
        areaConstruidaField.setPromptText("Área Construída");
        TextField areaTotalField = new TextField();
        areaTotalField.setPromptText("Área Total");
        TextField numeroQuartosField = new TextField();
        numeroQuartosField.setPromptText("Número de Quartos");
        TextField tipoField = new TextField();
        tipoField.setPromptText("Tipo (0 - Casa, 1 - Apartamento)");
        TextField precoField = new TextField();
        precoField.setPromptText("Preço");
        TextField cidadeField = new TextField();
        cidadeField.setPromptText("Cidade");
        TextField bairroField = new TextField();
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
        grid.add(tipoField, 1, 4);
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
        GridPane.setHgrow(tipoField, Priority.ALWAYS);
        GridPane.setHgrow(precoField, Priority.ALWAYS);
        GridPane.setHgrow(cidadeField, Priority.ALWAYS);
        GridPane.setHgrow(bairroField, Priority.ALWAYS);

        dialog.getDialogPane().setContent(grid);

        Button alterarButton = (Button) dialog.getDialogPane().lookupButton(alterarButtonType);
        alterarButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            try {
                int codigo = Integer.parseInt(codigoField.getText());
                float areaConstruida = Float.parseFloat(areaConstruidaField.getText());
                float areaTotal = Float.parseFloat(areaTotalField.getText());
                int numeroQuartos = Integer.parseInt(numeroQuartosField.getText());
                int tipo = Integer.parseInt(tipoField.getText());
                float preco = Float.parseFloat(precoField.getText());
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

    private void mostrarErroDeFormato(NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Erro de Formato");
        if (e.getMessage().contains("For input string")) {
            alert.setContentText("Por favor, insira um valor numérico válido.");
        } else {
            alert.setContentText("Erro desconhecido: " + e.getMessage());
        }
        alert.showAndWait();
    }
}