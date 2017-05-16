package ast.type;

import visitor.Visitor;

public class CharType extends AbstractType {

	private static CharType instance = null;

	public CharType(int line, int column) {
		super(line, column);
	}

	public static CharType getInstance() {
		if (instance == null) {
			instance = new CharType(0, 0);
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
		if (type instanceof CharType || type instanceof IntType || type instanceof RealType
				|| type instanceof ErrorType) {
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
	public Type logical() {
		return IntType.getInstance();
	}

	@Override
	public Type logical(Type type) {
		if (type instanceof IntType || type instanceof CharType) {
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
		if (type instanceof CharType || type instanceof IntType) {
			return this;
		} else if (type instanceof ErrorType) {
			return type;
		}
		return null;
	}

}
