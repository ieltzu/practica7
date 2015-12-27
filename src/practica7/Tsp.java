package practica7;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
 
public class Tsp
{
    // 2-D array to keep track of pairwise distances between cities
    private int[][] distances;
    private int numcities;
    private int llamadas = 0;
    private int maximollamadas = 1000000;
    // number of cities
 
    public Tsp(String fichero) throws Exception
    {
			BufferedReader in = new BufferedReader(new FileReader(fichero));
			boolean empezar = false;
			int y=0,x=0;
			for (Iterator iterator = in.lines().iterator(); iterator.hasNext();) {
				String linea = (String) iterator.next();
				if (linea.contains("DIMENSION")){
					String[] values = linea.split(" ");
					numcities = Integer.parseInt(values[values.length-1]);
					distances = new int[numcities][];
					for (int i = 0; i < distances.length; i++) {
						distances[i] = new int[i+1];
						
					}
				}
				
				if (linea.equals("EOF")) empezar= false;
				if (empezar){
					String[] values = linea.split(" ");
					for (String string : values) {
						if (!string.equals("")){
							int valor = Integer.parseInt(string);
							distances[x][y++] = valor;
							if (valor==0){
								x++;
								y=0;
							}
						}
					}
				}
				if (linea.contains("SECTION")){
					empezar=true;
				}
			}
        
    }
    
    public int getNumcities() {
		return numcities;
	}
    class Tuple<String, Double> { 
		  public String op; 
		  public final double valor; 
		  public Tuple(String x, double y) { 
		    this.op = x; 
		    this.valor = y; 
		  } 
		  public java.lang.String toString(){
			  return (java.lang.String) this.op;
		  }
	} 
    private double suma(ArrayList<Tuple> tuples){
    	double s = 0;
    	for (Tuple tuple : tuples) {
			s+=tuple.valor;
		}
    	return s;
    }
    private ArrayList<Tuple> recalcularOpciones(ArrayList<String> result) {

    	ArrayList<Tuple> nuevos = new ArrayList<Tuple>();
    	ArrayList<String> c = new ArrayList<String>();
    	ArrayList<String> h = new ArrayList<String>();
    	ArrayList<String> b = new ArrayList<String>();
    	for (int i = 0; i < this.numcities; i++) {
			c.add(i+"");
		}
    	String[] arr;
    	for (String s : result) {
			arr = s.split("-");
			h.add(arr[0]);
			b.add(arr[arr.length-1]);
			for (int i = 1; i < arr.length-1; i++) {
				c.remove(arr[i]);
			}
		}
    	for (int i = 0; i < c.size(); i++) {
			for (int j = 0; j < c.size(); j++) {
				if (!(h.contains(c.get(i)+"") || b.contains(c.get(j)+"") || ( h.contains(c.get(j)+"") && b.contains(c.get(i)+"") ) ) ){
					if(i!=j){
						nuevos.add(new Tuple(c.get(i)+"-"+c.get(j),1.0/this.getDistance(Integer.parseInt(c.get(i)), Integer.parseInt(c.get(j)))));
					}else{
						nuevos.add(new Tuple(c.get(i)+"",1.0/200));
					}
				}
			}
		}
    	return nuevos;
    }
    private void anadirRuta(ArrayList<String> rutas, Tuple tp){
    	int anadidos = 0;
    	int tpa = Integer.parseInt(((String) tp.op).split("-")[0]);
    	int tpb = Integer.parseInt(((String) tp.op).split("-")[((String) tp.op).split("-").length-1]);
    	int sa,sb;
    	for (int i = 0; i < rutas.size(); i++) {
			String s = rutas.get(i);
			sa = Integer.parseInt(s.split("-")[0]);
	    	sb = Integer.parseInt(s.split("-")[s.split("-").length-1]);
			if ( tpa==sb ){
				tp.op = s+(tpb!=sb?"-"+tpb:"");
				rutas.remove(s);
			}
			if (tpb == sa){
				tp.op = (tpa!=sa?tpa+"-":"")+s;
				rutas.remove(s);
			}
		}
    	rutas.add((String)tp.op);
    }
    
