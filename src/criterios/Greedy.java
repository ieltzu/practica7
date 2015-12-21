package criterios;

import java.util.ArrayList;

import mutaciones.Swap;
import practica7.Tsp;

public class Greedy implements Criterio{

	private Tsp tsp;
	@Override
	public int[] evaluar(Tsp tsp){
		this.tsp = tsp;
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

	private ArrayList<int[]> crearSwaps(int[] gan) {
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

