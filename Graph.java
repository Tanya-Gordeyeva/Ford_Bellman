package main.java.algoritm;
import java.util.Random;
import java.util.Vector;
import java.io.InputStream;

public class Graph implements Graph_Interface {
    final int inf = 1000000000;
    final Random random = new Random();
    static int c=0;
    /*int n;//количество узлов
    int m=0;//количество ветвей
    int v;// узел из которого нужно считать пути;
    Vector<Element_graph_way> list=new Vector<Element_graph_way>(); //= new Element_graph_way[];//база для информации о графе
    Vector <Integer> ways=new Vector<Integer>();
    Vector <Integer> road=new Vector<Integer>();*/

    public int input_file() {
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
                for (int j = 0; j < c; j++) {
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
        return v;
    }

    public void input_generation(int n, int k, Vector<Element_graph_way> list) {
       // n = 20;
        for (int i = 0; i < n; i++) {
            //int k = 2;// количество путей из i
           // m = m + k;
           c=c+k;
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

    public void output_graph() {

    }

}
