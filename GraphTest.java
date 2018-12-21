import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;
import sun.security.provider.certpath.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.*;


public class GraphTest {
    public static ArrayList visited = new ArrayList();
    public static ArrayList finished = new ArrayList();
    public static ArrayList discover = new ArrayList();
    public static ArrayList visitOrder = new ArrayList();

    public static<V,E> void dfs(Graph<V,E> g, V vertex) {
        Stack<V> discovered = new Stack();
      //  ArrayList finishOrder = new ArrayList();
        discover.add(vertex); //color
        discovered.push(vertex);
        while(!discovered.isEmpty()){
            vertex = discovered.pop();
            for (V v:g.getPredecessors(vertex)) {
                if (!visitOrder.contains(v)) {
                discovered.push(v);
                discover.add(v); //color

                }
            }
            visitOrder.add(vertex);
        }
        System.out.println(visitOrder);
    }

    public static<V,E> void bfs(Graph<V, E> g, V vertex) {
        Queue<V> seen = new LinkedList();

        seen.offer(vertex);
        while (!seen.isEmpty()) {
            V u = seen.poll();
            visited.add(u);
            for (V v : g.getPredecessors(u)) {
            if(!seen.contains(v) && !visited.contains(v)){
                seen.offer(v);
                visited.add(v);

            }
            }
            finished.add(u);
        }
        System.out.println(finished);
        //look for adjacent nodes
        //if those nodes have not been visited, add them to the queue
    }
    public GraphTest() {
        // Create a graph with Integer vertices and String edges
        Graph<Integer, String> g = new SparseGraph<Integer, String>();
        g.addEdge("02",0,2);
        g.addEdge("12",1,2);
        g.addEdge("23",2,3);
        g.addEdge("24",2,4);
        g.addEdge("35",3,5);
        g.addEdge("36",3,6);
        g.addEdge("67",6,7);
        g.addEdge("56",5,6);
        g.addEdge("58",5,8);
        g.addEdge("59",5,9);
    // Layout implements the graph drawing logic
    Layout<Integer, String> layout = new CircleLayout<Integer, String>(g);
        layout.setSize(new Dimension(300,300));

    // VisualizationServer actually displays the graph
    BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size

    // Transformer maps the vertex number to a vertex property
    Transformer<Integer, Paint> vertexColor = new Transformer<Integer,Paint>() {
        public Paint transform(Integer i) {
            if(visited.contains(i)) return Color.BLUE;
            if(finished.contains(i)) return Color.GREEN;
            if(discover.contains(i)) return Color.BLUE;
            if(visitOrder.contains(i)) return Color.GREEN;
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
        Graph<Integer, String> g = new SparseGraph<Integer, String>();
        g.addEdge("02",0,2);
        g.addEdge("12",1,2);
        g.addEdge("23",2,3);
        g.addEdge("24",2,4);
        g.addEdge("35",3,5);
        g.addEdge("36",3,6);
        g.addEdge("67",6,7);
        g.addEdge("56",5,6);
        g.addEdge("58",5,8);
        g.addEdge("59",5,9);
        new GraphTest();
        dfs(g, 0);

    }
}
  /*  public static void main(String[] args) {



        Graph<Integer, String> g = new SparseGraph<>();
        g.addEdge("02",0,2);
        g.addEdge("12",1,2);
        g.addEdge("23",2,3);
        g.addEdge("24",2,4);
        g.addEdge("35",3,5);
        g.addEdge("36",3,6);
        g.addEdge("67",6,7);
        g.addEdge("56",5,6);
        g.addEdge("58",5,8);
        g.addEdge("59",5,9);
        System.out.println(g);
    }*/

