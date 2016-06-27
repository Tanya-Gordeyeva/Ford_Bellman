package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Vector;

public class Controller {
    private Alert alert;
    @FXML
    private TextField textField1;
    @FXML
    private TextArea amountVertex;
    @FXML
    private TextArea amountEdges;
    @FXML
    private TextArea result;
    @FXML
    private TextArea textGraph;

    private Graph P = new Graph();
    private GraphController child=new GraphController();

    @FXML
    public void generateGraph() {
        if (amountVertex.getText() == null || amountVertex.getText().length() == 0) {
            error("Введите количество вершин");
            int x = Integer.parseInt(amountVertex.getText());
        } else {
            if (amountEdges.getText() == null || amountEdges.getText().length() == 0) {
                error("Введите количество ребер");
                int y = Integer.parseInt(amountEdges.getText());

            }
            try {
                int x = Integer.parseInt(amountVertex.getText());
                int y = Integer.parseInt(amountEdges.getText());
                if (x > 30 || x < 0 || y > 10 || y < 0) {
                    error("Граф может быть не отображен.");
                } else {
                    P.n = x;
                    P.m = y;
                    textGraph.clear();
                    P.input_generation();
                    child.initChild();
                    for (int i = 0; i < list.size(); i++) {
                        textGraph.appendText("Путь из вершины " + (P.list.elementAt(i).from + 1) + " в вершину " + (P.list.elementAt(i).to + 1) + ": " + P.list.elementAt(i).l + "\n");
                    }
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                error("Введено некорректное значение в одно из полей! Пожалуста, вводите только цифры.");
            }
        }
    }


    public void error(String s) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Некорректный ввод");
        alert.setHeaderText(s);
        alert.showAndWait();
    }


    public void beginAlgorithm(int m) {
        P.search_algorithm();
        P.output_ways();
    }

    @FXML
    public void workBegin() {
        if (P.n == 0) {
            error("Граф не сгенерирован");
        } else {
            if (textField1.getText() == null || textField1.getText().length() == 0) {
                error("Введите вершину");
                int m = Integer.parseInt(amountEdges.getText());
                P.v=m;
            } else {
                try {
                    int m = Integer.parseInt(textField1.getText());
                    P.v=m;
                    if (m > v || m <= 0)
                        error("Вершина задана неверно");
                    else beginAlgorithm(m);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    error("Введено некорректное значение в одно из полей! Пожалуста, вводите только цифры.");
                }
            }
        }
    }

    @FXML
    public void fileGeneration() {
        P.input_file();
        textGraph.clear();
        for (int i = 0; i < list.size(); i++) {
            textGraph.appendText("Путь из вершины " + (P.list.elementAt(i).from + 1) + " в вершину " + (P.list.elementAt(i).to + 1) + ": " + P.list.elementAt(i).l + "\n");
        }
    }
}

