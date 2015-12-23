package criterios;

import java.util.ArrayList;

import practica7.Tsp;

public class Greedy  extends Criterios implements Criterio{

	public Greedy(Tsp tsp) {
		super(tsp);
	}
	
	@Override
	public int[] evaluar(){
		int[] ganadora = this.tsp.crearMuestraAleatoria();
//		ArrayList<int[]> swaps = this.crearSwaps(ganadora);
//		double costo=0;
//		try {
//			costo = this.tsp.cost(ganadora);
//		} catch (Exception e1) {
//		}
//		boolean mejorado=true;
//		double costotmp=Double.MAX_VALUE;
//		double costolocal;
//		do{
//			int[] ganlocal = null;
//			costolocal=Double.MAX_VALUE;
//			for (int[] is : swaps) {
//				try {
//					costotmp = this.tsp.cost(is);
//				} catch (Exception e) {
//					mejorado=false;
//					break;
//				}
//				if (costotmp<costolocal){
//					costolocal=costotmp;
//					ganlocal=is;
//				}
//			}
//			if(costolocal>=costo){
//				mejorado=false;
//			}else{
//				costo=costolocal;
//				ganadora = ganlocal;
//				swaps = this.crearSwaps(ganadora);
//			}
//		}while(mejorado);
		return ganadora;
	}

	
}	

