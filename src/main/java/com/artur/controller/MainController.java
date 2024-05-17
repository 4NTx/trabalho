package com.artur.controller;

import com.artur.model.Imovel;
import com.artur.service.Imobiliaria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private AdicionarImovelController adicionarImovelController;
    private ListarImoveisController listarImoveisController;
    private FiltrarImoveisController filtrarImoveisController;
    private ExcluirImovelController excluirImovelController;
    private AlterarImovelController alterarImovelController;

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

        adicionarImovelController = new AdicionarImovelController(imobiliaria, listaImoveis);
        listarImoveisController = new ListarImoveisController(imobiliaria, listaImoveis);
        filtrarImoveisController = new FiltrarImoveisController(imobiliaria, listaImoveis);
        excluirImovelController = new ExcluirImovelController(imobiliaria, listaImoveis);
        alterarImovelController = new AlterarImovelController(imobiliaria, listaImoveis);
    }

    @FXML
    private void handleAdicionarImovel() {
        adicionarImovelController.handleAdicionarImovel();
    }

    @FXML
    private void handleListarImoveis() {
        listarImoveisController.handleListarImoveis();
    }

    @FXML
    private void handleFiltrarImoveis() {
        filtrarImoveisController.handleFiltrarImoveis();
    }

    @FXML
    private void handleExcluirImovel() {
        excluirImovelController.handleExcluirImovel();
    }

    @FXML
    private void handleAlterarImovel() {
        alterarImovelController.handleAlterarImovel();
    }
}
