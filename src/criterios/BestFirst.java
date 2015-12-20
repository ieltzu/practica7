package criterios;

import java.util.ArrayList;

import practica7.Tsp;

public class BestFirst implements Criterio{

	@Override
	public Tsp evaluar(Tsp tsp,int[] camino) {
		ArrayList<Tsp> swaps = new ArrayList<Tsp>();
		tsp.crearSwaps(swaps);
		double costo=tsp.cost(camino);
		boolean mejorado=true;
		double costotmp=0.0;
		do{
			//aqui ira el algortimo greedy
			if(costotmp<costo){
				mejorado=false;
			}else{
				costo=costotmp;
			}
			
		}while(mejorado);
		return null;
	}

}
