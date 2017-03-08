package ast.type;

public class MainType extends AbstractType{

	private Type typeOf;

	public MainType(int line, int column, Type typeOf) {
		super(line, column);
		this.typeOf = typeOf;
	}

	public Type getTypeOf() {
		return typeOf;
	}

	@Override
	public String toString() {
		return "MainType [typeOf=" + typeOf + "]";
	}
	
	
	
	
}
