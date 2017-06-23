package codeGeneration;

import ast.expression.ArrayAccess;
import ast.expression.AssignmentExpr;
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
	private ExecuteVisitor execute;

	public ValueVisitor(CodeGenerator codeGenerator, ExecuteVisitor executeVisitor) {
		this.cg = codeGenerator;
		this.execute = executeVisitor;
	}

	public void setVisitorAddress(AddressVisitor address) {
		this.address = address;
	}

	@Override
	public Object visit(ArithmeticOperation arithmetic, Object param) {
		arithmetic.getLeftExpression().accept(this, param);
		cg.a2b(arithmetic.getLeftExpression().getType(), arithmetic.getType());
		arithmetic.getRightExpression().accept(this, param);
		cg.a2b(arithmetic.getRightExpression().getType(), arithmetic.getType());
		cg.arithmeticOperator(arithmetic.getOperator(), (Subfix) arithmetic.getType().accept(this, param));
		return null;
	}

	@Override
	public Object visit(CompOperation comparison, Object param) {
		comparison.getLeftExpression().accept(this, param);
		cg.a2b(comparison.getLeftExpression().getType(), comparison.getType());
		comparison.getRightExpression().accept(this, param);
		cg.a2b(comparison.getRightExpression().getType(), comparison.getType());
		cg.comparisonOperator(comparison.getOperator(),
				(Subfix) comparison.getLeftExpression().getType().accept(this, param));
		return null;
	}

	@Override
	public Object visit(LogicOperation logic, Object param) {
		logic.getLeftExpression().accept(this, param);
		cg.a2b(logic.getLeftExpression().getType(), logic.getType());
		logic.getRightExpression().accept(this, param);
		cg.a2b(logic.getRightExpression().getType(), logic.getType());
		cg.logicOperator(logic.getOperator());
		return null;
	}

	@Override
	public Object visit(LiteralChar litChar, Object param) {
		cg.pushB(litChar.getValue());
		cg.a2b(new CharType(0, 0), litChar.getType());
		return null;
	}

	@Override
	public Object visit(LiteralInt litInt, Object param) {
		cg.pushI(litInt.getValue());
		cg.a2b(new IntType(0, 0), litInt.getType());
		return null;
	}

	@Override
	public Object visit(LiteralReal litReal, Object param) {
		cg.pushF(litReal.getValue());
		cg.a2b(new RealType(0, 0), litReal.getType());
		return null;
	}

	@Override
	public Object visit(UnaryNot not, Object param) {
		not.getExpression().accept(this, param);
		cg.a2b(not.getExpression().getType(), not.getType());
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
	public Object visit(InvocationExp callFunc, Object param) {
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

	@Override
	public Object visit(AssignmentExpr assig, Object param) {
		assig.accept(execute, param);
		assig.getLeftExpression().accept(address, param);
		cg.load((Subfix) assig.getLeftExpression().getType().accept(this, param));
		return null;
	}

}
