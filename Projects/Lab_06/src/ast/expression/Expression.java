package ast.expression;

import ast.ASTNode;

public interface Expression extends ASTNode {

	boolean islValue();

	void setLValue(boolean lValue);
}
