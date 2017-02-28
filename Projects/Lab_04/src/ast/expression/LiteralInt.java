package ast.expression;

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
	public String toString() {
		return "LiteralInt [value=" + value + "]";
	}

}
