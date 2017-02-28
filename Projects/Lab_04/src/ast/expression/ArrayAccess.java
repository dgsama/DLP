package ast.expression;

public class ArrayAccess extends AbstractExp {

	private Expression name;
	private Expression index;

	public ArrayAccess(int line, int column, Expression name, Expression index) {
		super(line, column);
		this.name = name;
		this.index = index;
	}

	public Expression getName() {
		return name;
	}

	public void setName(Expression name) {
		this.name = name;
	}

	public Expression getIndex() {
		return index;
	}

	public void setIndex(Expression index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "ArrayAccess [name=" + name + ", index=" + index + "]";
	}

}
