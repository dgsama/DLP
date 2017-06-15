package semantic.symbolTable;

import java.util.*;
import ast.definition.Definition;

public class SymbolTable {

	private int scope;
	private List<Map<String, Definition>> table;

	public SymbolTable() {
		scope = 0;
		table = new ArrayList<>();
		table.add(new HashMap<>());
	}

	public void set() {
		scope++;
		table.add(new HashMap<>());
	}

	public void reset() {
		table.remove(scope);
		scope--;
	}

	public boolean insert(Definition definition) {
		if (findInCurrentScope(definition.getId()) != null) {
			return false;
		} else {
			definition.setScope(scope);
			table.get(scope).put(definition.getId(), definition);
			return true;
		}
	}

	public Definition find(String id) {
		for (int i = scope; i >= 0; i--) {
			Definition def = table.get(i).get(id);
			if (def != null) {
				return def;
			}
		}
		return null;
	}

	public Definition findInCurrentScope(String id) {
		return table.get(scope).get(id);
	}
}
