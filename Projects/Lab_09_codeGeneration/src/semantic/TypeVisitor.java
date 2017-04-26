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
import ast.statement.Statement;
import ast.statement.While;
import ast.statement.Write;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.FuncType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.Type;
import ast.type.VoidType;
import error.Err;

public class TypeVisitor extends AbstractVisitor {

	@Override
	public Object visit(DefFunc dF, Object param) {
		boolean hasReturn = false;
		for (Statement each : dF.getStatements()) {
			if ((boolean) each.accept(this, dF.getType()) == true) {
				hasReturn = true;
			}
		}
		if (dF.getType() instanceof FuncType) {
			if (!hasReturn && !(((FuncType) dF.getType()).getRetType() instanceof VoidType)) {
				new ErrorType(dF.getLine(), dF.getColumn(), "The function has not return so its wrong.");
			}
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
		exp.setType(exp.getType().parentesisCast(exp.getDinamicType()));
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "It is not posible to cast this."));
		}
		return null;
	}

	@Override
	public Object visit(StructAccess exp, Object param) {
		super.visit(exp, param);
		exp.setType(exp.getName().getType().dot(exp.getField()));
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(),
					"The access to this field of the struct is not posible."));
		}
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
		super.visit(w, param);
		w.getCondition().accept(this, param);
		w.getCondition().setType(w.getCondition().getType().logical());
		if (w.getCondition().getType() == null) {
			ErrorType err = new ErrorType(w.getLine(), w.getColumn(), "The type of the condition is invalid");
			w.getCondition().setType(err);
		}
		return false;
	}

	@Override
	public Object visit(IfElse ifElse, Object param) {

		boolean returnIf = false;
		boolean returnElse = false;

		ifElse.getCondition().accept(this, param);
		ifElse.getCondition().setType(ifElse.getCondition().getType().logical());
		if (ifElse.getCondition().getType() == null) {
			ErrorType err = new ErrorType(ifElse.getLine(), ifElse.getColumn(), "The type of the condition is invalid");
			ifElse.getCondition().setType(err);
		}
		for (Statement each : ifElse.getIfStatements()) {
			if ((boolean) each.accept(this, param) == true) {
				returnIf = true;
				break;
			}
		}
		for (Statement each : ifElse.getIfStatements()) {
			if ((boolean) each.accept(this, param) == true) {
				returnElse = true;
				break;
			}
		}
		return (returnElse && returnIf);
	}

	@Override
	public Object visit(Assignment assig, Object param) {
		super.visit(assig, param);
		if (assig.getRightExpression().getType().promotesTo(assig.getLeftExpression().getType()) == null) {
			assig.getLeftExpression().setType(new ErrorType(assig.getLeftExpression().getLine(),
					assig.getLeftExpression().getColumn(), "The assigment is not posible"));
		}

		return false;

	}

	@Override
	public Object visit(InvocationExp exp, Object param) {
		super.visit(exp, param);
		List<Type> types = new ArrayList<>();
		for (Expression each : exp.getParameters()) {
			types.add(each.getType());
		}
		exp.setType(exp.getDefinition().getType().parentesis(types));
		if (exp.getType() == null) {
			exp.setType(new ErrorType(exp.getLine(), exp.getColumn(), "The invocation is not valid"));
		}

		return null;
	}

	@Override
	public Object visit(InvocationSt cF, Object param) {
		super.visit(cF, param);
		List<Type> types = new ArrayList<>();
		for (Expression each : cF.getParameters()) {
			types.add(each.getType());
		}
		if ((cF.getDefinition().getType().parentesis(types)) == null) {
			new ErrorType(cF.getLine(), cF.getColumn(), "The invocation is not valid");
		}

		return false;
	}

	@Override
	public Object visit(Read read, Object param) {
		super.visit(read, param);
		return false;
	}

	@Override
	public Object visit(Return ret, Object param) {
		super.visit(ret, param);

		if (ret.getExpression().getType().promotesTo(((FuncType) param).getRetType()) == null) {
			ret.getExpression().setType(
					new ErrorType(ret.getLine(), ret.getColumn(), "The return type is not valid for this function"));
		}

		return true;
	}

	@Override
	public Object visit(Write write, Object param) {
		for (Expression each : write.getExpressions()) {
			each.accept(this, param);
			if (!each.getType().isPrimitive()) {
				new Err(each.getLine(), each.getColumn(), "This expression can't be called here.");
			}
		}
		return false;
	}

}
