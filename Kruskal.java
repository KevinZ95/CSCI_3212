import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import edu.gwu.algtest.*;
import edu.gwu.util.*;

public class Kruskal implements SpanningTreeAlgorithm {

	double[] X;
	double[] Y;
	double[][] myGraph;
	double[][] mst; 
	int[] mySet;
	int size;
	//double[] edges; 

	ArrayList<cell> edges; 
	
	class cell implements Comparable<cell>{
		double distance;
		int point_a;
		int point_b;
		@Override
		public int compareTo(cell arg0) {
			if (this.distance < arg0.distance) {
				return -1;
			} else if (this.distance == arg0.distance) {
				return 0;
			} else {
				return 1; 
			}
			
			
		}	
	}
	
	@Override
	public String getName() {
		return "Jinwen Zhang, Kruskal's algorithm";
	}

	@Override
	public void setPropertyExtractor(int arg0, PropertyExtractor arg1) {
		return;

	}

	@Override
	public double getTreeWeight() {
		double result = 0; 
		
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j < size; j++) {
				result += mst[i][j];
			}
		}
		
		return result;
	}

	@Override
	public void initialize(int n) {
		size = n;
		X = new double[n];
		Y = new double[n];
		myGraph = new double[n][n];
		mst = new double[n][n];
		
		
		for (int i = 0; i < n; i++) {
			X[i] = UniformRandom.uniform();
		}

		for (int i = 0; i < n; i++) {
			Y[i] = UniformRandom.uniform();
		}

		for (int i = 0; i < n; i++) {	// row
			
			for (int j = 0; j < n; j++) {	// column 
				myGraph[i][j] = getDistance(X[i], Y[i], X[j], Y[j]);
			}
		}
		
		//edges = new double[n * (n - 1) / 2];
		 edges = new ArrayList<>();
		
		
		for (int i = 0; i < n; i++) {	// row
			
			for (int j = i + 1; j < n; j++) {	// column 
				cell temp = new cell();
				temp.distance = myGraph[i][j];
				temp.point_a = i;
				temp.point_b = j;
				 edges.add(temp);
			}
		}
		
		Collections.sort(edges);
		
		mySet = new int[n];
		for (int i = 0; i < n; i++) {
			mySet[i] = i; 
		}
		
		
	}
	
	
	private double getDistance(double a_x, double a_y, double b_x, double b_y) {
		return Math.sqrt( Math.pow(a_x - b_x, 2) + Math.pow(a_y - b_y, 2));
	}

	private void printGraph(double[][] g) {
		//int size = myGraph.length;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(g[i][j] + "        ");
			}
			System.out.println();
		}
	}
	
	private void printEdges(ArrayList<cell> e) {
		System.out.println("length : " + e.size());

		for (int i = 0; i < e.size(); i++ ) {
			System.out.print("starting : " + e.get(i).point_a + " // ");
			System.out.print("ending : " + e.get(i).point_b + " // ");
			System.out.println(e.get(i).distance + " ");
		}
		System.out.println();

	}
	
	@Override
	public double[][] minimumSpanningTree(double[][] arg0) {
		
		for (int i = 0; i < edges.size(); i++) {
			
			if (!isSameSet(edges.get(i).point_a, edges.get(i).point_b)) {
				mst[edges.get(i).point_a][edges.get(i).point_b] = edges.get(i).distance;
				union(edges.get(i).point_a, edges.get(i).point_b);
			}
			
			
		}
		
		
		return mst;
	}

	private boolean isSameSet(int a, int b) {
		if (mySet[a] == mySet[b]) {
			return true;
		}
		
		return false; 
	}
	private void union(int a, int b) {
		int set_a = mySet[a];
		int set_b = mySet[b];
		
		for (int i = 0; i < size; i++) {
			if (mySet[i] == set_b) {
				mySet[i] = set_a;
			}
		}
	}
	
	
	// empty implement
	@Override
	public GraphVertex[] minimumSpanningTree(GraphVertex[] arg0) {
		return null;
	}
	
	public static void main(String[] arg) {
		Kruskal k = new Kruskal();
		k.initialize(5);
		k.printGraph(k.myGraph);
		k.printEdges(k.edges);
		k.minimumSpanningTree(k.myGraph);
		k.printGraph(k.mst);
		System.out.println(k.getTreeWeight());
	}

}
