package ast.statement;

import java.util.List;

import ast.expression.Expression;
import semantic.Visitor;

public class Read extends AbstractStatement {

	private List<Expression> expressions;

	public Read(int line, int column, List<Expression> expressions) {
		super(line, column);
		this.expressions = expressions;
	}

	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "Read [expressions=" + expressions + "]";
	}

}
