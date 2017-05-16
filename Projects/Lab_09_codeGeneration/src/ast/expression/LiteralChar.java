package ast.expression;

import visitor.Visitor;

public class LiteralChar extends AbstractExp {

	private char value;

	public LiteralChar(int line, int column, char value) {
		super(line, column);
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "LiteralChar [value=" + value + "]";
	}

}
