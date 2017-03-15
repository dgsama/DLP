package ast.type;

import semantic.Visitor;

public class RealType extends AbstractType {

	private static RealType instance;

	public RealType(int line, int column) {
		super(line, column);
	}

	public static RealType getInstance() {
		if (instance == null) {
			instance = new RealType(0, 0);
		}
		return instance;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}
