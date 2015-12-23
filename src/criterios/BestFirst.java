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
		Camino ganadora = tsp.crearMuestraAleatoria();
		ArrayList<Camino> swaps = this.crearSwaps(ganadora);
		double costo=0;
		try {
			costo = ganadora.distancia();
		} catch (Exception e1) {
		}
		boolean mejorado=true;
		double costotmp=Double.MAX_VALUE;
		double costolocal;
//		int loop =0;
		do{
			Camino ganlocal = null;
			costolocal=Double.MAX_VALUE;
			for (Camino is : swaps) {
				try {
					costotmp = is.distancia();
				} catch (Exception e) {
					mejorado = false;
					break;
				}
				if (costotmp<costolocal){
					costolocal=costotmp;
					ganlocal=is;
					break;
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
			//System.out.println();
		}while(mejorado);
		return ganadora;
	}

}
