package ast.definition;

import ast.AbstractASTNode;
import ast.type.Type;

public abstract class AbstractDef extends AbstractASTNode implements Definition {

	private Type type;
	private String name;

	public AbstractDef(int line, int column, Type type, String name) {
		super(line, column);
		this.type = type;
		this.name = name;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String getIdent() {
		return name;
	}

	@Override
	public String toString() {
		return "AbstractDef [type=" + type + ", name=" + name + "]";
	}

}
