package testingmutation;

import java.util.ArrayList;
import java.util.List;


/**
 Copyright (C) 2014  John Berlin

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see http://www.gnu.org/licenses/


@author jberlin


**/
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
