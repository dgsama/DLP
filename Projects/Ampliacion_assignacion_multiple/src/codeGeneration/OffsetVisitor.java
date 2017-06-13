package codeGeneration;

import ast.Program;
import ast.definition.DefField;
import ast.definition.DefFunc;
import ast.definition.DefVar;
import ast.definition.Definition;
import ast.expression.ArrayAccess;
import ast.expression.AssignmentExpr;
import ast.expression.Cast;
import ast.expression.InvocationExp;
import ast.expression.LiteralChar;
import ast.expression.LiteralInt;
import ast.expression.LiteralReal;
import ast.expression.StructAccess;
import ast.expression.Variable;
import ast.expression.binary.ArithmeticOperation;
import ast.expression.binary.CompOperation;
import ast.expression.binary.LogicOperation;
import ast.expression.unary.UnaryMinus;
import ast.expression.unary.UnaryNot;
import ast.statement.Assignment;
import ast.statement.IfElse;
import ast.statement.InvocationSt;
import ast.statement.Read;
import ast.statement.Return;
import ast.statement.While;
import ast.statement.Write;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.FuncType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.StructType;
import ast.type.VoidType;
import visitor.AbstractVisitor;

public class OffsetVisitor extends AbstractVisitor {

	public OffsetVisitor() {
	}

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
		int paramOffset;
		Definition parameter;

		paramOffset = 4;
		for (int i = (fT.getParameters().size() - 1); i >= 0; i--) {
			parameter = fT.getParameters().get(i);
			parameter.setOffset(paramOffset);
			paramOffset += (int) parameter.accept(this, paramOffset);
		}

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
			each.setOffset(total + (int) param);
			total += (int) each.getType().accept(this, each.getOffset());
		}
		return total;
	}

	@Override
	public Object visit(VoidType vT, Object param) {
		return 0;
	}

	@Override
	public Object visit(DefFunc dF, Object param) {
		int localOffset = 0;

		for (Definition each : dF.getDefinitions()) {
			localOffset -= (int) each.accept(this, localOffset);
			each.setOffset(localOffset);
		}
		return dF.getType().accept(this, param);
	}

	@Override
	public Object visit(DefVar dV, Object param) {
		return dV.getType().accept(this, param);
	}

	@Override
	public Object visit(Program program, Object param) {
		int offset = 0;
		for (Definition each : program.getDefinitions()) {
			each.setOffset(offset);
			offset += (int) each.accept(this, offset);
		}
		return null;
	}

	@Override
	public Object visit(DefField dF, Object param) {
		return null;
	}

	@Override
	public Object visit(ArithmeticOperation exp, Object param) {
		return null;
	}

	@Override
	public Object visit(CompOperation exp, Object param) {
		return null;
	}

	@Override
	public Object visit(LogicOperation exp, Object param) {
		return null;
	}

	@Override
	public Object visit(UnaryMinus exp, Object param) {
		return null;
	}

	@Override
	public Object visit(UnaryNot exp, Object param) {
		return null;
	}

	@Override
	public Object visit(ArrayAccess exp, Object param) {
		return null;
	}

	@Override
	public Object visit(InvocationExp exp, Object param) {
		return null;
	}

	@Override
	public Object visit(Cast exp, Object param) {
		return null;
	}

	@Override
	public Object visit(LiteralChar exp, Object param) {
		return null;
	}

	@Override
	public Object visit(LiteralInt exp, Object param) {
		return null;
	}

	@Override
	public Object visit(LiteralReal exp, Object param) {
		return null;
	}

	@Override
	public Object visit(StructAccess exp, Object param) {
		return null;
	}

	@Override
	public Object visit(Variable exp, Object param) {
		return null;
	}

	@Override
	public Object visit(Assignment assig, Object param) {
		return null;
	}

	@Override
	public Object visit(InvocationSt cF, Object param) {
		return null;
	}

	@Override
	public Object visit(IfElse ifElse, Object param) {
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		return null;
	}

	@Override
	public Object visit(Return ret, Object param) {
		return null;
	}

	@Override
	public Object visit(While w, Object param) {
		return null;
	}

	@Override
	public Object visit(Write write, Object param) {
		return null;
	}

	@Override
	public Object visit(ErrorType err, Object param) {
		return null;
	}

	// AMPLIACION

	@Override
	public Object visit(AssignmentExpr assig, Object param) {
		return null;
	}

}
