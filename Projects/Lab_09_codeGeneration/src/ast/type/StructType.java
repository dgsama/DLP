package ast.type;

import java.util.List;

import ast.definition.Definition;
import semantic.Visitor;

public class StructType extends AbstractType {

	private List<Definition> fieldsDefinitions;
	private Definition definition;
	private String ID;

	public StructType(int line, int column, List<Definition> fieldsDefinitions, String ID) {
		super(line, column);
		this.fieldsDefinitions = fieldsDefinitions;
		this.ID = ID;
	}

	public String getID() {
		return ID;
	}

	public List<Definition> getFieldsDefinitions() {
		return fieldsDefinitions;
	}

	public Definition getDefinition() {
		return definition;
	}

	public void setDefinition(Definition definition) {
		this.definition = definition;
	}

	@Override
	public Type dot(String fieldName) {
		for (Definition var : fieldsDefinitions) {
			if (fieldName.equals(var.getId())) {
				return var.getType();
			}
		}
		return null;
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
