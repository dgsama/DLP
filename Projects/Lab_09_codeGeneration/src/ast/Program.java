package ast;

import java.util.List;

import ast.definition.Definition;
import semantic.Visitor;

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

}
