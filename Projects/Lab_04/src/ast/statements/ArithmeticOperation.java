package ast.statements;

import ast.Expression;

public class ArithmeticOperation implements Expression {

	private Expression leftExpression;
	private Expression rightExpression;
	private char operator;

	public ArithmeticOperation(Expression leftExpression, char operator, Expression rightExpression) {
		super();
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
		this.operator = operator;
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

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "ArithmeticOperation [leftExpression=" + leftExpression + ", rightExpression=" + rightExpression
				+ ", operator=" + operator + "]";
	}

}
