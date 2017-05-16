package ast.expression;

import visitor.Visitor;

public class LiteralInt extends AbstractExp {

	private int value;

	public LiteralInt(int line, int column, int value) {
		super(line, column);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "LiteralInt [value=" + value + "]";
	}

}
