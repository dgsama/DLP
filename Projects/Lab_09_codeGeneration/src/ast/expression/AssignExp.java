package ast.expression;

import semantic.Visitor;

public class AssignExp extends AbstractExp {

	private Expression left;
	private Expression right;

	public AssignExp(int line, int column, Expression left, Expression right) {
		super(line, column);
		this.left = left;
		this.right = right;

	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}
