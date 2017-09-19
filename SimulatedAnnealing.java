import java.util.*;

public class SimulatedAnnealing
{
	private Matrix matrix;
	private int numberOfNodes;
	private int runningTotal;
	private ArrayList<Integer> bestTour = new ArrayList<Integer>();


	public SimulatedAnnealing()
	{
		matrix = new Matrix(); 
		numberOfNodes = matrix.getNumberOfCities();
	}

	Random random;

	// I set initial temperature and cooling rate
	// 2 cooling rates, one for linear and one for geometric cooling 

	final int START_TEMP = 1000000000;
	final double COOLING_RATE = 1.0001;
	final double RATE = 0.8;

	public void run(Matrix array)
	{
		matrix = array;
		random = new Random();
		
		int temperature = START_TEMP;

		// I reated a currentIndividual and randomised this Individual through 
		//generateRandomIndividual() method in my Individual class. 

		Individual currentIndividual = new Individual();

		currentIndividual.generateRandomIndividual(numberOfNodes, matrix);

		// I then created a current best Individual and set it to this current individual

		Individual best = currentIndividual;


		while (temperature > 0)
		{
			// GENERATE A NEW 'SUCCESSOR' INDIVIDUAL, BASED ON currentIndividual
			Individual newIndividual = new Individual(currentIndividual);

			// I then alter this new individual by swapping 2 random cities in the tour 
			// i.e [36214587] ---> [36241587]

			int i = (int) ((newIndividual.tourSize() -1) * Math.random());
			Collections.swap(newIndividual.getTour(), i, i+1);

			//Then compare this new individuals distance with the current individuals distance
			//If it is a better tour, i.e shorter. Replace it 
	
			int difference =  currentIndividual.getDistance() - newIndividual.getDistance();
			if (difference >= 0)
			{
				currentIndividual = newIndividual;
			}
			else
			{
				// If the difference is less than 0, i.e the improvement brings about a worse tour
				// We accept it if the probability given by the equation below is great than some number
				// Here I use Math.Random() to create a random probability.
				// This is the algorithms main different to the hill climbing 

				double prob = Math.exp(((difference + 0.0)) / ((temperature)));
				if (prob > Math.random())
				{
					currentIndividual = newIndividual;
				}
			}

			// If the current individual tour is better than the best tour
			// Replace it

			if(currentIndividual.getDistance() < best.getDistance())
			{
				best = currentIndividual;

			}
			// Cool the temperature according to the cooling schedule which we will look at later
			temperature -= COOLING_RATE;
		}

		// Computes the distance of the best individual
		runningTotal = best.getDistance();
		bestTour = best.getTour();
		
		// need to add one to all because the array starts from 0 and cities start from 1 
		for (int i = 0; i < bestTour.size(); i++) 
		{
    		bestTour.set(i, bestTour.get(i) + 1);
		}


		System.out.print("\n");
        System.out.print(matrix.getName() + "," + "\n") ;
        System.out.print("TOURSIZE = " + matrix.getNumberOfCities() + ","  + "\n");
        System.out.print("LENGTH = " + runningTotal + "," + "\n");
        String list = Arrays.toString(bestTour.toArray()).replace("[", "").replace("]", "");
        System.out.print(list + "\n");
	}
}
	