package criterios;

import java.util.ArrayList;

import practica7.Camino;
import practica7.Tsp;

public interface Criterio {
	
	public Camino evaluar(boolean tipo);
}
