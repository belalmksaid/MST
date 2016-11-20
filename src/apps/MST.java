package apps;

import structures.*;
import java.util.ArrayList;

public class MST {

	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph
	 *            Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		PartialTreeList L = new PartialTreeList();
		for (int i = 0; i < graph.vertices.length; i++) {
			PartialTree pt = new PartialTree(graph.vertices[i]);
			for (Vertex.Neighbor n = graph.vertices[i].neighbors; n != null; n = n.next) {
				PartialTree.Arc a = new PartialTree.Arc(graph.vertices[i], n.vertex, n.weight);
				pt.getArcs().insert(a);
			}
			L.append(pt);
		}
		return L;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree
	 * list
	 * 
	 * @param ptlist
	 *            Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is
	 *         irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		PartialTreeList L = ptlist;
		ArrayList<PartialTree.Arc> arcs = new ArrayList<PartialTree.Arc>();
		while (L.size() > 1) {
			PartialTree a1 = L.remove();
			PartialTree.Arc alpha = a1.getArcs().deleteMin();
			boolean belongs = true;
			while (belongs) {
				boolean has = false;
				for (PartialTree.Arc ax : a1.getArcs()) {
					if (ax.v1.name.equals(alpha.v2.name)) {
						has = true;
					}
				}
				if (has) {
					alpha = a1.getArcs().deleteMin();
					belongs = true;
				} else {
					belongs = false;
				}
			}
			PartialTree b2 = null;
			try {
				b2 = L.removeTreeContaining(alpha.v2);
				a1.merge(b2);
				L.append(a1);
				arcs.add(alpha);
			} catch (Exception e) {
				
			}
			/*
			 * for (PartialTree it : L) { System.out.println(it); }
			 * System.out.println("------------------after---------------");
			 */
		}
		return arcs;
	}
}
