import java.io.FileReader;
import java.io.IOException;

import error.ErrorHandler;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import io.ErrorFileManager;
import parser.Parser;
import scanner.Scanner;
import semantic.IdentificationVisitor;
import semantic.LValueVisitor;
import semantic.TypeVisitor;

public class Main {
	public static void main(String args[]) throws IOException {

		FileReader fr = null;
		String[] files = getFiles();
		if (files.length <= 0)
			System.err.println("Pass me any file, ¡gandul!");
		for (int i = 0; i < files.length; i++) {
			System.out.println("\n\n\t File: " + files[i] + "\n");
			try {
				fr = new FileReader(files[i]);
			} catch (IOException io) {
				System.err.println("The file " + args[0] + " could not be opened.");
			}

			Scanner scanner = new Scanner(fr);
			Parser parser = new Parser(scanner);

			/** SYNTATIC PHASE **/
			parser.run();
			if (checkErrors("SYNTACTIC", files[i]))
				continue;
			/** SEMANTIC PHASE **/
			parser.getRoot().accept(new LValueVisitor(), null);
			if (checkErrors("SEMANTIC (LValue)", files[i]))
				continue;
			parser.getRoot().accept(new IdentificationVisitor(), null);
			if (checkErrors("SEMANTIC (Identification)", files[i]))
				continue;
			parser.getRoot().accept(new TypeVisitor(), null);
			if (checkErrors("SEMANTIC (Type checking)", files[i]))
				continue;

			IntrospectorModel model = new IntrospectorModel("Program", parser.getRoot());
			new IntrospectorTree("Introspector", model);

		}
	}

	private static String[] getFiles() {
		String[] files = { "SemanticErrorFiles/wrong.input.2.txt"/*
																	 * ,
																	 * "SemanticErrorFiles/wrong.input.1.txt",
																	 * "SemanticErrorFiles/wrong.input.2.txt",
																	 * "SemanticErrorFiles/wrong.input.3.txt",
																	 * "SemanticErrorFiles/wrong.input.4.txt",
																	 * "SemanticErrorFiles/wrong.input.5.txt"
																	 */ };
		return files;
	}

	private static boolean checkErrors(String phase, String file) throws IOException {
		if (!ErrorHandler.getInstance().anyError()) {
			ErrorHandler.getInstance().showErrors(System.err);
			ErrorFileManager.getInstance().setPhase(phase);
			ErrorFileManager.getInstance().setFile(file);
			ErrorFileManager.getInstance().createErrorLog();
			ErrorHandler.getInstance().getErrorsList().clear();
			return true;

		} else {
			ErrorHandler.getInstance().getErrorsList().clear();
			return false;
		}
	}

}