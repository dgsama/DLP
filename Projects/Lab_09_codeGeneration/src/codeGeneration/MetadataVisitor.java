package codeGeneration;

import ast.definition.DefFunc;
import ast.definition.DefVar;
import ast.definition.Definition;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.FuncType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.StructType;
import ast.type.VoidType;
import codeGeneration.generator.CodeGenerator;
import visitor.AbstractVisitor;

public class MetadataVisitor extends AbstractVisitor {

	private CodeGenerator cg;

	public MetadataVisitor(CodeGenerator codeGenerator) {
		this.cg = codeGenerator;
	}

	@Override
	public Object visit(DefFunc functionDef, Object param) {
		cg.metaFunction(functionDef.getId());
		for (Definition def : functionDef.getParams()) {
			cg.metaParam(def.getId(), (String) def.getType().accept(this, param));
		}
		cg.metaReturn((String) functionDef.getType().accept(this, param));
		for (Definition def : functionDef.getDefinitions()) {
			cg.metaLocal(def.getId(), (String) def.getType().accept(this, param));
		}
		return null;
	}

	@Override
	public Object visit(DefVar variableDef, Object param) {
		cg.metaVariable(variableDef.getId(), (String) variableDef.getType().accept(this, param));
		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {
		return arrayType.getLength() + "*" + arrayType.getTypeOf().accept(this, param);
	}

	@Override
	public Object visit(CharType charType, Object param) {
		return "byte";
	}

	@Override
	public Object visit(ErrorType errorType, Object param) {
		return null;
	}

	@Override
	public Object visit(FuncType functionType, Object param) {
		return functionType.getRetType().accept(this, param);
	}

	@Override
	public Object visit(IntType intType, Object param) {
		return "int";
	}

	@Override
	public Object visit(RealType realType, Object param) {
		return "float";
	}

	@Override
	public Object visit(StructType structType, Object param) {
		String fields = "\n" + structType.getID() + "\n";

		for (Definition field : structType.getFieldsDefinitions()) {
			fields += field.getId() + ":" + field.getType().accept(this, param) + "\n";
		}
		cg.metaStruct(structType.getID(), fields);

		return fields;
	}

	@Override
	public Object visit(VoidType voidType, Object param) {
		return "void";
	}
}