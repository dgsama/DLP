package ast.definition;

import ast.AbstractASTNode;
import ast.type.Type;

public abstract class AbstractDef extends AbstractASTNode implements Definition {

	private Type type;
	private String name;
	private int scope;
	private int offset;

	public AbstractDef(int line, int column, Type type, String name) {
		super(line, column);
		this.type = type;
		this.name = name;
	}

	@Override
	public int getScope() {
		return scope;
	}

	@Override
	public void setScope(int scope) {
		this.scope = scope;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String getId() {
		return name;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "AbstractDef [type=" + type + ", name=" + name + "]";
	}

}
