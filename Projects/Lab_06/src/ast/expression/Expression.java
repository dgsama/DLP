package ast.expression;

import ast.ASTNode;
import ast.type.Type;

public interface Expression extends ASTNode {

	boolean islValue();

	void setLValue(boolean lValue);

	Type getType();

	void setType(Type type);
}
