package codeGeneration.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ast.type.CharType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.Type;
import codeGeneration.RunVisitor;

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
		write("\tpushb " + cte);
	}

	public void pushI(int cte) {
		write("\tpushi " + cte);
	}

	public void pushF(double cte) {
		write("\tpushf " + cte);
	}

	public void pushA(int cte) {
		write("\tpusha " + cte);
	}

	public void load(Subfix subfix) {
		write("\tload" + subfix);
	}

	public void store(Subfix subfix) {
		write("\tstore" + subfix);
	}

	public void pop(Subfix subfix) {
		write("\tpop" + subfix);
	}

	public void dup(Subfix subfix) {
		write("\tdup" + subfix);
	}

	public void pushBP() {
		write("\tpush bp");
	}

	public void add(Subfix subfix) {
		write("\tadd" + subfix);
	}

	public void sub(Subfix subfix) {
		write("\tsub" + subfix);
	}

	public void mul(Subfix subfix) {
		write("\tmul" + subfix);
	}

	public void div(Subfix subfix) {
		write("\tdiv" + subfix);
	}

	public void mod(Subfix subfix) {
		write("\tmod" + subfix);
	}

	public void and() {
		write("\tand");
	}

	public void or() {
		write("\tor");
	}

	public void not() {
		write("\tnot");
	}

	public void greaterThan(Subfix subfix) {
		write("\tgt" + subfix);
	}

	public void lessThan(Subfix subfix) {
		write("\tlt" + subfix);
	}

	public void greaterEqualThan(Subfix subfix) {
		write("\tge" + subfix);
	}

	public void lessEqualThan(Subfix subfix) {
		write("\tle" + subfix);
	}

	public void equal(Subfix subfix) {
		write("\teq" + subfix);
	}

	public void notEqual(Subfix subfix) {
		write("\tne" + subfix);
	}

	public void in(Subfix subfix) {
		write("\tin" + subfix);
	}

	public void out(Subfix subfix) {
		write("\tout" + subfix);
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
		write("\tjmp " + label);
	}

	public void jz(String label) {
		write("\tjz " + label);

	}

	public void jnz(String label) {
		write("\tjnz " + label);

	}

	public void call(String label) {
		write("call " + label);

	}

	public void ret(int retSize, int varsLocalSize, int paramsSize) {
		write("\tret " + retSize + ", " + varsLocalSize + ", " + paramsSize);

	}

	public void enter(int cte) {
		write("\tenter " + cte);

	}

	public void halt() {
		write("\thalt");

	}

	public void nop() {
		write("\tnop");

	}

	public long getLabelCount() {
		return labelCount++;
	}

	public void label(String label) {
		write(" " + label + ":");

	}

	public void metaDocu() {
		comment("source " + "");
	}

	public void metaVariable(String ident, String type, int i) {
		write("\t' * " + type + " " + ident + " (Offset " + i + ")");
	}

	public void metaFunction(String ident) {
		write("func " + ident);
	}

	public void preMetaParam() {
		write("\t' * Parameters");
	}

	public void metaParam(String ident, String type, int i) {
		write("\t' * " + type + " " + ident + " (Offset " + i + ")");
	}

	public void metaReturn(String type) {
		write("\tret " + type);
	}

	public void preMetaLocal() {
		write("\t' * Local variables");
	}

	public void metaLocal(String ident, String type, int i) {
		write("\t' * " + type + " " + ident + " (Offset " + i + ")");
	}

	public void metaStruct(String ident, String fields) {
		write("type " + ident + ": {" + fields + "}");
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