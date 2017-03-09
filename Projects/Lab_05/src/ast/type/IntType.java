package ast.type;

import visitor.Visitor;

public class IntType extends AbstractType {

	private static IntType instance;

	public IntType(int line, int column) {
		super(line, column);
		// TODO Auto-generated constructor stub
	}

	public static IntType getInstance() {
		if (instance == null) {
			instance = new IntType(0, 0);
		}
		return instance;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}
