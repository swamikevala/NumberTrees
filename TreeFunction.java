import org.jgrapht.*;
import org.jgrapht.alg.isomorphism.AHURootedTreeIsomorphismInspector;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class TreeFunction {

	private Map<Graph<Integer, DefaultEdge>, Integer> graphMap;

	public TreeFunction(Map<Graph<Integer, DefaultEdge>, Integer> graphMap) {
		this.graphMap = graphMap;
	}
	
	public TreeFunction grow() throws InterruptedException {
		
		Map<Graph<Integer, DefaultEdge>, Integer> newGraphMap = new HashMap<Graph<Integer, DefaultEdge>, Integer>();
		boolean isoExists;
		
		for (Map.Entry<Graph<Integer, DefaultEdge>, Integer> entry : graphMap.entrySet()) {
			Graph<Integer, DefaultEdge> g = entry.getKey();
			for (Integer i : g.vertexSet()) {
				isoExists = false;
				// clone the graph, add a new vertex+edge and add to new map
				//Graph<Integer, DefaultEdge> newGraph = (Graph<Integer, DefaultEdge>) ((AbstractBaseGraph)g).clone();
				Graph<Integer, DefaultEdge> newGraph = copyGraph(g);
				Integer newNumber = getNumber() + 1; 
				newGraph.addVertex(newNumber);
				newGraph.addEdge(i, newNumber);
				
				//calculate graph probability
				//check if isomorphic to another tree in the set
				
				Iterator<Map.Entry<Graph<Integer, DefaultEdge>, Integer>> iterator = newGraphMap.entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry<Graph<Integer, DefaultEdge>, Integer> newEntry = iterator.next();
					AHURootedTreeIsomorphismInspector<Integer, DefaultEdge> isoInspector = new AHURootedTreeIsomorphismInspector<Integer, DefaultEdge>(newEntry.getKey(), 1, newGraph, 1);
					if (isoInspector.isomorphismExists()) {
						isoExists = true;
						newEntry.setValue(newEntry.getValue() + 1);
						break;
					} 
				}
				if (!iterator.hasNext() && !isoExists) {
					newGraphMap.put(newGraph, entry.getValue());
				}
			}
		}
		return new TreeFunction(newGraphMap);
	}
    
	
	private Graph<Integer, DefaultEdge> copyGraph(Graph<Integer, DefaultEdge> g) {
		
		Graph<Integer, DefaultEdge> newG = new DefaultUndirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		
		for (Integer i : g.vertexSet()) {
			newG.addVertex(i);
		}
		
		for (DefaultEdge e : g.edgeSet()) {
			Integer s = g.getEdgeSource(e);
			Integer d = g.getEdgeTarget(e);
			newG.addEdge(s, d);
		}
		
		return newG;
	}
	
	
	public Map<Graph<Integer, DefaultEdge>, Integer> getGraphMap() {
		return graphMap;
	}
	
	public List<Integer> getProbabilities() {
		List<Integer> probabilities = new ArrayList<Integer>();
		for (Map.Entry<Graph<Integer, DefaultEdge>, Integer> entry : graphMap.entrySet()) {
			probabilities.add(entry.getValue());
		}
		return probabilities;
	}
	
	public int getMaxProbability() {
		List<Integer> probabilities = getProbabilities();
		return Collections.max(probabilities);
	}
	
	
	public int getNumber() {
		Map.Entry<Graph<Integer, DefaultEdge>, Integer> entry = graphMap.entrySet().iterator().next();
		return entry.getKey().vertexSet().size();
	}
	
	public int getDenominator() {
		int total = 0;
		for (Map.Entry<Graph<Integer, DefaultEdge>, Integer> entry : graphMap.entrySet()) {
			total += entry.getValue();
		}
		return total;
	}
	
	/*
	 * Define graph symmetry metric in the following way:
	 * 
	 * - start with the 1-level nodes (adjacent to the root)
	 * - sum the nodes of each subtree (having the 1-level node as the root)
	 * - take the mean of the subtree sums
	 * - sum the squares of the differences between each subtree sum and the mean
	 * ...
	 * - now take the 2-level nodes and do the same thing
	 * - multiply the score of each 2-level node by it's parent 1-level node
	 * ...
	 * - do the same thing until leaf nodes are reached
	 * - take the sum of the leaf node scores
	 * 
	 */

	public double getSymmetry(Graph<Integer, DefaultEdge> g) {
		
		double score = 0;
		List<Integer> successors = null;
		List<Double> scores = null;
		double mean = 0;
		int size = 0;
		
		List<Integer> neighbors = Graphs.neighborListOf(g, 1);
		for (Integer n : neighbors) {
			successors = Graphs.successorListOf(g, n);
			size = successors.size();
			mean += size;
		}
		int degree = g.degreeOf(i);
		Set<Integer> g.vertexSet();
		
		return score;
	}

}
