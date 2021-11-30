import org.jgrapht.*;
import org.jgrapht.alg.isomorphism.AHURootedTreeIsomorphismInspector;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class TreeTest {

	public static void main(String args[]) throws InterruptedException {
		
		Graph<Integer, DefaultEdge> seed = new DefaultUndirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		seed.addVertex(1);
		
		Map<Graph<Integer, DefaultEdge>, Rational> map = new HashMap<Graph<Integer, DefaultEdge>, Rational>();
		map.put(seed, new Rational(1,1));
		TreeFunction tf1 = new TreeFunction(map);
		TreeFunction tf2 = tf1.grow();
		TreeFunction tf3 = tf2.grow();
		TreeFunction tf4 = tf3.grow();
		TreeFunction tf5 = tf4.grow();
		TreeFunction tf6 = tf5.grow();
		TreeFunction tf7 = tf6.grow();
		TreeFunction tf8 = tf7.grow();
		TreeFunction tf9 = tf8.grow();
		TreeFunction tf10 = tf9.grow();
		TreeFunction tf11 = tf10.grow();
		TreeFunction tf12 = tf11.grow();
		TreeFunction tf = tf12.grow();
	
		Iterator<Map.Entry<Graph<Integer, DefaultEdge>, Rational>> iterator = tf.getGraphMap().entrySet().iterator();
		Graph<Integer, DefaultEdge> maxG = null;
		Rational max = tf.getMaxProbability();
		int count = tf.getIsomorphismCount();
		while (iterator.hasNext()) {
			Map.Entry<Graph<Integer, DefaultEdge>, Rational> entry = iterator.next();
			Graph<Integer, DefaultEdge> g = entry.getKey();
			Rational r = entry.getValue();
			if (r.compareTo(max) == 0) {
				DrawGraph dg = new DrawGraph(g, r);
			}
		}
		
		
		//int n = tf6.getDenominator();
		//System.out.println(n);
		System.out.println("COUNT: " + count);
		System.out.println("MAX: " + max);
		
		List<Rational> probs = tf.getProbabilities();
		for (Rational p : probs) {
			System.out.println("- " + p.toString());
		}
			
	/*	
		List<Integer> probs = tf5.getProbabilities();
		for (Integer i : probs) {
			System.out.println("- " + i);
		}
		
		DrawGraph dg = new DrawGraph(tf5.getGraphMap().keySet().iterator().next());
*/
	}
}
