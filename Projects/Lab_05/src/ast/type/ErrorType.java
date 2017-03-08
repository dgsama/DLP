package ast.type;

public class ErrorType extends AbstractType {
	
	public static ErrorType instance = null;
	
	public ErrorType(int line, int column) {
		super(line, column);
	}
	
	public static ErrorType getInstance(){
		if(instance == null){
			instance = new ErrorType(0, 0);
		}
		return instance;
	}

	@Override
	public String toString() {
		return "";
	}
	


	

}
