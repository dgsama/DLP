package ast.type;

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

	public int getLength() {
		return length;
	}

	@Override
	public String toString() {
		return "ArrayType [typeOf=" + typeOf + ", length=" + length + "]";
	}

}
