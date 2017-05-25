package ast.statement;

import java.util.List;

import ast.definition.Definition;
import ast.expression.Expression;
import visitor.Visitor;

public class InvocationSt extends AbstractStatement {

	private String name;
	private List<Expression> parameters;
	private Definition definition;

	public InvocationSt(int line, int column, String name, List<Expression> parameters) {
		super(line, column);
		this.name = name;
		this.parameters = parameters;
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

	public Definition getDefinition() {
		return definition;
	}

	public void setDefinition(Definition definition) {
		this.definition = definition;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		String out = "";
		out += name + "(";
		for (Expression exp : getParameters()) {
			out += exp.toString() + ",";
		}

		out = out.substring(0, out.length() - 1);
		out += ");";
		return out;
	}

}
