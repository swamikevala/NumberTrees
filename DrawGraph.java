import java.awt.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import javax.swing.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultGraphType;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;
import org.jungrapht.visualization.VisualizationViewer;
import org.jungrapht.visualization.layout.algorithms.KKLayoutAlgorithm;


public class DrawGraph {

	private Graph<Integer, DefaultEdge> g;
	
	public DrawGraph(Graph<Integer, DefaultEdge> g, Rational r) {

		VisualizationViewer<Integer, DefaultEdge> vv =
        VisualizationViewer.builder(g)
            .viewSize(new Dimension(700, 700))
            .layoutAlgorithm(new KKLayoutAlgorithm<>())
            .build();
    
		vv.getRenderContext().setVertexLabelFunction(v -> v.toString());

	    // create a frame to hold the graph visualization
	    final JFrame frame = new JFrame(r.toString());
	    frame.getContentPane().add(vv.getComponent());
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public void setGraph(Graph<Integer, DefaultEdge> graph) {
		this.g = graph;
	}

}