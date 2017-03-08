package ast.error;

import ast.ASTNode;

public class Err {

	private ASTNode node;
	private String errorMessage;

	public Err(ASTNode node) {
		this.node = node;
	}

	public Err(ASTNode node, String errMessage) {
		this.node = node;
		this.errorMessage = errMessage;
	}

	public ASTNode getNode() {
		return node;
	}

	public void setNode(ASTNode node) {
		this.node = node;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getLine() {
		return node.getLine();
	}

	public int getColumn() {
		return node.getColumn();
	}

}
