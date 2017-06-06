package ast.type;

import visitor.Visitor;

public class RealType extends AbstractType {

	private static RealType instance;

	public RealType(int line, int column) {
		super(line, column);
	}

	public static RealType getInstance() {
		if (instance == null) {
			instance = new RealType(0, 0);
		}
		return instance;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public boolean isPrimitive() {
		return true;
	}

	@Override
	public Type arithmetic(Type type) {
		if (type instanceof RealType || type instanceof CharType || type instanceof IntType) {
			return this;
		}
		if (type instanceof ErrorType) {
			return type;
		}
		return null;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

	@Override
	public Type comparison(Type type) {
		if (type instanceof IntType || type instanceof CharType || type instanceof RealType) {
			return IntType.getInstance();
		} else if (type instanceof ErrorType) {
			return type;
		}
		return null;
	}

	@Override
	public Type parentesisCast(Type type) {
		return type;
	}

	@Override
	public Type promotesTo(Type type) {
		if (type instanceof RealType || type instanceof ErrorType) {
			return type;
		}
		return null;
	}

	@Override
	public String toString() {
		return "REAL";
	}
}
