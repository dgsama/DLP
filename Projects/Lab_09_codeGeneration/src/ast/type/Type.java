package ast.type;

import java.util.List;

import ast.ASTNode;
import ast.expression.Expression;

public interface Type extends ASTNode {

	Type arithmetic(Type rightExprType);

	Type arithmetic();

	Type comparison(Type type);

	Type logical(Type type);

	Type logical();

	Type promotesTo(Type type);

	Type parentesis(List<Expression> list); // funcType, mainType

	Type dot(String fieldName); // structType

	Type squareBrackets(Type type); // arrayType

	Type parentesisCast(Type type);

	boolean isPrimitive();

	int getTamaño();

}
