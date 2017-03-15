package semantic;

import ast.definition.DefField;
import ast.definition.DefFunc;
import ast.definition.DefStruct;
import ast.definition.DefVar;
import ast.expression.Variable;
import ast.type.ErrorType;
import semantic.symbolTable.SymbolTable;

public class IdentificationVisitor extends AbstractVisitor {

	private SymbolTable sT;

	public IdentificationVisitor() {
		sT = new SymbolTable();
	}

	@Override
	public Object visit(DefField def, Object param) {
		if (!sT.insert(def)) {
			new ErrorType(def.getLine(), def.getColumn(),
					"The field " + def.getId() + " already exists in this struct.");
		}
		super.visit(def, param);
		return null;
	}

	@Override
	public Object visit(DefFunc def, Object param) {
		if (!sT.insert(def)) {
			new ErrorType(def.getLine(), def.getColumn(),
					"The function " + def.getId() + " already exists in the program.");
		}

		sT.set();

		super.visit(def, param);
		sT.reset();

		return null;
	}

	@Override
	public Object visit(DefStruct def, Object param) {
		if (!sT.insert(def)) {
			new ErrorType(def.getLine(), def.getColumn(),
					"The variable " + def.getId() + " already exists in this scope.");
		}
		sT.set();
		super.visit(def, param);
		sT.reset();
		return null;
	}

	@Override
	public Object visit(DefVar def, Object param) {
		if (!sT.insert(def)) {
			new ErrorType(def.getLine(), def.getColumn(),
					"The variable " + def.getId() + " already exists in this scope.");
		}
		super.visit(def, param);
		return null;
	}

	@Override
	public Object visit(Variable var, Object param) {
		var.setDefinition(sT.find(var.getName()));
		if (var.getDefinition() == null) {
			new ErrorType(var.getLine(), var.getColumn(), "asdasda");
		}
		super.visit(var, param);
		return null;
	}

}
