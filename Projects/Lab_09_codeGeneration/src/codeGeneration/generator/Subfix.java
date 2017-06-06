package codeGeneration.generator;

public enum Subfix {

	CHARACTER("b"), INTEGER("i"), REAL("f");

	private final String subfix;

	private Subfix(String subfix) {
		this.subfix = subfix;
	}

	@Override
	public String toString() {
		return subfix;
	}

}
