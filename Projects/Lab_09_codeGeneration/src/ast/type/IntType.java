package ast.type;

import semantic.Visitor;

public class IntType extends AbstractType {

	private static IntType instance;

	public IntType(int line, int column) {
		super(line, column);
	}

	public static IntType getInstance() {
		if (instance == null) {
			instance = new IntType(0, 0);
		}
		return instance;
	}

	@Override
	public boolean isPrimitive() {
		return true;
	}

	@Override
	public Type arithmetic(Type rightExprType) {
		if (rightExprType instanceof CharType || rightExprType instanceof IntType) {
			return this;
		} else if (rightExprType instanceof RealType || rightExprType instanceof ErrorType) {
			return rightExprType;
		}
		return null;
	}

	@Override
	public Type parentesisCast(Type type) {
		return type;
	}

	@Override
	public Type promotesTo(Type type) {
		if (type instanceof IntType) {
			return this;
		} else if (type instanceof RealType || type instanceof ErrorType) {
			return type;
		}
		return null;
	}

	@Override
	public Type comparison(Type type) {
		return type;
	}

	@Override
	public Type logical(Type type) {
		return type;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

	@Override
	public Type logical() {
		return this;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}
