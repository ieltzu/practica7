package mutaciones;

import practica7.Camino;

public class Swap implements Mutacion{
	
	private int x,y;
	
	public Swap(int x, int y) {
		this.x=x;
		this.y=y;
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
	public Camino mutacion(Camino c) {
		Camino copia = (Camino) c.clone();
		copia.setPos(x, c.getPos(y));
		copia.setPos(y, c.getPos(x));
		return copia;
	}
	
	
}
