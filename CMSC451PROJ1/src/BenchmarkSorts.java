/*
 *  Name: Alex Hong
 *  Class: CMSC 451
 *  Professor: Prof. Potolea
 *  Due Date: 11/15/22
 *  Description: Produce 50 data sets of 10 different sizes, sort the data sets 
 *  using recursive and iterative methods and record the critical operation counts
 *  and the time elapsed for each sorting function in arrays. Store the table data in 
 *  a 2d array.
 *  
 */

import java.util.Arrays;
import java.util.Random;

public class BenchmarkSorts {
	double[] iterCountsAvg;
	double[] iterTimeAvg;
	double[] iterCoeffCount;
	double[] iterCoeffTime;
	
	double[] recurCountsAvg;
	double[] recurTimeAvg;	
	double[] recurCoeffCount;
	double[] recurCoeffTime;
	
	int[] dataSets;
	int sets = 50;
	
	double[] iterCountsData = new double[sets];
	double[] iterTimesData = new double[sets];
	double[] recurCountsData = new double[sets];
	double[] recurTimesData = new double[sets];
	
	String[][] output;
	
	Random random = new Random();
	MergeSort mergeSort = new MergeSort();
	
	public BenchmarkSorts(int[] sizes) {
		dataSets = sizes;
		iterCountsAvg = new double[sizes.length];
		iterTimeAvg = new double[sizes.length];
		iterCoeffCount = new double[sizes.length];
		iterCoeffTime = new double[sizes.length];	
		recurCountsAvg = new double[sizes.length];
		recurTimeAvg = new double[sizes.length];	
		recurCoeffCount = new double[sizes.length];
		recurCoeffTime = new double[sizes.length];
	}
	
	
	public void benchmark() throws UnsortedException {
		output = new String[dataSets.length][9];
		for (int i = 0; i < dataSets.length; i ++) { // sizes
			int[] iterData = new int[dataSets[i]];
			int[] recurData = new int[dataSets[i]];
			for (int j = 0; j < sets; j++) { // sets		
				for (int k = 0; k < dataSets[i]; k++) {
					int value = random.nextInt(dataSets[i] + 1);
					iterData[k] = value;
					recurData[k] = value;
				}
//				System.out.println("Recursive unsorted array: " + Arrays.toString(recurData));
				mergeSort.recursiveSort(recurData);
				recurTimesData[j] = mergeSort.getTime();
				recurCountsData[j] = mergeSort.getCount();
//				System.out.println("Recursive sorted array: " + Arrays.toString(recurData));
//				System.out.println("Iterative sorted array: " + Arrays.toString(iterData));
				mergeSort.iterativeSort(iterData);
				iterTimesData[j] = mergeSort.getTime();
				iterCountsData[j] = mergeSort.getCount();
//				System.out.println("Iterative sorted array: " + Arrays.toString(iterData));
//				System.out.println();
				
			}
			iterCountsAvg[i] = getAvg(iterCountsData);
            iterCoeffCount[i] = getCoefVar(iterCountsData);
            iterTimeAvg[i] = getAvg(iterTimesData);
            iterCoeffTime[i] = getCoefVar(iterTimesData);

            recurCountsAvg[i] = getAvg(recurCountsData);
            recurCoeffCount[i] = getCoefVar(recurCountsData);
            recurTimeAvg[i] = getAvg(recurTimesData);
            recurCoeffTime[i] = getCoefVar(recurTimesData);
            
            
            
            
		}
		double[] row = new double[9];
		for (int i = 0; i < dataSets.length; i++) {
			
	//		System.out.println(Arrays.toString(recurCountsAvg));
	//        System.out.println(Arrays.toString(recurCoeffCount));
	//        System.out.println(Arrays.toString(recurTimeAvg));
	//        System.out.println(Arrays.toString(recurCoeffTime));
	//        System.out.println();
	//        System.out.println(Arrays.toString(iterCountsAvg));
	//        System.out.println(Arrays.toString(iterCoeffCount));
	//        System.out.println(Arrays.toString(iterTimeAvg));
	//        System.out.println(Arrays.toString(iterCoeffTime));
		   output[i][0] = String.valueOf(dataSets[i]);
	       output[i][1] = String.valueOf(recurCountsAvg[i]);
	       output[i][2] = String.valueOf(recurCoeffCount[i]);
	       output[i][3] = String.valueOf(recurTimeAvg[i]);
	       output[i][4] = String.valueOf(recurCoeffTime[i]);
	       
	       output[i][5] = String.valueOf(iterCountsAvg[i]);
	       output[i][6] = String.valueOf(iterCoeffCount[i]);
	       output[i][7] = String.valueOf(iterTimeAvg[i]);
	       output[i][8] = String.valueOf(iterCoeffTime[i]);
	}

	}
	
	public double getAvg(double[] arr) {
		double total = 0;
		for (double i : arr) {
			total += i;
		}
		return total/arr.length;
	}
	
	private double getStdDev(double[] data) {
        double sum = 0;
        for (double aData : data) {
            sum += (aData - getAvg(data)) * (aData - getAvg(data));
        }
        return Math.sqrt(sum / (data.length - 1));
    }
	
	private double getCoefVar(double[] data) {
        return ((getStdDev(data)) / getAvg(data)) * 100;
    }
}
