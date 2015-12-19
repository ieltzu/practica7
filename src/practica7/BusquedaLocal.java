package practica7;

public class BusquedaLocal {
	
	private Tsp tsp;
	private int[] camino;
	private double cost;
	
	public BusquedaLocal() {
		
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
		setCamino(tsp.crearMuestraAleatoria());
		setCost(tsp.cost(this.camino));
	}
	
}
