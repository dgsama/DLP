package ast.type;

import visitor.Visitor;

public class PunteroType extends AbstractType {

	private Type typeOf;

	public PunteroType(int line, int column, Type type) {
		super(line, column);
		this.typeOf = type;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	public Type getTypeOf() {
		return typeOf;
	}

	public void setTypeOf(Type typeOf) {
		this.typeOf = typeOf;
	}

	@Override
	public Type promotesTo(Type type) {
		if (type instanceof PunteroType) {
			return this;
		}
		if (type instanceof ErrorType) {
			return type;
		}
		return null;
	}
}
