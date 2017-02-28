package ast.type;

public class RealType extends AbstractType {

	private static RealType instance;

	public RealType(int line, int column) {
		super(line, column);
	}

	public static RealType getInstance() {
		if (instance == null) {
			instance = new RealType(0, 0);
		}
		return instance;
	}
}
