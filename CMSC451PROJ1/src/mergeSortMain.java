import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class mergeSortMain {
	
	
	mergeSortMain() throws UnsortedException{
		
		
				
	}
	public static void main(String[] args) throws UnsortedException, IOException {
		JFrame f = new JFrame();
		f.setTitle("data");
		String[] categories = {"size", "iter counts avg", "iter counts coeff", "iter times avg", "iter times coeff", "recur counts avg",
				"recur counts coeff", "recur times avg", "recur times coeff"};
		int[] sizes = {16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192};
		//int[] sizes1 = {10};
		//int[] sizes1 = {100};
		BenchmarkSorts bs = new BenchmarkSorts(sizes);
		mergeSortMain m = new mergeSortMain();
		for (int i = 0; i <= 100; i++) {
            long startTime = System.nanoTime();
            BenchmarkSorts warmup = new BenchmarkSorts(sizes);
            warmup.benchmark();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("warmup: " + i + "    \tTime (nanoseconds): " + duration);
        }
		
		bs.benchmark();
		for(String[] arr : bs.output) {
		System.out.println(Arrays.toString(arr));
		}
		TableModel model = new DefaultTableModel(bs.output, categories);
		JTable table = new JTable(model);
		JScrollPane jsp = new JScrollPane(table);
		f.add(jsp);
		f.setSize(1100, 500);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		StringBuilder builder = new StringBuilder();
		
		
	}

}
