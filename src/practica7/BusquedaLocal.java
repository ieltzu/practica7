package practica7;

import java.util.ArrayList;

import criterios.BestFirst;
import criterios.Greedy;

public class BusquedaLocal {
	
	private Tsp tsp;
	private int[] camino;
	private double cost;
	public enum criterios{
		Greedy, BestFirst
	}
	private criterios criterio;
	
	public BusquedaLocal(criterios criterio) {
		this.criterio=criterio;
	}
	
	public void setTsp(Tsp tsp) {
		this.tsp = tsp;
	}
	
	public void setCamino(int[] camino) {
		this.camino = camino;
	}
	
	public void setCost(double d) {
		this.cost = d;
	}
	
	public void ejecutar(){
		
		if(this.criterio==criterios.Greedy){
			Greedy gred = new Greedy();
			gred.evaluar(this.tsp,this.camino);//falta sumarle las cosas que sean para evaluarlo
		}else if(this.criterio==criterios.BestFirst){
			BestFirst bf = new BestFirst();
			bf.evaluar(this.tsp,this.camino);//falta sumarle las cosas que sean para evaluarlo
		}	
	}
	
}
