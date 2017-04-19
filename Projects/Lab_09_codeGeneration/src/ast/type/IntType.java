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
	public Type arithmetic(Type type) {
	}

	@Override
	public Type arithmetic() {
		return getInstance();
	}

	@Override
	public boolean isPrimitive() {
		return true;
	}

	@Override
	public Type promotesTo(Type type) {
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}
