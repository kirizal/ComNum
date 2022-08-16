import ConsoleUtils.ConCursorMove;
import ConsoleUtils.ConProgressBar;
import ConsoleUtils.ConColor;
import ProgramFunctionality.Algorithms.ShuntingYardAlgorithm;
import ProgramFunctionality.Algorithms.Token;
import ProgramFunctionality.FileManager;

import java.util.ArrayList;


public class Program {
	public static void main(String[] args) {
		try {
			ArrayList<Token> tokenArr;
			ConCursorMove.Right(20);
			System.out.println(args[0]);
			FileManager fm = new FileManager(args[0]);
			for(String line: fm.getTextFromCurrentFile()){
				System.out.println(line);
				tokenArr = ShuntingYardAlgorithm.Tokenization(line);
				for(Token token: tokenArr){
					System.out.println(token.toString());
				}
			}



		}
		catch(Exception ex) {
			System.out.println("\n" + ex.getMessage());
		}
	}
}