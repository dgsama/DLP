package ast.type;

import semantic.Visitor;

public class IntType extends AbstractType {

	private static IntType instance;

	public IntType(int line, int column) {
		super(line, column);
	}

	public static IntType getInstance() {
		if (instance == null) {
			instance = new IntType(0, 0);
		}
		return instance;
	}

	@Override
	public boolean isLogical() {
		return true;
	}

	@Override
	public Type arithmetic() {
		return getInstance();
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}
