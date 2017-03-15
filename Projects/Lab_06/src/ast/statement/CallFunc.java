package ast.statement;

import java.util.List;

import ast.expression.Expression;
import semantic.Visitor;

public class CallFunc extends AbstractStatement {

	private String name;
	private List<Expression> parameters;

	public CallFunc(int line, int column, String name, List<Expression> parameters) {
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

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "CallFunc [name=" + name + ", parameters=" + parameters + "]";
	}

}
