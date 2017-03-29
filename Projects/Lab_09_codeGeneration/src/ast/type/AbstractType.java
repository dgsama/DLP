package ast.type;

import java.util.List;

import ast.AbstractASTNode;

public abstract class AbstractType extends AbstractASTNode implements Type {

	public AbstractType(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean isLogical() {
		return false;
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
	public Type parentesis(List<Type> types) {
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

}
