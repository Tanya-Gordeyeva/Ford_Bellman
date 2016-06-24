package main.java.algoritm;


public interface Graph_Interface {
    void input_file();
    void input_generation();
    void search_algorithm();
    void output_ways();
    void output_graph();
    public static void main(String[] args) {
    Graph P=new Graph();
    P.input_generation();
    P.search_algorithm();
    P.output_ways();
    }
}
