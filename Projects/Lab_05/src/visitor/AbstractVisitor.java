package visitor;

import ast.Program;
import ast.definition.DefField;
import ast.definition.DefFunc;
import ast.definition.DefStruct;
import ast.definition.DefVar;
import ast.definition.Definition;
import ast.expression.ArrayAccess;
import ast.expression.CallFunction;
import ast.expression.Cast;
import ast.expression.Expression;
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
import ast.statement.Assigment;
import ast.statement.CallFunc;
import ast.statement.IfElse;
import ast.statement.Read;
import ast.statement.Return;
import ast.statement.Statement;
import ast.statement.While;
import ast.statement.Write;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.FuncType;
import ast.type.IntType;
import ast.type.MainType;
import ast.type.RealType;
import ast.type.StructType;
import ast.type.VoidType;

public class AbstractVisitor implements Visitor {

	@Override
	public Object visit(ArithmeticOperation exp, Object param) {
		exp.getLeftExpression().accept(this, param);
		exp.getRightExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(CompOperation exp, Object param) {
		exp.getLeftExpression().accept(this, param);
		exp.getRightExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(LogicOperation exp, Object param) {
		exp.getLeftExpression().accept(this, param);
		exp.getRightExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(UnaryMinus exp, Object param) {
		exp.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(UnaryNot exp, Object param) {
		exp.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(ArrayAccess exp, Object param) {
		exp.getName().accept(this, param);
		exp.getIndex().accept(this, param);
		return null;
	}

	@Override
	public Object visit(CallFunction exp, Object param) {
		for (Expression each : exp.getParameters()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(Cast exp, Object param) {
		exp.getExpression().accept(this, param);
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
		exp.getName().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Variable exp, Object param) {
		return null;
	}

	@Override
	public Object visit(DefField dF, Object param) {
		dF.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(DefFunc dF, Object param) {
		dF.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(DefStruct dS, Object param) {
		dS.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(DefVar dV, Object param) {
		dV.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Assigment assig, Object param) {
		assig.getLeftExpression().accept(this, param);
		assig.getRightExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(CallFunc cF, Object param) {
		for (Expression each : cF.getParameters()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(IfElse ifElse, Object param) {
		ifElse.getCondition().accept(this, param);
		for (Statement each : ifElse.getIfStatements()) {
			each.accept(this, param);
		}
		for (Statement each : ifElse.getElseStatements()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		for (Expression each : read.getExpressions()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(Return ret, Object param) {
		ret.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(While w, Object param) {
		w.getCondition().accept(this, param);
		for (Statement each : w.getStatements()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(Write write, Object param) {
		for (Expression each : write.getExpressions()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(ArrayType aT, Object param) {
		aT.getTypeOf().accept(this, param);
		return null;
	}

	@Override
	public Object visit(CharType cT, Object param) {

		return null;
	}

	@Override
	public Object visit(ErrorType err, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FuncType fT, Object param) {
		fT.getRetType().accept(this, param);
		for (Definition each : fT.getParameters()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(IntType iT, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MainType mT, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(RealType rT, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(StructType sT, Object param) {

		for (Definition each : sT.getFieldsDefinitions()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(Program program, Object param) {
		for (Definition each : program.getDefinitions()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(VoidType vT, Object param) {
		return null;
	}

}
