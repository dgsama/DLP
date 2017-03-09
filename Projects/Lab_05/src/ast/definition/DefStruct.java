package ast.definition;

import ast.type.Type;
import visitor.Visitor;

public class DefStruct extends AbstractDef {

	public DefStruct(int line, int column, Type type, String name) {
		super(line, column, type, name);
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}
