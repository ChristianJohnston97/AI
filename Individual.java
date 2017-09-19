import java.util.*;
public class Individual
	{
		//Number of nodes is the number of cities 
		private int numberOfNodes;

		//Instance of the Matrix
		//This provides the 2D array of cities
    	private Matrix matrix;

    	//This is an array list of all the cities in a tour, the basis of the algorithm
    	// Ie for 8 cities
    	// [74613825]
		private ArrayList<Integer> cities = new ArrayList<Integer>();
		private int distance;

		public Individual()
		{
			matrix = new Matrix();
        	numberOfNodes = matrix.getNumberOfCities();
		}	

		public Individual(Individual individual)
		{
			this.cities = new ArrayList<Integer>(individual.getTour());
			numberOfNodes = individual.getNumberOfCities();
			matrix = individual.getArray();
		}
	    
		
		public ArrayList getTour()
		{
        	return cities;
    	}
   

    	// This generate random individual added all of the cities in a row the arrayList
    	// [12345678]
    	// Then shuffled it randomly eg [82637451] 
		public void generateRandomIndividual(int numberOfNodes, Matrix matrix )
		{

	        for (int i = 0; i < numberOfNodes; i++) 
	        {
	        	cities.add(i);
	    	}
	    	Collections.shuffle(cities);
		}

		// This got the distance of the tour 
		
		public int getDistance()
		{
        	if (distance == 0) 
        	{
            	int tourDistance = 0;
            
            for (int cityIndex=0; cityIndex < numberOfNodes; cityIndex++) 
            {
                int fromCity = cityIndex;
                int destinationCity;

                if(cityIndex+1 < numberOfNodes)
                {
                    destinationCity = cityIndex+1;
                }
                else
                {
                    destinationCity = 0;
                }

                fromCity = cities.get(fromCity);
                destinationCity = cities.get(destinationCity);
                tourDistance += matrix.getArray()[fromCity][destinationCity];
            }
            
            distance = tourDistance;
        	}
        	return distance;
    	}

	    public int tourSize() 
	    {
	        return cities.size();
	    }

	    public Matrix getArray()
	    {
	    	return matrix;
	    }

	    public int getNumberOfCities()
	    {
	    	return numberOfNodes;
	    }
			 
	}

	
