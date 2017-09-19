import java.util.*;
 
public class BestFirstSearch2
{
    private PriorityQueue<Vertex> priorityQueue;
    private int numberOfNodes;
    private Matrix matrix;
    private int source;
    private int currentNode;
    private int runningTotal = 0;
    private int[] visited;
    private int evaluationNode;


    private ArrayList order; 


 
    public static final int MAX_VALUE = 999;
 
    public BestFirstSearch2()
    {
        matrix = new Matrix();
        numberOfNodes = matrix.getNumberOfCities();
        visited = new int [numberOfNodes + 1];
        this.priorityQueue = new PriorityQueue<Vertex>(numberOfNodes, new Vertex());
        order = new ArrayList();

    }
 
    public void bestFirstSearch(Matrix array)
    {
        Random rand = new Random();

        source = rand.nextInt(numberOfNodes-1) + 0;
        matrix = array;
        int destinationNode;
        int movedTo;
 
        priorityQueue.add(new Vertex(source, getNearestNeighbourHeuristic(source)));


        visited[source] = 1;

        while (!priorityQueue.isEmpty())
        {
            evaluationNode = getNodeWithMinimumHeuristicValue();
            priorityQueue.clear();
            visited[evaluationNode] = 1;
            order.add(evaluationNode+1);   
            destinationNode = 0;
            while (destinationNode < numberOfNodes)
            {
        
                Vertex vertex = new Vertex(destinationNode, getNearestNeighbourHeuristic(destinationNode));

                if ((matrix.getArray()[evaluationNode][destinationNode] != MAX_VALUE 			                                           
                      && evaluationNode != destinationNode)&& visited[destinationNode] == 0)
                {
                    priorityQueue.add(vertex);
                }
                destinationNode++;
            }
    
            if(!priorityQueue.isEmpty())
            {
                int minNode = getNodewithMinHeuristic();
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
    int element;
    element = currentNode;

    int min;
    min = matrix.getArray()[element][evaluationNode];


    for(int i=0; i<numberOfNodes-1; i++)
    {
        if(visited[i] != 1 && i != element)
        {
            min = min + matrix.getArray()[i][source];
            break;
        }     
    }

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