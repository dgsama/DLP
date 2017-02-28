package ast.statements;

import ast.Expression;
import ast.Statement;

public class Assigment implements Statement {

	private Expression leftExpression;
	private Expression rightExpression;

	public Assigment(Expression leftExpression, Expression rightExpression) {
		super();
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}

	public Expression getLeftExpression() {
		return leftExpression;
	}

	public void setLeftExpression(Expression leftExpression) {
		this.leftExpression = leftExpression;
	}

	public Expression getRightExpression() {
		return rightExpression;
	}

	public void setRightExpression(Expression rightExpression) {
		this.rightExpression = rightExpression;
	}

	@Override
	public String toString() {
		return "Assigment [leftExpression=" + leftExpression + ", rightExpression=" + rightExpression + "]";
	}

}
