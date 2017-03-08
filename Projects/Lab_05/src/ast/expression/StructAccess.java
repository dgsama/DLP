package ast.expression;

public class StructAccess extends AbstractExp {

	private Expression name;
	private String field;
	public StructAccess(int line, int column, Expression name, String field) {
		super(line, column);
		this.name = name;
		this.field = field;
	}
	public Expression getName() {
		return name;
	}
	public String getField() {
		return field;
	}
	@Override
	public String toString() {
		return "StructAccess [name=" + name + ", field=" + field + "]";
	}
	
	

}
