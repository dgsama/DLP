package semantic;

import java.util.ArrayList;
import java.util.List;

import ast.definition.DefFunc;
import ast.expression.ArrayAccess;
import ast.expression.Cast;
import ast.expression.Expression;
import ast.expression.InvocationExp;
import ast.expression.LiteralChar;
import ast.expression.LiteralInt;
import ast.expression.LiteralReal;
import ast.expression.Variable;
import ast.expression.binary.ArithmeticOperation;
import ast.expression.binary.CompOperation;
import ast.expression.binary.LogicOperation;
import ast.expression.unary.UnaryMinus;
import ast.expression.unary.UnaryNot;
import ast.statement.Assignment;
import ast.statement.IfElse;
import ast.statement.Statement;
import ast.statement.While;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.Type;

public class TypeVisitor extends AbstractVisitor {

	@Override
	public Object visit(DefFunc dF, Object param) {
		for (Statement each : dF.getStatements()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(ArithmeticOperation exp, Object param) {
		super.visit(exp, param);
		exp.setType(exp.getLeftExpression().getType().arithmetic(exp.getRightExpression().getType()));
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "The arithmetic operation is not posible."));
		}
		return null;
	}

	@Override
	public Object visit(CompOperation exp, Object param) {
		super.visit(exp, param);
		exp.setType(exp.getLeftExpression().getType().comparison(exp.getRightExpression().getType()));
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "The comparison is not posible."));
		}
		return null;
	}

	@Override
	public Object visit(LogicOperation exp, Object param) {
		super.visit(exp, param);
		exp.setType(exp.getLeftExpression().getType().logical(exp.getRightExpression().getType()));
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "The logical operation is not posible."));
		}
		return null;
	}

	@Override
	public Object visit(UnaryNot exp, Object param) {
		super.visit(exp, param);
		exp.setType(exp.getExpression().getType().logical());
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "The logical operation is not posible."));
		}
		return null;
	}

	@Override
	public Object visit(UnaryMinus exp, Object param) {
		super.visit(exp, param);
		exp.setType(exp.getExpression().getType().arithmetic());
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "The arithmetic operation is not posible."));
		}
		return null;
	}

	@Override
	public Object visit(ArrayAccess exp, Object param) {
		super.visit(exp, param);
		exp.setType(exp.getName().getType().squareBrackets(exp.getIndex().getType()));
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "The access to this array is not posible."));
		}
		return null;
	}
	
	@Override
	public Object visit(Cast exp, Object param) {
		super.visit(exp, param);
		return null;
	}

	@Override
	public Object visit(LiteralChar exp, Object param) {
		super.visit(exp, param);
		exp.setType(CharType.getInstance());
		return null;
	}

	@Override
	public Object visit(LiteralInt exp, Object param) {
		super.visit(exp, param);
		exp.setType(IntType.getInstance());
		return null;
	}

	@Override
	public Object visit(LiteralReal exp, Object param) {
		super.visit(exp, param);
		exp.setType(RealType.getInstance());
		return null;
	}

	@Override
	public Object visit(Variable exp, Object param) {
		super.visit(exp, param);
		exp.setType(exp.getDefinition().getType());
		return null;
	}

	@Override
	public Object visit(While w, Object param) {
		super.visit(w, param);
		if (w.getCondition().getType().isLogical()) {
			ErrorType err = new ErrorType(w.getLine(), w.getColumn(), "The type of the condition is invalid");
			w.getCondition().setType(err);
		}
		return null;
	}

	@Override
	public Object visit(IfElse ifElse, Object param) {
		super.visit(ifElse, param);
		if (ifElse.getCondition().getType().isLogical()) {
			ErrorType err = new ErrorType(ifElse.getLine(), ifElse.getColumn(), "The type of the condition is invalid");
			ifElse.getCondition().setType(err);
		}
		return null;
	}

	@Override
	public Object visit(Assignment assig, Object param) {
		super.visit(assig, param);
		if (assig.getLeftExpression().getType() == null) {
			assig.getLeftExpression().setType(new ErrorType(assig.getLeftExpression().getLine(),
					assig.getLeftExpression().getColumn(), "hola soy un error de asignacion"));
		}

		return null;

	}

	@Override
	public Object visit(InvocationExp exp, Object param) {
		super.visit(exp, param);
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
