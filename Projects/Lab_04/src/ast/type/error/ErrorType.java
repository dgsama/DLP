package ast.type.error;

import ast.ASTNode;
import ast.type.AbstractType;

public class ErrorType extends AbstractType {
	
	private String message;
	private ASTNode node;

	public ErrorType(int line, int column) {
		super(line, column);
	}
	
	public ErrorType(int line, int column,ASTNode node, String message){
		super(line, column);
		this.node = node;
		this.message = message;
	}

	

}
