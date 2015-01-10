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
