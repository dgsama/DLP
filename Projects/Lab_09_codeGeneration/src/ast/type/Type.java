package ast.type;

import java.util.List;

import ast.ASTNode;

public interface Type extends ASTNode {

	Type arithmetic(Type type);

	Type arithmetic();

	Type comparison(Type type);

	Type logical(Type type);

	Type logical();

	Type promotesTo(Type type);

	Type parentesis(List<Type> types);

	Type dot(String fieldName);

	Type squareBrackets(Type type);

	Type parentesisCast(Type type);

	boolean isPrimitive();

}
