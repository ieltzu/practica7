package practica7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import mutaciones.Swap;

public class GeneticAlgorithm {
	
	private Tsp tsp;
	private int poblacionMaxima;
	
	public GeneticAlgorithm(Tsp tsp, int pob) {
		this.tsp = tsp;
		this.poblacionMaxima = pob;
	}

	private Camino[] crearPopulacion() {
		Camino[] pop = new Camino[this.poblacionMaxima];
		for (int i = 0; i < this.poblacionMaxima; i++) {
			pop[i] = tsp.crearMuestraAleatoria();;
		}
		return pop;
	}
	
	private void mutaciones(Camino[] cruces) {
		Swap sw = new Swap();
		for (int i=0; i<cruces.length; i++) {
			Camino is = cruces[i];
			if (Math.random()<.3){
				int r1 = (int)Math.floor(Math.random()*is.paradas());
				int r2 = r1;
				while (r2==r1){
					r2 = (int)Math.floor(Math.random()*is.paradas());
				}
				sw.setX(r1);
				sw.setY(r2);
				cruces[i] = sw.mutacion(is);
			}
			
		}
	}
	
	private boolean lleno(int[] arr){
		boolean ret = true;
		for (int i : arr) {
			if (i==-1){
				ret=false;
				break;
			}
		}
		return ret;
	}

	private Camino[] generarUnCruce(Camino uno, Camino dos){
		System.out.println("Uno: "+uno.imprimir());
		System.out.println("Dos: "+dos.imprimir());
		int random = (int) Math.floor(Math.random()*((uno.paradas()/2)-1));
		int[][] hijos = new int[2][uno.paradas()];
		for (int i = 0; i < hijos.length; i++) {
			for (int j = 0; j < hijos[i].length; j++) {
				hijos[i][j]=-1;
			}
		}
		int last=0;
		for (int i = 0; i < uno.paradas()/2; i++) {
			last = i+random+1;
			hijos[0][last] = uno.getPos(last);
			hijos[1][last] = dos.getPos(last);
		}
		int last1 = last;
		int last2 = last;
		int tmp;
		do{
			tmp = uno.getIndexOf(dos.getPos(++last2 % hijos[0].length));
			if (tmp<random+1 || tmp>random) hijos[0][++last1 % hijos[0].length] = 3; 
		}while(!this.lleno(hijos[0]));
		
		return new Camino[]{new Camino(hijos[0]), new Camino(hijos[1])};
	}
	
	private Camino[] crearCruces(Camino[] pop) {
		Camino[] retu = pop.clone();
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for (int i = 0; i < retu.length; i++) {
			numeros.add(i);
		}
		do {
			int a = numeros.remove((int) Math.floor(Math.random()*numeros.size()));
			int b = numeros.remove((int) Math.floor(Math.random()*numeros.size()));
			Camino[] nuevos = this.generarUnCruce(retu[a], retu[b]);
			retu[a] = nuevos[0];
			retu[b] = nuevos[1];
		}while(numeros.size()>1);
		return retu;
	}
	
	public Camino ejecutar() {
		Camino[] pop = this.crearPopulacion();
		Arrays.sort(pop);
		Camino mejor;
		double pp;
		do{
			mejor = pop[0];
			Camino[] otros = new Camino[this.poblacionMaxima/2];
			for (int i = 0; i < otros.length; i++) {
				otros[i] = pop[i];
			}
			Camino[] cruces = this.crearCruces(otros);
			for (int i = cruces.length; i < pop.length; i++) {
				pop[i] = cruces[i-cruces.length];
			}
			mutaciones(pop);
			Arrays.sort(pop);
			
			try {
				pp = pop[1].distancia();
			} catch (Exception e) {
				pp=-1;
			}
			
		}while(pp!=-1);
		return mejor;
	}

	
	
}
