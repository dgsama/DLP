package codeGeneration;

import ast.definition.Definition;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.FuncType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.StructType;
import visitor.AbstractVisitor;

public class OffsetVisitor extends AbstractVisitor {

	@Override
	public Object visit(ArrayType aT, Object param) {
		return (int) aT.getTypeOf().accept(this, param) * aT.getLength();
	}

	@Override
	public Object visit(CharType cT, Object param) {
		return 1;
	}

	@Override
	public Object visit(FuncType fT, Object param) {
		return fT.getRetType().accept(this, param);
	}

	@Override
	public Object visit(IntType iT, Object param) {
		return 2;
	}

	@Override
	public Object visit(RealType rT, Object param) {
		return 4;
	}

	@Override
	public Object visit(StructType sT, Object param) {
		int total = 0;
		for (Definition each : sT.getFieldsDefinitions()) {
			total = (int) each.getType().accept(this, param);
		}
		return total;
	}

}
