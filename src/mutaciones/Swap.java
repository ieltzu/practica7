package mutaciones;

public class Swap implements Mutacion{
	
	private int x,y;
	
	public Swap(int x, int y) {
		x=x;
		y=y;
	}
	public Swap() {
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public int[] mutacion(int[] c) {
		int[] copia = c.clone();
		copia[x]=c[y];
		copia[y]=c[x];
		return copia;
	}
	
	
}