    public Camino crearMuestraAleatoria(boolean sesgado){
    	int[] citi= new int[numcities];
    	if (sesgado){
    		ArrayList<Tuple> op = new ArrayList<Tuple>();
        	for (int i = 0; i < this.numcities; i++) {
    			for (int j = 0; j < this.numcities; j++) {
    				if(i!=j){
    					op.add(new Tuple(i+"-"+j,1.0/this.getDistance(i, j)));
    				}else{
    					op.add(new Tuple(i+"",1.0/200));
    				}
    			}
    		}
        	ArrayList<String> result = new ArrayList<String>();
        	while (op.size()>0){
        		double random = Math.random()*this.suma(op);
        		double acum=0;
        		for (int i = 0; i < op.size(); i++) {
    				Tuple js = op.get(i);
    				acum += js.valor;
    				if (acum>random){
    					this.anadirRuta(result, js);
    					op=this.recalcularOpciones(result);
    					break;
    				}
    			}
        	}
        	int i = 0;
        	for (String string : result) {
        		String[] ops = string.split("-");
        		for (String j : ops) {
    				citi[i++] = Integer.parseInt(j);
    			}
    		}
    		
    	}else{
	    	ArrayList<Integer> todas = new ArrayList<Integer>();
	    	for (int i = 0; i < citi.length; i++) {
				todas.add(i);
			}
	    	int i=0;
	    	while(todas.size()!=0){
	    		int pos = (int) Math.floor(Math.random()*todas.size());
	    		citi[i++]=todas.get(pos);
	    		todas.remove(pos);
	    	}
    	}
    	return new Camino(citi);
    	
    }

	// A simple getIndex method to help test the constructor
    int getIndex(String city, String state)
    {
        return 0;
    }
 
    // Print distance information between a given pair of cities
    void printDistanceInfo(int i, int j)
    {
        if (i < j)
            System.out.println(distances[j][i]);
        else
            System.out.println(distances[i][j]);
    }
 
    int getDistance(int i, int j)
    {
        if (i < j)
            return distances[j][i];
        else if (j < i)
            return distances[i][j];
        else
            return 0;
    }
 
    void copy(int[] source, int[] dest)
    {
        for (int i = 0; i < dest.length; i++)
            dest[i] = source[i];
    }
 
    public double cost(int[] tour) throws Exception
    {
    	if (this.llamadas++>this.maximollamadas){
    		throw new Exception();
    	}
        return cost(tour, tour.length);
    }
 
    double cost(int[] tour, int tourSize)
    {
        double c = 0;
        for (int i = 0; i < tourSize - 1; i++)
            c = c + getDistance(tour[i], tour[i + 1]);
        c = c + getDistance(tour[tourSize - 1], tour[0]);
        return c;
    }
    
    void printMatriz(){
    	for (int[] is : distances) {
			for (int i : is) {
				System.out.print("\t"+i);
			}
			System.out.println();
		}
    }
    
    public void resetLlamadas(){
    	this.llamadas = 0;
    }
 
