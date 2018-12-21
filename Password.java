import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Password {
    public static<V,E> ArrayList topsort(Graph<V,E> g, V v){
        ArrayList<V> set = new ArrayList<>();
        set.add(v);
        ArrayList<V> list = new ArrayList<>();
        while (!set.isEmpty()){
            V n = set.remove(0);
            list.add(n);
            Collection<E> col = g.getOutEdges(n);
            ArrayList<V> nodes = new ArrayList();
            for (E edge:col) {
                nodes.add(g.getDest(edge));
            }
            for (V m: nodes) {
                g.removeEdge(g.findEdge(n,m));
                if(g.inDegree(m) == 0){
                    set.add(m);
                }
            }
        }
        return list;
    }

    public Password() {
        // Create a graph with Integer vertices and String edges
        Graph<Integer, String> g = new DirectedSparseGraph<Integer, String>();
        String fileName = "keylog.txt";
        int j = 0;
        try {
            String h = "";
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                h = "" + j;
                String line = scanner.nextLine();
                String[] numbersString = line.split("");
                int[] numbers = new int[3];
                for (int i = 0; i < 3; i++) {
                    numbers[i] = Integer.parseInt(numbersString[i]);
                }
                g.addEdge(h, numbers[0], numbers[1]);
                j = Integer.parseInt(h);
                j++;
                h = "" + j;
                g.addEdge(h, numbers[1], numbers[2]);
                j++;
            }
            scanner.close();
        }
        catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Layout implements the graph drawing logic
        Layout<Integer, String> layout = new CircleLayout<Integer, String>(g);
        layout.setSize(new Dimension(300,300));

        // VisualizationServer actually displays the graph
        BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size

        // Transformer maps the vertex number to a vertex property
        Transformer<Integer, Paint> vertexColor = new Transformer<Integer,Paint>() {
            public Paint transform(Integer i) {
         //       if(visited.contains(i)) return Color.BLUE;
                return Color.RED;
            }
        };

        vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

        JFrame frame = new JFrame("Simple Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Graph<Integer, String> g = new DirectedSparseGraph<Integer, String>();
        String fileName = "keylog.txt";
        int j = 0;
        try {
            String h = "";
                Scanner scanner = new Scanner(new File(fileName));
                while (scanner.hasNextLine()) {
                    h = "" + j;
                    String line = scanner.nextLine();
                    String[] numbersString = line.split("");
                    int[] numbers = new int[3];
                    for (int i = 0; i < 3; i++) {
                        numbers[i] = Integer.parseInt(numbersString[i]);
                    }
                    g.addEdge(h, numbers[0], numbers[1]);
                    j = Integer.parseInt(h);
                    j++;
                    h = "" + j;
                    g.addEdge(h, numbers[1], numbers[2]);
                    j++;
                }
                scanner.close();
            }
        catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        new Password();
        System.out.println(topsort(g, 7));

    }
}

