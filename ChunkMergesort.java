/*
 * Name: Zach White
 * Student #: V00759566
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class ChunkMergesort {
	
	public static final class Chunks {
		
		private final List<Integer> left;
		private final List<Integer> right;
		
		public Chunks(List<Integer> left, List<Integer> right) {
			this.left = left;
			this.right = right;
		}
		
		public List<Integer> left() {
			return this.left;
		}
		
		public List<Integer> right() {
			return this.right;
		}
	}
	
	private final List<Integer[]> comparisons;

	public ChunkMergesort() {
		this.comparisons = new ArrayList<Integer[]>();
	}
	
	/*
	* CHUNKMERGESORT: this is a recursive function that takes in an unsorted
	* list and returns a sorted list
	*/

	public List<Integer> chunkMergesort(List<Integer> S) throws UnsupportedOperationException {
		
		int chunks = 1;
		int x = 0;
		
		while (x < S.size()-1){
			this.comparisons.add(new Integer[]{S.get(x), S.get(x+1)});
			if (S.get(x) > S.get(x+1)){
				chunks++;
			}
			x++;
		}
		
		
		int size = S.size();
		
		List<Integer> merged = new ArrayList<Integer>();
		List<Integer> a = new ArrayList<Integer>();
		List<Integer> b = new ArrayList<Integer>();
		
		if (chunks == 1){
			return S;
		} else {
			try {
				Chunks p = chunkDivide(S, chunks);
				a = chunkMergesort(p.right);
				b = chunkMergesort(p.left);
			} catch (UnsupportedOperationException e) {
				System.out.println("Unsupported Operation");
			}
			try {
				return merged = merge(b, a);
			} catch (UnsupportedOperationException e) {
				System.out.println("Unsupported Operation");
				System.exit(1);
				return S;
			}
		}
		
	}
	
	/*
	* CHUNKDIVIDE: this function takes in a list and an integer and returns 
	*/

	public Chunks chunkDivide(List<Integer> S, int c) throws UnsupportedOperationException {
		
		int numChunks = c/2;
		int chunks = 1;
		int x = 0;
		while (chunks <= numChunks){
			this.comparisons.add(new Integer[]{S.get(x), S.get(x+1)});
			if (S.get(x) > S.get(x+1)){
				chunks++;
			}
			x++;
		}
		
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();
		
		for (int i = 0; i < S.size(); i++){
			if (i < x){
				left.add(S.get(i));
			} else {
				right.add(S.get(i));
			}
		}
		Chunks p = new Chunks(left, right);
		return p;
		
		
	}
	/*
	* MERGE: this function takes 2 chunks, sorts them and puts them into 1 single 
	* chunk
	*/

	public List<Integer> merge(List<Integer> S1, List<Integer> S2) throws UnsupportedOperationException {
		
		
		
		List<Integer> merged = new ArrayList<Integer>();
		
		int size = S1.size() + S2.size();
		int stop = 0;
		int x = 0;
		int y = 0;
		
		while (x<S1.size() && y<S2.size()){
			
			this.comparisons.add(new Integer[]{S1.get(x), S2.get(y)});
			if (S1.get(x)>=S2.get(y)){
				merged.add(S2.get(y));
				y++;
			} else if (S1.get(x)<=S2.get(y)){
				merged.add(S1.get(x));
				x++;
			}
			
		}
		
		if (x != S1.size() && merged.size()>0){
            stop = 0;
            while(x != S1.size()){
            	this.comparisons.add(new Integer[]{S1.get(x), merged.get(stop)});
            	if (S1.get(x) < merged.get(stop)){
               		merged.add(stop, S1.get(x));
                	x++;
				} else {
					merged.add(S1.get(x));
					x++;
				}
				stop++;
            }
        }
        if (y != S2.size() && merged.size()>0){
            stop = 0;
            while(y != S2.size()){
            	this.comparisons.add(new Integer[]{S2.get(y), merged.get(stop)});
            	if (S2.get(y) < merged.get(stop)){
               		merged.add(stop, S2.get(y));
                	y++;
				} else {
					merged.add(S2.get(y));
					y++;
				}
				stop++;
            }
        }
       
		return merged;
	}

	/**
	 * Writes a text file containing all the integer comparisons in order of
	 * ocurrence.
	 * 
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	public void report() throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("comparisons.txt", false));
		for (Integer[] data : this.comparisons)
			writer.append(data[0] + " " + data[1] + "\n");
		writer.close();
	}

	public static void main(String[] args) {
		Scanner s;
		if (args.length > 0) {
			try {
				s = new Scanner(new File(args[0]));
			} catch (java.io.FileNotFoundException e) {
				System.out.printf("Unable to open %s\n", args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n", args[0]);
		} else {
			s = new Scanner(System.in);
			System.out.printf("Enter a list of integers:\n");
		}
		List<Integer> inputList = new ArrayList<Integer>();

		int v;
		while (s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputList.add(v);

		s.close();
		System.out.printf("Read %d values.\n", inputList.size());

		long startTime = System.currentTimeMillis();
		
		//-----------------------start

		ChunkMergesort mergesort = new ChunkMergesort();
		
		try{
			List<Integer> sorted = mergesort.chunkMergesort(inputList);
		} catch (UnsupportedOperationException e) {
			System.out.println("Unsupported Operation");
			System.exit(1);
		}
		
		long endTime = System.currentTimeMillis();
		double totalTimeSeconds = (endTime - startTime) / 1000.0;

		System.out.printf("Total Time (seconds): %.2f\n", totalTimeSeconds);
		
		try {
			mergesort.report();
			System.out.println("Report written to comparisons.txt");
		} catch (IOException e) {
			System.out.println("Unable to write file comparisons.txt");
			return;
		}
	}

}