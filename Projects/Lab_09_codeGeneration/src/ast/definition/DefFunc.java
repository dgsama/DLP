package ast.definition;

import java.util.List;

import ast.statement.Return;
import ast.statement.Statement;
import ast.type.FuncType;
import ast.type.Type;
import ast.type.VoidType;
import visitor.Visitor;

public class DefFunc extends AbstractDef {

	private List<Definition> definitions;
	private List<Statement> statements;

	public DefFunc(int line, int column, Type type, String name, List<Definition> definitions,
			List<Statement> statements) {
		super(line, column, type, name);
		this.definitions = definitions;
		this.statements = statements;
	}

	public List<Definition> getParams() {
		return ((FuncType) this.getType()).getParameters();
	}

	public Type getRetType() {
		return ((FuncType) this.getType()).getRetType();
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		String out = getRetType() + " " + getId() + " (";
		for (Definition def : getParams()) {
			out += def.toString() + ",";
		}
		out = out.substring(0, out.length() - 1);
		out += "){\n";
		for (Definition def : definitions) {
			out += def.toString() + ";\n";
		}
		out += "\n";
		for (Statement st : statements) {
			out += st.toString() + ";\n";
		}
		out += "\n}";
		return out;
	}

}
