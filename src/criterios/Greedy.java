package criterios;

import java.util.ArrayList;

import practica7.Camino;
import practica7.Tsp;

public class Greedy  extends Criterios implements Criterio{

	public Greedy(Tsp tsp) {
		super(tsp);
	}
	
	@Override
	public Camino evaluar(){
		Camino ganadora = this.tsp.crearMuestraAleatoria();
		System.out.println("Inicial:");
		System.out.println(ganadora.imprimir());
		ArrayList<Camino> swaps = this.crearSwaps(ganadora);
		double costo=Double.MAX_VALUE;
		try {
			costo = ganadora.distancia();
		} catch (Exception e1) {
		}
		boolean mejorado=true;
		double costotmp=Double.MAX_VALUE;
		double costolocal;
		do{
			Camino ganlocal = null;
			costolocal=Double.MAX_VALUE;
			for (Camino is : swaps) {
				try {
					costotmp = is.distancia();
					if (costotmp<costolocal){
						costolocal=costotmp;
						ganlocal=is;
					}
				} catch (Exception e) {
					mejorado=false;
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
		}while(mejorado);
		return ganadora;
	}

	
}	

