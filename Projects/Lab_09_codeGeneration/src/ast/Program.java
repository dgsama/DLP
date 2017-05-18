package ast;

import java.util.List;

import ast.definition.Definition;
import visitor.Visitor;

public class Program extends AbstractASTNode {

	private List<Definition> definitions;

	public Program(int line, int column, List<Definition> definitions) {
		super(line, column);
		this.definitions = definitions;
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		String out = "";
		for (Definition each : definitions) {
			out += each.toString() + "\n";
		}
		return out;
	}

}
