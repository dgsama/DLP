package ast;

import java.util.List;

public class Program {

	private List<Definition> definitions;

	public Program(List<Definition> definitions) {
		super();
		this.definitions = definitions;
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<Definition> definitions) {
		this.definitions = definitions;
	}

	@Override
	public String toString() {
		return "Program [definitions=" + definitions + "]";
	}

}
