package practica7;

public class Camino {
	private static Tsp tsp;
	
	public static void setTsp(Tsp tsp) {
		Camino.tsp = tsp;
	}
	
	private int[] cam;
	private double dist=-1;
	
	public Camino(int[] c){
		this.cam = c;
	}
	
	public int getPos(int pos){
		return this.cam[pos];
	}
	
	public void setPos(int pos, int num){
		this.dist = -1;
		this.cam[pos] = num;
	}
	
	public double distancia() throws Exception{
		if (this.dist != -1){
			return this.dist;
		}else{
			return Camino.tsp.cost(cam);
		}
	}
}
