package ast;

import introspector.view.IntrospectorTree;
import introspector.model.IntrospectorModel;

import java.util.*;

public class ASTTest {

	/**
	 * The input program is: 
	 *   read a,b; 
	 *   a = (-b+3)*c/2; 
	 *   write a,c*2;
	 */
	private static ASTNode createAST() {
		List<Statement> statements = new ArrayList<Statement>();
		
		// * First line
		Expression expression = new Variable(1, 6, "a");
		statements.add(new Read(1, 6, expression));
		
		expression = new Variable(1, 8, "b");
		statements.add(new Read(1, 8, expression));
		
		// * Second Line
		Statement statement = new Assignment(2, 3, 
				new Variable(2, 1, "a"),			
				new Arithmetic(2, 13,
						new Arithmetic(2, 11,
							new Arithmetic(2, 8, 
									new UnaryMinus(2, 6, 
											new Variable(2, 7, "b")
											),
									"+",
									new IntLiteral(2, 9, 3)
									),
							"*",
							new Variable(2, 12, "c")
							),
						"/",
						new IntLiteral(2, 14, 2)
						)
				);
		statements.add(statement);
		
		// * Third line
		expression = new Variable(3, 7, "a");
		statements.add(new Write(3, 7, expression));
		
		expression = new Arithmetic(3, 10, 
				new Variable(3, 9, "c"),
				"*",
				new IntLiteral(3, 11, 2)
				);
		statements.add(new Write(3, 9, expression));
		
		// * We build and return the AST
		return new Program(1, 1, statements);
	}

	public static void main(String[] args) {
		IntrospectorModel model = new IntrospectorModel("Program", createAST());
		new IntrospectorTree("Introspector", model);
	}
}
