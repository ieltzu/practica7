package practica7;

import java.util.ArrayList;

import criterios.BestFirst;
import criterios.Criterio;
import criterios.Greedy;

public class BusquedaLocal {
	
	private Tsp tsp;
	private int[] camino;
	private double cost;
	public enum criterios{
		Greedy, BestFirst
	}
	private criterios criterio;
	
	public BusquedaLocal(criterios criterio, Tsp tsp) {
		this.criterio=criterio;
		this.camino=camino;
		this.tsp=tsp;
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
	
	public int[] ejecutar(){
		Criterio cr;
		if(this.criterio==criterios.Greedy){
			cr = new Greedy();
		}else{
			cr = new BestFirst();
		}
		return cr.evaluar(this.tsp);
	}
	
}
