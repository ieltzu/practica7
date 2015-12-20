package criterios;

import java.util.ArrayList;

import practica7.Tsp;

public class Greedy implements Criterio{

	@Override
	public Tsp evaluar(Tsp tsp, int[] camino){
		ArrayList<Tsp> swaps = new ArrayList<Tsp>();
		tsp.crearSwaps(swaps);
		double costo= tsp.cost(camino);
		boolean mejorado=true;
		double costotmp=0.0;
		do{
			//aqui ira el algortimo greedy
			if(costotmp<costo){
				mejorado=false;
			}else{
				costo=costotmp;
			}
			
		}while(mejorado);
       /*
		// Load triangle 0-1-2 into the the first 3 slots of the greedy array
        int[] greedy = new int[this.distances.length];
        int currentDistance;
        greedy[0] = 0;
        greedy[1] = 1;
        greedy[2] = 2;
        int currentBestDistance = getDistance(0, 1) + getDistance(1, 2)
                + getDistance(2, 0);
        for (int i = 0; i < this.distances.length; i++)
            for (int j = 0; j < i; j++)
                for (int k = 0; k < j; k++)
                    if ((currentDistance = getDistance(i, j)
                            + getDistance(j, k) + getDistance(i, k)) < currentBestDistance)
                    {
                        greedy[0] = i;
                        greedy[1] = j;
                        greedy[2] = k;
                        currentBestDistance = currentDistance;
                    }
        // Try greedily to add a city that yields the smallest increase
        // in the cost of the tour
        int partialTourSize = 3;
        boolean[] visited = new boolean[this.distances.length];
        for (int i = 0; i < this.distances.length; i++)
            visited[i] = false;
        visited[greedy[0]] = true;
        visited[greedy[1]] = true;
        visited[greedy[2]] = true;
        // Main loop: keep repeating until partial tour covers all cities
        while (partialTourSize < this.distances.length)
        {
            int smallestIncrease = Integer.MAX_VALUE;
            int increase = 0;
            int bestInsertionPoint = 0;
            int bestCity = 0;
            // Scan through all cities, stopping at unvisited cities
            for (int i = 0; i < this.distances.length; i++)
            {
                if (!visited[i])
                {
                    // Consider all possible positions of inserting city i into
                    // the tour
                    // and record the smallest increase
                    for (int j = 0; j < partialTourSize; j++)
                    {
                        increase = getDistance(greedy[j], i)
                                + getDistance(i, greedy[(j + 1) % this.distances.length])
                                - getDistance(greedy[j], greedy[(j + 1)
                                        % this.distances.length]);
                        if (increase < smallestIncrease)
                        {
                            smallestIncrease = increase;
                            bestCity = i;
                            bestInsertionPoint = j;
                        } // end of if we have found a smaller increase
                    } // end of for-j
                } // end of if not visited
            } // end of for-i
              // Now we are ready to insert the bestCity at the bestInsertionPoint
            for (int j = partialTourSize - 1; j > bestInsertionPoint; j--)
                greedy[j + 1] = greedy[j];
            greedy[bestInsertionPoint + 1] = bestCity;
            visited[bestCity] = true;
            partialTourSize++;
        } // end-while
        return greedy;
        */
		return null;
		}
}	

