package criterios;

import java.util.ArrayList;
import practica7.Tsp;

public interface Criterio {
	
	public Tsp evaluar(Tsp tsp,int[] camino);
}
