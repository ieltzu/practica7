package practica7;


public class Camino implements Comparable {
	private static Tsp tsp;
	
	public static void setTsp(Tsp tsp) {
		Camino.tsp = tsp;
	}
	
	private int[] cam;
	private double dist=-1;
	
	public Camino(int[] c){
		this.cam = c;
	}
	
	public Camino(int[] c, double dis){
		this.cam = c;
		this.dist = dis;
	}
	
	public int getPos(int pos){
		return this.cam[pos];
	}
	
	public void setPos(int pos, int num){
		this.dist = -1;
		this.cam[pos] = num;
	}
	
	public int paradas(){
		return this.cam.length;
	}
	
	public Camino clone(){
		return new Camino(this.cam.clone(), this.dist);
	}
	
	public String imprimir(){
		String s = "[";
		for (int i = 0; i < cam.length; i++) {
			int j = cam[i];
			s += j + ((i==(cam.length-1))?"]":",");
		}
		try {
			s+="  ->  " + this.distancia();
		} catch (Exception e) {
		}
		return s;
	}
	
	public boolean equals(Camino c){
		boolean iguales = this.cam.equals(c.cam);
		if (iguales && this.dist!=-1) c.dist=this.dist;
		if (iguales && c.dist!=-1) this.dist=c.dist;
		return iguales;
	}
	
	
	public double distancia() throws Exception{
		if (this.dist == -1){
			this.dist = Camino.tsp.cost(cam);
		}
		return this.dist;
	}
	
	public int getIndexOf(int ciudad){
		int i = -1;
		for (int j = 0; j < cam.length; j++) {
			if (cam[j]==ciudad){
				i = j;
			}
		}
		return i;
	}

	@Override
	public int compareTo(Object o) {
		double a;
		try {
			a = this.distancia();
		} catch (Exception e) {
			a=Double.MAX_VALUE;
		}
		double b;
		try {
			b = ((Camino) o).distancia();
		} catch (Exception e) {
			b = Double.MAX_VALUE;
		}
		if (a==b) return 0;
		return (a<b)?-1:1;
	}
}
