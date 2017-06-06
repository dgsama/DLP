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

	/**
	 * Create a list with all the names of the files that we wont to prove. The
	 * use of this is oriented to pass to the compiler wrong files. If you want
	 * to pass right files (in theory) pass one by one to avoid performance
	 * problems.
	 * 
	 * @return list: files name
	 */
	private static String[] getFiles() {
		String[] files = { "input.txt" };
		return files;
	}

	public static void main(String args[]) throws IOException {

		FileReader fr = null;
		String[] files = getFiles();
		if (files.length <= 0)
			System.err.println("Please, pass me any file.");

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

			System.out.print("\nNo errors in the SYNTACTIC phase\n");

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
			
			 System.out.print("\nNo errors in the SEMANTIC phase\n");
			
			 parser.getRoot().accept(new OffsetVisitor(), null);
			
			 parser.getRoot().accept(new RunVisitor(), null);

			IntrospectorModel model = new IntrospectorModel("Program", parser.getRoot());
			new IntrospectorTree("Introspector", model);

		}
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