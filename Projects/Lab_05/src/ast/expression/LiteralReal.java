package ast.expression;

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
	public String toString() {
		return "LiteralReal [value=" + value + "]";
	}

}
