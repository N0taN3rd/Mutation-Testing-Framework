package testingmutation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class Main {

	public static void main(String[] args){
		File file = new File(args[0]);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			for(String line; (line = br.readLine()) != null;){
				int number = Integer.parseInt(line.trim());
				List<Integer> fib = Addition.fibSequence(number);
				System.out.println(fib.toString());
				int sumOfFib = Addition.sum(fib);
				System.out.println(sumOfFib);
				int craySumOfFib = Addition.crazy(sumOfFib);
				System.out.println(craySumOfFib+"\n");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
