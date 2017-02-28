package ast.definition;

import ast.type.Type;

public class DefField extends AbstractDef {

	public DefField(int column, Type type, String name, int line) {
		super(line, column, type, name);
	}
	
	
}
