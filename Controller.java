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
    private Vector<Element_graph_way> list = new Vector<Element_graph_way>();
    Vector<Integer> ways = new Vector<Integer>();
    Vector<Integer> road = new Vector<Integer>();

    private static int v;
    private static int e;
    private static int k;

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
                    P.input_generation(x, y, list);
                    textGraph.clear();
                    v = x;
                    e = y;
                    for (int i = 0; i < list.size(); i++) {
                        textGraph.appendText("Путь из вершины " + (list.elementAt(i).from + 1) + " в вершину " + (list.elementAt(i).to + 1) + ": " + list.elementAt(i).l + "\n");
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
        P.search_algorithm(ways, road, v, m, list);
        output_ways(v, m, ways, road);
        road.clear();
        ways.clear();
    }

    @FXML
    public void workBegin() {
        if (v == 0) {
            error("Граф не сгенерирован");
        } else {
            if (textField1.getText() == null || textField1.getText().length() == 0) {
                error("Введите вершину");
                int m = Integer.parseInt(amountEdges.getText());

            } else {
                try {
                    int m = Integer.parseInt(textField1.getText());
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
        v = P.input_file(list, v);
        textGraph.clear();
        for (int i = 0; i < list.size(); i++) {
            textGraph.appendText("Путь из вершины " + (list.elementAt(i).from + 1) + " в вершину " + (list.elementAt(i).to + 1) + ": " + list.elementAt(i).l + "\n");
        }
    }


    public void output_ways(int n, int v, Vector<Integer> ways, Vector<Integer> road) {
        result.clear();
        Vector<Integer> path = new Vector<Integer>();
        for (int j = 0; j < n; j++)
            if (j != (v - 1)) {
                if (ways.elementAt(j) == 1000000000) {
                    result.appendText("Путь из вершины " + (v) + " в вершину " + (j + 1) + ": NO");
                } else {
                    for (int cur = j; cur != -1; cur = road.elementAt(cur))
                        path.add(cur);
                    result.appendText("Путь из вершины " + (v) + " в вершину " + (j + 1) + ": " + ways.elementAt(j) + "\nКратчайший путь: ");
                    for (int i = path.size() - 1; i >= 1; i--) {
                        int l = (path.elementAt(i) + 1);
                        result.appendText(l + "->");
                    }
                    result.appendText((path.elementAt(0) + 1) + "\n");
                    path.clear();
                }
            }
    }
}

