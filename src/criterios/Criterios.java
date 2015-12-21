package criterios;

import java.util.ArrayList;

import mutaciones.Swap;
import practica7.Tsp;

public class Criterios {
	
	private Tsp tsp;
	
	public Criterios(Tsp tsp) {
		this.tsp=tsp;
	}
	
	public ArrayList<int[]> crearSwaps(int[] gan) {
		ArrayList<int[]> nuevos = new ArrayList<int[]>();
		Swap sp = new Swap();
		for (int i = 0; i < this.tsp.getNumcities(); i++) {
			for (int j = i; j < this.tsp.getNumcities(); j++) {
				sp.setX(i);
				sp.setY(j);
				nuevos.add(sp.mutacion(gan));
			}
		}
		return nuevos;
	}
}
