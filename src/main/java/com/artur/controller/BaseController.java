package com.artur.controller;

import javafx.scene.control.Alert;

public abstract class BaseController {

    protected void mostrarErro(String cabecalho, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    protected void mostrarErroDeFormato(NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Erro de Formato");
        alert.setContentText("Por favor, insira valores válidos nos campos apropriados.");
        alert.showAndWait();
    }

    protected float parseFloat(String value, String fieldName) throws NumberFormatException {
        try {
            return Float.parseFloat(value.replace("R$", "").trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Formato inválido para o campo " + fieldName);
        }
    }

    protected int parseInt(String value, String fieldName) throws NumberFormatException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Formato inválido para o campo " + fieldName);
        }
    }
}
