/**
 *	Copyright (C) 2014  Michael Soliman
 *
 *	This program is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	This program is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 *	along with this program.  If not, see http://www.gnu.org/licenses/
 */

package edu.odu.cs.cs350.yellow1.mutationgeneration;
/**
 * 
 * @author Michael
 *
 */
public interface ICommonMutationOperations {
	public void addSub();
	public void addMul();
	public void addDiv();
	
	public void subAdd();
	public void subMul();
	public void subDiv();
	
	
	public void mulAdd();
	public void mulSub();
	public void mulDiv();
	
	public void divAdd();
	public void divSub();
	public void divMul();
	
	// < <=	
	public void ltLe();		

	// < >
	public void ltGt();
	
	// < >=
	public void ltGe();	
	
	// < ==
	public void ltEqual(); 	
	
	// > <
	public void gtLt();
	
	// > <=
	public void gtle();	
	
	// > >=
	public void gtGe();		
	
	// > ==
	public void gtEqual();	
	
	// >= <
	public void geLt();		
	
	// >= >
	public void geGt();		
	
	// >= <=
	public void geLe();		
	
	// >= ==
	public void geEqual();	

	
	// <= <
	public void leLt();	
	
	// <= >
	public void leGt();		
	
	// <= >=
	public void leGe();		
	
	// <= ==
	public void leEqual();	
	
	// == <
	public void equalLt();	
	
	// == >
	public void equalGt();	
	
	// == >=
	public void equalGe();	
	
	// == <=
	public void equalLe();	
	
	public void andOr();	
	public void orAnd();
	public void removeNot();
	
	public void ifConditionTrue();
	public void ifConditionFalse();
	
	public void whileConditionTrue();
	public void whileConditionFalse();
	public void executeAll();
	
	public void whileIf();
	
	public void constantPlusOne();
	public void constantMinusOne();
	public void constantBy1001();
	public void constantBy0999();
	//public void removeElseClause();
	//public void ifWhile();
	//public void stringBlank()
	
	
}
