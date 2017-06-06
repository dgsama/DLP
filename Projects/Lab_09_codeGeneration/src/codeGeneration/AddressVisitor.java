package codeGeneration;

import java.util.List;

import ast.definition.Definition;
import ast.expression.ArrayAccess;
import ast.expression.StructAccess;
import ast.expression.Variable;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.FuncType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.StructType;
import ast.type.VoidType;
import codeGeneration.generator.CodeGenerator;
import codeGeneration.generator.Subfix;
import visitor.AbstractVisitor;

public class AddressVisitor extends AbstractVisitor {

	private ValueVisitor value;
	private CodeGenerator cg;

	public AddressVisitor(CodeGenerator codeGenerator) {
		this.cg = codeGenerator;
	}

	public void setVisitorValue(ValueVisitor value) {
		this.value = value;
	}

	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {
		arrayAccess.getIndex().accept(this, param);
		arrayAccess.getIndex().accept(value, param);
		cg.pushI((int) arrayAccess.getType().accept(this, param));
		cg.mul(Subfix.INTEGER);
		cg.add(Subfix.INTEGER);
		return null;
	}

	@Override
	public Object visit(StructAccess structAccess, Object param) {
		structAccess.getName().accept(this, param);
		cg.pushI(findFieldDef(structAccess).getOffset());
		cg.add(Subfix.INTEGER);
		return null;
	}

	private Definition findFieldDef(StructAccess structAccess) {
		List<Definition> aux = ((StructType) structAccess.getName().getType()).getFieldsDefinitions();
		for (Definition def : aux) {
			if (def.getId().equals(structAccess.getField())) {
				return def;
			}
		}
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		if (variable.getDefinition().getScope() == 0) {
			cg.pushA(variable.getDefinition().getOffset());
		} else {
			cg.pushBP();
			cg.pushI(variable.getDefinition().getOffset());
			cg.add(Subfix.INTEGER);
		}
		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {
		return arrayType.getLength() * (int) arrayType.getTypeOf().accept(this, param);
	}

	@Override
	public Object visit(CharType charType, Object param) {
		return 1;
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
		return 2;
	}

	@Override
	public Object visit(RealType realType, Object param) {
		return 4;
	}

	@Override
	public Object visit(StructType structType, Object param) {
		return getDefsSize(structType.getFieldsDefinitions(), param);

	}

	@Override
	public Object visit(VoidType voidType, Object param) {
		return 0;
	}

	private int getDefsSize(List<Definition> defs, Object param) {
		int result = 0;
		for (Definition def : defs) {
			result += (int) def.getType().accept(this, param);
		}
		return result;
	}
}
