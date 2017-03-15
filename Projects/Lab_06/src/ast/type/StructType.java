package ast.type;

import java.util.List;

import ast.definition.Definition;
import semantic.Visitor;

public class StructType extends AbstractType {

	private List<Definition> fieldsDefinitions;

	public StructType(int line, int column, List<Definition> fieldsDefinitions) {
		super(line, column);
		this.fieldsDefinitions = fieldsDefinitions;
	}

	public List<Definition> getFieldsDefinitions() {
		return fieldsDefinitions;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public String toString() {
		return "StructType [fieldsDefinitions=" + fieldsDefinitions + "]";
	}

}
