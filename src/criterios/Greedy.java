package criterios;

import java.util.ArrayList;

import mutaciones.Swap;
import practica7.Tsp;

public class Greedy  extends Criterios implements Criterio{

	public Greedy(Tsp tsp) {
		super(tsp);
	}
	
	@Override
	public int[] evaluar(Tsp tsp){
		
		int[] ganadora = tsp.crearMuestraAleatoria();
		ArrayList<int[]> swaps = this.crearSwaps(ganadora);
		double costo= tsp.cost(ganadora);
		boolean mejorado=true;
		double costotmp=Double.MAX_VALUE;
		double costolocal;
//		int loop =0;
		do{
			int[] ganlocal = null;
			costolocal=Double.MAX_VALUE;
			for (int[] is : swaps) {
				costotmp = tsp.cost(is);
				if (costotmp<costolocal){
					costolocal=costotmp;
					ganlocal=is;
				}
			}
			if(costolocal>=costo){
				mejorado=false;
			}else{
				costo=costolocal;
				ganadora = ganlocal;
				swaps = this.crearSwaps(ganadora);
			}
//			System.out.print(loop++ + " - " + costo + " - ");
//			for (int i = 0; i < ganadora.length; i++) {
//	        	System.out.print(ganadora[i]+", ");
//			}
			System.out.println();
		}while(mejorado);
		return ganadora;
		}

	
}	

