package semantic;

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
import ast.statement.Read;
import ast.type.ErrorType;
import error.Err;
import error.ErrorHandler;

public class LValueVisitor extends AbstractVisitor {

	@Override
	public Object visit(ArithmeticOperation exp, Object param) {
		exp.setLValue(false);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(ArrayAccess exp, Object param) {
		exp.setLValue(true);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(CallFunction exp, Object param) {
		exp.setLValue(false);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(CompOperation exp, Object param) {
		exp.setLValue(false);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(Cast exp, Object param) {
		exp.setLValue(false);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(LiteralChar exp, Object param) {
		exp.setLValue(false);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(LiteralInt exp, Object param) {
		exp.setLValue(false);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(LiteralReal exp, Object param) {
		exp.setLValue(false);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(LogicOperation exp, Object param) {
		exp.setLValue(false);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(StructAccess exp, Object param) {
		exp.setLValue(true);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(UnaryMinus exp, Object param) {
		exp.setLValue(false);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(UnaryNot exp, Object param) {
		exp.setLValue(false);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(Variable exp, Object param) {
		exp.setLValue(true);
		return super.visit(exp, param);
	}

	@Override
	public Object visit(Assigment assig, Object param) {
		if (!assig.getLeftExpression().islValue()) {
			ErrorHandler.getInstance().addError(new Err(new ErrorType(assig.getLine(), assig.getColumn(),
					"The left part of the assigment can't be there")));
		}
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		for (Expression exp : read.getExpressions()) {
			if (!exp.islValue()) {
				ErrorHandler.getInstance().addError(
						new Err(new ErrorType(exp.getLine(), exp.getColumn(), "This variable can't store a value")));
			}
		}
		return null;
	}
}
