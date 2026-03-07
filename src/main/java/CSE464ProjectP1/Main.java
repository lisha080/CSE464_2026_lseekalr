package CSE464ProjectP1;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //feature 1
        Graph graph = new Graph();
        graph.parseGraph("input.dot");
        System.out.println(graph);

        //feature 2
        graph.addNode("x");
        graph.addNodes(new String[]{"y","z"});
    }
}