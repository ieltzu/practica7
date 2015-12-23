package criterios;

import java.util.ArrayList;

import mutaciones.Swap;
import practica7.Camino;
import practica7.Tsp;

public class Criterios {
	
	public Tsp tsp;
	
	public Criterios(Tsp tsp) {
		this.tsp=tsp;
	}
	
	public ArrayList<Camino> crearSwaps(Camino gan) {
		ArrayList<Camino> nuevos = new ArrayList<Camino>();
		Swap sp = new Swap();
		for (int i = 0; i < this.tsp.getNumcities(); i++) {
			for (int j = i; j < this.tsp.getNumcities(); j++) {
				sp.setX(i);
				sp.setY(j);
				Camino cc = sp.mutacion(gan);
				nuevos.add(cc);
			}
		}
		return nuevos;
	}
}
