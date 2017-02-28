package ast.types;

import java.util.List;

import ast.Definition;
import ast.Type;

public class FuncType implements Type {

	private Type retType;
	private List<Definition> algo;
}
