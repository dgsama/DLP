package ast.expression.binary;

import ast.expression.AbstractExp;
import ast.expression.Expression;

public class LogicOperation extends AbstractExp {

	private Expression leftExpression;
	private Expression rightExpression;
	private String operator;
	
	
	
	public LogicOperation(int line, int column, Expression leftExpression, Expression rightExpression,
			String operator) {
		super(line, column);
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
		this.operator = operator;
	}
	public Expression getLeftExpression() {
		return leftExpression;
	}
	public Expression getRightExpression() {
		return rightExpression;
	}
	public String getOperator() {
		return operator;
	}
	@Override
	public String toString() {
		return "LogicOperation [leftExpression=" + leftExpression + ", rightExpression=" + rightExpression
				+ ", operator=" + operator + "]";
	}
	
	

}
