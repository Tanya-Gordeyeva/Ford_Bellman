package main.java.algoritm;
import java.util.Random;
import java.util.Vector;
import java.io.InputStream;

public class Graph implements Graph_Interface {
    final int inf = 1000000000;
    final Random random = new Random();
    static int c=0;
    Vector<Element_graph_way> list = new Vector<Element_graph_way>();
    Vector<Integer> ways = new Vector<Integer>();
    Vector<Integer> road = new Vector<Integer>();
    int n;//количество узлов
    int m=0;//количество ветвей
    int v;// узел из которого нужно считать пути;

    public void input_file() {
        Scanner sc;
        try {
            sc = new Scanner(new File("D://in.txt"));
            try {
                if (sc.hasNextInt()) { // возвращает истинну если с потока ввода можно считать целое число
                    v = sc.nextInt();// считывает целое число с потока ввода и сохраняем в переменную
                } else System.out.println("В файле недостаточно данных");
                if (sc.hasNextInt()) {
                    c = sc.nextInt();
                } else System.out.println("В файле недостаточно данных");
                for (int j = 0; j < m; j++) {
                    Element_graph_way Q = new Element_graph_way();
                    if (sc.hasNextInt()) {
                        Q.from = sc.nextInt();
                        Q.from--;
                    }
                    if (sc.hasNextInt()) {
                        Q.to = sc.nextInt();
                        Q.to--;
                    }
                    if (sc.hasNextInt()) {
                        Q.l = sc.nextInt();
                    }
                    list.add(Q);

                }
            } catch (Exception ex)
            {
                System.out.println("Файл пуст!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не существует!");
        }
    }

    public void input_generation() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                Element_graph_way Q=new  Element_graph_way();
                Q.from = i;
               // System.out.println(i+1);
                int q = random.nextInt(n)+1;  // путь куда
              //  System.out.println( q);
                --q;
                Q.to = q;
                int a = random.nextInt(100); //вес
               // System.out.println(a);
                Q.l = a;
                list.add(Q);
            }
        }
     m=n*m;
    }

    public void search_algorithm(Vector<Integer> ways, Vector<Integer> road, int n, int v, int m, Vector<Element_graph_way> list) {

        //road = new int[n];
        for (int i = 0; i < n; ++i) {
            ways.add(inf);
            road.add(-1);
        }
        v--;
        ways.set(v,0);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < c; ++j) {
                if ((ways.elementAt(list.elementAt(j).from) < inf) & ((ways.elementAt(list.elementAt(j).from) + list.elementAt(j).l) < ways.elementAt(list.elementAt(j).to)))
                    if (i == n) {
                        //System.out.println("INCORRECT INPUT");
                    }
                    else {
                        ways.set(list.elementAt(j).to,(ways.elementAt(list.elementAt(j).from)+list.elementAt(j).l));
                        road.set(list.elementAt(j).to , list.elementAt(j).from);
                    }

            }
        }
    }

    public void output_ways( Controller P) {
        Vector<Integer> path = new Vector<Integer>();
        for (int j = 0; j < n; j++)
            if (j != (v )) {
                if (ways.elementAt(j) == 1000000000) {
                    P.result.appendText("Путь из вершины " + (v+1) + " в вершину " + (j + 1) + ": NO");
                } else {
                    for (int cur = j; cur != -1; cur = road.elementAt(cur))
                        path.add(cur);
                    P.result.appendText("Путь из вершины " + (v+1) + " в вершину " + (j + 1) + ": " + ways.elementAt(j) + "\nКратчайший путь: ");
                    for (int i = path.size() - 1; i >= 1; i--) {
                        int l = (path.elementAt(i) + 1);
                        P.result.appendText(l + "->");
                    }
                    P.result.appendText((path.elementAt(0) + 1) + "\n");
                    path.clear();
                }
            }
        road.clear();
        ways.clear();
    }
    
    
    public void output_graph() {

    }

}
