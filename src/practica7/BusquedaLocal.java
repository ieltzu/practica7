package practica7;

import criterios.BestFirst;
import criterios.Criterio;
import criterios.Greedy;

public class BusquedaLocal {
	
	private Tsp tsp;
	public enum criterios{
		Greedy, BestFirst
	}
	private criterios criterio;
	
	public BusquedaLocal(criterios criterio, Tsp tsp) {
		this.criterio=criterio;
		this.tsp=tsp;
	}
	
	public void setTsp(Tsp tsp) {
		this.tsp = tsp;
	}
	
	public int[] ejecutar(){
		Criterio cr;
		if(this.criterio==criterios.Greedy){
			cr = new Greedy(this.tsp);
		}else{
			cr = new BestFirst(this.tsp);
		}
		return cr.evaluar();
	}
	
}
