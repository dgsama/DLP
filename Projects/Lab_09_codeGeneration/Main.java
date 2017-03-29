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

public class Main {
	public static void main(String args[]) throws IOException {
		if (args.length < 1) {
			System.err.println("Pass me the name of the input file.");
			return;
		}

		FileReader fr = null;
		String[] files = { "wrong.input.txt", "wrong.input.1.txt", "wrong.input.2.txt", "wrong.input.3.txt",
				"wrong.input.4.txt", "wrong.input.5.txt" };
		for (int i = 0; i < files.length; i++) {
			System.out.println("\n\n\t File: " + files[i] + "\n");
			try {
				fr = new FileReader(files[i]);
			} catch (IOException io) {
				System.err.println("The file " + args[0] + " could not be opened.");
			}

			Scanner scanner = new Scanner(fr);
			Parser parser = new Parser(scanner);
			parser.run();
			if (!checkErrors("SYNTAX", files[i])) {
				parser.getRoot().accept(new LValueVisitor(), null);
				if (!checkErrors("SEMANTIC", files[i])) {
					parser.getRoot().accept(new IdentificationVisitor(), null);
					if (!checkErrors("SEMANTIC", files[i])) {
						IntrospectorModel model = new IntrospectorModel("Program", parser.getRoot());
						new IntrospectorTree("Introspector", model);
					}
				}
			}
		}
	}

	private static boolean checkErrors(String phase, String file) throws IOException {
		if (!ErrorHandler.getInstance().anyError()) {
			ErrorHandler.getInstance().showErrors(System.err);
			ErrorFileManager.getInstance().setPhase(phase);
			ErrorFileManager.getInstance().setFile(file);
			ErrorFileManager.getInstance().createErrorLog();
			return true;

		} else {
			ErrorHandler.getInstance().getErrorsList().clear();
			return false;
		}
	}

}