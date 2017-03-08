package ast.definition;

import java.util.List;

import ast.statement.Statement;
import ast.type.Type;

public class DefFunc extends AbstractDef {

	private List<Definition> definitions;
	private List<Statement> statements;

	

	public DefFunc(int line, int column, Type type, String name, List<Definition> definitions,
			List<Statement> statements) {
		super(line, column, type, name);
		this.definitions = definitions;
		this.statements = statements;
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	@Override
	public String toString() {
		return "DefFunc [definitions=" + definitions + ", statements=" + statements + "]";
	}

}
