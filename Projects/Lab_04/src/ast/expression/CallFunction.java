package ast.expression;

import java.util.List;

public class CallFunction extends AbstractExp {

	private String name;
	private List<Expression> parameters;

	public CallFunction(int line, int column, String name, List<Expression> parameters) {
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
	public String toString() {
		return "CallFunction [name=" + name + ", parameters=" + parameters + "]";
	}

}
