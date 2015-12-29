package practica7;

import criterios.BestFirst;
import criterios.Criterio;
import criterios.Greedy;

public class BusquedaLocal {
	
	private Tsp tsp;
	private boolean tipo;
	public enum criterios{
		Greedy, BestFirst
	}
	private criterios criterio;
	
	public BusquedaLocal(criterios criterio, Tsp tsp,boolean tipo) {
		this.criterio=criterio;
		this.tsp=tsp;
		this.tipo = tipo;
	}
	
	public void setTsp(Tsp tsp) {
		this.tsp = tsp;
	}
	
	public Camino ejecutar(){
		Criterio cr;
		if(this.criterio==criterios.Greedy){
			cr = new Greedy(this.tsp);
		}else{
			cr = new BestFirst(this.tsp);
		}
		return cr.evaluar(this.tipo);
	}
	
}
