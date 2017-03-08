package ast.expression;

public class Variable extends AbstractExp {

	private String name;

	public Variable(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

}
