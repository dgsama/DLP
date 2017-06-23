package ast.type;

import visitor.Visitor;

public class ArrayType extends AbstractType {

	private Type typeOf;
	private int length;

	public ArrayType(int line, int column, Type typeOf, int length) {
		super(line, column);
		this.typeOf = typeOf;
		this.length = length;
	}

	public Type getTypeOf() {
		return typeOf;
	}

	public void setTypeOf(Type typeOf) {
		this.typeOf = typeOf;
	}

	public int getLength() {
		return length;
	}

	@Override
	public Type squareBrackets(Type type) {
		if (type.promotesTo(IntType.getInstance()) == null) {
			return null;
		}
		return this.typeOf;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "ArrayType [typeOf=" + typeOf + ", length=" + length + "]";
	}
	@Override
	public int getSize() {
		return typeOf.getSize() * length;
	}
}
