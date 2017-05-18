package ast.statement;

import ast.expression.Expression;
import visitor.Visitor;

public class Assignment extends AbstractStatement {

	private Expression leftExpression;
	private Expression rightExpression;

	public Assignment(int line, int column, Expression leftExpression, Expression rightExpression) {
		super(line, column);
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
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return leftExpression.toString() + "=" + rightExpression.toString() + ";";
	}

}
