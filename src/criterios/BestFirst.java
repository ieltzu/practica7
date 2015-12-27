package criterios;

import java.util.ArrayList;

import practica7.Camino;
import practica7.Tsp;

public class BestFirst extends Criterios implements Criterio{
	
	public BestFirst(Tsp tsp) {
		super(tsp);
	}
	
	@Override
	public Camino evaluar() {
		Camino ganadora = tsp.crearMuestraAleatoria(true);
		System.out.println("Inicial:");
		System.out.println(ganadora.imprimir());
		Camino tmp;
		do{
			tmp = ganadora;
			ArrayList<Camino> swaps = this.crearSwaps(ganadora);
			for (Camino camino : swaps) {
				try {
					if (camino.distancia()<ganadora.distancia()){
						ganadora = camino;
						break;
					}
				} catch (Exception e) {
					break;
				}
			}
		}while(!ganadora.equals(tmp));

		return ganadora;
	}

}
