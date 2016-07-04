package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.awt.geom.Line2D;
import java.util.Vector;

/**
 * Класс для управления элементами дочерней панели.
 */
public class GraphController extends Controller {
    private Alert alert;
    @FXML
    private Pane pane1;
    static int Setting = 1;

    /**
     * Обработка нажатия клавиши "Нарисовать".
     * Изображает заданный граф.
     */
    @FXML
    public void graphButton() {

        for (int i = 0; i < P.n; i++) {
            Ellipse C = new Ellipse(P.vicual.elementAt(i).x, P.vicual.elementAt(i).y, 6, 9);
            C.setFill(Color.BLUE);
            pane1.getChildren().add(C);
            Label label = new Label(Integer.toString(P.vicual.elementAt(i).Name));
            label.setTextFill(Color.RED);
            label.setLayoutX(P.vicual.elementAt(i).x - 15);
            label.setLayoutY(P.vicual.elementAt(i).y + 5);
            pane1.getChildren().add(label);
        }
        for (int i = 0; i < P.m; i++) {
            Line q = new Line(P.vicual.elementAt(P.list.elementAt(i).from).x, P.vicual.elementAt(P.list.elementAt(i).from).y, P.vicual.elementAt(P.list.elementAt(i).to).x, P.vicual.elementAt(P.list.elementAt(i).to).y);
            q.setStrokeWidth(0.5);
            q.setFill(Color.BLACK);
            pane1.getChildren().add(q);
            this.arrow(P.vicual.elementAt(P.list.elementAt(i).from).x, P.vicual.elementAt(P.list.elementAt(i).to).x, P.vicual.elementAt(P.list.elementAt(i).from).y, P.vicual.elementAt(P.list.elementAt(i).to).y, Color.BLACK);
        }
        if (P.V != -1)
            if (P.ways.elementAt(P.V) == 1000000000) {
                Label label = new Label("Путь из вершины " + Integer.toString(P.v) + " в вершину " + Integer.toString(P.V + 1) + ": NO\n");
                label.setTextFill(Color.RED);
                label.setLayoutX(0);
                label.setLayoutY(0);
                pane1.getChildren().add(label);
            } else {
                Vector<Integer> path = new Vector<Integer>();
                for (int cur = P.V; cur != -1; cur = P.road.elementAt(cur))
                    path.add(cur);

                for (int i = path.size() - 1; i >= 1; i--) {

                    int l = (path.elementAt(i));
                    int k = (path.elementAt(i - 1));
                    ;
                    Line q = new Line(P.vicual.elementAt(l).x, P.vicual.elementAt(l).y, P.vicual.elementAt(k).x, P.vicual.elementAt(k).y);
                    q.setStroke(Color.RED);
                    q.setStrokeWidth(1);
                    pane1.getChildren().add(q);
                    this.arrow(P.vicual.elementAt(l).x, P.vicual.elementAt(k).x, P.vicual.elementAt(l).y, P.vicual.elementAt(k).y, Color.RED);
                }
                path.clear();
            }

    }

    /**
     * Функция отрисовки направления движения по ребру.
     *
     * @param x     координаты
     * @param x1    координаты
     * @param y     координаты
     * @param y1    координаты
     * @param color цвет линии.
     */
    void arrow(int x, int x1, int y, int y1, Color color) {
        double beta = Math.atan2(y - y1, x1 - x); //{ArcTan2 ищет арктангенс от x/y что бы неопределенностей не
        //  возникало(например, деление на 0)}
        double alfa = Math.PI / 10;// {угол между основной осью стрелки и рисочки в конце}
        int r1 = 10; //{длинна риски}

        int x2 = (int) Math.round(x1 - r1 * Math.cos(beta + alfa));
        int y2 = (int) Math.round(y1 + r1 * Math.sin(beta + alfa));
//g2d.drawLine(x1,y1,x2,y2);
        int x3 = (int) Math.round(x1 - r1 * Math.cos(beta - alfa));
        int y3 = (int) Math.round(y1 + r1 * Math.sin(beta - alfa));
//g2d.drawLine(x1,y1,x2,y2);
        Line q1 = new Line(x1, y1, x2, y2);
        Line q2 = new Line(x1, y1, x3, y3);
        q1.setStroke(color);
        pane1.getChildren().add(q1);
        q2.setStroke(color);
        pane1.getChildren().add(q2);
    }


}
