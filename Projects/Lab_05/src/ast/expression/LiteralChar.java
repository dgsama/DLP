package ast.expression;

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
	public String toString() {
		return "LiteralChar [value=" + value + "]";
	}

}
