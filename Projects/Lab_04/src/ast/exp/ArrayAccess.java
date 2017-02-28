package ast.exp;

import ast.Expression;
import ast.Statement;

public class ArrayAccess implements Statement {

	private Expression name;
	private Expression index;
}
