package practica7;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
 
public class Tsp
{
    // 2-D array to keep track of pairwise distances between cities
    private int[][] distances;
    private int numcities;
    // number of cities
 
    public Tsp(String fichero)
    {
        try {
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
    }
    
    public int getNumcities() {
		return numcities;
	}
    
    public int[] crearMuestraAleatoria(){
    	int[] citi= new int[numcities];
    	ArrayList<Integer> todas = new ArrayList<Integer>();
    	for (int i = 0; i < citi.length; i++) {
			todas.add(i);
		}
    	int i=0;
    	while(todas.size()!=0){
    		int pos = (int) Math.floor(Math.random()*todas.size());
    		System.out.println(todas.size());
    		citi[i++]=todas.get(pos);
    		todas.remove(pos);
    	}
    	return citi;
    	
    }
    // A simple getIndex method to help test the constructor
    int getIndex(String city, String state)
    {
        return 0;
    }
 
    // Print information about a city, given a city index
    void printCityInfo(int index)
    {
       
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
 
//    void TSP(int[] R, int partialTourSize, boolean[] visited, int[] T)
//    {
//        // Base case: we have discovered a tour better than T
//        if ((partialTourSize == numCities) && (cost(R) < cost(T)))
//        {
//            System.out.println("Base case. Tour cost is " + cost(R));
//            copy(R, T);
//            return;
//        }
//        // Another base case: our partial tour is not worth completing
//        if (cost(R, partialTourSize) >= cost(T))
//            return;
//        // Recursive case: R is not complete and is currently better than T
//        // and is therefore worth completing
//        for (int i = 0; i < numCities; i++)
//        {
//            if (!visited[i])
//            {
//                // System.out.println("Appending " + i);
//                visited[i] = true;
//                R[partialTourSize++] = i;
//                TSP(R, partialTourSize, visited, T);
//                partialTourSize--;
//                visited[i] = false;
//                // System.out.println("Deleting " + i);
//            }
//        } // end of for-loop
//    } // end of TSP
 
    public double cost(int[] tour)
    {
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
 
    // Main method
    public static void main(String[] args)
    {
        int n = 15;
        Tsp T = new Tsp("dantzig42.tsp");
        //T.printMatriz();
        BusquedaLocal bl = new BusquedaLocal(BusquedaLocal.criterios.Greedy,T);
        int [] sol = bl.ejecutar();
        for (int i = 0; i < sol.length; i++) {
        	System.out.print(sol[i]+", ");
		}
        System.out.println("\nY el costo:"+ T.cost(sol));
    }
} 