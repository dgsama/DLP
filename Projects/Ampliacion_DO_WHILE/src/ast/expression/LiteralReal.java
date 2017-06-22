package ast.expression;

import visitor.Visitor;

public class LiteralReal extends AbstractExp {

	private double value;

	public LiteralReal(int line, int column, double value) {
		super(line, column);
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "" + value;
	}

}
