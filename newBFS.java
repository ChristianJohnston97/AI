import java.util.*;
 
public class BestFirstSearch2
{
    private PriorityQueue<Vertex> priorityQueue;
    private int numberOfNodes;
    private Matrix matrix;
    private int source;
    private int currentNode;
    private int runningTotal = 0;

    private ArrayList order; 
    private List open;
    private List closed;

    private int current;
    private int minF;


//source = ThreadLocalRandom.current().nextInt(1, matrix.getNumberOfCities() + 1);

 
    public static final int MAX_VALUE = 999;
 
    public BestFirstSearch2()
    {
        matrix = new Matrix();
        numberOfNodes = matrix.getNumberOfCities();
        order = new ArrayList();
        open = new List();
        closed = new List();
    }
 
    public void bestFirstSearch(Matrix array)
    {
        source = 0;
        matrix = array;
        int evaluationNode;
        int destinationNode;
        int movedTo;
 
        open.add(source);
        //open is set of nodes to be evaluated 
        //closed is set of nodes already evaluated
        current = source;
        minF = 0;

        while(!open.isEmpty())
        {

            //current = Collections.min(open);
            //int minIndex = open.indexOf(Collections.min(open));
            open.remove(current);
            closed.add(current);

            if(open.isEmpty())
            {
                //return current;
            }

            neighbour = 0;
            while (neighbour < numberOfNodes)
            {
                if ((matrix.getArray()[current][neighbour] = MAX_VALUE 			                                           
                      | current = neighbour | closed.contain(neighbour))
                {
                    neighbour++;
                }

                if(matrix.getArray()[current][neighbour] < "shorter than what"| !open.contains(neighbour))
                    {
                        set f_cost of neighbour 
                        current =  parent of neighbour 
                        if(!open.contains(neighbour))
                        {
                            open.add(neighbour);
                        }
                    }
            }
               
        }

        System.out.print("\n");
        System.out.print(matrix.getName() + "," + "\n") ;
        System.out.print("TOURSIZE = " + matrix.getNumberOfCities() + ","  + "\n");
        System.out.print("LENGTH = " + runningTotal + "," + "\n");
        String list = Arrays.toString(order.toArray()).replace("[", "").replace("]", "");
        System.out.print(list + "\n");
    }
 


public int getNearestNeighbourHeuristic(int currentNode)
{
    int element, dst = 0, i;
    int min = Integer.MAX_VALUE;
    boolean minFlag = false;
    int distance;
    element = currentNode;
    System.out.println("Element = " + element);

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
                minFlag = false;
            }

    System.out.println("min = " + min);      

    return min;  
}
    

}
 