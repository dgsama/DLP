import parser.Parser;
import java.io.FileReader;
import java.io.IOException;
import ast.error.ErrorHandler;
import scanner.Scanner;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import io.ErrorFileManager;

public class Main {

	public static void main(String args[]) throws IOException {
		if (args.length < 1) {
			System.err.println("Pass me the name of the input file.");
			return;
		}

		FileReader fr = null;
		try {
			fr = new FileReader(args[0]);
		} catch (IOException io) {
			System.err.println("The file " + args[0] + " could not be opened.");
			return;
		}

		Scanner scanner = new Scanner(fr);
		if (!ErrorHandler.getInstance().anyError()) {
			ErrorHandler.getInstance().showErrors();
			return;
		}
		Parser parser = new Parser(scanner);
		parser.run();
		if (!ErrorHandler.getInstance().anyError()) {
			ErrorHandler.getInstance().showErrors();
			ErrorFileManager.getInstance().setPhase("SYNTAX");
			ErrorFileManager.getInstance().createErrorLog();
			return;
		}
		IntrospectorModel modelo = new IntrospectorModel("Program", parser.getRoot());
		new IntrospectorTree("Introspector", modelo);
	}
}