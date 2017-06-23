package ast.expression.binary;

import ast.expression.AbstractExp;
import ast.expression.Expression;
import visitor.Visitor;

public class CompOperation extends AbstractExp {

	private Expression leftExpression;
	private Expression rightExpression;
	private String operator;

	public CompOperation(int line, int column, Expression leftExpression, Expression rightExpression, String operator) {
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
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return leftExpression.toString() + operator + rightExpression.toString();
	}

	public void setRightExpression(Expression condition) {
		this.rightExpression = condition;

	}

}
