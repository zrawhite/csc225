/*
 * Name: Zach White
 * Student #: V00759566
 */
import java.io.BufferedWriter;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.lang.Math;
import java.util.*;
import java.lang.*;
import java.io.PrintWriter;

public final class PQ225 {

	public static final class Heaps{

		public static ArrayList<Integer> heapArray;

		public Heaps(ArrayList<Integer> heapArray) {
			this.heapArray = heapArray;
		}

		public ArrayList<Integer> heapArray(){
			return this.heapArray;
		}
	}
	
	private static int size1;
	private static int size2;
	private static int comparisons;

	
	/**************************************************************************/
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		Random rand = new Random(System.currentTimeMillis());
		System.out.println();
		System.out.println("How many elements would you like in the Heap? (Type and then Enter)");
		size1 = s.nextInt();
		size2 = size1;
		test();
	}

	/***************************************************************************/
	public static void ranGen(int n, int low, int high){
		int k = 0;
		ArrayList<Integer> heap = new ArrayList<Integer>();
		int range = (high - low) + 1;    
		while (k < n){
			int rn = (int)(Math.random() * range) + low;
			heap.add(rn);
			k++;
		}
		Heaps p = new Heaps(heap);
	}

	/****************************************************************************/
	public static int Size(){
		return Heaps.heapArray.size();
	}

	/****************************************************************************/
	public static void makeHeap(){
		size1 = Size();
		//setRoot();
		for (int i = size1/2; i>=0; i--){
			minHeapify(i);
		}
	}

	/*****************************************************************************/
	public static void minHeapify(int i){
		
		int left = 2*i+1;
		int right = 2*i+2;
		int small = i;

		comparisons++;
		if (left<size1 && Heaps.heapArray.get(left) <= Heaps.heapArray.get(small)){
			small = left;
		}
		comparisons++;
		if (right<size1 && Heaps.heapArray.get(right) <= Heaps.heapArray.get(small)){
			small = right;
		}
		comparisons++;
		if (small != i){
			swap(small, i);
			minHeapify(small);
		}
		
	}

	/*******************************************************************************/
	public static void swap(int higher, int lower){
		Collections.swap(Heaps.heapArray, higher, lower);
	}
	
	/*******************************************************************************/
	public static void insert(int i){
		Heaps.heapArray.add(i);
		int index = Size();
		siftUp(index);
	}

	/*******************************************************************************/
	public static void siftUp(int index){
		int parent = Heaps.heapArray.get((index-2)/2);
		int i = Heaps.heapArray.get(index-1);
		if (index-1 != 0){	
			if (parent >= i){
				swap(((index-2)/2), index-1);
				siftUp(((index-2)/2)+1);
			}
		}	
	}

	/********************************************************************************/
	public static void deleteMin(){
		int size = Size();
		swap(0, size-1);
		Heaps.heapArray.remove(size-1);
		siftDown(0);
	}

	/*********************************************************************************/
	public static void siftDown(int index){

		int size = Size();
		int left = 2*index+1;
		int right = 2*index+2;
		int min;

		if (right >= size-1){
			if (left >= size-1){
				return;
			} else {
				min = left;
			}
		} else {
			if (Heaps.heapArray.get(left) <= Heaps.heapArray.get(right)){
				min = left;
			} else {
				min = right;
			}
		}
		if (Heaps.heapArray.get(index) > Heaps.heapArray.get(min)){
			swap(index, min);
			siftDown(min);
		}
	}

	/**********************************************************************************/
	public static int heapsort(){
		int size = Size();
		size1 = Size();
		while (size1 > 0){
			swap(0, size1-1);
			size1--;
			minHeapify(0);
		}
		return 1;
	}

	/***********************************************************************************/
	public static void test(){
		try{
			PrintWriter writer = new PrintWriter ("pq_test.txt");
		

		ranGen(size1, 1, 100);
		size1 = Size();
		
		System.out.println();


		/****Test 1: Correct Heap Stucture After makeHeap() is called****/
		writer.println("***************************************************************************************");
		writer.println("||Test 1 checks to see if the order of the array satifies the Minimum Heap Structure after makeHeap() is called.||");
		writer.println();
		long startTimeTest1 = System.currentTimeMillis();
		makeHeap();
		long endTimeTest1 = System.currentTimeMillis();
		
		int counttest1 = 0;
		size1 = Size();
		int numParents;
		if (size1 % 2 == 0){
			numParents = size1/2;
			for (int i = 0; i < numParents-1; i++){
				if (Heaps.heapArray.get(i*2+1) >= Heaps.heapArray.get(i) && Heaps.heapArray.get(i*2+2) >= Heaps.heapArray.get(i)){
					counttest1++;
				}
			}
			if (Heaps.heapArray.get(numParents-1) <= Heaps.heapArray.get((numParents*2)-1)){
					counttest1++;
				}
			if (counttest1 == numParents){
				writer.println("Test 1: Passed");
			} else {
				writer.println("Test 1: Failed");
			}
		} else {
			numParents = (size1-1)/2;
			for (int i = 0; i < numParents; i++){
				if (Heaps.heapArray.get(i*2+1) >= Heaps.heapArray.get(i) && Heaps.heapArray.get(i*2+2) >= Heaps.heapArray.get(i)){
					counttest1++;
				}
			}
			if (counttest1 == numParents){
				writer.println("Test 1: Passed");
			} else {
				writer.println("Test 1: Failed");
			}
		}
		writer.println("***************************************************************************************");
		writer.println();
		




		/****Test 2: Correct Heap Stucture After insert(1) is called****/
		writer.println("***************************************************************************************");
		writer.println("||Test 2 checks to see if the order of the array satifies the Minimum Heap Structure after insert(1) is called.||");
		writer.println();
		long startTimeTest2 = System.currentTimeMillis();
		insert(1);
		long endTimeTest2 = System.currentTimeMillis();
		
		int counttest2 = 0;
		size1 = Size();
		if (size1 % 2 == 0){
			numParents = size1/2;
			for (int i = 0; i < numParents-1; i++){
				if (Heaps.heapArray.get(i*2+1) >= Heaps.heapArray.get(i) && Heaps.heapArray.get(i*2+2) >= Heaps.heapArray.get(i)){
					counttest2++;
				}
			}
			if (Heaps.heapArray.get(numParents-1) <= Heaps.heapArray.get((numParents*2)-1)){
					counttest2++;
				}
			if (counttest2 == numParents){
				writer.println("Test 2: Passed");
			} else {
				writer.println("Test 2: Failed");
			}
		} else {
			numParents = (size1-1)/2;
			for (int i = 0; i < numParents; i++){
				if (Heaps.heapArray.get(i*2+1) >= Heaps.heapArray.get(i) && Heaps.heapArray.get(i*2+2) >= Heaps.heapArray.get(i)){
					counttest2++;
				}
			}
			if (counttest2 == numParents){
					writer.println("Test 2: Passed");
				} else {
					writer.println("Test 2: Failed");
				}
		}
		writer.println("***************************************************************************************");
		writer.println();
		


		
		/****Test 3: Correct Heap Stucture After deleteMin() is called****/
		writer.println("***************************************************************************************");
		writer.println("||Test 3 checks to see if the order of the array satifies the Minimum Heap Structure after deleteMin() is called.||");
		writer.println();
		long startTimeTest3 = System.currentTimeMillis();
		deleteMin();
		long endTimeTest3 = System.currentTimeMillis();
		
		int counttest3 = 0;
		size1 = Size();
		if (size1 % 2 == 0){
			numParents = size1/2;
			for (int i = 0; i < numParents-1; i++){
				if (Heaps.heapArray.get(i*2+1) >= Heaps.heapArray.get(i) && Heaps.heapArray.get(i*2+2) >= Heaps.heapArray.get(i)){
					counttest3++;
				}
			}
			if (Heaps.heapArray.get(numParents-1) <= Heaps.heapArray.get((numParents*2)-1)){
					counttest3++;
				}
			if (counttest3 == numParents){
				writer.println("Test 3: Passed");
			} else {
				writer.println("Test 3: Failed");
			}
		} else {
			numParents = (size1-1)/2;
			for (int i = 0; i < numParents; i++){
				if (Heaps.heapArray.get(i*2+1) >= Heaps.heapArray.get(i) && Heaps.heapArray.get(i*2+2) >= Heaps.heapArray.get(i)){
					counttest3++;
				}
			}
			if (counttest3 == numParents){
				writer.println("Test 3: Passed");
			} else {
				writer.println("Test 3: Failed");
			}
		}
		writer.println("***************************************************************************************");
		writer.println();




		/****************TEST 4: Checking order after calling heapsort()******************/
		writer.println("***************************************************************************************");
		writer.println("||Test 4 checks the order of the array after calling heapsort(). If it is in decending order, the test is passed.||");
		writer.println();
		long startTimeTest4 = System.currentTimeMillis();
		heapsort();
		long endTimeTest4 = System.currentTimeMillis();
		int heapSortSize = Size();
		int countHeap = 0;
		
		for (int i = 0; i < heapSortSize-1; i++){
			if (Heaps.heapArray.get(i) >= Heaps.heapArray.get(i+1)){
				countHeap++;
			}
		}
		if (countHeap == heapSortSize-1){
			writer.println("Test 4: Passed");
		} else {
			writer.println("Test 4: Failed");
		}
		writer.println("***************************************************************************************");
		writer.println();



		
		/**************Test 5: Total Time for makeHeap()********************/
		writer.println("***************************************************************************************");
		writer.println("||Test 5 determines the total time for the makeHeap() method with "+size2+" elements.||");
		double totalTimeSeconds1 = (endTimeTest1 - startTimeTest1) / 1000.0;
		writer.println();
		writer.printf("Test 5: Total Time (seconds) for makeHeap(): %.2f\n", totalTimeSeconds1);
		writer.println("***************************************************************************************");
		writer.println();



		
		/**************Test 6: Total Time for insert(1)********************/
		writer.println("***************************************************************************************");
		writer.println("||Test 6 determines the total time for the insert(1) method with "+size2+" elements.||");
		double totalTimeSeconds2 = (endTimeTest2 - startTimeTest2) / 1000.0;
		writer.println();
		writer.printf("Test 6: Total Time (seconds) for insert(1): %.2f\n", totalTimeSeconds2);
		writer.println("***************************************************************************************");
		writer.println();



		
		/**************Test 7: Total Time for deleteMin()********************/
		writer.println("***************************************************************************************");
		writer.println("||Test 7 determines the total time for the deleteMin() method with "+size2+" elements.||");
		double totalTimeSeconds3 = (endTimeTest3 - startTimeTest3) / 1000.0;
		writer.println();
		writer.printf("Test 7: Total Time (seconds) for deleteMin(): %.2f\n", totalTimeSeconds3);
		writer.println("***************************************************************************************");
		writer.println();



		
		/**************Test 8: Total Time for heapsort()********************/
		writer.println("***************************************************************************************");
		writer.println("||Test 8 determines the total time for the heapsort() method with "+size2+" elements.||");
		double totalTimeSeconds4 = (endTimeTest4 - startTimeTest4) / 1000.0;
		writer.println();
		writer.printf("Test 8: Total Time (seconds) for heapSort(): %.2f\n", totalTimeSeconds4);
		writer.println("***************************************************************************************");
		writer.println();



		
		/**************Test 9: Total Time for All Methods********************/
		writer.println("***************************************************************************************");
		writer.println("||Test 9 determines the total time for the all the methods with "+size2+" elements.||");
		double totalTimeSeconds5 = (totalTimeSeconds1+totalTimeSeconds2+totalTimeSeconds3+totalTimeSeconds4);
		writer.println();
		writer.printf("Test 9: Total Time (seconds) for All Methods: %.2f\n", totalTimeSeconds5);
		writer.println("***************************************************************************************");
		writer.println();



		
		/**************Test 10: Number of comparisons for heapsort()********/
		writer.println("***************************************************************************************");
		writer.println("||Test 10 determines the number of comparisons for the heapsort() method with "+size2+" elements.||");
		writer.println();
		writer.println("Test 10: Number of Comparisons for heapsort(): "+comparisons);
		writer.println("***************************************************************************************");
		writer.println();

		writer.close();
	} catch (java.io.FileNotFoundException e) {
			System.out.println("Unable to open file 'pq_test.txt'");
			return;
	}

	}
}















