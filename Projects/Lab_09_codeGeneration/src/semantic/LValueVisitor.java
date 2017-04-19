package semantic;

import ast.expression.ArrayAccess;
import ast.expression.AssignExp;
import ast.expression.InvocationExp;
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
import ast.statement.Assignment;
import ast.statement.Read;
import ast.type.ErrorType;

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
	public Object visit(InvocationExp exp, Object param) {
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
	public Object visit(Assignment assig, Object param) {
		super.visit(assig, param);
		if (!assig.getLeftExpression().islValue()) {
			new ErrorType(assig.getLine(), assig.getColumn(), "The left part of the assignment can't be there");
		}

		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		for (Expression exp : read.getExpressions()) {
			exp.accept(this, param);
			if (!exp.islValue()) {
				new ErrorType(exp.getLine(), exp.getColumn(), "This variable can't store a value");
			}
		}
		return null;
	}

	/** AMPLIACIONES **/
	@Override
	public Object visit(AssignExp exp, Object param) {
		super.visit(exp, param);
		if (!exp.getLeft().islValue()) {
			new ErrorType(exp.getLine(), exp.getColumn(), "The left part of the assignment can't be there");
		}
		exp.setLValue(true);
		return null;
	}

}
