package practica7;

import practica7.BusquedaLocal.criterios;

public class GeneticAlgorithm {
	
	private Tsp tsp;
	
	public GeneticAlgorithm(Tsp tsp) {
		this.tsp = tsp;
	}

	private int[][] crearPopulacion() {
		int[][] pop = null;
		for (int i = 0; i < ??; i++) {
			int[] muestra = tsp.crearMuestraAleatoria(tsp);
			for (int j = 0; j < muestra.length; j++) {
				pop[i][j]=muestra[j];
			}
		}
		return null;
	}
	
	private int[][] mutaciones(int[][] cruces) {
		// TODO Auto-generated method stub
		return null;
	}

	private int[][] crearCruces() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int[] ejecutar() {
		int[][] pop = this.crearPopulacion(); 
		int[][] cruces = this.crearCruces();
		cruces=mutaciones(cruces);
		return null;
	}

	
	
}
