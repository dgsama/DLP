package ast;

import semantic.Visitor;

public interface ASTNode {

	public int getLine();

	public int getColumn();

	String toString();

	Object accept(Visitor visitor, Object param);

}
