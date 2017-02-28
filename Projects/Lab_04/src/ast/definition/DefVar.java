package ast.definition;

import ast.type.Type;

public class DefVar extends AbstractDef {

	public DefVar(int line, int column, Type type, String name) {
		super(line, column, type, name);
	}

}
