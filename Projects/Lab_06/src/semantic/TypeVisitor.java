package semantic;

import java.util.ArrayList;
import java.util.List;

import ast.expression.InvocationExp;
import ast.expression.Expression;
import ast.expression.LiteralChar;
import ast.expression.LiteralInt;
import ast.expression.LiteralReal;
import ast.expression.Variable;
import ast.expression.binary.ArithmeticOperation;
import ast.expression.binary.CompOperation;
import ast.expression.binary.LogicOperation;
import ast.statement.Assignment;
import ast.statement.IfElse;
import ast.statement.While;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.Type;

public class TypeVisitor extends AbstractVisitor {

	@Override
	public Object visit(ArithmeticOperation exp, Object param) {
		exp.setType(exp.getLeftExpression().getType().arithmetic(exp.getRightExpression().getType()));
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "te destruyo"));
		}
		return null;
	}

	@Override
	public Object visit(CompOperation exp, Object param) {
		exp.setType(exp.getLeftExpression().getType().comparison(exp.getRightExpression().getType()));
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "te destruyo"));
		}
		return null;
	}

	@Override
	public Object visit(LogicOperation exp, Object param) {
		exp.setType(exp.getLeftExpression().getType().logical(exp.getRightExpression().getType()));
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "te destruyo"));
		}
		return null;
	}

	@Override
	public Object visit(LiteralChar exp, Object param) {
		exp.setType(CharType.getInstance());
		return null;
	}

	@Override
	public Object visit(LiteralInt exp, Object param) {
		exp.setType(IntType.getInstance());
		return null;
	}

	@Override
	public Object visit(LiteralReal exp, Object param) {

		exp.setType(RealType.getInstance());
		return null;
	}

	@Override
	public Object visit(Variable exp, Object param) {
		exp.setType(exp.getDefinition().getType());
		return null;
	}

	@Override
	public Object visit(While w, Object param) {
		if (w.getCondition().getType().isLogical()) {
			ErrorType err = new ErrorType(w.getLine(), w.getColumn(), "The type of the condition is invalid");
			w.getCondition().setType(err);
		}
		return null;
	}

	@Override
	public Object visit(IfElse ifElse, Object param) {
		if (ifElse.getCondition().getType().isLogical()) {
			ErrorType err = new ErrorType(ifElse.getLine(), ifElse.getColumn(), "The type of the condition is invalid");
			ifElse.getCondition().setType(err);
		}
		return null;
	}

	@Override
	public Object visit(Assignment assig, Object param) {
		if (assig.getLeftExpression().getType() == null) {
			assig.getLeftExpression().setType(new ErrorType(assig.getLeftExpression().getLine(),
					assig.getLeftExpression().getColumn(), "hola soy un error de asignacion"));
		}

		return null;

	}

	@Override
	public Object visit(InvocationExp exp, Object param) {
		List<Type> types = new ArrayList<>();
		for (Expression each : exp.getParameters()) {
			types.add(each.getType());
		}
		exp.setType(exp.getType().parentesis(types));
		if (exp.getType() == null) {

		}
		return null;
	}

}
