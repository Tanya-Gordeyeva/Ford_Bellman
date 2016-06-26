package main.java.algoritm;
import java.util.Random;
import java.util.Vector;
import java.io.InputStream;

public class Graph implements Graph_Interface {
    final int inf = 1000000000;
    final Random random = new Random();
    /*int n;//количество узлов
    int m=0;//количество ветвей
    int v;// узел из которого нужно считать пути;
    Vector<Element_graph_way> list=new Vector<Element_graph_way>(); //= new Element_graph_way[];//база для информации о графе
    Vector <Integer> ways=new Vector<Integer>();
    Vector <Integer> road=new Vector<Integer>();*/

    public void input_file() {

    }

    public void input_generation(int n, int k, Vector<Element_graph_way> list) {
        int fi = 0; //что это?
       // n = 20;
        for (int i = 0; i < n; i++) {
            //int k = 2;// количество путей из i
           // m = m + k;
            for (int j = 0; j < k; j++) {
                Element_graph_way Q=new  Element_graph_way();
                Q.from = i;
                System.out.println(i+1);
                int q = random.nextInt(n)+1;  // путь куда
                System.out.println( q);
                --q;
                Q.to = q;
                int a = random.nextInt(100); //вес
                System.out.println(a);
                Q.l = a;
                list.add(Q);
                fi++;
            }
        }
    }

    public void search_algorithm(Vector<Integer> ways, Vector<Integer> road, int n, int v, int m, Vector<Element_graph_way> list) {

        //road = new int[n];
        for (int i = 0; i < n; ++i) {
            ways.add(inf);
            road.add(-1);
        }
        ways.set(v,0);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < m; ++j) {
                if ((ways.elementAt(list.elementAt(j).from) < inf) & ((ways.elementAt(list.elementAt(j).from) + list.elementAt(j).l) < ways.elementAt(list.elementAt(j).to)))
                    if (i == n) {
                        System.out.println("INCORRECT INPUT");
                    }
                    else {
                        ways.set(list.elementAt(j).to,(ways.elementAt(list.elementAt(j).from)+list.elementAt(j).l));
                        road.set(list.elementAt(j).to , list.elementAt(j).from);
                    }

            }
        }
    }

    public void output_ways(int n, int v, Vector<Integer> ways, Vector<Integer> road) {
        Vector <Integer> path=new Vector <Integer>();
        for (int j = 0; j < n; j++)
            if (j != v)
            {
                if (ways.elementAt(j) == inf)
                {
                    System.out.println( "Path from " +( v + 1) + " to " + (j + 1) + ": NO");
                }
                else
                {
                    for (int cur = j; cur != -1; cur = road.elementAt(cur))
                        path.add(cur);
                    System.out.println("Path from " + (v + 1) + " to " + (j + 1) + ": " + ways.elementAt(j)+"\nBy the way: " );
                    for (int i =path.size()-1; i >=0; i--) {
                        int k=(path.elementAt(i) + 1);
                        System.out.println( k + " ");
                    }
                    path.clear();
                }
            }
    }
    public void output_graph() {

    }

}
