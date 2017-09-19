import java.util.*;
 
public class BestFirstSearch
{
    private PriorityQueue<Vertex> priorityQueue;
    private int numberOfNodes;
    private Matrix matrix;
    private int source;
    private int currentNode;
    private int runningTotal = 0;
    private int[] visited;
    private ArrayList order; 


//source = ThreadLocalRandom.current().nextInt(1, matrix.getNumberOfCities() + 1);

 
    public static final int MAX_VALUE = 999;
 
    public BestFirstSearch()
    {
        matrix = new Matrix();
        numberOfNodes = matrix.getNumberOfCities();
        visited = new int [numberOfNodes + 1];
        this.priorityQueue = new PriorityQueue<Vertex>(numberOfNodes, new Vertex());
        order = new ArrayList();

    }
 
    public void bestFirstSearch(Matrix array)
    {
        source = 0;
        matrix = array;
        int evaluationNode;
        int destinationNode;
        int movedTo;
 
        priorityQueue.add(new Vertex(source, getNearestNeighbourHeuristic(source)));

        System.out.println("priorityQueue!!! = " + priorityQueue);

        visited[source] = 1;

        while (!priorityQueue.isEmpty())
        {
            evaluationNode = getNodeWithMinimumHeuristicValue();
            order.add(evaluationNode+1);   
            destinationNode = 0;
            while (destinationNode < numberOfNodes)
            {
                System.out.println("evaluationNode = " + evaluationNode);
                System.out.println("destinationNode = " + destinationNode);
                Vertex vertex = new Vertex(destinationNode, getNearestNeighbourHeuristic(destinationNode));

                if ((matrix.getArray()[evaluationNode][destinationNode] != MAX_VALUE 			                                           
                      && evaluationNode != destinationNode)&& visited[destinationNode] == 0)
                {
                    priorityQueue.add(vertex);
                    visited[destinationNode] = 1;
                    System.out.println("priorityQueue = " + priorityQueue);

                }
                destinationNode++;
            }
    
            if(!priorityQueue.isEmpty())
            {
                int minNode = getNodewithMinHeuristic();
                System.out.println("minNode = " + minNode);
                movedTo = matrix.getArray()[evaluationNode][minNode];
                runningTotal = runningTotal + movedTo;
            }
            else
            {
                movedTo = matrix.getArray()[evaluationNode][source];
                runningTotal = runningTotal + movedTo;
            }
            
        }

        System.out.print("\n");
        System.out.print(matrix.getName() + "," + "\n") ;
        System.out.print("TOURSIZE = " + matrix.getNumberOfCities() + ","  + "\n");
        System.out.print("LENGTH = " + runningTotal + "," + "\n");
        String list = Arrays.toString(order.toArray()).replace("[", "").replace("]", "");
        System.out.print(list + "\n");
    }
 
    private int getNodeWithMinimumHeuristicValue()
    {
        Vertex vertex = priorityQueue.remove();
        return vertex.node;
    }

    private int getNodewithMinHeuristic()
    {
        Vertex vertex = priorityQueue.peek();
        return vertex.node;
    }

    private int getNodesHeuristic()
    {
        Vertex vertex = priorityQueue.peek();
        return vertex.heuristicvalue;
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
 
class Vertex implements Comparator<Vertex>
{
    public int heuristicvalue;
    public int node;
 
    public Vertex(int node, int heuristicvalue)
    {
        this.heuristicvalue = heuristicvalue;
        this.node = node;
    }
 
    public Vertex()
    {
 
    } 

    public String toString()
    {
        return node + "";
    }
 

    @Override
    public int compare(Vertex vertex1, Vertex vertex2)
    {
        if (vertex1.heuristicvalue < vertex2.heuristicvalue)
            return -1;
        if (vertex1.heuristicvalue > vertex2.heuristicvalue)
            return 1;
        return 0;
    }


    @Override

    public boolean equals(Object obj)
    {
        if (obj instanceof Vertex)
        {
            Vertex node = (Vertex) obj;
            if (this.node == node.node)
            {
                return true;
            }
        }
        return false;
    }
}