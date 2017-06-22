import java.io.FileReader;
import java.io.IOException;

import codeGeneration.OffsetVisitor;
import codeGeneration.RunVisitor;
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
		String file = "big-input.txt";

		System.out.println("\n\n\t File: " + file + "\n");
		try {
			fr = new FileReader(file);
		} catch (IOException io) {
			System.err.println("The file " + file + " could not be opened.");
		}

		Scanner scanner = new Scanner(fr);
		Parser parser = new Parser(scanner);

		/** SYNTATIC PHASE **/
		parser.run();
		if (checkErrors("SYNTACTIC", file))
			return;

		System.out.print("\nNo errors in the SYNTACTIC phase\n");

		/** SEMANTIC PHASE **/

		parser.getRoot().accept(new LValueVisitor(), null);
		if (checkErrors("SEMANTIC (LValue)", file))
			return;
		parser.getRoot().accept(new IdentificationVisitor(), null);
		if (checkErrors("SEMANTIC (Identification)", file))
			return;
		parser.getRoot().accept(new TypeVisitor(), null);
		if (checkErrors("SEMANTIC (Type checking)", file))
			return;

		System.out.print("\nNo errors in the SEMANTIC phase\n");

		/** CODE GENERATION **/
		parser.getRoot().accept(new OffsetVisitor(), null);
		parser.getRoot().accept(new RunVisitor(file), null);

		System.out.print("\nOutput created\n");
		System.out.print("\nLaunching Introspector\n");
		
		/** INSTROSPECTOR **/
		IntrospectorModel model = new IntrospectorModel("Program", parser.getRoot());
		new IntrospectorTree("Introspector", model);

	}

	/**
	 * Check if there are any errors in this phase to stop or continue the
	 * execution. If there are any errors they are shown in the console and a
	 * log file is created for every file. In every call the last task is clear
	 * the errorList to avoid conflicts.
	 * 
	 * @param phase
	 * @param file
	 * @return boolean: true = errors, false = not errors
	 * @throws IOException
	 */
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