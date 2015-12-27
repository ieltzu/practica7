package practica7;

import java.util.ArrayList;
import java.util.Arrays;

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
			pop[i] = tsp.crearMuestraAleatoria(true);
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
		int random = (int) Math.floor(Math.random()*((uno.paradas()/2)-1));
		int[][] hijos = new int[2][uno.paradas()];
		for (int i = 0; i < hijos.length; i++) {
			for (int j = 0; j < hijos[i].length; j++) {
				hijos[i][j]=-1;
			}
		}
		int last=0;
		//int[][] ayudante = null;
		for (int i = 0; i < uno.paradas()/2; i++) {
			last = i+random+1;
			hijos[0][last] = uno.getPos(last);
			hijos[1][last] = dos.getPos(last);
			//ayudante[0][i] = uno.getPos(last);
			//ayudante[1][i] = dos.getPos(last);
		}
		/*
		int fin=uno.paradas();
		for(int k=0;k<2;k++){
			int pos=random+(uno.paradas()/2)+1;
			for(int i=random+(uno.paradas()/2)+1;i<uno.paradas();i=i+0){
				if(!contiene(ayudante[0],dos.getIndexOf(i))){
					hijos[k][i] = dos.getIndexOf(pos);
					i++;
					pos++;
				}else{
					pos++;
				}
				if(pos>uno.paradas()){
					pos=0;
				}
			}
			for (int i =0; i < random+1; i++) {
				if(!contiene(ayudante[0],dos.getIndexOf(i)))
					hijos[k][i] =  
			}
		}*/
		int last1 = last;
		int last2 = last;
		int tmp;
		do{
			tmp = uno.getIndexOf(dos.getPos(++last2 % hijos[0].length));
			if (tmp<random+1 || tmp>random+(uno.paradas()/2)) hijos[0][++last1 % hijos[0].length] = dos.getPos(last2 % hijos[0].length); 
		}while(!this.lleno(hijos[0]));
		last1 = last;
		last2 = last;
		do{
			tmp = dos.getIndexOf(uno.getPos(++last2 % hijos[1].length));
			if (tmp<random+1 || tmp>random+(dos.paradas()/2)) hijos[1][++last1 % hijos[1].length] = uno.getPos(last2 % hijos[1].length); 
		}while(!this.lleno(hijos[1]));
		
		return new Camino[]{new Camino(hijos[0]), new Camino(hijos[1])};
	}
	private boolean contiene(int[] array, int num){
		for (int i = 0; i < array.length; i++) {
			if(array[i] == num){
				return true;
			}
		}
		return false;
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
