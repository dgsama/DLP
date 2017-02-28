package ast.defs;

import java.util.List;

import ast.Definition;
import ast.Statement;
import ast.Type;

public class DefFunc implements Definition {

	private String name;
	private Type type;
	private List<Definition> definitions;
	private List<Statement> statements;

	public DefFunc(String name, Type type, List<Definition> definitions, List<Statement> statements) {
		super();
		this.name = name;
		this.type = type;
		this.definitions = definitions;
		this.statements = statements;
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

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<Definition> definitions) {
		this.definitions = definitions;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	@Override
	public String toString() {
		return "DefFunc [name=" + name + ", type=" + type + ", definitions=" + definitions + ", statements="
				+ statements + "]";
	}

}
