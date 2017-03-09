package ast.expression;

import visitor.Visitor;

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
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

}
