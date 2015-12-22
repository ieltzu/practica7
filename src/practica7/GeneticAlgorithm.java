package practica7;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import mutaciones.Swap;
import practica7.BusquedaLocal.criterios;

public class GeneticAlgorithm {
	
	private Tsp tsp;
	private int poblacionMaxima;
	
	public GeneticAlgorithm(Tsp tsp, int pob) {
		this.tsp = tsp;
		this.poblacionMaxima = pob;
	}

	private int[][] crearPopulacion() {
		int[][] pop = new int[this.poblacionMaxima][];
		for (int i = 0; i < this.poblacionMaxima; i++) {
			int[] muestra = tsp.crearMuestraAleatoria();
			for (int j = 0; j < muestra.length; j++) {
				pop[i][j]=muestra[j];
			}
		}
		return null;
	}
	
	private void mutaciones(int[][] cruces) {
		Swap sw = new Swap();
		for (int i=0; i<cruces.length; i++) {
			int[] is = cruces[i];
			if (Math.random()<.3){
				int r1 = (int)Math.floor(Math.random()*is.length);
				int r2 = r1;
				while (r2==r1){
					r2 = (int)Math.floor(Math.random()*is.length);
				}
				sw.setX(r1);
				sw.setY(r2);
				cruces[i] = sw.mutacion(is);
			}
			
		}
	}

	private int[][] generarUnCruce(int[] uno, int[] dos){
		double random = Math.floor(Math.random()*uno.length);
		int[][] hijos = new int[2][dos.length];
		for (int i = 0; i < dos.length; i++) {
			hijos[0][i] = i < random?uno[i]:dos[i];
			hijos[1][i] = i < random?dos[i]:uno[i];
		}
		return hijos;
	}
	
	private int[][] crearCruces(int[][] pop) {
		ArrayList<int[]> nuevagen = new ArrayList<int[]>();
		int[][] tmp;
		int[] numeros = new int[pop[0].length];
		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = i;
			
		}
		
		for (int i = 0; i < pop.length; i++) {
			for (int j = i; j < pop.length; j++) {
				tmp = this.generarUnCruce(pop[i], pop[j]);
				nuevagen.add(tmp[0]);
				nuevagen.add(tmp[1]);
			}
		}
		return null;
	}
	
	public int[] ejecutar() {
		int[][] pop = this.crearPopulacion(); 
		int[][] cruces = this.crearCruces(pop);
		mutaciones(cruces);
		return null;
	}

	
	
}
