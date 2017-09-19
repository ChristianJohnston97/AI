import java.util.*;
import java.io.*;


public class Matrix
{
    


	int[][] textData;
	int size;
    String name;
	
    public Matrix()
    {
        createArray();
    }	

	public int[][] createArray()
    {
        String string2 = null;

	    try{
        Scanner scanner = new Scanner( new File("AISearchtestcase.txt") );
		String text = scanner.useDelimiter("\\A").next();
        int firstComma = text.indexOf(",");
        name = text.substring(0, firstComma);
		int firstEquals = text.indexOf("=");
		int secondEquals = text.indexOf("=",firstEquals + 1);
		int comma = text.indexOf(",",secondEquals);
		String sizeString = text.substring(secondEquals + 1, comma);
		sizeString = sizeString.trim();
		size = Integer.parseInt(sizeString);
		string2 = text.substring(comma + 1, text.length());
		string2 = string2.replaceAll("[a-zA-Z]","");
		string2 = string2.replace(" ", "");
		string2 = string2.replace("\n", "").replace("\r", "");
		//System.out.println(string2);
		scanner.close(); 
    }

    catch(IOException e)
    {
        System.out.print(e.getMessage());
    }

    if(string2 == null)
    {
        System.out.println("Error.");
    }
    else
    {

		textData = new int[size][size];

		int pointer = 0;

		for(int i=0; i < size; i++)
	        {
	            for(int j=1; j < size-i; j++)
	            {
	            	int nextInt = string2.indexOf(",", pointer);

	            	if (nextInt < 0)
	            	{
	            		nextInt = string2.length();
	            	}
	            	
	            	String string3 = string2.substring(pointer, nextInt);
	            	int number = Integer.parseInt(string3);
	            	textData[i][j+i] = number;
	           		textData[j+i][i] = number;
	            	if(nextInt != string2.length())
	            	{
	            	pointer = nextInt + 1;
	            	}
	            }
        	}
        }
        return textData;
        
    }

    public void printMatrix()
    {
    	for(int i=0; i < textData.length; i++)
        {
            for(int j=0; j < textData.length; j++)
            {
            	String entry = textData[i][j] + "";

            if(entry.length() == 1)
            	{
            		System.out.print(entry + "    ");
            	}
            else 
            {
            	System.out.print(entry + "   ");
            }

            }
            System.out.println();
        }

    }


    public int[][] getArray()
    {
    	return textData;
    }

    public int getNumberOfCities()
    {
    	return size;
    }

    public String getName()
    {   
        return name;
    }


}

