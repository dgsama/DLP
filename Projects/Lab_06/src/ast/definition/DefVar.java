package ast.definition;

import ast.type.Type;
import semantic.Visitor;

public class DefVar extends AbstractDef {

	private int offset;

	public DefVar(int line, int column, Type type, String name) {
		super(line, column, type, name);
	}

	public int getOffset() {
		return offset;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}
