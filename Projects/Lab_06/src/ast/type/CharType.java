package ast.type;

import semantic.Visitor;

public class CharType extends AbstractType {

	private static CharType instance = null;

	public CharType(int line, int column) {
		super(line, column);
	}

	public static CharType getInstance() {
		if (instance == null) {
			instance = new CharType(0, 0);
		}
		return instance;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}
