import java.util.*;
import java.io.*;

public class NearestNeighbour
{
//http://www.sanfoundry.com/java-program-implement-traveling-salesman-problem-using-nearest-neighbour-algorithm/

	private Matrix matrix;
	private int numberOfNodes;
    private Stack<Integer> stack;
    private int source;
    private ArrayList order; 
    private int startingNumber;
 
    public NearestNeighbour()
    {
        stack = new Stack<Integer>();
        matrix = new Matrix();
        order = new ArrayList();
    }
 

    public void tsp(Matrix array)
    {
        matrix = array;
        numberOfNodes = matrix.getNumberOfCities();
        int[] visited = new int[numberOfNodes + 1];
        visited[0] = 1;
        stack.push(0);
        int runningTotal = 0;
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        order.add(1);
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 0;
            min = Integer.MAX_VALUE;
            while (i < numberOfNodes)
            {
                if (matrix.getArray()[element][i] > 1 && visited[i] == 0)
                {
                    if (min > matrix.getArray()[element][i])
                    {
                        min = matrix.getArray()[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }


            if (minFlag)
            {
                visited[dst] = 1;
                runningTotal = runningTotal + min;
                stack.push(dst);
                System.out.println("stack push = " + stack);
                order.add((dst+1));
                minFlag = false;
                continue;
            }
            stack.pop();
            System.out.println("stack pop = " + stack);

        }
        System.out.print("\n");
        System.out.print(matrix.getName() + "," + "\n") ;
        System.out.print("TOURSIZE = " + matrix.getNumberOfCities() + ","  + "\n");
        runningTotal = runningTotal + matrix.getArray()[dst][0];
        System.out.print("LENGTH = " + runningTotal + "," + "\n");
        String list = Arrays.toString(order.toArray()).replace("[", "").replace("]", "");
        System.out.print(list + "\n");
/**
public void nN()
    {
        source = 0;
        int bestTour = 99999999999999;

        while(source < numberOfNodes)
        {
            tsp(matrix, source) = distance;

            if(distance < bestTour)
            {
                bestTour = distance;
            }
            source++;
        }
        */
    }
}