package codeGeneration;

import ast.Program;
import ast.definition.Definition;
import codeGeneration.generator.CodeGenerator;
import visitor.AbstractVisitor;

public class RunVisitor extends AbstractVisitor {

	private MetadataVisitor metadata;
	private ExecuteVisitor execute;
	private CodeGenerator codeGenerator;
	private String file;

	public RunVisitor(String file) {
		codeGenerator = new CodeGenerator();
		metadata = new MetadataVisitor(codeGenerator);
		execute = new ExecuteVisitor(codeGenerator);
		this.file = file;
	}

	@Override
	public Object visit(Program program, Object param) {

		codeGenerator.metaDocu(file);

		for (Definition def : program.getDefinitions()) {
			def.accept(metadata, param);
		}

		codeGenerator.call("main");
		codeGenerator.halt();
		for (Definition def : program.getDefinitions()) {
			def.accept(execute, param);
		}
		codeGenerator.end();
		return null;
	}
}
