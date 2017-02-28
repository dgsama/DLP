package ast.defs;

import ast.Definition;
import ast.Type;

public class DefStruct implements Definition {

	private String name;
	private Type type;

	public DefStruct(String name, Type type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DefStruct [name=" + name + ", type=" + type + "]";
	}

}