    // Main method
    public static void main(String[] args) throws Exception{
    	Hashtable<String, String> params = Args.parse(args);
        Tsp T = new Tsp(params.containsKey("-f")?params.get("-f"):"dantzig42.tsp");
        Camino.setTsp(T);
        
        boolean caleat = false, greedy = false, bestFirst = false, genetic = false;
        
        if (params.containsKey("-checkAleatorio")) caleat = true;
        if (params.containsKey("-greedy")) greedy = true;
        if (params.containsKey("-bestfirst")) bestFirst = true;
        if (params.containsKey("-genetic")) genetic = true;
        
        int loops = 10;
        if (params.containsKey("-l")){
        	loops = Integer.parseInt(params.get("-l"));
        }
        
        int pobMax = 2500;
        if (params.containsKey("-pobmax")){
        	pobMax = Integer.parseInt(params.get("-pobmax"));
        }
        
        if (!(caleat || greedy || bestFirst || genetic)){
        	caleat = true;
        	greedy = true;
        	bestFirst = true;
        	genetic = true;
        }
        
        System.out.println("#########################################");
        if (caleat){
        	System.out.println("Comprobaremos si es mejor empezar con un aleatorio uniforme o sesgada:");
        	long tI, tI2, m1=0, m2=0;
        	double t1=0, t2=0;
        	for (int i = 0; i < loops; i++) {
        		T.resetLlamadas();
				tI = System.currentTimeMillis();
				try {
					t1+=T.crearMuestraAleatoria(false).distancia();
				} catch (Exception e) {}
				m1 += (System.currentTimeMillis() - tI);
				
				tI2 = System.currentTimeMillis();
				try {
					t2+=T.crearMuestraAleatoria(true).distancia();
				} catch (Exception e) {}
				m2 += (System.currentTimeMillis() - tI2);
			}
        	m1 = m1 / loops;
        	m2 = m2 / loops;
        	t1 = t1 / loops;
        	t2 = t2 / loops;
        	
        	System.out.println("\nAleatorio uniforme:");
        	System.out.println("\tDistancia Media:\t"+t1);
        	System.out.println("\tTiempo de ejecucion Medio:\t"+m1);
        	System.out.println("\nAleatorio sesgado:");
        	System.out.println("\tDistancia Media:\t"+t2);
        	System.out.println("\tTiempo de ejecucion Medio:\t"+m2);
        	
        	System.out.println("\nPara la selección de tipo de aleatoriedad nos basaremos en la distancia.");
        	if (t1>t2){
        		System.out.println("\tElegimos aleatoriedad sesgada que tiene distancia más baja.");
        		caleat = true;
        	}else{
        		System.out.println("\tElegimos aleatoriedad uniforme que tiene distancia más baja.");
        		caleat = false;
        	}
        }else{
        	System.out.println("Utilizaremos muestras con una aleatoriedad sesgada.");
        	caleat=true;
        }
        
        BusquedaLocal bl;
        Camino sol,best;
        
        if (greedy){
        	System.out.println("\n#########################################");
        	System.out.println("Analizaremos como funciona el Greedy: ("+loops+" vueltas)");
        	bl = new BusquedaLocal(BusquedaLocal.criterios.Greedy,T);
            T.resetLlamadas();
            long tI, tM=0;
            double dm=0.0;
            best = T.crearMuestraAleatoria(false);
            try {
				best.distancia();
			} catch (Exception e) {
			}
            System.out.println("\nCada vuelta:");
            for (int i = 0; i < loops; i++) {
            	T.resetLlamadas();
            	tI = System.currentTimeMillis();
            	sol = bl.ejecutar();
            	tM += (System.currentTimeMillis() - tI);
            	try {
					dm += sol.distancia();
					if (best.distancia()>sol.distancia()){
	            		best = sol;
	            	}
					System.out.println(i+1+"\t"+sol.imprimir());
				} catch (Exception e) {
				}
			}
            System.out.println("\n\tSe tarda una media de " + (tM / loops) + "ms para ejecutarse cada greedy.");
            System.out.println("\tLa media de distancia: " + (dm / loops));
            
            System.out.println("\nLa mejor opcion salida:");
            System.out.println("\t"+best.imprimir());
        }
        
        if (bestFirst){
        	System.out.println("\n#########################################");
        	System.out.println("Analizaremos como funciona el BestFirst: ("+loops+" vueltas)");
        	bl = new BusquedaLocal(BusquedaLocal.criterios.BestFirst,T);
            T.resetLlamadas();
            long tI, tM=0;
            double dm=0.0;
            best = T.crearMuestraAleatoria(false);
            try {
				best.distancia();
			} catch (Exception e) {
			}
            System.out.println("\nCada vuelta:");
            for (int i = 0; i < loops; i++) {
            	T.resetLlamadas();
            	tI = System.currentTimeMillis();
            	sol = bl.ejecutar();
            	tM += (System.currentTimeMillis() - tI);
            	try {
					dm += sol.distancia();
					if (best.distancia()>sol.distancia()){
	            		best = sol;
	            	}
					System.out.println(i+1+"\t"+sol.imprimir());
				} catch (Exception e) {
				}
			}
            System.out.println("\n\tSe tarda una media de " + (tM / loops) + "ms para ejecutarse cada Bestfirst.");
            System.out.println("\tLa media de distancia: " + (dm / loops));
            
            System.out.println("\nLa mejor opcion salida:");
            System.out.println("\t"+best.imprimir());
        }
        
        if (genetic){
        	System.out.println("\n#########################################");
        	System.out.println("Analizaremos como funciona el GeneticAlgorithm: ("+loops+" vueltas)");
        	GeneticAlgorithm ga = new GeneticAlgorithm(T, pobMax);
            T.resetLlamadas();
            long tI, tM=0;
            double dm=0.0;
            best = T.crearMuestraAleatoria(false);
            try {
				best.distancia();
			} catch (Exception e) {
			}
            System.out.println("\nCada vuelta:");
            for (int i = 0; i < loops; i++) {
            	T.resetLlamadas();
            	tI = System.currentTimeMillis();
            	sol = ga.ejecutar();
            	tM += (System.currentTimeMillis() - tI);
            	try {
					dm += sol.distancia();
					if (best.distancia()>sol.distancia()){
	            		best = sol;
	            	}
					System.out.println(i+1+"\t"+sol.imprimir());
				} catch (Exception e) {
				}
			}
            System.out.println("\n\tSe tarda una media de " + (tM / loops) + "ms para ejecutarse cada GeneticAlgorithm.");
            System.out.println("\tLa media de distancia: " + (dm / loops));
            
            System.out.println("\nLa mejor opcion salida:");
            System.out.println("\t"+best.imprimir());
        }
        System.out.println("#########################################");
    }
} 