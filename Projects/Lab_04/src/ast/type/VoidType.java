package ast.type;

public class VoidType extends AbstractType {

	private static VoidType instance;

	public VoidType(int line, int column) {
		super(line, column);
	}
	
	public static VoidType getInstance() {
		if (instance == null) {
			instance = new VoidType(0, 0);
		}
		return instance;
	}

}
