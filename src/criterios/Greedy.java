package criterios;

import java.util.ArrayList;

import practica7.Camino;
import practica7.Tsp;

public class Greedy  extends Criterios implements Criterio{

	public Greedy(Tsp tsp) {
		super(tsp);
	}
	
	@Override
	public Camino evaluar(boolean tipo){
		Camino ganadora = this.tsp.crearMuestraAleatoria(tipo);
		Camino tmp;
		do{
			tmp = ganadora;
			ArrayList<Camino> swaps = this.crearSwaps(ganadora);
			for (Camino camino : swaps) {
				try {
					if (camino.distancia()<ganadora.distancia()){
						ganadora = camino;
					}
				} catch (Exception e) {
					break;
				}
			}
		}while(!ganadora.equals(tmp));
		return ganadora;
	}

	
}	

