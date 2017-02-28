package ast.type;

import java.util.List;

import ast.definition.Definition;

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
	public String toString() {
		return "StructType [fieldsDefinitions=" + fieldsDefinitions + "]";
	}

}
