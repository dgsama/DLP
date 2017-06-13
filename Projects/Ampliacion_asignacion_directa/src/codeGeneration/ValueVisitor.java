package codeGeneration;

import ast.expression.ArrayAccess;
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
import ast.statement.InvocationSt;
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

public class ValueVisitor extends AbstractVisitor {

	private AddressVisitor address;
	private CodeGenerator cg;

	public ValueVisitor(CodeGenerator codeGenerator) {
		this.cg = codeGenerator;
	}

	public void setVisitorAddress(AddressVisitor address) {
		this.address = address;
	}

	@Override
	public Object visit(ArithmeticOperation arithmetic, Object param) {
		arithmetic.getLeftExpression().accept(this, param);
		arithmetic.getRightExpression().accept(this, param);
		cg.arithmeticOperator(arithmetic.getOperator(), (Subfix) arithmetic.getType().accept(this, param));
		return null;
	}

	@Override
	public Object visit(CompOperation comparison, Object param) {
		comparison.getLeftExpression().accept(this, param);
		comparison.getRightExpression().accept(this, param);
		cg.comparisonOperator(comparison.getOperator(),
				(Subfix) comparison.getLeftExpression().getType().accept(this, param));
		return null;
	}

	@Override
	public Object visit(LogicOperation logic, Object param) {
		logic.getLeftExpression().accept(this, param);
		logic.getRightExpression().accept(this, param);
		cg.logicOperator(logic.getOperator());
		return null;
	}

	@Override
	public Object visit(LiteralChar litChar, Object param) {
		cg.pushB(litChar.getValue());
		return null;
	}

	@Override
	public Object visit(LiteralInt litInt, Object param) {
		cg.pushI(litInt.getValue());
		return null;
	}

	@Override
	public Object visit(LiteralReal litReal, Object param) {
		cg.pushF(litReal.getValue());
		return null;
	}

	/*
	 * @Override public Object visit(TernaryComparison ternaryComp, Object
	 * param) { ternaryComp.getLeftExpr().accept(this, param);
	 * ternaryComp.getCenterExpr().accept(this, param);
	 * cg.comparisonOperator(ternaryComp.getLeftOperator(), (Subfix)
	 * ternaryComp.getLeftExpr().getType().accept(this, param));
	 * ternaryComp.getCenterExpr().accept(this, param);
	 * ternaryComp.getRightExpr().accept(this, param);
	 * cg.comparisonOperator(ternaryComp.getLeftOperator(), (Subfix)
	 * ternaryComp.getLeftExpr().getType().accept(this, param)); cg.and();
	 * return null; }
	 */

	@Override
	public Object visit(UnaryNot not, Object param) {
		not.getExpression().accept(this, param);
		cg.not();
		return null;
	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object param) {
		cg.pushI(0);
		cg.a2b(IntType.getInstance(), unaryMinus.getType());
		unaryMinus.getExpression().accept(this, param);
		cg.sub((Subfix) unaryMinus.getType().accept(this, param));
		return null;
	}

	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {
		arrayAccess.accept(address, param);
		cg.load((Subfix) arrayAccess.getType().accept(this, param));
		return null;
	}

	@Override
	public Object visit(InvocationSt callFunc, Object param) {
		for (Expression arg : callFunc.getParameters()) {
			arg.accept(this, param);
		}
		cg.call(callFunc.getName());
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {
		cast.getExpression().accept(this, param);
		cg.a2b(cast.getExpression().getType(), cast.getDinamicType());
		return null;
	}

	@Override
	public Object visit(StructAccess structAccess, Object param) {
		structAccess.accept(address, param);
		cg.load((Subfix) structAccess.getType().accept(this, param));
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		variable.accept(address, param);
		cg.load((Subfix) variable.getType().accept(this, param));
		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {
		return arrayType.getTypeOf().accept(this, param);
	}

	@Override
	public Object visit(CharType charType, Object param) {
		return Subfix.CHARACTER;
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
		return Subfix.INTEGER;
	}

	@Override
	public Object visit(RealType realType, Object param) {
		return Subfix.REAL;
	}

	@Override
	public Object visit(StructType structType, Object param) {
		return null;
	}

	@Override
	public Object visit(VoidType voidType, Object param) {
		return null;
	}

}
