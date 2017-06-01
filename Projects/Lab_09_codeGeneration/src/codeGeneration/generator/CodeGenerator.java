package codeGeneration.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ast.type.CharType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.Type;

public class CodeGenerator {

	private int labelCount;

	private final String route = "output/output.txt";

	private BufferedWriter writer;

	public CodeGenerator() {
		labelCount = 0;
		try {
			writer = new BufferedWriter(new FileWriter(route));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void write(String instruct) {
		try {
			writer.write(instruct);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pushB(int cte) {
		write("pushb" + cte);
	}

	public void pushI(int cte) {
		write("pushi " + cte);
	}

	public void pushF(double cte) {
		write("pushf " + cte);
	}

	public void pushA(int cte) {
		write("pusha " + cte);
	}

	public void load(Subfix subfix) {
		write("load" + subfix);
	}

	public void store(Subfix subfix) {
		write("store" + subfix);
	}

	public void pop(Subfix subfix) {
		write("pop" + subfix);
	}

	public void dup(Subfix subfix) {
		write("dup" + subfix);
	}

	public void pushBP() {
		write("push bp");
	}

	public void add(Subfix subfix) {
		write("add" + subfix);
	}

	public void sub(Subfix subfix) {
		write("sub" + subfix);
	}

	public void mul(Subfix subfix) {
		write("mul" + subfix);
	}

	public void div(Subfix subfix) {
		write("div" + subfix);
	}

	public void mod(Subfix subfix) {
		write("mod" + subfix);
	}

	public void and() {
		write("and");
	}

	public void or() {
		write("or");
	}

	public void not() {
		write("not");
	}

	public void greaterThan(Subfix subfix) {
		write("gt" + subfix);
	}

	public void lessThan(Subfix subfix) {
		write("lt" + subfix);
	}

	public void greaterEqualThan(Subfix subfix) {
		write("ge" + subfix);
	}

	public void lessEqualThan(Subfix subfix) {
		write("le" + subfix);
	}

	public void equal(Subfix subfix) {
		write("eq" + subfix);
	}

	public void notEqual(Subfix subfix) {
		write("ne" + subfix);
	}

	public void in(Subfix subfix) {
		write("in" + subfix);
	}

	public void out(Subfix subfix) {
		write("out" + subfix);
	}

	public void a2b(Type originalType, Type finalType) {
		if (originalType instanceof CharType) {
			if (finalType instanceof IntType) {
				write("b2i");
			} else if (finalType instanceof RealType) {
				write("b2i");
				write("i2f");
			}
		} else if (originalType instanceof IntType) {
			if (finalType instanceof CharType) {
				write("i2b");
			} else if (finalType instanceof RealType) {
				write("i2f");
			}
		} else if (originalType instanceof RealType) {
			if (finalType instanceof IntType) {
				write("f2i");
			} else if (finalType instanceof CharType) {
				write("f2i");
				write("i2b");
			}
		}
	}

	public void jmp(String label) {
		write("jmp " + label);
	}

	public void jz(String label) {
		write("jz " + label);

	}

	public void jnz(String label) {
		write("jnz " + label);

	}

	public void call(String label) {
		write("call " + label);

	}

	public void ret(int retSize, int varsLocalSize, int paramsSize) {
		write("ret " + retSize + ", " + varsLocalSize + ", " + paramsSize);

	}

	public void enter(int cte) {
		write("enter " + cte);

	}

	public void halt() {
		write("halt");

	}

	public void nop() {
		write("nop");

	}

	public long getLabelCount() {
		return labelCount++;
	}

	public void label(String label) {
		write(label + ":");

	}

	public void metaVariable(String ident, String type) {
		comment("var " + ident + ":" + type);
	}

	public void metaFunction(String ident) {
		comment("func " + ident);
	}

	public void metaParam(String ident, String type) {
		comment("param " + ident + ":" + type);
	}

	public void metaReturn(String type) {
		comment("ret " + type);
	}

	public void metaLocal(String ident, String type) {
		comment("local " + ident + ":" + type);
	}

	public void metaStruct(String ident, String fields) {
		comment("type " + ident + ": {" + fields + "}");
	}

	public void arithmeticOperator(String operator, Subfix subfix) {
		if (operator.equals("*")) {
			mul(subfix);
			return;
		}

		if (operator.equals("/")) {
			div(subfix);
			return;
		}

		if (operator.equals("%")) {
			mod(subfix);
			return;
		}

		if (operator.equals("+")) {
			add(subfix);
			return;
		}

		if (operator.equals("-")) {
			sub(subfix);
			return;
		}
	}

	public void comparisonOperator(String operator, Subfix subfix) {
		if (operator.equals("gt")) {
			greaterThan(subfix);
			return;
		}

		if (operator.equals("lt")) {
			lessThan(subfix);
			return;
		}

		if (operator.equals("ge")) {
			greaterEqualThan(subfix);
			return;
		}

		if (operator.equals("le")) {
			lessEqualThan(subfix);
			return;
		}

		if (operator.equals("eq")) {
			equal(subfix);
			return;
		}

		if (operator.equals("ne")) {
			notEqual(subfix);
			return;
		}
	}

	public void logicOperator(String operator) {
		if (operator.equals("and")) {
			and();
			return;
		}

		if (operator.equals("or")) {
			or();
			return;
		}
	}

	public void comment(String comment) {
		write("#" + comment);

	}

	public void end() {
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
