import java.io.*;
import java.util.*;

/*
* ID: V00759566 Zach White
*
* NAME: TripleSum.java
*
* DESCRIPTION: This program takes a file of integers and searches through to find 3 numbers 
* that add up to 225
*
* INPUT: An array A of n non-negative integers.
*
* OUTPUT: A boolean value (true	or false). If there are three indices i,j, k such that 0 â‰¤ i,j, k â‰¤
* n âˆ’ 1 and A[i] + A[j] + A[k] = 225, the output will be true. If no such indices exist,
* the output will be false. Note that i,j and k do not have to be distinct.
*/

public class TripleSum{

	public static void main(String[] args){
		 
		 Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);

		int[] array = new int[inputVector.size()];

		for (int i = 0; i < array.length; i++)
			array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);


		long startTime = System.currentTimeMillis();

		boolean tripleExists = TripleSum225(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.printf("Array %s a triple of values which add to 225.\n",tripleExists? "contains":"does not contain");
		System.out.printf("Total Time (seconds): %.2f\n",totalTimeSeconds);
		 
	}
	
	/*
	* name: TripleSum225
	* input: integer array
	* output: boolean value depending of whether an addition of 225 is found
	*/
	
	public static boolean TripleSum225(int[] A){
		/*
		int f = 0;
		int z = 0;
		
		for (int i = 0; i < A.length; i++){
			if (A[i] < 226){
				f++;
			}
		}
		
		int[] array = new int[f];
		
		for (int i = 0; i < A.length; i++){
			if (A[i] < 226){
				array[z] = A[i];
				z++;
			}
		}
		*/
		
		for (int i = 0; i<A.length-2; i++){
			
			int j = i;							// start j at the beginning of array
			int k = A.length-1;					// start k at the end of array
			
			while (k >= j){
			
				if ((A[i]+A[j]+A[k]) == 225){	//if 3 element in the array add up to 225 return true
					return true;
				}
				
				if ((A[i]+A[j]+A[k]) > 225){	
					k--;						
				} else {
					j++;
				}
			}	
		}
		
		return false;
		
	}
	
}