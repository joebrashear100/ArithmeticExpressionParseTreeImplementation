package cmsc256;

import bridges.connect.Bridges;
import java.util.Stack;
import bridges.base.BinTreeElement;

public class Project5 {
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public static bridges.base.BinTreeElement<String> buildParseTree(String expression){
		 	System.out.println(expression);
		 	String[] tokens = expression.split("\\s+");
		 	
		 	BinTreeElement<String> node = new BinTreeElement<String>();
		 	BinTreeElement<String> tempNode = new BinTreeElement<String>();
		 	BinTreeElement<String> tempNode2 = new BinTreeElement<String>();
		 	
		 	Stack<BinTreeElement> nodeStack = new Stack<BinTreeElement>();
		 	Stack<String> stringStack = new Stack<String>();
		 	String OPERATOR = "+-/*";

		 	for (int i = 0; i < tokens.length; i++) {
		 		if(tokens[i].equals("^"))
	 				throw new IllegalArgumentException();
		 		if(tokens[i].equals("(")) {
		 			stringStack.push(tokens[i]);
		 		}
		 		else if( (!OPERATOR.contains(tokens[i])) && (!tokens[i].equals(")"))) {
		 			node = new BinTreeElement<String>(tokens[i], tokens[i]);
		 			nodeStack.push(node);
		 		}
		 		else if(OPERATOR.contains(tokens[i])) {
		 			stringStack.push(tokens[i]); 
		 		}
		 		else if(tokens[i].equals(")")) {
		 			while(!stringStack.isEmpty() && !stringStack.peek().equals("(")) {
		 				node = new BinTreeElement<String>(stringStack.peek(), stringStack.peek()); 
		 				stringStack.pop();
		 				tempNode = nodeStack.pop();
		 				tempNode2 = nodeStack.pop();
		 				
		 				node.setRight(tempNode);
		 				node.setLeft(tempNode2);
		 				nodeStack.push(node);
		 			}
		 			stringStack.pop();
		 		}
		 	}
		 	node = nodeStack.pop();
		 	return node;
	 }
	 
	 public static double evaluate(bridges.base.BinTreeElement<String> tree) {
		 if(tree == null)
			 return Double.NaN;
		 
		 if(tree.getLeft() == null && tree.getRight() == null) {
			 return Double.parseDouble(tree.getValue());
		 }
		 
		 double leftSubTree = evaluate(tree.getLeft());
		 double rightSubTree = evaluate(tree.getRight());
		 
		 if(tree.getValue().equals("+")) 
			 return leftSubTree + rightSubTree;
		 
		 else if(tree.getValue().equals("-")) 
			 return leftSubTree - rightSubTree;
		 
		 else if(tree.getValue().equals("*"))
			 return leftSubTree * rightSubTree;
		 
		 else {
			 if(rightSubTree == 0 || leftSubTree == 0) {
				 throw new ArithmeticException("Unable to divide by 0.");
			 }
			 return leftSubTree / rightSubTree;
		 }

		 
	 }
	 
	 public static String getEquation(bridges.base.BinTreeElement<String> tree) {
		 if(tree.getLeft() == null && tree.getRight() == null) {
			 return tree.getValue();
		 }
		 return "( " + getEquation(tree.getLeft()) + " " + tree.getValue() + " " + getEquation(tree.getRight()) + " )";
	 }

	 
//	 public static void main(String[] args) throws Exception {
//		 String expression = "( ( 7 + 3 ) * ( 5 - 2 ) )";
//		 BinTreeElement<String> r = new BinTreeElement<String>();
//		 Bridges bridges = new Bridges(3, "joebrashear100", "1074463099749");
//		 
//		 r = buildParseTree(expression);
//		 System.out.println(getEquation(r));
//	
//		 bridges.setDataStructure(r);
//		 bridges.visualize();
//		 
//		 
//	 }

}
