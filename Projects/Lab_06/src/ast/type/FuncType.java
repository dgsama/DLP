package ast.type;

import java.util.List;

import ast.definition.Definition;
import semantic.Visitor;

public class FuncType extends AbstractType {

	private Type retType;
	private List<Definition> parameters;

	public FuncType(int line, int column, Type retType, List<Definition> parameters) {
		super(line, column);
		this.retType = retType;
		this.parameters = parameters;
	}

	public Type getRetType() {
		return retType;
	}

	public List<Definition> getParameters() {
		return parameters;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "FuncType [retType=" + retType + ", parameters=" + parameters + "]";
	}

}
