import java.io.FileReader;
import java.io.IOException;

import ast.error.ErrorHandler;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import parser.Parser;
import scanner.Scanner;

public class Main {
	public static void main(String args[]) throws IOException {
	      if (args.length<1) {
	        System.err.println("Pass me the name of the input file.");
	        return;
	    }
	        
		FileReader fr=null;
		try {
			fr=new FileReader(args[0]);
		} catch(IOException io) {
			System.err.println("The file "+args[0]+" could not be opened.");
			return;
		}
		
		Scanner scanner = new Scanner(fr);
		Parser parser = new Parser(scanner);
		parser.run();	
						
		if(!ErrorHandler.getInstance().anyError()){			
			ErrorHandler.getInstance().showErrors(System.err);
		}
		else{						
			IntrospectorModel model=new IntrospectorModel("Program",parser.getRoot());
			new IntrospectorTree("Introspector", model);		
		}
	}

}