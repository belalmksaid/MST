package apps;

import java.io.IOException;
import java.util.ArrayList;

import apps.PartialTree.Arc;
import structures.Graph;
import structures.Vertex;

public class program {
	public static void main(String[] args) throws IOException {
		PartialTreeList L = MST.initialize(new Graph("graph3.txt"));
		ArrayList<Arc> a = MST.execute(L);
		for(int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
		}
		/*for(PartialTree it : L) {
			System.out.println(it);
		}
		System.out.println("------------------before---------------\n\n\n");
		for(PartialTree it : L) {
			System.out.println(it);
		}
		System.out.println("------------------after---------------");*/
	}
}
