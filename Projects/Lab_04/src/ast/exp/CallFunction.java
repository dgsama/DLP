package ast.exp;

import java.util.List;

import ast.Expression;

public class CallFunction implements Expression {

	private String name;
	private List<Expression> parameters;
}
