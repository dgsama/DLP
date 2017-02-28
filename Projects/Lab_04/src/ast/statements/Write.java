package ast.statements;

import java.util.List;

import ast.Expression;
import ast.Statement;

public class Write implements Statement {
	private List<Expression> expressions;

	public Write(List<Expression> expressions) {
		super();
		this.expressions = expressions;
	}

	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	@Override
	public String toString() {
		return "Write [expressions=" + expressions + "]";
	}

}
