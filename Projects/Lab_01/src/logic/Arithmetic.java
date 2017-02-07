package logic;

public class Arithmetic implements Expression {

	private String operator;
	private Expression left;
	private Expression right;

	public Arithmetic(String operator, Expression left, Expression right) {
		super();
		this.operator = operator;
		this.left = left;
		this.right = right;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Arithmetic [operator=" + operator + ", left=" + left + ", right=" + right + "]";
	}

}
