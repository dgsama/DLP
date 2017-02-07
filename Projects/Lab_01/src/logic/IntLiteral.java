package logic;

public class IntLiteral implements Expression {

	private int value;

	public IntLiteral(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "IntLiteral [value=" + value + "]";
	}

}
