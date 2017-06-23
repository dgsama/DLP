package codeGeneration;

import java.util.List;

import ast.definition.DefFunc;
import ast.definition.Definition;
import ast.expression.AssignmentExpr;
import ast.expression.Expression;
import ast.statement.Assignment;
import ast.statement.DoWhile;
import ast.statement.IfElse;
import ast.statement.InvocationSt;
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
import ast.type.RealType;
import ast.type.StructType;
import ast.type.Type;
import ast.type.VoidType;
import codeGeneration.generator.CodeGenerator;
import codeGeneration.generator.Subfix;
import visitor.AbstractVisitor;

public class ExecuteVisitor extends AbstractVisitor {

	private ValueVisitor value;
	private AddressVisitor address;
	private CodeGenerator cg;

	public ExecuteVisitor(CodeGenerator codeGenerator) {
		cg = codeGenerator;
		value = new ValueVisitor(codeGenerator, this);
		address = new AddressVisitor(codeGenerator);
		value.setVisitorAddress(address);
		address.setVisitorValue(value);
	}

	@Override
	public Object visit(DefFunc functionDef, Object param) {
		cg.metaLine(functionDef.getLine());
		cg.label(functionDef.getId());
		cg.enter(getDefsSize(functionDef.getDefinitions(), param));
		for (Statement stat : functionDef.getStatements()) {
			stat.accept(this, functionDef);
		}
		if (functionDef.getRetType() instanceof VoidType) {
			cg.ret((int) functionDef.getType().accept(this, param), getDefsSize(functionDef.getDefinitions(), param),
					getDefsSize(functionDef.getParams(), param));
		}
		return null;
	}

	@Override
	public Object visit(Assignment assignment, Object param) {
		cg.metaLine(assignment.getLine());
		assignment.getLeftExpression().accept(address, param);
		assignment.getRightExpression().accept(value, param);
		cg.a2b(assignment.getRightExpression().getType(), assignment.getLeftExpression().getType());
		cg.store((Subfix) assignment.getLeftExpression().getType().accept(value, param));
		return null;
	}

	@Override
	public Object visit(InvocationSt callProc, Object param) {
		cg.metaLine(callProc.getLine());
		for (Expression arg : callProc.getParameters()) {
			arg.accept(value, param);
		}
		cg.call(callProc.getName());
		Type retType = ((FuncType) callProc.getDefinition().getType()).getRetType();
		if (!(retType instanceof VoidType)) {
			cg.pop((Subfix) callProc.getDefinition().getType().accept(value, param));
		}
		return null;
	}

	@Override
	public Object visit(IfElse ifElse, Object param) {
		cg.metaLine(ifElse.getLine());
		String startElse = "startElse" + cg.getLabelCount();
		String endElse = "endElse" + cg.getLabelCount();
		ifElse.getCondition().accept(value, param);
		cg.jz(startElse);
		for (Statement stat : ifElse.getIfStatements()) {
			stat.accept(this, param);
		}
		cg.jmp(endElse);
		cg.label(startElse);
		for (Statement stat : ifElse.getElseStatements()) {
			stat.accept(this, param);
		}
		cg.label(endElse);
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		cg.metaLine(read.getLine());
		for (Expression expr : read.getExpressions()) {
			expr.accept(address, param);
			cg.in((Subfix) expr.getType().accept(value, param));
			cg.store((Subfix) expr.getType().accept(value, param));
		}
		return null;
	}

	@Override
	public Object visit(Return ret, Object param) {
		cg.metaLine(ret.getLine());
		DefFunc def = (DefFunc) param;

		if (ret.getExpression() != null) {
			ret.getExpression().accept(value, param);
		}
		cg.ret((int) def.getType().accept(this, param), getDefsSize(def.getDefinitions(), param),
				getDefsSize(def.getParams(), param));
		return null;
	}

	@Override
	public Object visit(While whileStat, Object param) {
		cg.metaLine(whileStat.getLine());
		String startWhile = "startWhile" + cg.getLabelCount();
		String endWhile = "endWhile" + cg.getLabelCount();

		cg.label(startWhile);
		whileStat.getCondition().accept(value, param);
		cg.jz(endWhile);
		for (Statement stat : whileStat.getStatements()) {
			stat.accept(this, param);
		}
		cg.jmp(startWhile);
		cg.label(endWhile);
		return null;
	}

	@Override
	public Object visit(Write write, Object param) {
		cg.metaLine(write.getLine());
		for (Expression expr : write.getExpressions()) {
			expr.accept(value, param);
			cg.out((Subfix) expr.getType().accept(value, param));
		}
		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {
		return arrayType.getLength() * (int) arrayType.getTypeOf().accept(this, param);
	}

	@Override
	public Object visit(CharType charType, Object param) {
		return 1;
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
		return 2;
	}

	@Override
	public Object visit(RealType realType, Object param) {
		return 4;
	}

	@Override
	public Object visit(StructType structType, Object param) {
		return getDefsSize(structType.getFieldsDefinitions(), param);
	}

	@Override
	public Object visit(VoidType voidType, Object param) {
		return 0;
	}

	private int getDefsSize(List<Definition> defs, Object param) {
		int result = 0;
		for (Definition def : defs) {
			result += (int) def.getType().accept(this, param);
		}
		return result;
	}

	@Override
	public Object visit(DoWhile whileStat, Object param) {

		cg.metaLine(whileStat.getLine());
		String startWhile = "startWhile" + cg.getLabelCount();

		cg.label(startWhile);
		for (Statement stat : whileStat.getStatements()) {
			stat.accept(this, param);
		}
		whileStat.getCondition().accept(value, param);
		cg.jnz(startWhile);
		return null;
	}

	@Override
	public Object visit(AssignmentExpr assignment, Object param) {
		cg.metaLine(assignment.getLine());
		assignment.getLeftExpression().accept(address, param);
		assignment.getRightExpression().accept(value, param);
		cg.a2b(assignment.getRightExpression().getType(), assignment.getLeftExpression().getType());
		cg.store((Subfix) assignment.getLeftExpression().getType().accept(value, param));
		return null;
	}

}
