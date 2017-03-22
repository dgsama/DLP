package ast.expression;

import java.util.List;

import ast.definition.Definition;
import semantic.Visitor;

public class CallFunction extends AbstractExp {

	private String name;
	private List<Expression> parameters;
	private Definition definition;

	public CallFunction(int line, int column, String name, List<Expression> parameters) {
		super(line, column);
		this.name = name;
		this.parameters = parameters;
	}

	public Definition getDefinition() {
		return definition;
	}
	
	public void setDefinition(Definition definition) {
		this.definition = definition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Expression> getParameters() {
		return parameters;
	}

	public void setParameters(List<Expression> parameters) {
		this.parameters = parameters;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "CallFunction [name=" + name + ", parameters=" + parameters + "]";
	}

	

}
