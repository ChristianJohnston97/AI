public class Runner
{
public static void main(String[] args)
    {

    	Matrix textData = new Matrix();
    	textData.printMatrix();
        System.out.print("\n");

        
        SimulatedAnnealing sa = new SimulatedAnnealing();
        sa.run(textData);


    }
 }