package ast.expression.binary;

import ast.expression.AbstractExp;
import ast.expression.Expression;
import semantic.Visitor;

public class ArithmeticOperation extends AbstractExp {

	private Expression leftExpression;
	private Expression rightExpression;
	private char operator;

	public ArithmeticOperation(int line, int column, Expression leftExpression, Expression rightExpression,
			char operator) {
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

	public char getOperator() {
		return operator;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "ArithmeticOperation [leftExpression=" + leftExpression + ", rightExpression=" + rightExpression
				+ ", operator=" + operator + "]";
	}


}
