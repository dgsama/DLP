package ast.type;

import java.util.List;

import ast.AbstractASTNode;
import ast.expression.Expression;

public abstract class AbstractType extends AbstractASTNode implements Type {

	public AbstractType(int line, int column) {
		super(line, column);
	}

	@Override
	public Type arithmetic(Type type) {
		return null;
	}

	@Override
	public Type arithmetic() {
		return null;
	}

	@Override
	public Type comparison(Type type) {
		return null;
	}

	@Override
	public Type logical(Type type) {
		return null;
	}

	@Override
	public Type promotesTo(Type type) {
		return null;
	}

	@Override
	public Type parentesis(List<Expression> types) {
		return null;
	}

	@Override
	public Type dot(String fieldName) {
		return null;
	}

	@Override
	public Type squareBrackets(Type type) {
		return null;
	}

	@Override
	public Type logical() {
		return null;
	}

	@Override
	public Type parentesisCast(Type type) {
		return null;
	}

	@Override
	public boolean isPrimitive() {
		return false;
	}
	
	@Override
	public int getTamaño() {
		return 0;
	}

}
