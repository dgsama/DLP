package ast.expression;

import visitor.Visitor;

public class PunteroAddressAccess extends AbstractExp {

	private Expression exp;

	public PunteroAddressAccess(int line, int column, Expression exp) {
		super(line, column);
		this.exp = exp;
	}

	public Expression getExp() {
		return exp;
	}

	public void setExp(Expression exp) {
		this.exp = exp;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}
