package testingmutation;

import java.util.ArrayList;
import java.util.List;

public class Addition {
	
	public static List<Integer> fibSequence(int upTo){
		List<Integer> sequence = new ArrayList<>();
		if(upTo == 1 || upTo == 2){
			sequence.add(1);
			return sequence;
		}
		sequence.add(0);
		sequence.add(1);
		for(int i = 2; i <= upTo; ++i){
			sequence.add(sequence.get(i-1) + sequence.get(i-2));
		}
		return sequence;
	}
	
	public static int sum(List<Integer> sumMe){
		int ret = 0;
		for(Integer i : sumMe)
			ret = ret + i;
		return ret;
	}
	
	public static int crazy(int x){
		int val = x +2;
		val = x + 2 + val / 3 + val;
		x = val + 2 + 9 * 2 + 3;
		return val;
	}

}
/*
  public static List<Integer> fibSequence(int upTo){
		List<Integer> sequence = new ArrayList<>();
		if(upTo == 1 || upTo == 2){
			sequence.add(1);
			return sequence;
		}
		sequence.add(0);
		sequence.add(1);
		for(int i = 2; i <= upTo; ++i){
			sequence.add(sequence.get(i-1) + sequence.get(i-2));
		}
		return sequence;
	}
	
	public static int sum(List<Integer> sumMe){
		int ret = 0;
		for(Integer i : sumMe)
			ret = ret + i;
		return ret;
	}
	
	public static int crazy(int x){
		int val = x +2;
		val = x + 2 + val / 3 + val;
		x = val + 2 + 9 * 2 + 3;
		return val;
	}
	*/
