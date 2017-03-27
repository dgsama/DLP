package semantic;

import ast.definition.DefField;
import ast.definition.DefFunc;
import ast.definition.DefVar;
import ast.definition.Definition;
import ast.expression.ArrayAccess;
import ast.expression.InvocationExp;
import ast.expression.Expression;
import ast.expression.StructAccess;
import ast.expression.Variable;
import ast.statement.InvocationSt;
import ast.type.ArrayType;
import ast.type.ErrorType;
import ast.type.StructType;
import semantic.symbolTable.SymbolTable;

public class IdentificationVisitor extends AbstractVisitor {

	private SymbolTable sT;

	public IdentificationVisitor() {
		super();
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
	public Object visit(DefVar def, Object param) {
		if (!sT.insert(def)) {
			new ErrorType(def.getLine(), def.getColumn(),
					"The variable " + def.getId() + " already exists in this scope.");
		}
		//super.visit(def, param);
		return null;
	}

	@Override
	public Object visit(Variable var, Object param) {
		Definition aux = sT.find(var.getName());
		if (aux == null) {
			new ErrorType(var.getLine(), var.getColumn(), "The variable " + var.getName() + " is not declared.");
		} else {
			var.setDefinition(aux);
		}
		return aux;
	}

	@Override
	public Object visit(ArrayAccess exp, Object param) {
		exp.getIndex().accept(this, param);
		return exp.getName().accept(this, param);
	}

	@Override
	public Object visit(InvocationExp exp, Object param) {
		Definition aux = sT.find(exp.getName());
		if (aux == null) {
			new ErrorType(exp.getLine(), exp.getColumn(), "The function " + exp.getName() + " is not declared.");
		} else {
			exp.setDefinition(aux);
		}
		for (Expression each : exp.getParameters()) {
			each.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(InvocationSt cF, Object param) {
		Definition aux = sT.find(cF.getName());
		if (aux == null) {
			new ErrorType(cF.getLine(), cF.getColumn(), "The function " + cF.getName() + " is not declared.");
		} else {
			cF.setDefinition(aux);
		}
		for (Expression each : cF.getParameters()) {
			each.accept(this, param);
		}

		return null;
	}

	@Override
	public Object visit(ArrayType aT, Object param) {
		return aT.getTypeOf().accept(this, param);
	}

	@Override
	public Object visit(StructType structT, Object param) {
		for (Definition each : structT.getFieldsDefinitions()) {
			if (each.getId().equals(param.toString())) {
				return each;
			}
		}
		return null;
	}

	@Override
	public Object visit(StructAccess exp, Object param) {
		Definition aux = (Definition) exp.getName().accept(this, param);
		Definition object = null;
		if (aux != null) {
			object = (Definition) aux.getType().accept(this, exp.getField());
			if (object == null) {
				new ErrorType(exp.getLine(), exp.getColumn(), "The field doesn´t exists");
			}
		} else {
			new ErrorType(exp.getLine(), exp.getColumn(), "The struct doesn´t exists");
		}
		return object;
	}

}
