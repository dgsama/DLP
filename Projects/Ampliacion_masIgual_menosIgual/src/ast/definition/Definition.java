package ast.definition;

import ast.ASTNode;
import ast.type.Type;

public interface Definition extends ASTNode {

	Type getType();

	String getId();

	int getScope();

	void setScope(int scope);

	int getOffset();

	void setOffset(int offset);
}
