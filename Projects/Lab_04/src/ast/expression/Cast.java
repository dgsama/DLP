package ast.expression;

import ast.type.Type;

public class Cast extends AbstractExp {

	private Type dinamicType;
	private Expression expression;

	public Cast(int line, int column, Type dinamicType, Expression expression) {
		super(line, column);
		this.dinamicType = dinamicType;
		this.expression = expression;
	}

	public Type getDinamicType() {
		return dinamicType;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "Cast [dinamicType=" + dinamicType + ", expression=" + expression + "]";
	}

}
