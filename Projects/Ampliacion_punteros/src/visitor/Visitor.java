package visitor;

import ast.Program;
import ast.definition.DefField;
import ast.definition.DefFunc;
import ast.definition.DefVar;
import ast.expression.ArrayAccess;
import ast.expression.Cast;
import ast.expression.InvocationExp;
import ast.expression.LiteralChar;
import ast.expression.LiteralInt;
import ast.expression.LiteralReal;
import ast.expression.PunteroAddressAccess;
import ast.expression.PunteroValueAccess;
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
import ast.type.PunteroType;
import ast.type.RealType;
import ast.type.StructType;
import ast.type.VoidType;

public interface Visitor {

	Object visit(ArithmeticOperation aO, Object param);

	Object visit(CompOperation cO, Object param);

	Object visit(LogicOperation lO, Object param);

	Object visit(UnaryMinus uM, Object param);

	Object visit(UnaryNot uN, Object param);

	Object visit(ArrayAccess arrayAccess, Object param);

	Object visit(InvocationExp cF, Object param);

	Object visit(Cast cast, Object param);

	Object visit(LiteralChar lC, Object param);

	Object visit(LiteralInt lI, Object param);

	Object visit(LiteralReal lR, Object param);

	Object visit(StructAccess sA, Object param);

	Object visit(Variable var, Object param);

	Object visit(DefField dF, Object param);

	Object visit(DefFunc dF, Object param);

	Object visit(DefVar dV, Object param);

	Object visit(Assignment assig, Object param);

	Object visit(InvocationSt cF, Object param);

	Object visit(IfElse ifElse, Object param);

	Object visit(Read read, Object param);

	Object visit(Return ret, Object param);

	Object visit(While w, Object param);

	Object visit(Write write, Object param);

	Object visit(ArrayType aT, Object param);

	Object visit(CharType cT, Object param);

	Object visit(ErrorType err, Object param);

	Object visit(FuncType fT, Object param);

	Object visit(IntType iT, Object param);

	Object visit(RealType rT, Object param);

	Object visit(StructType sT, Object param);

	Object visit(Program program, Object param);

	Object visit(VoidType vT, Object param);

	Object visit(PunteroType punteroType, Object param);

	Object visit(PunteroValueAccess exp, Object param);

	Object visit(PunteroAddressAccess exp, Object param);

}
